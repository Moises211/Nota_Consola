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
import java.util.HashSet;
import java.util.LinkedList;

/**
 *
 * @author USER NVIDIA
 */
public class PonderacionesDao {

    private Conexion conn = new Conexion();
    private Ponderaciones pon;
    private MateriasDao matDao = new MateriasDao(conn);
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;
    private LinkedList<Ponderaciones> list;

    public PonderacionesDao(Conexion conn) {
        this.conn = conn;
    }

    public boolean insert(Ponderaciones ponderaciones) {
        sql = "INSERT INTO ponderaciones values(?,?,?,?)";
        try {
            ps = conn.conectar().prepareStatement(sql);
            String id = ponderaciones.getCodigoMateria();
            if(!(matDao.findByPK(id)==null)){                
                ps.setInt(1, ponderaciones.getIdponderacion());
                ps.setDouble(2, ponderaciones.getPonderacion());
                ps.setString(3, ponderaciones.getDescripcionPonderacion());
                ps.setString(4, ponderaciones.getCodigoMateria());
                ps.executeUpdate();
                System.out.println("Ingresado con exito");
                conn.closeConexion();
                return true;
            }else{
                System.out.println("El codigo de la materia no existe");
                conn.closeConexion();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al ingresar datos: " + e);
            conn.closeConexion();
            return false;
        }
    }

    public LinkedList<Ponderaciones> findAll() {
        sql = "SELECT * FROM ponderaciones";
        try {
            ps = conn.conectar().prepareStatement(sql);
            rs = ps.executeQuery();

            list = new LinkedList<>();
            while (rs.next()) {
                pon = new Ponderaciones();
                pon.setIdponderacion(rs.getInt("idPonderacion"));
                pon.setDescripcionPonderacion(rs.getString("descripcionPonderacion"));
                pon.setPonderacion(rs.getDouble("ponderacion"));
                pon.setCodigoMateria(rs.getString("codigoMateria"));
                pon.setNombreMateria(matDao.findByPK(pon.getCodigoMateria()).get(0).getNombreMateria());
                list.add(pon);
            }
            conn.closeConexion();
            return list;
        } catch (Exception e) {
            System.out.println("Error al listar los datos" + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public LinkedList<Ponderaciones> findAll(int ids, String carnet){
        sql = "SELECT * FROM ponderaciones where idPonderacion = ? and codigoMateria = ?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setInt(1, ids);
            ps.setString(2, carnet);
            rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                pon = new Ponderaciones();
                pon.setIdponderacion(rs.getInt("idPonderacion"));
                pon.setDescripcionPonderacion(rs.getString("descripcionPonderacion"));
                pon.setPonderacion(rs.getDouble("ponderacion"));
                pon.setCodigoMateria(rs.getString("codigoMateria"));
                pon.setNombreMateria(matDao.findByPK(pon.getCodigoMateria()).get(0).getNombreMateria());
                list.add(pon);
            }
            conn.closeConexion();
            return list;
        } catch (Exception e) {
            System.out.println("Error al listar los datos" + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public LinkedList<Ponderaciones> findByCodigo(String carnet){
        sql = "SELECT * FROM ponderaciones where codigoMateria = ?";
        try {
            ps = conn.conectar().prepareStatement(sql);           
            ps.setString(1, carnet);
            rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                pon = new Ponderaciones();
                pon.setIdponderacion(rs.getInt("idPonderacion"));
                pon.setDescripcionPonderacion(rs.getString("descripcionPonderacion"));
                pon.setPonderacion(rs.getDouble("ponderacion"));
                pon.setCodigoMateria(rs.getString("codigoMateria"));
                pon.setNombreMateria(matDao.findByPK(pon.getCodigoMateria()).get(0).getNombreMateria());
                list.add(pon);
            }
            conn.closeConexion();
            return list;
        } catch (Exception e) {
            System.out.println("Error al listar los datos" + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public double findSum(String carnet){
        double suma = 0;
        sql = "SELECT sum(ponderacion) as Sumas FROM ponderaciones where codigoMateria = ?";
        try {
            ps = conn.conectar().prepareStatement(sql);           
            ps.setString(1, carnet);
            rs = ps.executeQuery();            
            while (rs.next()) {                                          
                suma = suma + rs.getDouble("Sumas");
            }
            conn.closeConexion();
            return suma;
        } catch (Exception e) {
            System.out.println("Error al listar los datos" + e);
            conn.closeConexion();
            return 0;
        }
    }
}
