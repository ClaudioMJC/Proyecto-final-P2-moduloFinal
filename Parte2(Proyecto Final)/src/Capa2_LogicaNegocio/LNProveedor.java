/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa2_LogicaNegocio;

import Capa3_AccesoDatos.ADProveedor;
import Capa_Entidades.Proveedor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class LNProveedor {

    private String _mensaje;

    // Métodos de acceso GET
    public String getMensaje() {
        return _mensaje;
    }

    //llamar a insertar
    public int Insertar(Proveedor proveedor) throws Exception {
        int id = -1;
        ADProveedor adproveedor;

        try {
            adproveedor = new ADProveedor();
            id = adproveedor.Insertar(proveedor);
            _mensaje = adproveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

    // Listar Registros de Proveedores
    public List<Proveedor> ListarRegistros(String condicion) throws Exception {
        List<Proveedor> resultado = new ArrayList<>();
        ADProveedor adProveedor;
        try {
            adProveedor = new ADProveedor();
            resultado = adProveedor.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

// Obtener Registro de Proveedor
    public Proveedor ObtenerRegistro(String condicion) throws Exception {
        Proveedor resultado;
        ADProveedor adProveedor;
        try {
            adProveedor = new ADProveedor();
            resultado = adProveedor.ObtenerRegistro(condicion);
            if (resultado.isExiste()) {
                _mensaje = "Proveedor recuperado exitosamente";
            } else {
                _mensaje = "El Proveedor no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

// Modificar Proveedor
    public int Modificar(Proveedor proveedor) throws Exception {
        int resultado = -1;
        ADProveedor adProveedor;
        try {
            adProveedor = new ADProveedor();
            resultado = adProveedor.Modificar(proveedor);
            _mensaje = adProveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

// Eliminar Proveedor
    public int Eliminar(Proveedor proveedor) throws Exception {
        int resultado = -1;
        ADProveedor adProveedor;
        try {
            adProveedor = new ADProveedor();
            resultado = adProveedor.Eliminar(proveedor);
            _mensaje = adProveedor.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

}
