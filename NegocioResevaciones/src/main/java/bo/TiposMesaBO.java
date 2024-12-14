/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package bo;


import Excepciones.DAOException;
import convertidores.TipoMesaConvertidor;
import daos.TiposMesaDAO;
import dto.TipoMesaDTO;
import entidades.TipoMesa;
import excepciones.NegocioException;
import ibo.ITiposMesaBO;
import idaos.ITiposMesaDAO;


import java.util.List;

/**
 * Implementación de la interfaz ITiposMesaBO para manejar la lógica de negocio
 * relacionada con los tipos de mesa.
 * Utiliza el patrón Singleton para asegurar que solo exista una instancia de esta clase.
 * 
 * @author caarl
 */
public class TiposMesaBO implements ITiposMesaBO {

    // Instancia única de la clase
    private static TiposMesaBO instance;
    
    // DAO para acceder a los datos de tipos de mesa
    private final ITiposMesaDAO tiposMesaDAO;

    // Convertidor para transformar entidades de TipoMesa a DTOs y viceversa
    private final TipoMesaConvertidor tipoMesaConvertidor;

    /**
     * Constructor privado para implementar el patrón Singleton.
     * Inicializa el DAO y el convertidor.
     */
    private TiposMesaBO() {
        this.tiposMesaDAO = TiposMesaDAO.getInstance(); // Obtiene la instancia del DAO
        this.tipoMesaConvertidor = new TipoMesaConvertidor(); // Crea un nuevo convertidor
    }
    
    /**
     * Método para obtener la instancia única de TiposMesaBO.
     * 
     * @return instancia única de TiposMesaBO
     */
    public static TiposMesaBO getInstance() {
        if (instance == null) {
            instance = new TiposMesaBO(); // Crea una nueva instancia si no existe
        }
        
        return instance; // Retorna la instancia existente
    }

    @Override
    public List<TipoMesaDTO> obtenerTiposMesaTodos() throws NegocioException {
        try {
            // Obtiene la lista de todos los tipos de mesa a través del DAO
            List<TipoMesa> tiposMesa = tiposMesaDAO.obtenerTiposMesaTodos();
            // Convierte la lista de entidades a DTOs antes de devolverla
            return tipoMesaConvertidor.createFromEntities(tiposMesa);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void agregarTipoMesa(TipoMesaDTO tipoMesaDTO) throws NegocioException {
        try {
            // Convierte el DTO a la entidad correspondiente
            TipoMesa tipoMesa = tipoMesaConvertidor.convertFromDto(tipoMesaDTO);
            // Agrega el nuevo tipo de mesa a través del DAO
            tiposMesaDAO.agregarTipoMesa(tipoMesa);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public void eliminarTipoMesa(Long id) throws NegocioException {
        try {
            // Elimina el tipo de mesa especificado por su ID a través del DAO
            tiposMesaDAO.eliminarTipoMesa(id);
        } catch (DAOException e) {
            // Captura la excepción del DAO y lanza una excepción de servicio
            throw new NegocioException(e.getMessage());
        }
    }
}
