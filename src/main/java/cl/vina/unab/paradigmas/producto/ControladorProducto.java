package cl.vina.unab.paradigmas.producto;

import cl.vina.unab.paradigmas.main.utilidades.SelectFrame;
import cl.vina.unab.paradigmas.main.utilidades.SelectItem;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorProducto {
    private List<ModeloProducto> lista_productos;
    private DaoProducto dao_producto;
    private VistaProducto vista_producto;
    private JFrame vista_anterior;
    
    public ControladorProducto(JFrame vista_anterior, String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_productos = new ArrayList<>();
        dao_producto = new DaoProducto(DATABASE_USER, DATABASE_PASSWORD);
        vista_producto = new VistaProducto();
        this.vista_anterior = vista_anterior;
    }
    
    public void initializationProducto() {
        if (dao_producto.select(lista_productos)) {
                       
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_producto.table_productos.getModel();
            
            for (int i = 0; i < lista_productos.size(); i++) {
                ModeloProducto producto = lista_productos.get(i);
                
                modelo_tabla.addRow(new Object[] {
                    producto.getId(),           // ID Real
                    abs(producto.getId()),    // ID visible
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getPeso(),
                    producto.getVolumen()
                });
            }
            
            vista_producto.button_crear.addActionListener((e) -> {
                showUpdateFrame("Crear producto", null, 0);  //Ir a la ventana agregar
            });

            vista_producto.button_editar.addActionListener((e) -> {
                showSelectFrame("Editar producto", false);   //Ir a la ventana seleccionar
            });

            vista_producto.button_deshabilitar.addActionListener((e) -> {
                showSelectFrame("Control producto", true);
            });

            vista_producto.button_volver.addActionListener((e) -> {
                vista_producto.setVisible(false);
                vista_anterior.setVisible(true);
            });
            
            vista_producto.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
    
    private void showSelectFrame(String title, boolean is_disabling) {
        SelectFrame frame = new SelectFrame();
        
        for (int i = 0; i < lista_productos.size(); i++) {
            if (lista_productos.get(i).getId() > 0 || is_disabling) {
                frame.combobox_select.addItem(new SelectItem(lista_productos.get(i), i));
            }
        }
        if (frame.combobox_select.getItemCount() > 0) {
            frame.button_seleccionar.addActionListener((e) -> {
                frame.setVisible(false);

                ModeloProducto producto = (ModeloProducto) ((SelectItem) frame.combobox_select.getSelectedItem()).getObject();
                int row = ((SelectItem) frame.combobox_select.getSelectedItem()).getRow();

                if (is_disabling) {
                    disableProducto(title, producto, row);
                }
                else {
                    showUpdateFrame("Editar producto", producto, row);
                }

                vista_producto.table_productos.setRowSelectionInterval(row, 0);
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
    
    private void showUpdateFrame(String title, ModeloProducto producto, int row) {
        VistaUpdateProducto frame = new VistaUpdateProducto();

        frame.button_enviar.addActionListener((e) -> {
            // Comprobar que los datos hayan sido ingresados correctamente
            String nombre = frame.textfield_nombre.getText();
            String precio_string = frame.textfield_precio.getText();
            String peso_string = frame.textfield_peso.getText();
            String volumen_string = frame.textfield_volumen.getText();
            
            if (nombre.equals("") || precio_string.equals("") || peso_string.equals("") || volumen_string.equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Casilla(s) vacia(s)");
            }
            else {
                try {
                    float precio = Float.parseFloat(precio_string);
                    float peso = Float.parseFloat(peso_string);
                    float volumen = Float.parseFloat(volumen_string);
                    
                    if (precio < 0 || peso < 0 || volumen < 0) {
                        JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) mayor a 0");
                    }
                    else {
                        if (producto == null) {
                            insertProducto(nombre, precio, peso, volumen);
                            
                            // Limpia los textfields para seguir agregando productos
                            frame.textfield_nombre.setText("");
                            frame.textfield_precio.setText("");
                            frame.textfield_peso.setText("");
                            frame.textfield_volumen.setText("");
                        }
                        else {
                            //Settear todo en caso de que se haya editado
                            producto.setNombre(nombre);
                            producto.setPrecio(precio);
                            producto.setPeso(peso);
                            producto.setVolumen(volumen);

                            editProducto(producto, row);
                            
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
        if (producto != null) {
            frame.textfield_nombre.setText(producto.getNombre());
            frame.textfield_precio.setText(Float.toString(producto.getPrecio()));
            frame.textfield_peso.setText(Float.toString(producto.getPeso()));
            frame.textfield_volumen.setText(Float.toString(producto.getVolumen()));
        }
        
        frame.setTitle(title);
        frame.setLocation(200, 400);
        frame.setVisible(true);
    }
    
    private void insertProducto(String nombre, float precio, float peso, float volumen) {
        ModeloProducto producto = new ModeloProducto(nombre, precio, peso, volumen);

        if (dao_producto.insert(producto)) {

            lista_productos.add(producto);
            
            ((DefaultTableModel) vista_producto.table_productos.getModel()).addRow(new Object [] {
                producto.getId(),
                abs(producto.getId()),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getPeso(),
                producto.getVolumen()
            });

            JOptionPane.showMessageDialog(null, "Producto agregado");
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: Producto no se pudo agregar");
        }
    }
    
    private void editProducto(ModeloProducto producto, int index) {
        if (dao_producto.update(producto)) {
            // Obtener modelo de la tabla
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_producto.table_productos.getModel();
            
            // Editar la tabla en el index especificado
            modelo_tabla.setValueAt(producto.getNombre(), index, 2);
            modelo_tabla.setValueAt(producto.getPrecio(), index, 3);
            modelo_tabla.setValueAt(producto.getPeso(), index, 4);
            modelo_tabla.setValueAt(producto.getVolumen(), index, 5);

            JOptionPane.showMessageDialog(null, "Producto editado"); 
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: Producto no pudo ser editado");
        }
    }
    
    private void disableProducto(String title, ModeloProducto producto, int row) {
        String message;
        
        if (producto.getId() < 0) {
            message = "rehabilita";
        }
        else {
            message = "deshabilita";
        }
        
        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de "+message+"r este producto?", title, 0) == 0) {

            if (dao_producto.disable(producto)) {
                producto.setId(producto.getId()*-1);

                ((DefaultTableModel) vista_producto.table_productos.getModel()).setValueAt(producto.getId(), row, 0);

                JOptionPane.showMessageDialog(null, "Producto "+message+"do");
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: Producto no pudo ser "+message+"do");
            }
        }
    }
    
}
