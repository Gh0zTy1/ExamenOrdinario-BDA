/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

import dto.ReservacionDTO;
import excepciones.NegocioException;

/**
 *
 * @author caarl
 */
public interface ICrearReservacionFCD {
     void crearReservacion(ReservacionDTO reservacion) throws NegocioException;
}
