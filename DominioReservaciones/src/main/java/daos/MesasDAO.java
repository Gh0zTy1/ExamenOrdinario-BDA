/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.DAOException;
import entidades.Mesa;
import entidades.Restaurante;
import entidades.TipoMesa;
import entidades.UbicacionMesa;
import idaos.IMesasDAO;

import javax.persistence.EntityManager;
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

    private final EntityManager entityManager;

    // Constructor con inyección de dependencias
    public MesasDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Mesa> obtenerMesasTodas(Long idRestaurante) throws DAOException {
        try {
            TypedQuery<Mesa> query = entityManager.createQuery(
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
            TypedQuery<Mesa> query = entityManager.createQuery(
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

    @Override
    public void insertarMesas(Long idRestaurante, TipoMesa tipo, UbicacionMesa ubicacion, int cantidad) throws DAOException {
        EntityTransaction transaction = entityManager.getTransaction();

        if (ubicacion == null || cantidad < 1) {
            throw new DAOException("Parámetros inválidos para la inserción de mesas");
        }

        try {
            transaction.begin();
            for (int i = 0; i < cantidad; i++) {
                Mesa mesa = new Mesa();
                String codigoMesa = generarCodigoMesa(ubicacion, tipo);
                mesa.setCodigo(codigoMesa);
                mesa.setTipoMesa(tipo);
                mesa.setUbicacion(ubicacion);

                Restaurante restaurante = entityManager.find(Restaurante.class, idRestaurante);
                if (restaurante == null) {
                    throw new DAOException("El restaurante especificado no existe");
                }
                mesa.setRestaurante(restaurante);
                entityManager.persist(mesa);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw new DAOException("Error al insertar mesas: " + e.getMessage());
        }
    }

    @Override
    public List<Mesa> obtenerMesasDisponibles(Long idRestaurante) throws DAOException {
        try {
            String jpql = "SELECT m FROM Mesa m WHERE m.restaurante.id = :idRestaurante " +
                          "AND (m.fechaNuevaDisponibilidad IS NULL OR m.fechaNuevaDisponibilidad <= :fechaActual) " +
                          "AND NOT EXISTS (SELECT r FROM Reservacion r WHERE r.mesa = m AND r.estado LIKE 'PENDIENTE')";
            TypedQuery<Mesa> query = entityManager.createQuery(jpql, Mesa.class);
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

            Long conteo = entityManager.createQuery(
                "SELECT COUNT(m) FROM Mesa m WHERE m.codigo = :codigo", Long.class)
                .setParameter("codigo", codigo)
                .getSingleResult();

            if (conteo == 0) return codigo;
        }
    }
}
