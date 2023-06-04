package cl.vina.unab.paradigmas.stock;

import cl.vina.unab.paradigmas.bodega.ModeloBodega;
import cl.vina.unab.paradigmas.main.utilidades.SelectFrame;
import cl.vina.unab.paradigmas.main.utilidades.SelectItem;
import cl.vina.unab.paradigmas.producto.ModeloProducto;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorStock {
    private List<ModeloStock> lista_stocks;
    private List<ModeloProducto> lista_productos;
    private DaoStock dao_stock;
    private VistaStock vista_stock;
    private JFrame vista_anterior;
    private ModeloBodega bodega;
    
    private float peso_total = 0, volumen_total = 0;

    public ControladorStock(JFrame vista_anterior, ModeloBodega bodegaSeleccionada, String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_stocks = new ArrayList<>();
        lista_productos = new ArrayList<>();
        dao_stock = new DaoStock(DATABASE_USER, DATABASE_PASSWORD);
        vista_stock = new VistaStock();
        this.vista_anterior = vista_anterior;
        this.bodega = bodegaSeleccionada;
    }
    
    
    public void initializationStock() {
        if (dao_stock.select(lista_stocks, lista_productos, bodega.getId())) {
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_stock.table_stock.getModel();
            
            for (int i = 0; i < lista_stocks.size(); i++) {
                ModeloStock stock_bodega = lista_stocks.get(i);
                
                peso_total += stock_bodega.getPeso() * stock_bodega.getStock();
                volumen_total += stock_bodega.getVolumen() * stock_bodega.getStock();
                
                modelo_tabla.addRow(new Object[] {
                    stock_bodega.getIdProducto(),
                    abs(stock_bodega.getIdProducto()),
                    stock_bodega.getNombre(),
                    stock_bodega.getStock(),
                    stock_bodega.getPeso() * stock_bodega.getStock(),
                    stock_bodega.getVolumen() * stock_bodega.getStock()
                });
            }
            
            vista_stock.button_agregar.addActionListener((e) -> {
                showAddFrame();  //Ir a la ventana agregar
            });

            vista_stock.button_editar.addActionListener((e) -> {
                showSelectFrame("Editar stock", false);
            });

            vista_stock.button_eliminar.addActionListener((e) -> {
                showSelectFrame("Eliminar stock", true);
            });

            vista_stock.button_volver.addActionListener((e) -> {
                vista_stock.setVisible(false);
                vista_anterior.setVisible(true);
            });
            
            vista_stock.label_stock.setText(
                "<html>"+
                "   <h2 style='text-align: center;'>Bodega N°"+bodega.getId()+"</h2>"+
                "</html>"
            );
            
            updateBodegaLimitsLabels();
            vista_stock.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
    
    private void showSelectFrame(String title, boolean is_deleting) {
        SelectFrame frame = new SelectFrame();
        
        for (int i = 0; i < lista_stocks.size(); i++) {
            if (lista_stocks.get(i).getIdProducto() > 0 || is_deleting) {
                frame.combobox_select.addItem(new SelectItem(lista_stocks.get(i), i));
            }
        }
        
        if (frame.combobox_select.getItemCount() > 0) {
            frame.button_seleccionar.addActionListener((e) -> {
                frame.setVisible(false);

                ModeloStock stock_producto_bodega = (ModeloStock) ((SelectItem) frame.combobox_select.getSelectedItem()).getObject();
                int row = ((SelectItem) frame.combobox_select.getSelectedItem()).getRow();

                if (is_deleting) {
                    deleteStock(title, stock_producto_bodega, row);
                }
                else {
                    showUpdateFrame(title, stock_producto_bodega, row);
                }

                vista_stock.table_stock.setRowSelectionInterval(row, 0);
            });

            // Luego de que se haya creado el listener, se inicializa la vista
            frame.setTitle(title);
            frame.setLocation(400, 400);
            frame.setVisible(true); 
        }
        else {
            JOptionPane.showMessageDialog(frame, "Error: No hay objetos para seleccionar."); 
        }
        
    }
   
    private void showAddFrame() {
        VistaAddStock frame = new VistaAddStock();
        
        DefaultTableModel modelo_tabla = (DefaultTableModel) frame.table_productos.getModel();
        for (ModeloProducto producto : lista_productos) {            
            if (producto.getId() > 0) {
                frame.combobox_idProducto.addItem(producto);
            }
                    
            modelo_tabla.addRow(new Object[] {
                producto.getId(),
                abs(producto.getId()),
                producto.getNombre(),
                producto.getPeso(),
                producto.getVolumen()
            });
        }
        
        if (frame.combobox_idProducto.getItemCount() > 0) {
            frame.button_enviar.addActionListener((e) -> {
                try {
                    ModeloProducto producto = (ModeloProducto) frame.combobox_idProducto.getSelectedItem();
                    int stock = Integer.parseInt(frame.textfield_stock.getText());

                    if (stock < 1) {
                       JOptionPane.showMessageDialog(frame, "Error: Ingresa un valor mayor a 0"); 
                    }
                    else if (checkSpaceLimits(producto.getId(), stock)) {
                        ModeloStock stock_bodega = new ModeloStock(producto.getId(), stock);

                        if (dao_stock.insert(stock_bodega, bodega.getId())) {
                            // Actualizar valores totales del peso y volumen con el stock agregado
                            // Y actualizar los labels
                            peso_total += stock_bodega.getPeso()*stock;
                            volumen_total += stock_bodega.getVolumen()*stock;
                            updateBodegaLimitsLabels();

                            lista_stocks.add(stock_bodega);

                            ((DefaultTableModel) vista_stock.table_stock.getModel()).addRow(new Object [] {
                                stock_bodega.getIdProducto(),
                                abs(stock_bodega.getIdProducto()),
                                stock_bodega.getNombre(),
                                stock_bodega.getStock(),
                                stock_bodega.getPeso(),
                                stock_bodega.getVolumen()
                            });

                            JOptionPane.showMessageDialog(frame, "Stock de producto agregado");
                            frame.setVisible(false);
                        }
                        else {
                            JOptionPane.showMessageDialog(frame, "Error: Stock de producto no pudo ser agregado / Producto repetido");
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) numerico(s)");
                }
                frame.textfield_stock.setText("");
            });

            frame.setTitle("Agregar stock de producto");
            frame.setLocation(100, 400);
            frame.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(frame, "Error: No hay objetos para seleccionar."); 
        }
    }
     
    private void showUpdateFrame(String title, ModeloStock stock_producto_bodega, int row) {
        VistaUpdateStock frame = new VistaUpdateStock();
        int stock_anterior = stock_producto_bodega.getStock();
        
        frame.button_enviar.addActionListener((e) -> {
            try {
                int stock = Integer.parseInt(frame.textfield_stock.getText());
                
                if (stock < 1) {
                   JOptionPane.showMessageDialog(frame, "Error: Ingresa un valor mayor a 0"); 
                }
                else if (checkSpaceLimits(stock_producto_bodega.getIdProducto(), stock)) {
                    
                    stock_producto_bodega.setStock(stock);
                    
                    if (dao_stock.update(stock_producto_bodega, bodega.getId())) {
                        // Restar valores del stock anterior
                        // Actualizar valores totales del peso y volumen con el stock editado
                        // Esto permite que si el stock editado es menor o mayor, se quite primero los valores anteriores
                        // y se agreguen los (posibles) nuevos.
                        peso_total -= stock_producto_bodega.getPeso()*stock_anterior;
                        volumen_total -= stock_producto_bodega.getVolumen()*stock_anterior;
                        
                        float peso_actualizado = stock_producto_bodega.getPeso() * stock_producto_bodega.getStock();
                        peso_total += peso_actualizado;
                        
                        float volumen_actualizado = stock_producto_bodega.getVolumen() * stock_producto_bodega.getStock();
                        volumen_total += volumen_actualizado;
                        
                        // Y actualizar los labels
                        updateBodegaLimitsLabels();

                        // Editar modelo
                        ((DefaultTableModel) vista_stock.table_stock.getModel()).setValueAt(stock_producto_bodega.getStock(), row, 3);
                        ((DefaultTableModel) vista_stock.table_stock.getModel()).setValueAt(peso_actualizado, row, 4);
                        ((DefaultTableModel) vista_stock.table_stock.getModel()).setValueAt(volumen_actualizado, row, 5);
                        
                        JOptionPane.showMessageDialog(frame, "Stock de producto actualizado");
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Error: Stock de producto no pudo ser actualizado");
                    }
                    frame.setVisible(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) numerico(s)");
            }
            
            frame.textfield_stock.setText("");
        });
        
        frame.label_nombre.setText("Nombre: " +stock_producto_bodega.getNombre());
        frame.label_peso.setText("Peso: "+stock_producto_bodega.getPeso());
        frame.label_volumen.setText("Volumen: "+stock_producto_bodega.getVolumen());
        frame.textfield_stock.setText(Integer.toString(stock_producto_bodega.getStock()));
        
        frame.setTitle(title);
        frame.setLocation(400, 400);
        frame.setVisible(true);
    }
    
    private void deleteStock(String title, ModeloStock stock_producto_bodega, int index) {       
        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este stock de la bodega?", title, 0) == 0) {

            if (dao_stock.delete(stock_producto_bodega, bodega.getId())) {
                // Restar valores anteriores
                peso_total -= stock_producto_bodega.getPeso()*stock_producto_bodega.getStock();
                volumen_total -= stock_producto_bodega.getVolumen()*stock_producto_bodega.getStock();
                
                // Y actualizar los labels
                updateBodegaLimitsLabels();
                
                lista_stocks.remove(index);

                ((DefaultTableModel) vista_stock.table_stock.getModel()).removeRow(index);

                JOptionPane.showMessageDialog(null, "Stock eliminado");
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: Stock no pudo ser eliminado");
            }
        }
    }
    
    private void updateBodegaLimitsLabels() {
        vista_stock.label_peso.setText(
            "<html>"+
                "<p>Peso Maximo : "+bodega.getPesoMax()+"</p>"+
                "<p>Peso Utilizado : "+peso_total+"</p>"+
                "<p>Peso Restante : "+(bodega.getPesoMax()-peso_total)+"</p>"+
            "</html>"
        );
        
        vista_stock.label_volumen.setText(
            "<html>"+
                "<p>Volumen Maximo : "+bodega.getVolumenMax()+"</p>"+
                "<p>Volumen Utilizado : "+volumen_total+"</p>"+
                "<p>Volumen Restante : "+(bodega.getVolumenMax()-volumen_total)+"</p>"+
            "</html>"
        );
    }
    
    private boolean checkSpaceLimits(int idProductoSeleccionado, int stock) {
        for (ModeloProducto producto : lista_productos) {
            
            if (idProductoSeleccionado == producto.getId()) {
                // Si el peso restante es menor al stock * peso del producto seleccionado
                if (bodega.getPesoMax()-peso_total < stock*producto.getPeso()) {
                    JOptionPane.showMessageDialog(null, "Error: Stock supera el peso maximo de esta bodega...");
                    return false;
                }
                // Si el volumen restante es menor al stock * volumen del producto seleccionado
                if (bodega.getVolumenMax()-volumen_total < stock*producto.getVolumen()) {
                    JOptionPane.showMessageDialog(null, "Error: Stock supera el volumen maximo de esta bodega...");
                    return false;
                }
                else {
                    return true;
                }
            }
        }
        
        JOptionPane.showMessageDialog(null, "Error: Producto no encontrado");
        return false;
    }
}
