package cl.vina.unab.paradigmas.caja;

import cl.vina.unab.paradigmas.main.DaoMain;
import cl.vina.unab.paradigmas.vendedor.ModeloVendedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoCaja extends DaoMain {
    
    public DaoCaja(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean select(List<ModeloCaja> lista_cajas, List<ModeloVendedor> lista_vendedores, List<ModeloProductoVenta> lista_productos_venta, int idAlmacen) {
        try {
            this.connect();
            // Seleccionar las cajas del almacen seleccionado
            PreparedStatement statement = connection.prepareStatement("SELECT idCaja, tipo, numero, vendedores_idVendedor FROM cajas WHERE almacenes_idAlmacen = ?;");
            statement.setInt(1, idAlmacen);
            
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                lista_cajas.add(new ModeloCaja(
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getInt(4)
                    )
                );
            }
            
            // Seleccionar todos los vendedores, ya que en la BD no son fijos de un almacen en especifico
            result = connection.prepareStatement("SELECT * FROM vendedores").executeQuery();
            
            while (result.next()) {
                lista_vendedores.add(new ModeloVendedor(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3)
                    )
                );
            }
            
            // Obtener todos los productos disponibles y habilitados en todas las bodegas del almacen seleccionado
            statement = connection.prepareStatement(
                "SELECT idBodega, idProducto, productos.nombre, stock, precio " +
                "FROM almacenes " +
                "INNER JOIN bodegas " +
                "ON idAlmacen = almacenes_idAlmacen " +
                "INNER JOIN stock_producto_bodega " +
                "ON idBodega = bodegas_idBodega " +
                "INNER JOIN productos " +
                "ON idProducto = productos_idProducto " +
                "WHERE idAlmacen = ? AND idProducto > 0;"
            );
            statement.setInt(1, idAlmacen);
            
            result = statement.executeQuery();
            
            while (result.next()) {
                lista_productos_venta.add(new ModeloProductoVenta(
                        result.getInt(1),
                        result.getInt(2),
                        result.getString(3),
                        result.getInt(4),
                        result.getFloat(5)
                    )
                );
            }
            return true;
        }
        catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
        finally {
            this.close();
        }
    }
    
    public boolean insert(ModeloCaja caja, int idAlmacen) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("INSERT INTO cajas (tipo, numero, almacenes_idAlmacen, vendedores_idVendedor) VALUES (?, ?, ?, ?)");
            
            statement.setString(1, caja.getTipo());
            statement.setInt(2, caja.getNumero());
            statement.setInt(3, idAlmacen);
            statement.setInt(4, caja.getIdVendedor());
            statement.executeUpdate();
            
            ResultSet result = connection.prepareStatement("SELECT LAST_INSERT_ID() FROM cajas").executeQuery();
            
            if (result.next()) {
                caja.setId(result.getInt(1));
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
    
    public boolean update(ModeloCaja caja) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE cajas SET tipo = ?, numero = ?, vendedores_idVendedor = ? WHERE idCaja = ?");
            
            statement.setString(1, caja.getTipo());
            statement.setInt(2, caja.getNumero());
            statement.setInt(3, caja.getIdVendedor());
            statement.setFloat(4, caja.getId());
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
    
    public boolean disable(ModeloCaja caja) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement("UPDATE cajas SET idCaja = ? WHERE idCaja = ?");
            
            statement.setInt(1, caja.getId() * -1);
            statement.setInt(2,caja.getId());
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
