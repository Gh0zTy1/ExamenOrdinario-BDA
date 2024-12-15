/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DominioTests;

/**
 *
 * @author caarl
 */
import daos.ReservacionesDAO;
import daos.MesasDAO;
import entidades.*;
import Excepciones.DAOException;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de prueba para ReservacionesDAO.
 */
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//public class ReservacionesDAOTest {
//
//    private EntityManager em;
//    private MesasDAO mockMesasDAO;
//    private ReservacionesDAO reservacionesDAO;
//
////    @BeforeEach
////    void setUp() {
////        em = mock(EntityManager.class);
////        mockMesasDAO = mock(MesasDAO.class);
////        reservacionesDAO = new ReservacionesDAO(em, mockMesasDAO);
////    }
//
//    @AfterEach
//    void tearDown() {
//        if (em != null && em.isOpen()) {
//            em.close();
//        }
//    }
//
//    @Test
//void testAgregarReservacion() {
//    Reservacion reservacion = new Reservacion();
//    Cliente cliente = new Cliente();
//    cliente.setId(1L);
//    cliente.setNombreCompleto("Carlos Perez");
//    reservacion.setCliente(cliente);
//
//    // Configurar TipoMesa
//    TipoMesa tipoMesa = new TipoMesa();
//    tipoMesa.setId(1L);
//    tipoMesa.setNombre("Mesa Grande");
//    tipoMesa.setMaximoPersonas(6);
//    tipoMesa.setMinimoPersonas(2);
//    tipoMesa.setPrecio(500.0f);
//
//    // Configurar Mesa
//    Mesa mesa = new Mesa();
//    mesa.setId(1L);
//    mesa.setCodigo("MESA-001");
//    mesa.setFechaNuevaDisponibilidad(LocalDateTime.now().minusHours(2));
//    mesa.setTipoMesa(tipoMesa); // Asignar el tipo de mesa
//    Restaurante restaurante = new Restaurante();
//    restaurante.setId(1L);
//    restaurante.setHoraCierre(LocalDateTime.now().toLocalTime().plusHours(4));
//    mesa.setRestaurante(restaurante);
//
//    reservacion.setMesa(mesa);
//    reservacion.setFechaHora(LocalDateTime.now().plusHours(1));
//    reservacion.setNumeroPersonas(4);
//
//    EntityTransaction transaction = mock(EntityTransaction.class);
//    when(em.getTransaction()).thenReturn(transaction);
//
//    // Mock de las consultas JPQL
//    // 1. Cliente tiene reservación activa en el restaurante
//    TypedQuery<Long> queryClienteRestaurante = mock(TypedQuery.class);
//    when(em.createQuery(
//            "SELECT COUNT(r) FROM Reservacion r WHERE r.cliente.id = :idCliente AND r.mesa.restaurante.id = :idRestaurante AND r.estado LIKE 'PENDIENTE'",
//            Long.class)).thenReturn(queryClienteRestaurante);
//    when(queryClienteRestaurante.setParameter("idCliente", 1L)).thenReturn(queryClienteRestaurante);
//    when(queryClienteRestaurante.setParameter("idRestaurante", 1L)).thenReturn(queryClienteRestaurante);
//    when(queryClienteRestaurante.getSingleResult()).thenReturn(0L);
//
//    // 2. Cliente tiene reservación activa en otro restaurante
//    TypedQuery<Long> queryClienteOtroRestaurante = mock(TypedQuery.class);
//    when(em.createQuery(
//            "SELECT COUNT(r) FROM Reservacion r WHERE r.cliente.id = :idCliente AND r.estado LIKE 'PENDIENTE'",
//            Long.class)).thenReturn(queryClienteOtroRestaurante);
//    when(queryClienteOtroRestaurante.setParameter("idCliente", 1L)).thenReturn(queryClienteOtroRestaurante);
//    when(queryClienteOtroRestaurante.getSingleResult()).thenReturn(0L);
//
//    // 3. Mesa está ocupada por otra reservación pendiente
//    TypedQuery<Long> queryMesaOcupada = mock(TypedQuery.class);
//    when(em.createQuery(
//            "SELECT COUNT(r) FROM Reservacion r WHERE r.mesa.codigo = :codigoMesa AND r.estado LIKE 'PENDIENTE' AND r.mesa.restaurante.id = :idRestaurante",
//            Long.class)).thenReturn(queryMesaOcupada);
//    when(queryMesaOcupada.setParameter("codigoMesa", "MESA-001")).thenReturn(queryMesaOcupada);
//    when(queryMesaOcupada.setParameter("idRestaurante", 1L)).thenReturn(queryMesaOcupada);
//    when(queryMesaOcupada.getSingleResult()).thenReturn(0L);
//
//    // Ejecutar el método y verificar resultados
//    assertDoesNotThrow(() -> {
//        reservacionesDAO.agregarReservacion(reservacion);
//    });
//
//    verify(em).persist(reservacion);
//    verify(transaction).commit();
//}
//
//
//    @Test
//    void testObtenerReservacionesTodos() throws DAOException {
//        Long idRestaurante = 1L;
//
//        // Mock de TypedQuery
//        TypedQuery<Reservacion> queryMock = mock(TypedQuery.class);
//        when(em.createQuery("SELECT r FROM Reservacion r WHERE r.mesa.restaurante.id = :idRestaurante", Reservacion.class))
//                .thenReturn(queryMock);
//        when(queryMock.setParameter("idRestaurante", idRestaurante)).thenReturn(queryMock);
//        when(queryMock.getResultList()).thenReturn(List.of(
//                new Reservacion() {{
//                    setId(1L);
//                    setFechaHora(LocalDateTime.now());
//                }},
//                new Reservacion() {{
//                    setId(2L);
//                    setFechaHora(LocalDateTime.now().plusDays(1));
//                }}
//        ));
//
//        List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesTodos(idRestaurante);
//
//        assertNotNull(reservaciones);
//        assertEquals(2, reservaciones.size());
//        assertEquals(1L, reservaciones.get(0).getId());
//        assertEquals(2L, reservaciones.get(1).getId());
//    }
//
//    @Test
//    void testObtenerReservacionesCliente() throws DAOException {
//        Long idRestaurante = 1L;
//        String telefono = "1234567890";
//
//        // Mock de TypedQuery
//        TypedQuery<Reservacion> queryMock = mock(TypedQuery.class);
//        when(em.createQuery("SELECT r FROM Reservacion r WHERE r.mesa.restaurante.id = :idRestaurante AND r.cliente.telefono = :telefono", Reservacion.class))
//                .thenReturn(queryMock);
//        when(queryMock.setParameter("idRestaurante", idRestaurante)).thenReturn(queryMock);
//        when(queryMock.setParameter("telefono", telefono)).thenReturn(queryMock);
//        when(queryMock.getResultList()).thenReturn(List.of(
//                new Reservacion() {{
//                    setId(1L);
//                    setFechaHora(LocalDateTime.now());
//                }}
//        ));
//
//        List<Reservacion> reservaciones = reservacionesDAO.obtenerReservacionesCliente(idRestaurante, telefono);
//
//        assertNotNull(reservaciones);
//        assertEquals(1, reservaciones.size());
//        assertEquals(1L, reservaciones.get(0).getId());
//    }
//
//    @Test
//    void testObtenerReservacionPorID() throws DAOException {
//        Long idReservacion = 1L;
//
//        Reservacion mockReservacion = new Reservacion();
//        mockReservacion.setId(idReservacion);
//        mockReservacion.setFechaHora(LocalDateTime.now());
//
//        when(em.find(Reservacion.class, idReservacion)).thenReturn(mockReservacion);
//
//        Reservacion reservacion = reservacionesDAO.obtenerReservacionPorID(idReservacion);
//
//        assertNotNull(reservacion);
//        assertEquals(idReservacion, reservacion.getId());
//    }
//}
