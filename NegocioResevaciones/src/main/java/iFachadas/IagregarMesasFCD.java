/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

import dto.TipoMesaDTO;
import dto.UbicacionMesaDTO;
import excepciones.NegocioException;

/**
 *
 * @author caarl
 */
public interface IagregarMesasFCD {
    void agregarMesas(Long idRestaurante, TipoMesaDTO tipoMesa, UbicacionMesaDTO ubicacion, int cantidad) throws NegocioException;
}
