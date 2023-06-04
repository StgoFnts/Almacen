package cl.vina.unab.paradigmas.boleta;

import cl.vina.unab.paradigmas.main.DaoMain;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoBoleta extends DaoMain {
    
    public DaoBoleta(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean selectBoleta(List<ModeloBoleta> lista_boletas, int id_almacen) {
        try {
            this.connect();
            // Seleccionar todas las boletas del almacen seleccionado
            ResultSet result_boleta = connection.prepareStatement(
                "SELECT idBoleta, fecha, idCaja " +
                "FROM boletas " +
                "INNER JOIN cajas " +
                "ON idCaja = cajas_idCaja " +
                "INNER JOIN almacenes " +
                "ON idAlmacen = almacenes_idAlmacen " +
                "WHERE idAlmacen = " + id_almacen +
                " ORDER BY idBoleta DESC"
            ).executeQuery();
            
            // Construir cada boleta obtenida de la query
            while (result_boleta.next()) {
                // Crear boleta
                ModeloBoleta boleta = new ModeloBoleta(result_boleta.getInt(1), result_boleta.getString(2), result_boleta.getInt(3));
                // Seleccionar todos los detalles de boletas de la boleta creada
                ResultSet result_detalle = connection.prepareStatement(
                    "SELECT idProducto, nombre, cantidad, precio_venta " +
                    "FROM almacen.detalle_de_boleta " +
                    "INNER JOIN productos " +
                    "ON productos_idProducto = idProducto " +
                    "WHERE boletas_idBoleta = " + result_boleta.getInt(1) // No es necesario un prepareStatement ya que se lo entregamos directo de boleta
                ).executeQuery();
                
                // Y almacenarla detalle de boleta en la "lista de detalles de boleta" en  el objeto boleta
                while (result_detalle.next()) {
                    boleta.getListaDetalleBoleta().add(new ModeloDetalleBoleta(
                        result_detalle.getInt(1),
                        result_detalle.getString(2),
                        result_detalle.getInt(3),
                        result_detalle.getFloat(4)
                    ));
                }
                // Finalmente, añadir boleta en lista de boletas
                lista_boletas.add(boleta);
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
    
    // Insertar boleta en BD
    public boolean insertBoleta(ModeloBoleta boleta) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO boletas (fecha, cajas_idCaja) VALUES (?, ?)");
            
            statement.setString(1, boleta.getFecha());
            statement.setInt(2, boleta.getIdCaja());
            statement.executeUpdate();
            // Obtener id de boleta más reciente
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
    
    // Insertar detalle de boleta en BD con su id de boleta asociado
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
