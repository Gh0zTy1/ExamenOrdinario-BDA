/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import dto.TipoMesaDTO;
import dto.UbicacionMesaDTO;
import excepciones.NegocioException;
import iFachadas.IagregarMesasFCD;
import ibo.IMesasBO;

/**
 *
 * @author caarl
 */
public class agregarMesasFCD implements IagregarMesasFCD {
    private final IMesasBO mesasBO;

    public agregarMesasFCD(IMesasBO mesasBO) {
        this.mesasBO = mesasBO;
    }

    @Override
    public void agregarMesas(Long idRestaurante, TipoMesaDTO tipoMesa, UbicacionMesaDTO ubicacion, int cantidad) throws NegocioException {
        mesasBO.insertarMesas(idRestaurante, tipoMesa, ubicacion, cantidad);
    }
}
