/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package convertidores;

import dto.MesaDTO;
import dto.RestauranteDTO;
import dto.TipoMesaDTO;
import dto.UbicacionMesaDTO;
import entidades.Mesa;
import entidades.Restaurante;
import entidades.TipoMesa;
import entidades.UbicacionMesa;

/**
 * Clase de conversión para objetos Mesa en el sistema.
 * Permite la transformación bidireccional entre MesaDTO (Data Transfer Object)
 * y Mesa (entidad de base de datos).
 * Extiende de la clase base Converter, que proporciona una estructura general para convertidores.
 * 
 * Utiliza convertidores anidados para transformar dependencias de TipoMesa y Restaurante.
 * 
 * @author caarl
 */
public class MesaConvertidor extends Converter<MesaDTO, Mesa> {
  
    /**
     * Constructor de MesaConvertidor.
     * Inicializa el convertidor especificando los métodos de conversión
     * a través de referencias a métodos estáticos.
     */
    public MesaConvertidor() {
        super(MesaConvertidor::convertirAEntidad, MesaConvertidor::convertirADTO);
    }
    
    /**
     * Convierte un objeto MesaDTO en un objeto Mesa.
     * Utiliza convertidores adicionales para transformar objetos relacionados, como TipoMesa y Restaurante.
     * 
     * @param dto el objeto MesaDTO a convertir
     * @return un objeto Mesa con los datos de dto
     */
    public static Mesa convertirAEntidad(MesaDTO dto) {
        Converter<TipoMesaDTO, TipoMesa> tipoMesaConvertidor = new TipoMesaConvertidor();
        
        Mesa mesa = new Mesa();
        mesa.setId(dto.getId());
        mesa.setCodigo(dto.getCodigo());
        mesa.setTipoMesa(tipoMesaConvertidor.convertFromDto(dto.getTipoMesa()));
        
        // Conversión de Ubicación de Mesa
        mesa.setUbicacion(UbicacionMesa.valueOf(dto.getUbicacion().toString()));
        
        // Conversión de Restaurante relacionado
        Restaurante r = new Restaurante(
                dto.getRestaurante().getId(),
                dto.getRestaurante().getNombre(),
                dto.getRestaurante().getTelefono(),
                dto.getRestaurante().getDireccion(),
                dto.getRestaurante().getHoraApertura(),
                dto.getRestaurante().getHoraCierre()
        );
        
        mesa.setRestaurante(r);
        
        return mesa;
    }
    
    /**
     * Convierte un objeto Mesa en un objeto MesaDTO.
     * Utiliza convertidores adicionales para transformar objetos relacionados, como TipoMesa y RestauranteDTO.
     * 
     * @param entidad el objeto Mesa a convertir
     * @return un objeto MesaDTO con los datos de la entidad
     */
    public static MesaDTO convertirADTO(Mesa entidad) {
        Converter<TipoMesaDTO, TipoMesa> tipoMesaConvertidor = new TipoMesaConvertidor();
        
        MesaDTO mesa = new MesaDTO();
        mesa.setId(entidad.getId());
        mesa.setCodigo(entidad.getCodigo());
        mesa.setTipoMesa(tipoMesaConvertidor.convertFromEntity(entidad.getTipoMesa()));
        
        // Conversión de Ubicación de MesaDTO
        mesa.setUbicacion(UbicacionMesaDTO.valueOf(entidad.getUbicacion().toString()));
        
        // Conversión de Restaurante relacionado
        RestauranteDTO r = new RestauranteDTO(
                entidad.getRestaurante().getId(),
                entidad.getRestaurante().getNombre(),
                entidad.getRestaurante().getTelefono(),
                entidad.getRestaurante().getDireccion(),
                entidad.getRestaurante().getHoraApertura(),
                entidad.getRestaurante().getHoraCierre()
        );
        
        mesa.setRestaurante(r);
        
        return mesa;
    }
}
