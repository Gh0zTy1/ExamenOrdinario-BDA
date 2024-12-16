/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import Excepciones.DAOException;
import convertidores.MesaConvertidor;
import convertidores.TipoMesaConvertidor;
import dto.MesaDTO;
import dto.TipoMesaDTO;
import dto.UbicacionMesaDTO;
import entidades.Mesa;
import entidades.TipoMesa;
import entidades.UbicacionMesa;
import excepciones.NegocioException;
import ibo.IMesasBO;
import idaos.IMesasDAO;

import java.util.List;

/**
 * Implementación de la interfaz IMesasBO para manejar la lógica de negocio
 * relacionada con las mesas. Ahora utiliza inyección de dependencias.
 * 
 * @author caarl
 */
public class MesasBO implements IMesasBO {

    private final IMesasDAO mesasDAO;
    private final MesaConvertidor mesaConvertidor;
    private final TipoMesaConvertidor tipoMesaConvertidor;

    
    
    
    /**
     * Constructor para inyección de dependencias.
     * 
     * @param mesasDAO DAO para el acceso a datos de mesas.
     * @param mesaConvertidor Convertidor de Mesa a DTO y viceversa.
     * @param tipoMesaConvertidor Convertidor de TipoMesa a DTO y viceversa.
     */
    public MesasBO(IMesasDAO mesasDAO, MesaConvertidor mesaConvertidor, TipoMesaConvertidor tipoMesaConvertidor) {
        this.mesasDAO = mesasDAO;
        this.mesaConvertidor = mesaConvertidor;
        this.tipoMesaConvertidor = tipoMesaConvertidor;
    }

    @Override
    public List<MesaDTO> obtenerMesasTodas(Long idRestaurante) throws NegocioException {
        try {
            List<Mesa> mesas = mesasDAO.obtenerMesasTodas(idRestaurante);
            return mesaConvertidor.createFromEntities(mesas);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<MesaDTO> obtenerMesasDisponibles(Long idRestaurante) throws NegocioException {
        try {
            return mesaConvertidor.createFromEntities(mesasDAO.obtenerMesasDisponibles(idRestaurante));
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public List<MesaDTO> obtenerMesasPorTipo(Long idRestaurante, TipoMesaDTO tipo) throws NegocioException {
        try {
            TipoMesa tipoMesa = tipoMesaConvertidor.convertFromDto(tipo);
            List<Mesa> mesas = mesasDAO.obtenerMesasPorTipo(idRestaurante, tipoMesa);
            return mesaConvertidor.createFromEntities(mesas);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }



    @Override
    public void eliminarMesa(Long idRestaurante, String codigo) throws NegocioException {
        try {
            mesasDAO.eliminarMesa(idRestaurante, codigo);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }
    
    @Override
public void agregarMesa(MesaDTO mesaDTO) throws NegocioException {
    try {
        Mesa mesa = mesaConvertidor.convertFromDto(mesaDTO);
        mesasDAO.insertarMesa(mesa);
    } catch (DAOException e) {
        throw new NegocioException("Error al agregar la mesa: " + e.getMessage());
    }
}



    @Override
    public void modificarMesa(MesaDTO mesaDTO) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
