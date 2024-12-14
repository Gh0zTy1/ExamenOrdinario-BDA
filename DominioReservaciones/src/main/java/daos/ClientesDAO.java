/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import Excepciones.DAOException;
import conexion.IConexion;
import entidades.Cliente;
import idaos.IClientesDAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 *
 * @author caarl
 */
public class ClientesDAO implements IClientesDAO {

    private final IConexion conexion; // Dependencia inyectada

    // Constructor con inyección de dependencias
    public ClientesDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public void insercionMasivaClientes(List<Cliente> clientes) throws DAOException {
        EntityManager entityManager = conexion.crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            for (Cliente cliente : clientes) {
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

    @Override
    public List<Cliente> obtenerClientesTodos() throws DAOException {
        EntityManager entityManager = conexion.crearConexion();

        try {
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener la lista de todos los clientes", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Cliente obtenerClientePorTelefono(String numeroTelefono) throws DAOException {
        EntityManager entityManager = conexion.crearConexion();

        try {
            Cliente cl = entityManager.createQuery(
                            "SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class)
                    .setParameter("telefono", numeroTelefono)
                    .getSingleResult();

            if (cl == null) {
                throw new DAOException("No se encontró al cliente con el número de teléfono dado.");
            }

            return cl;
        } catch (Exception e) {
            throw new DAOException("Error al obtener la información del cliente, por favor intente más tarde.", e);
        } finally {
            entityManager.close();
        }
    }
}
