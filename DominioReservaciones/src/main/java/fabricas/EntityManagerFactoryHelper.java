/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

/**
 *
 * @author caarl
 */
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Helper para crear una instancia de EntityManagerFactory.
 */
public class EntityManagerFactoryHelper {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("restaurantesPU");
        }
        return emf;
    }
}