package cl.vina.unab.paradigmas.boleta;

public class ModeloBoleta {
    private int idBoleta, idCaja;
    private String fecha;

    public ModeloBoleta(String fecha, int idCaja) {
        this.fecha = fecha;
        this.idCaja = idCaja;
    }
    
    public ModeloBoleta(int idBoleta, String fecha, int idCaja) {
        this.idBoleta = idBoleta;
        this.fecha = fecha;
        this.idCaja = idCaja;
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
