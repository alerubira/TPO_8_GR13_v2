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
public class Materia {
    private int id_Materia;
    private String nombre;
    private boolean estado;
    private int anio;
    public Materia(int id_Materia, String nombre, boolean estado, int anio) {
        this.id_Materia = id_Materia;
        this.nombre = nombre;
        this.estado = estado;
        this.anio = anio;
    }

    public Materia() {
    }

    public Materia(String nombre, boolean estado, int anio) {
        this.nombre = nombre;
        this.estado = estado;
        this.anio = anio;
    }
    

    public int getId_Materia() {
        return id_Materia;
    }

    public void setId_Materia(int id_Materia) {
        this.id_Materia = id_Materia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Override
    public String toString() {
        return "Materia{" + "id_Materia=" + id_Materia + ", nombre=" + nombre + ", estado=" + estado + ", anio=" + anio + '}';
    }
    
    
}
