/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import src.Entidades.Alumno;
import src.Entidades.Inscripcion;
import src.Entidades.Materia;

/**
 *
 * @author EQUIPO
 */
public class InscripcionData {
    private Connection con = null;
    private MateriaData materiaData;
    private AlumnoData alumnoData;

    public InscripcionData() {
        con = ConeccionData.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion inscripcion){
        String sql = "INSERT INTO inscripcion (idmateria, idalumno, nota) VALUES (?, ?, 0)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, inscripcion.getMa().getId_Materia());
            ps.setInt(2, inscripcion.getAl().getId_Alumno());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                JOptionPane.showMessageDialog(null, "Inscripcion realizada con exito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
        }
    }
    /*
    public List<Inscripcion> obtenerInscripciones(){
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion WHERE  ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                Alumno al new Alumno();
                Materia ma new Materia();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setNota(rs.getDouble("nota"));
                inscripcion.setAlumno(al.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMateria(ma.buscarMateria(rs.getInt("idMateria"));
                inscripciones.add(inscripcion);
            }
            ps.close();
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Inscripcion "+ex.getMessage());
        }
        return inscripciones;
        
    }
    
    public List<Inscripcion> obtenerInscripcionPorAlumno(int id){
    List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion WHERE idAlumno = ?  ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ps.setInt(1,id);
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                Alumno al new Alumno();
                Materia ma new Materia();
                inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                inscripcion.setNota(rs.getDouble("nota"));
                inscripcion.setAlumno(al.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMateria(ma.buscarMateria(rs.getInt("idMateria"));
                inscripciones.add(inscripcion);
            }
            ps.close();
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Inscripcion "+ex.getMessage());
        }
        return inscripciones;
        
    }
    
    public List<Inscripcion> obtenerMateriasCursadas(int id){
       
        return;
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int id){
        return
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        
    }
    
    public void actualizarNota(int idAlumno, int idMateria, double nota){
       
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria){
        return
    }
 */
}
