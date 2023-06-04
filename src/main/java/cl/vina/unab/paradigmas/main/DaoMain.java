package cl.vina.unab.paradigmas.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DaoMain {
    //Propiedades heredables
    protected Connection connection;
    private String DATABASE_USER, DATABASE_PASSWORD;

    public DaoMain(String DATABASE_USER, String DATABASE_PASSWORD) {
        this.DATABASE_USER = DATABASE_USER;
        this.DATABASE_PASSWORD = DATABASE_PASSWORD;
    }
    
    public String getDatabaseUser() {
        return DATABASE_USER;
    }

    public String getDatabasePassword() {
        return DATABASE_PASSWORD;
    }
    
    protected void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/almacen", this.DATABASE_USER, this.DATABASE_PASSWORD);
    }
    
    protected void close() {
        try {
           connection.close(); 
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"No se pudo cerrar la sesion");
        }
    }
    
    protected boolean testConnection() {
        try {
            this.connect();
            this.close();
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
    }
}
