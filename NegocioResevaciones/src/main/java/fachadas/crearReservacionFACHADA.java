/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import bo.ReservacionesBO;
import dto.ReservacionDTO;
import excepciones.NegocioException;
import iFachadas.ICrearReservacionFCD;

/**
 *
 * @author caarl
 */
public class crearReservacionFACHADA implements ICrearReservacionFCD {
    private final ReservacionesBO reservacionesBO;

    public crearReservacionFACHADA(ReservacionesBO reservacionesBO) {
        this.reservacionesBO = reservacionesBO;
    }

    @Override
    public void crearReservacion(ReservacionDTO reservacion) throws NegocioException {
        reservacionesBO.agregarReservacion(reservacion);
    } 
    
}
