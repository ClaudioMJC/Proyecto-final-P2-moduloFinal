/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Capa1_Presentacion;

import Capa2_LogicaNegocio.LNProducto;
import Capa3_AccesoDatos.ADProducto;
import Capa_Entidades.Producto;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Claudio
 */
public class frmProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmProductos
     * @throws java.lang.Exception
     */
    DefaultTableModel modelo; //variable global
    public frmProductos() throws Exception {
        initComponents();
        //llenarComboBoxProveedores();
        cboProveedores.setSelectedItem(null);
         //cboProveedores.setModel(new DefaultComboBoxModel<>()); // Inicializa el ComboBox vacío
        try {
            llenarComboBoxProveedores();
            llenarComboBoxIdProveedores();
            CargarDatos("");
        } catch (Exception e) {

            //throw e;
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
        
        
//        try {
//            CargarDatos("");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
//        }
        
    }
    
    private void llenarComboBoxProveedores() throws Exception {
        
        try {
            ADProducto adProducto = new ADProducto();
            List<String> listaProveedores = adProducto.obtenerProveedores();
            cboProveedores.removeAllItems();
            
            for (String infoProveedor : listaProveedores) {
                cboProveedores.addItem(infoProveedor);
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    
        private void llenarComboBoxIdProveedores() throws Exception {
        
        try {
            LNProducto lnProducto = new LNProducto();
            List<Integer> listaIdProveedores = lnProducto.obtenerIdProveedores();
            cboId.removeAllItems();
            
            for (Integer infoProveedor : listaIdProveedores) {
                cboId.addItem(infoProveedor.toString());
            }
            
        } catch (Exception e) {
            throw e;
        }
    }
    
    
    public void Limpiar() {
        txtId.setText("");
        txtDescripcion.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        cboId.setSelectedIndex(-1);
    }

    private void LimpiarTabla() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblProductos.setModel(modelo);
        modelo.addColumn("Código");
        modelo.addColumn("Descripción");
        modelo.addColumn("Precio Compra");
        modelo.addColumn("Precio Venta");
        modelo.addColumn("ID Proveedor");
    }

    private void CargarDatos(String condicion) throws Exception {
        try {
            LNProducto logica = new LNProducto();
            List<Producto> lista;
            LimpiarTabla();
            Object[] fila = new Object[5];
            lista = logica.ListarRegistros(condicion);
            for (Producto producto : lista) {
                fila[0] = producto.getId_producto();
                fila[1] = producto.getDescripcion();
                fila[2] = producto.getPrecioCompra();
                fila[3] = producto.getPrecioVenta();
                fila[4] = producto.getId_producto();
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    
    //Generar entidad modificado para la entidad productos, maneja excepciones
    private Producto GenerarEntidad() {
        Producto producto = new Producto();
        if (!txtId.getText().equals("")) {
            producto.setExiste(true);
            producto.setId_producto(Integer.parseInt(txtId.getText()));

        }

        producto.setDescripcion(txtDescripcion.getText());

        if (!txtPrecioCompra.getText().isEmpty()) {
            try {
                double precioCompra = Double.parseDouble(txtPrecioCompra.getText());
                producto.setPrecioCompra(precioCompra);
            } catch (NumberFormatException e) {
                // Manejo la excepción si se ingresó un valor no numérico

                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el precio de compra.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
        }

        if (!txtPrecioVenta.getText().isEmpty()) {
            try {
                double precioVenta = Double.parseDouble(txtPrecioVenta.getText());
                producto.setPrecioVenta(precioVenta);
            } catch (NumberFormatException e) {
                // de igual forma manejo lo excepción si se ingresó un valor no numérico

                JOptionPane.showMessageDialog(this, "Por favor, ingrese un valor numérico válido para el precio de venta.", "Error", JOptionPane.ERROR_MESSAGE);
                return null; // Devolver null para indicar que la entidad no se pudo crear
            }
        }

        // Obtener el ID del proveedor seleccionado del combobox
//        int idProveedor = (int) cboId.getSelectedItem();
//        producto.setId_proveedor(idProveedor);

    int idProveedor;
        try {
            String valorCombo = cboId.getSelectedItem().toString();
            idProveedor = Integer.parseInt(valorCombo);
            producto.setId_proveedor(idProveedor);
        } catch (NumberFormatException e) {
            // Manejar la excepción si no se puede convertir la cadena a un entero
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un proveedor válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return null; // Devolver null para indicar que la entidad no se pudo crear
        }

           
        



        return producto;
    }

    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDescripcion = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        cboProveedores = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboId = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProductos = new javax.swing.JTable();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        txtId.setEditable(false);

        jLabel1.setText("Descripción");

        jLabel2.setText("id_proveedor");

        jLabel3.setText("Código");

        jLabel4.setText("Precio_Venta");

        jLabel5.setText("Información de Proveedores");

        cboId.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Precio_Compra");

        tblProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProductos.setShowHorizontalLines(true);
        tblProductos.setShowVerticalLines(true);
        tblProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProductos);

        btnLimpiar.setBackground(new java.awt.Color(204, 204, 204));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(204, 204, 204));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnSalir.setBackground(new java.awt.Color(204, 204, 204));
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(cboId, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(cboProveedores, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel3))
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel1)))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(8, 8, 8))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLimpiar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnGuardar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel2)
                                .addGap(121, 121, 121)
                                .addComponent(jLabel5)))
                        .addGap(0, 5, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnBuscar, btnEliminar, btnGuardar, btnLimpiar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(btnSalir))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBuscar, btnEliminar, btnGuardar, btnLimpiar, btnSalir});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
         Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    //Acción para buscar producto y que llama al form de búscqueda
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
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
                        txtId.setText(String.valueOf(producto.getId_producto()));
                        txtDescripcion.setText(producto.getDescripcion());
                        txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
                        txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));

                        // Configura el combobox con el proveedor asociado al producto
                        cboId.setSelectedItem(producto.getId_proveedor());

                    } else {
                        txtId.setText("");
                        txtDescripcion.setText("");
                        txtPrecioCompra.setText("");
                        txtPrecioVenta.setText("");
                        cboId.setSelectedIndex(-1); // Reinicia el combobox a la selección inicial
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

        });

        frmBuscar.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        LNProducto logica = new LNProducto();
        Producto producto = GenerarEntidad();
        //        try {
        //            logica.Insertar(cliente);
        //            JOptionPane.showMessageDialog(this, logica.getMensaje());
        //
        //        } catch (Exception e) {
        //             JOptionPane.showMessageDialog(this, e.getMessage());
        //        }

        ///////////Versión actualizada para modificar o guardar  un nuevo resgistro
        try {
            if (producto.isExiste()) {
                logica.Modificar(producto);

            } else {
                logica.Insertar(producto);
            }
            JOptionPane.showMessageDialog(this, logica.getMensaje());
            Limpiar();

            CargarDatos("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
          LNProducto logica = new LNProducto();
        Producto producto;
        try {
            producto=GenerarEntidad();
            if (producto.isExiste()) {
                if (logica.Eliminar(producto)>0) {
                    JOptionPane.showMessageDialog(this,logica.getMensaje());
                    Limpiar();

                    CargarDatos("");

                }else{
                    JOptionPane.showMessageDialog(this, "No fue posible eliminar el cliente");

                }

            }else{
                JOptionPane.showMessageDialog(this, "Se debe seleccionar al cliente que desea eliminar");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductosMouseClicked
        try {
            LNProducto logica = new LNProducto(); // Cambiar a la lógica de Producto
            Producto producto;
            String condicion;
            if (evt.getClickCount() == 2) {
                int fila = tblProductos.rowAtPoint(evt.getPoint());
                txtId.setText(tblProductos.getValueAt(fila, 0).toString());
                condicion = String.format("id_producto=%s", txtId.getText()); // Cambiar a id_producto
                producto = logica.ObtenerRegistro(condicion); // Cambiar a Producto
                txtId.setText(String.valueOf(producto.getId_producto()));
                txtDescripcion.setText(producto.getDescripcion());
                txtPrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
                txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
                cboId.setSelectedItem(producto.getId_proveedor()); // Actualiza el combobox con el ID del proveedor

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_tblProductosMouseClicked

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboId;
    private javax.swing.JComboBox<String> cboProveedores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblProductos;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioVenta;
    // End of variables declaration//GEN-END:variables
}
