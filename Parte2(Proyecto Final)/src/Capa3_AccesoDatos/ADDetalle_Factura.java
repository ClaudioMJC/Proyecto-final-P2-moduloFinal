/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa3_AccesoDatos;

import Capa_Entidades.Detalle_Factura;
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
public class ADDetalle_Factura {
    
     private Connection _cnn;
    private String _mensaje;

    public ADDetalle_Factura() throws Exception {
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
      
    //01/10/2023
      
    public int InsertarDetalleFactura(Detalle_Factura detalleFactura) throws Exception {
        int idDetalleFactura = -1;
        String sentencia = "INSERT INTO DETALLE_FACTURAS (ID_FACTURA, ID_PRODUCTO, CANTIDAD) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, detalleFactura.getIdFactura());
            ps.setInt(2, detalleFactura.getIdProducto());
            ps.setInt(3, detalleFactura.getCantidad());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                idDetalleFactura = rs.getInt(1);
                _mensaje = "Detalle de factura ingresado satisfactoriamente";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return idDetalleFactura;
    }

      
    
    public List<Detalle_Factura> ListarRegistros(String condicion) throws Exception {
        ResultSet rs = null;
        List<Detalle_Factura> lista = new ArrayList<>();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_FACTURA, ID_PRODUCTO, CANTIDAD FROM DETALLE_FACTURAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Detalle_Factura(
                        rs.getInt("ID_FACTURA"),
                        rs.getInt("ID_PRODUCTO"),
                        rs.getInt("CANTIDAD")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }

    
    public Detalle_Factura ObtenerRegistro(String condicion) throws Exception {
        Detalle_Factura detalleFactura = new Detalle_Factura();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_FACTURA, ID_PRODUCTO, CANTIDAD FROM DETALLE_FACTURAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);

            if (rs.next()) {
                detalleFactura.setIdFactura(rs.getInt("ID_FACTURA"));
                detalleFactura.setIdProducto(rs.getInt("ID_PRODUCTO"));
                detalleFactura.setCantidad(rs.getInt("CANTIDAD"));

                detalleFactura.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return detalleFactura;
    }

    
    
    public int ModificarDetalleFactura(Detalle_Factura detalleFactura) throws Exception {
        int resultado = 0;
        String sentencia = "UPDATE DETALLE_FACTURAS SET ID_PRODUCTO=?, CANTIDAD=? WHERE ID_FACTURA=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, detalleFactura.getIdProducto());
            ps.setInt(2, detalleFactura.getCantidad());
            ps.setInt(3, detalleFactura.getIdFactura());
            resultado = ps.executeUpdate();
            if (resultado > 0) {
                _mensaje = "Registro de Detalle de Factura modificado satisfactoriamente";
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            _cnn = null;
        }
        return resultado;
    }

//    public int EliminarDetalleFactura(Detalle_Factura detalleFactura) throws Exception {
//        int resultado = 0;
//        String sentencia = "DELETE FROM DETALLE_FACTURA WHERE ID_FACTURA=?";
//        try {
//            PreparedStatement ps = _cnn.prepareStatement(sentencia);
//            ps.setInt(1, detalleFactura.getIdFactura());
//            resultado = ps.executeUpdate();
//            if (resultado > 0) {
//                _mensaje = "Registro de Detalle de Factura eliminado satisfactoriamente";
//            }
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            _cnn = null;
//        }
//        return resultado;
//    }
    
    
    
   

      
    
    
}
