/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guis;

import dto.MesaDTO;
import dto.RestauranteDTO;
import dto.TipoMesaDTO;
import dto.UbicacionMesaDTO;
import excepciones.NegocioException;
import fabricas.fabricaFCD;
import fachadas.TiposMesaFachada;
import fachadas.agregarMesasFCD;
import fachadas.eliminarMesasFCD;
import iFachadas.ICargarMesasFCD;
import java.awt.GridLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author caarl
 */


public class frmAministradorMesas extends javax.swing.JFrame {
    /**
     * Creates new form frmAministradorMesas
     */
    private final RestauranteDTO restaurante;
    
    public frmAministradorMesas(RestauranteDTO restaurante) {   
        
        initComponents();
        this.restaurante = restaurante;
      cargarMesasEnTabla();
        
        
       
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
                mesa.getRestaurante().getId(),
                mesa.getTipoMesa().getNombre()
            });
        }
    } catch (NegocioException x) {
        JOptionPane.showMessageDialog(this, "Error al cargar mesas: " + x.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
   private String generarCodigoMesa(String ubicacion, String tipoMesa, String capacidad) {
    // Convertir las primeras letras de la ubicación y tipo de mesa
    String ubicacionAbrev = ubicacion.substring(0, 3).toUpperCase();
    String tipoMesaAbrev = tipoMesa.substring(0, 3).toUpperCase();

    // Obtener el siguiente número secuencial único desde la base de datos
    int numeroSecuencial = obtenerSiguienteNumeroSecuencial(ubicacionAbrev, tipoMesaAbrev);

    // Formatear el código como: "UBI-TIP-001"
    return ubicacionAbrev + "-" + capacidad + "-" + String.format("%03d", numeroSecuencial);
}
private int obtenerSiguienteNumeroSecuencial(String ubicacionAbrev, String tipoMesaAbrev) {
    // Aquí es donde obtienes el siguiente número secuencial desde la base de datos.
    // Llamas al DAO o al servicio que maneja las mesas y consultas el siguiente número secuencial.
    try {
        ICargarMesasFCD fachadaMesas = fabricaFCD.fabricaFCDCargarMesas();
        Long idRestaurante = restaurante.getId(); // Obtén el ID del restaurante
        List<MesaDTO> mesas = fachadaMesas.cargarMesas(idRestaurante);

        // Filtrar las mesas que corresponden a la ubicación y tipo de mesa
        long count = mesas.stream()
                .filter(m -> m.getUbicacion().toString().substring(0, 3).equals(ubicacionAbrev) && 
                             m.getTipoMesa().getNombre().substring(0, 3).equals(tipoMesaAbrev))
                .count();

        // El siguiente número será el número de mesas con esa ubicación y tipo + 1
        return (int) (count + 1);
    } catch (NegocioException e) {
        JOptionPane.showMessageDialog(this, "Error al obtener el siguiente número secuencial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        return 1;  // En caso de error, empieza desde 1
    }
}
    public void eliminarMesaSeleccionada() {
    // Obtén la fila seleccionada de la tabla
    int filaSeleccionada = tblMesas.getSelectedRow();

    // Verifica que una fila esté seleccionada
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione una mesa para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Obtén el ID del restaurante y el código de la mesa desde la tabla
    Long idRestaurante = (Long) tblMesas.getValueAt(filaSeleccionada, 3); // Cambia el índice según corresponda (columna 0 para idRestaurante)
    String codigoMesa = (String) tblMesas.getValueAt(filaSeleccionada, 1); // Cambia el índice según corresponda (columna 1 para codigoMesa)

    // Verifica si los valores son válidos
    if (idRestaurante == null || codigoMesa == null || codigoMesa.isEmpty()) {
        JOptionPane.showMessageDialog(this, "La mesa seleccionada no tiene un ID o código válido.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Confirmación antes de eliminar
    int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar esta mesa?", "Confirmación", JOptionPane.YES_NO_OPTION);
    if (respuesta == JOptionPane.YES_OPTION) {
        // Aquí llamamos al método de la fachada para eliminar la mesa
        try {
            eliminarMesaDeFachada(idRestaurante, codigoMesa); // Llamamos a la fachada con ambos parámetros
            JOptionPane.showMessageDialog(this, "Mesa eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la mesa: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
          // Actualizar la tabla
                cargarMesasEnTabla();
    }
}

/**
 * Método para eliminar la mesa usando la fachada correspondiente.
 */
private void eliminarMesaDeFachada(Long idRestaurante, String codigoMesa) throws Exception {
    // Aquí puedes llamar a tu fachada para manejar la eliminación de la mesa
    // Llamamos al método de la fachada con ambos parámetros
    eliminarMesasFCD eliminarFCD = fabricaFCD.fabricaFCDEliminarMesas();
    eliminarFCD.eliminarMesa(idRestaurante, codigoMesa); // Método modificado para aceptar ambos parámetros
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDayChooser1 = new com.toedter.calendar.JDayChooser();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMesas = new javax.swing.JTable();
        btnAgregarMesa = new javax.swing.JButton();
        btnEliminarMesa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("Administrador Mesas");

        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        tblMesas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Codigo", "Ubicacion", "ID restaurante", "Tipo mesa"
            }
        ));
        jScrollPane1.setViewportView(tblMesas);

        btnAgregarMesa.setText("Agregar Mesas");
        btnAgregarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarMesaActionPerformed(evt);
            }
        });

        btnEliminarMesa.setText("Eliminar Mesa");
        btnEliminarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnAtras))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarMesa)
                    .addComponent(btnAgregarMesa))
                .addGap(20, 20, 20))
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

    private void btnAgregarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarMesaActionPerformed
        try {
            // Obtener los tipos de mesa usando el método de la fábrica
            List<TipoMesaDTO> tiposMesaDTO = fabricaFCD.obtenerTiposMesa(); // Devuelve List<TipoMesaDTO>
            
            // Crear el JComboBox para los tipos de mesa
            JComboBox<TipoMesaDTO> cmbTiposMesa = new JComboBox<>(tiposMesaDTO.toArray(new TipoMesaDTO[0])); // Usamos TipoMesaDTO directamente
            
            // Crear el JComboBox para las ubicaciones (suponiendo que UbicacionMesaDTO es un enum)
            JComboBox<UbicacionMesaDTO> cmbUbicaciones = new JComboBox<>(UbicacionMesaDTO.values());
            
            // Crear el panel para el JOptionPane
            JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
            panel.add(new JLabel("Seleccione el tipo de mesa:"));
            panel.add(cmbTiposMesa);
            panel.add(new JLabel("Seleccione la ubicación:"));
            panel.add(cmbUbicaciones);
            
            // Mostrar el JOptionPane
            int resultado = JOptionPane.showConfirmDialog(this, panel, "Agregar Mesa", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (resultado == JOptionPane.OK_OPTION) {
                // Obtener los valores seleccionados
                TipoMesaDTO tipoMesaSeleccionado = (TipoMesaDTO) cmbTiposMesa.getSelectedItem();
                String ubicacionSeleccionada = cmbUbicaciones.getSelectedItem().toString();
                String capacidadSeleccionada = tipoMesaSeleccionado.getNombre().equals("Pequeña") ? "2" :
                        tipoMesaSeleccionado.getNombre().equals("Mediana") ? "4" : "6";
                
                // Generar automáticamente el código de la mesa
                String codigoMesa = generarCodigoMesa(ubicacionSeleccionada, tipoMesaSeleccionado.getNombre(), capacidadSeleccionada);
                
                // Crear los DTOs necesarios
                UbicacionMesaDTO ubicacionDTO = UbicacionMesaDTO.valueOf(ubicacionSeleccionada.toUpperCase()); // DTO de la ubicación
                RestauranteDTO restauranteDTO = new RestauranteDTO();
                restauranteDTO.setId(1L); // Solo manejamos el restaurante con ID 1
                
                // Crear el DTO de la mesa
                MesaDTO mesaDTO = new MesaDTO();
                mesaDTO.setCodigo(codigoMesa); // Pasamos el código generado
                mesaDTO.setTipoMesa(tipoMesaSeleccionado); // Asignar el TipoMesaDTO directamente
                mesaDTO.setUbicacion(ubicacionDTO);
                mesaDTO.setRestaurante(restauranteDTO); // Asociamos el restaurante
                mesaDTO.setFechaNuevaDisponibilidad(null); // Fecha es null cuando se crea la mesa
                
                // Llamar a la fachada para agregar la mesa
                agregarMesasFCD fachadaMesas = fabricaFCD.fabricaFCDAgregarMesas();
                fachadaMesas.agregarMesas(mesaDTO);
                
                // Mensaje de éxito
                JOptionPane.showMessageDialog(this, "Mesa agregada con éxito. Código de la mesa: " + codigoMesa);
                
                // Actualizar la tabla
                cargarMesasEnTabla();
            }   } catch (NegocioException ex) {
            Logger.getLogger(frmAministradorMesas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnAgregarMesaActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
       
        frmMenu men = new frmMenu(restaurante);
        men.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnEliminarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarMesaActionPerformed
eliminarMesaSeleccionada();
 
    }//GEN-LAST:event_btnEliminarMesaActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarMesa;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnEliminarMesa;
    private com.toedter.calendar.JDayChooser jDayChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMesas;
    // End of variables declaration//GEN-END:variables
}
