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
import daos.MesasDAO;
import entidades.Mesa;
import entidades.Restaurante;
import entidades.TipoMesa;
import entidades.UbicacionMesa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.function.Supplier;
import org.junit.After;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class MesaDAOTest {
    private Conexion conexion;
    private MesasDAO mesaDAO;

    @BeforeAll
    public void setUp() {
        conexion = Conexion.getInstance();
        assertNotNull(conexion);
        mesaDAO = new MesasDAO(conexion);
    }



    @Test
    public void testInsertarMesa_Exito() throws DAOException {
        // Crear un restaurante
        Restaurante restaurante = new Restaurante();
        restaurante.setNombre("Restaurante Test");
        restaurante.setDireccion("Dirección Test");
        restaurante.setTelefono("123456789");
        restaurante.setHoraApertura(LocalTime.of(9, 0));
        restaurante.setHoraCierre(LocalTime.of(22, 0));

        // Crear un tipo de mesa
        TipoMesa tipoMesa = new TipoMesa();
        tipoMesa.setNombre("Mesa de 4 personas");
        tipoMesa.setMaximoPersonas(4);
        tipoMesa.setMinimoPersonas(2);
        tipoMesa.setPrecio(200f);

        // Iniciar transacción y persistir el restaurante y tipo de mesa
        EntityManager em = conexion.crearConexion();
        em.getTransaction().begin();
        em.persist(restaurante);
        em.persist(tipoMesa);
        em.getTransaction().commit();

        // Crear la mesa
        Mesa mesa = new Mesa();
        mesa.setTipoMesa(tipoMesa);
        mesa.setRestaurante(restaurante);
        mesa.setUbicacion(UbicacionMesa.TERRAZA);
        mesa.setFechaNuevaDisponibilidad(LocalDateTime.now());

        // Llamar al método de inserción
        mesaDAO.insertarMesa(mesa);

        // Verificar que la mesa se haya insertado correctamente
        em = conexion.crearConexion(); // Volver a crear el EntityManager para la consulta
        Mesa mesaGuardada = em.find(Mesa.class, mesa.getId());
        assertNotNull(mesaGuardada);
        assertEquals(mesa.getTipoMesa().getId(), mesaGuardada.getTipoMesa().getId());
        assertEquals(mesa.getRestaurante().getId(), mesaGuardada.getRestaurante().getId());
        assertEquals(mesa.getUbicacion(), mesaGuardada.getUbicacion());
    }

    
    @Before
    public void tearDown() {
        conexion.crearConexion().close();
    }
}