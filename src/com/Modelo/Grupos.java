/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Modelo;

/**
 *
 * @author USER NVIDIA
 */
public class Grupos extends Materias{
    private int CodigoGrupo;
    private String codigoMaterias;
    
    public Grupos() {
        super();
    }

    public int getCodigoGrupo() {
        return CodigoGrupo;
    }

    public void setCodigoGrupo(int CodigoGrupo) {
        this.CodigoGrupo = CodigoGrupo;
    }

    public String getCodigoMaterias() {
        return codigoMaterias;
    }

    public void setCodigoMaterias(String codigoMateria) {
        this.codigoMaterias = codigoMateria;
    }           
}
