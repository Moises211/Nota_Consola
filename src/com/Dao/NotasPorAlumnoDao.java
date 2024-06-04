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
public class NotasPorAlumnoDao {

    private Conexion conn = new Conexion();
    private NotasPorAlumno notPA;
    private AlumnosPorGrupo alumPG;
    private PonderacionesDao pondDao;
    private AlumnosPorGrupoDao alumPGDao;
    private MateriasDao matDao;
    private LinkedList<NotasPorAlumno> list;
    private ResultSet rs;
    String sql;
    private PreparedStatement ps;

    public NotasPorAlumnoDao(Conexion conn) {
        this.conn = conn;
    }

    public boolean insert(NotasPorAlumno notas) {
        sql = "INSERT INTO notasporalumno values(?,?,?,?,?)";
        try {
            alumPGDao = new AlumnosPorGrupoDao(conn);
            pondDao = new PonderacionesDao(conn);
            ps = conn.conectar().prepareStatement(sql);
            String codigoCarnet = notas.getAlumnosPorGrupo().getAlumnos().getCodigoCarnet();
            String codigoMateria = notas.getCodigoMateria();
            int idGrupo = notas.getAlumnosPorGrupo().getGrupos().getCodigoGrupo();
            int idPonderacion = notas.getIdponderacion();
            boolean comprobar = (!alumPGDao.findByIdAlumno(codigoMateria, idGrupo, codigoCarnet).isEmpty()) && (!pondDao.findAll(idPonderacion, codigoMateria).isEmpty());            
            if (comprobar) {                
                ps.setString(1, codigoMateria);
                ps.setInt(2, idGrupo);
                ps.setInt(3, idPonderacion);
                ps.setString(4, codigoCarnet);
                ps.setDouble(5, notas.getNota());
                ps.executeUpdate();
                System.out.println("Nota ingresada con exito");
                conn.closeConexion();
                return true;
            } else {
                conn.closeConexion();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al ingresar los datos" + e);
            conn.closeConexion();
            return false;
        }
    }

    public LinkedList<NotasPorAlumno> findByIdAlumno(String carnet) {
        sql = "SELECT notasporalumno.*, SUM(((nota*10)/100)*(ponderacion/100)*100) as notas from notasporalumno,ponderaciones where codigoCarnet=? and "
                + "notasporalumno.codigoMateria=? and ponderaciones.codigoMateria=?"
                + "and ponderaciones.idponderacion = notasporalumno.idPonderacion";
        try {
            //hay que crear una busqueda en AlumnosPorGrupo Dao donde
            //se busque el alumno solo en un grupo y asi obtener el dato del codigo de la materia
            String codigoMateria;
            list = new LinkedList<>();
            //list = null;
            matDao = new MateriasDao(conn);
            alumPGDao = new AlumnosPorGrupoDao(conn);
            //System.out.println(matDao.findAll().size());
            for (int i = 0; i < matDao.findAll().size(); i++) {
                codigoMateria = matDao.findAll().get(i).getCodigoMateria();
                String nombrMateria = matDao.findAll().get(i).getNombreMateria();
                ps = conn.conectar().prepareStatement(sql);
                ps.setString(1, carnet);
                ps.setString(2, codigoMateria);
                ps.setString(3, codigoMateria);
                rs = ps.executeQuery();
                int contador = 0;
                boolean comprobar;
                while (rs.next()) {
                    contador++;
                    comprobar = alumPGDao.findByIdAlumno(codigoMateria, rs.getInt("codigoGrupo"), carnet).isEmpty();
                    //System.out.println(contador +" " + comprobar);
                    if (!alumPGDao.findByIdAlumno(codigoMateria, rs.getInt("codigoGrupo"), carnet).isEmpty()) {
                        alumPG = new AlumnosPorGrupo();
                        notPA = new NotasPorAlumno();
                        alumPG = alumPGDao.findByIdAlumno(codigoMateria, rs.getInt("codigoGrupo"), carnet).getFirst();                        
                        notPA.setAlumnosPorGrupo(alumPG);
                        notPA.setNombreMateria(nombrMateria);
                        notPA.setNota(rs.getDouble("notas"));
                        list.add(notPA);
                    }
                }
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            conn.closeConexion();
            return null;
        }
    }

    public LinkedList<NotasPorAlumno> findByIdMateria(String idMateria, int idGrupo) {
        sql = "SELECT notasporalumno.* , SUM(((nota*10)/100)*(ponderacion/100)*100) as notas from notasporalumno,ponderaciones "
                + "where notasporalumno.codigoMateria = ? and notasporalumno.codigoGrupo = ? and notasporalumno.codigoCarnet=? "
                + "and ponderaciones.codigoMateria=? "
                + "and ponderaciones.idponderacion = notasporalumno.idPonderacion";
        try {
            alumPGDao = new AlumnosPorGrupoDao(conn);
            matDao = new MateriasDao(conn);
            list = new LinkedList<>();
            for (int i = 0; i < alumPGDao.findById(idMateria, idGrupo).size(); i++) {
                String codigoAlumno = alumPGDao.findById(idMateria, idGrupo).get(i).getAlumnos().getCodigoCarnet();
                String nombrMateria = matDao.findByPK(idMateria).getLast().getNombreMateria();
                ps = conn.conectar().prepareStatement(sql);
                ps.setString(1, idMateria);
                ps.setInt(2, idGrupo);
                ps.setString(3, codigoAlumno);
                ps.setString(4, idMateria);
                rs = ps.executeQuery();
                while (rs.next()) {
                    alumPG = new AlumnosPorGrupo();
                    notPA = new NotasPorAlumno();
                    alumPG = alumPGDao.findByIdAlumno(idMateria, idGrupo, codigoAlumno).getFirst();
                    notPA.setAlumnosPorGrupo(alumPG);
                    notPA.setNombreMateria(nombrMateria);
                    notPA.setNota(rs.getDouble("notas"));
                    list.add(notPA);
                }

            }
            conn.closeConexion();
            return list;

        } catch (SQLException e) {
            System.out.println(e);
            conn.closeConexion();
            return null;
        }
    }

    public LinkedList<NotasPorAlumno> findByAll(String idMateria, int idGrupo, String carnet) {
        sql = "SELECT notasporalumno.*  from notasporalumno "
                + "where codigoMateria = ? and codigoGrupo = ? and codigoCarnet=? ";

        try {
            alumPGDao = new AlumnosPorGrupoDao(conn);
            matDao = new MateriasDao(conn);
            list = new LinkedList<>();
            ps = conn.conectar().prepareStatement(sql);
            ps.setString(1, idMateria);
            ps.setInt(2, idGrupo);
            ps.setString(3, carnet);
            rs = ps.executeQuery();
            while (rs.next()) {
                alumPG = new AlumnosPorGrupo();
                notPA = new NotasPorAlumno();
                alumPG = alumPGDao.findByIdAlumno(idMateria, idGrupo, carnet).getFirst();
                notPA.setAlumnosPorGrupo(alumPG);
                notPA.setNota(rs.getDouble("nota"));
                list.add(notPA);
            }
            conn.closeConexion();
            return list;
        } catch (SQLException e) {
            System.out.println(e);
            conn.closeConexion();
            return null;
        }
    }
}
