
package dto;

/**
 * Enumeración que representa las diferentes ubicaciones posibles para una mesa en el restaurante.
 * 
 * Esta enumeración contiene las opciones de ubicación que pueden ser asignadas a las mesas,
 * lo que permite categorizar las mesas según su localización dentro del establecimiento.
 * 
 * @author caarl
 */
public enum UbicacionMesaDTO {
    
    /** 
     * Representa una mesa ubicada junto a la ventana. 
     */
    VENTANA,
    
    /** 
     * Representa una mesa ubicada en la terraza. 
     */
    TERRAZA,
    
    /** 
     * Representa una mesa ubicada en el área general del restaurante. 
     */
    GENERAL;

}
