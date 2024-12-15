/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.presentacionresevaciones;

import bo.ClientesBO;
import bo.MesasBO;
import bo.ReservacionesBO;
import conexion.Conexion;
import convertidores.ReservacionConvertidor;
import daos.MesasDAO;
import daos.ReservacionesDAO;
import dto.RestauranteDTO;
import fabricas.ReportesFachadaFactory;
import fachadas.ReportesFachada;
import guis.frmHistorialCliente;
import guis.frmInicio;
import ibo.IReservacionesBO;
import idaos.IReservacionesDAO;

/**
 *
 * @author caarl
 */
public class Main {

    public static void main(String[] args) {
        // Configuración de la conexión (suponiendo que tienes esta clase Conexion)
        Conexion conexion = new Conexion();
        
        // Crear una instancia de RestauranteDTO
        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.setId(1L); // Asignar un ID o los valores necesarios

        // Crear la fachada a través de la fábrica
        ReportesFachada fachada = ReportesFachadaFactory.getFachada();

        // Crear el frame de reportes, pasando la fachada a través de sus dependencias
        frmHistorialCliente frame = new frmHistorialCliente(restaurante);

        // Mostrar el frame
        frame.setVisible(true);
    }
}
