/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logica;

import com.Dao.*;
import com.Modelo.*;
import com.conexion.Conexion;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author USER NVIDIA
 */
public class AlumnosPorGrupoLogica {

    private Scanner scann;
    private Conexion conn = new Conexion();
    private AlumnosPorGrupo alumPG = new AlumnosPorGrupo();
    private Alumnos alum = new Alumnos();
    private Grupos grup = new Grupos();
    private AlumnosPorGrupoDao alumPGDao;
    private LinkedList<AlumnosPorGrupo> list;
    private int idGrupo = 0;
    private int cont = 0;
    private String codigoMateria = "";

    public AlumnosPorGrupoLogica() {
    }

    public void insertarAlumnos_Grupo(String codGrupo) {
        decodificar(codGrupo);
        alumPGDao = new AlumnosPorGrupoDao(conn);
        System.out.println("Ingrese el Carnet de Alumno");
        alum.setCodigoCarnet(scann.nextLine());
        if (alumPGDao.findByIdAlumno2(getCodigoMateria(), alum.getCodigoCarnet()).isEmpty()) {
            grup.setCodigoGrupo(getIdGrupo());
            grup.setCodigoMateria(getCodigoMateria());
            alumPG.setAlumnos(alum);
            alumPG.setGrupos(grup);
            alumPGDao.insert(alumPG);
        } else {
            System.out.println("El alumno ya esta registrado en un grupo de la materia");
        }
        conn.closeConexion();
    }

    public boolean mostrarAlumnos_Grupo(String codGrupo) {
        decodificar(codGrupo);
        alumPGDao = new AlumnosPorGrupoDao(conn);
        list = new LinkedList<>();
        list = alumPGDao.findById(getCodigoMateria(), getIdGrupo());
        if (!list.isEmpty()) {
            System.out.print("Alumnos en: " + list.get(0).getGrupos().getNombreMateria());
            System.out.println(" - Grupo #: " + codGrupo);
            for (int i = 0; i < list.size(); i++) {                
                System.out.print("Alumno: " + list.get(i).getAlumnos().getNombreAlumno());
                System.out.println(". Carnet: " + list.get(i).getAlumnos().getCodigoCarnet());
            }
            conn.closeConexion();
            return true;
        } else {
            System.out.println("No hay alumnos en este grupo");
            conn.closeConexion();
            return false;
        }
        
    }

    public void decodificar(String codGrupo) {
        int idGrupo = 0;
        int cont = 0;
        String idGrupoTransitorio = "";
        String codigoMateria = "";
        scann = new Scanner(System.in);
        char[] cadena = codGrupo.toCharArray();
        scann = new Scanner(System.in);
        for (int i = 0; i < cadena.length; i++) {
            if (Character.isDigit(cadena[i])) {
                if (cont++ > 3) {
                    idGrupoTransitorio += cadena[i];
                } else {
                    codigoMateria += cadena[i];
                }
            } else {
                codigoMateria += cadena[i];
            }
        }
        idGrupo = Integer.parseInt(idGrupoTransitorio);
        setCodigoMateria(codigoMateria);
        setIdGrupo(idGrupo);
    }

    protected int getIdGrupo() {
        return idGrupo;
    }

    protected void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    protected String getCodigoMateria() {
        return codigoMateria;
    }

    protected void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }
}
