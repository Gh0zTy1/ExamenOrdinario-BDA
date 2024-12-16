/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import Excepciones.DAOException;
import convertidores.ClienteConvertidor;
import dto.ClienteDTO;
import entidades.Cliente;
import excepciones.NegocioException;
import ibo.IClientesBO;
import idaos.IClientesDAO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase de negocio que implementa la interfaz {@link IClientesBO} para la gestión de
 * clientes en el sistema. Utiliza inyección de dependencias para trabajar con
 * la DAO y el convertidor de clientes.
 * 
 * @author caarl
 */
public class ClientesBO implements IClientesBO {

    private final IClientesDAO clientesDAO;
    private final ClienteConvertidor clienteConvertidor;

    /**
     * Constructor para inicializar el objeto con las dependencias necesarias.
     * 
     * @param clientesDAO la instancia de la DAO de clientes que realiza las operaciones de datos.
     * @param clienteConvertidor el convertidor utilizado para transformar entre entidades y DTOs.
     */
    public ClientesBO(IClientesDAO clientesDAO, ClienteConvertidor clienteConvertidor) {
        this.clientesDAO = clientesDAO;
        this.clienteConvertidor = clienteConvertidor;
    }

    /**
     * Realiza la inserción masiva de una lista de clientes en el sistema.
     * 
     * @param clientes una lista de objetos {@link ClienteDTO} que se desean insertar.
     * @throws NegocioException si ocurre un error durante la operación de negocio.
     */
    @Override
    public void insercionMasivaClientes(List<ClienteDTO> clientes) throws NegocioException {
        try {
            List<Cliente> entidadesClientes = clientes.stream()
                    .map(clienteConvertidor::convertFromDto)
                    .collect(Collectors.toList());
            clientesDAO.insercionMasivaClientes(entidadesClientes);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene todos los clientes registrados en el sistema.
     * 
     * @return una lista de objetos {@link ClienteDTO} que representan a todos los clientes.
     * @throws NegocioException si ocurre un error durante la operación de negocio.
     */
    @Override
    public List<ClienteDTO> obtenerClientesTodos() throws NegocioException {
        try {
            List<Cliente> entidadesClientes = clientesDAO.obtenerClientesTodos();
            return entidadesClientes.stream()
                    .map(clienteConvertidor::convertFromEntity)
                    .collect(Collectors.toList());
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Busca un cliente por su número de teléfono.
     * 
     * @param numeroTelefono el número de teléfono del cliente a buscar.
     * @return un objeto {@link ClienteDTO} que representa al cliente encontrado.
     * @throws NegocioException si no se encuentra al cliente o si ocurre un error en la operación.
     */
    @Override
    public ClienteDTO obtenerClientePorTelefono(String numeroTelefono) throws NegocioException {
        try {
            Cliente entidadCliente = clientesDAO.obtenerClientePorTelefono(numeroTelefono);
            if (entidadCliente == null) {
                throw new NegocioException("No se encontró al cliente con el teléfono dado");
            }
            return clienteConvertidor.convertFromEntity(entidadCliente);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
