/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidades;

/**
 *
 * @author Claudio
 */
public class Producto {
    
     private int id_producto;
    private int id_proveedor;
    private String descripcion;
    private double precioCompra;
    private double precioVenta;
    private boolean existe;

    // Constructor vacío
    public Producto() {
        id_producto = 0;
        id_proveedor = 0;
        descripcion = "";
        precioCompra = 0.0;
        precioVenta = 0.0;
        existe = false;
    }

    // Constructor con parámetros
    public Producto(int id_producto, int id_proveedor, String descripcion, double precioCompra, double precioVenta) {
        this.id_producto = id_producto;
        this.id_proveedor = id_proveedor;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.existe = true;
    }
    
    // Métodos getter y setter
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
    
    
    
    
    
}
