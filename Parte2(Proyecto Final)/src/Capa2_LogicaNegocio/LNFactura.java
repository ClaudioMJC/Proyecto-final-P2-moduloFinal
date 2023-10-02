/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa2_LogicaNegocio;

import Capa3_AccesoDatos.ADFactura;
import Capa_Entidades.Factura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class LNFactura {
    private String _mensaje;

    // Métodos de acceso GET
    public String getMensaje() {
        return _mensaje;
    }
    
    public int InsertarFactura(Factura factura) throws Exception {
    int id = -1;
        ADFactura aDFactura;

    try {
        aDFactura = new ADFactura();
        id = aDFactura.InsertarFactura(factura);
        _mensaje = aDFactura.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return id;
}


// Llamar a listar registros de Producto
public List<Factura> ListarRegistros(String condicion) throws Exception {
    List<Factura> resultado = new ArrayList<>();
    ADFactura aDFactura;

    try {
        aDFactura = new ADFactura();
        resultado = aDFactura.ListarRegistros(condicion);
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}


// Llamar a obtener registro de Producto
public Factura ObtenerRegistro(String condicion) throws Exception {
    Factura resultado;
    ADFactura adFactura;

    try {
        adFactura = new ADFactura();
        resultado = adFactura.ObtenerRegistro(condicion);
        if (resultado.isExiste()) {
            _mensaje = "Factura Recuperada exitosamente";
        } else {
            _mensaje = "La Factura no existe";
        }
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}


// Llamar a modificar Producto
public int ModificarFactura(Factura factura) throws Exception {
    int resultado = -1;
    ADFactura aDFactura;

    try {
        aDFactura = new ADFactura();
        resultado = aDFactura.ModificarFactura(factura);
        _mensaje = aDFactura.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}


// Llamar a eliminar Producto
public int EliminarFactura(Factura factura) throws Exception {
    int resultado = -1;
    ADFactura aDFactura;

    try {
        aDFactura = new ADFactura();
        resultado = aDFactura.EliminarFactura(factura);
        _mensaje = aDFactura.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}


 public List<Integer> obtenerIdUsuarios() throws Exception {
        ADFactura adFactura = new ADFactura();
        List<Integer> listaIdUsuarios = adFactura.obtenerIdUsuarios();
        return listaIdUsuarios;
    }
    
}
