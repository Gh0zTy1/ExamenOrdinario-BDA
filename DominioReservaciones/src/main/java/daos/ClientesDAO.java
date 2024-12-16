/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.DAOException;
import cifrado.CifradoTelefono;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Cliente;
import idaos.IClientesDAO;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Implementación del Data Access Object (DAO) para la entidad {@link Cliente}.
 * Esta clase proporciona métodos para realizar operaciones CRUD sobre los clientes
 * en la base de datos, incluyendo inserciones masivas y búsquedas por teléfono.
 * Además, realiza cifrado y descifrado de los números de teléfono de los clientes.
 * 
 * <p>Se utilizan transacciones para asegurar la consistencia de los datos en las operaciones.</p>
 * 
 * @author caarl
 */
public class ClientesDAO implements IClientesDAO {
    
    /**
     * Instancia de conexión a la base de datos, inyectada mediante el constructor.
     */
    private final IConexion conexion; 

    /**
     * Constructor de la clase {@link ClientesDAO}. Recibe una instancia de {@link IConexion}
     * para la gestión de la conexión a la base de datos.
     * 
     * @param conexion La conexión a la base de datos.
     */
    public ClientesDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Realiza la inserción masiva de clientes en la base de datos. Los teléfonos de los clientes
     * son cifrados antes de ser persistidos.
     * 
     * @param clientes Lista de objetos {@link Cliente} que serán insertados en la base de datos.
     * @throws DAOException Si ocurre un error durante la inserción masiva de clientes.
     */
    @Override
    public void insercionMasivaClientes(List<Cliente> clientes) throws DAOException {
        EntityManager entityManager = conexion.crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            for (Cliente cliente : clientes) {
                // Cifrar el teléfono antes de persistir
                if (cliente.getTelefono() != null) {
                    cliente.setTelefono(CifradoTelefono.encriptar(cliente.getTelefono()));
                }
                entityManager.persist(cliente);
            }
            entityManager.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback(); // Aseguramos rollback en caso de error
            throw new DAOException("Error al insertar clientes de manera masiva.", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Obtiene todos los clientes almacenados en la base de datos.
     * Los números de teléfono son descifrados antes de ser retornados.
     * 
     * @return Una lista de objetos {@link Cliente}.
     * @throws DAOException Si ocurre un error al obtener la lista de clientes.
     */
    @Override
    public List<Cliente> obtenerClientesTodos() throws DAOException {
        EntityManager entityManager = conexion.crearConexion();
        try {
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            List<Cliente> clientes = query.getResultList();
            
            // Descifrar los teléfonos
            return clientes.stream()
                .map(cliente -> {
                    try {
                        // Descifrar solo si el teléfono no está vacío
                        if (cliente.getTelefono() != null && !cliente.getTelefono().isEmpty()) {
                            cliente.setTelefono(CifradoTelefono.desencriptar(cliente.getTelefono()));
                        }
                        return cliente;
                    } catch (Exception e) {
                        // Manejar error de descifrado
                        throw new RuntimeException("Error al descifrar teléfono", e);
                    }
                })
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new DAOException("Error al obtener la lista de todos los clientes", e);
        } finally {
            entityManager.close();
        }
    }

    /**
     * Obtiene un cliente específico buscando por su número de teléfono. El número de teléfono
     * es cifrado antes de ser usado en la consulta, y es descifrado antes de retornar el cliente.
     * 
     * @param numeroTelefono El número de teléfono del cliente a buscar.
     * @return El cliente correspondiente al número de teléfono proporcionado.
     * @throws DAOException Si ocurre un error durante la búsqueda o si no se encuentra al cliente.
     */
    @Override
    public Cliente obtenerClientePorTelefono(String numeroTelefono) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        try {
            // Cifrar el número de teléfono para la consulta
            String telefonoCifrado = CifradoTelefono.encriptar(numeroTelefono);

            // Buscar cliente por el teléfono cifrado
            Cliente cliente = entityManager.createQuery(
                    "SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class)
                    .setParameter("telefono", telefonoCifrado)
                    .getSingleResult();

            // Descifrar el teléfono antes de retornarlo
            if (cliente.getTelefono() != null) {
                cliente.setTelefono(CifradoTelefono.desencriptar(cliente.getTelefono()));
            }

            return cliente;
        } catch (NoResultException e) {
            throw new DAOException("No se encontró al cliente con el número de teléfono dado.", e);
        } catch (Exception e) {
            throw new DAOException("Error al obtener la información del cliente, por favor intente más tarde.", e);
        } finally {
            entityManager.close();
        }
    }
}
