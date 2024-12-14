/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import bo.ClientesBO;
import conexion.IConexion;
import convertidores.ClienteConvertidor;
import daos.ClientesDAO;
import idaos.IClientesDAO;

/**
 *
 * @author caarl
 */
/**
 * Fábrica para crear instancias de ClientesBO con sus dependencias.
 */
public class ClientesBOFactory {

    public static ClientesBO createClientesBO(IConexion conexion) {
        IClientesDAO clientesDAO = new ClientesDAO(conexion);
        ClienteConvertidor clienteConvertidor = new ClienteConvertidor();
        return new ClientesBO(clientesDAO, clienteConvertidor);
    }
}