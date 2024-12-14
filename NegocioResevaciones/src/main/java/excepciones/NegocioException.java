/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author caarl
 */
public class NegocioException extends Exception {

    /**
     * Constructor que crea una nueva instancia de ServicioException
     * con un mensaje específico.
     * 
     * @param message El mensaje que describe la excepción.
     */
    public NegocioException(String message) {
        super(message);
    }
}
