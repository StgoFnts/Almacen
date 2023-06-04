package cl.vina.unab.paradigmas.bodega;

import cl.vina.unab.paradigmas.almacen.ModeloAlmacen;
import cl.vina.unab.paradigmas.utilidades.SelectFrame;
import cl.vina.unab.paradigmas.utilidades.SelectItem;
import cl.vina.unab.paradigmas.stock.ControladorStock;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorBodega {
    
    private List<ModeloBodega> lista_bodegas;
    private DaoBodega dao_bodega;
    private VistaBodega vista_bodega;
    private JFrame vista_anterior;
    private ModeloAlmacen almacen;
    
    public ControladorBodega(JFrame vista_anterior, ModeloAlmacen almacenSeleccionado, String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_bodegas = new ArrayList<>();
        dao_bodega = new DaoBodega(DATABASE_USER, DATABASE_PASSWORD);
        vista_bodega = new VistaBodega();
        this.vista_anterior = vista_anterior;
        this.almacen = almacenSeleccionado;
    }
    
    //Preparar la vista
    public void initializationBodega() {
        // Obtener lista de bodegas del almacen seleccionado
        if (dao_bodega.select(lista_bodegas, almacen.getId())) {
            // Obtener modelo e introducir objetos en tabla
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_bodega.table_bodega.getModel();
            
            for (ModeloBodega bodega : lista_bodegas) {                
                modelo_tabla.addRow(new Object[] {
                    bodega.getId(),
                    abs(bodega.getId()),
                    bodega.getPesoMax(),
                    bodega.getVolumenMax()
                });
            }
            
            // Inicializar los actions listeners de los botones
            vista_bodega.button_seleccionar.addActionListener((e) -> {
                showSelectFrame("Seleccionar almacen", 1);
            });
            vista_bodega.button_agregar.addActionListener((e) -> {
                showUpdateFrame("Agregar almacen", null, 0);  //Ir a la ventana agregar
            });
            vista_bodega.button_editar.addActionListener((e) -> {
                showSelectFrame("Editar almacen", 2);   //Ir a la ventana seleccionar
            });
            vista_bodega.button_deshabilitar.addActionListener((e) -> {
                showSelectFrame("Control Almacen", 3);
            });
            vista_bodega.button_volver.addActionListener((e) -> {
                vista_bodega.setVisible(false);
                vista_anterior.setVisible(true);
            });
            
            vista_bodega.label_bodega.setText(
                "<html>"+
                "   <h2 style='text-align: center;'>Bodegas del Almacen "+almacen.getNombre()+"</h2>"+
                "</html>"
            );
            vista_bodega.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
    
    private void showSelectFrame(String title, int option) {
        SelectFrame frame = new SelectFrame();
        
        // Introducir lista de bodegas en combobox
        for (int i = 0; i < lista_bodegas.size(); i++) {
            // Si su id indica que esta habilitada o se selecciono la opcion 3 (que rehabilita)
            if (lista_bodegas.get(i).getId() > 0 || option == 3) {
                frame.combobox_select.addItem(new SelectItem(lista_bodegas.get(i), i));
            }
        }
        // Si hay objetos dentro del combobox
        if (frame.combobox_select.getItemCount() > 0) {
            // Action listener que cambia según que boton llamo a la ventana de seleccionar
            frame.button_seleccionar.addActionListener((e) -> {
                frame.setVisible(false);
                // Obtener bodega del comobox
                ModeloBodega bodega = (ModeloBodega) ((SelectItem) frame.combobox_select.getSelectedItem()).getObject();
                int row = ((SelectItem) frame.combobox_select.getSelectedItem()).getRow();  // Fila

                switch (option) {
                    // Al seleccionar una bodega
                    case 1:
                        vista_bodega.setVisible(false);

                        new ControladorStock(
                            vista_bodega,
                            bodega,
                            dao_bodega.getDatabaseUser(),
                            dao_bodega.getDatabasePassword()
                        ).initializationStock();

                        break;
                    // Al editar una bodega
                    case 2:
                        showUpdateFrame("Editar almacen", bodega, row);
                        break;
                    // Al des/habilitar un almacen
                    case 3:
                        disableBodega(bodega, title, row);
                        break;
                }

                vista_bodega.table_bodega.setRowSelectionInterval(row, 0);
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
        
    private void showUpdateFrame(String title, ModeloBodega bodega, int row) {
        VistaUpdateBodega frame = new VistaUpdateBodega();
        
        // Action listener que depende si se esta creando o editando bodega
        frame.button_enviar.addActionListener((e) -> {
            // Comprobar que los datos hayan sido ingresados correctamente
            String peso_max_string = frame.textfield_peso_max.getText();
            String volumen_max_string = frame.textfield_volumen_max.getText();
            
            if (peso_max_string.equals("") || volumen_max_string.equals("")) {
                JOptionPane.showMessageDialog(frame, "Error: Casilla(s) vacia(s)");
            }
            else {
                try {
                    float peso_max = Float.parseFloat(peso_max_string);
                    float volumen_max = Float.parseFloat(volumen_max_string);
                    
                    if (peso_max < 0 || volumen_max < 0) {
                        JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) mayor a 0");
                    }
                    else {
                        // Si es null, es bodega nueva
                        if (bodega == null) {
                            insertBodega(peso_max, volumen_max);
                            
                            // Limpia los textfields para seguir creando bodegas
                            frame.textfield_peso_max.setText("");
                            frame.textfield_volumen_max.setText("");
                        }
                        // Si no, settear valores en caso de que hayan sido editados y editar
                        else {
                            bodega.setPesoMax(peso_max);
                            bodega.setVolumenMax(volumen_max);
                            
                            editBodega(bodega, row);
                            
                            frame.setVisible(false);
                        }
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) numerico(s)");
                }
            }
        });
            
        
        //Si el producto es distinto de null, rellenar con información los textfields
        if (bodega != null) {
            frame.textfield_peso_max.setText(Float.toString(bodega.getPesoMax()));
            frame.textfield_volumen_max.setText(Float.toString(bodega.getVolumenMax()));
        }
        
        frame.setTitle(title);
        frame.setLocation(200, 400);
        frame.setVisible(true);
    }
    
    private void insertBodega(float peso_max, float volumen_max) {
        ModeloBodega bodega = new ModeloBodega(peso_max, volumen_max);
        
        // Si fue posible insertar bodega en el almacen seleccionado
        if (dao_bodega.insert(bodega, almacen.getId())) {
            lista_bodegas.add(bodega);
            // Introducir bodega nueva en la tabla
            ((DefaultTableModel) vista_bodega.table_bodega.getModel()).addRow(new Object[] {
                bodega.getId(),
                abs(bodega.getId()),
                bodega.getPesoMax(),
                bodega.getVolumenMax()
            });
            
            JOptionPane.showMessageDialog(null, "Bodega agregada");
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: Bodega no pudo ser agregada");
        }
    }
    
    private void editBodega(ModeloBodega bodega, int row) {
        // Si se pudo editar
        if (dao_bodega.update(bodega)) {           
            // Obtener modelo de la tabla
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_bodega.table_bodega.getModel();
            
            // Editar la tabla en el row especificado
            modelo_tabla.setValueAt(bodega.getPesoMax(), row, 2);
            modelo_tabla.setValueAt(bodega.getVolumenMax(), row, 3);
            
            JOptionPane.showMessageDialog(null, "Bodega editada");
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: Bodega no pudo ser editada");
        }
    }

    private void disableBodega(ModeloBodega bodega, String title, int row) {
        String message;
        
        if (bodega.getId() < 0) {
            message = "rehabilita";
        }
        else {
            message = "deshabilita";
        }
        
        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de "+message+"r este producto?", title, 0) == 0) {

            if (dao_bodega.disable(bodega)) {
                bodega.setId(bodega.getId()*-1);

                ((DefaultTableModel) vista_bodega.table_bodega.getModel()).setValueAt(bodega.getId(), row, 0);

                JOptionPane.showMessageDialog(null, "Producto "+message+"do");
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: Producto no pudo ser "+message+"do");
            }
        }
    }
}

