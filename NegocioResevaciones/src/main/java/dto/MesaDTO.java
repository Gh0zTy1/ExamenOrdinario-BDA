/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Clase que representa el DTO de la entidad Mesa.
 * Se utiliza para transferir datos entre capas sin exponer directamente la entidad.
 * 
 * @author caarl
 */
public class MesaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; // Identificador único de la mesa
    private String codigo; // Código de la mesa
    private TipoMesaDTO tipoMesa; // Tipo de mesa
    private UbicacionMesaDTO ubicacion; // Ubicación de la mesa
    private LocalDateTime fechaNuevaDisponibilidad; // Fecha de nueva disponibilidad
    private RestauranteDTO restaurante; // Restaurante asociado a la mesa

    /**
     * Constructor por defecto.
     */
    public MesaDTO() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param id Identificador único de la mesa.
     * @param codigo Código de la mesa.
     * @param tipoMesa Tipo de la mesa (DTO).
     * @param ubicacion Ubicación de la mesa (DTO).
     * @param fechaNuevaDisponibilidad Fecha de nueva disponibilidad de la mesa.
     * @param restaurante Restaurante asociado a la mesa (DTO).
     */
    public MesaDTO(Long id, String codigo, TipoMesaDTO tipoMesa, UbicacionMesaDTO ubicacion,
                   LocalDateTime fechaNuevaDisponibilidad, RestauranteDTO restaurante) {
        this.id = id;
        this.codigo = codigo;
        this.tipoMesa = tipoMesa;
        this.ubicacion = ubicacion;
        this.fechaNuevaDisponibilidad = fechaNuevaDisponibilidad;
        this.restaurante = restaurante;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoMesaDTO getTipoMesa() {
        return tipoMesa;
    }

    public void setTipoMesa(TipoMesaDTO tipoMesa) {
        this.tipoMesa = tipoMesa;
    }

    public UbicacionMesaDTO getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionMesaDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    public LocalDateTime getFechaNuevaDisponibilidad() {
        return fechaNuevaDisponibilidad;
    }

    public void setFechaNuevaDisponibilidad(LocalDateTime fechaNuevaDisponibilidad) {
        this.fechaNuevaDisponibilidad = fechaNuevaDisponibilidad;
    }

    public RestauranteDTO getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteDTO restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public String toString() {
        return "MesaDTO{" +
               "id=" + id +
               ", codigo='" + codigo + '\'' +
               ", tipoMesa=" + tipoMesa +
               ", ubicacion=" + ubicacion +
               ", fechaNuevaDisponibilidad=" + fechaNuevaDisponibilidad +
               ", restaurante=" + restaurante +
               '}';
    }
}
