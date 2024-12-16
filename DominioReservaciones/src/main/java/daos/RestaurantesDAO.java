/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package daos;

import Excepciones.DAOException;
import conexion.Conexion;
import conexion.IConexion;
import entidades.Restaurante;
import idaos.IRestaurantesDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Implementa los metodos definidos por IRestaurantesDAO
 * @author caarl
 */
public class RestaurantesDAO implements IRestaurantesDAO {

   private final IConexion conexion;

    // Constructor para inyección de dependencias

    public RestaurantesDAO(IConexion conexion) {
        this.conexion = conexion;
    }

 

    
    @Override
    public List<Restaurante> obtenerRestaurantesTodos() throws DAOException {
       EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            TypedQuery<Restaurante> query = entityManager.createQuery("SELECT r FROM Restaurante r", Restaurante.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener todos los restaurantes");
        } finally {
           if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Restaurante obtenerRestaurantePorID(Long id) throws DAOException {
       EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            return entityManager.find(Restaurante.class, id);
        } catch (NoResultException e) {
            throw new DAOException("No se encontro al restaurante con el ID dado [telefono: %d]".formatted(id));
        } catch (Exception e) {
            throw new DAOException("Error al obtener el restaurante por ID");
        } finally {
           if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public Restaurante obtenerRestaurantePorNumeroTelefono(String numeroTelefono) throws DAOException {
       EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            TypedQuery<Restaurante> query = entityManager.createQuery(
                "SELECT r FROM Restaurante r WHERE r.telefono = :telefono", Restaurante.class);
            query.setParameter("telefono", numeroTelefono);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new DAOException("No se encontro al restaurante con el numero de telefono dado [telefono: %s]".formatted(numeroTelefono));
        } catch (Exception e) {
            System.out.println("ERROR CONSULTA TELEFONO(%s): %s".formatted(e.getClass().getSimpleName(), e.getMessage()));
            throw new DAOException("Error al obtener el restaurante por número de teléfono");
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void agregarRestaurante(Restaurante restaurante) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void actualizarRestaurante(Restaurante restaurante) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarRestaurante(Long idRestaurante) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
