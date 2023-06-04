package cl.vina.unab.paradigmas.boleta;

import cl.vina.unab.paradigmas.almacen.ModeloAlmacen;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorBoleta {
    private List<ModeloBoleta> lista_boletas;
    private DaoBoleta dao_boleta;
    private VistaBoleta vista_boleta;
    private ModeloAlmacen almacen;
    private int index = 0;

    public ControladorBoleta(ModeloAlmacen almacen, String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_boletas = new ArrayList<>();
        dao_boleta = new DaoBoleta(DATABASE_USER, DATABASE_PASSWORD);
        vista_boleta = new VistaBoleta();
        this.almacen = almacen;
    }
    
    public void initializationCaja() {
        if (dao_boleta.selectBoleta(lista_boletas, almacen.getId())) {
            if (!lista_boletas.isEmpty()) {
                // Mostrar el indice entregado
                showBoleta(index);

                vista_boleta.button_siguiente.addActionListener((e) -> {
                    // Si se fuese a incrementar en 1 el index y es menor al tamaño de la lista, mostrar boleta
                    if (index + 1 < lista_boletas.size()) {
                        showBoleta(++index);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "No hay más boletas para visualizar.");
                    }
                });

                vista_boleta.button_anterior.addActionListener((e) -> {
                    // Si se fuese a restar 1 al index y sigue siendo mayor o igual a 0
                    if (index - 1 >= 0) {
                        showBoleta(--index);
                    }
                });

                vista_boleta.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: No hay boletas en este almacen");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
    
    private void showBoleta(int index) {
        // Obtener boleta de la lista en la posicion index
        ModeloBoleta boleta = lista_boletas.get(index);
        // Obtener modelo de la tabla
        DefaultTableModel modelo_tabla = (DefaultTableModel) vista_boleta.table_detalle_boleta.getModel();
        // Limpiar tabla de datos anteriores // @TO DO separar boletas en paneles y alternarlos, para que no se deba de rehacer todo el proceso por cada acción
        for (int i = modelo_tabla.getRowCount() - 1; i > -1; i--) {
            modelo_tabla.removeRow(i);
        }
        // Inicializar costo total en 0, introducir detalles de la boleta en la tabla
        float costo_total = 0;
        for (ModeloDetalleBoleta detalle_boleta : boleta.getListaDetalleBoleta()) {
            modelo_tabla.addRow(new Object[] {
                detalle_boleta.getIdProducto(),
                detalle_boleta.getNombre(),
                detalle_boleta.getCantidad(),
                detalle_boleta.getPrecioVenta()
            });
            // Incrementar costo total por el precio de la venta de un producto
            costo_total += detalle_boleta.getPrecioVenta();
        }
        // Settear cada label vacio por información del objeto boleta
        vista_boleta.label_almacen.setText("<html><p>"+almacen.getNombre()+"</p><p>"+almacen.getDireccion()+"</p></html>"); // Nombre y direccion del almacen
        vista_boleta.label_numero_boleta.setText(Integer.toString(boleta.getIdBoleta()));                             // Numero de boleta
        vista_boleta.label_fecha.setText(boleta.getFecha());                                                            // Fecha
        vista_boleta.label_numero_caja.setText(Integer.toString(abs(boleta.getIdCaja()))); // Absoluto ya que las cajas mientras esten abiertas, estan deshabilitadas para otros usos
        vista_boleta.label_costo_total.setText(Float.toString(costo_total));                                          // Costo total
        
        // Si el indice es 0, es porque se esta al inicio de la lista, desactivar boton de anterior
        if (index == 0) {
            vista_boleta.button_anterior.setEnabled(false);
        }
        else {
            vista_boleta.button_anterior.setEnabled(true);
        }
    }
}
