
package convertidores;

import dto.ClienteDTO;
import entidades.Cliente;


/**
 * Clase de conversión para los objetos Cliente en el sistema.
 * Permite la transformación bidireccional entre ClienteDTO (Data Transfer Object)
 * y Cliente (entidad de base de datos). Extiende de la clase base Converter,
 * que proporciona una estructura general para convertidores.
 * 
 * Utiliza referencias a métodos estáticos para realizar las conversiones específicas 
 * de DTO a entidad y viceversa.
 * 
 * @author caarl
 */
public class ClienteConvertidor extends Converter<ClienteDTO, Cliente> {

    /**
     * Constructor de ClienteConvertidor.
     * Inicializa el convertidor especificando los métodos de conversión
     * a través de referencias a métodos estáticos.
     */
    public ClienteConvertidor() {
        super(ClienteConvertidor::convertirAEntidad, ClienteConvertidor::convertirADTO);
    }
    
    /**
     * Convierte un objeto ClienteDTO en un objeto Cliente.
     * 
     * @param clienteDTO el objeto ClienteDTO a convertir
     * @return un objeto Cliente con los datos de clienteDTO
     */
    public static Cliente convertirAEntidad(ClienteDTO clienteDTO) {
        Cliente c = new Cliente();
        c.setId(clienteDTO.getId());
        c.setNombreCompleto(clienteDTO.getNombreCompleto());
        c.setTelefono(clienteDTO.getTelefono());
        return c;
    }
    
    /**
     * Convierte un objeto Cliente en un objeto ClienteDTO.
     * 
     * @param cliente el objeto Cliente a convertir
     * @return un objeto ClienteDTO con los datos de cliente
     */
    public static ClienteDTO convertirADTO(Cliente cliente) {
        ClienteDTO c = new ClienteDTO();
        c.setId(cliente.getId());
        c.setNombreCompleto(cliente.getNombreCompleto());
        c.setTelefono(cliente.getTelefono());
        return c;
    }

}
