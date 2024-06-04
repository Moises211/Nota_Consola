/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logica;

import com.Dao.GruposDao;
import com.Dao.MateriasDao;
import com.Modelo.Grupos;
import com.conexion.Conexion;
import java.util.*;

/**
 *
 * @author USER NVIDIA
 */
public class GruposLogica {

    private Scanner scann = new Scanner(System.in);
    private Conexion conn = new Conexion();
    private Grupos grup = new Grupos();
    private GruposDao grupDao = new GruposDao(conn);
    private MateriasDao matDao = new MateriasDao(conn);
    private LinkedList<Grupos> list;

    public GruposLogica() {
    }

    public void crearGrupos(String codigoMateria) {
        int cantidad;
        int tamano;
        System.out.println("Ingrese la cantidad de grupos a crear");
        cantidad = scann.nextInt();
        tamano = grupDao.findByCodiMateria(codigoMateria).size();
        cantidad = cantidad + tamano;
        for (int i = tamano; i < cantidad; i++) {
            grup.setCodigoGrupo(i + 1);
            grup.setCodigoMateria(codigoMateria);
            if (grupDao.insert(grup)) {
                System.out.println("Grupo #: " + (i + 1) + " Creados");
            }
        }
        conn.closeConexion();
    }

    public void mostrarGrupos(String codigoMateria) {
        try {
            list = new LinkedList<>();
            list = grupDao.findByCodiMateria(codigoMateria);
            String codigoGrupoMateria;
            System.out.println(list.get(0).getNombreMateria());
            for (int i = 0; i < list.size(); i++) {
                codigoGrupoMateria = list.get(i).getCodigoMateria();
                codigoGrupoMateria += String.format("%02d", list.get(i).getCodigoGrupo());
                System.out.println("Codigo: " + codigoGrupoMateria);
            }                                
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        conn.closeConexion();
    }
}
