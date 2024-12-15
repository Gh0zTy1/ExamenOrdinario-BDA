/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import bo.MesasBO;
import bo.ReservacionesBO;
import conexion.Conexion;
import convertidores.MesaConvertidor;
import convertidores.ReservacionConvertidor;
import convertidores.TipoMesaConvertidor;
import daos.MesasDAO;
import daos.ReservacionesDAO;
import fachadas.agregarMesasFCD;
import fachadas.cancelarReservacionFCD;
import fachadas.consultarHistorialClienteFCD;
import fachadas.consultarHistorialRestauranteFCD;
import fachadas.crearReservacionFACHADA;


/**
 *
 * @author caarl
 */
public class fabricaFCD {
    
    
    public static cancelarReservacionFCD getInstancia(){
        Conexion conexion = Conexion.getInstance();
        MesasDAO mesasdao = new MesasDAO(conexion);
        
        
    }
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
   
   
}
