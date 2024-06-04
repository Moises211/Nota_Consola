/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import com.Modelo.Alumnos;
import com.conexion.Conexion;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author USER NVIDIA
 */
public class AlumnosDao {
    Conexion conn = new Conexion();
    Alumnos alum;
    String sql;
    LinkedList<Alumnos> list;
    PreparedStatement ps;
    ResultSet rs;

    public AlumnosDao(Conexion conn) {
        this.conn = conn;
    }
    
    public boolean insert(Alumnos alumno){
        sql = "INSERT INTO alumnos VALUES(?,?)";
        try {
            ps = conn.conectar().prepareStatement(sql); 
            // alumno.getCodigoCarnet,el nombre de dato de entrada debe ser igual que el atributo pasado
            ps.setString(1, alumno.getCodigoCarnet());
            ps.setString(2, alumno.getNombreAlumno());            
            ps.executeUpdate();  
            System.out.println("Datos ingresado con exito");
            conn.closeConexion();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar alumno: " + e);
            conn.closeConexion();
            return false;
        }        
    }
    
    public LinkedList<Alumnos> findAll(){
        sql = "SELECT * FROM alumnos";
        try {
            ps = conn.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            
            list = new LinkedList<>();
            
            while (rs.next()) {
                alum = new Alumnos();
                alum.setCodigoCarnet(rs.getString("codigoCarnet"));
                alum.setNombreAlumno(rs.getString("nombreAlumno"));                               
                list.add(alum);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar los datos " + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public LinkedList<Alumnos> findByPK(String carnet){
        sql = "SELECT * FROM alumnos where CodigoCarnet = ? ";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, carnet);
            rs = ps.executeQuery();
            
            list = new LinkedList<>();
            
            while (rs.next()) {
                alum = new Alumnos();
                alum.setCodigoCarnet(rs.getString("codigoCarnet"));
                alum.setNombreAlumno(rs.getString("nombreAlumno"));                               
                list.add(alum);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar los datos " + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public int findCantidad(String carnet){
        int contador = 0;
        sql = "SELECT count( codigoCarnet ) as cuenta FROM alumnos WHERE codigoCarnet LIKE CONCAT(?,'%')";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, carnet);
            rs = ps.executeQuery();                       
            
            while (rs.next()) {     
                contador = rs.getInt("cuenta");
            }
            conn.closeConexion();
            return contador;
        } catch (SQLException e) {
            System.out.println("Error al listar los datos " + e);
            conn.closeConexion();
            return 0;
        }
    }
}
