/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author USER NVIDIA
 */
public class Conexion {
    private static Connection cnn;
    private static String user = "root";
    private static String pass = "usbw";
    private static String db = "calificaciones";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3307/"+db +"?autoReconnect=true&useSSL=false";
    
    /**
     * @param args the command line arguments
     */
    public Conexion(){
        this.cnn = null;        
    }
    
    public Connection conectar(){        
        try {
           Class.forName(driver);           
           cnn = DriverManager.getConnection(url, user, pass);  
            if (cnn!=null) {
               // System.out.println("Conectado a la base de datos");
            }
        } catch (ClassNotFoundException | SQLException e) {            
            System.out.println("Error al conectar : " + e.getMessage());
        }
        return cnn;
    }
    
    public void closeConexion(){        
        try {
            cnn.close();
            //System.out.println(cnn.isClosed());
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println("Ejecutando...");
        Conexion con = new Conexion();
        con.conectar();
        con.closeConexion();
    }  
}
