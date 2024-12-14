
package dto;

/**
 * Enumeración usada para mostrar el estado actual de la reservación.
 * @author caarl
 */
public enum EstadoReservacionDTO {
    /** Estado cuando la reservación está pendiente. */
    PENDIENTE,
    
    /** Estado cuando la reservación ha sido finalizada. */
    FINALIZADA,
    
    /** Estado cuando la reservación ha sido cancelada. */
    CANCELADA;
}
