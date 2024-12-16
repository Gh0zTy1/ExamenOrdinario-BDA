/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.presentacionresevaciones;

import bo.TiposMesaBO;
import dto.RestauranteDTO;
import dto.TipoMesaDTO;
import excepciones.NegocioException;
import fabricas.fabricaFCD;
import static fabricas.fabricaFCD.fabricaFCDAgregarRestaurante;
import fachadas.AgregarRestauranteFACHADA;
import fachadas.agregarTiposMesaFCD;
import guis.frmInicio;

/**
 *
 * @author caarl
 */
import java.time.LocalTime;

public class MainPrograma {

    public static void main(String[] args) {
        // Establecer el look and feel del sistema (opcional)
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear una instancia de AgregarRestauranteFACHADA usando la fábrica
        AgregarRestauranteFACHADA agregarRestauranteFACHADA = fabricaFCD.fabricaFCDAgregarRestaurante();

        // Crear una instancia de RestauranteDTO
        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.setId(1L);  // Asignar un ID al restaurante
        restaurante.setNombre("Wings Army");
        restaurante.setDireccion("Lazaro Cardenas Blvd. 1600, La Pechuga, 22425");
        restaurante.setTelefono("6441693779");
        restaurante.setHoraApertura(LocalTime.of(9, 0));  // Ejemplo: 9:00 AM
        restaurante.setHoraCierre(LocalTime.of(23, 0));   // Ejemplo: 11:00 PM

        try {
            // Agregar restaurante a la base de datos usando la fachada
            agregarRestauranteFACHADA.agregarRestaurante(restaurante);
            System.out.println("Restaurante agregado exitosamente.");
        } catch (NegocioException e) {
            System.out.println("Error al agregar el restaurante: " + e.getMessage());
        }

        // Crear e insertar los tipos de mesa
        agregarTiposMesaFCD agregarTiposMesaFCD = fabricaFCD.fabricaFCDAgregarTipoMesa();
        
        // Crear los tipos de mesa
        TipoMesaDTO tipoMesa1 = new TipoMesaDTO(1L, "Pequeña", 1, 2, 300f);
        TipoMesaDTO tipoMesa2 = new TipoMesaDTO(2L, "Mediana", 3, 4, 500f);
        TipoMesaDTO tipoMesa3 = new TipoMesaDTO(3L, "Grande", 5, 8, 700f);

        try {
            // Agregar los tipos de mesa a la base de datos usando la fachada
            agregarTiposMesaFCD.agregarTipoMesa(tipoMesa1);
            agregarTiposMesaFCD.agregarTipoMesa(tipoMesa2);
            agregarTiposMesaFCD.agregarTipoMesa(tipoMesa3);
            System.out.println("Tipos de mesa agregados exitosamente.");
        } catch (NegocioException e) {
            System.out.println("Error al agregar los tipos de mesa: " + e.getMessage());
        }

        // Crear y mostrar el frame frmInicio
        java.awt.EventQueue.invokeLater(() -> {
            new frmInicio(restaurante).setVisible(true);
        });
    }
}

