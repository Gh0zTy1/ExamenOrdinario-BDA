/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conexion;

/**
 *
 * @author caarl
 */

import fabricas.EntityManagerFactoryHelper;

import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        // Obtener EntityManagerFactory desde el helper
        EntityManagerFactory emf = EntityManagerFactoryHelper.getEntityManagerFactory();

        // Inyectar la dependencia en la clase Conexion
        IConexion conexion =  Conexion.getInstance();

        // Crear una conexión (EntityManager)
        System.out.println("Probando conexión...");
        conexion.crearConexion();
        System.out.println("Conexión exitosa.");
    }
}
