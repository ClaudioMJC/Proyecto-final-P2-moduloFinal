/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa2_LogicaNegocio;

import Capa3_AccesoDatos.ADGasto;
import Capa_Entidades.Gasto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class LNGasto {
    
     private String _mensaje;

    // Métodos de acceso GET
    public String getMensaje() {
        return _mensaje;
    }
    
    // Llamar a insertar un Gasto
    public int Insertar(Gasto gasto) throws Exception {
        int id = -1;
        ADGasto adGasto;

        try {
            adGasto = new ADGasto();
            id = adGasto.InsertarGasto(gasto);
            _mensaje = adGasto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return id;
    }

// Llamar a listar registros de Gasto
    public List<Gasto> ListarRegistros(String condicion) throws Exception {
        List<Gasto> resultado = new ArrayList<>();
        ADGasto adGasto;
        try {
            adGasto = new ADGasto();
            resultado = adGasto.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

// Llamar a obtener un registro de Gasto
    public Gasto ObtenerRegistro(String condicion) throws Exception {
        Gasto resultado;
        ADGasto adGasto;
        try {
            adGasto = new ADGasto();
            resultado = adGasto.ObtenerRegistro(condicion);
            if (resultado.isExiste()) {
                _mensaje = "Gasto Recuperado exitosamente";
            } else {
                _mensaje = "El Gasto no existe";
            }
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

// Llamar a modificar un Gasto
    public int Modificar(Gasto gasto) throws Exception {
        int resultado = -1;
        ADGasto adGasto;

        try {
            adGasto = new ADGasto();
            resultado = adGasto.ModificarGasto(gasto);
            _mensaje = adGasto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

// Llamar a eliminar un Gasto
    public int Eliminar(Gasto gasto) throws Exception {
        int resultado = -1;
        ADGasto adGasto;

        try {
            adGasto = new ADGasto();
            resultado = adGasto.EliminarGasto(gasto);
            _mensaje = adGasto.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

}
