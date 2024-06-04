/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logica;

import com.Dao.*;
import com.Modelo.*;
import com.Modelo.Grupos;
import com.conexion.Conexion;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author USER NVIDIA
 */
public class NotasPorAlumnoLogica extends AlumnosPorGrupoLogica {

    private Scanner scann;
    private Conexion conn = new Conexion();
    private AlumnosPorGrupo alumPG = new AlumnosPorGrupo();
    private Alumnos alum = new Alumnos();
    private Grupos grup = new Grupos();
    private NotasPorAlumno notPA = new NotasPorAlumno();
    private MateriasDao matDao;
    private PonderacionesDao pondDao;
    //private AlumnosPorGrupoDao alumPGDao;
    private NotasPorAlumnoDao notPADao;
    private LinkedList<NotasPorAlumno> list;

    public void insertarNotas(String codGrupo) {
        decodificar(codGrupo);
        notPADao = new NotasPorAlumnoDao(conn);
        pondDao = new PonderacionesDao(conn);
        scann = new Scanner(System.in);
        if (mostrarAlumnos_Grupo(codGrupo)) {
            System.out.println("Ingrese el carnet del alumno");
            String codigo = scann.nextLine();
            list = new LinkedList<>();
            list = notPADao.findByAll(getCodigoMateria(), getIdGrupo(), codigo);
            if (list.isEmpty()) {
                for (int i = 0; i < pondDao.findByCodigo(getCodigoMateria()).size(); i++) {
                    notPA.setIdponderacion(pondDao.findByCodigo(getCodigoMateria()).get(i).getIdponderacion());
                    //System.out.println(notPA.getIdponderacion());
                    alum.setCodigoCarnet(codigo);
                    grup.setCodigoGrupo(getIdGrupo());
                    alumPG.setAlumnos(alum);
                    alumPG.setGrupos(grup);
                    notPA.setCodigoMateria(getCodigoMateria());
                    notPA.setAlumnosPorGrupo(alumPG);
                    System.out.print(pondDao.findByCodigo(getCodigoMateria()).get(i).getDescripcionPonderacion() + ". ");
                    System.out.print("Nota: ");
                    notPA.setNota(Double.parseDouble(scann.nextLine()));
                    System.out.println("");
                    if(!notPADao.insert(notPA)){
                        System.out.println("El alumno no pertenece al grupo");
                        break;
                    }
                }
            } else {
                System.out.println("El alumno ya posee notas");
            }
            conn.closeConexion();
        }
    }

    public void mostrarPorAlumno(String codAlumno) {
        //decodificar(codGrupo);
        notPADao = new NotasPorAlumnoDao(conn);
        list = new LinkedList<>();
        list = notPADao.findByIdAlumno(codAlumno);
        if (!list.isEmpty()) {
            System.out.println("Alumno: " + list.get(0).getAlumnosPorGrupo().getAlumnos().getNombreAlumno());
            for (int i = 0; i < list.size(); i++) {
                System.out.print("Materia: " + list.get(i).getNombreMateria());
                System.out.println(". Promedio : " + list.get(i).getNota() + "/100");
            }
        } else {
            System.out.println("El alumno no cuenta con notas");
        }
        conn.closeConexion();
    }

    public void mostrarPorMateria(String codGrupo) {
        decodificar(codGrupo);
        notPADao = new NotasPorAlumnoDao(conn);
        list = new LinkedList<>();
        list = notPADao.findByIdMateria(getCodigoMateria(), getIdGrupo());
        if (!list.isEmpty()) {
            System.out.println("Materia: " + list.get(0).getNombreMateria());
            System.out.println("Notas en el grupo # :" + list.get(0).getAlumnosPorGrupo().getGrupos().getCodigoGrupo());
            for (int i = 0; i < list.size(); i++) {
                System.out.print("Nombre: " + list.get(i).getAlumnosPorGrupo().getAlumnos().getNombreAlumno());
                System.out.println(". Promedio: " + list.get(i).getNota() + "/100");
            }
        } else {
            System.out.println("No hay notas registradas en el grupo");
        }
        conn.closeConexion();
    }
}
