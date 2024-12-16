/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

/**
 *
 * @author caarl
 */


import excepciones.NegocioException;

import java.util.List;

import dto.TipoMesaDTO;
import ibo.ITiposMesaBO;

/**
 * Fachada para operaciones relacionadas con los tipos de mesa.
 */
public class TiposMesaFachada {

    private final ITiposMesaBO tiposMesaBO;

    /**
     * Constructor para inyección de dependencias.
     *
     * @param tiposMesaBO Instancia de ITiposMesaBO para manejar la lógica de negocio.
     */
    public TiposMesaFachada(ITiposMesaBO tiposMesaBO) {
        this.tiposMesaBO = tiposMesaBO;
    }

    /**
     * Obtiene todos los tipos de mesa disponibles en el sistema.
     *
     * @return Lista de TipoMesaDTO.
     * @throws NegocioException En caso de error en la capa de negocio.
     */
    public List<TipoMesaDTO> obtenerTiposMesaTodos() throws NegocioException {
        return tiposMesaBO.obtenerTiposMesaTodos();
    }
}
