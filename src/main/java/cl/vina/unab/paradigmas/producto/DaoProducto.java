package cl.vina.unab.paradigmas.producto;

import cl.vina.unab.paradigmas.main.DaoMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoProducto extends DaoMain {

    public DaoProducto(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean select(List<ModeloProducto> lista_productos) {
        try {
            this.connect();
            
            ResultSet result = connection.prepareStatement("SELECT * FROM productos").executeQuery();
            
            while (result.next()) {
                lista_productos.add(new ModeloProducto(
                        result.getInt(1),
                        result.getString(2), 
                        result.getFloat(3), 
                        result.getFloat(4), 
                        result.getFloat(5)
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
    
    public boolean insert(ModeloProducto producto) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO productos (nombre, precio, peso, volumen) VALUES (?, ?, ?, ?)");
            
            statement.setString(1, producto.getNombre());
            statement.setFloat(2, producto.getPrecio());
            statement.setFloat(3, producto.getPeso());
            statement.setFloat(4, producto.getVolumen());
            statement.executeUpdate();
            
            ResultSet result = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM productos").executeQuery();
            
            if (result.next()) {
                producto.setId(result.getInt(1));
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
    
    public boolean update(ModeloProducto producto) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE productos SET nombre = ?, precio = ?, peso = ?, volumen = ? WHERE idProducto = ?");
            
            statement.setString(1, producto.getNombre());
            statement.setFloat(2, producto.getPrecio());
            statement.setFloat(3, producto.getPeso());
            statement.setFloat(4, producto.getVolumen());
            statement.setFloat(5, producto.getId());
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
    
    public boolean disable(ModeloProducto producto) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE productos SET idProducto = ? WHERE idProducto = ?;");
            
            statement.setInt(1, producto.getId() * -1);
            statement.setInt(2,producto.getId());
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
}
