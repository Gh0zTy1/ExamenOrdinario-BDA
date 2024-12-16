/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import bo.MesasBO;
import bo.ReservacionesBO;
import bo.RestaurantesBO;
import bo.TiposMesaBO;
import conexion.Conexion;
import convertidores.MesaConvertidor;
import convertidores.ReservacionConvertidor;
import convertidores.RestauranteConvertidor;
import convertidores.TipoMesaConvertidor;
import daos.MesasDAO;
import daos.ReservacionesDAO;
import daos.RestaurantesDAO;
import daos.TiposMesaDAO;
import dto.TipoMesaDTO;
import excepciones.NegocioException;
import fachadas.ActualizarRestauranteFACHADA;
import fachadas.AgregarRestauranteFACHADA;
import fachadas.CargarMesasFACHADA;
import fachadas.RestaurantesFachada;
import fachadas.TiposMesaFachada;
import fachadas.agregarMesasFCD;
import fachadas.agregarTiposMesaFCD;
import fachadas.cancelarReservacionFCD;
import fachadas.consultarHistorialClienteFCD;
import fachadas.consultarHistorialRestauranteFCD;
import fachadas.crearReservacionFACHADA;
import fachadas.eliminarMesasFCD;
import iFachadas.ITiposMesaFachada;
import ibo.ITiposMesaBO;
import idaos.ITiposMesaDAO;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author caarl
 */
public class fabricaFCD {
    
    

    public static cancelarReservacionFCD fabricaFCDCancelar(){
        
        Conexion conexion = Conexion.getInstance();

        MesasDAO mesasdao = new MesasDAO(conexion);
        ReservacionesDAO reservDAO = new ReservacionesDAO(conexion, mesasdao);

        
        ReservacionConvertidor reservacionConvertidor = new ReservacionConvertidor();
        ReservacionesBO reservacionesbo = new ReservacionesBO(reservDAO,reservacionConvertidor);
        
        

        
     return new cancelarReservacionFCD(reservacionesbo);
    }   
    
   public static crearReservacionFACHADA fabricaFCDRerservar(){
        
        Conexion conexion = Conexion.getInstance();

        MesasDAO mesasdao = new MesasDAO(conexion);
        ReservacionesDAO reservDAO = new ReservacionesDAO(conexion, mesasdao);

        
        ReservacionConvertidor reservacionConvertidor = new ReservacionConvertidor();
        ReservacionesBO reservacionesbo = new ReservacionesBO(reservDAO,reservacionConvertidor);
        
        

        
     return new crearReservacionFACHADA(reservacionesbo);
    }   
   
   public static consultarHistorialClienteFCD fabricaFCDConsultarHCliente(){
        
        Conexion conexion = Conexion.getInstance();

        MesasDAO mesasdao = new MesasDAO(conexion);
        ReservacionesDAO reservDAO = new ReservacionesDAO(conexion, mesasdao);

        
        ReservacionConvertidor reservacionConvertidor = new ReservacionConvertidor();
        ReservacionesBO reservacionesbo = new ReservacionesBO(reservDAO,reservacionConvertidor);
        
        

        
     return new consultarHistorialClienteFCD(reservacionesbo);
    }   
    
   public static consultarHistorialRestauranteFCD fabricaFCDConsultarHRestauranrte(){
        
        Conexion conexion = Conexion.getInstance();

        MesasDAO mesasdao = new MesasDAO(conexion);
        ReservacionesDAO reservDAO = new ReservacionesDAO(conexion, mesasdao);

        
        ReservacionConvertidor reservacionConvertidor = new ReservacionConvertidor();
        ReservacionesBO reservacionesbo = new ReservacionesBO(reservDAO,reservacionConvertidor);
        
        

        
     return new consultarHistorialRestauranteFCD(reservacionesbo);
    }  
    public static agregarMesasFCD fabricaFCDAgregarMesas() {
        // Crear la conexión a la base de datos
        Conexion conexion = Conexion.getInstance();

        // Crear el DAO necesario para acceder a las mesas
        MesasDAO mesasDAO = new MesasDAO(conexion);

        // Crear los convertidores
        TipoMesaConvertidor tipoMesaConvertidor = new TipoMesaConvertidor();
        MesaConvertidor mesaConvertidor = new MesaConvertidor();

        // Crear el BO (lógica de negocio) para las mesas, pasando los convertidores
        MesasBO mesasBO = new MesasBO(mesasDAO, mesaConvertidor, tipoMesaConvertidor);

        // Crear y devolver la instancia de agregarMesasFCD
        return new agregarMesasFCD(mesasBO);
    }
    
    public static CargarMesasFACHADA fabricaFCDCargarMesas() {
    // Crear la conexión a la base de datos
    Conexion conexion = Conexion.getInstance();

    // Crear el DAO necesario para acceder a las mesas
    MesasDAO mesasDAO = new MesasDAO(conexion);

    // Crear los convertidores
    TipoMesaConvertidor tipoMesaConvertidor = new TipoMesaConvertidor();
    MesaConvertidor mesaConvertidor = new MesaConvertidor();

    // Crear el BO (lógica de negocio) para las mesas
    MesasBO mesasBO = new MesasBO(mesasDAO, mesaConvertidor, tipoMesaConvertidor);

    // Crear y devolver la instancia de CargarMesasFACHADA
    return new CargarMesasFACHADA(mesasBO);
}

   /**
     * Método para obtener los tipos de mesa utilizando la fachada.
     *
     * @return Lista de nombres de tipos de mesa.
     * @throws NegocioException Si ocurre un error en la capa de negocio.
     */
    public static List<TipoMesaDTO> obtenerTiposMesa() throws NegocioException {
    // Crear la conexión a la base de datos
    Conexion conexion = Conexion.getInstance();

    // Crear el DAO necesario para acceder a los tipos de mesa
    TiposMesaDAO mesasDAO = new TiposMesaDAO(conexion);

    // Crear el convertidor de tipo de mesa
    TipoMesaConvertidor tipoMesaConvertidor = new TipoMesaConvertidor();

    // Crear el BO de tipos de mesa
    ITiposMesaBO tiposMesaBO = new TiposMesaBO(mesasDAO, tipoMesaConvertidor);

    // Crear la fachada para manejar los tipos de mesa
    TiposMesaFachada tiposMesaFachada = new TiposMesaFachada(tiposMesaBO);

    // Obtener los tipos de mesa
    List<TipoMesaDTO> tiposMesaDTOs = tiposMesaFachada.obtenerTiposMesaTodos();


    // Retornar la lista de nombres de los tipos de mesa
    return tiposMesaDTOs;
}
    
    public static eliminarMesasFCD fabricaFCDEliminarMesas() {
    // Crear la conexión a la base de datos
    Conexion conexion = Conexion.getInstance();

    // Crear el DAO necesario para acceder a las mesas
    MesasDAO mesasDAO = new MesasDAO(conexion);

    // Crear los convertidores
    TipoMesaConvertidor tipoMesaConvertidor = new TipoMesaConvertidor();
    MesaConvertidor mesaConvertidor = new MesaConvertidor();

    // Crear el BO (lógica de negocio) para las mesas, pasando los convertidores
    MesasBO mesasBO = new MesasBO(mesasDAO, mesaConvertidor, tipoMesaConvertidor);

    // Crear y devolver la instancia de eliminarMesasFCD
    return new eliminarMesasFCD(mesasBO);
}

    
    public static ActualizarRestauranteFACHADA fabricaFCDActualizarRestaurante() {
    // Crear la conexión a la base de datos
    Conexion conexion = Conexion.getInstance();

    // Crear el DAO necesario para acceder a los restaurantes
    RestaurantesDAO restaurantesDAO = new RestaurantesDAO(conexion);

    // Crear el convertidor de restaurantes
    RestauranteConvertidor restauranteConvertidor = new RestauranteConvertidor();

    // Crear el BO (lógica de negocio) para restaurantes
    RestaurantesBO restaurantesBO = new RestaurantesBO(restaurantesDAO, restauranteConvertidor);

    // Crear y devolver la instancia de ActualizarRestauranteFACHADA
    return new ActualizarRestauranteFACHADA(restaurantesBO);
}

public static AgregarRestauranteFACHADA fabricaFCDAgregarRestaurante() {
    // Crear la conexión a la base de datos
    Conexion conexion = Conexion.getInstance();

    // Crear el DAO necesario para acceder a los restaurantes
    RestaurantesDAO restaurantesDAO = new RestaurantesDAO(conexion);

    // Crear el convertidor de restaurantes
    RestauranteConvertidor restauranteConvertidor = new RestauranteConvertidor();

    // Crear el BO (lógica de negocio) para restaurantes
    RestaurantesBO restaurantesBO = new RestaurantesBO(restaurantesDAO, restauranteConvertidor);

    // Crear y devolver la instancia de AgregarRestauranteFACHADA
    return new AgregarRestauranteFACHADA(restaurantesBO);
}
    
public static agregarTiposMesaFCD fabricaFCDAgregarTipoMesa() {
        // Crear la conexión a la base de datos
        Conexion conexion = Conexion.getInstance();

        // Crear el DAO necesario para acceder a los tipos de mesa
        ITiposMesaDAO tiposMesaDAO = new TiposMesaDAO(conexion);

        // Crear el convertidor de tipos de mesa
        TipoMesaConvertidor tipoMesaConvertidor = new TipoMesaConvertidor();

        // Crear el BO para tipos de mesa
        TiposMesaBO tiposMesaBO = new TiposMesaBO(tiposMesaDAO, tipoMesaConvertidor);

        // Crear y devolver la instancia de AgregarTiposMesaFCD
        return new agregarTiposMesaFCD(tiposMesaBO);
    }


public static RestaurantesFachada fabricaFCDRestaurantes() {
    // Crear la conexión a la base de datos
    Conexion conexion = Conexion.getInstance();

    // Crear los DAO necesarios
    RestaurantesDAO restaurantesDAO = new RestaurantesDAO(conexion);

    // Crear el convertidor
    RestauranteConvertidor restauranteConvertidor = new RestauranteConvertidor();

    // Crear el BO (lógica de negocio) para los restaurantes
    RestaurantesBO restaurantesBO = new RestaurantesBO(restaurantesDAO, restauranteConvertidor);

    // Crear y devolver la instancia de RestaurantesFachada
    return new RestaurantesFachada(restaurantesBO);
}

}
