/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

import dto.MesaDTO;
import excepciones.NegocioException;

/**
 *
 * @author caarl
 */
public interface ImodificarMesasFCD {
    /**
     * 
     * @param mesaDTO
     * @throws NegocioException 
     */
    void modificarMesa(MesaDTO mesaDTO) throws NegocioException;
}
