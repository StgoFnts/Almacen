
package cl.vina.unab.paradigmas.bodega;

import static java.lang.Math.abs;

public class ModeloBodega {
    private int id;
    private float peso_max, volumen_max;

    public ModeloBodega(float peso_max, float volumen_max) {
        this.peso_max = peso_max;
        this.volumen_max = volumen_max;
    }
    
    public ModeloBodega(int id, float peso_max, float volumen_max) {
        this.id = id;
        this.peso_max = peso_max;
        this.volumen_max = volumen_max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    //Sobreescribir funcion toString, de forma que al llamar al objeto en combobox, envie el id
    @Override
    public String toString() {
        return "ID: "+abs(this.id)+" - Peso Max: "+(this.peso_max/1000)+"[KG] - Volumen Max: "+(this.volumen_max/1000000)+"[M3]";
    }

    public float getPesoMax() {
        return peso_max;
    }

    public void setPesoMax(float peso_max) {
        this.peso_max = peso_max;
    }

    public float getVolumenMax() {
        return volumen_max;
    }

    public void setVolumenMax(float volumen_max) {
        this.volumen_max = volumen_max;
    }
    
}
