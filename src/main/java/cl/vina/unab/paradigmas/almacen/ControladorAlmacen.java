package cl.vina.unab.paradigmas.almacen;

import cl.vina.unab.paradigmas.main.utilidades.SelectFrame;
import cl.vina.unab.paradigmas.bodega.ControladorBodega;
import cl.vina.unab.paradigmas.caja.ControladorCaja;
import cl.vina.unab.paradigmas.main.utilidades.SelectItem;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorAlmacen {
    private List<ModeloAlmacen> lista_almacenes;
    private DaoAlmacen dao_almacen;
    private VistaAlmacen vista_almacen;
    private JFrame vista_anterior;
    
    public ControladorAlmacen(JFrame vista_anterior, String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_almacenes = new ArrayList<>();
        dao_almacen = new DaoAlmacen(DATABASE_USER, DATABASE_PASSWORD);
        vista_almacen = new VistaAlmacen();
        this.vista_anterior = vista_anterior;
    }
    
    // Preparar la vista
    public void initializationAlmacen() {
        if (dao_almacen.select(lista_almacenes)) {
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_almacen.table_almacen.getModel();
            
            for (int i = 0; i < lista_almacenes.size(); i++) {
                ModeloAlmacen almacen = lista_almacenes.get(i);
                
                modelo_tabla.addRow(new Object [] {
                    almacen.getId(),
                    abs(almacen.getId()),
                    almacen.getNombre(),
                    almacen.getDireccion()
                });
            }
            
            // Inicializar los actions listeners de los botones
            vista_almacen.button_seleccionar.addActionListener((e) -> {
                showSelectFrame("Seleccionar almacen", 1);
            });
            vista_almacen.button_agregar.addActionListener((e) -> {
                showUpdateFrame("Agregar almacen", null, 0);  //Ir a la ventana agregar
            });
            vista_almacen.button_editar.addActionListener((e) -> {
                showSelectFrame("Editar almacen", 2);   //Ir a la ventana seleccionar
            });
            vista_almacen.button_deshabilitar.addActionListener((e) -> {
                showSelectFrame("Control Almacen", 3);
            });
            vista_almacen.button_volver.addActionListener((e) -> {
                vista_almacen.setVisible(false);
                vista_anterior.setVisible(true);
            });
            
            vista_almacen.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
    
    private void showSelectFrame(String title, int option) {
        SelectFrame frame = new SelectFrame();
        
        for (int i = 0; i < lista_almacenes.size(); i++) {
            if (lista_almacenes.get(i).getId() > 0 || option == 3) {
                frame.combobox_select.addItem(new SelectItem(lista_almacenes.get(i), i));
            }
        }
        if (frame.combobox_select.getItemCount() > 0) {
            frame.button_seleccionar.addActionListener((e) -> {
                frame.setVisible(false);

                ModeloAlmacen almacen = (ModeloAlmacen) ((SelectItem) frame.combobox_select.getSelectedItem()).getObject();
                int row = ((SelectItem) frame.combobox_select.getSelectedItem()).getRow();

                switch (option) {
                    // Al seleccionar un almacen
                    case 1:
                        vista_almacen.setVisible(false);
                        showOptionsFrame(almacen);
                        break;
                    // Al editar un almacen
                    case 2:
                        showUpdateFrame("Editar almacen", almacen, row);
                        break;
                    // Al in/habilitar un almacen
                    case 3:
                        disableBodega(almacen, title, row);
                        break;
                }

                vista_almacen.table_almacen.setRowSelectionInterval(row, 0);
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
    
    private void showUpdateFrame(String title, ModeloAlmacen almacen, int index) {
        VistaUpdateAlmacen frame = new VistaUpdateAlmacen();

        frame.button_enviar.addActionListener((e) -> {
            String nombre = frame.textfield_nombre.getText();
            String direccion = frame.textfield_direccion.getText();
            
            if (nombre.equals("") || direccion.equals("")) {
                JOptionPane.showMessageDialog(frame, "Error: Casilla(s) vacia(s)");
            }
            else {
                if (almacen == null) {
                    insertAlmacen(nombre, direccion);

                    frame.textfield_nombre.setText("");
                    frame.textfield_direccion.setText("");
                }
                else {
                    almacen.setNombre(nombre);
                    almacen.setDireccion(direccion);

                    editAlmacen(almacen, index);

                    frame.setVisible(false);
                }
            }
        });
        
        //Si la id es mayor a 0, colocar datos de almacen coincidente de la lista
        if (almacen != null) {
            frame.textfield_nombre.setText(almacen.getNombre());
            frame.textfield_direccion.setText(almacen.getDireccion());
        }
        
        frame.setTitle(title);
        frame.setLocation(200, 400);
        frame.setVisible(true);
    }
    
    private void showOptionsFrame(ModeloAlmacen almacen) {
        VistaOptionsAlmacen frame = new VistaOptionsAlmacen();
        
        frame.button_bodegas.addActionListener((e) -> {
            frame.setVisible(false);
            new ControladorBodega(
                frame, 
                almacen, 
                dao_almacen.getDatabaseUser(), 
                dao_almacen.getDatabasePassword()
            ).initializationAlmacen();
        });

        frame.button_cajas.addActionListener((e) -> {
            frame.setVisible(false);
            new ControladorCaja(
                frame, 
                almacen, 
                dao_almacen.getDatabaseUser(), 
                dao_almacen.getDatabasePassword()
            ).initializationCaja();
        });
        
        frame.button_volver.addActionListener((e) -> {
            frame.setVisible(false);
            vista_almacen.setVisible(true);
        });
        
        frame.label_almacen.setText(
            "<html>"+
            "   <h2 style='text-align: center;'>Bienvenido a la interfaz del Almacen</h2>"+
            "   <h1 style='text-align: center;'>"+almacen.getNombre()+"</h1>"+
            "</html>"
        );
        
        frame.setTitle(almacen.getNombre());
        frame.setVisible(true);
    }
    
    private void insertAlmacen(String nombre, String direccion) {
        ModeloAlmacen almacen = new ModeloAlmacen(nombre, direccion);

        if (dao_almacen.insert(almacen)) {

            lista_almacenes.add(almacen);
            
            ((DefaultTableModel) vista_almacen.table_almacen.getModel()).addRow(new Object[] {
                almacen.getId(),
                abs(almacen.getId()),
                almacen.getNombre(),
                almacen.getDireccion()
            });

            JOptionPane.showMessageDialog(null, "Almacen agregado");
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: Almacen no se pudo agregar");
        }
    }
    
    private void editAlmacen(ModeloAlmacen almacen, int index) {
        if (dao_almacen.update(almacen)) {
            // Obtener el modelo default de la tabla
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_almacen.table_almacen.getModel();
            // Editar modelo
            modelo_tabla.setValueAt(almacen.getNombre(), index, 2);
            modelo_tabla.setValueAt(almacen.getDireccion(), index, 3);

            JOptionPane.showMessageDialog(null, "Almacen editado"); 
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: Almacen no pudo ser editado");
        }
    }
    
    private void disableBodega(ModeloAlmacen almacen, String title, int index) {
        String message;
        
        if (almacen.getId() < 0) {
            message = "rehabilita";
        }
        else {
            message = "deshabilita";
        }
        
        if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de "+message+"r este producto?", title, 0) == 0) {

            if (dao_almacen.disable(almacen)) {
                almacen.setId(almacen.getId()*-1);

                ((DefaultTableModel) vista_almacen.table_almacen.getModel()).setValueAt(almacen.getId(), index, 0);

                JOptionPane.showMessageDialog(null, "Producto "+message+"do");
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: Producto no pudo ser "+message+"do");
            }
        }
    }
}
