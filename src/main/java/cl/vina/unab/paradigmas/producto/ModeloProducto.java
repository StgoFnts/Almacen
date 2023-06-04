package cl.vina.unab.paradigmas.producto;

import static java.lang.Math.abs;

public class ModeloProducto {
    private int id;
    private String nombre;
    private float precio, peso, volumen;

    public ModeloProducto(int id, String nombre, float peso, float volumen) {
        this.id = id;
        this.nombre = nombre;
        this.peso = peso;
        this.volumen = volumen;
    }
   
    public ModeloProducto(String nombre, float precio, float peso, float volumen) {
        this.nombre = nombre;
        this.precio = precio;
        this.peso = peso;
        this.volumen = volumen;
    }
    
    public ModeloProducto(int id, String nombre, float precio, float peso, float volumen) {
        this.id = id;
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
    
    //Sobreescribir funcion toString, de forma que al llamar al objeto en combobox, envie el nombre
    @Override
    public String toString() {
        return abs(this.id) + " - " +this.nombre;
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
