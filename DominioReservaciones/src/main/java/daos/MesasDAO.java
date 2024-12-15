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
public void insertarMesas(Long idRestaurante, TipoMesa tipo, UbicacionMesa ubicacion, int cantidad) throws DAOException {
    EntityTransaction transaction = conexion.crearConexion().getTransaction();

    // Validar que los parámetros no sean nulos o incorrectos
    if (ubicacion == null) {
        throw new DAOException("La ubicación no puede ser nula.");
    }
    if (cantidad < 1) {
        throw new DAOException("La cantidad de mesas debe ser mayor o igual a 1.");
    }
    if (tipo == null) {
        throw new DAOException("El tipo de mesa no puede ser nulo.");
    }

    // Verificar que el restaurante exista
    Restaurante restaurante = conexion.crearConexion().find(Restaurante.class, idRestaurante);
    if (restaurante == null) {
        throw new DAOException("El restaurante especificado no existe.");
    }

    // Validar los atributos del tipo de mesa
    if (tipo.getNombre() == null || tipo.getNombre().trim().isEmpty()) {
        throw new DAOException("El nombre del tipo de mesa no puede estar vacío.");
    }
    if (tipo.getMaximoPersonas() == null || tipo.getMaximoPersonas() < 1) {
        throw new DAOException("El número máximo de personas debe ser mayor o igual a 1.");
    }
    if (tipo.getMinimoPersonas() == null || tipo.getMinimoPersonas() < 1) {
        throw new DAOException("El número mínimo de personas debe ser mayor o igual a 1.");
    }
    if (tipo.getPrecio() == null || tipo.getPrecio() <= 0) {
        throw new DAOException("El precio del tipo de mesa debe ser mayor que 0.");
    }

    try {
        transaction.begin();
        for (int i = 0; i < cantidad; i++) {
            Mesa mesa = new Mesa();
            String codigoMesa = generarCodigoMesa(ubicacion, tipo);
            
            // Validar que el código de la mesa no esté vacío
            if (codigoMesa == null || codigoMesa.trim().isEmpty()) {
                throw new DAOException("El código de la mesa generado es inválido.");
            }

            mesa.setCodigo(codigoMesa);
            mesa.setTipoMesa(tipo);
            mesa.setUbicacion(ubicacion);
            mesa.setRestaurante(restaurante);

            // Persistir la mesa
            conexion.crearConexion().persist(mesa);
        }
        transaction.commit();
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        throw new DAOException("Error al insertar mesas: " + e.getMessage());
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

    // Método auxiliar para generar códigos únicos de mesas
    private String generarCodigoMesa(UbicacionMesa ubicacion, TipoMesa tipo) {
        while (true) {
            int numeroRandom = ThreadLocalRandom.current().nextInt(0, 999);
            String codigo = String.format("%3s-%d-%03d", ubicacion.toString().substring(0, 3), tipo.getMaximoPersonas(), numeroRandom);

            Long conteo = conexion.crearConexion().createQuery(
                "SELECT COUNT(m) FROM Mesa m WHERE m.codigo = :codigo", Long.class)
                .setParameter("codigo", codigo)
                .getSingleResult();

            if (conteo == 0) return codigo;
        }
    }
    
   @Override
public void insertarMesa(Mesa mesa) throws DAOException {
    EntityTransaction transaction = conexion.crearConexion().getTransaction();

    if (mesa == null || mesa.getUbicacion() == null || mesa.getTipoMesa() == null) {
        throw new DAOException("Parámetros inválidos para la inserción de la mesa");
    }

    try {
        transaction.begin();

        Restaurante restaurante = conexion.crearConexion().find(Restaurante.class, mesa.getRestaurante().getId());
        if (restaurante == null) {
            throw new DAOException("El restaurante especificado no existe");
        }
        mesa.setRestaurante(restaurante);

        // Validar código único
        String codigoMesa = generarCodigoMesa(mesa.getUbicacion(), mesa.getTipoMesa());
        mesa.setCodigo(codigoMesa);

        conexion.crearConexion().persist(mesa);

        transaction.commit();
    } catch (Exception e) {
        if (transaction.isActive()) transaction.rollback();
        throw new DAOException("Error al insertar la mesa: " + e.getMessage());
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
