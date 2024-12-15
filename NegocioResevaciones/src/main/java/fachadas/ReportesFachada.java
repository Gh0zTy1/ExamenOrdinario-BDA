/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import bo.ClientesBO;
import bo.MesasBO;
import bo.ReservacionesBO;
import conexion.Conexion;
import convertidores.ClienteConvertidor;
import convertidores.MesaConvertidor;
import convertidores.ReservacionConvertidor;
import convertidores.TipoMesaConvertidor;
import daos.ClientesDAO;
import daos.MesasDAO;
import daos.ReservacionesDAO;
import ibo.IReservacionesBO;
import idaos.IClientesDAO;
import idaos.IMesasDAO;
import idaos.IReservacionesDAO;

/**
 *
 * @author caarl
 */
public class ReportesFachada {

    private final IReservacionesBO reservacionesBO;
    private final MesasBO mesasBO;
    private final ClientesBO clientesBO;

    public ReportesFachada(Conexion conexion) {
        // Crea las dependencias necesarias
        IMesasDAO mesasDAO = new MesasDAO(conexion);
        IClientesDAO clientesDAO = new ClientesDAO(conexion);
        IReservacionesDAO reservacionesDAO = new ReservacionesDAO(conexion, mesasDAO);

        // Crea los convertidores
        ReservacionConvertidor reservConvertidor = new ReservacionConvertidor();
        MesaConvertidor mesaConvertidor = new MesaConvertidor();
        TipoMesaConvertidor tipoMesaConvertidor = new TipoMesaConvertidor();
        ClienteConvertidor clienteConvertidor = new ClienteConvertidor();

        // Crea las instancias de los objetos de negocio
        this.reservacionesBO = new ReservacionesBO(reservacionesDAO, reservConvertidor);
        this.mesasBO = new MesasBO(mesasDAO, mesaConvertidor, tipoMesaConvertidor);
        this.clientesBO = new ClientesBO(clientesDAO, clienteConvertidor);
    }

    public IReservacionesBO getReservacionesBO() {
        return reservacionesBO;
    }

    public MesasBO getMesasBO() {
        return mesasBO;
    }

    public ClientesBO getClientesBO() {
        return clientesBO;
    }
}
