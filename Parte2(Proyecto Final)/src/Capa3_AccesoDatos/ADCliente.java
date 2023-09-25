/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa3_AccesoDatos;

import Capa_Entidades.Cliente;
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
public class ADCliente {
    
    private Connection _cnn;
    private String _mensaje;

    //Constructor
    public ADCliente() throws Exception {
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
      //Método para Insertar un cliente
    
    
    public int Insertar(Cliente cliente) throws Exception {
        int id_cliente = -1;
        String sentencia = "Insert into Clientes (nombre,apellido,direccion,telefono) values (?,?,?,?)";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            
            
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {

                id_cliente = rs.getInt(1);
                _mensaje = "Cliente ingresado satisfactoriamente";

            }
        } catch (Exception e) {
            throw e;

        } finally {
            _cnn = null;
        }
        return id_cliente;
    }

    
    //Listar Registros
    
    public ResultSet ListarRegistros(String condicion, String orden) throws SQLException {
        ResultSet res = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_cliente,nombre,apellido,direccion,telefono from clientes";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);

            }

            if (!orden.equals("")) {
                sentencia = String.format("%s order by s", sentencia, orden);

            }
            res = stm.executeQuery(sentencia);
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return res;
    }

    //usando list
    public List<Cliente> ListarRegistros(String condicion) throws SQLException {
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_cliente,nombre,apellido,direccion,telefono from clientes";
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("id_cliente"), rs.getString("nombre"), rs.getString("apellido"), rs.getString("direccion"), rs.getString("telefono")));

            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }
        return lista;
    }
      
   
    
    
         //OBTENER REGISTRO
     
     public Cliente ObtenerRegistro(String condicion) throws Exception {
        Cliente cliente = new Cliente();
        ResultSet rs = null;
        try {
            Statement stm = _cnn.createStatement();
            String sentencia = "Select id_cliente, nombre, apellido, direccion, " + "telefono from clientes";
            if (!condicion.equals("")) {

                sentencia = String.format("%s where %s", sentencia, condicion);

            }
            rs = stm.executeQuery(sentencia);

            if (rs.next()) {
                cliente.setIdCliente(rs.getInt(1));
                cliente.setNombre(rs.getString(2));
                cliente.setApellido(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
                
                cliente.setExiste(true);

            }
        } catch (Exception e) {
            throw e;
        } finally {

            _cnn = null;

        }
        return cliente;

    }
     
     
     
     //MODIFICAR CLIENTE
    public int Modificar(Cliente cliente) throws Exception {
        int resultado = 0;
        String sentencia = "update Clientes set nombre=?,apellido=?,direccion=?,telefono=? where id_cliente=?";
        try {
            PreparedStatement ps = _cnn.prepareStatement(sentencia);
            ps.setString(1, cliente.getNombre());
             ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
             ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getIdCliente());
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
       
     
    
      //Eliminar Cliente
     
     public int Eliminar (Cliente cliente)throws Exception{
     
     int resultado = 0;
     String sentencia="delete Clientes where id_cliente=?";
         try {
             PreparedStatement ps=_cnn.prepareStatement(sentencia);
             ps.setInt(1, cliente.getIdCliente());
             resultado = ps.executeUpdate();
             if (resultado>0) {
                 _mensaje="Registro eliminado satisfactoriamente";
                 
             }
         } catch (Exception ex) {
             throw ex;
         }finally{
             _cnn=null;
         }
         return resultado;
     }//fin método eliminar
       
       
    
    
}
