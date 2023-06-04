package cl.vina.unab.paradigmas.boleta;

import cl.vina.unab.paradigmas.main.DaoMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoBoleta extends DaoMain {
    
    public DaoBoleta(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean insertBoleta(ModeloBoleta boleta) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO boletas (fecha, cajas_idCaja) VALUES (?, ?)");
            
            statement.setString(1, boleta.getFecha());
            statement.setInt(2, boleta.getIdCaja());
            statement.executeUpdate();
            
            ResultSet result = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM boletas").executeQuery();
            
            if (result.next()) {
                boleta.setIdBoleta(result.getInt(1));
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
    
    public boolean insertDetalleBoleta(ModeloDetalleBoleta detalle_boleta) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO detalle_de_boleta (boletas_idBoleta, productos_idProducto, cantidad, precio_venta) VALUES (?, ?, ?, ?)");
            
            statement.setInt(1, detalle_boleta.getIdBoleta());
            statement.setInt(2, detalle_boleta.getIdProducto());
            statement.setInt(3, detalle_boleta.getCantidad());
            statement.setFloat(4, detalle_boleta.getPrecioVenta());
            
            statement.executeUpdate();
            
            return false;
        }
        catch (SQLException ex) {
            return false;
        }
        finally {
            this.close();
        }
    }
}
