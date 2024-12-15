/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import excepciones.NegocioException;
import iFachadas.IeliminarMesasFCD;
import ibo.IMesasBO;

/**
 *
 * @author caarl
 */
public class eliminarMesasFCD implements IeliminarMesasFCD {
    private final IMesasBO mesasBO;

    public eliminarMesasFCD(IMesasBO mesasBO) {
        this.mesasBO = mesasBO;
    }

    @Override
    public void eliminarMesa(Long idRestaurante, String codigo) throws NegocioException {
        mesasBO.eliminarMesa(idRestaurante, codigo);
    }
}
