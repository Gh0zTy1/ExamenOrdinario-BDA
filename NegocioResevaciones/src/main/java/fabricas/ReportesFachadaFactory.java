/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fabricas;

import conexion.Conexion;
import fachadas.ReportesFachada;

/**
 *
 * @author caarl
 */
public class ReportesFachadaFactory {

    private static ReportesFachada fachada;

    public static ReportesFachada getFachada() {
        if (fachada == null) {
            // Crear la fachada con la conexión necesaria
            Conexion conexion = new Conexion();  // Suponiendo que tienes esta clase
            fachada = new ReportesFachada(conexion);
        }
        return fachada;
    }
}
