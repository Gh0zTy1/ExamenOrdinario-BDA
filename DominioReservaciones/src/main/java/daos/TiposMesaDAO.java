
package daos;

import Excepciones.DAOException;
import conexion.Conexion;
import conexion.IConexion;
import entidades.TipoMesa;
import idaos.ITiposMesaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 * Clase concreta de la interfaz ITiposMesaDAO
 * @author caarl
 */
public class TiposMesaDAO implements ITiposMesaDAO {

 private final IConexion conexion;

    // Constructor para inyección de dependencias

    public TiposMesaDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    
    @Override
    public List<TipoMesa> obtenerTiposMesaTodos() throws DAOException {
       
   EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();
        
        try {
           TypedQuery<TipoMesa> query = entityManager.createQuery("SELECT t FROM TipoMesa t", TipoMesa.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener todos los tipos de mesas");
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void agregarTipoMesa(TipoMesa tipoMesa) throws DAOException {
      
        EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            
            boolean existe = entityManager.createQuery("SELECT COUNT(t) FROM TipoMesa t WHERE t.nombre LIKE :nombreTipo", Long.class)
                            .setParameter("nombreTipo", tipoMesa.getNombre())
                            .getSingleResult() > 0;
            if (existe) {
                throw new DAOException("El tipo de mesa a agregar ya esta registrado");
            }
            
            transaction.begin();
           entityManager.persist(tipoMesa);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            
            throw new DAOException(e.getMessage());
        } finally {
           if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    @Override
    public void eliminarTipoMesa(Long id) throws DAOException {
         EntityManager entityManager = Conexion.getInstance().crearConexion();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            
            boolean noExiste = entityManager.createQuery("SELECT COUNT(t) FROM TipoMesa t WHERE t.id = :id", Long.class)
                            .setParameter("id", id)
                            .getSingleResult() == 0;
            if (noExiste) {
                throw new DAOException("El tipo de mesa con el ID dado no existe");
            }
            
            entityManager.getTransaction().begin();

            TipoMesa tipoMesa = conexion.crearConexion().find(TipoMesa.class, id);
            if (tipoMesa != null) {
                conexion.crearConexion().remove(tipoMesa);
            }

            conexion.crearConexion().getTransaction().commit();
        } catch (DAOException e) {
            if (conexion.crearConexion().getTransaction().isActive()) {
                conexion.crearConexion().getTransaction().rollback();
            }
            
            throw new DAOException(e.getMessage());
        } finally {
           if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }
    @Override
public float obtenerPrecioPorTipoMesa(Long id) throws DAOException {
    EntityManager entityManager = Conexion.getInstance().crearConexion();
    try {
        // Consulta para obtener el precio por ID
        float precio = entityManager.createQuery(
                "SELECT t.precio FROM TipoMesa t WHERE t.id = :id", float.class)
                .setParameter("id", id)
                .getSingleResult();

        return precio;
    } catch (NoResultException e) {
        throw new DAOException("No se encontró un tipo de mesa con el ID especificado.");
    } catch (Exception e) {
        throw new DAOException("Error al obtener el precio del tipo de mesa: " + e.getMessage());
    } finally {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}

}
