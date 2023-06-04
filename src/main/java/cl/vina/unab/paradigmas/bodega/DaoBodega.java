package cl.vina.unab.paradigmas.bodega;

import cl.vina.unab.paradigmas.main.DaoMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoBodega extends DaoMain {
    
    public DaoBodega(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean select(List<ModeloBodega> lista_bodegas, int id_almacen) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM bodegas WHERE almacenes_idAlmacen = ?");
            statement.setInt(1, id_almacen);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                lista_bodegas.add(new ModeloBodega(
                        result.getInt(1), 
                        result.getFloat(2), 
                        result.getFloat(3)
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
    
    public boolean insert(ModeloBodega bodega, int id_almacen) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO bodegas (peso_max, volumen_max, almacenes_idAlmacen) VALUES (?, ?, ?)");
            
            statement.setFloat(1,bodega.getPesoMax());
            statement.setFloat(2,bodega.getVolumenMax());
            statement.setInt(3, id_almacen);
            statement.executeUpdate();
            
            ResultSet result = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM bodegas").executeQuery();
            
            if (result.next()) {
                bodega.setId(result.getInt(1));
                return true;
            }
            
            return false;
        }
        catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {
            this.close();
        }
    }
    
    public boolean update(ModeloBodega bodega) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE bodegas SET peso_max = ?, volumen_max = ? WHERE idBodega = ?");
            
            statement.setFloat(1, bodega.getPesoMax());
            statement.setFloat(2, bodega.getVolumenMax());
            statement.setFloat(3, bodega.getId());
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
    
    public boolean disable(ModeloBodega bodega) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE bodegas SET idBodega = ? WHERE idBodega = ?");
            
            statement.setInt(1, bodega.getId() * -1);
            statement.setInt(2, bodega.getId());
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
