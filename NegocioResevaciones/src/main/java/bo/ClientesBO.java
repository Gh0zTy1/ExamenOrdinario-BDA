/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import Excepciones.DAOException;
import convertidores.ClienteConvertidor;
import daos.ClientesDAO;
import dto.ClienteDTO;
import entidades.Cliente;
import excepciones.NegocioException;
import ibo.IClientesBO;
import idaos.IClientesDAO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de negocio que implementa la interfaz IClientesBO para la gestión de
 * clientes en el sistema.
 * Utiliza el patrón Singleton para garantizar que solo haya una instancia de
 * ClientesBO.
 * 
 * @author caarl
 */
public class ClientesBO implements IClientesBO {

    private final IClientesDAO clientesDAO; // Interfaz para la capa de acceso a datos
    private final ClienteConvertidor clienteConvertidor; // Convertidor para DTOs y entidades

    // Instancia única de la clase
    private static ClientesBO instance;

    /**
     * Constructor privado para implementar Singleton.
     * Inicializa la DAO y el convertidor de clientes.
     */
    private ClientesBO() {
        this.clientesDAO = ClientesDAO.getInstance();
        this.clienteConvertidor = new ClienteConvertidor();
    }

    /**
     * Método para obtener la instancia única de ClientesBO.
     *
     * @return instancia única de ClientesBO
     */
    public static synchronized ClientesBO getInstance() {
        if (instance == null) {
            instance = new ClientesBO();
        }
        return instance;
    }

    @Override
    public void insercionMasivaClientes(List<ClienteDTO> clientes) throws NegocioException {
        try {
            // Convierte la lista de DTOs a entidades
            List<Cliente> entidadesClientes = clientes.stream()
                    .map(clienteConvertidor::convertFromDto)
                    .collect(Collectors.toList());
            // Llama a la DAO para la inserción masiva
            clientesDAO.insercionMasivaClientes(entidadesClientes);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<ClienteDTO> obtenerClientesTodos() throws NegocioException {
        try {
            // Obtiene todos los clientes desde la DAO
            List<Cliente> entidadesClientes = clientesDAO.obtenerClientesTodos();
            // Convierte las entidades a DTOs
            return entidadesClientes.stream()
                    .map(clienteConvertidor::convertFromEntity)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public ClienteDTO obtenerClientePorTelefono(String numeroTelefono) throws NegocioException {
        try {
            // Obtiene un cliente específico por su número de teléfono
            Cliente entidadCliente = clientesDAO.obtenerClientePorTelefono(numeroTelefono);
            if (entidadCliente == null) {
                throw new NegocioException("No se encontró al cliente con el teléfono dado");
            }
            // Convierte la entidad a DTO
            return clienteConvertidor.convertFromEntity(entidadCliente);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
