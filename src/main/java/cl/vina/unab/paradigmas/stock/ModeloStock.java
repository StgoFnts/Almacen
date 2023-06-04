package cl.vina.unab.paradigmas.stock;

import static java.lang.Math.abs;

public class ModeloStock {
    private int idProducto, stock;
    private float peso, volumen;
    private String nombre;

    public ModeloStock(int idProducto, int stock) {
        this.idProducto = idProducto;
        this.stock = stock;
    }

    public ModeloStock(int idProducto, String nombre, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.stock = stock;
    }

    public ModeloStock(int idProducto, String nombre, int stock, float peso, float volumen) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.stock = stock;
        this.peso = peso;
        this.volumen = volumen;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getVolumen() {
        return volumen;
    }

    public void setVolumen(float volumen) {
        this.volumen = volumen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString() {
        return abs(this.idProducto) + " - " +this.nombre;
    }
}
