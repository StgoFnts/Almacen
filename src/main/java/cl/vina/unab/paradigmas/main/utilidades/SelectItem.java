package cl.vina.unab.paradigmas.main.utilidades;

public class SelectItem {
    private Object object;
    private int row;

    public SelectItem(Object object, int row) {
        this.object = object;
        this.row = row;
    }
    
    @Override
    public String toString() {
        return this.object.toString()+" - fila: "+this.row;
    }
    
    public Object getObject() {
        return object;
    }
    
    public int getRow() {
        return row;
    }
    
}
