/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.*;
import java.util.*;

/**
 *
 * @author USER NVIDIA
 */
public class LecturaEscritura_FicherosPlanos {

    private static final Scanner scn = new Scanner(System.in);

    public void escritura(String linea) {
        try {
            FileWriter fichero = new FileWriter("texto.txt", true);
            BufferedWriter flujo = new BufferedWriter(fichero);
            PrintWriter escribir = new PrintWriter(flujo);
            escribir.println(linea);
            escribir.flush();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<String> leer() {
        ArrayList<String> texto = new ArrayList<>();

        try {
            FileReader fichero = new FileReader("texto.txt");
            BufferedReader leer = new BufferedReader(fichero);

            while (leer.ready()) {
                texto.add(leer.readLine());
            }
        } catch (IOException e) {
            System.err.printf("Error leer: ", e.getMessage());
            //printf para darle formato al texto en consola investiga al momento de usarlo de nuevo.
        }
        return texto;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LecturaEscritura_FicherosPlanos fichero = new LecturaEscritura_FicherosPlanos();
        // TODO code application logic here
        String linea;
        System.out.println("Elija escribir 'E' o Leer 'L'");
        linea = scn.nextLine();
        if (linea.toUpperCase().equals("E")) {
            scn.reset();
            System.out.println("Escriba");
            linea = scn.nextLine();
            fichero.escritura(linea);

        } else if (linea.toUpperCase().equals("L")) {
            //System.err.printf("Datos en fichero %-51s", linea);
            String[] texto = fichero.leer().toArray(args);
            LinkedList<String> list = new LinkedList<>();

            for (String texto1 : texto) {
                int longitud = texto1.length();
                int cont = longitud;
                String text;
                int num = 0;
                do {                    
                    if (longitud > 50) {
                        int rest = longitud - cont;        
                        int tern = ((cont<51)?50*num+cont:50*num+50);                        
                        //si es mayor de 50 la longitud hace salto de linea y continua con el mismo.                        
                        text = texto1.substring( rest, tern ) + ((texto1.charAt((tern-1))!=' '&& cont > 50)?"-":"");
                        System.out.printf("| %-" + 51 + "s", text).println("|" /*+ cont*/);
                        //contadores:
                        cont -= 50;                        
                        num += 1;
                    } else {
                        cont = 0;
                        System.out.printf("| %-" + 51 + "s", texto1).println("|" /*+ longitud*/);
                    }                    
                } while (cont > 0);                
            }
        }
    }

}
