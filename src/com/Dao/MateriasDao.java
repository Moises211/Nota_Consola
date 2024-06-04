/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;

import com.Modelo.Materias;
import com.conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.*;
import java.util.LinkedList;

/**
 *
 * @author USER NVIDIA
 */
public class MateriasDao {
    Conexion conn = new Conexion();
    Materias mats;
    String sql;
    LinkedList<Materias> list;
    PreparedStatement ps;
    ResultSet rs;

    public MateriasDao(Conexion con) {
        this.conn = con;
    }
    
    public boolean insert(Materias materia){
        sql = "INSERT INTO MATERIAS VALUES(?,?)";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, materia.getCodigoMateria());
            ps.setString(2, materia.getNombreMateria());
            ps.executeUpdate();
            conn.closeConexion();
            return true;
        }catch (SQLException e) {
            System.out.println("Error al insertar" + e);
            conn.closeConexion();
            return false;
        }
    }
    
    public LinkedList<Materias> findAll(){
    sql = "SELECT * FROM MATERIAS";
        try {
            ps = conn.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            
            list = new LinkedList<>();
            while(rs.next()){
                mats = new Materias();
                mats.setCodigoMateria(rs.getString("codigoMateria"));
                mats.setNombreMateria(rs.getString("nombreMateria"));
                list.add(mats);
            }      
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listas: " + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public LinkedList<Materias> findByPK(String codigo){
    sql = "SELECT * FROM MATERIAS WHERE CODIGOMATERIA = ?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            
            list = new LinkedList<>();
            while(rs.next()){    
                mats = new Materias();
                mats.setCodigoMateria(rs.getString("codigoMateria"));
                mats.setNombreMateria(rs.getString("nombreMateria"));
                //System.out.println(rs.getString("nombreMateria"));
                list.add(mats);                
            }        
            if (list.isEmpty()) {                
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listas: " + e);
            conn.closeConexion();
            return null;
        }
    }
    
}
