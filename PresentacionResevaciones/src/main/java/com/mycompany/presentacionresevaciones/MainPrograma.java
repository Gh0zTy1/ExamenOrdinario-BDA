/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.presentacionresevaciones;

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

        // Crear y mostrar el frame frmInicio
        java.awt.EventQueue.invokeLater(() -> {
            new frmInicio().setVisible(true);
        });
    }
}
