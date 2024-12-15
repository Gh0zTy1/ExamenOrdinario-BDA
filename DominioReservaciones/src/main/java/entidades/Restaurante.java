/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalTime;
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
 * Clase encargada de modelar la entidad Restaurante.
 * 
 * @author caarl
 */
@Entity
@Table(name = "restaurante")
public class Restaurante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", nullable = false, length = 150, unique=true)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 15, unique=true)
    private String telefono;

    @Column(name = "hora_apertura", nullable = false, columnDefinition = "TIME")
    private LocalTime horaApertura;

    @Column(name = "hora_cierre", nullable = false, columnDefinition = "TIME")
    private LocalTime horaCierre;
    
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Mesa> mesas;

    /**
     * Constructor por defecto para restaurante
     */
    public Restaurante() {

    }

    /**
     * Constructor con ID para identificacion del restaurante
     *
     * @param id ID del restaurante
     */
    public Restaurante(Long id) {
        this.id = id;
    }

    /**
     * Constructor para la construccion completa de un objeto restaurante de
     * forma inmediata
     *
     * @param id Identificador del restaurante
     * @param nombre Nombre distintivo de la sucursal del restaurante (variacion)
     * @param telefono Telefono del restaurante concreto
     * @param direccion Direccion de la sucursal
     * @param horaApertura Horario de apertura del restaurante
     * @param horaCierre Horario de cierre del restaurante
     */
    public Restaurante(Long id, String nombre, String telefono, String direccion, LocalTime horaApertura, LocalTime horaCierre) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.horaCierre = horaCierre;
        this.horaApertura = horaApertura;
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
        if (!(object instanceof Restaurante)) {
            return false;
        }
        Restaurante other = (Restaurante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Restaurante[ id=" + id + " ]";
    }

    /**
     * Obtiene el identificador único del restaurante.
     *
     * @return el ID del restaurante.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del restaurante.
     *
     * @param id el ID que se asignará al restaurante.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del restaurante.
     *
     * @return el nombre del restaurante.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del restaurante.
     *
     * @param nombre el nombre que se asignará al restaurante.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del restaurante.
     *
     * @return la dirección del restaurante.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del restaurante.
     *
     * @param direccion la dirección que se asignará al restaurante.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el número de teléfono del restaurante.
     *
     * @return el número de teléfono del restaurante.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del restaurante.
     *
     * @param telefono el número de teléfono que se asignará al restaurante.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la hora de apertura del restaurante.
     *
     * @return la hora de apertura del restaurante.
     */
    public LocalTime getHoraApertura() {
        return horaApertura;
    }

    /**
     * Establece la hora de apertura del restaurante.
     *
     * @param horaApertura la hora de apertura que se asignará al restaurante.
     */
    public void setHoraApertura(LocalTime horaApertura) {
        this.horaApertura = horaApertura;
    }

    /**
     * Obtiene la hora de cierre del restaurante.
     *
     * @return la hora de cierre del restaurante.
     */
    public LocalTime getHoraCierre() {
        return horaCierre;
    }

    /**
     * Establece la hora de cierre del restaurante.
     *
     * @param horaCierre la hora de cierre que se asignará al restaurante.
     */
    public void setHoraCierre(LocalTime horaCierre) {
        this.horaCierre = horaCierre;
    }

    /**
     * Obtiene todas las mesas que tiene el restaurante
     * @return Las mesas del restaurante
     */
    public List<Mesa> getMesas() {
        return mesas;
    }

    /**
     * Asigna las mesas al restaurante
     * @param mesas Mesas a asignar
     */
    public void setMesas(List<Mesa> mesas) {
        this.mesas = mesas;
    }
}
