/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa3_AccesoDatos;

import Capa_Entidades.Producto;
import Config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class ADProducto {
    
     private Connection _cnn;
    private String _mensaje;

    //Constructor
    public ADProducto() throws Exception {
        try {
            String url = Config.getConnectionString();
            _cnn = DriverManager.getConnection(url);
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }

    //Getter
    public String getMensaje() {
        return _mensaje;
    }
    
    
    //Insertar
    public int Insertar(Producto producto) throws Exception {
    int id_producto = -1;
    String sentencia = "Insert into Productos (id_proveedor, descripcion, preciocompra, precioventa) values (?,?,?,?)";
    try {
        PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, producto.getId_proveedor());
        ps.setString(2, producto.getDescripcion());
        ps.setDouble(3, producto.getPrecioCompra());
        ps.setDouble(4, producto.getPrecioVenta());
        
        ps.execute();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs != null && rs.next()) {
            id_producto = rs.getInt(1);
            _mensaje = "Producto ingresado satisfactoriamente";
        }
    } catch (Exception e) {
        throw e;
    } finally {
        _cnn = null;
    }
    return id_producto;
}

    
    
    
    public List<Producto> ListarRegistros(String condicion) throws SQLException {
        ResultSet rs = null;
        List<Producto> lista = new ArrayList();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_producto, id_proveedor, descripcion, preciocompra, precioventa from Productos";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Producto(
                        rs.getInt("id_producto"),
                        rs.getInt("id_proveedor"),
                        rs.getString("descripcion"),
                        rs.getDouble("preciocompra"),
                        rs.getDouble("precioventa")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    
    // Obtener un registro de Producto
public Producto ObtenerRegistro(String condicion) throws Exception {
    Producto producto = new Producto();
    ResultSet rs = null;
    try {
        Statement stm = _cnn.createStatement();
        String sentencia = "SELECT id_producto, id_proveedor, descripcion, precioCompra, precioVenta FROM productos";
        if (!condicion.equals("")) {
            sentencia = String.format("%s WHERE %s", sentencia, condicion);
        }
        rs = stm.executeQuery(sentencia);

        if (rs.next()) {
            producto.setId_producto(rs.getInt(1));
            producto.setId_proveedor(rs.getInt(2));
            producto.setDescripcion(rs.getString(3));
            producto.setPrecioCompra(rs.getDouble(4));
            producto.setPrecioVenta(rs.getDouble(5));

            producto.setExiste(true);
        }
    } catch (Exception e) {
        throw e;
    } finally {
        _cnn = null;
    }
    return producto;
}

// Modificar un Producto
public int Modificar(Producto producto) throws Exception {
    int resultado = 0;
    String sentencia = "UPDATE Productos SET id_proveedor=?, descripcion=?, precioCompra=?, precioVenta=? WHERE id_producto=?";
    try {
        PreparedStatement ps = _cnn.prepareStatement(sentencia);
        ps.setInt(1, producto.getId_proveedor());
        ps.setString(2, producto.getDescripcion());
        ps.setDouble(3, producto.getPrecioCompra());
        ps.setDouble(4, producto.getPrecioVenta());
        //ps.setInt(4, producto.getId_proveedor());
        ps.setInt(5, producto.getId_producto());
        resultado = ps.executeUpdate();
        if (resultado > 0) {
            _mensaje = "Registro modificado satisfactoriamente";
        }
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }
    return resultado;
}


public int Eliminar(Producto producto) throws Exception {
    int resultado = 0;
    String sentencia = "DELETE FROM Productos WHERE ID_PRODUCTO=?";
    try {
        PreparedStatement ps = _cnn.prepareStatement(sentencia);
        ps.setInt(1, producto.getId_producto());
        resultado = ps.executeUpdate();
        if (resultado > 0) {
            _mensaje = "Registro eliminado satisfactoriamente";
        }
    } catch (Exception ex) {
        throw ex;
    } finally {
        _cnn = null;
    }
    return resultado;
}

    



 // Método para obtener la lista de proveedores
    public List<String> obtenerProveedores() throws Exception {
        List<String> listaProveedores = new ArrayList<>();
        ResultSet rs = null;

        try {
            String sentencia = "SELECT ID_PROVEEDOR, DESCRIPCION FROM PROVEEDORES";
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            rs = ps.executeQuery(); // Ejecutar la consulta que contendrá la información requerida

            while (rs.next()) {                             //mientras hayan registros se guadarán
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                String descripcion = rs.getString("DESCRIPCION");  //tanto el id como la descripción son los datos que se guardan

                // Formatear la información como "id_proveedor = nombre de la empresa" esta cadena será la guía para que el usuario seleccione el id del proveedor deseado y asociado al producto que va a ingresar
                String infoProveedor = "id_proveedor = " + idProveedor + " - " + descripcion;

                listaProveedores.add(infoProveedor);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaProveedores;
    }

   
    
    
    public List<Integer> obtenerIdProveedores() throws Exception {
        List<Integer> listaProveedores = new ArrayList<>();
        ResultSet rs = null;

        try {
            String sentencia = "SELECT ID_PROVEEDOR FROM PROVEEDORES";
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            rs = ps.executeQuery(); // Codigo muy similar al anterior solo que solo vamos a cargar el id_proveedor que se va a ingresar

            while (rs.next()) {                             
                int idProveedor = rs.getInt("ID_PROVEEDOR");
                 

                
                int infoProveedor =  idProveedor;

                listaProveedores.add(infoProveedor);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaProveedores;
    }
    
   
   
   
    
    
    
}
