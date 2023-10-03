/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Capa3_AccesoDatos;

import Config.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Claudio
 */
public class ADCierre {

    private Connection _cnn;
    private String _mensaje;

    //Constructor
    public ADCierre() throws Exception {
        try {
            String url = Config.getConnectionString();
            _cnn = DriverManager.getConnection(url);
            _mensaje = "";
        } catch (Exception e) {
            throw e;
        }
    }

    //Getter
    public String getMensaje() {
        return _mensaje;
    }

    public double calcularVentasPorFecha(String fechaConsulta) throws Exception {
        double totalVentas = 0.0;

        try {
            String sql = "SELECT SUM(TOTAL_VENTA) AS TOTAL_VENTAS FROM FACTURAS WHERE FECHA = ?";

            PreparedStatement pstmt = _cnn.prepareStatement(sql);
            pstmt.setString(1, fechaConsulta);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                totalVentas = rs.getDouble("TOTAL_VENTAS");
            }

            rs.close();
            pstmt.close();
        } catch (Exception e) {
            throw e;
        }

        return totalVentas;
    }

}
