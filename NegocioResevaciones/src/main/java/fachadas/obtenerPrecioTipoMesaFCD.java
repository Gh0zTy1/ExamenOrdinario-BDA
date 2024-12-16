/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import bo.TiposMesaBO;
import excepciones.NegocioException;
import iFachadas.IObtenerPrecioTipoMesaFCD;

/**
 *
 * @author caarl
 */
public class obtenerPrecioTipoMesaFCD implements IObtenerPrecioTipoMesaFCD {

    private final TiposMesaBO tiposMesaBO;

    /**
     * Constructor que inyecta la dependencia del BO de tipos de mesa.
     * 
     * @param tiposMesaBO instancia del BO para manejar la lógica de negocio.
     */
    public obtenerPrecioTipoMesaFCD(TiposMesaBO tiposMesaBO) {
        this.tiposMesaBO = tiposMesaBO;
    }

    @Override
    public float obtenerPrecioPorTipoMesa(Long id) throws NegocioException {
        return tiposMesaBO.obtenerPrecioPorTipoMesa(id);
    }
 
    
}
