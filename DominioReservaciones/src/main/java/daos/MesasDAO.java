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
import javax.persistence.EntityManager;

/**
 * Implementación del DAO para la gestión de mesas en un restaurante.
 * Proporciona métodos para acceder, insertar, modificar y eliminar mesas.
 * 
 * @author caarl
 */
public class MesasDAO implements IMesasDAO {

    private final IConexion conexion;

    /**
     * Constructor de la clase.
     * 
     * @param conexion la conexión a la base de datos.
     */
    public MesasDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Obtiene todas las mesas de un restaurante.
     * 
     * @param idRestaurante el ID del restaurante del cual obtener las mesas.
     * @return una lista de mesas asociadas al restaurante.
     * @throws DAOException si ocurre un error al obtener las mesas.
     */
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

    /**
     * Obtiene las mesas de un restaurante filtradas por tipo.
     * 
     * @param idRestaurante el ID del restaurante del cual obtener las mesas.
     * @param tipo el tipo de mesa (pequeña, mediana, grande).
     * @return una lista de mesas del tipo especificado.
     * @throws DAOException si ocurre un error al obtener las mesas por tipo.
     */
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

    /**
     * Elimina una mesa de un restaurante por su código.
     * 
     * @param idRestaurante el ID del restaurante.
     * @param codigo el código de la mesa a eliminar.
     * @throws DAOException si ocurre un error al eliminar la mesa.
     */
    @Override
    public void eliminarMesa(Long idRestaurante, String codigo) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            TypedQuery<Mesa> consulta = entityManager.createQuery(
                "SELECT m FROM Mesa m WHERE m.codigo = :codigo AND m.restaurante.id = :idRestaurante", 
                Mesa.class
            );
            consulta.setParameter("codigo", codigo);
            consulta.setParameter("idRestaurante", idRestaurante);

            Mesa mesa = consulta.getSingleResult();

            if (mesa == null) {
                throw new DAOException("No se encontró la mesa con el código dado");
            }

            entityManager.remove(mesa);
            transaction.commit();
        } catch (NoResultException e) {
            if (transaction.isActive()) transaction.rollback();
            throw new DAOException("No se encontró la mesa a eliminar");
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new DAOException("Error al eliminar la mesa: " + e.getMessage());
        }
    }

    /**
     * Obtiene las mesas disponibles en un restaurante.
     * Las mesas se consideran disponibles si no están reservadas ni bloqueadas.
     * 
     * @param idRestaurante el ID del restaurante del cual obtener las mesas disponibles.
     * @return una lista de mesas disponibles.
     * @throws DAOException si ocurre un error al obtener las mesas disponibles.
     */
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

    /**
     * Inserta una nueva mesa en la base de datos.
     * Si ya existe una mesa con el mismo código, se genera un nuevo código incrementando el número.
     * 
     * @param mesa la mesa a insertar.
     * @throws DAOException si ocurre un error al insertar la mesa.
     */
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

            // Verificar si el restaurante existe
            Restaurante restaurante = entityManager.find(Restaurante.class, mesa.getRestaurante().getId());
            if (restaurante == null) {
                throw new DAOException("El restaurante especificado no existe");
            }
            mesa.setRestaurante(restaurante);

            // Obtener el código original
            String codigoOriginal = mesa.getCodigo();
            String nuevoCodigo = codigoOriginal;
            
            // Consulta para obtener todas las mesas que tienen el mismo prefijo de código
            TypedQuery<Mesa> query = entityManager.createQuery(
                "SELECT m FROM Mesa m WHERE m.codigo LIKE :codigo", Mesa.class);
            query.setParameter("codigo", codigoOriginal.substring(0, codigoOriginal.lastIndexOf('-') + 1) + "%");
            List<Mesa> mesasExistentes = query.getResultList();

            // Si ya existe una mesa con el código, incrementar el número final del código
            if (!mesasExistentes.isEmpty()) {
                int maxNumber = 0;
                // Buscar el máximo número final del código
                for (Mesa m : mesasExistentes) {
                    String codigoMesa = m.getCodigo();
                    String[] partes = codigoMesa.split("-");
                    int numero = Integer.parseInt(partes[2]);
                    if (numero > maxNumber) {
                        maxNumber = numero;
                    }
                }
                // Incrementar el número final
                maxNumber++;
                // Generar el nuevo código con el número incrementado
                nuevoCodigo = codigoOriginal.substring(0, codigoOriginal.lastIndexOf('-') + 1) + String.format("%03d", maxNumber);
                mesa.setCodigo(nuevoCodigo);
            }

            // Asignar el nuevo código a la mesa
            mesa.setCodigo(nuevoCodigo);

            // Persistir la mesa con el nuevo código generado
            entityManager.persist(mesa);

            // Forzar la sincronización de los cambios con la base de datos
            entityManager.flush();

            // Verificar que la mesa se haya guardado correctamente
            Mesa mesaGuardada = entityManager.find(Mesa.class, mesa.getId());

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
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    /**
     * Modifica los datos de una mesa en un restaurante.
     * 
     * @param idRestaurante el ID del restaurante.
     * @param codigo el código de la mesa a modificar.
     * @param nuevoTipo el nuevo tipo de mesa.
     * @param nuevaUbicacion la nueva ubicación de la mesa.
     * @throws DAOException si ocurre un error al modificar la mesa.
     */
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
