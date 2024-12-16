/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import bo.TiposMesaBO;
import dto.TipoMesaDTO;
import excepciones.NegocioException;
import iFachadas.ITiposMesaFachada;

/**
 *
 * @author caarl
 */
public class agregarTiposMesaFCD implements ITiposMesaFachada {
  private final TiposMesaBO tiposMesaBO;

    public agregarTiposMesaFCD(TiposMesaBO tiposMesaBO) {
        this.tiposMesaBO = tiposMesaBO;
    }

    @Override
    public void agregarTipoMesa(TipoMesaDTO tipoMesaDTO) throws NegocioException {
        tiposMesaBO.agregarTipoMesa(tipoMesaDTO);
    }
}