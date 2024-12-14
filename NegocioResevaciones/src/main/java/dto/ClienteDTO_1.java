package dto;

import java.io.Serializable;

/**
 * Representa un cliente en el sistema.
 * Esta clase implementa la interfaz Serializable para permitir la
 * serialización de objetos de cliente.
 * 
 * @author Saul Neri
 */
public class ClienteDTO_1 implements Serializable {

    private static final long serialVersionUID = 1L;

     /** El identificador único del cliente. */
    private Long id;

    /** El nombre completo del cliente. */
    private String nombreCompleto;

    /** El número de teléfono del cliente. */
    private String telefono;

    /**
     * Constructor sin argumentos que inicializa un nuevo objeto ClienteDTO.
     */
    public ClienteDTO_1() {
        // Constructor vacío
    }

    /**
     * Constructor que inicializa un nuevo objeto ClienteDTO con el ID
     * especificado, nombre completo y teléfono.
     *
     * @param id el identificador único del cliente
     * @param nombreCompleto el nombre completo del cliente
     * @param telefono el número de teléfono del cliente
     */
    public ClienteDTO_1(Long id, String nombreCompleto, String telefono) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }

    /**
     * Constructor que inicializa un nuevo objeto ClienteDTO con el nombre
     * completo y el teléfono especificados.
     *
     * @param nombreCompleto el nombre completo del cliente
     * @param telefono el número de teléfono del cliente
     */
    public ClienteDTO_1(String nombreCompleto, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }

    /**
     * Constructor que inicializa un nuevo objeto ClienteDTO con el ID
     * especificado.
     *
     * @param id el identificador único del cliente
     */
    public ClienteDTO_1(Long id) {
        this.id = id;
    }

    /**
     * Constructor que inicializa un nuevo objeto ClienteDTO con el teléfono
     * especificado.
     *
     * @param telefono el número de teléfono del cliente
     */
    public ClienteDTO_1(String telefono) {
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
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ClienteDTO_1)) {
            return false;
        }
        ClienteDTO_1 other = (ClienteDTO_1) object;
        return (this.id != null && this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "%s, %s".formatted(this.nombreCompleto, this.telefono);
    }
}
