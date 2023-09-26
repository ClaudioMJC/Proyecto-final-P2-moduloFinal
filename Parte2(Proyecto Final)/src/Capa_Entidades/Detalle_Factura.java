/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidades;

/**
 *
 * @author Claudio
 */
public class Detalle_Factura {
    
    private int idFactura;
    private int idProducto;
    private int cantidad;
    private boolean existe;
    
      public Detalle_Factura() {
        idFactura = 0;
        idProducto = 0;
        cantidad = 0;
        existe = false;
    }

    public Detalle_Factura (int idFactura, int idProducto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        existe= true;
    }
    
    
     public boolean isExiste() {
        return existe;
    }
     public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
