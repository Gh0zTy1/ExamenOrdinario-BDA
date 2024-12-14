/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import bo.RestaurantesBO;
import conexion.IConexion;
import convertidores.RestauranteConvertidor;
import daos.RestaurantesDAO;
import idaos.IRestaurantesDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author caarl
 */
public class RestaurantesBOFactory {

    public static RestaurantesBO createRestaurantesBO(IConexion conexion) {
        IRestaurantesDAO restaurantesDAO = new RestaurantesDAO((EntityManager) conexion);
        RestauranteConvertidor restauranteConvertidor = new RestauranteConvertidor();
        return new RestaurantesBO(restaurantesDAO, restauranteConvertidor);
    }
}
