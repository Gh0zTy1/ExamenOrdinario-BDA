package daos;

import Excepciones.DAOException;
import cifrado.CifradoTelefono;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Cliente;
import entidades.EstadoReservacion;
import entidades.Reservacion;
import entidades.TipoMesa;
import idaos.IMesasDAO;
import idaos.IReservacionesDAO;
import java.time.Duration;
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

    private final IConexion conexion;
    private final IMesasDAO mesasDAO;

    public ReservacionesDAO(Conexion conexion, IMesasDAO mesasDAO) {
        this.conexion = conexion;
        this.mesasDAO = mesasDAO;
    }

 
    

     @Override
public List<Reservacion> obtenerReservacionesTodos(Long idRestaurante) throws DAOException {
    try {
        TypedQuery<Reservacion> query = conexion.crearConexion().createQuery(
                "SELECT r FROM Reservacion r WHERE r.mesa.restaurante.id = :idRestaurante", Reservacion.class);
        query.setParameter("idRestaurante", idRestaurante);
        List<Reservacion> reservaciones = query.getResultList();
        
        // Descifrar los teléfonos de cada reservación
        for (Reservacion reservacion : reservaciones) {
            // Asumimos que el objeto Cliente está asociado a cada Reservacion
            String telefonoCifrado = reservacion.getCliente().getTelefono();  // Accedemos al teléfono cifrado
            if (telefonoCifrado != null) {
                try {
                    String telefonoDescifrado = CifradoTelefono.desencriptar(telefonoCifrado);  // Desciframos el teléfono
                    reservacion.getCliente().setTelefono(telefonoDescifrado);  // Actualizamos el teléfono del cliente
                } catch (Exception e) {
                    // Manejo de excepción en caso de error al descifrar
                    throw new DAOException("Error al descifrar el teléfono del cliente", e);
                }
            }
        }
        
        return reservaciones;
    } catch (NoResultException e) {
        return new ArrayList<>();
    } catch (Exception e) {
        throw new DAOException("Error al obtener todas las reservaciones del restaurante", e);
    }
}


    @Override
    public List<Reservacion> obtenerReservacionesDeMesa(Long idRestaurante, String codigoMesa) throws DAOException {
       
        // Filtrar por restaurante y código de mesa dentro de ese restaurante
        try {
            boolean existeMesa = conexion.crearConexion().createQuery(
                    "SELECT COUNT(m) FROM Mesa m WHERE m.codigo = :codigoMesa AND m.restaurante.id = :idRestaurante", Long.class)
                    .setParameter("codigoMesa", codigoMesa)
                    .setParameter("idRestaurante", idRestaurante)
                    .getSingleResult() > 0;

            if (!existeMesa) {
                throw new DAOException("La mesa con el código indicado no existe en el restaurante especificado");
            }

            TypedQuery<Reservacion> query = conexion.crearConexion().createQuery(
                    "SELECT r FROM Reservacion r WHERE r.mesa.codigo = :codigoMesa AND r.mesa.restaurante.id = :idRestaurante", Reservacion.class);
            query.setParameter("codigoMesa", codigoMesa);
            query.setParameter("idRestaurante", idRestaurante);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener reservaciones de la mesa en el restaurante");
        } finally {
            conexion.crearConexion().close();
        }
    }

    @Override
    public List<Reservacion> obtenerReservacionesPorPeriodo(Long idRestaurante, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws DAOException {
      
        // Filtrar por restaurante y el período especificado
        try {
            TypedQuery<Reservacion> query = conexion.crearConexion().createQuery(
                    "SELECT r FROM Reservacion r WHERE r.mesa.restaurante.id = :idRestaurante AND r.fechaHora BETWEEN :fechaInicio AND :fechaFin", Reservacion.class);
            query.setParameter("idRestaurante", idRestaurante);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener reservaciones por período en el restaurante");
        } finally {
          conexion.crearConexion().close();
        }
    }

    @Override
public List<Reservacion> obtenerReservacionesCliente(Long idRestaurante, String telefonodesEncriptado) throws DAOException {
    EntityManager entityManager = null;

    try {
        // Desencriptar el teléfono proporcionado
        String telefonoDesencriptado;
        try {
            telefonoDesencriptado = CifradoTelefono.encriptar(telefonodesEncriptado);
        } catch (Exception e) {
            throw new DAOException("Error al desencriptar el teléfono proporcionado: " + telefonodesEncriptado, e);
        }

        // Crear conexión y consulta
        entityManager = Conexion.getInstance().crearConexion();
        TypedQuery<Reservacion> query = entityManager.createQuery(
                "SELECT r FROM Reservacion r WHERE r.mesa.restaurante.id = :idRestaurante AND r.cliente.telefono = :telefono", 
                Reservacion.class
        );
        query.setParameter("idRestaurante", idRestaurante);
        query.setParameter("telefono", telefonoDesencriptado);

        // Obtener reservaciones
        List<Reservacion> reservaciones = query.getResultList();

        // Desencriptar el teléfono de los clientes en las reservaciones
        for (Reservacion reservacion : reservaciones) {
            if (reservacion.getCliente() != null && reservacion.getCliente().getTelefono() != null) {
                try {
                    Cliente cliente = reservacion.getCliente();
                    String telefonoClienteEncriptado = cliente.getTelefono();
                    String telefonoClienteDesencriptado = CifradoTelefono.desencriptar(telefonoClienteEncriptado);
                    cliente.setTelefono(telefonoClienteDesencriptado); // Actualizar cliente con teléfono desencriptado
                } catch (Exception e) {
                    throw new DAOException("Error al desencriptar el teléfono del cliente en la reservación", e);
                }
            }
        }

        return reservaciones;

    } catch (NoResultException e) {
        return new ArrayList<>(); // Retorna lista vacía si no hay resultados
    } catch (Exception e) {
        throw new DAOException("Error al obtener reservaciones del cliente en el restaurante", e);
    } finally {
        if (entityManager != null) {
            try {
                entityManager.close();
            } catch (Exception e) {
                System.err.println("Error al cerrar el EntityManager: " + e.getMessage());
            }
        }
    }
}


    @Override
    public Reservacion obtenerReservacionPorID(Long id) throws DAOException {
       

        try {
            Reservacion res = conexion.crearConexion().find(Reservacion.class, id);
            if (res == null) {
                throw new DAOException("No se encontro la reservacion con el ID especificado");
            }

            return res;
        } catch (Exception e) {
            throw new DAOException("Error al obtener la reservación por ID");
        } finally {
            conexion.crearConexion().close();
        }
    }

   @Override
public void agregarReservacion(Reservacion reservacion) throws DAOException {
    EntityManager entityManager = Conexion.getInstance().crearConexion();
    EntityTransaction transaction = entityManager.getTransaction();

    try {
        // Verificar que el cliente no tenga una reservación activa en el mismo restaurante
        boolean reservacionActivaRestaurante = entityManager.createQuery(
                "SELECT COUNT(r) FROM Reservacion r WHERE r.cliente.id = :idCliente AND r.mesa.restaurante.id = :idRestaurante AND r.estado LIKE 'PENDIENTE'", Long.class)
                .setParameter("idCliente", reservacion.getCliente().getId())
                .setParameter("idRestaurante", reservacion.getMesa().getRestaurante().getId())
                .getSingleResult() > 0;

        if (reservacionActivaRestaurante) {
            throw new DAOException("No se puede realizar la reservación porque el cliente ya tiene una reservación activa en este restaurante.");
        }

        // Verificar disponibilidad de la mesa (si el horario de cierre está configurado)
        LocalTime horaCierre = reservacion.getMesa().getRestaurante().getHoraCierre();
        if (horaCierre != null) {
            LocalTime limiteReservacion = horaCierre.minusHours(1); // Última hora válida para reservar
            LocalTime horaReservacion = reservacion.getFechaHora().toLocalTime();

            if (horaReservacion.isAfter(limiteReservacion)) {
                throw new DAOException(String.format(
                    "La reservación no puede realizarse porque excede el horario límite permitido, que es hasta las %s",
                    limiteReservacion.toString()
                ));
            }
        }

        // Verificar que la mesa no esté ocupada
        boolean mesaOcupada = entityManager.createQuery(
                "SELECT COUNT(r) FROM Reservacion r WHERE r.mesa.codigo = :codigoMesa AND r.estado LIKE 'PENDIENTE' AND r.mesa.restaurante.id = :idRestaurante", Long.class)
                .setParameter("codigoMesa", reservacion.getMesa().getCodigo())
                .setParameter("idRestaurante", reservacion.getMesa().getRestaurante().getId())
                .getSingleResult() > 0;

        if (mesaOcupada) {
            throw new DAOException("La mesa que se intenta reservar ya está ocupada.");
        }

        // Persistir la reservación
        transaction.begin();
        entityManager.persist(reservacion);
        transaction.commit();
    } catch (Exception e) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
        throw new DAOException(e.getMessage());
    } finally {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
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
          if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void eliminarReservacion(Long id) throws DAOException {
      
        EntityTransaction transaction = conexion.crearConexion().getTransaction();

        try {
            transaction.begin();
            Reservacion reservacion = conexion.crearConexion().find(Reservacion.class, id);
            if (reservacion != null) {
                conexion.crearConexion().remove(reservacion);
            } else {
                throw new DAOException("No se encontro la reservacion a eliminar");
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            //System.out.println("ELIMINAR RESERVACION: " + e.getMessage());
            throw new DAOException(e.getMessage());
        } finally {
            conexion.crearConexion().close();
        }
    }
    
     @Override
    public void cancelarReservacion(Long idReservacion) throws DAOException {
        
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
      

        Reservacion reservacion = entityManager.find(Reservacion.class, idReservacion);
        if (reservacion == null) {
            throw new DAOException("No se encontro la reservacion");
        }

        if (reservacion.getEstado().equals(EstadoReservacion.CANCELADA)) {
            throw new DAOException("La reservacion ya fue cancelada con anterioridad");
        }

        if (reservacion.getEstado().equals(EstadoReservacion.FINALIZADA)) {
            throw new DAOException("La reservacion que se intenta cancelar ya fue termino");
        }

        // se calcula la multa correspondiente al tiempo en el que fue cancelada
        LocalDateTime fechaHoraReservacion = reservacion.getFechaHora();
        LocalDateTime fechaHoraActual = LocalDateTime.now();

        Duration diffTemporal = Duration.between(fechaHoraActual, fechaHoraReservacion);

        long horasRestantes = diffTemporal.toHours();

        // NOTE: DEBUG
        System.out.println("DIFERENCIA TEMPORAL DE CANCELACION: " + horasRestantes + " horas...");

        // se cambia el estado de la reservacion
        reservacion.setEstado(EstadoReservacion.CANCELADA);

      

        try {
            transaction.begin();
            conexion.crearConexion().merge(reservacion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new DAOException("Error al cancelar la reservacion, porfavor intente mas tarde");
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }

    

   
    }
    }
