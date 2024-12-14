/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase encargada de modelar la entidad Mesa
 * 
 * @author caarl
 */
@Entity
@Table(name = "mesa")
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, length = 15)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "tipo_mesa", referencedColumnName = "id")
    private TipoMesa tipoMesa;

    @Enumerated(EnumType.STRING)
    @Column(name = "ubicacion", nullable = false)
    private UbicacionMesa ubicacion;

    @Column(name = "fecha_hora_disp", nullable=true, columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaNuevaDisponibilidad;

    @ManyToOne
    @JoinColumn(name = "restaurante", referencedColumnName = "id")
    private Restaurante restaurante;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mesa)) {
            return false;
        }
        Mesa other = (Mesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Mesa[ id=" + id + " ]";
    }

    /**
     * Constructor por defecto que inicializa una nueva instancia de Mesa. Este
     * constructor no realiza ninguna acción adicional.
     */
    public Mesa() {

    }

    /**
     * Constructor que inicializa una nueva instancia de Mesa con un
     * identificador específico.
     *
     * @param id El identificador único de la mesa.
     */
    public Mesa(Long id) {
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
     * @return El tipo de mesa asociado a esta instancia de Mesa.
     */
    public TipoMesa getTipoMesa() {
        return tipoMesa;
    }

    /**
     * Establece el tipo de mesa.
     *
     * @param tipoMesa El tipo de mesa a establecer.
     */
    public void setTipoMesa(TipoMesa tipoMesa) {
        this.tipoMesa = tipoMesa;
    }

    /**
     * Obtiene la ubicacion de la mesa en el restaurante
     *
     * @return La ubicacion de la mesa
     */
    public UbicacionMesa getUbicacion() {
        return ubicacion;
    }

    /**
     * Asigna la ubicacion de la mesa en el restaurante
     *
     * @param ubicacion Ubicacion de la mesa a asignar
     */
    public void setUbicacion(UbicacionMesa ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene la fecha y hora en la que la mesa estará disponible nuevamente.
     *
     * @return la fecha y hora de nueva disponibilidad de la mesa.
     */
    public LocalDateTime getFechaNuevaDisponibilidad() {
        return fechaNuevaDisponibilidad;
    }

    /**
     * Establece la fecha y hora en la que la mesa estará disponible nuevamente.
     *
     * @param fechaNuevaDisponibilidad la nueva fecha y hora de disponibilidad.
     */
    public void setFechaNuevaDisponibilidad(LocalDateTime fechaNuevaDisponibilidad) {
        this.fechaNuevaDisponibilidad = fechaNuevaDisponibilidad;
    }

    /**
     * Obtiene el restaurante asociado a la mesa.
     *
     * @return el restaurante al que pertenece esta mesa.
     */
    public Restaurante getRestaurante() {
        return restaurante;
    }

    /**
     * Establece el restaurante al que pertenece la mesa.
     *
     * @param restaurante el restaurante que se asociará a esta mesa.
     */
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

}
