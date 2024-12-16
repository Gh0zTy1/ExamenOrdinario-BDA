/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

import dto.RestauranteDTO;
import excepciones.NegocioException;

/**
 *
 * @author caarl
 */
/**
 * Define la interfaz para la fachada de actualizar restaurante.
 */
public interface IActualizarRestauranteFCD {
    /**
     * Actualiza un restaurante existente.
     *
     * @param restaurante DTO del restaurante a actualizar.
     * @throws NegocioException en caso de un error en la lógica de negocio.
     */
    void actualizarRestaurante(RestauranteDTO restaurante) throws NegocioException;
}