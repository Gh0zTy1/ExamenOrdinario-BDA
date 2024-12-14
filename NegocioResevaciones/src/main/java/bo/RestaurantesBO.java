/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bo;

import Excepciones.DAOException;
import convertidores.RestauranteConvertidor;
import dto.RestauranteDTO;
import entidades.Restaurante;
import excepciones.NegocioException;
import ibo.IRestaurantesBO;
import idaos.IRestaurantesDAO;

import java.util.List;

/**
 * Implementación de la interfaz IRestaurantesBO para gestionar la lógica de negocio
 * relacionada con los restaurantes. Ahora utiliza inyección de dependencias.
 * 
 * @author caarl
 */
public class RestaurantesBO implements IRestaurantesBO {

    private final IRestaurantesDAO restaurantesDAO;
    private final RestauranteConvertidor restauranteConvertidor;

    /**
     * Constructor para inyección de dependencias.
     * 
     * @param restaurantesDAO DAO para acceso a datos de restaurantes.
     * @param restauranteConvertidor Convertidor para transformar entre entidad y DTO.
     */
    public RestaurantesBO(IRestaurantesDAO restaurantesDAO, RestauranteConvertidor restauranteConvertidor) {
        this.restaurantesDAO = restaurantesDAO;
        this.restauranteConvertidor = restauranteConvertidor;
    }

    @Override
    public List<RestauranteDTO> obtenerRestaurantesTodos() throws NegocioException {
        try {
            List<Restaurante> restaurantes = restaurantesDAO.obtenerRestaurantesTodos();
            return restauranteConvertidor.createFromEntities(restaurantes);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public RestauranteDTO obtenerRestaurantePorID(Long id) throws NegocioException {
        try {
            Restaurante restaurante = restaurantesDAO.obtenerRestaurantePorID(id);
            return restauranteConvertidor.convertFromEntity(restaurante);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public RestauranteDTO obtenerRestaurantePorNumeroTelefono(String numeroTelefono) throws NegocioException {
        try {
            Restaurante restaurante = restaurantesDAO.obtenerRestaurantePorNumeroTelefono(numeroTelefono);
            return restauranteConvertidor.convertFromEntity(restaurante);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }



    @Override
    public void agregarRestaurante(RestauranteDTO restaurante) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarRestaurante(RestauranteDTO restaurante) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarRestaurante(Long idRestaurante) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    



    

}
