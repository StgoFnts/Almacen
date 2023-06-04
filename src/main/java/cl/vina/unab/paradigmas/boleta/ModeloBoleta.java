package cl.vina.unab.paradigmas.boleta;

import java.util.ArrayList;
import java.util.List;

public class ModeloBoleta {
    private int idBoleta, idCaja;
    private String fecha;
    private List<ModeloDetalleBoleta> lista_detalle_boleta; // Guardar detalles de boleta asociados a la id de esta boleta

    public ModeloBoleta(String fecha, int idCaja) {
        this.fecha = fecha;
        this.idCaja = idCaja;
    }
    
    public ModeloBoleta(int idBoleta, String fecha, int idCaja) {
        this.idBoleta = idBoleta;
        this.fecha = fecha;
        this.idCaja = idCaja;
        this.lista_detalle_boleta = new ArrayList<>();
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

    public String getFecha() {
        return fecha;
    }

    public List<ModeloDetalleBoleta> getListaDetalleBoleta() {
        return lista_detalle_boleta;
    }
}
