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
public class Ponderaciones extends Materias{
    private int idponderacion;
    private double ponderacion;
    private String descripcionPonderacion;
    private Materias codigoMateria;
    
    public Ponderaciones() {
        super();
    }

    public int getIdponderacion() {
        return idponderacion;
    }

    public void setIdponderacion(int idponderacion) {
        this.idponderacion = idponderacion;
    }

    public double getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(double ponderacion) {
        this.ponderacion = ponderacion;
    }

    public String getDescripcionPonderacion() {
        return descripcionPonderacion;
    }

    public void setDescripcionPonderacion(String descripcionPonderacion) {
        this.descripcionPonderacion = descripcionPonderacion;
    }    

    public void setCodigoMateria(Materias codigoMateria) {
        this.codigoMateria = codigoMateria;
    }
    
    
}
