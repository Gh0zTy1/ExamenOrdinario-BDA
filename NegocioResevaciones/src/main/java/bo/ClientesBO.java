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
 * Clase de negocio que implementa la interfaz IClientesBO para la gestión de
 * clientes en el sistema.
 * Ahora utiliza inyección de dependencias para la DAO y el convertidor.
 * 
 * @author caarl
 */
public class ClientesBO implements IClientesBO {

    private final IClientesDAO clientesDAO;
    private final ClienteConvertidor clienteConvertidor;

    /**
     * Constructor para inyección de dependencias.
     * 
     * @param clientesDAO la DAO de clientes
     * @param clienteConvertidor el convertidor de clientes
     */
    public ClientesBO(IClientesDAO clientesDAO, ClienteConvertidor clienteConvertidor) {
        this.clientesDAO = clientesDAO;
        this.clienteConvertidor = clienteConvertidor;
    }

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
