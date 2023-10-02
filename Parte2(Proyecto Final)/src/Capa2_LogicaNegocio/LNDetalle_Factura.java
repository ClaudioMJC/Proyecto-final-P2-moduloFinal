/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa2_LogicaNegocio;

import Capa3_AccesoDatos.ADDetalle_Factura;
import Capa_Entidades.Detalle_Factura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Claudio
 */
public class LNDetalle_Factura {
     private String _mensaje;

    // Métodos de acceso GET
    public String getMensaje() {
        return _mensaje;
    }
    
    
    
    public int InsertarDetalleFactura(Detalle_Factura detalleFactura) throws Exception {
    int id = -1;
    ADDetalle_Factura adDetalleFactura;

    try {
        adDetalleFactura = new ADDetalle_Factura();
        id = adDetalleFactura.InsertarDetalleFactura(detalleFactura);
        _mensaje = adDetalleFactura.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return id;
}

// Llamar a listar registros de Detalle_Factura
public List<Detalle_Factura> ListarRegistros(String condicion) throws Exception {
    List<Detalle_Factura> resultado = new ArrayList<>();
    ADDetalle_Factura adDetalleFactura;

    try {
        adDetalleFactura = new ADDetalle_Factura();
        resultado = adDetalleFactura.ListarRegistros(condicion);
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}

// Llamar a obtener registro de Detalle_Factura
public Detalle_Factura ObtenerRegistro(String condicion) throws Exception {
    Detalle_Factura resultado;
    ADDetalle_Factura adDetalleFactura;

    try {
        adDetalleFactura = new ADDetalle_Factura();
        resultado = adDetalleFactura.ObtenerRegistro(condicion);
        if (resultado.isExiste()) {
            _mensaje = "Detalle de Factura recuperado exitosamente";
        } else {
            _mensaje = "El Detalle de Factura no existe";
        }
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}

// Llamar a modificar Detalle_Factura
public int ModificarDetalleFactura(Detalle_Factura detalleFactura) throws Exception {
    int resultado = -1;
    ADDetalle_Factura adDetalleFactura;

    try {
        adDetalleFactura = new ADDetalle_Factura();
        resultado = adDetalleFactura.ModificarDetalleFactura(detalleFactura);
        _mensaje = adDetalleFactura.getMensaje();
    } catch (Exception e) {
        throw e;
    }
    return resultado;
}

// Llamar a eliminar Detalle_Factura
//public int EliminarDetalleFactura(Detalle_Factura detalleFactura) throws Exception {
//    int resultado = -1;
//    ADDetalle_Factura adDetalleFactura;
//
//    try {
//        adDetalleFactura = new ADDetalle_Factura();
//        resultado = adDetalleFactura.EliminarDetalleFactura(detalleFactura);
//        _mensaje = adDetalleFactura.getMensaje();
//    } catch (Exception e) {
//        throw e;
//    }
//    return resultado;
//}

    
    
    
}
