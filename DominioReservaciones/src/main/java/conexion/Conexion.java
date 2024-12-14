/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author caarl
 */
public class Conexion {
    
    

    private static Conexion instancia;
    private EntityManagerFactory emf;
            
    private Conexion() {
        this.emf = Persistence.createEntityManagerFactory("restaurantePU");
    }
    
    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        
        return instancia;
    }
    
    public EntityManager crearConexion() {
        return emf.createEntityManager();
    }
}
