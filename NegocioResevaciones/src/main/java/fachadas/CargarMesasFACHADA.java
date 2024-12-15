/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

/**
 *
 * @author caarl
 */
import bo.MesasBO;
import dto.MesaDTO;
import excepciones.NegocioException;
import iFachadas.ICargarMesasFCD;
import java.util.List;

/**
 * Fachada para la carga de mesas.
 */
public class CargarMesasFACHADA implements ICargarMesasFCD {
    private final MesasBO mesasBO;

    /**
     * Constructor que inyecta la dependencia del BO de mesas.
     *
     * @param mesasBO instancia de MesasBO.
     */
    public CargarMesasFACHADA(MesasBO mesasBO) {
        this.mesasBO = mesasBO;
    }

    @Override
    public List<MesaDTO> cargarMesas(Long idRestaurante) throws NegocioException {
        return mesasBO.obtenerMesasTodas(idRestaurante);
    }
}