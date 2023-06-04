package cl.vina.unab.paradigmas.caja;

import static java.lang.Math.abs;

public class ModeloCaja {
    private int id, numero, idVendedor;
    private String tipo;

    public ModeloCaja(String tipo, int numero, int idVendedor) {
        this.tipo = tipo;
        this.numero = numero;
        this.idVendedor = idVendedor;
    }
    
    public ModeloCaja(int id, String tipo, int numero, int idVendedor) {
        this.id = id;
        this.tipo = tipo;
        this.numero = numero;
        this.idVendedor = idVendedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }
    
    //Sobreescribir funcion toString, de forma que al llamar al objeto en combobox, envie el nombre
    @Override
    public String toString() {
        return "ID: "+abs(this.id)+" - Tipo: "+this.tipo+" - Numero: "+this.numero;
    }
}
