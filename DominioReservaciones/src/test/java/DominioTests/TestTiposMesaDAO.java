/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DominioTests;

/**
 *
 * @author caarl
 */
import Excepciones.DAOException;
import conexion.Conexion;
import daos.TiposMesaDAO;
import entidades.TipoMesa;
import java.util.List;

public class TestTiposMesaDAO {

    public static void main(String[] args) {
        try {
            // Crear una instancia del DAO correspondiente
            TiposMesaDAO tiposMesaDAO = new TiposMesaDAO(Conexion.getInstance());

            // Llamar al método obtenerTiposMesaTodos para obtener todos los tipos de mesa
            List<TipoMesa> tiposMesa = tiposMesaDAO.obtenerTiposMesaTodos();

            // Mostrar los resultados
            if (tiposMesa != null && !tiposMesa.isEmpty()) {
                System.out.println("Tipos de mesa encontrados:");
                for (TipoMesa tipoMesa : tiposMesa) {
                    System.out.println("ID: " + tipoMesa.getId() + ", Nombre: " + tipoMesa.getNombre());
                }
            } else {
                System.out.println("No se encontraron tipos de mesa.");
            }

        } catch (DAOException e) {
            // Manejo de excepciones
            System.err.println("Error al obtener los tipos de mesa: " + e.getMessage());
        }
    }
}
