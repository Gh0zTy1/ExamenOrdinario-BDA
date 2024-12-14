/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;

/**
 * Representa una Mesa en el sistema.
 * Esta clase es utilizada para almacenar la información de una mesa específica en un restaurante.
 * @author caarl
 */
public class MesaDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /** El identificador único de la mesa. */
    private Long id;

    /** El código que representa la mesa. */
    private String codigo;

    /** El tipo de mesa asociado a esta instancia. */
    private TipoMesaDTO tipoMesa;

    /** La ubicación de la mesa en el restaurante. */
    private UbicacionMesaDTO ubicacion;

    /** El restaurante al cual pertenece la mesa. */
    private RestauranteDTO restaurante;

    /**
     * Constructor por defecto que inicializa una nueva instancia de MesaDTO.
     * Este constructor no realiza ninguna acción adicional.
     */
    public MesaDTO() {
        
    }

    /**
     * Constructor que inicializa una nueva instancia de MesaDTO con los atributos especificados.
     * 
     * @param id El identificador único de la mesa.
     * @param codigo El código que representa la mesa.
     * @param tipoMesa El tipo de mesa asociado a esta instancia.
     * @param ubicacion La ubicación de la mesa en el restaurante.
     * @param restaurante El restaurante al cual pertenece la mesa.
     */
    public MesaDTO(Long id, String codigo, TipoMesaDTO tipoMesa, UbicacionMesaDTO ubicacion, RestauranteDTO restaurante) {
        this.id = id;
        this.codigo = codigo;
        this.tipoMesa = tipoMesa;
        this.ubicacion = ubicacion;
        this.restaurante = restaurante;
    }

    /**
     * Constructor que inicializa una nueva instancia de MesaDTO con el código, tipo de mesa y ubicación especificados.
     * 
     * @param codigo El código que representa la mesa.
     * @param tipoMesa El tipo de mesa asociado a esta instancia.
     * @param ubicacion La ubicación de la mesa en el restaurante.
     */
    public MesaDTO(String codigo, TipoMesaDTO tipoMesa, UbicacionMesaDTO ubicacion) {
        this.codigo = codigo;
        this.tipoMesa = tipoMesa;
        this.ubicacion = ubicacion;
    }

    /**
     * Constructor que inicializa una nueva instancia de MesaDTO con un identificador específico.
     * 
     * @param id El identificador único de la mesa.
     */
    public MesaDTO(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador único de la mesa.
     * 
     * @return El identificador único de la mesa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la mesa.
     * 
     * @param id El identificador único de la mesa a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el código que representa la mesa.
     * 
     * @return El código de la mesa.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código que representa la mesa.
     * 
     * @param codigo El código de la mesa a establecer.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el tipo de mesa.
     * 
     * @return El tipo de mesa asociado a esta instancia de MesaDTO.
     */
    public TipoMesaDTO getTipoMesa() {
        return tipoMesa;
    }

    /**
     * Establece el tipo de mesa.
     * 
     * @param tipoMesa El tipo de mesa a establecer.
     */
    public void setTipoMesa(TipoMesaDTO tipoMesa) {
        this.tipoMesa = tipoMesa;
    }

    /**
     * Obtiene la ubicación de la mesa en el restaurante.
     * 
     * @return La ubicación de la mesa.
     */
    public UbicacionMesaDTO getUbicacion() {
        return ubicacion;
    }

    /**
     * Asigna la ubicación de la mesa en el restaurante.
     * 
     * @param ubicacion Ubicación de la mesa a asignar.
     */
    public void setUbicacion(UbicacionMesaDTO ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene la información del restaurante al cual pertenece la mesa.
     * 
     * @return Restaurante de la mesa (sucursal).
     */
    public RestauranteDTO getRestaurante() {
        return restaurante;
    }

    /**
     * Asigna el restaurante al cual pertenecerá la mesa.
     * 
     * @param restaurante Restaurante (sucursal).
     */
    public void setRestaurante(RestauranteDTO restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MesaDTO)) {
            return false;
        }
        MesaDTO other = (MesaDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MesaDTO[ id=" + id + " ]";
    }
}

