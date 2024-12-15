/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author caarl
 */
@Entity
@Table(name="tipo_mesas")
public class TipoMesa implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable=false, length=100, unique=true)
    private String nombre;
    
    @Column(name="maximo_personas", nullable=false)
    private Integer maximoPersonas;
    
    @Column(name="minimo_personas", nullable=false)
    private Integer minimoPersonas;
    
    @Column(name="precio", nullable=false)
    private Float precio;
    
    @OneToMany(mappedBy = "tipoMesa", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private List<Mesa> mesas;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMesa)) {
            return false;
        }
        TipoMesa other = (TipoMesa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.TipoMesa[ id=" + id + " ]";
    }
    
    /**
     * Constructor por defecto que inicializa una nueva instancia de TipoMesa.
     * Este constructor no realiza ninguna acción adicional.
     */
    public TipoMesa() {
        
    }

    /**
     * Obtiene el identificador único del tipo de mesa.
     * 
     * @return El identificador único del tipo de mesa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del tipo de mesa.
     * 
     * @param id El identificador único del tipo de mesa a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el número máximo de personas que pueden usar la mesa.
     * 
     * @return El número máximo de personas para el tipo de mesa.
     */
    public Integer getMaximoPersonas() {
        return maximoPersonas;
    }

    /**
     * Establece el número máximo de personas que pueden usar la mesa.
     * 
     * @param maximoPersonas El número máximo de personas a establecer.
     */
    public void setMaximoPersonas(Integer maximoPersonas) {
        this.maximoPersonas = maximoPersonas;
    }

    /**
     * Obtiene el número mínimo de personas que pueden usar la mesa.
     * 
     * @return El número mínimo de personas para el tipo de mesa.
     */
    public Integer getMinimoPersonas() {
        return minimoPersonas;
    }

    /**
     * Establece el número mínimo de personas que pueden usar la mesa.
     * 
     * @param minimoPersonas El número mínimo de personas a establecer.
     */
    public void setMinimoPersonas(Integer minimoPersonas) {
        this.minimoPersonas = minimoPersonas;
    }

    /**
     * Obtiene el precio asociado al tipo de mesa.
     * 
     * @return El precio del tipo de mesa.
     */
    public Float getPrecio() {
        return precio;
    }

    /**
     * Establece el precio asociado al tipo de mesa.
     * 
     * @param precio El precio a establecer para el tipo de mesa.
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la lista de mesas que pertenecen a este tipo.
     * 
     * @return La lista de mesas del tipo.
     */
    public List<Mesa> getMesas() {
        return mesas;
    }

    /**
     * Devuelve el nombre descriptivo del tipo de mesa
     * @return El nombre de la mesa
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre descriptivo del tipo de mesa
     * @param nombre El nombre de la mesa
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
