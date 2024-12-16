/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ibo;

import dto.MesaDTO;
import dto.TipoMesaDTO;
import dto.UbicacionMesaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Define las operaciones necesarias para interactuar con las mesas en el
 * sistema
 *
 * @author caarl
 */
public interface IMesasBO {
 
    void agregarMesa(MesaDTO mesaDTO) throws NegocioException;
    void modificarMesa(MesaDTO mesaDTO) throws NegocioException;

    /**
     * Devuelve todas las mesas registradas en el sistema.
     *
     * @param idRestaurante ID del restaurante
     * @return lista de mesas registradas
     * @throws excepciones.NegocioException
     */
    public List<MesaDTO> obtenerMesasTodas(Long idRestaurante) throws NegocioException;

    /**
     * Devuelve una lista con las mesas disponibles para su reservación.
     *
     * @param idRestaurante ID del restaurante
     * @return mesas disponibles
     * @throws excepciones.NegocioException
     */
    public List<MesaDTO> obtenerMesasDisponibles(Long idRestaurante) throws NegocioException;

    /**
     * Devuelve todas las mesas del tipo especificado.
     *
     * @param idRestaurante ID del restaurante en cuestión
     * @param tipo tipo de mesa
     * @return lista de mesas del tipo especificado
     * @throws excepciones.NegocioException
     */
    public List<MesaDTO> obtenerMesasPorTipo(Long idRestaurante, TipoMesaDTO tipo) throws NegocioException;

    
    /**
     * Elimina una mesa en el sistema por su código especificado.
     *
     * @param idRestaurante ID del restaurante donde se eliminará la mesa
     * @param codigo código de la mesa a eliminar
     * @throws excepciones.NegocioException
     */
    public void eliminarMesa(Long idRestaurante, String codigo) throws NegocioException;

}
