/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import bo.ReservacionesBO;
import dto.ReservacionDTO;
import excepciones.NegocioException;
import iFachadas.IconsultarHistorialRestauranteFCD;
import java.util.List;

/**
 *
 * @author caarl
 */
public class consultarHistorialRestauranteFCD implements IconsultarHistorialRestauranteFCD {
     private final ReservacionesBO reservacionesBO;

    public consultarHistorialRestauranteFCD(ReservacionesBO reservacionesBO) {
        this.reservacionesBO = reservacionesBO;
    }
     
     
    
    
    @Override
    public List<ReservacionDTO> consultarHistorialRestaurante(Long idRestaurante) throws NegocioException {
        return reservacionesBO.obtenerReservacionesTodos(idRestaurante);
    }
}
