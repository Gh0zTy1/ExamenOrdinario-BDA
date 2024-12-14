/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author caarl
 */
@Entity
@Table(name="multa")
public class Multa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="descripcion", nullable=false, unique=true)
    private String descripcion;
    
    @Column(name="porcentaje", nullable=false)
    private Float porcentaje;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Multa)) {
            return false;
        }
        Multa other = (Multa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Multa{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", porcentaje=" + porcentaje +
                '}';
    }
    
    /**
     * Constructor por defecto que inicializa una nueva instancia de Multa.
     * Este constructor no realiza ninguna acción adicional.
     */
    public Multa() {
        
    }
    
    /**
     * Constructor que inicializa una nueva instancia de Multa con un identificador específico.
     * 
     * @param id El identificador único de la multa.
     */
    public Multa(Long id) {
        this.id = id;
    }
    
    /**
     * Obtiene el identificador único de la multa.
     * 
     * @return El identificador único de la multa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la multa.
     * 
     * @param id El identificador único de la multa a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la descripción de la multa.
     * 
     * @return La descripción de la multa.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la multa.
     * 
     * @param descripcion La descripción de la multa a establecer.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el porcentaje asociado a la multa.
     * 
     * @return El porcentaje de la multa.
     */
    public Float getPorcentaje() {
        return porcentaje;
    }

    /**
     * Establece el porcentaje asociado a la multa.
     * 
     * @param porcentaje El porcentaje de la multa a establecer.
     */
    public void setPorcentaje(Float porcentaje) {
        this.porcentaje = porcentaje;
    }
}
