/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

import dto.ReservacionDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author caarl
 */
public interface IconsultarHistorialRestauranteFCD {
    List<ReservacionDTO> consultarHistorialRestaurante(Long idRestaurante) throws NegocioException;
}
