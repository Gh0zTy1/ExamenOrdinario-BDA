/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

/**
 *
 * @author caarl
 */
import dto.RestauranteDTO;
import excepciones.NegocioException;

/**
 * Define la interfaz para la fachada de agregar restaurante.
 */
public interface IAgregarRestauranteFCD {
    /**
     * Agrega un nuevo restaurante.
     *
     * @param restaurante DTO del restaurante a agregar.
     * @throws NegocioException en caso de un error en la lógica de negocio.
     */
    void agregarRestaurante(RestauranteDTO restaurante) throws NegocioException;
}
