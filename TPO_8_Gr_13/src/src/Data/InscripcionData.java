/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    public List<Inscripcion> obtenerInscripciones(){
        List<Inscripcion> inscripciones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM inscripcion WHERE 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                Alumno al = new Alumno();
                Materia ma = new Materia();
                AlumnoData ad = new AlumnoData();
                MateriaData md = new MateriaData();
                inscripcion.setIdInscripcion(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getDouble("nota"));
                inscripcion.setAl(ad.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMa(md.buscarMateria(rs.getInt("idMateria")));
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
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Inscripcion inscripcion = new Inscripcion();
                Alumno al = new Alumno();
                Materia ma = new Materia();
                MateriaData md = new MateriaData();
                AlumnoData ad = new AlumnoData();
                inscripcion.setIdInscripcion(rs.getInt("idInscripto"));
                inscripcion.setNota(rs.getDouble("nota"));
                inscripcion.setAl(ad.buscarAlumno(rs.getInt("idAlumno")));
                inscripcion.setMa(md.buscarMateria(rs.getInt("idMateria")));
                inscripciones.add(inscripcion);
            }
            ps.close();
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Inscripcion "+ex.getMessage());
        }
        return inscripciones;
        
    }
    
    public List<Materia> obtenerMateriasCursadas(int id){
       List<Materia> materias = new ArrayList<>();
        try {
            String sql = "SELECT M.* FROM alumno A JOIN inscripcion I ON(A.idAlumno=I.idAlumno) JOIN materia M on (I.idMateria=M.idMateria) WHERE A.idAlumno = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
               Materia m = new Materia();
               
               m.setId_Materia(rs.getInt("idMateria"));
               m.setNombre(rs.getString("nombre"));
               m.setAnio(rs.getInt("año"));
            
                materias.add(m);
            }
            ps.close();
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia "+ex.getMessage());
        }
        return materias;
        
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int id){
    List<Materia> materias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM materia  WHERE idMateria not in (SELECT materia.idMateria FROM materia join inscripcion on(materia.idMateria=inscripcion.idMateria) WHERE idAlumno = ?) ";

            PreparedStatement ps = con.prepareStatement(sql);
             ps.setInt(1,id);
             ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia m = new Materia();
               m.setId_Materia(rs.getInt("idMateria"));
               m.setNombre(rs.getString("nombre"));
               m.setAnio(rs.getInt("año"));
            
                materias.add(m);
            }
            ps.close();
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia "+ex.getMessage());
        }
        return materias;
        

        
    }
 
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
         String sql = "DELETE FROM `inscripcion` WHERE idAlumno=? AND idMateria=?";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,idAlumno);
            ps.setInt(2, idMateria);
            int exito = ps.executeUpdate();
            System.out.println(exito);
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Inscripcion Borrada Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La Inscripcion no existe");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
        }
    }

    public void actualizarNota(int idAlumno, int idMateria, double nota){
        String sql = " UPDATE `inscripcion` SET `nota`= ? WHERE idAlumno = ? AND idMateria=?;";
    
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1,nota);
            ps.setInt(2,idAlumno);
            ps.setInt(3, idMateria);
            int exito = ps.executeUpdate();
            //ResultSet rs = ps.getGeneratedKeys();
            if (exito == 1) {
                //inscripcion.setIdInscripcion(rs.getInt("idInscripcion"));
                JOptionPane.showMessageDialog(null, "Nota cargada con exito.");
            }else{
                JOptionPane.showMessageDialog(null, "No se pudo cargar la nota");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion" + ex.getMessage());
        }
    }
   
    public List<Alumno> obtenerAlumnosPorMateria(int idMateria){
        List<Alumno> alumnos = new ArrayList<>();
        try {
            String sql = "SELECT alumno.* FROM alumno JOIN inscripcion ON(alumno.idAlumno=inscripcion.idAlumno) JOIN materia ON(inscripcion.idMateria = materia.idMateria)  WHERE inscripcion.idMateria = ?  ";

            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1,idMateria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno a = new Alumno();
               a.setId_Alumno(rs.getInt("idAlumno"));
               a.setNombre(rs.getString("nombre"));
               a.setApellido(rs.getString("apellido"));
               a.setDni(rs.getInt("dni"));
               a.setFechaNacimiento(rs.getDate("fechaNacimiento").toLocalDate());
               //a.setFechaNacimiento(Date.valueOf(rs.getDate("fechaNacimiento")));
               //System.out.println(rs.getDate("fechaNacimiento"));
               alumnos.add(a);
            }
            ps.close();
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Alumno "+ex.getMessage());
        }
        return alumnos;
        
        
    }

}
