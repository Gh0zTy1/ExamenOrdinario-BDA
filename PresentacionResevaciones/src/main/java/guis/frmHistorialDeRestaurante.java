/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
//ola
package guis;

import bo.ClientesBO;
import bo.MesasBO;
import dto.ClienteDTO;
import dto.EstadoReservacionDTO;
import dto.MesaDTO;
import dto.ReservacionDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dto.RestauranteDTO;
import excepciones.NegocioException;
import fabricas.ReportesFachadaFactory;
import fachadas.ReportesFachada;
import ibo.IReservacionesBO;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author caarl
 */
public class frmHistorialDeRestaurante extends javax.swing.JFrame {

    private final IReservacionesBO reservacionesBO;
    private final MesasBO mesasBO;
    private final ClientesBO clientesBO;
    private RestauranteDTO restaurante;

    public frmHistorialDeRestaurante(RestauranteDTO restaurante) {
        this.restaurante = restaurante;
        initComponents();
        this.setTitle("Historial");
        this.setResizable(false);

        // Obtener la fachada desde la fábrica
        ReportesFachada fachada = ReportesFachadaFactory.getFachada();
        this.reservacionesBO = fachada.getReservacionesBO();
        this.mesasBO = fachada.getMesasBO();
        this.clientesBO = fachada.getClientesBO();

        cargarReservaciones();
        cargarEstados();
        cargarMesasEnComboBox();
        
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
            Logger.getLogger(frmHistorialDeRestaurante.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al cargar las reservaciones: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEstados() {
        cbxEstado.addItem("<None>");
        for (EstadoReservacionDTO estado : EstadoReservacionDTO.values()) {
            cbxEstado.addItem(estado.name());
        }

    }

    private void cargarMesasEnComboBox() {
        cbxMesas.addItem("<None>");  // Primer elemento como <None>

        try {
            List<MesaDTO> mesas = mesasBO.obtenerMesasTodas(this.restaurante.getId());
            for (MesaDTO mesa : mesas) {
                cbxMesas.addItem(mesa.getCodigo());  // Aquí puedes usar `mesa.getCodigo()` o `mesa.getNombre()`, dependiendo de la información que prefieras mostrar
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar mesas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            Logger.getLogger(frmHistorialDeRestaurante.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbxMesas = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btnGenerarRe = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblResultado = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Sitka Subheading", 1, 24)); // NOI18N
        jLabel1.setText("Historial por restaurante");

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 3, 14)); // NOI18N
        jLabel2.setText("Filtros");

        cbxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoActionPerformed(evt);
            }
        });

        jLabel3.setText("Estado");

        cbxMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMesasActionPerformed(evt);
            }
        });

        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnGenerarRe.setText("Generar");
        btnGenerarRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarReActionPerformed(evt);
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

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 3, 14)); // NOI18N
        jLabel6.setText("Resultado");

        jLabel7.setText("Mesa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbxMesas, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7))
                            .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(51, 51, 51)
                        .addComponent(btnGenerarRe)))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(396, 396, 396))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(393, 393, 393)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMesas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(246, 246, 246)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGenerarRe)
                            .addComponent(jButton1)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarReActionPerformed
        Document document = new Document(PageSize.A4); // Configuración para un documento tamaño A4
    String filePath = "ReporteReservaciones.pdf";

    try {
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();

        // Definimos los estilos de fuente para el título, subtítulo y contenido
        Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font fontSubTitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.ITALIC);
        Font fontContenido = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);

        // Título del reporte
        Paragraph titulo = new Paragraph("Reporte de Reservaciones", fontTitulo);
        titulo.setAlignment(Element.ALIGN_CENTER);
        document.add(titulo);
        document.add(new Paragraph(" ")); // Espacio en blanco

        // Información del restaurante
        Paragraph infoRestauranteTitulo = new Paragraph("Información del Restaurante", fontSubTitulo);
        infoRestauranteTitulo.setAlignment(Element.ALIGN_LEFT);
        document.add(infoRestauranteTitulo);

        String nombreRestaurante = "Nombre: " + restaurante.getNombre();
        String direccionRestaurante = "Dirección: " + restaurante.getDireccion();
        String telefonoRestaurante = "Teléfono: " + restaurante.getTelefono();

        Paragraph infoRestaurante = new Paragraph(
            nombreRestaurante + "\n" + direccionRestaurante + "\n" + telefonoRestaurante, fontContenido
        );
        infoRestaurante.setAlignment(Element.ALIGN_LEFT);
        document.add(infoRestaurante);
        document.add(new Paragraph(" ")); // Espacio en blanco

        // Sección de "Filtros aplicados"
        Paragraph filtrosTitulo = new Paragraph("Filtros aplicados:", fontSubTitulo);
        filtrosTitulo.setAlignment(Element.ALIGN_LEFT);
        document.add(filtrosTitulo);

        // Obtener y agregar valores seleccionados en los combobox
        String filtroEstado = "Estado: " + (cbxEstado.getSelectedItem() != null ? cbxEstado.getSelectedItem().toString() : "No especificado");
        String filtroMesa = "Mesa: " + (cbxMesas.getSelectedItem() != null ? cbxMesas.getSelectedItem().toString() : "No especificado");
  
       

        Paragraph filtrosSeleccionados = new Paragraph(
            filtroEstado + "\n" + filtroMesa    , fontContenido
        );
        filtrosSeleccionados.setAlignment(Element.ALIGN_LEFT);
        document.add(filtrosSeleccionados);
        document.add(new Paragraph(" ")); // Espacio en blanco antes de la tabla

        // Tabla de reservaciones
        PdfPTable table = new PdfPTable(tblResultado.getColumnCount());
        table.setWidthPercentage(100);

        // Añadimos los encabezados de la tabla
        for (int i = 0; i < tblResultado.getColumnCount(); i++) {
            table.addCell(new Paragraph(tblResultado.getColumnName(i), fontSubTitulo));
        }

        // Añadimos las filas de datos a la tabla
        for (int row = 0; row < tblResultado.getRowCount(); row++) {
            for (int col = 0; col < tblResultado.getColumnCount(); col++) {
                Object value = tblResultado.getValueAt(row, col);
                table.addCell(new Paragraph(value != null ? value.toString() : "", fontContenido));
            }
        }

        document.add(table); // Agregamos la tabla al documento

    } catch (DocumentException | IOException e) {
        JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return;
    } finally {
        document.close();
    }

    // Mensaje de confirmación y apertura automática del PDF
    JOptionPane.showMessageDialog(this, "El PDF se ha generado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    try {
        Desktop.getDesktop().open(new File(filePath));
    } catch (IOException ex) {
        JOptionPane.showMessageDialog(this, "No se pudo abrir el archivo PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnGenerarReActionPerformed

    private void cbxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoActionPerformed
        String estadoSeleccionado = (String) cbxEstado.getSelectedItem();
        if (estadoSeleccionado != null && !estadoSeleccionado.equals("<None>")) {
            filtrarReservacionesPorEstado(estadoSeleccionado);
        } else {
            cargarReservaciones(); // Mostrar todas las reservaciones si selecciona "<None>"
        }

    }//GEN-LAST:event_cbxEstadoActionPerformed

    private void cbxMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMesasActionPerformed
String mesaSeleccionada = (String) cbxMesas.getSelectedItem();

    if ("<None>".equals(mesaSeleccionada)) {
        cargarReservaciones();  // Muestra todas las reservaciones si selecciona "<None>"
    } else {
        try {
            // Usamos la instancia de reservacionesBO inyectada en el constructor
            List<ReservacionDTO> reservaciones = reservacionesBO.obtenerReservacionesDeMesa(this.restaurante.getId(), mesaSeleccionada);

            // Lógica para cargar en la tabla `tblResultado`
            DefaultTableModel modeloTabla = (DefaultTableModel) tblResultado.getModel();
            modeloTabla.setRowCount(0);  // Limpiamos la tabla

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
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, "Error al obtener reservaciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_cbxMesasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Cerrar el frame actual
        this.dispose();

        // Abrir el nuevo frame
        frmMenu nuevoFrame = new frmMenu(restaurante);
        nuevoFrame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarRe;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxMesas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblResultado;
    // End of variables declaration//GEN-END:variables
}
