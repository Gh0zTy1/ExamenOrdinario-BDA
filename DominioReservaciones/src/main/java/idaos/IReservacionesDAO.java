
package idaos;

import Excepciones.DAOException;
import entidades.Reservacion;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Define las operaciones para el DAO de reservaciones
 * @author caarl
 */
public interface IReservacionesDAO {
       
    /**
     * Obtiene la lista de reservaciones registradas de una mesa a partir de su codigo
     * @param idRestaurante ID del restaurante en donde se quiere consultar las reservas
     * @param codigoMesa Codigo de la mesa a buscar
     * @return Reservaciones de dicha mesa
     * @throws DAOException Si ocurre un error en la consulta
     */
    public List<Reservacion> obtenerReservacionesDeMesa(Long idRestaurante, String codigoMesa) throws DAOException;
    
    
    /**
     * Obtiene una lista de todas las reservaciones almacenadas.
     *
     * @param idRestaurante ID del restaurante en cuestion
     * @return Una lista de objetos `Reservacion`.
     * @throws DAOException Si ocurre un error durante la obtención de los
     * datos.
     */
    public List<Reservacion> obtenerReservacionesTodos(Long idRestaurante) throws DAOException;

    /**
     * Obtiene una lista de reservaciones dentro de un período de tiempo
     * específico.
     *
     * @param idRestaurante ID del restaurante en cuestion
     * @param fechaInicio La fecha y hora de inicio del período.
     * @param fechaFin La fecha y hora de fin del período.
     * @return Una lista de objetos `Reservacion` que se encuentran en el rango
     * especificado.
     * @throws DAOException Si ocurre un error durante la obtención de los
     * datos.
     */
    public List<Reservacion> obtenerReservacionesPorPeriodo(Long idRestaurante, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws DAOException;

    /**
     * Obtiene una lista de reservaciones asociadas a un cliente específico
     * basado en su número de teléfono.
     *
     * @param idRestaurante ID del restaurante en cuestion
     * @param telefono El número de teléfono del cliente.
     * @return Una lista de objetos `Reservacion` pertenecientes al cliente.
     * @throws DAOException Si ocurre un error durante la obtención de los
     * datos.
     */
    public List<Reservacion> obtenerReservacionesCliente(Long idRestaurante, String telefono) throws DAOException;

    /**
     * Obtiene una reservación específica basada en su identificador único.
     *
     * @param id El identificador de la reservación.
     * @return Un objeto `Reservacion` correspondiente al identificador
     * proporcionado.
     * @throws DAOException Si ocurre un error durante la obtención de los
     * datos.
     */
    public Reservacion obtenerReservacionPorID(Long id) throws DAOException;

    /**
     * Agrega una nueva reservación al sistema.
     *
     * @param reservacion El objeto `Reservacion` que se desea agregar.
     * @throws DAOException Si ocurre un error durante la inserción de los
     * datos.
     */
    public void agregarReservacion(Reservacion reservacion) throws DAOException;

    /**
     * Actualiza los detalles de una reservación existente en el sistema.
     *
     * @param reservacion El objeto `Reservacion` que contiene los datos
     * actualizados.
     * @throws DAOException Si ocurre un error durante la actualización de los
     * datos.
     */
    public void actualizarReservacion(Reservacion reservacion) throws DAOException;

    /**
     * Elimina una reservación del sistema basada en su identificador único.
     *
     * @param id El identificador de la reservación que se desea eliminar.
     * @throws DAOException Si ocurre un error durante la eliminación de los
     * datos.
     */
    public void eliminarReservacion(Long id) throws DAOException;
}
