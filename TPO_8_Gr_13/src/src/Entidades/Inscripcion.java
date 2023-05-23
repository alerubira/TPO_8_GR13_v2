/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Entidades;

/**
 *
 * @author Gabriel
 */
public class Inscripcion {
    private int idInscripcion;
    private Alumno al;
    private Materia ma;
    private double nota;
    public Inscripcion(int idInscripcion, Alumno al, Materia ma, int nota) {
        this.idInscripcion = idInscripcion;
        this.al = al;
        this.ma = ma;
        this.nota = nota;
    }
    

    public Inscripcion() {
    }

    public Inscripcion(Alumno al, Materia ma, double nota) {
        this.al = al;
        this.ma = ma;
        this.nota = nota;
    }

    public double getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public Alumno getAl() {
        return al;
    }

    public void setAl(Alumno al) {
        this.al = al;
    }

    public Materia getMa() {
        return ma;
    }

    public void setMa(Materia ma) {
        this.ma = ma;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    
    
}
