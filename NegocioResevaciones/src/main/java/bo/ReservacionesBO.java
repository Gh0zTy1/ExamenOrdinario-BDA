/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import Excepciones.DAOException;
import convertidores.ReservacionConvertidor;
import dto.ReservacionDTO;
import entidades.Reservacion;
import excepciones.NegocioException;
import ibo.IReservacionesBO;
import idaos.IReservacionesDAO;
import java.time.Duration;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implementación de la interfaz IReservacionesBO para manejar la lógica de negocio
 * relacionada con las reservaciones. Ahora utiliza inyección de dependencias.
 * 
 * @author caarl
 */
public class ReservacionesBO implements IReservacionesBO {

    private final IReservacionesDAO reservacionesDAO;
    private final ReservacionConvertidor reservacionConvertidor;

    /**
     * Constructor para inyección de dependencias.
     * 
     * @param reservacionesDAO DAO para el acceso a datos de reservaciones.
     * @param reservacionConvertidor Convertidor de Reservacion a DTO y viceversa.
     */
    public ReservacionesBO(IReservacionesDAO reservacionesDAO, ReservacionConvertidor reservacionConvertidor) {
        this.reservacionesDAO = reservacionesDAO;
        this.reservacionConvertidor = reservacionConvertidor;
    }
/**
 * 
 * @param idRestaurante
 * @param codigoMesa
 * @return
 * @throws NegocioException 
 */
    @Override
    public List<ReservacionDTO> obtenerReservacionesDeMesa(Long idRestaurante, String codigoMesa) throws NegocioException {
        try {
            List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesDeMesa(idRestaurante, codigoMesa);
            return reservacionConvertidor.createFromEntities(reservaciones);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }
/**
 * 
 * @param idRestaurante
 * @return
 * @throws NegocioException 
 */
    @Override
    public List<ReservacionDTO> obtenerReservacionesTodos(Long idRestaurante) throws NegocioException {
        try {
            List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesTodos(idRestaurante);
            return reservacionConvertidor.createFromEntities(reservaciones);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<ReservacionDTO> obtenerReservacionesPorPeriodo(Long idRestaurante, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException {
        try {
            List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesPorPeriodo(idRestaurante, fechaInicio, fechaFin);
            return reservacionConvertidor.createFromEntities(reservaciones);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<ReservacionDTO> obtenerReservacionesCliente(Long idRestaurante, String telefono) throws NegocioException {
        try {
            List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesCliente(idRestaurante, telefono);
            return reservacionConvertidor.createFromEntities(reservaciones);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public ReservacionDTO obtenerReservacionPorID(Long id) throws NegocioException {
        try {
            Reservacion reservacion = reservacionesDAO.obtenerReservacionPorID(id);
            return reservacionConvertidor.convertFromEntity(reservacion);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void agregarReservacion(ReservacionDTO reservacion) throws NegocioException {
        try {
            Reservacion entidad = reservacionConvertidor.convertFromDto(reservacion);
            reservacionesDAO.agregarReservacion(entidad);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void actualizarReservacion(ReservacionDTO reservacion) throws NegocioException {
        try {
            Reservacion entidad = reservacionConvertidor.convertFromDto(reservacion);
            reservacionesDAO.actualizarReservacion(entidad);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void eliminarReservacion(Long id) throws NegocioException {
        try {
            reservacionesDAO.eliminarReservacion(id);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
     @Override
    public void cancelarReservacion(Long idReservacion) throws NegocioException {
        try {
            // Llamar al DAO para cancelar la reservación
            reservacionesDAO.cancelarReservacion(idReservacion);

            // Lógica adicional de negocio si es necesario
            Reservacion reservacion = reservacionesDAO.obtenerReservacionPorID(idReservacion);
            LocalDateTime fechaHoraReservacion = reservacion.getFechaHora();
            LocalDateTime fechaHoraActual = LocalDateTime.now();
            Duration diffTemporal = Duration.between(fechaHoraActual, fechaHoraReservacion);
            long horasRestantes = diffTemporal.toHours();
            
            // Aquí podrías manejar el cálculo de multas o cualquier lógica adicional
            System.out.println("Horas restantes para la reservación: " + horasRestantes);

        } catch (DAOException e) {
            // Aquí manejamos las excepciones que puedan surgir en el DAO
            throw new NegocioException("Error al cancelar la reservación. " + e.getMessage());
        }
    }
}
