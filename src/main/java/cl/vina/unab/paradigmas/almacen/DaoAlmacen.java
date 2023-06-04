package cl.vina.unab.paradigmas.almacen;

import cl.vina.unab.paradigmas.main.DaoMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoAlmacen extends DaoMain {

    public DaoAlmacen(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean select(List<ModeloAlmacen> lista_almacenes) {
        try {
            this.connect();
   
            ResultSet result = connection.prepareStatement("SELECT * FROM almacenes").executeQuery();
            // Ya que no es posible hacer return a un ResultSet luego de que se haya cerrado la conexion
            // Se guardar almacenes en una lista
            while (result.next()) {
                lista_almacenes.add(new ModeloAlmacen(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3)
                    )
                );
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
    
    public boolean insert(ModeloAlmacen almacen) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO almacenes (nombre, direccion) VALUES (?, ?)");
            
            statement.setString(1,almacen.getNombre());
            statement.setString(2,almacen.getDireccion());
            statement.executeUpdate();
            
            // Obtener ultima id de objeto recientemente insertado
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
    
    public boolean update(ModeloAlmacen almacen) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE almacenes SET nombre = ?, direccion = ? WHERE idAlmacen = ?");
            
            statement.setString(1,almacen.getNombre());
            statement.setString(2,almacen.getDireccion());
            statement.setInt(3,almacen.getId());
            statement.executeUpdate();
            
            return true;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            this.close();
        }
    }
    
    public boolean disable(ModeloAlmacen almacen) {
        try {
            this.connect();
            // Deshabilitar almacen haciendo que su id sea negativa, evitando que sea seleccionable o editable
            PreparedStatement statement = connection.prepareStatement("UPDATE almacenes SET idAlmacen = ? WHERE idAlmacen = ?");
            statement.setInt(1, almacen.getId() * -1);
            statement.setInt(2, almacen.getId());
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
