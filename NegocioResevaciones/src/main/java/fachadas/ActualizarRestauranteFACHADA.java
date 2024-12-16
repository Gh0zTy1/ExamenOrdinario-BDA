/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import bo.RestaurantesBO;
import dto.RestauranteDTO;
import excepciones.NegocioException;
import iFachadas.IActualizarRestauranteFCD;

/**
 *
 * @author caarl
 */
/**
 * Fachada para actualizar un restaurante existente.
 */
public class ActualizarRestauranteFACHADA implements IActualizarRestauranteFCD {
    private final RestaurantesBO restaurantesBO;

    /**
     * Constructor que inyecta la dependencia del BO de restaurantes.
     *
     * @param restaurantesBO instancia de RestaurantesBO.
     */
    public ActualizarRestauranteFACHADA(RestaurantesBO restaurantesBO) {
        this.restaurantesBO = restaurantesBO;
    }

    @Override
    public void actualizarRestaurante(RestauranteDTO restaurante) throws NegocioException {
        restaurantesBO.actualizarRestaurante(restaurante);
    }
}