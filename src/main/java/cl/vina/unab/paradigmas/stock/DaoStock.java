/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cl.vina.unab.paradigmas.stock;

import cl.vina.unab.paradigmas.main.DaoMain;
import cl.vina.unab.paradigmas.producto.ModeloProducto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoStock extends DaoMain {
    
    public DaoStock(String DATABASE_USER, String DATABASE_PASSWORD) {
        super(DATABASE_USER, DATABASE_PASSWORD);
    }
    
    public boolean select(List<ModeloStock> lista_stocks, List<ModeloProducto> lista_productos, int id_bodega) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement(
                "SELECT idProducto, nombre, stock, peso, volumen " +
                "FROM stock_producto_bodega " +
                "INNER JOIN productos " +
                "ON productos_idProducto = idProducto " +
                "WHERE bodegas_idBodega = ?"
            );
            statement.setInt(1, id_bodega);
            
            ResultSet result = statement.executeQuery();
            
            while (result.next()) {
                lista_stocks.add(new ModeloStock(
                    result.getInt(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getFloat(4),
                    result.getFloat(5))
                );
            }
                       
            result = connection.prepareStatement("SELECT idProducto, nombre, peso, volumen FROM productos").executeQuery();
            
            while (result.next()) {
                lista_productos.add(new ModeloProducto(
                        result.getInt(1),
                        result.getString(2),
                        result.getFloat(3),
                        result.getFloat(4)
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
    
    public boolean insert(ModeloStock stock_bodega, int id_bodega) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO stock_producto_bodega (bodegas_idBodega, productos_idProducto, stock) VALUES (?, ?, ?)"
            );
            statement.setInt(1, id_bodega);
            statement.setInt(2, stock_bodega.getIdProducto());
            statement.setInt(3, stock_bodega.getStock());
            statement.executeUpdate();
            
            statement = connection.prepareStatement(
                "SELECT nombre, peso, volumen FROM productos WHERE idProducto = ?"
            );
            statement.setInt(1, stock_bodega.getIdProducto());
           
            ResultSet result = statement.executeQuery();
            
            if (result.next()) {
                stock_bodega.setNombre(result.getString(1));
                stock_bodega.setPeso(result.getFloat(2));
                stock_bodega.setVolumen(result.getFloat(3));
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
    
    public boolean update(ModeloStock stock_bodega, int id_bodega) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE stock_producto_bodega SET stock = ? WHERE bodegas_idBodega = ? AND productos_idProducto = ?"
            );
            
            statement.setInt(1, stock_bodega.getStock());
            statement.setInt(2, id_bodega);
            statement.setInt(3, stock_bodega.getIdProducto());
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
    
    public boolean delete(ModeloStock stock_bodega, int id_bodega) {
        try {
            this.connect();
            
            PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM stock_producto_bodega WHERE bodegas_idBodega = ? AND productos_idProducto = ?"
            );
            
            statement.setInt(1, id_bodega);
            statement.setInt(2, stock_bodega.getIdProducto());
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
