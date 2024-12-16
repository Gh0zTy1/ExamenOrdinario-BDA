/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guis;

import bo.ClientesBO;
import dto.ClienteDTO;
import dto.EstadoReservacionDTO;
import dto.MesaDTO;
import dto.ReservacionDTO;
import dto.RestauranteDTO;
import excepciones.NegocioException;
import fabricas.ReportesFachadaFactory;
import fabricas.fabricaFCD;
import fachadas.ReportesFachada;
import iFachadas.ICargarMesasFCD;
import iFachadas.ICrearReservacionFCD;
import ibo.IReservacionesBO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caarl
 */


public class frmReservarMesa extends javax.swing.JFrame {
private final RestauranteDTO restaurante;
 private final ClientesBO clientesBO;
 private final IReservacionesBO reservacionesBO;
    /**
     * Creates new form frmReservarMesa
     */

    public frmReservarMesa(RestauranteDTO restaurante) {
        initComponents();
        // Obtener la fachada desde la fábrica
        ReportesFachada fachada = ReportesFachadaFactory.getFachada();
         this.reservacionesBO = fachada.getReservacionesBO();
this.restaurante = restaurante;
this.clientesBO = fachada.getClientesBO();
        cargarMesasEnTabla();
        cargarClientes();
    }
    
    
        private void cargarMesasEnTabla() {
    try {
        // Obtener la instancia de la fachada desde la fábrica
        ICargarMesasFCD fachadaMesas = fabricaFCD.fabricaFCDCargarMesas();

        // Llamar al método de la fachada para cargar las mesas
        Long idRestaurante = 1L; // Cambia esto según el contexto de tu aplicación
        List<MesaDTO> mesas = fachadaMesas.cargarMesas(idRestaurante);

        // Configurar el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblMesas.getModel();
        modelo.setRowCount(0); // Limpiar la tabla antes de agregar nuevas filas

        // Poblar la tabla con las mesas obtenidas
        for (MesaDTO mesa : mesas) {
            modelo.addRow(new Object[]{
                mesa.getId(),
                mesa.getCodigo(),
                mesa.getUbicacion().toString(),
                mesa.getTipoMesa().getNombre()
            });
        }
    } catch (NegocioException x) {
        JOptionPane.showMessageDialog(this, "Error al cargar mesas: " + x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private String generarCodigoMesa(String ubicacion, String tipoMesa) {
    // Convertir las primeras letras de ubicación y tipo de mesa
    String ubicacionAbrev = ubicacion.substring(0, 3).toUpperCase();
    String tipoMesaAbrev = tipoMesa.substring(0, 3).toUpperCase();

    // Generar un número secuencial único (puedes usar lógica más avanzada si es necesario)
    int numeroSecuencial = tblMesas.getRowCount() + 1; // Basado en las filas actuales de la tabla

    // Formatear el código
    return ubicacionAbrev + "-" + tipoMesaAbrev + "-" + String.format("%03d", numeroSecuencial);
}
    
    
    private void cargarClientes() {
        cbxClientes.removeAllItems(); // Limpiamos el combobox
        cbxClientes.addItem("<None>"); // Añadimos la opción inicial
        try {
            List<ClienteDTO> clientes = clientesBO.obtenerClientesTodos();
            for (ClienteDTO cliente : clientes) {
                cbxClientes.addItem(cliente.getTelefono()); // Agregamos los teléfonos de los clientes
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarFormulario() {
    tblMesas.clearSelection();
    cbxClientes.setSelectedIndex(0);
    dtcFechaReservacion.setDate(null);
    tpHoradeseada.setTime(null);
    cbxCantPersonas.setSelectedIndex(0);
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxClientes = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        dtcFechaReservacion = new com.toedter.calendar.JDateChooser();
        btnReservar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMesas = new javax.swing.JTable();
        tpHoradeseada = new com.github.lgooddatepicker.components.TimePicker();
        cbxCantPersonas = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("Crear reservacion");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Informacion del cliente");

        jLabel4.setText("Cliente:");

        cbxClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(cbxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel5.setText("Cantidad de personas:");

        jLabel6.setText("Hora deseada: ");

        jLabel7.setText("Fecha: ");

        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        tblMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Codigo", "Ubicacion", "Tipo mesa"
            }
        ));
        jScrollPane1.setViewportView(tblMesas);

        cbxCantPersonas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NONE", "1", "2", "3", "4", "5", "6", "7", "8" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tpHoradeseada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                                        .addComponent(cbxCantPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(250, 250, 250)
                                        .addComponent(btnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dtcFechaReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(395, 395, 395)
                                .addComponent(btnAtras)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(btnAtras))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbxCantPersonas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tpHoradeseada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(dtcFechaReservacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(38, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReservar)
                        .addGap(15, 15, 15))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        frmMenu men = new frmMenu(restaurante);
        men.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed
 try {
        // Validación 1: Obtener la mesa seleccionada
        int filaSeleccionada = tblMesas.getSelectedRow();
        if (filaSeleccionada == -1) {
            throw new IllegalArgumentException("Por favor, seleccione una mesa.");
        }
        Long idMesa = (Long) tblMesas.getValueAt(filaSeleccionada, 0); // ID de la mesa
        String codigoMesa = (String) tblMesas.getValueAt(filaSeleccionada, 1);

        // Crear un objeto MesaDTO
        MesaDTO mesaSeleccionada = new MesaDTO();
        mesaSeleccionada.setId(idMesa);
        mesaSeleccionada.setCodigo(codigoMesa);
        mesaSeleccionada.setRestaurante(restaurante);

        // Validación 2: Obtener el cliente seleccionado
        String telefonoCliente = (String) cbxClientes.getSelectedItem();
        if (telefonoCliente == null || telefonoCliente.equals("<None>")) {
            throw new IllegalArgumentException("Seleccione un cliente válido.");
        }

        ClienteDTO cliente = clientesBO.obtenerClientePorTelefono(telefonoCliente);

        // Validación 3: Obtener la fecha y hora
        Date fechaSeleccionada = dtcFechaReservacion.getDate();
        LocalTime horaSeleccionada = tpHoradeseada.getTime();
        if (fechaSeleccionada == null) {
            throw new IllegalArgumentException("Seleccione una fecha válida.");
        }
        if (horaSeleccionada == null) {
            throw new IllegalArgumentException("Seleccione una hora válida.");
        }

        // Validaciones de fecha y hora
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaReservacion = fechaSeleccionada.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (fechaReservacion.isBefore(fechaActual)) {
            throw new IllegalArgumentException("La fecha no puede ser anterior al día de hoy.");
        }
        if (fechaReservacion.isEqual(fechaActual) && horaSeleccionada.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("La hora no puede ser anterior a la hora actual si la reservación es para hoy.");
        }

        // Validación de horario del restaurante
        LocalTime horaApertura = restaurante.getHoraApertura();
        LocalTime horaCierre = restaurante.getHoraCierre().minusHours(1); // Última hora válida
        if (horaSeleccionada.isBefore(horaApertura) || horaSeleccionada.isAfter(horaCierre)) {
            throw new IllegalArgumentException("La reservación solo puede hacerse entre %s y %s"
                    .formatted(horaApertura, horaCierre));
        }

        // Combinar fecha y hora en LocalDateTime
        LocalDateTime fechaHoraReservacion = LocalDateTime.of(fechaReservacion, horaSeleccionada);

        // Validación 4: Obtener el número de personas
        String cantPersonasStr = (String) cbxCantPersonas.getSelectedItem();
        if (cantPersonasStr == null) {
            throw new IllegalArgumentException("Seleccione la cantidad de personas.");
        }
        int numeroPersonas = Integer.parseInt(cantPersonasStr);

        // Creación del DTO de Reservación
        ReservacionDTO nuevaReservacion = new ReservacionDTO();
        nuevaReservacion.setMesa(mesaSeleccionada);
        nuevaReservacion.setCliente(cliente);
        nuevaReservacion.setFechaHora(fechaHoraReservacion);
        nuevaReservacion.setNumeroPersonas(numeroPersonas);
        nuevaReservacion.setEstado(EstadoReservacionDTO.PENDIENTE);

        // Guardar la reservación
        reservacionesBO.agregarReservacion(nuevaReservacion);
        JOptionPane.showMessageDialog(this, "Reservación creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        limpiarFormulario();

    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
    } catch (NegocioException ex) {
        JOptionPane.showMessageDialog(this, "Error al guardar la reservación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnReservarActionPerformed

    private void cbxClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClientesActionPerformed
     
    }//GEN-LAST:event_cbxClientesActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnReservar;
    private javax.swing.JComboBox<String> cbxCantPersonas;
    private javax.swing.JComboBox<String> cbxClientes;
    private com.toedter.calendar.JDateChooser dtcFechaReservacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMesas;
    private com.github.lgooddatepicker.components.TimePicker tpHoradeseada;
    // End of variables declaration//GEN-END:variables
}
