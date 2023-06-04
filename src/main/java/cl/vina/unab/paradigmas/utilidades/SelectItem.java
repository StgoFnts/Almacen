package cl.vina.unab.paradigmas.utilidades;

public class SelectItem {
    private Object object;
    private int row;

    public SelectItem(Object object, int row) {
        this.object = object;
        this.row = row;
    }
    
    @Override
    public String toString() {
        return "fila: "+(this.row+1)+" - "+this.object.toString();
    }
    
    public Object getObject() {
        return object;
    }
    
    public int getRow() {
        return row;
    }
    
}
