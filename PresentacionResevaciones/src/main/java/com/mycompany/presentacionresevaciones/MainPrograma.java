/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.presentacionresevaciones;

import dto.RestauranteDTO;
import guis.frmInicio;

/**
 *
 * @author caarl
 */
public class MainPrograma {

    public static void main(String[] args) {
     // Establecer el look and feel del sistema (opcional)
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
 // Crear una instancia de RestauranteDTO
        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.setId(1L); // Asignar un ID o los valores necesarios
        // Crear y mostrar el frame frmInicio
        java.awt.EventQueue.invokeLater(() -> {
            new frmInicio(restaurante).setVisible(true);
        });
    }
}
