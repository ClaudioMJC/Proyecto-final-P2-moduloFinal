/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa2_LogicaNegocio;

import Capa3_AccesoDatos.ADProducto;
import Capa_Entidades.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class LNProducto {
    
    
    private String _mensaje;

    // Métodos de acceso GET
    public String getMensaje() {
        return _mensaje;
    }
    
    //se llama al método de combobox para mostrar el id de los proveedores junto con la descripción
    
    public List<String> obtenerProveedoresDesdeLogica() throws Exception {
        ADProducto adProducto = new ADProducto();
        List<String> listaProveedores = adProducto.obtenerProveedores();
        return listaProveedores;
    }
    
    
     public List<Integer> obtenerIdProveedores() throws Exception {
        ADProducto adProducto = new ADProducto();
        List<Integer> listaIdProveedores = adProducto.obtenerIdProveedores();
        return listaIdProveedores;
    }
    
    
    // Llamar a insertar Producto
public int Insertar(Producto producto) throws Exception {
    int id = -1;
    ADProducto adproducto;

    try {
        adproducto = new ADProducto();
        id = adproducto.Insertar(producto);
        _mensaje = adproducto.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return id;
}


// Llamar a listar registros de Producto
public List<Producto> ListarRegistros(String condicion) throws Exception {
    List<Producto> resultado = new ArrayList<>();
    ADProducto adproducto;

    try {
        adproducto = new ADProducto();
        resultado = adproducto.ListarRegistros(condicion);
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}


// Llamar a obtener registro de Producto
public Producto ObtenerRegistro(String condicion) throws Exception {
    Producto resultado;
    ADProducto adproducto;

    try {
        adproducto = new ADProducto();
        resultado = adproducto.ObtenerRegistro(condicion);
        if (resultado.isExiste()) {
            _mensaje = "Producto Recuperado exitosamente";
        } else {
            _mensaje = "El Producto no existe";
        }
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}


// Llamar a modificar Producto
public int Modificar(Producto producto) throws Exception {
    int resultado = -1;
    ADProducto adproducto;

    try {
        adproducto = new ADProducto();
        resultado = adproducto.Modificar(producto);
        _mensaje = adproducto.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}


// Llamar a eliminar Producto
public int Eliminar(Producto producto) throws Exception {
    int resultado = -1;
    ADProducto adproducto;

    try {
        adproducto = new ADProducto();
        resultado = adproducto.Eliminar(producto);
        _mensaje = adproducto.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}

    
    
    
    
    
    
}
