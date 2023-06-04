package cl.vina.unab.paradigmas.boleta;

public class ModeloDetalleBoleta {
    private int idBoleta, idProducto, cantidad;
    private float precio_venta;

    public ModeloDetalleBoleta(int idBoleta, int idProducto, int cantidad, float precio_venta) {
        this.idBoleta = idBoleta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precio_venta = precio_venta;
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecioVenta() {
        return precio_venta;
    }

    public void setPrecioVenta(float precio_venta) {
        this.precio_venta = precio_venta;
    }
    
    
}
