/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import Excepciones.DAOException;
import convertidores.ReservacionConvertidor;
import daos.ReservacionesDAO;
import dto.ReservacionDTO;
import entidades.Reservacion;
import excepciones.NegocioException;
import ibo.IReservacionesBO;
import idaos.IReservacionesDAO;


import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación de la interfaz IReservacionesBO para manejar la lógica de negocio
 * relacionada con las reservaciones. Implementa el patrón Singleton para asegurar
 * que solo exista una instancia de esta clase.
 * 
 * @author caarl
 */

public class ReservacionesBO implements IReservacionesBO {

    // DAO para acceder a los datos de reservaciones
    private final IReservacionesDAO reservacionesDAO;
    
    // Convertidor para transformar entidades a DTOs y viceversa
    private final ReservacionConvertidor reservacionConvertidor;

    // Instancia única de la clase
    private static ReservacionesBO instance;

    /**
     * Constructor privado para implementar el patrón Singleton.
     * Inicializa el DAO y el convertidor.
     */
    private ReservacionesBO() {
        this.reservacionesDAO = ReservacionesDAO.getInstance(); // Obtiene la instancia del DAO
        this.reservacionConvertidor = new ReservacionConvertidor(); // Crea un nuevo convertidor
    }

    /**
     * Método para obtener la instancia única de ReservacionesBO.
     * Se utiliza sincronización para asegurar que no se creen instancias
     * simultáneamente en un entorno multihilo.
     * 
     * @return instancia única de ReservacionesBO
     */
    public static synchronized ReservacionesBO getInstance() {
        if (instance == null) {
            instance = new ReservacionesBO(); // Crea una nueva instancia si no existe
        }
        return instance; // Retorna la instancia existente
    }

    /**
     * Obtiene las reservaciones asociadas a una mesa específica en un restaurante.
     * 
     * @param idRestaurante ID del restaurante
     * @param codigoMesa Código de la mesa
     * @return lista de ReservacionDTO correspondientes a la mesa especificada
     * @throws excepciones.NegocioException
     */
    @Override
    public List<ReservacionDTO> obtenerReservacionesDeMesa(Long idRestaurante, String codigoMesa) throws NegocioException {
        try {
            // Obtiene las reservaciones de la mesa especificada a través del DAO
            List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesDeMesa(idRestaurante, codigoMesa);
            // Convierte la lista de entidades a DTOs antes de devolverla
            return reservacionConvertidor.createFromEntities(reservaciones);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

   

    /**
     * Obtiene todas las reservaciones de un restaurante específico.
     * 
     * @param idRestaurante ID del restaurante
     * @return lista de ReservacionDTO de todas las reservaciones del restaurante
     */
    @Override
    public List<ReservacionDTO> obtenerReservacionesTodos(Long idRestaurante) throws NegocioException {
        try {
            // Obtiene todas las reservaciones del restaurante especificado
            List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesTodos(idRestaurante);
            // Convierte la lista de entidades a DTOs antes de devolverla
            return reservacionConvertidor.createFromEntities(reservaciones);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene las reservaciones de un restaurante dentro de un periodo específico.
     * 
     * @param idRestaurante ID del restaurante
     * @param fechaInicio Fecha y hora de inicio del periodo
     * @param fechaFin Fecha y hora de fin del periodo
     * @return lista de ReservacionDTO dentro del periodo especificado
     * @throws ServicioException si ocurre un error al obtener las reservaciones
     */
    @Override
    public List<ReservacionDTO> obtenerReservacionesPorPeriodo(Long idRestaurante, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException {
        try {
            // Obtiene las reservaciones dentro del periodo especificado
            List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesPorPeriodo(idRestaurante, fechaInicio, fechaFin);
            // Convierte la lista de entidades a DTOs antes de devolverla
            return reservacionConvertidor.createFromEntities(reservaciones);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene las reservaciones de un cliente específico basado en su número de teléfono.
     * 
     * @param idRestaurante ID del restaurante
     * @param telefono Número de teléfono del cliente
     * @return lista de ReservacionDTO del cliente especificado
     */
    @Override
    public List<ReservacionDTO> obtenerReservacionesCliente(Long idRestaurante, String telefono) throws NegocioException {
        try {
            // Obtiene las reservaciones de un cliente específico basado en su número de teléfono
            List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesCliente(idRestaurante, telefono);
            // Convierte la lista de entidades a DTOs antes de devolverla
            return reservacionConvertidor.createFromEntities(reservaciones);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene una reservación específica a través de su ID.
     * 
     * @param id ID de la reservación
     * @return ReservacionDTO de la reservación correspondiente al ID
     * @throws ServicioException si ocurre un error al obtener la reservación
     */
    @Override
    public ReservacionDTO obtenerReservacionPorID(Long id) throws NegocioException {
        try {
            // Obtiene una reservación por su ID
            Reservacion reservacion = reservacionesDAO.obtenerReservacionPorID(id);
            // Convierte la entidad a DTO antes de devolverla
            return reservacionConvertidor.convertFromEntity(reservacion);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Agrega una nueva reservación al sistema.
     * 
     * @param reservacion DTO de la reservación a agregar
     * @throws ServicioException si ocurre un error al agregar la reservación
     */
    @Override
    public void agregarReservacion(ReservacionDTO reservacion) throws NegocioException {
        try {
            // Convierte el DTO a la entidad correspondiente
            Reservacion entidad = reservacionConvertidor.convertFromDto(reservacion);
            // Agrega la nueva reservación a través del DAO
            reservacionesDAO.agregarReservacion(entidad);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Actualiza una reservación existente en el sistema.
     * 
     * @param reservacion DTO de la reservación a actualizar
     * @throws ServicioException si ocurre un error al actualizar la reservación
     */
    @Override
    public void actualizarReservacion(ReservacionDTO reservacion) throws NegocioException {
        try {
            // Convierte el DTO a la entidad correspondiente
            Reservacion entidad = reservacionConvertidor.convertFromDto(reservacion);
            // Actualiza la reservación existente a través del DAO
            reservacionesDAO.actualizarReservacion(entidad);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Elimina una reservación del sistema.
     * 
     * @param id ID de la reservación a eliminar
     * @throws excepciones.NegocioException
     */
    @Override
    public void eliminarReservacion(Long id) throws NegocioException {
        try {
            // Elimina la reservación del sistema a través del DAO
            reservacionesDAO.eliminarReservacion(id);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }
}
