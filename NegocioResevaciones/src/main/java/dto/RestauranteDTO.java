/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.time.LocalTime;

import java.util.List;

/**
 * Representa un restaurante en el sistema.
 * @author caarl
 */
public class RestauranteDTO {

    private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private LocalTime horaApertura;
    private LocalTime horaCierre;
    private List<MesaDTO> mesas;

    /**
     * Constructor por defecto para restaurante.
     */
    public RestauranteDTO() {
    }

    /**
     * Constructor con ID para identificacion del restaurante.
     *
     * @param id ID del restaurante.
     */
    public RestauranteDTO(Long id) {
        this.id = id;
    }

    /**
     * Constructor para la construcción completa de un objeto restaurante de
     * forma inmediata.
     *
     * @param id Identificador del restaurante.
     * @param nombre Nombre distintivo de la sucursal del restaurante.
     * @param telefono Teléfono del restaurante concreto.
     * @param direccion Dirección de la sucursal.
     * @param horaApertura Horario de apertura del restaurante.
     * @param horaCierre Horario de cierre del restaurante.
     */
    public RestauranteDTO(Long id, String nombre, String telefono, String direccion, LocalTime horaApertura, LocalTime horaCierre) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.horaCierre = horaCierre;
        this.horaApertura = horaApertura;
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
     * Obtiene todas las mesas que tiene el restaurante.
     *
     * @return las mesas del restaurante.
     */
    public List<MesaDTO> getMesas() {
        return mesas;
    }

    /**
     * Asigna las mesas al restaurante.
     *
     * @param mesas mesas a asignar.
     */
    public void setMesas(List<MesaDTO> mesas) {
        this.mesas = mesas;
    }
}
