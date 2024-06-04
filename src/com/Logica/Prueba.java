/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logica;

import com.Dao.*;
import com.Modelo.*;
import com.conexion.Conexion;
import java.util.*;


/**
 *
 * @author USER NVIDIA
 */
public class Prueba {   
    /**
     * @param args the command line arguments
     */
    
    //Alumnos alum = new Alumnos();
    Grupos grup;    
    Conexion conn = new Conexion();
    //AlumnosDao alumDao = new AlumnosDao(conn);
    GruposDao  grupDao = new GruposDao(conn);
    int codigoGrup;
    String convert;
    public Prueba() {
    }
    
    /*public void insertAlumno(){        
        alum.setCodigoCarnet("VV22015");
        alum.setNombreAlumno("Kelvin Antonio Velasquez Vasquez");
        alumDao.insert(alum);
    }
    
    public void mostrarAlumnos(){    
        System.out.println("Lista de Alumno");
        LinkedList<Alumnos> list= alumDao.findAll();        
        for (int i = list.size()-1; i >= 0; i--) {            
            System.out.println("Carnet: "+ list.get(i).getCodigoCarnet());
            System.out.println("Nombre: "+ list.get(i).getNombreAlumno());
        }
    }
    
    public void mostrarCarnet(){    
        System.out.println("Lista de Carnet");
        LinkedList<Alumnos> list= alumDao.findAll();        
        for (int i = list.size()-1; i >= 0; i--) {            
            System.out.println("Carnet: "+ list.get(i).getCodigoCarnet());           
        }        
    }
    
    public void mostrarAlumnosPorCarnet(String Carnet){    
        System.out.println("Lista de Alumno con Carnet: " + Carnet);
        LinkedList<Alumnos> list= alumDao.findByPK(Carnet);        
        for (int i = list.size()-1; i >= 0; i--) {            
            System.out.println("Carnet: "+ list.get(i).getCodigoCarnet());           
            System.out.println("Nombre: "+ list.get(i).getNombreAlumno());
        }
        
    }*/
    
    public void insertAlumno(){        
        grup = new Grupos();
        grup.setCodigoGrupo(2);
        grup.setCodigoMateria("LGD2023");        
        grupDao.insert(grup);
    }
    
    public void mostrarAlumnos(){    
        System.out.println("Lista de Alumno");
        LinkedList<Grupos> list = grupDao.findAll();
        
        for (int i = list.size()-1; i >= 0; i--) {              
            codigoGrup = list.get(i).getCodigoGrupo();
            convert = String.format("%02d", codigoGrup);
            System.out.println("Materia: "+ list.get(i).getNombreMateria());
            System.out.println("Codigo Grupo: "+ list.get(i).getCodigoMaterias() +convert );
        }        
    }
    
    public void mostrarCarnet(){    
        System.out.println("Lista de Alumno");
        LinkedList<Grupos> list = grupDao.findAll();        
        for (int i = list.size()-1; i >= 0; i--) {  
            codigoGrup = list.get(i).getCodigoGrupo();
            convert = String.format("%02d", codigoGrup);
            System.out.println("Codigo Grupo: "+ list.get(i).getCodigoMaterias() +convert );
        }      
    }
    
    public void mostrarAlumnosPorCarnet(String codigoMat, int codigoGrup){    
        System.out.println("Lista de Alumno");        
        LinkedList<Grupos> list = grupDao.findByPk(codigoMat, codigoGrup);        
        for (int i = list.size()-1; i >= 0; i--) {  
            codigoGrup = list.get(i).getCodigoGrupo();
            convert = String.format("%02d", codigoGrup);
            System.out.println("Materia: "+ list.get(i).getNombreMateria());
            System.out.println("Codigo Grupo: "+ list.get(i).getCodigoMaterias() +convert );
        }        
    }
    
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        Prueba pre = new Prueba();        
        int entrada;
        String carnet;
        System.out.println("Para entrada: 1, para motrar 2");
        entrada = scann.nextInt();
        switch(entrada){                     
            case 1:                
                pre.insertAlumno();
                break;
            case 2: 
                pre.mostrarAlumnos();
                break;
            case 3:
                pre.mostrarCarnet();
                System.out.println("Ingrese el carnet");
                carnet = scann.next();    
                entrada = scann.nextInt();
                pre.mostrarAlumnosPorCarnet(carnet, entrada);
        }
    }
    
}
