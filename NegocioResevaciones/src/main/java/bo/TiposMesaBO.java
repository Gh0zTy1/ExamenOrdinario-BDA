/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import Excepciones.DAOException;
import convertidores.TipoMesaConvertidor;
import dto.TipoMesaDTO;
import entidades.TipoMesa;
import excepciones.NegocioException;
import ibo.ITiposMesaBO;
import idaos.ITiposMesaDAO;

import java.util.List;

/**
 * Implementación de la interfaz ITiposMesaBO para manejar la lógica de negocio
 * relacionada con los tipos de mesa. Ahora utiliza inyección de dependencias.
 * 
 * @author caarl
 */
public class TiposMesaBO implements ITiposMesaBO {

    private final ITiposMesaDAO tiposMesaDAO;
    private final TipoMesaConvertidor tipoMesaConvertidor;

    /**
     * Constructor para inyección de dependencias.
     * 
     * @param tiposMesaDAO DAO para acceso a datos de tipos de mesa.
     * @param tipoMesaConvertidor Convertidor para transformar entre entidad y DTO.
     */
    public TiposMesaBO(ITiposMesaDAO tiposMesaDAO, TipoMesaConvertidor tipoMesaConvertidor) {
        this.tiposMesaDAO = tiposMesaDAO;
        this.tipoMesaConvertidor = tipoMesaConvertidor;
    }

    @Override
    public List<TipoMesaDTO> obtenerTiposMesaTodos() throws NegocioException {
        try {
            List<TipoMesa> tiposMesa = tiposMesaDAO.obtenerTiposMesaTodos();
            return tipoMesaConvertidor.createFromEntities(tiposMesa);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void agregarTipoMesa(TipoMesaDTO tipoMesaDTO) throws NegocioException {
        try {
            TipoMesa tipoMesa = tipoMesaConvertidor.convertFromDto(tipoMesaDTO);
            tiposMesaDAO.agregarTipoMesa(tipoMesa);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void eliminarTipoMesa(Long id) throws NegocioException {
        try {
            tiposMesaDAO.eliminarTipoMesa(id);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    @Override
public float obtenerPrecioPorTipoMesa(Long id) throws NegocioException {
    try {
        // Llamamos al DAO para obtener el precio
        return tiposMesaDAO.obtenerPrecioPorTipoMesa(id);
    } catch (DAOException e) {
        // Encapsulamos cualquier error del DAO en una NegocioException
        throw new NegocioException("Error al obtener el precio del tipo de mesa: " + e.getMessage());
    }
}

}
