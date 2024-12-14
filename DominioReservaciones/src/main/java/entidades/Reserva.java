/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase encargada de modelar la entidad Reserva.
 * 
 * @author caarl
 */
@Entity
@Table(name = "reservas")
public class Reserva implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(name = "hora_reserva", nullable = false)
    private LocalDateTime horaReserva; 
    
    @Column(name = "fecha_reserva", nullable = false)
    private LocalDateTime fechaReserva; 

    @Column(name = "numero_personas", nullable = false)
    private int numeroPersonas; 

    @Column(nullable = false)
    private double costo; 

    @Column(nullable = false)
    private String estado; 
    
    @Column(nullable = true)
    private double multa; 

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente; 

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa; 

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    private Restaurante restaurante; 

    
    /**
     * Constructor por defecto de la clase.
     * 
     */
    public Reserva() {
    }

    /**
     * Constructor para inicializar los atributos necesarios para realizar una 
     * insercion.
     * 
     * @param numeroPersonas Numero de personas.
     * @param costo Costo de la reserva.
     * @param estado Estado de la reserva.
     * @param multa Multa por cancelación, si aplica.
     * @param cliente Cliente al que le pertenece la reserva.
     * @param mesa Mesa a la que le pertenece la reserva.
     * @param restaurante Restaurante al que pertenece la reserva.
     */
    public Reserva(Long id, LocalDateTime horaReserva, LocalDateTime fechaReserva, int numeroPersonas, double costo, String estado, double multa, Cliente cliente, Mesa mesa, Restaurante restaurante) {    
        this.id = id;
        this.horaReserva = horaReserva;
        this.fechaReserva = fechaReserva;
        this.numeroPersonas = numeroPersonas;
        this.costo = costo;
        this.estado = estado;
        this.multa = multa;
        this.cliente = cliente;
        this.mesa = mesa;
        this.restaurante = restaurante;
    }

    /**
     * Constructor para inializar todos los atributos de la clase.
     * 
     * @param numeroPersonas Numero de personas.
     * @param costo Costo de la reserva.
     * @param estado Estado de la reserva.
     * @param multa Multa por cancelación, si aplica.
     * @param cliente Cliente al que le pertenece la reserva.
     * @param mesa Mesa a la que le pertenece la reserva.
     * @param restaurante Restaurante al que pertenece la reserva.
     */
    


    
    public Reserva(LocalDateTime horaReserva, LocalDateTime fechaReserva, int numeroPersonas, double costo, String estado, double multa, Cliente cliente, Mesa mesa, Restaurante restaurante) {
        this.horaReserva = horaReserva;
        this.fechaReserva = fechaReserva;
        this.numeroPersonas = numeroPersonas;
        this.costo = costo;
        this.estado = estado;
        this.multa = multa;
        this.cliente = cliente;
        this.mesa = mesa;
        this.restaurante = restaurante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHoraReserva() {
        return horaReserva;
    }

    public void setHoraReserva(LocalDateTime horaReserva) {
        this.horaReserva = horaReserva;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    
    

    

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", horaReserva=" + horaReserva + ", fechaReserva=" + fechaReserva + ", numeroPersonas=" + numeroPersonas + ", costo=" + costo + ", estado=" + estado + ", multa=" + multa + ", cliente=" + cliente + ", mesa=" + mesa + ", restaurante=" + restaurante + '}';
    }

    /**
     * Metodo toString para representar en linea de texto los atributos 
     * de la clase.
     * 
     * @return String con todos los atributos de la clase.
     */
    

}
