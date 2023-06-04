package cl.vina.unab.paradigmas.caja;

import static java.lang.Math.abs;

public class ModeloCaja {
    private int id_caja, numero, id_vendedor;
    private String tipo;

    public ModeloCaja(String tipo, int numero, int id_vendedor) {
        this.tipo = tipo;
        this.numero = numero;
        this.id_vendedor = id_vendedor;
    }
    
    public ModeloCaja(int id_caja, String tipo, int numero, int id_vendedor) {
        this.id_caja = id_caja;
        this.tipo = tipo;
        this.numero = numero;
        this.id_vendedor = id_vendedor;
    }

    public int getIdCaja() {
        return id_caja;
    }

    public void setIdCaja(int id_caja) {
        this.id_caja = id_caja;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdVendedor() {
        return id_vendedor;
    }

    public void setIdVendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
    
    //Sobreescribir funcion toString, de forma que al llamar al objeto en combobox, envie el nombre
    @Override
    public String toString() {
        return "ID: "+abs(this.id_caja)+" - Tipo: "+this.tipo+" - Numero: "+this.numero;
    }
}
