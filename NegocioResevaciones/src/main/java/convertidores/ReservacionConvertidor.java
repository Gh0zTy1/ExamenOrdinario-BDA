

package convertidores;

import dto.ClienteDTO;
import dto.EstadoReservacionDTO;
import dto.MesaDTO;
import dto.ReservacionDTO;
import entidades.Cliente;
import entidades.EstadoReservacion;
import entidades.Mesa;
import entidades.Reservacion;

/**
 * Clase de conversión para objetos Reservacion en el sistema.
 * Permite la transformación bidireccional entre ReservacionDTO (Data Transfer Object)
 * y Reservacion (entidad de base de datos).
 * 
 * @author caarl
 */
public class ReservacionConvertidor extends Converter<ReservacionDTO, Reservacion> {
    
    /**
     * Constructor de ReservacionConvertidor.
     * Inicializa el convertidor especificando los métodos de conversión
     * a través de referencias a métodos estáticos.
     */
    public ReservacionConvertidor() {
        super(ReservacionConvertidor::convertirAEntidad, ReservacionConvertidor::convertirADTO);
    }
    
    /**
     * Convierte un objeto ReservacionDTO en un objeto Reservacion.
     * 
     * @param dto el objeto ReservacionDTO a convertir
     * @return un objeto Reservacion con los datos de dto
     */
    public static Reservacion convertirAEntidad(ReservacionDTO dto) {
        // Instancia de convertidores para las entidades relacionadas
        Converter<ClienteDTO, Cliente> clienteConvertidor = new ClienteConvertidor();
        Converter<MesaDTO, Mesa> mesaConvertidor = new MesaConvertidor();
        
        
        // Crear la entidad Reservacion
        Reservacion r = new Reservacion();
        r.setId(dto.getId());
        r.setMontoTotal(dto.getMontoTotal());
        r.setFechaHora(dto.getFechaHora());
        r.setCliente(clienteConvertidor.convertFromDto(dto.getCliente()));
        r.setMesa(mesaConvertidor.convertFromDto(dto.getMesa()));
        r.setFechaHoraRegistro(dto.getFechaHoraRegistro());
        
       
        
        // Asigna el estado y el número de personas
        r.setEstado(EstadoReservacion.valueOf(dto.getEstado().toString()));
        r.setNumeroPersonas(dto.getNumeroPersonas());
        
        return r;
    }
    
    /**
     * Convierte un objeto Reservacion en un objeto ReservacionDTO.
     * 
     * @param entidad el objeto Reservacion a convertir
     * @return un objeto ReservacionDTO con los datos de la entidad
     */
    public static ReservacionDTO convertirADTO(Reservacion entidad) {
        // Instancia de convertidores para las entidades relacionadas
        Converter<ClienteDTO, Cliente> clienteConvertidor = new ClienteConvertidor();
        Converter<MesaDTO, Mesa> mesaConvertidor = new MesaConvertidor();
        
        
        // Crear el DTO ReservacionDTO
        ReservacionDTO r = new ReservacionDTO();
        r.setId(entidad.getId());
        r.setMontoTotal(entidad.getMontoTotal());
        r.setFechaHora(entidad.getFechaHora());
        r.setCliente(clienteConvertidor.convertFromEntity(entidad.getCliente()));
        r.setMesa(mesaConvertidor.convertFromEntity(entidad.getMesa()));
        r.setFechaHoraRegistro(entidad.getFechaHoraRegistro());
        
        
        
        // Asigna el estado y el número de personas
        r.setEstado(EstadoReservacionDTO.valueOf(entidad.getEstado().toString()));
        r.setNumeroPersonas(entidad.getNumeroPersonas());
        
        return r;
    }
}
