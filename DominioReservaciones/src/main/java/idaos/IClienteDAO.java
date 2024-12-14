/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

/**
 *
 * @author caarl
 */
import entidades.Cliente;
import java.util.List;

public interface IClienteDAO {
    /**
     * Método para guardar un nuevo cliente
     * @param cliente Cliente a guardar
     * @return Cliente guardado con su ID generado
     */
    Cliente guardar(Cliente cliente);

    /**
     * Método para actualizar un cliente existente
     * @param cliente Cliente a actualizar
     * @return Cliente actualizado
     */
    Cliente actualizar(Cliente cliente);

    /**
     * Método para eliminar un cliente por su ID
     * @param id Identificador del cliente a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    boolean eliminar(Long id);

    /**
     * Buscar cliente por su ID
     * @param id Identificador del cliente
     * @return Cliente encontrado o null si no existe
     */
    Cliente buscarPorId(Long id);

    /**
     * Buscar cliente por teléfono
     * @param telefono Teléfono del cliente
     * @return Cliente encontrado o null si no existe
     */
    Cliente buscarPorTelefono(String telefono);

    /**
     * Listar todos los clientes
     * @return Lista de todos los clientes
     */
    List<Cliente> listarTodos();

    /**
     * Método para buscar clientes por un fragmento de su nombre
     * @param nombreParcial Parte del nombre a buscar
     * @return Lista de clientes que coinciden con el nombre parcial
     */
    List<Cliente> buscarPorNombre(String nombreParcial);
}