/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Capa1_Presentacion;

import Capa2_LogicaNegocio.LNDetalle_Factura;
import Capa2_LogicaNegocio.LNFactura;
import Capa2_LogicaNegocio.LNProducto;
import Capa_Entidades.Detalle_Factura;
import Capa_Entidades.Factura;
import Capa_Entidades.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Claudio
 */
public class frmFacturas extends javax.swing.JInternalFrame {
    
    DefaultTableModel modelo; //variable global
    DefaultTableModel modelo2; //variable global
    /**
     * Creates new form frmFacturas
     */
    
    public frmFacturas() {
        initComponents();
        DefaultComboBoxModel<String> comboBoxModelEstado = new DefaultComboBoxModel<>(new String[]{"PENDIENTE", "CANCELADA", "ANULADA"});
        
        cboUsuario.setSelectedItem(null);
        //cboUsuario.removeAllItems();
        
        try {
            CargarDatosDF("");
             llenarComboBoxIdUsuarios();
            CargarDatosF("");
            
           

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    cboEstado.setModel(comboBoxModelEstado);
    
        btnEliminarP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtén el índice de la fila seleccionada en la tabla
                int filaSeleccionada = tblDetalleF.getSelectedRow();

                // Verifica si se seleccionó una fila
                if (filaSeleccionada >= 0) {
                    // Elimina la fila seleccionada de la tabla de detalles de factura
                    DefaultTableModel modeloTabla = (DefaultTableModel) tblDetalleF.getModel();
                    modeloTabla.removeRow(filaSeleccionada);
                } else {
                    // Muestra un mensaje de error si no se seleccionó ninguna fila
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.");
                }
            }
        });
        
       
    
    }
    
    private void llenarComboBoxIdUsuarios() throws Exception {
        
//        try {
//            LNFactura lNFactura = new LNFactura();
//            List<Integer> listaIdUsuarios = lNFactura.obtenerIdUsuarios();
//            cboUsuario.removeAllItems();
//
//            for (Integer infoUsuario : listaIdUsuarios) {
//                cboUsuario.addItem(infoUsuario.toString());
//            }
//
//        } catch (Exception e) {
//            throw e;
//        }

        try {
            LNFactura lNFactura = new LNFactura();
            List<Integer> listaIdUsuarios = lNFactura.obtenerIdUsuarios();
            cboUsuario.removeAllItems();

            for (Integer idUsuario : listaIdUsuarios) {
                cboUsuario.addItem(idUsuario.toString());
            }

        } catch (Exception e) {
            throw e;
        }
    }
    
    //LIMPIAR CAMPOS
    public void Limpiar() {
        txtIdFactura.setText("");
        txtIdCliente.setText("");
        txtIdProducto.setText("");
        txtNombreP.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtTotal.setText("");
    }
    
    
    //AGREGA LAS COLUMNAS A LA TABLA FACTURA
    private void LimpiarTablaF() {
        modelo2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblFactura.setModel(modelo2);
        modelo2.addColumn("N°Factura");
        modelo2.addColumn("ID_Usuario");
        modelo2.addColumn("ID Cliente");
        modelo2.addColumn("Fecha");
        modelo2.addColumn("Estado");
         modelo2.addColumn("Total_Venta");
        
    }
    
    
         //AGREGA LAS COLUMNAS A LA TABLA DETALLE_FACTURA
        private void LimpiarTablaDF() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDetalleF.setModel(modelo);
        modelo.addColumn("N°Articulo");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Subtotal");
    }
    
   
        
        //CARGA LOS DATOS DE LA FACTURA 
     private void CargarDatosF(String condicion) throws Exception {
        try {
            LNFactura logica = new LNFactura();
            List<Factura> lista;
            LimpiarTablaF();
            Object[] fila = new Object[6];
            lista = logica.ListarRegistros(condicion);
            for (Factura factura : lista) {
                fila[0] = factura.getIdFactura();
                fila[1] = factura.getIdCliente();
                fila[2] = factura.getIdUsuario();
                fila[3] = factura.getFecha();
                fila[4] = factura.getEstado();
                fila[5] = factura.getTotalVenta();
                modelo2.addRow(fila);
            }
        } catch (Exception e) {
            throw e;
        }
    }
       
        
    //CARGA LOS DATOS DE DETALLE_FACTURA
    
    private void CargarDatosDF(String condicion) throws Exception {
        try {
            LNDetalle_Factura logica = new  LNDetalle_Factura();
            List<Detalle_Factura> lista;
            LimpiarTablaDF();
            Object[] fila = new Object[5];
            lista = logica.ListarRegistros(condicion);
            for (Detalle_Factura detalle_Factura : lista) {
                fila[0] = detalle_Factura.getIdFactura();
                fila[1] = detalle_Factura.getIdProducto();
                fila[2] = detalle_Factura.getCantidad();
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            throw e;
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

        jLabel1 = new javax.swing.JLabel();
        txtIdFactura = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboUsuario = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dcFecha = new com.toedter.calendar.JDateChooser();
        cboEstado = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnBuscarAC = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleF = new javax.swing.JTable();
        btnBuscarP = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
        txtIdProducto = new javax.swing.JTextField();
        txtNombreP = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblFactura = new javax.swing.JTable();
        btnEliminarP = new javax.swing.JButton();
        txtPrecio = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btnBuscarF = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Abastecedor \"El Buen Vecindario\"");

        txtIdFactura.setEditable(false);

        jLabel2.setText("ID_Cliente");

        jLabel3.setText("Fecha");

        cboUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("ID_Usuario");

        cboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("ID_Factura");

        jLabel6.setText("Estado_Factura");

        btnBuscarAC.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarAC.setText("Buscar/Agregar_Cliente");
        btnBuscarAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarACActionPerformed(evt);
            }
        });

        txtTotal.setEditable(false);

        jLabel7.setText("Total a Pagar");

        tblDetalleF.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalleF.setShowHorizontalLines(true);
        tblDetalleF.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tblDetalleF);

        btnBuscarP.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarP.setText("Buscar/Seleccionar Producto");
        btnBuscarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPActionPerformed(evt);
            }
        });

        btnAgregarProducto.setBackground(new java.awt.Color(204, 204, 204));
        btnAgregarProducto.setText("Agregar Producto");
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });

        txtIdCliente.setEditable(false);

        txtIdProducto.setEditable(false);

        tblFactura.setModel(new javax.swing.table.DefaultTableModel(
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
        tblFactura.setShowHorizontalLines(true);
        tblFactura.setShowVerticalLines(true);
        tblFactura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFacturaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblFactura);

        btnEliminarP.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminarP.setText("Eliminar Producto");
        btnEliminarP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPActionPerformed(evt);
            }
        });

        jLabel9.setText("Precio");

        jLabel10.setText("Código_Artículo");

        jLabel12.setText("Cantidad");

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setText("Guardar Factura");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jLabel13.setText("Descripción");

        btnBuscarF.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscarF.setText("Buscar Factura");
        btnBuscarF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(jLabel10)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel13)))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(125, 125, 125)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(108, 108, 108))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(cboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(56, 56, 56)
                                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(46, 46, 46)
                                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(53, 53, 53)
                                        .addComponent(jLabel4)
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel2)
                                        .addGap(84, 84, 84)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(116, 116, 116)
                                        .addComponent(jLabel3))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnBuscarP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminarP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnAgregarProducto)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnBuscarAC)
                        .addGap(35, 35, 35)
                        .addComponent(btnBuscarF)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtIdFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarAC)
                    .addComponent(btnBuscarF))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(jLabel10)
                                .addComponent(jLabel12))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarP))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarP)
                            .addComponent(btnGuardar))))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void btnBuscarACActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarACActionPerformed
        frmBuscarCliente frmbuscar = new frmBuscarCliente(null, true);
    frmbuscar.addWindowListener(new WindowAdapter() {

        @Override
        public void windowClosed(WindowEvent e) {
            try {
                int id = frmbuscar.ObtnerId();
                if (id > -1) {
                    // Aquí se asigna el ID del cliente al campo correspondiente en el formulario de facturas
                    txtIdCliente.setText(String.valueOf(id));
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    });

    frmbuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarACActionPerformed

    
    //Método para ir agregando  los productos a la tabla DETALLE_FACTURA (SE CALCULA A SU VEZ SUBTOTAL Y TOTAL)
    
    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
        // Obtener datos del producto de los campos de texto
        int idProducto = Integer.parseInt(txtIdProducto.getText());
        String nombreProducto = txtNombreP.getText();
        double precioProducto = Double.parseDouble(txtPrecio.getText());
        //int cantidadProducto = Integer.parseInt(txtCantidad.getText());
        int cantidadProducto;
        try {
            cantidadProducto = Integer.parseInt(txtCantidad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para la cantidad del producto.", "Error", JOptionPane.ERROR_MESSAGE);

            return;
        }

        // Calcular subtotal (precio x cantidad)
        double subtotal = precioProducto * cantidadProducto;

        // Agregar los datos a la tabla de detalles (tblDetalleF)
        DefaultTableModel modeloTabla = (DefaultTableModel) tblDetalleF.getModel();
        modeloTabla.addRow(new Object[]{idProducto, nombreProducto, precioProducto, cantidadProducto, subtotal});

        // Calcular y actualizar el total de la factura
        double totalFactura = calcularTotalFactura();  //MÉTOD DE CALCULAR EL TOTAL BASADOS EN EL SUBTOTAL
        txtTotal.setText(String.valueOf(totalFactura));

        // Limpiar los campos de texto para el próximo producto
        txtIdProducto.setText("");
        txtNombreP.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    
    //MÉTODO PARA SELECCIONAR QUE AL HABER SELECCIONADO O MARCADO UNA FILA DE UN PRODUCTO SE ELIMINE AL PRECIONAR EL BOTÓN ELIMINAR
    
    private void btnEliminarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPActionPerformed
        // Obtén el índice de la fila seleccionada en la tabla
        int filaSeleccionada = tblDetalleF.getSelectedRow();

        // Verifica si se seleccionó una fila
        if (filaSeleccionada >= 0) {
            // Elimina la fila seleccionada de la tabla de detalles de factura
            DefaultTableModel modeloTabla = (DefaultTableModel) tblDetalleF.getModel();
            modeloTabla.removeRow(filaSeleccionada);
        } else {
            // Muestra un mensaje de error si no se seleccionó ninguna fila
            JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.");
        }

    }//GEN-LAST:event_btnEliminarPActionPerformed
        
    
    
    //Método para recorrer la factura procesar los datos de detalle factura e ingresarlos a la tabla Detalle_Factura
    
    
    private void ProcesarDetalleFactura(DefaultTableModel modelDf) {
        // Recorremos cada fila de la tabla
        int rowCount = modelDf.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            int idProducto = (int) modelDf.getValueAt(i, 0); // Se especifica la columna que contiene el ID del producto
            int cantidad = (int) modelDf.getValueAt(i, 3);    // Se especifica la columna que contiene la cantidad

            // Crear un objeto Detalle_Factura con los datos de la fila
            Detalle_Factura detalleFactura = new Detalle_Factura();
            detalleFactura.setIdProducto(idProducto);
            detalleFactura.setCantidad(cantidad);

            // Insertar el detalle de factura en la base de datos
            try {
                LNDetalle_Factura lnDetalleFactura = new LNDetalle_Factura();
                lnDetalleFactura.InsertarDetalleFactura(detalleFactura);
            } catch (Exception e) {
                // Manejar cualquier excepción que ocurra al insertar el detalle de factura
                JOptionPane.showMessageDialog(this, "Error al procesar detalle de factura: " + e.getMessage());
            }
        }
    }

    
    
    
    
    
    
        //GUARDAR LA FACTURA/////////////////////////////////////////////
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         LNFactura lnFactura = new LNFactura();
        Factura factura = GenerarFactura(); // En este método se verfica tanto Factura como detalle factura para guardar ambos
        Detalle_Factura detalleFactura = GenerarDetalleFactura(); 

        try {
            if (factura.isExiste()) {
                lnFactura.ModificarFactura(factura);
            } else {
                int idFactura = lnFactura.InsertarFactura(factura);
                detalleFactura.setIdFactura(idFactura);
            }

            
            LNDetalle_Factura lnDetalleFactura = new LNDetalle_Factura();
            if (detalleFactura.isExiste()) {
                lnDetalleFactura.ModificarDetalleFactura(detalleFactura);
            } else {
                lnDetalleFactura.InsertarDetalleFactura(detalleFactura);
            }
            
            // Llamada al método para procesar los detalles de factura después de guardar la factura principal.
//            DefaultTableModel model = (DefaultTableModel) tblDetalleF.getModel();
//            ProcesarDetalleFactura(model);

            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
            Limpiar();
              CargarDatosF("");
              CargarDatosDF("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar los datos: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    
      //Método para BUSCAR un producto Abriendo un JDIALOG///////////////
    
    private void btnBuscarPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPActionPerformed
        frmBuscarProducto frmBuscar = new frmBuscarProducto(null, true);
        frmBuscar.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                try {
                    int id = frmBuscar.ObtnerId();
                    String condicion = "";
                    LNProducto logica = new LNProducto();
                    Producto producto;
                    if (id > -1) {
                        condicion = String.format("id_producto = %d", id);
                        producto = logica.ObtenerRegistro(condicion);
                        txtIdProducto.setText(String.valueOf(producto.getId_producto())); //Asigna la información requerida del producto
                        txtNombreP.setText(producto.getDescripcion());                    //A los campos que ingresaremos a la BD
                        txtPrecio.setText(String.valueOf(producto.getPrecioVenta()));

                        
                        

                    } else {
                        txtIdProducto.setText("");  // si no se selecciona nada o se cierra el form de buscar el producto 
                        txtNombreP.setText("");     //los campos texbox en el form quedan vacíos, listo para volver a buscar y seleccionar
                        txtPrecio.setText("");
                        
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

        });

        frmBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarPActionPerformed
    
    //LIMPIAR LOS CAMPOS PARA INGRESAR UNA NUEVA FACTURA
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    
    //EVENTO CUANDO SE CLICKEA A LA TABLA DE FACTURA DOS VECES
    private void tblFacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFacturaMouseClicked
         try {
            LNFactura logica = new LNFactura(); // Conexion a la capa lógica
            Factura factura;
            String condicion;
            if (evt.getClickCount() == 2) {
                int fila = tblFactura.rowAtPoint(evt.getPoint());
                int idFactura = Integer.parseInt(tblFactura.getValueAt(fila, 0).toString());
                condicion = String.format("idFactura=%s", idFactura); // Cambiar a idFactura
                factura = logica.ObtenerRegistro(condicion); // Cambiar a Factura
                txtIdFactura.setText(String.valueOf(factura.getIdFactura()));
                txtIdCliente.setText(String.valueOf(factura.getIdCliente()));
                cboUsuario.setSelectedItem(factura.getIdUsuario());
                dcFecha.setDate(factura.getFecha());
                cboEstado.setSelectedItem(factura.getEstado());
                txtTotal.setText(String.valueOf(factura.getTotalVenta()));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tblFacturaMouseClicked

    private void btnBuscarFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFActionPerformed
        frmBuscarFactura frmbuscar = new frmBuscarFactura(null, true);
        frmbuscar.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {

                try {
                    int id = frmbuscar.ObtnerId();
                    String condicion = "";
                    LNFactura logica = new LNFactura();
                    Factura factura;
                    if (id > -1) {
                        condicion = String.format("ID_FACTURA = %d", id);
                        factura = logica.ObtenerRegistro(condicion);
                        txtIdFactura.setText(String.valueOf(factura.getIdCliente()));
                        cboUsuario.setSelectedItem(factura.getIdUsuario());
                        txtIdCliente.setText(String.valueOf(factura.getIdCliente()));
                        cboEstado.setSelectedItem(factura.getEstado());
                        dcFecha.setDate(factura.getFecha());

                    } else {

                        // Limpiar los campos o realizar alguna otra acción cuando no se selecciona una factura
                        txtIdFactura.setText("");
                        cboUsuario.setSelectedItem(null); // Limpiar la selección del combo
                        txtIdCliente.setText("");
                        cboEstado.setSelectedItem(null); // Limpiar la selección del combo
                        dcFecha.setDate(null); // Limpiar la fecha

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

        });

        frmbuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarFActionPerformed

    
    
    // Método para calcular el total de la factura sumando los subtotales de los productos
    private double calcularTotalFactura() {
        DefaultTableModel modeloTabla = (DefaultTableModel) tblDetalleF.getModel();
        double total = 0.0;

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            double subtotal = (double) modeloTabla.getValueAt(i, 4); // Se indica la columna donde está el subtotal 
            total += subtotal;
        }

        return total;
    }
    
    //quedé REVISANDO LO DEL DAR DOBLE CLICK A LA TBLFACTURA MÉTODO
    
    //GENERA LA ENTIDAD FACTURA//////////
    
    private Factura GenerarFactura() {
        Factura factura = new Factura();

        // Obtener el ID de la factura (idFactura)
        if (!txtIdFactura.getText().isEmpty()) {
            try {
                int idFactura = Integer.parseInt(txtIdFactura.getText());
                factura.setIdFactura(idFactura);
                factura.setExiste(true);
            } catch (NumberFormatException e) {
                // Manejo de excepción si se ingresa un valor no numérico
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el ID de la factura.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
        }

        // Obtener el ID del cliente (idCliente)
        if (!txtIdCliente.getText().isEmpty()) {
            try {
                int idCliente = Integer.parseInt(txtIdCliente.getText());
                factura.setIdCliente(idCliente);
            } catch (NumberFormatException e) {
                // Manejo de excepción si se ingresa un valor no numérico
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el ID del cliente.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
        }

        // Obtener el ID del usuario (idUsuario) desde el combobox
        if (cboUsuario.getSelectedItem() != null) {
            try {
                String valorComboUsuario = cboUsuario.getSelectedItem().toString();
                int idUsuario = Integer.parseInt(valorComboUsuario);
                factura.setIdUsuario(idUsuario);
            } catch (NumberFormatException e) {
                // Manejar la excepción si no se puede convertir la cadena a un entero
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
}

        // Obtener la fecha (fecha) desde el componente dcFecha
        if (dcFecha.getDate() != null) {
            java.sql.Date fecha = new java.sql.Date(dcFecha.getDate().getTime());
            factura.setFecha(fecha);
        }

        // Obtener el estado (estado) desde el combobox cboEstado
        if (cboEstado.getSelectedItem() != null) {
            String estado = cboEstado.getSelectedItem().toString();
            factura.setEstado(estado);
        }

        // Obtener el total de venta (totalVenta)
        if (!txtTotal.getText().isEmpty()) {
            try {
                double totalVenta = Double.parseDouble(txtTotal.getText());
                factura.setTotalVenta(totalVenta);
            } catch (NumberFormatException e) {
                // Manejo de excepción si se ingresa un valor no numérico
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el total de venta.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
        }

        return factura;
    }

    
    //Generamos la entidad detalelle_factura
//    private Detalle_Factura GenerarDetalleFactura() {
//        Detalle_Factura detalleFactura = new Detalle_Factura();
//
//        // Obtener el ID del producto (idProducto)
//        if (!txtIdProducto.getText().isEmpty()) {
//            try {
//                int idProducto = Integer.parseInt(txtIdProducto.getText());
//                detalleFactura.setIdProducto(idProducto);
//            } catch (NumberFormatException e) {
//                // Manejo de excepción si se ingresa un valor no numérico
//                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el ID del producto.", "Error", JOptionPane.ERROR_MESSAGE);
//                return null; // Devolver null para indicar que la entidad no se pudo crear
//            }
//        }
//
//
//        // Obtener la cantidad (cantidad)
//        if (!txtCantidad.getText().isEmpty()) {
//            try {
//                int cantidad = Integer.parseInt(txtCantidad.getText());
//                detalleFactura.setCantidad(cantidad);
//            } catch (NumberFormatException e) {
//                // Manejo de excepción si se ingresa un valor no numérico
//                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
//                return null; // Devolver null para indicar que la entidad no se pudo crear
//            }
//        }
//
//        return detalleFactura;
//    }

    
    
    private Detalle_Factura GenerarDetalleFactura() {
        Detalle_Factura detalleFactura = new Detalle_Factura();

        DefaultTableModel model = (DefaultTableModel) tblDetalleF.getModel();
        int rowCount = model.getRowCount();

        List<Integer> idProductos = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            Object idProductoObj = model.getValueAt(i, 0); // Reemplaza 'columnaIdProducto' con el índice real de la columna de id_producto
            Object cantidadObj = model.getValueAt(i, 3); // Reemplaza 'columnaCantidad' con el índice real de la columna de cantidad

            if (idProductoObj instanceof Integer && cantidadObj instanceof Integer) {
                int idProducto = (int) idProductoObj;
                int cantidad = (int) cantidadObj;

                idProductos.add(idProducto);
                cantidades.add(cantidad);
            } else {
                // Manejar el caso en que los valores no sean enteros válidos
                JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para los ID de producto y cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
        }

        if (!idProductos.isEmpty() && !cantidades.isEmpty()) {
            detalleFactura.setIdProducto(idProductos.get(0));
            detalleFactura.setCantidad(cantidades.get(0)); // Cambiado a obtener la cantidad del primer elemento
            return detalleFactura;
        } else {
            JOptionPane.showMessageDialog(this, "La lista de productos o cantidades está vacía.", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Devolver null si no se encontraron datos válidos
        }
    }


    
   

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarAC;
    private javax.swing.JButton btnBuscarF;
    private javax.swing.JButton btnBuscarP;
    private javax.swing.JButton btnEliminarP;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cboEstado;
    private javax.swing.JComboBox<String> cboUsuario;
    private com.toedter.calendar.JDateChooser dcFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblDetalleF;
    private javax.swing.JTable tblFactura;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdFactura;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtNombreP;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
