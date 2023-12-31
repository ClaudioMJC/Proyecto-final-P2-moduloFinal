/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */

package Capa1_Presentacion;

import Capa2_LogicaNegocio.LNGasto;
import Capa_Entidades.Gasto;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Claudio
 */
public class frmGasto extends javax.swing.JInternalFrame {
    DefaultTableModel modelo; //variable global
    /** Creates new form frmGasto */
    public frmGasto() {
        initComponents();
        try {
            
            CargarDatos("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    public void Limpiar() {
        txtId.setText("");
        txtMonto.setText("");
        dcFecha.setDate(null);
        txtDescripcion.setText("");
    }
    
    
    private void LimpiarTabla() {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblGastos.setModel(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Fecha");
        modelo.addColumn("Monto");
        modelo.addColumn("Descripción");
    }
    
    
    private void CargarDatos(String condicion) throws Exception {
    try {
        LNGasto logica = new LNGasto(); 
        //List<Gasto> lista = logica.ListarRegistros(condicion);
        List<Gasto> lista;
        LimpiarTabla();

        Object[] fila = new Object[4];
        lista = logica.ListarRegistros(condicion);
        for (Gasto gasto : lista) { 
            fila[0] = gasto.getIdGasto(); 
            fila[1] = gasto.getFecha(); 
            fila[2] = gasto.getMonto(); 
            fila[3] = gasto.getDescripcion(); 
            modelo.addRow(fila);
        }
    } catch (Exception e) {
        // Manejar la excepción apropiadamente
    }
}
    
 
    
    
    private Gasto GenerarGasto() {
        Gasto gasto = new Gasto();

        // Obtener el ID del gasto (idGasto)
        if (!txtId.getText().isEmpty()) {
            try {
                int idGasto = Integer.parseInt(txtId.getText());
                gasto.setIdGasto(idGasto);
                gasto.setExiste(true);
            } catch (NumberFormatException e) {
                // Manejo de excepción si se ingresa un valor no numérico
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el ID del gasto.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
        }

        // Obtener el monto (monto)
        if (!txtMonto.getText().isEmpty()) {
            try {
                double monto = Double.parseDouble(txtMonto.getText());
                gasto.setMonto(monto);
            } catch (NumberFormatException e) {
                // Manejo de excepción si se ingresa un valor no numérico
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el monto.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
        }

        // Obtener la fecha (fecha) desde el componente dcFecha
        java.util.Date fechaUtil = dcFecha.getDate();
        if (fechaUtil != null) {
            java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());
            gasto.setFecha(fecha);
        }

        // Obtener la descripción (descripcion)
        String descripcion = txtDescripcion.getText();
        gasto.setDescripcion(descripcion);

        return gasto;
    }





    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtId = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        dcFecha = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGastos = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        jLabel1.setText("Monto");

        jLabel2.setText("Fecha");

        jLabel3.setText("Descripción/Gasto");

        jLabel4.setText("Código");

        tblGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblGastos.setShowHorizontalLines(true);
        tblGastos.setShowVerticalLines(true);
        tblGastos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGastosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGastos);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(26, 26, 26)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(15, 15, 15))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(33, 33, 33)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(384, 384, 384))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(btnBuscar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnLimpiar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnGuardar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnEliminar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnSalir))))
                .addContainerGap(9, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(156, 156, 156)
                .addComponent(jLabel2)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
         frmBuscarGasto frmBuscar = new frmBuscarGasto(null, true);
    frmBuscar.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            try {
                int id = frmBuscar.ObtenerId();
                String condicion = "";
                LNGasto logica = new LNGasto();
                Gasto gasto;
                if (id > -1) {
                    condicion = String.format("ID_GASTO = %d", id);
                    gasto = logica.ObtenerRegistro(condicion);
                    if (gasto != null) {
                        txtId.setText(String.valueOf(gasto.getIdGasto()));
                        txtMonto.setText(String.valueOf(gasto.getMonto()));
                        dcFecha.setDate(gasto.getFecha());
                        txtDescripcion.setText(gasto.getDescripcion());
                    } else {
                        JOptionPane.showMessageDialog(null, "El gasto no existe.");
                    }
                } else {
                    // Limpiar los campos si no se selecciona ningún gasto
                    txtId.setText("");
                    txtMonto.setText("");
                    dcFecha.setDate(null);
                    txtDescripcion.setText("");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    });

    frmBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        LNGasto logica = new LNGasto();
        Gasto gasto = GenerarGasto();

        try {
            if (gasto != null) {
                if (gasto.isExiste()) {
                    logica.Modificar(gasto);
                } else {
                    logica.Insertar(gasto);
                }
                JOptionPane.showMessageDialog(this, logica.getMensaje());
                Limpiar();
                CargarDatos("");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        LNGasto logica = new LNGasto();
        Gasto gasto;
        try {
            gasto = GenerarGasto();
            if (gasto.isExiste()) {
                if (logica.Eliminar(gasto) > 0) {
                    JOptionPane.showMessageDialog(this, logica.getMensaje());
                    Limpiar();
                    CargarDatos("");
                } else {
                    JOptionPane.showMessageDialog(this, "No fue posible eliminar el gasto");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Se debe seleccionar el gasto que desea eliminar");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblGastosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGastosMouseClicked
        try {
            LNGasto logica = new LNGasto();
            Gasto gasto;
            String condicion;
            if (evt.getClickCount() == 2) {
                int fila = tblGastos.rowAtPoint(evt.getPoint());
                txtId.setText(tblGastos.getValueAt(fila, 0).toString());
                condicion = String.format("ID_GASTO=%s", txtId.getText());
                gasto = logica.ObtenerRegistro(condicion);
                txtId.setText(String.valueOf(gasto.getIdGasto()));
                txtMonto.setText(String.format("%.2f", gasto.getMonto())); // Con formato para dos decimales
                dcFecha.setDate(gasto.getFecha());
                txtDescripcion.setText(gasto.getDescripcion());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tblGastosMouseClicked

    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGastos;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables

}
