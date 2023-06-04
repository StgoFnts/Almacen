package cl.vina.unab.paradigmas.almacen;

import static java.lang.Math.abs;

public class ModeloAlmacen {
    private int id;
    private String nombre, direccion;
    
    public ModeloAlmacen(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public ModeloAlmacen(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    //Sobreescribir funcion toString, de forma que al llamar al objeto en combobox, envie el nombre
    @Override
    public String toString() {
        return "ID: "+abs(this.id)+" - "+this.nombre+" - "+this.direccion;
    }
}
