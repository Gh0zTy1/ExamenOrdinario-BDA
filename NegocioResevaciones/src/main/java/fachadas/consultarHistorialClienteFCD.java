/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import bo.ReservacionesBO;
import dto.ReservacionDTO;
import excepciones.NegocioException;
import iFachadas.IconsultarHistorialClienteFCD;
import java.util.List;

/**
 *
 * @author caarl
 */
public class consultarHistorialClienteFCD implements IconsultarHistorialClienteFCD {
     private final ReservacionesBO reservacionesBO;

    public consultarHistorialClienteFCD(ReservacionesBO reservacionesBO) {
        this.reservacionesBO = reservacionesBO;
    }
     
     
    
    
      @Override
    public List<ReservacionDTO> consultarHistorialCliente(Long idCliente) throws NegocioException {
        return reservacionesBO.obtenerReservacionesCliente(null, idCliente.toString());
    }

    
}
