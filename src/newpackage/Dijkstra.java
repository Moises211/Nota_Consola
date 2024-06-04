/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.util.*;

/**
 *
 * @author USER NVIDIA
 */
public class Dijkstra {

    public int[] distancia = new int[10];//creamos donde se guardaran los arcos
    public int[][] costo = new int[10][10];//el peso de estos arcos

    public void calc(int n, int s) {
        int[] bandera = new int[n + 1];
        int i, minpos = 1, k, c, minima;

        for (i = 1; i <= n; i++) {//todo esto calcula la distancia optima entre cada nodo y forma el grafo
            bandera[i] = 0;
            this.distancia[i] = this.costo[s][i];
        }
        c = 2;
        while (c <= n) {
            minima = 99;
            for (k = 1; k <= n; k++) {
                if (this.distancia[k] < minima && bandera[k] != 1) {
                    minima = this.distancia[i];
                    minpos = k;
                }
            }
            bandera[minpos] = 1;
            c++;
            for (k = 1; k <= n; k++) {
                if (this.distancia[minpos] + this.costo[minpos][k] < this.distancia[k] && bandera[k] != 1) {
                    this.distancia[k] = this.distancia[minpos] + this.costo[minpos][k];
                }
            }
        }

    }

    public static void main(String args[]) {
        int nodos, posicion, i, j;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el número de nodos ");
        nodos = sc.nextInt();
        Dijkstra d = new Dijkstra();
        System.out.println("Ingrese los pesos de la matriz de costos: ");
        for (i = 1; i <= nodos; i++) {
            for (j = 1; j <= nodos; j++) {
                if (i != j) {
                    System.out.println("costo "+ j+" a destino " + i + " :");
                    d.costo[i][j] = sc.nextInt();
                }
                /*if (j > 1) {
                    d.costo[i][j] = 0;
                }*/
                if (d.costo[i][j] == 0) {
                    d.costo[i][j] = 999;
                }
            }
        }
        System.out.println("Ingrese la posicion de origen :\n");
        posicion = sc.nextInt();

        d.calc(nodos, posicion);
        System.out.println("El camino más corto desde la posicion \t" + posicion + "\t a todos los demás puntos son : \n");
        for (i = 1; i <= nodos; i++) {
            if (i != posicion) {
                System.out.println("posicion :" + posicion + "\t destino :" + i + "\t Costo minimo es :" + d.distancia[i] + "\t");
            }
        }

    }
}
