/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.DAOException;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Mesa;
import entidades.Restaurante;
import entidades.TipoMesa;
import entidades.UbicacionMesa;
import idaos.IMesasDAO;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.EntityManager;

/**
 * DAO para la gestión de mesas.
 * @author caarl
 */
public class MesasDAO implements IMesasDAO {

    private final  IConexion conexion;

    // Constructor con inyección de dependencias

    public MesasDAO(Conexion conexion) {
        this.conexion = conexion;
    }


    @Override
    public List<Mesa> obtenerMesasTodas(Long idRestaurante) throws DAOException {
        try {
            TypedQuery<Mesa> query = conexion.crearConexion().createQuery(
                "SELECT m FROM Mesa m WHERE m.restaurante.id = :idRestaurante", 
                Mesa.class
            );
            query.setParameter("idRestaurante", idRestaurante);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener todas las mesas: " + e.getMessage());
        }
    }

    @Override
    public List<Mesa> obtenerMesasPorTipo(Long idRestaurante, TipoMesa tipo) throws DAOException {
        try {
            TypedQuery<Mesa> query = conexion.crearConexion().createQuery(
                "SELECT m FROM Mesa m WHERE m.tipoMesa = :tipo AND m.restaurante.id = :idRestaurante", 
                Mesa.class
            );
            query.setParameter("tipo", tipo);
            query.setParameter("idRestaurante", idRestaurante);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener mesas por tipo: " + e.getMessage());
        }
    }

    @Override
    public void eliminarMesa(Long idRestaurante, String codigo) throws DAOException {
        EntityTransaction transaction = conexion.crearConexion().getTransaction();
        try {
            transaction.begin();

            TypedQuery<Mesa> consulta = conexion.crearConexion().createQuery(
                "SELECT m FROM Mesa m WHERE m.codigo = :codigo AND m.restaurante.id = :idRestaurante", 
                Mesa.class
            );
            consulta.setParameter("codigo", codigo);
            consulta.setParameter("idRestaurante", idRestaurante);

            Mesa mesa = consulta.getSingleResult();

            if (mesa == null) {
                throw new DAOException("No se encontró la mesa con el código dado");
            }

            conexion.crearConexion().remove(mesa);
            transaction.commit();
        } catch (NoResultException e) {
            if (transaction.isActive()) transaction.rollback();
            throw new DAOException("No se encontró la mesa a eliminar");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new DAOException("Error al eliminar la mesa: " + e.getMessage());
        }
    }

    


    @Override
    public List<Mesa> obtenerMesasDisponibles(Long idRestaurante) throws DAOException {
        try {
            String jpql = "SELECT m FROM Mesa m WHERE m.restaurante.id = :idRestaurante " +
                          "AND (m.fechaNuevaDisponibilidad IS NULL OR m.fechaNuevaDisponibilidad <= :fechaActual) " +
                          "AND NOT EXISTS (SELECT r FROM Reservaciones r WHERE r.mesa = m AND r.estado LIKE 'PENDIENTE')";
            TypedQuery<Mesa> query = conexion.crearConexion().createQuery(jpql, Mesa.class);
            query.setParameter("idRestaurante", idRestaurante);
            query.setParameter("fechaActual", LocalDateTime.now());
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new DAOException("Error al obtener mesas disponibles: " + e.getMessage());
        }
    }
    
    
  @Override
public void insertarMesa(Mesa mesa) throws DAOException {
    EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
 

    if (mesa == null || mesa.getUbicacion() == null || mesa.getTipoMesa() == null || mesa.getCodigo() == null) {
        throw new DAOException("Parámetros inválidos para la inserción de la mesa");
    }

    try {
        // Iniciar la transacción
        transaction.begin();

        Restaurante restaurante = entityManager.find(Restaurante.class, mesa.getRestaurante().getId());
        if (restaurante == null) {
            throw new DAOException("El restaurante especificado no existe");
        }
        mesa.setRestaurante(restaurante);

        // Persistir la mesa con el código generado
        entityManager.persist(mesa);
        
        // Forzar la sincronización de los cambios con la base de datos
       entityManager.flush();

        // Verificar que la mesa se haya guardado correctamente
        TypedQuery<Mesa> query =entityManager.createQuery(
            "SELECT m FROM Mesa m WHERE m.codigo = :codigo", Mesa.class);
        query.setParameter("codigo", mesa.getCodigo());
        
        Mesa mesaGuardada = query.getSingleResult();

        // Si llegamos aquí, la mesa se ha guardado correctamente
        System.out.println("Mesa guardada correctamente: " + mesaGuardada);

        // Commit de la transacción
        transaction.commit();
    } catch (Exception e) {
        // Si ocurre un error, revertir la transacción
        if (transaction.isActive()) {
            transaction.rollback();
        } 
        throw new DAOException("Error al insertar la mesa: " + e.getMessage());
    }finally{
        if (entityManager.isOpen()) {
                entityManager.close();
            }
    }
}

@Override
public void modificarMesa(Long idRestaurante, String codigo, TipoMesa nuevoTipo, UbicacionMesa nuevaUbicacion) throws DAOException {
    EntityTransaction transaction = conexion.crearConexion().getTransaction();

    if (codigo == null || nuevoTipo == null || nuevaUbicacion == null) {
        throw new DAOException("Parámetros inválidos para modificar una mesa");
    }

    try {
        transaction.begin();

        // Buscar la mesa existente
        TypedQuery<Mesa> consulta = conexion.crearConexion().createQuery(
            "SELECT m FROM Mesa m WHERE m.codigo = :codigo AND m.restaurante.id = :idRestaurante", 
            Mesa.class
        );
        consulta.setParameter("codigo", codigo);
        consulta.setParameter("idRestaurante", idRestaurante);

        Mesa mesa = consulta.getSingleResult();

        if (mesa == null) {
            throw new DAOException("No se encontró la mesa con el código especificado");
        }

        // Actualizar los campos de la mesa
        mesa.setTipoMesa(nuevoTipo);
        mesa.setUbicacion(nuevaUbicacion);

        // Guardar los cambios
        conexion.crearConexion().merge(mesa);

        transaction.commit();
    } catch (NoResultException e) {
        if (transaction.isActive()) transaction.rollback();
        throw new DAOException("No se encontró la mesa a modificar");
    } catch (Exception e) {
        if (transaction.isActive()) transaction.rollback();
        throw new DAOException("Error al modificar la mesa: " + e.getMessage());
    }
}

}
