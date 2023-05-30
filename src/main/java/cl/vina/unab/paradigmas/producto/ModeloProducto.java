package cl.vina.unab.paradigmas.producto;

import cl.vina.unab.paradigmas.main.ModeloMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ModeloProducto extends ModeloMain {

    public ModeloProducto(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean select(List<Producto> lista_productos) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM productos");
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                String nombre = result.getString(2);
                Float precio = result.getFloat(3);
                Float peso = result.getFloat(4);
                Float volumen = result.getFloat(5);
                
                Producto producto = new Producto(nombre, precio, peso, volumen);
                producto.setId(result.getInt(1));
 
                lista_productos.add(producto);
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
    
    public boolean insert(Producto producto) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO productos (nombre, precio, peso, volumen) VALUES (?, ?, ?, ?)");
            statement.setString(1, producto.getNombre());
            statement.setFloat(1, producto.getPrecio());
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
    
    public boolean update(Producto producto) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE productos SET nombre = ?, precio = ?, peso = ?, volumen = ? WHERE idProducto = ?");
            statement.setString(1, producto.getNombre());
            statement.setFloat(1, producto.getPrecio());
            statement.setFloat(3, producto.getPeso());
            statement.setFloat(4, producto.getVolumen());
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
    
    public boolean disable(Producto producto) {
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
