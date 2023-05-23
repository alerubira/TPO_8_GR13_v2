
package src.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConeccionData {
    private static final String URL="jdbc:mysql://localhost/";
    private static final String DB="universidad";
    private static final String USUARIO="root";
    private static String PASSWORD="";

    private static Connection connection;
   
    
    //Metodo constructor
   
    private ConeccionData(){}
    
    
public static Connection getConexion() {
        
       
        if (connection == null) {
            
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                // Setup the connection with the DB
                connection = DriverManager
                        .getConnection(URL+DB + "?useLegacyDatetimeCode=false&serverTimezone=UTC"
                                + "&user=" + USUARIO + "&password=" + PASSWORD);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectarse a la BD");
            }catch(ClassNotFoundException ex){
                
                JOptionPane.showMessageDialog(null, "Error al cargar los Drivers");
            }
        }
        return connection;
    }

    
}
