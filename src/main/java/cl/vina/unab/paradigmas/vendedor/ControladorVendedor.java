package cl.vina.unab.paradigmas.vendedor;

import cl.vina.unab.paradigmas.utilidades.SelectFrame;
import cl.vina.unab.paradigmas.utilidades.SelectItem;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorVendedor {
    private List<ModeloVendedor> lista_vendedores;
    private DaoVendedor dao_vendedor;
    private VistaVendedor vista_vendedor;
    private JFrame vista_anterior;
    
    public ControladorVendedor(JFrame vista_anterior, String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_vendedores = new ArrayList<>();
        dao_vendedor = new DaoVendedor(DATABASE_USER, DATABASE_PASSWORD);
        vista_vendedor = new VistaVendedor();
        this.vista_anterior = vista_anterior;
    }
    
    public void initializationVendedor() {
        // Seleccionar todos los vendedores
        if (dao_vendedor.select(lista_vendedores)) {
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_vendedor.table_vendedor.getModel();
            // Introducirlos en la tabla
            for (int i = 0; i < lista_vendedores.size(); i++) {
                ModeloVendedor vendedor = lista_vendedores.get(i);
                
                modelo_tabla.addRow(new Object[] {
                    vendedor.getId(),
                    abs(vendedor.getId()),
                    vendedor.getNombre(),
                    vendedor.getRun()
                });
            }
            // Inicializar action listeners de botones
            vista_vendedor.button_crear.addActionListener((e) -> {
                showUpdateFrame("Contratar vendedor", null, 0);  //Ir a la ventana agregar
            });
            
            vista_vendedor.button_editar.addActionListener((e) -> {
                showSelectFrame("Editar datos vendedor", false);   //Ir a la ventana seleccionar
            });

            vista_vendedor.button_deshabilitar.addActionListener((e) -> {
                showSelectFrame("Disponibilidad vendedor", true);
            });
            
            vista_vendedor.button_volver.addActionListener((e) -> {
                vista_vendedor.setVisible(false);
                vista_anterior.setVisible(true);
            });
            
            vista_vendedor.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
    
    private void showSelectFrame(String title, boolean is_disabling) {
        SelectFrame frame = new SelectFrame();
        // Por cada vendedor en la lista vendedores
        for (int i = 0; i < lista_vendedores.size(); i++) {
            // Si esta disponible o si se esta editando disponibilidad
            if (lista_vendedores.get(i).getId() > 0 || is_disabling) {
                frame.combobox_select.addItem(new SelectItem(lista_vendedores.get(i), i));
            }
        }
        // Si combobox es mayor a 0
        if (frame.combobox_select.getItemCount() > 0) {
            frame.button_seleccionar.addActionListener((e) -> {
                frame.setVisible(false);
                // Obtener vendedor de combobox
                ModeloVendedor vendedor = (ModeloVendedor) ((SelectItem) frame.combobox_select.getSelectedItem()).getObject();
                int row = ((SelectItem) frame.combobox_select.getSelectedItem()).getRow();
                // Des/Habilitar
                if (is_disabling) {
                    disableProducto(title, vendedor, row);
                }
                // Editar
                else {
                    showUpdateFrame("Editar vendedor", vendedor, row);
                }
                vista_vendedor.table_vendedor.setRowSelectionInterval(row, 0);
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
    
    private void showUpdateFrame(String title, ModeloVendedor vendedor, int row) {
        VistaUpdateVendedor frame = new VistaUpdateVendedor();

        frame.button_enviar.addActionListener((e) -> {
            // Comprobar que los datos hayan sido ingresados correctamente
            String nombre = frame.textfield_nombre.getText();
            String run = frame.textfield_run.getText();
            
            if (nombre.equals("") || run.equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Casilla(s) vacia(s)");
            }
            else {
                if (frame.textfield_run.getText().length() > 12) {
                    JOptionPane.showMessageDialog(frame, "Error: El RUN no puede superar 12 caracteres");
                }
                else {
                    // Si vendedor es null, es vendedor nuevo
                    if (vendedor == null) {
                        insertVendedor(nombre, run);

                        frame.textfield_nombre.setText("");
                        frame.textfield_run.setText("");
                    }
                    else {
                        //Settear todo en caso de que se haya editado
                        vendedor.setNombre(nombre);
                        vendedor.setRun(run);

                        editProducto(vendedor, row);

                        frame.setVisible(false);
                    }
                }
            }
        });
            
        
        //Si el objeto es distinto de null, rellenar con información los textfields
        if (vendedor != null) {
            frame.textfield_nombre.setText(vendedor.getNombre());
            frame.textfield_run.setText(vendedor.getRun());
        }
        
        frame.setTitle(title);
        frame.setLocation(200, 400);
        frame.setVisible(true);
    }
    
    private void insertVendedor(String nombre, String run) {
        ModeloVendedor vendedor = new ModeloVendedor(nombre, run);
        // Si se logro insertar
        if (dao_vendedor.insert(vendedor)) {
            // Agregar a lista
            lista_vendedores.add(vendedor);
            // Agregar a tabla
            ((DefaultTableModel) vista_vendedor.table_vendedor.getModel()).addRow(new Object [] {
                vendedor.getId(),
                abs(vendedor.getId()),
                vendedor.getNombre(),
                vendedor.getRun()
            });

            JOptionPane.showMessageDialog(null, "Vendedor agregado");
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: Vendedor no se pudo agregar");
        }
    }
    
    private void editProducto(ModeloVendedor vendedor, int row) {
        // Si se logra editar
        if (dao_vendedor.update(vendedor)) {
            // Obtener modelo de la tabla
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_vendedor.table_vendedor.getModel();
            
            // Editar la tabla en el index especificado
            modelo_tabla.setValueAt(vendedor.getNombre(), row, 2);
            modelo_tabla.setValueAt(vendedor.getRun(), row, 3);

            JOptionPane.showMessageDialog(null, "Vendedor editado"); 
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: Vendedor no pudo ser editado");
        }
    }
    
    private void disableProducto(String title, ModeloVendedor vendedor, int row) {
        String message;
        
        if (vendedor.getId() < 0) {
            message = "rehabilita";
        }
        else {
            message = "deshabilita";
        }
        
        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de "+message+"r este vendedor?", title, 0) == 0) {
            if (dao_vendedor.disable(vendedor)) {
                vendedor.setId(vendedor.getId()*-1);
                // Editar en la tabla, en la columna no visible de IDREAL
                ((DefaultTableModel) vista_vendedor.table_vendedor.getModel()).setValueAt(vendedor.getId(), row, 0);

                JOptionPane.showMessageDialog(null, "Vendedor "+message+"do");
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: Vendedor no pudo ser "+message+"do");
            }
        }
    }
    
}
