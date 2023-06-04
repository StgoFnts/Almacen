package cl.vina.unab.paradigmas.vendedor;

import static java.lang.Math.abs;

public class ModeloVendedor {
    private int id;
    private String nombre, run;

    public ModeloVendedor(String nombre, String run) {
        this.nombre = nombre;
        this.run = run;
    }
    
    public ModeloVendedor(int id, String nombre, String run) {
        this.id = id;
        this.nombre = nombre;
        this.run = run;
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
        return abs(this.id)+" - "+this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }
}
