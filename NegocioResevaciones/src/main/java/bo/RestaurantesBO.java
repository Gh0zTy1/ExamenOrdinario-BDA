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
public void agregarRestaurante(RestauranteDTO restauranteDTO) throws NegocioException {
    // Validaciones de negocio
    if (restauranteDTO == null) {
        throw new NegocioException("El restaurante no puede ser nulo");
    }
    
    // Validaciones de campos obligatorios
    if (restauranteDTO.getNombre() == null || restauranteDTO.getNombre().trim().isEmpty()) {
        throw new NegocioException("El nombre del restaurante es obligatorio");
    }
    
    if (restauranteDTO.getDireccion() == null || restauranteDTO.getDireccion().trim().isEmpty()) {
        throw new NegocioException("La dirección del restaurante es obligatoria");
    }
    
    if (restauranteDTO.getTelefono() == null || !restauranteDTO.getTelefono().matches("\\d{10}")) {
        throw new NegocioException("El teléfono debe ser un número válido de 10 dígitos");
    }
    
    if (restauranteDTO.getHoraApertura() == null || restauranteDTO.getHoraCierre() == null) {
        throw new NegocioException("Las horas de apertura y cierre son obligatorias");
    }
    
    // Validar que la hora de cierre sea posterior a la hora de apertura
    if (!restauranteDTO.getHoraCierre().isAfter(restauranteDTO.getHoraApertura())) {
        throw new NegocioException("La hora de cierre debe ser posterior a la hora de apertura");
    }
    
    try {
        // Convertir DTO a entidad
        Restaurante restaurante = restauranteConvertidor.convertFromDto(restauranteDTO);
        
        // Agregar restaurante
        restaurantesDAO.agregarRestaurante(restaurante);
    } catch (DAOException e) {
        throw new NegocioException("Error al agregar el restaurante: " + e.getMessage());
    }
}

@Override
public void actualizarRestaurante(RestauranteDTO restauranteDTO) throws NegocioException {
    // Validaciones de negocio
    if (restauranteDTO == null) {
        throw new NegocioException("El restaurante no puede ser nulo");
    }
    
    // Validar que el ID exista
    if (restauranteDTO.getId() == null) {
        throw new NegocioException("El ID del restaurante es obligatorio para actualizar");
    }
    
    // Validaciones de horas
    if (restauranteDTO.getHoraApertura() == null || restauranteDTO.getHoraCierre() == null) {
        throw new NegocioException("Las horas de apertura y cierre son obligatorias");
    }
    
    // Validar que la hora de cierre sea posterior a la hora de apertura
    if (!restauranteDTO.getHoraCierre().isAfter(restauranteDTO.getHoraApertura())) {
        throw new NegocioException("La hora de cierre debe ser posterior a la hora de apertura");
    }
    
    try {
        // Verificar que el restaurante existe
        RestauranteDTO restauranteExistente = obtenerRestaurantePorID(restauranteDTO.getId());
        
        // Convertir DTO a entidad
        Restaurante restaurante = restauranteConvertidor.convertFromDto(restauranteDTO);
        
        // Actualizar restaurante
        restaurantesDAO.actualizarRestaurante(restaurante);
    } catch (DAOException e) {
        throw new NegocioException("Error al actualizar el restaurante: " + e.getMessage());
    }
}

    @Override
    public void eliminarRestaurante(Long idRestaurante) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   

    



    

}
