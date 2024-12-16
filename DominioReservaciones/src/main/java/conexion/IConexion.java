/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;

/**
 *
 * @author caarl
 */
public interface IConexion {
    
    

   /**
    * 
    * @return 
    */  
public EntityManager crearConexion();
    
}
