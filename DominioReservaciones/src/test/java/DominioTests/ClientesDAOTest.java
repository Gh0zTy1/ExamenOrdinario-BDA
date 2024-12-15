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
import conexion.IConexion;
import daos.ClientesDAO;
import entidades.Cliente;
import org.junit.jupiter.api.*;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.TypedQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de prueba para ClientesDAO.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClientesDAOTest {

    private IConexion mockConexion;
    private EntityManager em;
    private ClientesDAO clientesDAO;

    @BeforeEach
    void setUp() {
        // Mock de la conexión y EntityManager
        mockConexion = mock(IConexion.class);
        em = mock(EntityManager.class);

        // Configurar el mock para devolver el EntityManager
        when(mockConexion.crearConexion()).thenReturn(em);

        // Crear instancia del DAO con inyección de dependencia
        clientesDAO = new ClientesDAO(mockConexion);
    }

    @AfterEach
    void tearDown() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    @Test
    void testObtenerClientesTodos() throws DAOException {
        // Mock de TypedQuery
        TypedQuery<Cliente> queryMock = mock(TypedQuery.class);
        when(em.createQuery("SELECT c FROM Cliente c", Cliente.class)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(List.of(
                new Cliente() {{
                    setId(1L);
                    setNombreCompleto("Carlos Perez");
                    setTelefono("1234567890");
                }},
                new Cliente() {{
                    setId(2L);
                    setNombreCompleto("Ana Lopez");
                    setTelefono("0987654321");
                }}
        ));

        List<Cliente> clientes = clientesDAO.obtenerClientesTodos();

        assertNotNull(clientes);
        assertEquals(2, clientes.size());
        assertEquals("Carlos Perez", clientes.get(0).getNombreCompleto());
        assertEquals("Ana Lopez", clientes.get(1).getNombreCompleto());
    }

    @Test
    void testObtenerClientePorTelefono() throws DAOException {
        String telefono = "1234567890";

        // Mock de TypedQuery
        TypedQuery<Cliente> queryMock = mock(TypedQuery.class);
        when(em.createQuery("SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class)).thenReturn(queryMock);
        when(queryMock.setParameter("telefono", telefono)).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(new Cliente() {{
            setId(1L);
            setNombreCompleto("Carlos Perez");
            setTelefono(telefono);
        }});

        Cliente cliente = clientesDAO.obtenerClientePorTelefono(telefono);

        assertNotNull(cliente);
        assertEquals("Carlos Perez", cliente.getNombreCompleto());
        assertEquals(telefono, cliente.getTelefono());
    }

//    @Test
//    void testObtenerClientePorTelefonoNoEncontrado() {
//        String telefonoInexistente = "9999999999";
//
//        // Mock de TypedQuery
//        TypedQuery<Cliente> queryMock = mock(TypedQuery.class);
//        when(em.createQuery("SELECT c FROM Cliente c WHERE c.telefono = :telefono", Cliente.class)).thenReturn(queryMock);
//        when(queryMock.setParameter("telefono", telefonoInexistente)).thenReturn(queryMock);
//        when(queryMock.getSingleResult()).thenThrow(new RuntimeException("No se encontró al cliente"));
//
//        DAOException exception = assertThrows(DAOException.class, () -> {
//            clientesDAO.obtenerClientePorTelefono(telefonoInexistente);
//        });
//
//        assertNotNull(exception.getMessage());
//        assertTrue(exception.getMessage().toLowerCase().contains("no se encontró"));
//    }
}
