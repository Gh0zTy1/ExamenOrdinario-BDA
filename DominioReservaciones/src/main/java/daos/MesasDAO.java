/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.DAOException;
import conexion.Conexion;
import entidades.Mesa;
import entidades.Restaurante;
import entidades.TipoMesa;
import entidades.UbicacionMesa;
import idaos.IMesasDAO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author caarl
 */
public class MesasDAO implements IMesasDAO {

    private static IMesasDAO instancia;

    // Constructor privado para implementar el patrón Singleton
    private MesasDAO() {
    }

    /**
     * Obtiene la instancia única de MesasDAO.
     *
     * @return Instancia de MesasDAO.
     */
    public static IMesasDAO getInstance() {
        if (instancia == null) {
            instancia = new MesasDAO();
        }
        return instancia;
    }

    @Override
    public List<Mesa> obtenerMesasTodas(Long idRestaurante) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();

        try {
            // Consulta para obtener todas las mesas de un restaurante específico
            TypedQuery<Mesa> query = entityManager.createQuery(
                "SELECT m FROM Mesa m WHERE m.restaurante.id = :idRestaurante", 
                Mesa.class);
            query.setParameter("idRestaurante", idRestaurante);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener todas las mesas");
        } finally {
            entityManager.close(); // Cerrar el EntityManager al final
        }
    }

    @Override
    public List<Mesa> obtenerMesasPorTipo(Long idRestaurante, TipoMesa tipo) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();

        try {
            // Consulta para obtener mesas de un restaurante según el tipo especificado
            TypedQuery<Mesa> query = entityManager.createQuery(
                "SELECT m FROM Mesa m WHERE m.tipoMesa = :tipo AND m.restaurante.id = :idRestaurante", 
                Mesa.class);
            query.setParameter("tipo", tipo);
            query.setParameter("idRestaurante", idRestaurante);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener mesas por tipo");
        } finally {
            entityManager.close(); // Cerrar el EntityManager al final
        }
    }

    @Override
    public void eliminarMesa(Long idRestaurante, String codigo) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            // Consulta para encontrar la mesa por código y restaurante
            TypedQuery<Mesa> consulta = entityManager.createQuery(
                "SELECT m FROM Mesa m WHERE m.codigo = :codigo AND m.restaurante.id = :idRestaurante", 
                Mesa.class);
            consulta.setParameter("codigo", codigo);
            consulta.setParameter("idRestaurante", idRestaurante);
            Mesa mesa = consulta.getSingleResult(); // Obtener la mesa encontrada

            if (mesa == null) {
                throw new DAOException("No se encontró la mesa con el código dado");
            }

            transaction.begin(); // Iniciar la transacción
            entityManager.remove(mesa); // Eliminar la mesa
            transaction.commit(); // Confirmar la transacción

        } catch (NoResultException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revertir si hay un error
            }
            throw new DAOException("No se encontró la mesa a eliminar");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revertir si hay un error
            }
            throw new DAOException("No se pudo eliminar la mesa debido a un error, por favor intente más tarde");
        } finally {
            entityManager.close(); // Cerrar el EntityManager al final
        }
    }

    @Override
    public void insertarMesas(Long idRestaurante, TipoMesa tipo, UbicacionMesa ubicacion, int cantidad) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();

        UbicacionMesa ubicacionReal = UbicacionMesa.valueOf(ubicacion.toString()); // Convertir a UbicacionMesa

        // Validaciones de entrada
        if (ubicacionReal == null) {
            throw new DAOException("La ubicación de las mesas dada es incorrecta, ingrese una ubicación de mesa válida");
        }

        if (cantidad < 1) {
            throw new DAOException("La cantidad de mesas a crear debe ser de al menos 1, ingrese una cantidad correcta");
        }
        transaction.begin(); // Iniciar la transacción

        int cantidadRestante = cantidad; // Contador para las mesas restantes

        try {
            // Crear las mesas en un ciclo
            while (cantidadRestante > 0) {
                Mesa m = new Mesa();
                String codigoMesa;
                boolean codigoValido = false;

                // Generar un código de mesa único
                while (!codigoValido) {
                    int numeroRandom = ThreadLocalRandom.current().nextInt(0, 999);
                    codigoMesa = String.format("%3s-%d-%03d", ubicacionReal.toString().substring(0, 3), tipo.getMaximoPersonas(), numeroRandom);

                    // Verificar si el código ya existe
                    if (entityManager.createQuery("SELECT COUNT(m) FROM Mesa m WHERE m.codigo = :codigo", Long.class)
                            .setParameter("codigo", codigoMesa)
                            .getSingleResult() == 0) {
                        codigoValido = true; // Código válido
                        m.setCodigo(codigoMesa); // Asignar código a la mesa
                    }
                }

                m.setTipoMesa(tipo); // Asignar tipo de mesa
                m.setUbicacion(ubicacionReal); // Asignar ubicación
                
                // Obtener el restaurante por ID
                Restaurante restaurante = entityManager.find(Restaurante.class, idRestaurante);
                if (restaurante == null) {
                    throw new DAOException("El restaurante especificado no existe.");
                }
                m.setRestaurante(restaurante); // Asignar restaurante a la mesa

                entityManager.persist(m); // Persistir la nueva mesa
                cantidadRestante--; // Reducir la cantidad restante
            }

            entityManager.flush(); // Forzar la sincronización de las operaciones en la base de datos
            transaction.commit(); // Confirmar la transacción
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Revertir si hay un error
            }
            throw new DAOException("Error al insertar las mesas: " + e.getMessage());

        } finally {
            entityManager.close(); // Cerrar el EntityManager al final
        }
    }

    @Override
public List<Mesa> obtenerMesasDisponibles(Long idRestaurante) throws DAOException {
    EntityManager entityManager = Conexion.getInstance().crearConexion();

    try {
        // Consulta para obtener mesas disponibles en el restaurante especificado
        String jpql = "SELECT m FROM Mesa m WHERE m.restaurante.id = :idRestaurante " +
                      "AND (m.fechaNuevaDisponibilidad IS NULL OR m.fechaNuevaDisponibilidad <= :fechaActual) " +
                      "AND NOT EXISTS (SELECT r FROM Reservacion r WHERE r.mesa = m AND r.estado LIKE 'PENDIENTE')";

        TypedQuery<Mesa> query = entityManager.createQuery(jpql, Mesa.class);

        // Parámetros de la consulta
        LocalDateTime fechaActual = LocalDateTime.now();
        query.setParameter("idRestaurante", idRestaurante);
        query.setParameter("fechaActual", fechaActual);

        // Impresión de depuración
        System.out.println("ID del Restaurante: " + idRestaurante);
        System.out.println("Fecha actual para comparación: " + fechaActual);

        List<Mesa> mesasDisponibles = query.getResultList();
        System.out.println("Mesas disponibles encontradas: " + mesasDisponibles.size());

        return mesasDisponibles;
    } catch (NoResultException e) {
        // Si no hay resultados, devuelve una lista vacía
        System.out.println("No se encontraron mesas disponibles.");
        return new ArrayList<>();
    } catch (Exception e) {
        System.out.println("Error en obtenerMesasDisponibles: " + e.getMessage());
        throw new DAOException("Error al obtener mesas disponibles");
    } finally {
        entityManager.close();
    }
}




}
