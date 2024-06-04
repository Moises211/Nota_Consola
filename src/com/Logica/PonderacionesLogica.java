/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logica;

import com.Dao.*;
import com.Modelo.Ponderaciones;
import com.conexion.Conexion;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author USER NVIDIA
 */
public class PonderacionesLogica {

    private Scanner scann;
    private Conexion conn = new Conexion();
    private Ponderaciones pond = new Ponderaciones();
    private PonderacionesDao pondDao;    
    private LinkedList<Ponderaciones> list;
    private double tamano = 0;
    private int contador = 1;

    public PonderacionesLogica() {

    }

    public int crearPonderaciones(String codigoMateria) {
        pondDao = new PonderacionesDao(conn);
        list = new LinkedList<>();
        scann = new Scanner(System.in);
        if (pondDao.findSum(codigoMateria) < 100) {                        
                pond.setCodigoMateria(codigoMateria);
                pond.setIdponderacion(contador);
                System.out.print("Porcentaje: ");
                pond.setPonderacion(Double.parseDouble(scann.nextLine()));
                System.out.print("Descripcion: ");
                scann = new Scanner(System.in);
                pond.setDescripcionPonderacion(scann.nextLine());
                pondDao.insert(pond);
                contador++;
                crearPonderaciones(codigoMateria);
                conn.closeConexion();
                return contador;                                 
        }else{
            System.out.println("100% de ponderacion ingresado");
            conn.closeConexion();
         return 0;
        }
    }

    public void mostrarPonderaciones(String codigoMateria) {
        list = new LinkedList<>();
        list = pondDao.findByCodigo(codigoMateria);
        System.out.println(list.get(0).getNombreMateria());
        for (int i = 0; i < list.size(); i++) {
            System.out.print("-" + list.get(i).getDescripcionPonderacion());
            System.out.printf("-" + list.get(i).getPonderacion() + "%n");
        }
        conn.closeConexion();
    }
}
