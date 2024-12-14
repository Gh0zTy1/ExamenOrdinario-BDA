
package idaos;

import Excepciones.DAOException;
import entidades.Multa;
import java.util.List;

/**
 * Define las operaciones basicas para manejar multas de reservaciones
 * @author caarl
 */
public interface IMultasDAO {
    
    /**
     * Obtiene todos los tipos de multa existentes en el sistema
     * @return Lista con tipos de multa
     * @throws DAOException Si ocurre un error en la consulta
     */
    public List<Multa> obtenerTiposMultaTodos() throws DAOException;
   
    /**
    * Agrega un nuevo tipo de multa al sistema
    * @param multa Nuevo tipo de multa a agregar
    * @throws DAOException Si ocurre un error en la insercion
    */
    public void agregarTipoMulta(Multa multa) throws DAOException;
    
    /**
     * Elimina un tipo de multa en el sistema
     * @param idMulta ID de la multa a eliminar del sistema
     * @throws DAOException 
     */
    public void eliminarTipoMulta(Long idMulta) throws DAOException;
}
