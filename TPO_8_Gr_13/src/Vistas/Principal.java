/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;
import src.Data.AlumnoData;
import src.Data.InscripcionData;
import src.Data.MateriaData;
import src.Entidades.Alumno;
import src.Entidades.Inscripcion;
import src.Entidades.Materia;

/**
 *
 * @author EQUIPO
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         /*try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost/universidad","root","");
             String sql="INSERT INTO alumno(nombre,apellido,dni,fechaNacimiento,estado) VALUES ('Ricardo','Argent',885865,'1964-01-19',true)";
            
            PreparedStatement ps=con.prepareStatement(sql);
            int resultado=ps.executeUpdate();
            if(resultado>0){
            
                JOptionPane.showMessageDialog(null,"Alumno agregado");
            }else {
            
                JOptionPane.showMessageDialog(null,"Error al agregar alumno");
            }
            
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar los Drivers");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectarse a la BD");
        }*/
        LocalDate l = LocalDate.now();
        //Alumno a = new Alumno(4,"Pedro", "Suarez", 28967668, l, true);
        AlumnoData al = new AlumnoData();
        //al.guardarAlumno(a);
        //al.eliminarAlumno(4);
        
        //Materia m = new Materia(6, "LenguaExtranjera II", true, 2);
        MateriaData md = new MateriaData();
        //md.guardarMateria(m);
        //md.modificarMateria(m);
        //List<Materia> america = md.listarMaterias();
        //for (Materia materia : america) {
        //    System.out.println(materia);
        //}
        
        Alumno a = al.buscarAlumno(2);
        Materia m = md.buscarMateria(7);
        
        Inscripcion i = new Inscripcion(a, m, 0);
        InscripcionData id = new InscripcionData();
        
        id.guardarInscripcion(i);
    }
    
}
