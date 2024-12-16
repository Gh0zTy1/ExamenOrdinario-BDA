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
/**
 * Implementación de la interfaz IConexion utilizando el patrón Singleton.
 */
public class Conexion implements IConexion {

    private static Conexion instancia;
    private EntityManagerFactory emf;

    public Conexion() {
        try {
            this.emf = Persistence.createEntityManagerFactory("restaurantesPU");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear EntityManagerFactory: " + e.getMessage(), e);
        }
    }

    public static Conexion getInstance() {
        if (instancia == null) {
            synchronized (Conexion.class) {
                if (instancia == null) {
                    instancia = new Conexion();
                }
            }
        }
        return instancia;
    }

    @Override
    public EntityManager crearConexion() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory no está inicializado correctamente.");
        }
        return emf.createEntityManager();
    }
}
