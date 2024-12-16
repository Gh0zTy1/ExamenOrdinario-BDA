/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

import dto.TipoMesaDTO;
import excepciones.NegocioException;

/**
 *
 * @author caarl
 */
/**
 * asd
 * @author caarl
 */
public interface ITiposMesaFachada {
    /**
     * 
     * @param tipoMesaDTO
     * @throws NegocioException 
     */
    void agregarTipoMesa(TipoMesaDTO tipoMesaDTO) throws NegocioException;
}
