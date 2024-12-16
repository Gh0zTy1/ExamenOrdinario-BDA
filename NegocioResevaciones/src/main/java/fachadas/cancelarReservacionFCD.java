/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import bo.ReservacionesBO;
import excepciones.NegocioException;
import iFachadas.IcancelarReservacionFCD;

/**
 *
 * @author caarl
 */
public class cancelarReservacionFCD implements IcancelarReservacionFCD {
    private final ReservacionesBO reservacionesBO;

    public cancelarReservacionFCD(ReservacionesBO reservacionesBO) {
        this.reservacionesBO = reservacionesBO;
    }
    
     @Override
    public void cancelarReservacion(Long idReservacion) throws NegocioException {
        reservacionesBO.cancelarReservacion(idReservacion);
    }
}
