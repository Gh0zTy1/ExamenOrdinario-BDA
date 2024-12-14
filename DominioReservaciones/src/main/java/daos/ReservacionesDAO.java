package daos;

import Excepciones.DAOException;
import conexion.Conexion;
import entidades.EstadoReservacion;
import entidades.Reservacion;
import entidades.TipoMesa;
import idaos.IMesasDAO;
import idaos.IReservacionesDAO;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Clase concreta de la interfaz IReservacionesDAO
 *
 * @author caarl
 */
public class ReservacionesDAO implements IReservacionesDAO {

    private static IReservacionesDAO instancia;

    private IMesasDAO mesasDAO = MesasDAO.getInstance();

    private ReservacionesDAO() {
    }

    public static IReservacionesDAO getInstance() {
        if (instancia == null) {
            instancia = new ReservacionesDAO();
        }
        return instancia;
    }

    @Override
    public List<Reservacion> obtenerReservacionesTodos(Long idRestaurante) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        // Filtrar por restaurante sin aplicar más filtros
        try {
            TypedQuery<Reservacion> query = entityManager.createQuery(
                    "SELECT r FROM Reservacion r WHERE r.mesa.restaurante.id = :idRestaurante", Reservacion.class);
            query.setParameter("idRestaurante", idRestaurante);
            return query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw new DAOException("Error al obtener todas las reservaciones del restaurante");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Reservacion> obtenerReservacionesDeMesa(Long idRestaurante, String codigoMesa) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        // Filtrar por restaurante y código de mesa dentro de ese restaurante
        try {
            boolean existeMesa = entityManager.createQuery(
                    "SELECT COUNT(m) FROM Mesa m WHERE m.codigo = :codigoMesa AND m.restaurante.id = :idRestaurante", Long.class)
                    .setParameter("codigoMesa", codigoMesa)
                    .setParameter("idRestaurante", idRestaurante)
                    .getSingleResult() > 0;

            if (!existeMesa) {
                throw new DAOException("La mesa con el código indicado no existe en el restaurante especificado");
            }

            TypedQuery<Reservacion> query = entityManager.createQuery(
                    "SELECT r FROM Reservacion r WHERE r.mesa.codigo = :codigoMesa AND r.mesa.restaurante.id = :idRestaurante", Reservacion.class);
            query.setParameter("codigoMesa", codigoMesa);
            query.setParameter("idRestaurante", idRestaurante);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener reservaciones de la mesa en el restaurante");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Reservacion> obtenerReservacionesPorPeriodo(Long idRestaurante, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        // Filtrar por restaurante y el período especificado
        try {
            TypedQuery<Reservacion> query = entityManager.createQuery(
                    "SELECT r FROM Reservacion r WHERE r.mesa.restaurante.id = :idRestaurante AND r.fechaHora BETWEEN :fechaInicio AND :fechaFin", Reservacion.class);
            query.setParameter("idRestaurante", idRestaurante);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener reservaciones por período en el restaurante");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Reservacion> obtenerReservacionesCliente(Long idRestaurante, String telefono) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        // Filtrar por restaurante y el teléfono del cliente
        try {
            TypedQuery<Reservacion> query = entityManager.createQuery(
                    "SELECT r FROM Reservacion r WHERE r.mesa.restaurante.id = :idRestaurante AND r.cliente.telefono = :telefono", Reservacion.class);
            query.setParameter("idRestaurante", idRestaurante);
            query.setParameter("telefono", telefono);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener reservaciones del cliente en el restaurante");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Reservacion obtenerReservacionPorID(Long id) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();

        try {
            Reservacion res = entityManager.find(Reservacion.class, id);
            if (res == null) {
                throw new DAOException("No se encontro la reservacion con el ID especificado");
            }

            return res;
        } catch (Exception e) {
            throw new DAOException("Error al obtener la reservación por ID");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void agregarReservacion(Reservacion reservacion) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();

        System.out.println("MESA: " + reservacion.getMesa());
        
        try {
            // Verificar que el cliente no tenga una reservación activa en el mismo restaurante.
            boolean reservacionActivaRestaurante = entityManager.createQuery(
                    "SELECT COUNT(r) FROM Reservacion r WHERE r.cliente.id = :idCliente AND r.mesa.restaurante.id = :idRestaurante AND r.estado LIKE 'PENDIENTE'", Long.class)
                    .setParameter("idCliente", reservacion.getCliente().getId())
                    .setParameter("idRestaurante", reservacion.getMesa().getRestaurante().getId())
                    .getSingleResult() > 0;

            if (reservacionActivaRestaurante) {
                throw new DAOException("No se puede realizar la reservación porque el cliente ya tiene una reservación activa en este restaurante.");
            }
            
            // Verificar que el cliente no tenga una reservación activa en otro restaurante.
            boolean reservacionActivaOtroRestaurante = entityManager.createQuery(
                    "SELECT COUNT(r) FROM Reservacion r WHERE r.cliente.id = :idCliente AND r.estado LIKE 'PENDIENTE'", Long.class)
                    .setParameter("idCliente", reservacion.getCliente().getId())
                    .getSingleResult() > 0;

            if (reservacionActivaOtroRestaurante) {
                throw new DAOException("No se puede realizar la reservación porque el cliente ya tiene una reservación activa en otro restaurante.");
            }

            // Obtener la fecha y hora actual
            LocalDateTime fechaActual = LocalDateTime.now();

            // Verificar que la mesa esté disponible (fecha de disponibilidad vencida).
            if (reservacion.getMesa().getFechaNuevaDisponibilidad() != null
                    && reservacion.getMesa().getFechaNuevaDisponibilidad().isAfter(fechaActual)) {
                throw new DAOException(String.format("La mesa estará disponible hasta %s",
                        reservacion.getMesa().getFechaNuevaDisponibilidad().toString()));
            }

            // Verificar que la hora de la reservación no exceda el límite permitido (una hora antes del cierre del restaurante).
            LocalTime horaCierre = reservacion.getMesa().getRestaurante().getHoraCierre();
            LocalTime limiteReservacion = horaCierre.minusHours(1);

            // Extrae la hora de la fecha y hora de la reservación
            LocalTime horaReservacion = reservacion.getFechaHora().toLocalTime();

            if (horaReservacion.isAfter(limiteReservacion)) {
                throw new DAOException(String.format("La reservación no puede realizarse porque excede el horario límite permitido, que es hasta las %s", limiteReservacion.toString()));
            }
            // Verificar que la mesa no esté ocupada por otra reservación pendiente.
            boolean mesaOcupada = entityManager.createQuery(
                    "SELECT COUNT(r) FROM Reservacion r WHERE r.mesa.codigo = :codigoMesa AND r.estado LIKE 'PENDIENTE' AND r.mesa.restaurante.id = :idRestaurante", Long.class)
                    .setParameter("codigoMesa", reservacion.getMesa().getCodigo())
                    .setParameter("idRestaurante", reservacion.getMesa().getRestaurante().getId())
                    .getSingleResult() > 0;

            if (mesaOcupada) {
                throw new DAOException("La mesa que se intenta reservar ya está ocupada.");
            }

            // Validar el número de personas para la reservación en base al tipo de mesa.
            Integer cantidadPersonas = reservacion.getNumeroPersonas();
            TipoMesa tipoMesa = reservacion.getMesa().getTipoMesa();

            if (cantidadPersonas == null) {
                throw new DAOException("Debe especificarse el número de personas para la reservación.");
            }
            if (cantidadPersonas > tipoMesa.getMaximoPersonas()) {
                throw new DAOException(String.format("El número de personas excede el máximo permitido por mesa [máximo: %d]", tipoMesa.getMaximoPersonas()));
            }
            if (cantidadPersonas < tipoMesa.getMinimoPersonas()) {
                throw new DAOException(String.format("El número de personas no cumple el mínimo permitido por mesa [mínimo: %d]", tipoMesa.getMinimoPersonas()));
            }

            // Establecer el monto total de la reservación basado en el tipo de mesa.
            reservacion.setMontoTotal(tipoMesa.getPrecio());

            // Persistir la reservación.
            transaction.begin();
            entityManager.persist(reservacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void actualizarReservacion(Reservacion reservacion) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            Reservacion reservacionExistente = entityManager.find(Reservacion.class, reservacion.getId());
            if (reservacionExistente == null) {
                throw new DAOException("La reservacion que se desea actualizar no existe en el sistema");
            }

            boolean finalizada = reservacionExistente.getEstado().equals(EstadoReservacion.FINALIZADA) || reservacionExistente.getEstado().equals(EstadoReservacion.CANCELADA);
            if (finalizada) {
                throw new DAOException("La reservacion que se desea actualizar ya concluyo o fue cancelada");
            }

            Integer cantidadPersonas = reservacion.getNumeroPersonas();
            TipoMesa tipoMesa = reservacion.getMesa().getTipoMesa();

            if (cantidadPersonas > tipoMesa.getMaximoPersonas()) {
                throw new DAOException(String.format("El numero de personas de la reservacion excede el maximo por mesa [maximo: %d]", tipoMesa.getMaximoPersonas()));
            }

            if (cantidadPersonas < tipoMesa.getMinimoPersonas()) {
                throw new DAOException(String.format("El numero de personas de la reservacion no rebasa el minimo por mesa [minimo: %d]", tipoMesa.getMinimoPersonas()));
            }
            
            reservacion.getMesa().setFechaNuevaDisponibilidad(LocalDateTime.now().plusHours(5));

            // TODO: HACER QUE LA NUEVA FECHA DE DISPONIBILIDAD PARA LA MESA SEA LA FECHAHORA ACTUAL + 5 HORAS
            reservacion.setMontoTotal(reservacion.getMesa().getTipoMesa().getPrecio());

            transaction.begin();
            entityManager.merge(reservacion);
            entityManager.merge(reservacion.getMesa());
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void eliminarReservacion(Long id) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            Reservacion reservacion = entityManager.find(Reservacion.class, id);
            if (reservacion != null) {
                entityManager.remove(reservacion);
            } else {
                throw new DAOException("No se encontro la reservacion a eliminar");
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            //System.out.println("ELIMINAR RESERVACION: " + e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

   
    }
