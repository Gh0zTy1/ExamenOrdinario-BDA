/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Clase encargada de modelar la entidad Reserva.
 * 
 * @author caarl
 */
@Entity
@Table(name="reservacion")
public class Reservacion implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha_hora", nullable=false, columnDefinition="TIMESTAMP")
    private LocalDateTime fechaHora;
    
    @Column(name="numero_personas", nullable=false)
    private Integer numeroPersonas;
    
    @Enumerated(EnumType.STRING)
    @Column(name="estado", nullable=false)
    private EstadoReservacion estado;
    
    @ManyToOne
    @JoinColumn(name="mesa_id", nullable=true, referencedColumnName = "id")
    private Mesa mesa;
    
    @ManyToOne
    @JoinColumn(name="cliente_id", nullable=false)
    private Cliente cliente;
    
    @Column(name="fecha_hora_registro", nullable=false, updatable=false, columnDefinition="TIMESTAMP")
    private LocalDateTime fechaHoraRegistro;
    
    @Column(name="monto_total")
    private Float montoTotal;
    
    @ManyToOne
    @JoinColumn(name="multa_id")
    private Multa multa;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservacion)) {
            return false;
        }
        Reservacion other = (Reservacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Reservacion[ id=" + id + " ]";
    }
    
    @PrePersist
    public void onCreate() {
        this.setFechaHoraRegistro(LocalDateTime.now());
    }
    
    /**
     * Constructor por defecto que inicializa una nueva instancia de Reservacion.
     * Este constructor no realiza ninguna acción adicional.
     */
    public Reservacion() {
        this.estado = EstadoReservacion.PENDIENTE;
                
    }
    
    /**
     * Constructor que inicializa una nueva instancia de Reservacion con un identificador específico.
     * 
     * @param id El identificador único de la reservación.
     */
    public Reservacion(Long id) {
        this.id = id;
        this.estado = EstadoReservacion.PENDIENTE;
    }
    
    /**
     * Obtiene el identificador único de la reservación.
     * 
     * @return El identificador único de la reservación.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la reservación.
     * 
     * @param id El identificador único de la reservación a establecer.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la fecha y hora de la reservación.
     * 
     * @return La fecha y hora de la reservación.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora de la reservación.
     * 
     * @param fechaHora La fecha y hora de la reservación a establecer.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el número de personas para la reservación.
     * 
     * @return El número de personas de la reservación.
     */
    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    /**
     * Establece el número de personas para la reservación.
     * 
     * @param numeroPersonas El número de personas a establecer.
     */
    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    /**
     * Obtiene el estado de la reservación.
     * 
     * @return El estado de la reservación.
     */
    public EstadoReservacion getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la reservación.
     * 
     * @param estado El estado de la reservación a establecer.
     */
    public void setEstado(EstadoReservacion estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la mesa asociada a la reservación.
     * 
     * @return La mesa de la reservación.
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Establece la mesa asociada a la reservación.
     * 
     * @param mesa La mesa a establecer para la reservación.
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * Devuelve el cliente asociado a la reservacion
     * @return El cliente que pidio la reservacion
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Asigna el cliente a asociar con la reservacion
     * @param cliente El cliente a asignar
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Regresa el monto toal a pagar por la reservacion
     * @return El monto total
     */
    public Float getMontoTotal() {
        return montoTotal;
    }

    /**
     * Asigna el monto total a pagar por la reservacion
     * @param montoTotal Monto total a pagar
     */
    public void setMontoTotal(Float montoTotal) {
        this.montoTotal = montoTotal;
    }

    /**
     * Multa por cancelacion asociada a la reservacion
     * @return the multa
     */
    public Multa getMulta() {
        return multa;
    }

    /**
     * Asigna una multa por cancelacion a la reservacion
     * @param multa Multa a asignar
     */
    public void setMulta(Multa multa) {
        this.multa = multa;
    }

    /**
     * Regresa la fecha y hora exacta de cuando se registro la reservacion en el sistema
     * @return Fecha y hora exacta
     */
    public LocalDateTime getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }
    

    /**
     * Asigna la fecha y hora exacta de cuando se registro la reservacion en el sistema
     * @param fechaHoraRegistro Fecha y hora a asignar
     */
    public void setFechaHoraRegistro(LocalDateTime fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }
}
