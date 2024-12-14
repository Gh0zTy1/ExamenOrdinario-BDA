/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ibo;

import dto.RestauranteDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Define las operaciones necesarias para administrar sucursales de restaurantes en el sistema
 * @author caarl
 */
public interface IRestaurantesBO {
    /**
     * Obtiene todos las sucursales del restaurante en el sistema
     * @return Lista sucursales restaurante
     * @throws excepciones.NegocioException
     */
    public List<RestauranteDTO> obtenerRestaurantesTodos() throws NegocioException;
    
    /**
     * Obtiene la sucursal del restaurante con el ID dado
     * @param id ID de la sucursal del restaurante
     * @return Restaurante sucursal
     * @throws excepciones.NegocioException
     */
    public RestauranteDTO obtenerRestaurantePorID(Long id) throws NegocioException;
    
    /**
     * Obtiene la sucursal del restaurante con el numero telefonico dado
     * @param numeroTelefono Numero de telefono de la sucursal
     * @return Restaurante sucursal
     * @throws excepciones.NegocioException 
     */
    public RestauranteDTO obtenerRestaurantePorNumeroTelefono(String numeroTelefono) throws NegocioException;
    
    /**
     * Agrega una nueva sucursal del restaurante al sistema
     * @param restaurante Nueva sucursal del restaurante
     * @throws excepciones.NegocioException
     */
    public void agregarRestaurante(RestauranteDTO restaurante) throws NegocioException;
    
    /**
     * Actualiza la informacion de la sucursal del restaurante dado
     * @param restaurante Sucursal del restaurante
     * @throws excepciones.NegocioException
     */
    public void actualizarRestaurante(RestauranteDTO restaurante) throws NegocioException;
    
    /**
     * Elimina una sucursal en el sistema
     * @param idRestaurante ID de la sucursal a eliminar
     * @throws excepciones.NegocioException
     */
    public void eliminarRestaurante(Long idRestaurante) throws NegocioException;
}
