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
public class Gasto {
    
     private int idGasto;
    private Date fecha;
    private double monto;
    private String descripcion;
    private boolean existe;

    // Constructor vacío con atributos
    public Gasto() {
        idGasto = 0; // Valor predeterminado para idGasto
        fecha = null; // Fecha actual por defecto
        monto = 0.0; // Valor predeterminado para monto
        descripcion = "";
        existe = false; // Por defecto, el registro no existe
    }

    // Constructor con parámetros
    public Gasto(int idGasto, Date fecha, double monto, String descripcion) {
        this.idGasto = idGasto;
        this.fecha = fecha;
        this.monto = monto;
        this.descripcion = descripcion;
        existe = true; // El registro existe cuando se crea con datos
    }
    
     // Getter y Setter para idGasto
    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    // Getter y Setter para fecha
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Getter y Setter para monto
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    // Getter y Setter para descripcion
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getter y Setter para existe
    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
    
}
