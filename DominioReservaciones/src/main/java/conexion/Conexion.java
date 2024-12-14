/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author caarl
 */
public class Conexion implements IConexion {

    private final EntityManagerFactory emf;

    // Constructor con inyección de dependencias
    public Conexion(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public EntityManager crearConexion() {
        return emf.createEntityManager();
    }
}
