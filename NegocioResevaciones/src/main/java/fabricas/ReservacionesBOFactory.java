/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import bo.ReservacionesBO;
import convertidores.ReservacionConvertidor;
import daos.ReservacionesDAO;
import conexion.IConexion;
import daos.MesasDAO;
import idaos.IMesasDAO;
import javax.persistence.EntityManager;
/**
 *
 * @author caarl
 */
public class ReservacionesBOFactory {

    public static ReservacionesBO createReservacionesBO(IConexion conexion) {
        EntityManager entityManager = conexion.crearConexion();
        IMesasDAO mesasDAO = new MesasDAO((EntityManager) conexion);
        ReservacionesDAO reservacionesDAO = new ReservacionesDAO(entityManager, mesasDAO);
        ReservacionConvertidor reservacionConvertidor = new ReservacionConvertidor();
        return new ReservacionesBO(reservacionesDAO, reservacionConvertidor);
    }
}