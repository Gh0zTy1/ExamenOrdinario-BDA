/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import bo.ReservacionesBO;
import conexion.Conexion;
import convertidores.ReservacionConvertidor;
import daos.MesasDAO;
import daos.ReservacionesDAO;
import fachadas.cancelarReservacionFCD;
import fachadas.consultarHistorialClienteFCD;
import fachadas.consultarHistorialRestauranteFCD;
import fachadas.crearReservacionFACHADA;


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
}
