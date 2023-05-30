package cl.vina.unab.paradigmas.producto;

public class Producto {
    private int id;
    private String nombre;
    private float precio, peso, volumen;

    public Producto(String nombre, float precio, float peso, float volumen) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.volumen = volumen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
    
}
