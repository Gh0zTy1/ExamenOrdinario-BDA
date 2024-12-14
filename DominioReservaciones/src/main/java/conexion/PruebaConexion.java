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
import javax.persistence.EntityTransaction;

public class PruebaConexion {
    public static void main(String[] args) {
        // Obtén la instancia de la conexión
        Conexion conexion = Conexion.getInstance();
        
        // Crea un EntityManager
        EntityManager em = conexion.crearConexion();
        
        // Inicia una transacción para comprobar que la conexión es válida
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin(); // Inicia la transacción
            System.out.println("Conexión exitosa a la base de datos.");
            transaction.commit(); // Realiza el commit de la transacción
        } catch (Exception e) {
            // Si ocurre algún error, muestra el mensaje
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback(); // Revierte la transacción en caso de error
            }
        } finally {
            em.close(); // Cierra el EntityManager
        }
    }
}
