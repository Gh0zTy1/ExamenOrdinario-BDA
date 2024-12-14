/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

/**
 *
 * @author caarl
 */
import conexion.IConexion;
import entidades.Cliente;
import idaos.IClienteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class ClienteDAO implements IClienteDAO {
    
    private final IConexion conexion;
    
    // Inyección de dependencias mediante constructor
    public ClienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public Cliente guardar(Cliente cliente) {
        EntityManager em = conexion.crearConexion();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            return cliente;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al guardar el cliente", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente actualizar(Cliente cliente) {
        EntityManager em = conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Cliente clienteActualizado = em.merge(cliente);
            em.getTransaction().commit();
            return clienteActualizado;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar el cliente", e);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean eliminar(Long id) {
        EntityManager em = conexion.crearConexion();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, id);
            if (cliente != null) {
                em.remove(em.contains(cliente) ? cliente : em.merge(cliente));
                em.getTransaction().commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar el cliente", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente buscarPorId(Long id) {
        EntityManager em = conexion.crearConexion();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Cliente buscarPorTelefono(String telefono) {
        EntityManager em = conexion.crearConexion();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.telefono = :telefono", 
                Cliente.class
            );
            query.setParameter("telefono", telefono);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> listarTodos() {
        EntityManager em = conexion.crearConexion();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                "SELECT c FROM Cliente c", 
                Cliente.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Cliente> buscarPorNombre(String nombreParcial) {
        EntityManager em = conexion.crearConexion();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                "SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(:nombre)", 
                Cliente.class
            );
            query.setParameter("nombre", "%" + nombreParcial + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}