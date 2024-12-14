/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;
import bo.ReservacionesBO;
import dto.ReservacionDTO;
import excepciones.NegocioException;
import iFachadas.IFachadaReservaciones;

import java.util.List;
/**
 *
 * @author caarl
 */
/**
 * Implementación de la fachada para gestionar reservaciones.
 */
public class FachadaReservaciones implements IFachadaReservaciones {

    private final ReservacionesBO reservacionesBO;

    /**
     * Constructor con inyección de dependencias.
     *
     * @param reservacionesBO Instancia de la capa de negocio.
     */
    public FachadaReservaciones(ReservacionesBO reservacionesBO) {
        this.reservacionesBO = reservacionesBO;
    }

    @Override
    public void crearReservacion(ReservacionDTO reservacion) throws NegocioException {
        reservacionesBO.agregarReservacion(reservacion);
    }

    @Override
    public void cancelarReservacion(Long idReservacion) throws NegocioException {
        reservacionesBO.eliminarReservacion(idReservacion);
    }

    @Override
    public List<ReservacionDTO> consultarHistorialCliente(Long idCliente) throws NegocioException {
        return reservacionesBO.obtenerReservacionesCliente(null, idCliente.toString());
    }

    @Override
    public List<ReservacionDTO> consultarHistorialRestaurante(Long idRestaurante) throws NegocioException {
        return reservacionesBO.obtenerReservacionesTodos(idRestaurante);
    }
}