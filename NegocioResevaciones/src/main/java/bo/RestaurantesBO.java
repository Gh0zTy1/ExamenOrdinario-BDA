/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bo;


import Excepciones.DAOException;
import convertidores.RestauranteConvertidor;
import daos.RestaurantesDAO;
import dto.RestauranteDTO;
import entidades.Restaurante;
import excepciones.NegocioException;
import ibo.IRestaurantesBO;
import idaos.IRestaurantesDAO;


import java.util.List;

/**
 * Implementación de la interfaz IRestaurantesBO para gestionar la lógica de negocio
 * relacionada con los restaurantes. Implementa el patrón Singleton para asegurar
 * que solo exista una instancia de esta clase.
 * 
 * @author caarl
 */
public class RestaurantesBO implements IRestaurantesBO {

    // DAO para acceder a los datos de restaurantes
    private final IRestaurantesDAO restaurantesDAO;
    
    // Convertidor para transformar entidades de Restaurante a DTOs y viceversa
    private final RestauranteConvertidor restauranteConvertidor;
    
    // Instancia única de la clase
    private static RestaurantesBO instance;

    /**
     * Constructor privado para implementar el patrón Singleton.
     * Inicializa el DAO y el convertidor.
     */
    private RestaurantesBO() {
        this.restaurantesDAO = RestaurantesDAO.getInstance(); // Obtiene la instancia del DAO
        this.restauranteConvertidor = new RestauranteConvertidor(); // Crea un nuevo convertidor
    }

    /**
     * Método para obtener la instancia única de RestaurantesBO.
     * Se utiliza sincronización para asegurar que no se creen instancias
     * simultáneamente en un entorno multihilo.
     * 
     * @return instancia única de RestaurantesBO
     */
    public static synchronized RestaurantesBO getInstance() {
        if (instance == null) {
            instance = new RestaurantesBO(); // Crea una nueva instancia si no existe
        }
        return instance; // Retorna la instancia existente
    }

    @Override
    public List<RestauranteDTO> obtenerRestaurantesTodos() throws NegocioException {
        try {
            // Obtiene la lista de todos los restaurantes a través del DAO
            List<Restaurante> restaurantes = restaurantesDAO.obtenerRestaurantesTodos();
            // Convierte la lista de entidades a DTOs antes de devolverla
            return restauranteConvertidor.createFromEntities(restaurantes); 
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public RestauranteDTO obtenerRestaurantePorID(Long id) throws NegocioException {
        try {
            // Obtiene un restaurante específico por su ID a través del DAO
            Restaurante restaurante = restaurantesDAO.obtenerRestaurantePorID(id);
            // Convierte la entidad a DTO antes de devolverla
            return restauranteConvertidor.convertFromEntity(restaurante); 
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public RestauranteDTO obtenerRestaurantePorNumeroTelefono(String numeroTelefono) throws NegocioException {
        try {
            // Obtiene un restaurante específico por su número de teléfono a través del DAO
            Restaurante restaurante = restaurantesDAO.obtenerRestaurantePorNumeroTelefono(numeroTelefono);
            // Convierte la entidad a DTO antes de devolverla
            return restauranteConvertidor.convertFromEntity(restaurante); 
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
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
