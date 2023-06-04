package cl.vina.unab.paradigmas.caja;

import cl.vina.unab.paradigmas.stock.ModeloStock;

// Modelo que hereda de stock pero incluye el precio y la bodega a la que pertece cada producto
public class ModeloProductoVenta extends ModeloStock {
    
    private int idBodega;
    private float precio;

    public ModeloProductoVenta(int idBodega, int idProducto, String nombre, int stock, float precio) {
        super(idProducto, nombre, stock);
        this.idBodega = idBodega;
        this.precio = precio;
    }

    public int getIdBodega() {
        return idBodega;
    }

    public float getPrecio() {
        return precio;
    }
    
    @Override
    public String toString() {
        return "Bodega: "+this.idBodega+" - Nombre: "+this.getNombre()+" - $"+this.precio;
    }
}
