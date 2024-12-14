/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package convertidores;

import dto.TipoMesaDTO;
import entidades.TipoMesa;

/**
 * Clase de conversión para los distintos tipos de mesa en el sistema.
 * Permite la transformación bidireccional entre TipoMesaDTO (Data Transfer Object)
 * y TipoMesa (entidad de base de datos).
 * 
 * @author caarl
 */
public class TipoMesaConvertidor extends Converter<TipoMesaDTO, TipoMesa> {
    
    /**
     * Constructor de TipoMesaConvertidor.
     * Inicializa el convertidor especificando los métodos de conversión
     * a través de referencias a métodos estáticos.
     */
    public TipoMesaConvertidor() {
        super(TipoMesaConvertidor::convertirAEntidad, TipoMesaConvertidor::convertirADTO);
    }
    
    /**
     * Convierte un objeto TipoMesaDTO en un objeto TipoMesa.
     * 
     * @param dto el objeto TipoMesaDTO a convertir
     * @return un objeto TipoMesa con los datos de dto
     */
    public static TipoMesa convertirAEntidad(TipoMesaDTO dto) {
        // Crear la entidad TipoMesa
        TipoMesa t = new TipoMesa();
        
        t.setId(dto.getId());
        t.setMaximoPersonas(dto.getMaximoPersonas());
        t.setMinimoPersonas(dto.getMinimoPersonas());
        t.setNombre(dto.getNombre());
        t.setPrecio(dto.getPrecio());
        
        return t;
    }
    
    /**
     * Convierte un objeto TipoMesa en un objeto TipoMesaDTO.
     * 
     * @param entidad el objeto TipoMesa a convertir
     * @return un objeto TipoMesaDTO con los datos de la entidad
     */
    public static TipoMesaDTO convertirADTO(TipoMesa entidad) {
        // Crear el DTO TipoMesaDTO
        TipoMesaDTO t = new TipoMesaDTO();
        
        t.setId(entidad.getId());
        t.setMaximoPersonas(entidad.getMaximoPersonas());
        t.setMinimoPersonas(entidad.getMinimoPersonas());
        t.setNombre(entidad.getNombre());
        t.setPrecio(entidad.getPrecio());
        
        return t;
    }
}
