package cl.vina.unab.paradigmas.caja;

import cl.vina.unab.paradigmas.stock.ModeloStock;

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

    public void setIdBodega(int idBodega) {
        this.idBodega = idBodega;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString() {
        return "Nombre: "+this.getNombre()+" - $"+this.precio;
    }
}
