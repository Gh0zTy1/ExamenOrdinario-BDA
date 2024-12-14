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
@Table(name="cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="nombre_completo", nullable=false, length=100)
    private String nombreCompleto;
    
    @Column(name="telefono", nullable=false, length=15, unique=true)
    private String telefono;

    @OneToMany(
            mappedBy="cliente", 
            cascade={
                CascadeType.PERSIST
            }, 
            orphanRemoval=true
    )
    private List<Reservacion> reservaciones;
    
    /**
     * Constructor sin argumentos que inicializa un nuevo objeto Cliente.
     */
    public Cliente() {

    }

    /**
     * Constructor que inicializa un nuevo objeto Cliente con el ID
     * especificado.
     *
     * @param id el identificador único del cliente
     */
    public Cliente(Long id) {
        this.id = id;
    }

    /**
     * Constructor que inicializa un nuevo objeto Cliente con el teléfono
     * especificado.
     *
     * @param telefono el número de teléfono del cliente
     */
    public Cliente(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el identificador único del cliente.
     *
     * @return el identificador del cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del cliente.
     *
     * @param id el identificador del cliente a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre completo del cliente.
     *
     * @return el nombre completo del cliente
     */
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    /**
     * Establece el nombre completo del cliente.
     *
     * @param nombreCompleto el nombre completo del cliente a establecer
     */
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return el número de teléfono del cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     *
     * @param telefono el número de teléfono del cliente a establecer
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Cliente[ id=" + id + " ]";
    }
}
