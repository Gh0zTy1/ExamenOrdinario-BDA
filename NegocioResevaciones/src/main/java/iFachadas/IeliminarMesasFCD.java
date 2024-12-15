/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

import excepciones.NegocioException;

/**
 *
 * @author caarl
 */
public interface IeliminarMesasFCD {
    void eliminarMesa(Long idRestaurante, String codigo) throws NegocioException;
}
