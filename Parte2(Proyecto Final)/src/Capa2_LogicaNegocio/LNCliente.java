/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa2_LogicaNegocio;

import Capa3_AccesoDatos.ADCliente;
import Capa_Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class LNCliente {
    
    private String _mensaje;

    // Métodos de acceso GET
    public String getMensaje() {
        return _mensaje;
    }
    
    //llamar a insertar
    public int Insertar(Cliente cliente) throws Exception {
        int id = -1;
        ADCliente adcliente;

        try {
            adcliente = new ADCliente();
            id = adcliente.Insertar(cliente);
            _mensaje = adcliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }
    
    
    
    //llamar listar resgistro
    
    public List<Cliente> ListarRegistros(String condicion) throws Exception {
        List<Cliente> resultado = new ArrayList();
        ADCliente adcliente;
        try {
            adcliente = new ADCliente();
            resultado = adcliente.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;

    }
    
    
    
    
      
        //llamar Obtener registro
        
        
        public Cliente ObtenerRegistro(String condicion) throws Exception {
            Cliente resultado;
            ADCliente adcliente;
             try {
                adcliente= new ADCliente();
                resultado=adcliente.ObtenerRegistro(condicion);
                 if (resultado.isExiste()) {
                     _mensaje="Cliente Recuperado existosamente";
                     
                 }else{
                 _mensaje="El Cliente no existe";
                 
                 }
            } catch (Exception e) {
                
                throw e;
            }
        return resultado;
      }
    
        
        
      //LLAMAR MODIFICAR
    public int Modificar(Cliente cliente) throws Exception {
        int resultado = -1;
        ADCliente adcliente;

        try {
            adcliente = new ADCliente();
            resultado = adcliente.Modificar(cliente);
            _mensaje = adcliente.getMensaje();

        } catch (Exception e) {
            throw e;

        }
        return resultado;
    }

    //LLamar Eliminar Cliente
    public int Eliminar(Cliente cliente) throws Exception {
        int resultado = -1;
        ADCliente adcCliente;
        try {
            adcCliente = new ADCliente();
            resultado = adcCliente.Eliminar(cliente);
            _mensaje = adcCliente.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

        
    
    
}
