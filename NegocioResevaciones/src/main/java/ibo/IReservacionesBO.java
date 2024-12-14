package ibo;

import dto.ReservacionDTO;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Define las operaciones necesarias para interactuar con los objetos de negocio
 *
 * @author caarl
 */
public interface IReservacionesBO {

    /**
     * Obtiene la lista de reservaciones registradas de una mesa a partir de su
     * código.
     *
     * @param idRestaurante ID del restaurante en donde se quiere consultar las
     * reservas
     * @param codigoMesa Código de la mesa a buscar
     * @return Reservaciones de dicha mesa
     * @throws excepciones.NegocioException
     */
    public List<ReservacionDTO> obtenerReservacionesDeMesa(Long idRestaurante, String codigoMesa) throws NegocioException;


    /**
     * Obtiene una lista de todas las reservaciones almacenadas.
     *
     * @param idRestaurante ID del restaurante en cuestión
     * @return Una lista de objetos `ReservacionDTO`.
     * @throws excepciones.NegocioException
     */
    public List<ReservacionDTO> obtenerReservacionesTodos(Long idRestaurante) throws NegocioException;

    /**
     * Obtiene una lista de reservaciones dentro de un período de tiempo
     * específico.
     *
     * @param idRestaurante ID del restaurante en cuestión
     * @param fechaInicio La fecha y hora de inicio del período.
     * @param fechaFin La fecha y hora de fin del período.
     * @return Una lista de objetos `ReservacionDTO` que se encuentran en el
     * rango especificado.
     * @throws excepciones.NegocioException
     */
    public List<ReservacionDTO> obtenerReservacionesPorPeriodo(Long idRestaurante, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException;

    /**
     * Obtiene una lista de reservaciones asociadas a un cliente específico
     * basado en su número de teléfono.
     *
     * @param idRestaurante ID del restaurante en cuestión
     * @param telefono El número de teléfono del cliente.
     * @return Una lista de objetos `ReservacionDTO` pertenecientes al cliente.
     * @throws excepciones.NegocioException
     */
    public List<ReservacionDTO> obtenerReservacionesCliente(Long idRestaurante, String telefono) throws NegocioException;

    /**
     * Obtiene una reservación específica basada en su identificador único.
     *
     * @param id El identificador de la reservación.
     * @return Un objeto `ReservacionDTO` correspondiente al identificador
     * proporcionado.
     * @throws excepciones.NegocioException
     */
    public ReservacionDTO obtenerReservacionPorID(Long id) throws NegocioException;

    /**
     * Agrega una nueva reservación al sistema.
     *
     * @param reservacion El objeto `ReservacionDTO` que se desea agregar.
     * @throws excepciones.NegocioException
     */
    public void agregarReservacion(ReservacionDTO reservacion) throws NegocioException;

    /**
     * Actualiza los detalles de una reservación existente en el sistema.
     *
     * @param reservacion El objeto `ReservacionDTO` que contiene los datos
     * actualizados.
     * @throws excepciones.NegocioException
     */
    public void actualizarReservacion(ReservacionDTO reservacion) throws NegocioException;

    /**
     * Elimina una reservación del sistema basada en su identificador único.
     *
     * @param id El identificador de la reservación que se desea eliminar.
     * @throws excepciones.NegocioException
     */
    public void eliminarReservacion(Long id) throws NegocioException;

}
