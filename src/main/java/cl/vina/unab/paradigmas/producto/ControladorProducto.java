package cl.vina.unab.paradigmas.producto;

import cl.vina.unab.paradigmas.main.VistaMain;
import cl.vina.unab.paradigmas.main.vistas.SelectFrame;
import cl.vina.unab.paradigmas.producto.vistas.UpdateProducto;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorProducto {
    private List<Producto> lista_productos;
    private ModeloProducto modelo_producto;
    private VistaProducto vista_producto;
    private VistaMain vista_main;
    
    public ControladorProducto(VistaMain vista_main, String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_productos = new ArrayList<>();
        modelo_producto = new ModeloProducto(DATABASE_USER, DATABASE_PASSWORD);
        vista_producto = new VistaProducto();
        this.vista_main = vista_main;
    }
    
    private void showUpdateFrame(String title, Producto producto, int index) {
        UpdateProducto frame = new UpdateProducto();

        frame.button_enviar.addActionListener((e) -> {
            String nombre = frame.textfield_nombre.getText();
            String precio = frame.textfield_precio.getText();
            String peso = frame.textfield_peso.getText();
            String volumen = frame.textfield_volumen.getText();
            
            if (nombre.equals("") || precio.equals("") || peso.equals("") || volumen.equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Casilla(s) vacia(s)");
                return;
            }
            else {
                
            }
            
            // Obtener el modelo default de la tabla
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_producto.table_productos.getModel();
            
            // Agregar
            if (producto == null) {
                Producto nuevo_producto;
                
                try {
                    nuevo_producto = new Producto(nombre, Float.parseFloat(precio), Float.parseFloat(peso), Float.parseFloat(volumen));
                    
                    if (nuevo_producto.getPrecio() < 0 || nuevo_producto.getPeso() < 0 || nuevo_producto.getVolumen() < 0) {
                        JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) mayor a 0");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) numerico(s)");
                    return;
                }
                
                
                if (modelo_producto.insert(nuevo_producto)) {
                    
                    lista_productos.add(nuevo_producto);
                    
                    modelo_tabla.addRow(new Object [] {
                        nuevo_producto.getId(),
                        nuevo_producto.getNombre(),
                        nuevo_producto.getPrecio(),
                        nuevo_producto.getPeso(),
                        nuevo_producto.getVolumen()
                    });
                    
                    JOptionPane.showMessageDialog(null, "Producto agregado");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error: Producto no se pudo agregar");
                }
                
                frame.textfield_nombre.setText("");
                frame.textfield_precio.setText("");
                frame.textfield_peso.setText("");
                frame.textfield_volumen.setText("");
            }
            // Editar
            else {
                producto.setNombre(nombre);
                try {
                    producto.setPrecio(Float.parseFloat(precio));
                    producto.setPeso(Float.parseFloat(peso));
                    producto.setVolumen(Float.parseFloat(volumen));
                    
                    if (producto.getPrecio() < 0 || producto.getPeso() < 0 || producto.getVolumen() < 0) {
                        JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) mayor a 0");
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: Ingresa valor(es) numerico(s)");
                    return;
                }
                
                if (modelo_producto.update(producto)) {                       
                    lista_productos.set(index, producto);
                    
                    modelo_tabla.setValueAt(producto.getNombre(), index, 1);
                    modelo_tabla.setValueAt(producto.getPrecio(), index, 2);
                    modelo_tabla.setValueAt(producto.getPeso(), index, 3);
                    modelo_tabla.setValueAt(producto.getVolumen(), index, 4);
                    
                    JOptionPane.showMessageDialog(null, "Producto editado"); 
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error: Producto no pudo ser editado");
                }
                
                frame.setVisible(false);
            }
        });
        
        //Si la id es mayor a 0, colocar datos de almacen coincidente de la lista
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
    
    private void showSelectFrame(String title, boolean is_disabling) {
        SelectFrame frame = new SelectFrame();
        
        for (Producto producto : lista_productos) {
            frame.combobox_select.addItem(Integer.toString(producto.getId()));
        }
        
        frame.button_seleccionar.addActionListener((e) -> {
            frame.setVisible(false);
            
            int index = 0;
            int id_seleccionado = Integer.parseInt(frame.combobox_select.getSelectedItem().toString());

            for (Producto producto : lista_productos) {
                
                if (id_seleccionado == producto.getId()) {
                    // Dependiendo de la opcion elegida anteriormente, cambiara
                    // el comportamiento del boton seleccionar
                    if (is_disabling) {
                        // Para in/habilitar un producto
                        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de des/habilitar este producto?", title, 0) == 0) {

                            if (modelo_producto.disable(producto)) {
                                producto.setId(producto.getId()*-1);

                                DefaultTableModel modelo_tabla = (DefaultTableModel) vista_producto.table_productos.getModel();
                                modelo_tabla.setValueAt(producto.getId(), index, 0);

                                JOptionPane.showMessageDialog(null, "Producto modificado");
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "Error: Producto no pudo ser modificado");
                            }
                        }
                    }
                    else {
                        // Para editar un producto
                        showUpdateFrame("Editar almacen", producto, index);
                    }
                    break;
                }
                index++;
            }
        });
        
        // Luego de que se haya creado el listener, se inicializa la vista
        frame.setTitle(title);
        frame.setLocation(400, 400);
        frame.setVisible(true);
    }
    
    private void buttonsActionPerformed() {
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
            vista_main.setVisible(true);
        });
    }
    
    public void initializationProducto() {
        if (modelo_producto.select(lista_productos)) {
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_producto.table_productos.getModel();
            
            for (Producto producto : lista_productos) {
                modelo_tabla.addRow(new Object [] {
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getPeso(),
                    producto.getVolumen()
                });
            }
            
            buttonsActionPerformed();
            
            vista_producto.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
}
