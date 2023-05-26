package cl.vina.unab.paradigmas.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

public class MainModelo {
    //Propiedades heredables
    private Connection connection;
    protected String DATABASE_USER, DATABASE_PASSWORD;

    public void setDatabaseUser(String DATABASE_USER) {
        this.DATABASE_USER = DATABASE_USER;
    }

    public void setDatabasePassword(String DATABASE_PASSWORD) {
        this.DATABASE_PASSWORD = DATABASE_PASSWORD;
    }
    
    private void connect() throws SQLException {
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
    
    public boolean testConnection() {
        try {
            this.connect();
            this.close();
            return false;
        }
        catch (SQLException ex) {
            return true;
        }
    }

    //Modelo Almacen
    public boolean select(List<Almacen> lista_almacenes) {
        try {
            this.connect();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM almacenes");
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                Almacen almacen = new Almacen();
                almacen.setId(result.getInt(1));
                almacen.setNombre(result.getString(2));
                almacen.setDireccion(result.getString(3));
                lista_almacenes.add(almacen);
            }
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            this.close();
        }
    }
    
    public boolean insert(Almacen almacen) {
        try {
            this.connect();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO almacenes (nombre, direccion) VALUES (?, ?)");
            statement.setString(1,almacen.getNombre());
            statement.setString(2,almacen.getDireccion());
            statement.execute();
            
            ResultSet result = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM almacenes").executeQuery();
            if (result.next()) {
                almacen.setId(result.getInt(1));
                return true;
            } 
            return false;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            this.close();
        }
    }
    
    public boolean update(Almacen almacen) {
        try {
            this.connect();
            PreparedStatement statement = connection.prepareStatement("UPDATE almacenes SET nombre = ?, direccion = ? WHERE id = ?");
            statement.setString(1,almacen.getNombre());
            statement.setString(2,almacen.getDireccion());
            statement.setInt(3,almacen.getId());
            statement.execute();
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            this.close();
        }
    }
    
    public boolean delete(Almacen almacen) {
        try {
            this.connect();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM almacenes WHERE id = ?");
            statement.setInt(1,almacen.getId());
            statement.execute();
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            this.close();
        }
    }
}
