/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa3_AccesoDatos;

import Capa_Entidades.Proveedor;
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
public class ADProveedor {
    
    private Connection _cnn;
    private String _mensaje;

    //Constructor
    public ADProveedor() throws Exception {
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

    ////////////////////////////////////////////////////////////////////// 
// Método para Insertar un proveedor
    public int Insertar(Proveedor proveedor) throws Exception {
        int idProveedor = -1;
        String sentencia = "INSERT INTO PROVEEDORES (NOMBRE, APELLIDO, TELEFONO, DESCRIPCION) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getApellido());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDescripcion());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                idProveedor = rs.getInt(1);
                _mensaje = "Proveedor ingresado satisfactoriamente";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return idProveedor;
    }

// Listar Registros
    public List<Proveedor> ListarRegistros(String condicion) throws SQLException {
        ResultSet rs = null;
        List<Proveedor> lista = new ArrayList<>();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_PROVEEDOR, NOMBRE, APELLIDO, TELEFONO, DESCRIPCION FROM PROVEEDORES";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Proveedor(rs.getInt("ID_PROVEEDOR"), rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("TELEFONO"), rs.getString("DESCRIPCION")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

// Obtener Registro
    public Proveedor ObtenerRegistro(String condicion) throws Exception {
        Proveedor proveedor = new Proveedor();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_PROVEEDOR, NOMBRE, APELLIDO, TELEFONO, DESCRIPCION FROM PROVEEDORES";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);

            if (rs.next()) {
                proveedor.setIdProveedor(rs.getInt("ID_PROVEEDOR"));
                proveedor.setNombre(rs.getString("NOMBRE"));
                proveedor.setApellido(rs.getString("APELLIDO"));
                proveedor.setTelefono(rs.getString("TELEFONO"));
                proveedor.setDescripcion(rs.getString("DESCRIPCION"));
                proveedor.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return proveedor;
    }

// Modificar Proveedor
    public int Modificar(Proveedor proveedor) throws Exception {
        int resultado = 0;
        String sentencia = "UPDATE PROVEEDORES SET NOMBRE=?, APELLIDO=?, TELEFONO=?, DESCRIPCION=? WHERE ID_PROVEEDOR=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getApellido());
            ps.setString(3, proveedor.getTelefono());
            ps.setString(4, proveedor.getDescripcion());
            ps.setInt(5, proveedor.getIdProveedor());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Proveedor modificado satisfactoriamente";
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return resultado;
    }

// Eliminar Proveedor
    public int Eliminar(Proveedor proveedor) throws Exception {
        int resultado = 0;
        String sentencia = "DELETE FROM PROVEEDORES WHERE ID_PROVEEDOR=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, proveedor.getIdProveedor());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Proveedor eliminado satisfactoriamente";
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return resultado;
    }

    
    
    
}
