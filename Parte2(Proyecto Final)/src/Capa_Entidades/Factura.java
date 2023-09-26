/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa_Entidades;

import java.sql.Date;

/**
 *
 * @author Claudio
 */
public class Factura {
    
  
    private int idFactura;
    private int idCliente;
    private int idUsuario;
    private Date fecha;
    private String estado;
    private double totalVenta;
    private boolean existe;

    // Constructor vacío
    public Factura() {
        idFactura = 0;
        idCliente = 0;
        idUsuario = 0;
        fecha = null;
        estado = "";
        totalVenta = 0.0;
        existe = false;
    }

    // Constructor con parámetros
    public Factura(int idCliente, int idUsuario, Date fecha, String estado, double totalVenta) {
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.estado = estado;
        this.totalVenta = totalVenta;
        this.existe = true;
    }
    
    
    // Getters y setters de los atributos
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
}
