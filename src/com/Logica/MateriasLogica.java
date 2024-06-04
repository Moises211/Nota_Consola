/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logica;

import com.Dao.MateriasDao;
import com.Modelo.Materias;
import com.conexion.Conexion;
import java.util.*;

/**
 *
 * @author USER NVIDIA
 */
public class MateriasLogica {

    private Scanner scann = new Scanner(System.in);
    private Conexion conn = new Conexion();
    private Materias mat = new Materias();
    private MateriasDao matDao = new MateriasDao(conn);
    private StringTokenizer st;//De una cadena obtiene cada palabra de la cadena, creando subcandenas
    private LinkedList<Materias> list;

    public MateriasLogica() {

    }

    public void crearMaterias() {
        String nombre;
        String palabra;
        String año;
        int cont;
        System.out.println("Ingrese los datos solicitados");
        System.out.print("Nombre de la materia: ");
        nombre = scann.nextLine();
        System.out.print("Año de la materia: ");
        año = scann.nextLine();
        mat.setNombreMateria(nombre);
        st = new StringTokenizer(nombre);
        nombre = "";
        cont = st.countTokens();
        while (st.hasMoreTokens()) {
            palabra = st.nextToken();
            if (palabra.length() > 3 && cont > 3) {
                nombre = nombre + palabra.charAt(0);
            }
            if (cont <= 3) {
                nombre = nombre + palabra.charAt(0);
            }
        }
        /*Diseño y programacion de Bases de Datos
            Logica de Programacion*/
        mat.setCodigoMateria(nombre.toUpperCase() + año);                
        if (matDao.findByPK(mat.getCodigoMateria()).isEmpty()) {
            if (matDao.insert(mat)) {
                System.out.println("Se ingresaron los datos con exito");
            }
        } else {
            System.out.println("La materia ya se a ingresado");
        }
        conn.closeConexion();
    }

    public void MostrarMaterias() {
        System.out.println("Materias en Lista");
        list = new LinkedList<>();
        list = matDao.findAll();
        for (int i = 0; i < list.size(); i++) {
            System.out.print((i + 1) + " " + list.get(i).getCodigoMateria());
            System.out.println("-" + list.get(i).getNombreMateria());
        }
        conn.closeConexion();
    }
    
}
