package cl.vina.unab.paradigmas.vendedor;

import cl.vina.unab.paradigmas.main.DaoMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoVendedor extends DaoMain {
   
    public DaoVendedor(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean select(List<ModeloVendedor> lista_vendedores) {
        try {
            this.connect();
            
            
            ResultSet result = connection.prepareStatement("SELECT * FROM vendedores").executeQuery();
            
            while (result.next()) {
                lista_vendedores.add(new ModeloVendedor(
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
    
    public boolean insert(ModeloVendedor vendedor) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO vendedores (nombre, run) VALUES (?, ?)");
            statement.setString(1, vendedor.getNombre());
            statement.setString(2, vendedor.getRun());
            statement.executeUpdate();
            
            // Obtener la id asignada por la BD al vendedor
            ResultSet result = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM vendedores").executeQuery();
            
            if (result.next()) {
                vendedor.setId(result.getInt(1));
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
    
    public boolean update(ModeloVendedor vendedor) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE vendedores SET nombre = ?, run = ? WHERE idVendedor = ?");
            
            statement.setString(1,vendedor.getNombre());
            statement.setString(2, vendedor.getRun());
            statement.setInt(3, vendedor.getId());
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
    
    public boolean disable(ModeloVendedor vendedor) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE vendedores SET idVendedor = ? WHERE idVendedor = ?");
            
            statement.setInt(1, vendedor.getId() * -1);
            statement.setInt(2,vendedor.getId());
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
    
    public boolean delete(ModeloVendedor vendedor) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM vendedores WHERE idVendedor = ?"
            );
            
            statement.setInt(1, vendedor.getId());
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
