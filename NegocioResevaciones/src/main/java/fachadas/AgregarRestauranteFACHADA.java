/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

/**
 *
 * @author caarl
 */
import bo.RestaurantesBO;
import dto.RestauranteDTO;
import excepciones.NegocioException;
import iFachadas.IAgregarRestauranteFCD;

/**
 * Fachada para agregar un nuevo restaurante.
 */
public class AgregarRestauranteFACHADA implements IAgregarRestauranteFCD {
    private final RestaurantesBO restaurantesBO;

    /**
     * Constructor que inyecta la dependencia del BO de restaurantes.
     *
     * @param restaurantesBO instancia de RestaurantesBO.
     */
    public AgregarRestauranteFACHADA(RestaurantesBO restaurantesBO) {
        this.restaurantesBO = restaurantesBO;
    }

    @Override
    public void agregarRestaurante(RestauranteDTO restaurante) throws NegocioException {
        restaurantesBO.agregarRestaurante(restaurante);
    }
}