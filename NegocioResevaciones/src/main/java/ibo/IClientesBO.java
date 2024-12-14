
package ibo;

import dto.ClienteDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Define las operaciones necesarias para interactuar con la informacion de 
 * clientes en el sistema
 * @author caarl
 */
public interface IClientesBO {

    /**
     * Inserta la lista de clientes dada
     *
     * @param clientes clientes a registrar
     * @throws excepciones.NegocioException
     */
    public void insercionMasivaClientes(List<ClienteDTO> clientes) throws NegocioException;

    /**
     * Regresa una lista con todos los clientes en el sistema.
     *
     * @return Lista de `ClienteDTO`
     * @throws excepciones.NegocioException
     */
    public List<ClienteDTO> obtenerClientesTodos() throws NegocioException;

    /**
     * Obtiene el cliente por su número de teléfono registrado en el sistema
     *
     * @param numeroTelefono Número de teléfono del cliente
     * @return Objeto `ClienteDTO`
     */
    public ClienteDTO obtenerClientePorTelefono(String numeroTelefono) throws NegocioException;
}
