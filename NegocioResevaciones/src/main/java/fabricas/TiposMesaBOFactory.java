/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import bo.TiposMesaBO;
import conexion.IConexion;
import convertidores.TipoMesaConvertidor;
import daos.TiposMesaDAO;
import idaos.ITiposMesaDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author caarl
 */
public class TiposMesaBOFactory {

    public static TiposMesaBO createTiposMesaBO(IConexion conexion) {
        ITiposMesaDAO tiposMesaDAO = new TiposMesaDAO((EntityManager) conexion);
        TipoMesaConvertidor tipoMesaConvertidor = new TipoMesaConvertidor();
        return new TiposMesaBO(tiposMesaDAO, tipoMesaConvertidor);
    }
}