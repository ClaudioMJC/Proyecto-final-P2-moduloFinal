/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa3_AccesoDatos;

import Capa_Entidades.Gasto;
import Config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class ADGasto {
    
    private Connection _cnn;
    private String _mensaje;
    
    
     public ADGasto() throws Exception {
        try {
            String url = Config.getConnectionString();
            _cnn = DriverManager.getConnection(url);
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }
      public String getMensaje() {
        return _mensaje;
    }
      
      
     public int InsertarGasto(Gasto gasto) throws Exception {
        int idGasto = -1;
        String sentencia = "INSERT INTO GASTOS (FECHA, MONTO, DESCRIPCION) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, new java.sql.Date(gasto.getFecha().getTime())); // Convertir fecha
            ps.setDouble(2, gasto.getMonto());
            ps.setString(3, gasto.getDescripcion());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                idGasto = rs.getInt(1);
                _mensaje = "Gasto ingresado satisfactoriamente";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return idGasto;
    }

    // Listar registros de gastos
    public List<Gasto> ListarRegistros(String condicion) throws Exception {
        ResultSet rs = null;
        List<Gasto> lista = new ArrayList<>();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_GASTO, FECHA, MONTO, DESCRIPCION FROM GASTOS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Gasto(
                        rs.getInt("ID_GASTO"),
                        rs.getDate("FECHA"),
                        rs.getDouble("MONTO"),
                        rs.getString("DESCRIPCION")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    // Obtener un registro de gasto
    public Gasto ObtenerRegistro(String condicion) throws Exception {
        Gasto gasto = new Gasto();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_GASTO, FECHA, MONTO, DESCRIPCION FROM GASTOS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);

            if (rs.next()) {
                gasto.setIdGasto(rs.getInt("ID_GASTO"));
                gasto.setFecha(rs.getDate("FECHA"));
                gasto.setMonto(rs.getDouble("MONTO"));
                gasto.setDescripcion(rs.getString("DESCRIPCION"));
                gasto.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return gasto;
    }

    // Modificar un registro de gasto
    public int ModificarGasto(Gasto gasto) throws Exception {
        int resultado = 0;
        String sentencia = "UPDATE GASTOS SET FECHA=?, MONTO=?, DESCRIPCION=? WHERE ID_GASTO=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setObject(1, gasto.getFecha()); // Fecha como objeto
            ps.setDouble(2, gasto.getMonto());
            ps.setString(3, gasto.getDescripcion());
            ps.setInt(4, gasto.getIdGasto());
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

    // Eliminar un registro de gasto
    public int EliminarGasto(Gasto gasto) throws Exception {
        int resultado = 0;
        String sentencia = "DELETE FROM GASTOS WHERE ID_GASTO=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, gasto.getIdGasto());
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

    
    
}
