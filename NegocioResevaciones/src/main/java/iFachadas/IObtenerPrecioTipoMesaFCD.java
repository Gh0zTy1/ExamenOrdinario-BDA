/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

/**
 *
 * @author caarl
 */


import excepciones.NegocioException;

/**
 * Interfaz para la fachada que obtiene el precio de un tipo de mesa.
 */
public interface IObtenerPrecioTipoMesaFCD {

    /**
     * Obtiene el precio de un tipo de mesa por su ID.
     * 
     * @param id ID del tipo de mesa.
     * @return Precio del tipo de mesa.
     * @throws NegocioException Si ocurre un error en la operación.
     */
    float obtenerPrecioPorTipoMesa(Long id) throws NegocioException;
}
