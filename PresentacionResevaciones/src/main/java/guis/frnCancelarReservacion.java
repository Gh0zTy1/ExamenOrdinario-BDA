/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guis;

import bo.ClientesBO;
import dto.ClienteDTO;
import dto.ReservacionDTO;
import dto.RestauranteDTO;
import excepciones.NegocioException;
import fabricas.ReportesFachadaFactory;
import fabricas.fabricaFCD;
import fachadas.ReportesFachada;
import fachadas.cancelarReservacionFCD;
import ibo.IReservacionesBO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caarl
 */
public class frnCancelarReservacion extends javax.swing.JFrame {

    
     private final IReservacionesBO reservacionesBO;
    private final ClientesBO clientesBO;
    private final RestauranteDTO restaurante;
    /**
     * Creates new form frnCancelarReservacion
     * @param restaurante
     */
    public frnCancelarReservacion(RestauranteDTO restaurante) {
        this.restaurante = restaurante;
        initComponents();
        
        // Obtener la fachada desde la fábrica
        ReportesFachada fachada = ReportesFachadaFactory.getFachada();
        this.reservacionesBO = fachada.getReservacionesBO();
        fachada.getMesasBO();
        this.clientesBO = fachada.getClientesBO();
        cargarReservaciones();
        cargarClientes();
        this.setLocationRelativeTo(null);
        
    }
    
    private void cargarReservaciones() {
        try {
            // Obtenemos todas las reservaciones existentes
            List<ReservacionDTO> reservaciones = reservacionesBO.obtenerReservacionesTodos(this.restaurante.getId());
            // Configuramos el modelo de la tabla si aún no lo tiene
            DefaultTableModel modeloTabla = (DefaultTableModel) tblResultado.getModel();
            modeloTabla.setRowCount(0); // Limpiamos la tabla

            // Llenamos la tabla con las reservaciones obtenidas
            for (ReservacionDTO reservacion : reservaciones) {
                modeloTabla.addRow(new Object[]{
                    reservacion.getId(),
                    reservacion.getEstado(),
                    reservacion.getFechaHora(),
                    reservacion.getFechaHoraRegistro(),
                    reservacion.getMontoTotal(),
                    reservacion.getNumeroPersonas(),
                    reservacion.getCliente().getTelefono(), // Cambiado a número de teléfono
                    reservacion.getMesa().getCodigo(),
                    
                });
            }
        } catch (NegocioException ex) {
            // Manejo de excepciones
            Logger.getLogger(frmHistorialCliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cargar las reservaciones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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

   

    private Long obtenerIdReservacionSeleccionada() {
        int filaSeleccionada = tblResultado.getSelectedRow();
        if (filaSeleccionada != -1) {
            // Asumimos que la primera columna contiene el ID de la reservación
            return (Long) tblResultado.getValueAt(filaSeleccionada, 0);
        }
        return null;
    }

    private void filtrarReservacionesPorEstado(String estado) {
        try {
            List<ReservacionDTO> reservaciones = reservacionesBO.obtenerReservacionesTodos(this.restaurante.getId());
            DefaultTableModel modeloTabla = (DefaultTableModel) tblResultado.getModel();
            modeloTabla.setRowCount(0);

            for (ReservacionDTO reservacion : reservaciones) {
                if (reservacion.getEstado().name().equals(estado)) {
                    modeloTabla.addRow(new Object[]{
                        reservacion.getId(),
                        reservacion.getEstado(),
                        reservacion.getFechaHora(),
                        reservacion.getFechaHoraRegistro(),
                        reservacion.getMontoTotal(),
                        reservacion.getNumeroPersonas(),
                        reservacion.getCliente().getTelefono(), // Cambiado a número de teléfono
                        reservacion.getMesa().getCodigo(),
                        
                    });
                }
            }
        } catch (NegocioException ex) {
            Logger.getLogger(frmHistorialCliente.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cargar las reservaciones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbxClientes = new javax.swing.JComboBox<>();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("Cancelar reservacion");

        jLabel3.setText("Fecha");

        jLabel5.setText("Numero de telefono");

        jButton1.setText("Reset");

        cbxClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClientesActionPerformed(evt);
            }
        });

        jLabel7.setText("Desde");

        jLabel8.setText("Hasta");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(cbxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addContainerGap(42, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel3))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Filtrar por:");

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancelar mi reservacion");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Atras");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tblResultado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Estado", "Fecha", "Fecha Registro", "MontoTotal", "Numero de personas ", "Cliente ", "Mesa"
            }
        ));
        jScrollPane1.setViewportView(tblResultado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(80, 80, 80))))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       frmMenu men = new frmMenu(restaurante);
        men.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cbxClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClientesActionPerformed
    // Obtener el teléfono seleccionado en el ComboBox
    String telefonoSeleccionado = (String) cbxClientes.getSelectedItem();

    // Verificar si el teléfono seleccionado es nulo
    if (telefonoSeleccionado == null) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione un teléfono.", "Error", JOptionPane.ERROR_MESSAGE);
        return; // Salir del método si no hay selección
    }

    try {
        List<ReservacionDTO> reservaciones;

        if (telefonoSeleccionado.equals("<None>")) {
            // Si no hay un teléfono seleccionado, cargamos todas las reservaciones
            reservaciones = reservacionesBO.obtenerReservacionesTodos(this.restaurante.getId());
        } else {
            // Filtramos las reservaciones por el teléfono del cliente
            reservaciones = reservacionesBO.obtenerReservacionesCliente(this.restaurante.getId(), telefonoSeleccionado);
        }

        // Limpiar y cargar la tabla con las reservaciones filtradas
        DefaultTableModel modeloTabla = (DefaultTableModel) tblResultado.getModel();
        modeloTabla.setRowCount(0); // Limpiamos la tabla

        for (ReservacionDTO reservacion : reservaciones) {
            modeloTabla.addRow(new Object[]{
                reservacion.getId(),
                reservacion.getEstado(),
                reservacion.getFechaHora(),
                reservacion.getFechaHoraRegistro(),
                reservacion.getMontoTotal(),
                reservacion.getNumeroPersonas(),
                reservacion.getCliente().getTelefono(), // Número de teléfono
                reservacion.getMesa().getCodigo(),
            });
        }
    } catch (NegocioException ex) {
        // Manejo de excepciones
        Logger.getLogger(frnCancelarReservacion.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(this, "Error al filtrar las reservaciones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_cbxClientesActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // Verifica si se ha seleccionado una fila en la tabla
    int filaSeleccionada = tblResultado.getSelectedRow();  // Suponiendo que 'tablaReservaciones' es tu JTable
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una reservación para cancelar.", 
                                      "Error", JOptionPane.ERROR_MESSAGE);
        return; // Si no se selecciona ninguna fila, salir del método
    }
    
    // Obtén el ID de la reservación desde la tabla (suponiendo que la columna 0 tiene el ID)
    Long idReservacion = (Long) tblResultado.getValueAt(filaSeleccionada, 0);
    
    try {
        // Instanciar la fachada utilizando la fábrica
        cancelarReservacionFCD fachadaCancelar = fabricaFCD.fabricaFCDCancelar();
        
        // Llamar al método cancelarReservacion de la fachada
        fachadaCancelar.cancelarReservacion(idReservacion);
        
        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Reservación cancelada correctamente.");
        
        // Opcional: Actualizar la tabla o interfaz después de cancelar
        // Actualiza la tabla o realiza cualquier otra acción necesaria después de la cancelación
cargarReservaciones();
    } catch (NegocioException e) {
        // Manejar la excepción y mostrar mensaje de error
        JOptionPane.showMessageDialog(this, "Error al cancelar la reservación: " + e.getMessage(), 
                                      "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbxClientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblResultado;
    // End of variables declaration//GEN-END:variables
}
