/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import dto.MesaDTO;
import excepciones.NegocioException;
import iFachadas.ImodificarMesasFCD;
import ibo.IMesasBO;

/**
 *
 * @author caarl
 */
public class modificarMesasFCD implements ImodificarMesasFCD {
    private final IMesasBO mesasBO;

    public modificarMesasFCD(IMesasBO mesasBO) {
        this.mesasBO = mesasBO;
    }

    @Override
    public void modificarMesa(MesaDTO mesaDTO) throws NegocioException {
        mesasBO.modificarMesa(mesaDTO);
    }
}
