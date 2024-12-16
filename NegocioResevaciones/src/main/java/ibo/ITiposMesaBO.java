
package ibo;

import dto.TipoMesaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Define las operaciones necesarias para interactuar con los tipos de mesa en el sistema
 * @author caarl
 */
public interface ITiposMesaBO {

    /**
     * Devuelve todos los tipos de mesa en el sistema
     *
     * @return Lista de `TipoMesaDTO`
     * @throws excepciones.NegocioException
     */
    public List<TipoMesaDTO> obtenerTiposMesaTodos() throws NegocioException;

    /**
     * Agrega un tipo de mesa en el sistema
     *
     * @param tipoMesa Tipo de mesa a registrar
     * @throws excepciones.NegocioException
     */
    public void agregarTipoMesa(TipoMesaDTO tipoMesa) throws NegocioException;

    /**
     * Elimina un tipo de mesa en el sistema
     *
     * @param id ID del tipo de mesa a eliminar
     * @throws excepciones.NegocioException
     */
    public void eliminarTipoMesa(Long id) throws NegocioException;
    
    /**
 * Obtiene el precio de un tipo de mesa por su ID.
 * 
 * @param id ID del tipo de mesa.
 * @return Precio del tipo de mesa.
 * @throws NegocioException Si ocurre un error en la operación.
 */
float obtenerPrecioPorTipoMesa(Long id) throws NegocioException;


}
