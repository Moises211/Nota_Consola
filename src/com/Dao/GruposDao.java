/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Dao;
    import com.Modelo.*;
    import com.conexion.Conexion;
    import java.sql.PreparedStatement;
    import java.sql.*;
    import java.util.LinkedList;
/**
 *
 * @author USER NVIDIA
 */
public class GruposDao{
    Conexion conn = new Conexion();
    Grupos grup;  
    Materias mat = new Materias();
    MateriasDao matDao = new MateriasDao(conn);
    String sql;
    LinkedList<Grupos> list;
    PreparedStatement ps;
    ResultSet rs;
    
    public GruposDao(Conexion conn) {
        this.conn = conn;
    }
    
    public boolean insert(Grupos grupo){
        sql = "INSERT INTO GRUPOS VALUES(?,?)";
        try {
            ps = conn.conectar().prepareStatement(sql);
            
            String id = grupo.getCodigoMateria();
            if(!(matDao.findByPK(id)==null)){
                ps.setString(2, grupo.getCodigoMateria());
                ps.setInt(1, grupo.getCodigoGrupo());               
                ps.executeUpdate();
                return true;
            }else{
                System.out.println("El codigo de la materia no existe");
                conn.closeConexion();
                return false;
            }            
        } catch (SQLException e) {            
            System.out.println("Error al ingresar los datos: " + e);
            conn.closeConexion();
            return false;
        }        
    }           
    
    public LinkedList<Grupos> findAll(){
        sql = "SELECT * FROM GRUPOS";
        try {
            ps = conn.conectar().prepareStatement(sql);            
            rs = ps.executeQuery();
            
            list = new LinkedList<>();
            int i = 0;
            while (rs.next()) {
                grup = new Grupos();
                grup.setCodigoGrupo(rs.getInt("codigoGrupo"));                                
                grup.setCodigoMaterias(rs.getString("codigoMateria"));                                
                grup.setNombreMateria(matDao.findByPK(grup.getCodigoMaterias()).get(0).getNombreMateria());                                              
                list.add(grup);
            }         
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar datos" + e.getMessage());
            conn.closeConexion();
            return null;
        }
    }
    
    public LinkedList<Grupos> findByPk(String codigoMat, int codigoGrup){
        sql = "SELECT * FROM GRUPOS WHERE CODIGOMATERIA = ? AND CODIGOGRUPO=?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, codigoMat);
            ps.setInt(2, codigoGrup);
            rs = ps.executeQuery();
            
            list = new LinkedList<>();
            
            while (rs.next()) {   
                grup = new Grupos();
                grup.setCodigoGrupo(rs.getInt("codigoGrupo"));                                
                grup.setCodigoMaterias(rs.getString("codigoMateria"));                                
                grup.setNombreMateria(matDao.findByPK(grup.getCodigoMaterias()).get(0).getNombreMateria());                                              
                list.add(grup);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar datos" + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public LinkedList<Grupos> findByCodiMateria(String codigoMat){
        sql = "SELECT * FROM GRUPOS WHERE CODIGOMATERIA = ?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, codigoMat);            
            rs = ps.executeQuery();
            
            list = new LinkedList<>();
            
            while (rs.next()) {        
                grup = new Grupos();
                grup.setCodigoGrupo(rs.getInt("codigoGrupo"));                                
                grup.setCodigoMateria(rs.getString("codigoMateria"));                                
                grup.setNombreMateria(matDao.findByPK(grup.getCodigoMateria()).get(0).getNombreMateria());                                              
                list.add(grup);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar datos" + e);
            conn.closeConexion();
            return null;
        }
    }
    
}
