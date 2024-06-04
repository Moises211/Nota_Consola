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
public class AlumnosPorGrupoDao {

    private Conexion conn = new Conexion();
    private AlumnosPorGrupo alumPG;
    private Alumnos alum = new Alumnos();
    private Grupos grup = new Grupos();
    private AlumnosDao alumDao = new AlumnosDao(conn);
    private GruposDao grupDao = new GruposDao(conn);
    private LinkedList<AlumnosPorGrupo> list;
    private String sql;
    private PreparedStatement ps;
    private ResultSet rs;

    public AlumnosPorGrupoDao(Conexion conn) {
        this.conn = conn;
    }

    public boolean insert(AlumnosPorGrupo alumnoPG) {
        sql = "INSERT INTO alumnosporgrupo VALUES(?,?,?)";
        try {
            ps = conn.conectar().prepareStatement(sql);
            boolean comprovar;
            String carnetAlumno = alumnoPG.getAlumnos().getCodigoCarnet();
            String mateCarnet = alumnoPG.getGrupos().getCodigoMateria();
            int idGrupo = alumnoPG.getGrupos().getCodigoGrupo();
            //System.out.println("Materia:" + mateCarnet + " Id: " + idGrupo +"Alumno " + carnetAlumno);
            comprovar = (!grupDao.findByPk(mateCarnet, idGrupo).isEmpty()) && (!alumDao.findByPK(carnetAlumno).isEmpty());                        
            if (comprovar) {
                ps.setString(3, carnetAlumno);
                ps.setString(2, mateCarnet);
                ps.setInt(1, idGrupo);
                ps.executeUpdate();
                System.out.println("Registrado con exito");
                conn.closeConexion();
                return true;
            } else {
                System.out.println("Error no se puede insertar, verifique los datos");
                conn.closeConexion();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error no se pudo insetar " + e);
            conn.closeConexion();
            return false;
        }
    }

    public LinkedList<AlumnosPorGrupo> findAll() {
        sql = "SELECT * FROM alumnosporgrupo";
        try {
            ps = conn.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                alumPG = new AlumnosPorGrupo();
                alumDao.findByPK(rs.getString("codigoCarnet"));
                alum = alumDao.findByPK(rs.getString("codigoCarnet")).getFirst();
                alumPG.setAlumnos(alum);
                grup = grupDao.findByPk(rs.getString("codigoMateria"), rs.getInt("codigoGrupo")).getFirst();
                alumPG.setGrupos(grup);
                list.add(alumPG);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar los datos: " + e.getCause());
            conn.closeConexion();
            return null;
        }
    }

    public LinkedList<AlumnosPorGrupo> findById(String codigo, int ids) {
        sql = "SELECT * FROM alumnosporgrupo where codigoMateria = ? AND codigoGrupo =?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setInt(2, ids);
            rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                alumPG = new AlumnosPorGrupo();
                alumDao.findByPK(rs.getString("codigoCarnet"));
                alum = alumDao.findByPK(rs.getString("codigoCarnet")).getFirst();
                alumPG.setAlumnos(alum);
                grup = grupDao.findByPk(rs.getString("codigoMateria"), rs.getInt("codigoGrupo")).getFirst();
                alumPG.setGrupos(grup);
                list.add(alumPG);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar los datos: " + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public LinkedList<AlumnosPorGrupo> findByIdAlumno(String codigo, int ids, String carnet) {
        sql = "SELECT * FROM alumnosporgrupo where codigoMateria = ? AND codigoGrupo =? AND codigoCarnet=?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, codigo);
            ps.setInt(2, ids);
            ps.setString(3, carnet);
            rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                alumPG = new AlumnosPorGrupo();
                alumDao.findByPK(rs.getString("codigoCarnet"));
                alum = alumDao.findByPK(rs.getString("codigoCarnet")).getFirst();
                alumPG.setAlumnos(alum);
                grup = grupDao.findByPk(rs.getString("codigoMateria"), rs.getInt("codigoGrupo")).getFirst();
                alumPG.setGrupos(grup);
                list.add(alumPG);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar los datos: " + e);
            conn.closeConexion();
            return null;
        }
    }
    
    public LinkedList<AlumnosPorGrupo> findByIdAlumno2(String codigo, String carnet) {
        sql = "SELECT * FROM alumnosporgrupo where codigoMateria = ? AND codigoCarnet=?";
        try {
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, codigo);            
            ps.setString(2, carnet);
            rs = ps.executeQuery();
            list = new LinkedList<>();
            while (rs.next()) {
                alumPG = new AlumnosPorGrupo();
                alumDao.findByPK(rs.getString("codigoCarnet"));
                alum = alumDao.findByPK(rs.getString("codigoCarnet")).getFirst();
                alumPG.setAlumnos(alum);
                grup = grupDao.findByPk(rs.getString("codigoMateria"), rs.getInt("codigoGrupo")).getFirst();
                alumPG.setGrupos(grup);
                list.add(alumPG);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println("Error al listar los datos: " + e);
            conn.closeConexion();
            return null;
        }
    }
}
