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
import entidades.Mesa;
import entidades.TipoMesa;
import excepciones.NegocioException;
import ibo.IMesasBO;
import idaos.IMesasDAO;

import java.util.List;

/**
 * Implementación de la interfaz {@link IMesasBO} para manejar la lógica de negocio
 * relacionada con las mesas. Utiliza inyección de dependencias para desacoplar la lógica 
 * de negocio del acceso a datos y de las conversiones entre entidades y DTOs.
 * 
 * @author caarl
 */
public class MesasBO implements IMesasBO {

    private final IMesasDAO mesasDAO;
    private final MesaConvertidor mesaConvertidor;
    private final TipoMesaConvertidor tipoMesaConvertidor;

    /**
     * Constructor para inicializar las dependencias necesarias.
     * 
     * @param mesasDAO DAO para el acceso a datos de mesas.
     * @param mesaConvertidor Convertidor para transformar entre {@link Mesa} y {@link MesaDTO}.
     * @param tipoMesaConvertidor Convertidor para transformar entre {@link TipoMesa} y {@link TipoMesaDTO}.
     */
    public MesasBO(IMesasDAO mesasDAO, MesaConvertidor mesaConvertidor, TipoMesaConvertidor tipoMesaConvertidor) {
        this.mesasDAO = mesasDAO;
        this.mesaConvertidor = mesaConvertidor;
        this.tipoMesaConvertidor = tipoMesaConvertidor;
    }

    /**
     * Obtiene todas las mesas de un restaurante específico.
     * 
     * @param idRestaurante El identificador del restaurante.
     * @return Una lista de objetos {@link MesaDTO} que representan todas las mesas del restaurante.
     * @throws NegocioException Si ocurre un error al obtener los datos.
     */
    @Override
    public List<MesaDTO> obtenerMesasTodas(Long idRestaurante) throws NegocioException {
        try {
            List<Mesa> mesas = mesasDAO.obtenerMesasTodas(idRestaurante);
            return mesaConvertidor.createFromEntities(mesas);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene las mesas disponibles de un restaurante específico.
     * 
     * @param idRestaurante El identificador del restaurante.
     * @return Una lista de objetos {@link MesaDTO} que representan las mesas disponibles.
     * @throws NegocioException Si ocurre un error al obtener los datos.
     */
    @Override
    public List<MesaDTO> obtenerMesasDisponibles(Long idRestaurante) throws NegocioException {
        try {
            return mesaConvertidor.createFromEntities(mesasDAO.obtenerMesasDisponibles(idRestaurante));
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Obtiene las mesas de un restaurante que pertenecen a un tipo específico.
     * 
     * @param idRestaurante El identificador del restaurante.
     * @param tipo El tipo de mesa representado como un {@link TipoMesaDTO}.
     * @return Una lista de objetos {@link MesaDTO} que representan las mesas del tipo especificado.
     * @throws NegocioException Si ocurre un error al obtener los datos.
     */
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

    /**
     * Elimina una mesa de un restaurante específico.
     * 
     * @param idRestaurante El identificador del restaurante.
     * @param codigo El código único de la mesa a eliminar.
     * @throws NegocioException Si ocurre un error durante la operación.
     */
    @Override
    public void eliminarMesa(Long idRestaurante, String codigo) throws NegocioException {
        try {
            mesasDAO.eliminarMesa(idRestaurante, codigo);
        } catch (DAOException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    /**
     * Agrega una nueva mesa al sistema.
     * 
     * @param mesaDTO La información de la mesa representada como un {@link MesaDTO}.
     * @throws NegocioException Si ocurre un error al agregar la mesa.
     */
    @Override
    public void agregarMesa(MesaDTO mesaDTO) throws NegocioException {
        try {
            Mesa mesa = mesaConvertidor.convertFromDto(mesaDTO);
            mesasDAO.insertarMesa(mesa);
        } catch (DAOException e) {
            throw new NegocioException("Error al agregar la mesa: " + e.getMessage());
        }
    }

    /**
     * Modifica una mesa existente en el sistema.
     * 
     * @param mesaDTO La información de la mesa representada como un {@link MesaDTO}.
     * @throws NegocioException Si la operación no está soportada actualmente.
     */
    @Override
    public void modificarMesa(MesaDTO mesaDTO) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // To be implemented
    }
}
