/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import bo.MesasBO;
import convertidores.MesaConvertidor;
import convertidores.TipoMesaConvertidor;
import daos.MesasDAO;
import conexion.IConexion;
import idaos.IMesasDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author caarl
 */
/**
 * Fábrica para crear instancias de MesasBO con sus dependencias.
 */
public class MesasBOFactory {

    public static MesasBO createMesasBO(IConexion conexion) {
        IMesasDAO mesasDAO = new MesasDAO((EntityManager) conexion);
        MesaConvertidor mesaConvertidor = new MesaConvertidor();
        TipoMesaConvertidor tipoMesaConvertidor = new TipoMesaConvertidor();
        return new MesasBO(mesasDAO, mesaConvertidor, tipoMesaConvertidor);
    }
}