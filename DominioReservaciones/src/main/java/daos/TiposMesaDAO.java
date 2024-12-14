
package daos;

import Excepciones.DAOException;
import conexion.Conexion;
import entidades.TipoMesa;
import idaos.ITiposMesaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

/**
 * Clase concreta de la interfaz ITiposMesaDAO
 * @author caarl
 */
public class TiposMesaDAO implements ITiposMesaDAO {

 private final EntityManager entityManager;

    // Constructor para inyección de dependencias
    public TiposMesaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public List<TipoMesa> obtenerTiposMesaTodos() throws DAOException {
       

        try {
            TypedQuery<TipoMesa> query = entityManager.createQuery("SELECT t FROM TipoMesa t", TipoMesa.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new DAOException("Error al obtener todos los tipos de mesas");
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void agregarTipoMesa(TipoMesa tipoMesa) throws DAOException {
      
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
            entityManager.close();
        }
    }

    @Override
    public void eliminarTipoMesa(Long id) throws DAOException {
        

        try {
            
            boolean noExiste = entityManager.createQuery("SELECT COUNT(t) FROM TipoMesa t WHERE t.id = :id", Long.class)
                            .setParameter("id", id)
                            .getSingleResult() == 0;
            if (noExiste) {
                throw new DAOException("El tipo de mesa con el ID dado no existe");
            }
            
            entityManager.getTransaction().begin();

            TipoMesa tipoMesa = entityManager.find(TipoMesa.class, id);
            if (tipoMesa != null) {
                entityManager.remove(tipoMesa);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            
            throw new DAOException(e.getMessage());
        } finally {
            entityManager.close();
        }
    }
}
