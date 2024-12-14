/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package convertidores;

import dto.MesaDTO;
import dto.RestauranteDTO;
import entidades.Mesa;
import entidades.Restaurante;

/**
 * Clase de conversión para objetos Restaurante en el sistema.
 * Permite la transformación bidireccional entre RestauranteDTO (Data Transfer Object)
 * y Restaurante (entidad de base de datos).
 * 
 * @author caarl
 */
public class RestauranteConvertidor extends Converter<RestauranteDTO, Restaurante> {
    
    /**
     * Constructor de RestauranteConvertidor.
     * Inicializa el convertidor especificando los métodos de conversión
     * a través de referencias a métodos estáticos.
     */
    public RestauranteConvertidor() {
        super(RestauranteConvertidor::convertirAEntidad, RestauranteConvertidor::convertirADTO);
    }
    
    /**
     * Convierte un objeto RestauranteDTO en un objeto Restaurante.
     * 
     * @param dto el objeto RestauranteDTO a convertir
     * @return un objeto Restaurante con los datos de dto
     */
    public static Restaurante convertirAEntidad(RestauranteDTO dto) {
        // Crear la entidad Restaurante
        Restaurante r = new Restaurante(
                dto.getId(),
                dto.getNombre(),
                dto.getTelefono(),
                dto.getDireccion(),
                dto.getHoraApertura(),
                dto.getHoraCierre()
        );
        
        // Verifica si hay mesas asociadas y las convierte
        if (dto.getMesas() != null) {
            Converter<MesaDTO, Mesa> mesaConvertidor = new MesaConvertidor();
            r.setMesas(mesaConvertidor.createFromDtos(dto.getMesas()));
        }
        
        return r;
    }
    
    /**
     * Convierte un objeto Restaurante en un objeto RestauranteDTO.
     * 
     * @param entidad el objeto Restaurante a convertir
     * @return un objeto RestauranteDTO con los datos de la entidad
     */
    public static RestauranteDTO convertirADTO(Restaurante entidad) {
        // Crear el DTO RestauranteDTO
        RestauranteDTO r = new RestauranteDTO(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getTelefono(),
                entidad.getDireccion(),
                entidad.getHoraApertura(),
                entidad.getHoraCierre()
        );
        
        // Verifica si hay mesas asociadas y las convierte
        if (entidad.getMesas() != null) {
            Converter<MesaDTO, Mesa> mesaConvertidor = new MesaConvertidor();
            r.setMesas(mesaConvertidor.createFromEntities(entidad.getMesas()));
        }
        
        return r;
    }
}
