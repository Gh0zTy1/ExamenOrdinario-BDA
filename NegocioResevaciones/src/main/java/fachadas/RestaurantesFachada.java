/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

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
     * Obtiene todos los restaurantes con manejo de errores centralizado.
     * 
     * @return Lista de restaurantes en formato DTO
     * @throws NegocioException Si ocurre un error al recuperar los restaurantes
     */
    public List<RestauranteDTO> obtenerRestaurantesTodos() throws NegocioException {
        List<RestauranteDTO> restaurantes = restaurantesBO.obtenerRestaurantesTodos(); // Logging de errores
        // Puedes agregar lógica adicional de manejo de errores
        // Validaciones adicionales si son necesarias
        if (restaurantes == null || restaurantes.isEmpty()) {
            System.out.println("[!] No se encontraron restaurantes.");
            return Collections.emptyList();
        }
        return restaurantes;
    }

    /**
     * Método para obtener el primer restaurante de la lista.
     * 
     * @return Primer restaurante encontrado
     * @throws NegocioException Si no hay restaurantes o ocurre un error
     */
    public RestauranteDTO obtenerPrimerRestaurante() throws NegocioException {
        List<RestauranteDTO> restaurantes = obtenerRestaurantesTodos();
        
        if (restaurantes.isEmpty()) {
            throw new NegocioException("No se encontraron restaurantes.");
        }
        
        return restaurantes.getFirst();
    }

    /**
     * Método para obtener un restaurante por su nombre.
     * 
     * @param nombre Nombre del restaurante a buscar
     * @return Restaurante encontrado
     * @throws NegocioException Si no se encuentra el restaurante
     */
    public RestauranteDTO obtenerRestaurantePorNombre(String nombre) throws NegocioException {
        List<RestauranteDTO> restaurantes = obtenerRestaurantesTodos();
        
        return restaurantes.stream()
            .filter(r -> r.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElseThrow(() -> new NegocioException("No se encontró un restaurante con el nombre: " + nombre));
    }
}