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
import java.util.StringTokenizer;
/*
D: DATA 
A: ACCESS 
O: OBJECT
/*
/**
 *
 * @author USER NVIDIA
 */
public class AlumnosLogica {
    private Scanner scann = new Scanner(System.in);
    private Conexion conn = new Conexion();
    private Alumnos alum = new Alumnos();
    private AlumnosDao alumDao = new AlumnosDao(conn);
    private StringTokenizer st;//De una cadena obtiene cada palabra de la cadena, creando subcandenas
    private LinkedList<Alumnos> list;

    public AlumnosLogica() {
    }
    
    public void crearAlumnos(){
        String iniciales = "";
        String año;
        String palabra;
        int cont, i;
        System.out.println("Ingrese los datos solicitados");
        System.out.print("Nombre: ");
        alum.setNombreAlumno(scann.nextLine());
        System.out.print("Año de ingreso: ");
        año = scann.nextLine();
        st = new StringTokenizer(alum.getNombreAlumno());
        cont = st.countTokens();
        i = 1;        
        while(st.hasMoreTokens()){
            palabra = st.nextToken();
            if (i > (cont - 2)) {
                iniciales = iniciales + palabra.charAt(0);               
            }
            i++;
        } 
               
        iniciales = iniciales + año.substring(2);
        cont = alumDao.findCantidad(iniciales)+1;
        alum.setCodigoCarnet(iniciales + String.format("%03d", cont) );
        alumDao.insert(alum);
        conn.closeConexion();
    }
    
    public void mostrarAlumnos(){
        System.out.println("Alumnos: ");
        list = new LinkedList<>();
        list = alumDao.findAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i+1)+" " +list.get(i).getNombreAlumno());
            System.out.println("-"+list.get(i).getCodigoCarnet());
        }
        conn.closeConexion();
    }        
}


