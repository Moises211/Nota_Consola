/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logica;

import java.io.Console;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER NVIDIA
 */
public class Principal {

    static MateriasLogica matL = new MateriasLogica();
    static GruposLogica grupL = new GruposLogica();
    static PonderacionesLogica pondL = new PonderacionesLogica();
    static AlumnosLogica alumL = new AlumnosLogica();
    static AlumnosPorGrupoLogica alumPGL = new AlumnosPorGrupoLogica();
    static NotasPorAlumnoLogica notPAL = new NotasPorAlumnoLogica();
    static Scanner scann;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal prin = new Principal();
        int entrada;
        scann = new Scanner(System.in);
        System.out.println("*\\Bienvenido a Gestion de Calificaciones/*");
        do {
            prin.datosMenu();
            entrada = Integer.parseInt(scann.next());
            prin.limpiarPantalla();
            switch (entrada) {
                case 1://menu de materia                    
                    prin.Gestionar_materias();
                    break;
                case 2://menu alumno
                    prin.Gestionar_Alumnos();
                    break;
                case 3://menu calificaciones
                    prin.Gestionar_Notas();
                    break;
                case 4://Salir                                  
                    System.out.println("Tenga un buen dia.");
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (Exception e) {
                    }
                    break;
            }
        } while (entrada != 4);
    }    

    public void datosMenu() {
        System.out.println("Seleccione la entrada");
        System.out.println("1.Gestionar Materias");
        System.out.println("2.Gestionar Alumno");
        System.out.println("3.Gestionar notas");
        System.out.println("4.Salir");
    }

    public void limpiarPantalla() {
        try {
            Thread.sleep(500);
            String sistemaOperativo = System.getProperty("os.name");
            ArrayList<String> comando = new ArrayList<String>();
            if (sistemaOperativo.contains("Windows")) {
                comando.add("cmd");
                comando.add("/C");
                comando.add("cls");
            } else {
                comando.add("clear"); //UNIX => MAC, LINUX
            }
            ProcessBuilder pb = new ProcessBuilder(comando);
            Process iniciarProceso = pb.inheritIO().start();
            iniciarProceso.waitFor();

        } catch (Exception e) {
            System.err.println("Error al limpiar la pantalla" + e.getMessage());
        }
    }

    private void Gestionar_materias() {
        limpiarPantalla();
        System.out.println("Gestion de Materias");
        System.out.println("Selecciona una opcion: ");
        System.out.println("1.Crear Materias");
        System.out.println("2.Crear Grupos");
        System.out.println("3.Crear Ponderaciones");
        System.out.println("4.Volver a menu principal");
        scann = new Scanner(System.in);        
        int opcion = Integer.parseInt(scann.nextLine());
        limpiarPantalla();
        switch (opcion) {
            case 1:
                crear_Materias();                
                break;
            case 2:
                crear_grupos();
                break;
            case 3:
                crear_Ponderaciones();
                break;
        }
    }
      
    private void Gestionar_Alumnos() {
        limpiarPantalla();
        System.out.println("Gestion de Alumnos");
        System.out.println("Selecciona una opcion: ");
        System.out.println("1.Crear Alumno");
        System.out.println("2.Asignar Alumno a grupo");
        System.out.println("3.Volver a menu principal");
        scann = new Scanner(System.in);
        scann = new Scanner(System.in);        
        int opcion = Integer.parseInt(scann.nextLine());
        limpiarPantalla();
        switch (opcion) {
            case 1:
                crear_Alumno();
                break;
            case 2:
                Alumnos_Grupo();
                break;
        }
    }

    private void Gestionar_Notas() {
        limpiarPantalla();
        System.out.println("Gestion de Notas");
        System.out.println("Selecciona una opcion: ");
        System.out.println("1.Ingresar Calificaciones");
        System.out.println("2.Ver Resumen de calificaciones por grupo");
        System.out.println("3.Ver Resumen de calificaciones por alumno");
        System.out.println("4.Volver a menu principal");
        scann = new Scanner(System.in);
        scann = new Scanner(System.in);        
        int opcion = Integer.parseInt(scann.nextLine());
        limpiarPantalla();
        switch (opcion) {
            case 1:
                IngresarNotas();
                break;
            case 2:
                NotasPorMateria();
                break;
            case 3:
                Notas_Alumno();
                break;
        }
    }

    private void crear_Materias() {
        matL.crearMaterias();
    }

    private void crear_grupos() {
        scann = new Scanner(System.in);
        matL.MostrarMaterias();
        System.out.println("Ingrese el codigo de la materia");
        grupL.crearGrupos(scann.nextLine());
    }

    private void crear_Ponderaciones() {
        scann = new Scanner(System.in);
        matL.MostrarMaterias();
        System.out.println("Ingrese el codigo de la materia");
        pondL.crearPonderaciones(scann.nextLine());
    }

    private void crear_Alumno() {
        alumL.crearAlumnos();
    }

    private void Alumnos_Grupo() {
        String codGrupo;
        Scanner scann = new Scanner(System.in);
        matL.MostrarMaterias();
        System.out.println("Ingrese el codigo de la materia");
        codGrupo = scann.nextLine();
        grupL.mostrarGrupos(codGrupo);
        System.out.println("Ingrese el codigo de del grupo");
        codGrupo = scann.nextLine();
        alumPGL.insertarAlumnos_Grupo(codGrupo);
    }

    private void Notas_Alumno() {
        Scanner scann = new Scanner(System.in);
        System.out.println("Ingrese el codigo del Alumno");
        notPAL.mostrarPorAlumno(scann.nextLine());
    }

    private void NotasPorMateria() {
        String codGrupo;
        Scanner scann = new Scanner(System.in);
        matL.MostrarMaterias();
        System.out.println("Ingrese el codigo de la materia");
        codGrupo = scann.nextLine();
        grupL.mostrarGrupos(codGrupo);
        System.out.println("Ingrese el codigo de del grupo");
        codGrupo = scann.nextLine();
        notPAL.mostrarPorMateria(codGrupo);
    }

    public void IngresarNotas() {
        String codGrupo;
        Scanner scann = new Scanner(System.in);
        matL.MostrarMaterias();
        System.out.println("Ingrese el codigo de la materia");
        codGrupo = scann.nextLine();
        grupL.mostrarGrupos(codGrupo);
        System.out.println("Ingrese el codigo de del grupo");
        codGrupo = scann.nextLine();
        notPAL.insertarNotas(codGrupo);
    }
}
