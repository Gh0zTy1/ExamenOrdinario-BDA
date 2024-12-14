/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

/**
 *
 * @author caarl
 */
import Excepciones.DAOException;
import conexion.Conexion;
import entidades.Cliente;
import idaos.IClientesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class ClientesDAO implements IClientesDAO {

    private static IClientesDAO instancia;

    public ClientesDAO() {
    }
    
    public static IClientesDAO getInstance() {
        if (instancia == null) {
            instancia = new ClientesDAO();
        }
        
        return instancia;
    }
    
    @Override
    public void insercionMasivaClientes(List<Cliente> clientes) throws DAOException {
        
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try {
            transaction.begin();
            
            for (Cliente cliente : clientes) {
                entityManager.persist(cliente);
            }
            entityManager.flush();
            
            transaction.commit();
            
        } catch (Exception e) {
            throw new DAOException("Error al insertar clientes de manera masiva.");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Cliente> obtenerClientesTodos() throws DAOException {
         
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        
        try {
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c", Cliente.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener la lista de todos los clientes");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Cliente obtenerClientePorTelefono(String numeroTelefono) throws DAOException {
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        
        try {
            Cliente cl = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class)
                    .setParameter("telefono", numeroTelefono)
                    .getSingleResult();
            
            if (cl == null) {
                throw new DAOException("No se encontro al cliente con el numero de telefono dado");
            }
            
            return cl;
        } catch (DAOException e) {
            throw new DAOException("Error al obtener la informacion del cliente, por favor intente mas tarde");
        } finally {
            entityManager.close();
        }
    }
}
