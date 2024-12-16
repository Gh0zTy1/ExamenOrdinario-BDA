/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import Excepciones.DAOException;
import dto.RestauranteDTO;
import excepciones.NegocioException;
import ibo.IRestaurantesBO;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author caarl
 */
public class RestaurantesFachada {
    private final IRestaurantesBO restaurantesBO;

    /**
     * Constructor para inyección de dependencias de la fachada de restaurantes.
     * 
     * @param restaurantesBO Objeto de negocio para operaciones de restaurantes
     */
    public RestaurantesFachada(IRestaurantesBO restaurantesBO) {
        this.restaurantesBO = restaurantesBO;
    }

    
    /**
     * Método para obtener un restaurante por su ID.
     * 
     * @param id ID del restaurante a buscar
     * @return Restaurante encontrado
     * @throws NegocioException Si ocurre un error o no se encuentra el restaurante
     */
    public  RestauranteDTO obtenerRestaurantePorID(Long id) throws NegocioException {
        RestauranteDTO restaurante = restaurantesBO.obtenerRestaurantePorID(id);
        return restaurante;
    }
    
    
    
}