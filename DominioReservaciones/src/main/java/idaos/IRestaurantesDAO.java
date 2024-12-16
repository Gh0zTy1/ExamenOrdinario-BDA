/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import Excepciones.DAOException;
import entidades.Restaurante;
import java.util.List;

/**
 * Define las operaciones necesarias para administrar sucursales de restaurantes en el sistema
 * @author neri
 */
public interface IRestaurantesDAO {
    /**
     * Obtiene todos las sucursales del restaurante en el sistema
     * @return Lista sucursales restaurante
     * @throws DAOException Si ocurre un error en la consulta
     */
    public List<Restaurante> obtenerRestaurantesTodos() throws DAOException;
    
    /**
     * Obtiene la sucursal del restaurante con el ID dado
     * @param id ID de la sucursal del restaurante a buscar
     * @return Restaurante sucursal
     * @throws DAOException Si ocurre un error en la consulta
     */
    public Restaurante obtenerRestaurantePorID(Long id) throws DAOException;
    
    /**
     * Obtiene la sucursal del restaurante con el numero telefonico dado
     * @param numeroTelefono Numero de telefono de la sucursal
     * @return Restaurante
     * @throws DAOException Si ocurre un error en la consulta 
     */
    public Restaurante obtenerRestaurantePorNumeroTelefono(String numeroTelefono) throws DAOException;
    
    /**
     * Agrega una nueva sucursal del restaurante al sistema
     * @param restaurante Nueva sucursal del restaurante
     * @throws DAOException Si ocurre un error en la insercion
     */
    public void agregarRestaurante(Restaurante restaurante) throws DAOException;
    
    /**
     * Actualiza la informacion de la sucursal del restaurante dado
     * @param restaurante Sucursal del restaurante
     * @throws DAOException Si ocurre un error en la modificacion
     */
    public void actualizarRestaurante(Restaurante restaurante) throws DAOException;
    
    /**
     * Elimina una sucursal en el sistema
     * @param idRestaurante ID de la sucursal a eliminar
     * @throws excepciones.DAOException Si ocurre un error en la eliminacion
     */
    public void eliminarRestaurante(Long idRestaurante) throws DAOException;
}
