/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa3_AccesoDatos;

import Capa_Entidades.Factura;
import Config.Config;
import java.sql.CallableStatement;
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
public class ADFactura {
    
    private Connection _cnn;
    private String _mensaje;
    
    
     public ADFactura() throws Exception {
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
    
        public int InsertarFactura(Factura factura) throws Exception {
        int idFactura = -1;
        String sentencia = "INSERT INTO FACTURAS (ID_CLIENTE, ID_USUARIO, FECHA, ESTADO, TOTAL_VENTA) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdUsuario());
            ps.setDate(3, new java.sql.Date(factura.getFecha().getTime())); //basicamente convertimos un objeto java.until.Date en un objeto java.sql.Date para que sea compatible con la base de datos y se pueda insertar correctamente
            ps.setString(4, factura.getEstado());
            ps.setDouble(5, factura.getTotalVenta());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                idFactura = rs.getInt(1);
                _mensaje = "Factura ingresada satisfactoriamente";
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return idFactura;
    }
     
     //LISTAR 
    public List<Factura> ListarRegistros(String condicion) throws Exception {
        ResultSet rs = null;
        List<Factura> lista = new ArrayList<>();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT ID_FACTURA, ID_CLIENTE, ID_USUARIO, FECHA, ESTADO, TOTAL_VENTA FROM FACTURAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Factura(
                        rs.getInt("ID_FACTURA"),
                        rs.getInt("ID_CLIENTE"),
                        rs.getInt("ID_USUARIO"),
                        rs.getDate("FECHA"),
                        rs.getString("ESTADO"),
                        rs.getDouble("TOTAL_VENTA")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }
    
    
    public Factura ObtenerRegistro(String condicion) throws Exception {
        Factura factura = new Factura();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "SELECT id_factura, id_cliente, id_usuario, fecha, estado, total_venta FROM FACTURAS";
            if (!condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, condicion);
            }
            rs = stm.executeQuery(sentencia);

            if (rs.next()) {
                factura.setIdFactura(rs.getInt(1));
                factura.setIdCliente(rs.getInt(2));
                factura.setIdUsuario(rs.getInt(3));
                factura.setFecha(rs.getDate(4));
                factura.setEstado(rs.getString(5));
                factura.setTotalVenta(rs.getDouble(6));

                factura.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return factura;
    }

    
    public int ModificarFactura(Factura factura) throws Exception {
        int resultado = 0;
        String sentencia = "UPDATE FACTURAS SET id_cliente=?, id_usuario=?, fecha=?, estado=?, total_venta=? WHERE id_factura=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, factura.getIdCliente());
            ps.setInt(2, factura.getIdUsuario());
            ps.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            ps.setString(4, factura.getEstado());
            ps.setDouble(5, factura.getTotalVenta());
            ps.setInt(6, factura.getIdFactura());
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

    public int EliminarFactura(Factura factura) throws Exception {
        int resultado = 0;
        String sentencia = "DELETE FROM FACTURAS WHERE ID_FACTURA=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setInt(1, factura.getIdFactura());
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


    
    
//    public List<Integer> obtenerIdUsuarios() throws Exception {
//        List<Integer> listaUsuarios = new ArrayList<>();
//        ResultSet rs = null;
//
//        try {
//            String sentencia = "SELECT ID_USUARIO FROM USUARIOS";
//            PreparedStatement ps = _cnn.prepareStatement(sentencia);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                int idUsuario = rs.getInt("ID_USUARIO");
//                listaUsuarios.add(idUsuario);
//            }
//
//        } catch (Exception e) {
//            throw e;
//        }
//
//        return listaUsuarios;
//    }
    public List<Integer> obtenerIdUsuarios() throws Exception {
        List<Integer> listaUsuarios = new ArrayList<>();
        ResultSet rs = null;

        try {
            String sentencia = "SELECT ID_USUARIO FROM USUARIOS";
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            rs = ps.executeQuery();

            while (rs.next()) {
                int idUsuario = rs.getInt("ID_USUARIO");
                listaUsuarios.add(idUsuario);
            }

        } catch (Exception e) {
            throw e;
        }

        return listaUsuarios;
    }


    
    
      
      
      //Este método implementa el uso de un procedimiento almacenado
    
//    public int Eliminar(int idFactura) throws Exception {
//        int resultado = 0;
//        String sentencia = "{call EliminarFacturaConDetalles(?)}"; // Cambia el nombre del procedimiento almacenado si es diferente
//
//        try (CallableStatement callableStatement = _cnn.prepareCall(sentencia)) {
//            callableStatement.setInt(1, idFactura);  //callbleStatement es utilizada para invocar procedimientos almacenados en una base de datos
//            resultado = callableStatement.executeUpdate();
//
//            if (resultado > 0) {
//                _mensaje = "Factura eliminada satisfactoriamente";
//            }
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            // Cierra recursos, si es necesario
//        }
//
//        return resultado;
//    }

    
    
    
    
}
