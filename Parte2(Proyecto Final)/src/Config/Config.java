/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

/**
 *
 * @author Claudio
 */
public class Config {
    
     public static String getConnectionString()throws ClassNotFoundException{
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        return "jdbc:sqlserver://localhost;databasename=DB_PULPERIA;user=sa;password=sa";
    }
    
}
