/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package src.Data;

import src.Data.ConeccionData;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import src.Entidades.Materia;

/**
 *
 * @author Leonardo
 */
public class MateriaData {

    private Connection con = null;

    public MateriaData() {

        con = ConeccionData.getConexion();

    }

    public void guardarMateria(Materia materia) {

        String sql = "INSERT INTO materia (nombre, estado, año) VALUES (?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setBoolean(2, materia.isEstado());
            ps.setInt(3, materia.getAnio());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                materia.setId_Materia(rs.getInt("idMateria"));
                JOptionPane.showMessageDialog(null, "Materia añadida con exito.");
            }
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia" + ex.getMessage());
        }

    }

    public Materia buscarMateria(int idMateria) {
        Materia materia = null;
        String sql = "SELECT * FROM materia WHERE idMateria=? AND estado = 1";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,idMateria);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                materia = new Materia();
                materia.setId_Materia(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setEstado(rs.getBoolean("estado"));
                materia.setAnio(rs.getInt("año"));
                
 
            } else {
                JOptionPane.showMessageDialog(null, "No existe la materia");
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia " + ex.getMessage());
        }

        return materia;
    }

    public void modificarMateria(Materia m){
        String sql = "UPDATE materia SET nombre = ?, estado = ?, año = ? WHERE idMateria = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, m.getNombre());
            ps.setBoolean(2, m.isEstado());
            ps.setInt(3, m.getAnio());
            ps.setInt(4, m.getId_Materia());
            int exito = ps.executeUpdate();
            
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno "+ex.getMessage());
        }
        
    }
    
    public void eliminarMateria(int id){
        String sql = "DELETE FROM `materia` WHERE idMateria = ?";
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);   //Para setear el id donde esta el signo ?
            int exito = ps.executeUpdate();
            if (exito == 1) {
                JOptionPane.showMessageDialog(null, "Borrado Exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "La materia no existe");
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Alumno "+ex.getMessage());
        }
    }
    
    public List<Materia> listarMaterias(){
        List<Materia> materias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM materia WHERE estado = 1 ";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Materia m = new Materia();

                m.setId_Materia(rs.getInt("idMateria"));
                m.setNombre(rs.getString("nombre"));
                m.setEstado(rs.getBoolean("estado"));
                m.setAnio(rs.getInt("año"));
                materias.add(m);
            }
            ps.close();
           

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materia "+ex.getMessage());
        }
        return materias;
    }
    
}
