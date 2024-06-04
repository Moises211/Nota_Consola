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
public class NotasPorAlumno extends Ponderaciones{
    private AlumnosPorGrupo alumnosPorGrupo;
    private Ponderaciones ponderaciones;
    private Double nota;

    public NotasPorAlumno() {
    
    }   

    public AlumnosPorGrupo getAlumnosPorGrupo() {
        return alumnosPorGrupo;
    }

    public void setAlumnosPorGrupo(AlumnosPorGrupo alumnosPorGrupo) {
        this.alumnosPorGrupo = alumnosPorGrupo;
    }
    
    public Ponderaciones getPonderaciones() {
        return ponderaciones;
    }

    public void setPonderaciones(Ponderaciones ponderaciones) {
        this.ponderaciones = ponderaciones;
    }        

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
