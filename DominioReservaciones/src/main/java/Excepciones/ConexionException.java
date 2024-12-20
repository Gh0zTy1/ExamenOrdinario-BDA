/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 * @author caarl
 */
public class ConexionException extends Exception {

   
    public ConexionException() {
    }

    /**
     * @param message El mensaje de error que describe la excepción.
     */
    public ConexionException(String message) {
        super(message);
    }

    /**
     * Constructor que crea una nueva instancia de {@code ConexionException} 
     * con un mensaje y una causa específica.
     * 
     * @param message El mensaje de error que describe la excepción.
     * @param cause La causa subyacente de la excepción 
     *              (otra excepción que la causó).
     */
    public ConexionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor que crea una nueva instancia de {@code ConexionException} 
     * con una causa específica.
     * 
     * @param cause La causa subyacente de la excepción 
     *              (otra excepción que la causó).
     */
    public ConexionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor que crea una nueva instancia de {@code ConexionException} 
     * con un mensaje, una causa, y opciones para habilitar la supresión de 
     * excepciones y habilitar o deshabilitar la escritura del stack trace.
     * 
     * @param message El mensaje de error que describe la excepción.
     * @param cause La causa subyacente de la excepción 
     *              (otra excepción que la causó).
     * @param enableSuppression Indica si se permite la supresión de 
     *                          excepciones.
     * @param writableStackTrace Indica si el stack trace es escribible.
     */
    public ConexionException(String message, Throwable cause, 
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}

