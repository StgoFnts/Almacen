package cl.vina.unab.paradigmas.almacen;

import cl.vina.unab.paradigmas.main.ModeloMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModeloAlmacen extends ModeloMain {

    public ModeloAlmacen(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean select(List<Almacen> lista_almacenes) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM almacenes");
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                Almacen almacen = new Almacen(result.getString(2), result.getString(3));
                almacen.setId(result.getInt(1));
                
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
            
            PreparedStatement statement = connection.prepareStatement("UPDATE almacenes SET nombre = ?, direccion = ? WHERE idAlmacen = ?");
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
    
    public boolean disable(Almacen almacen) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE almacenes SET idAlmacen = ? WHERE idAlmacen = ?");
            statement.setInt(1,almacen.getId() * -1);
            statement.setInt(2,almacen.getId());
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
