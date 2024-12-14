/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conexion;

/**
 *
 * @author caarl
 */

import javax.persistence.EntityManager;

public class ConnectionTest {
    public static void main(String[] args) {
        try {
            // Obtener la instancia del EntityManager
            EntityManager entityManager = Conexion.getInstance().crearConexion();

            // Simplemente validar que se obtuvo una instancia válida del EntityManager
            if (entityManager != null) {
                System.out.println("¡Conexión establecida exitosamente!");
            } else {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        } catch (Exception e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}