/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package iFachadas;

/**
 *
 * @author caarl
 */
import dto.MesaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Define la interfaz para la fachada encargada de gestionar las mesas.
 */
public interface ICargarMesasFCD {
    /**
     * Obtiene todas las mesas de un restaurante.
     *
     * @param idRestaurante ID del restaurante.
     * @return Lista de mesas.
     * @throws NegocioException en caso de un error en la lógica de negocio.
     */
    List<MesaDTO> cargarMesas(Long idRestaurante) throws NegocioException;
}