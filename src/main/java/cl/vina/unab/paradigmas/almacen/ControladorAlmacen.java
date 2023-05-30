package cl.vina.unab.paradigmas.almacen;

import cl.vina.unab.paradigmas.almacen.vistas.OptionsAlmacen;
import cl.vina.unab.paradigmas.main.vistas.SelectFrame;
import cl.vina.unab.paradigmas.almacen.vistas.UpdateAlmacen;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorAlmacen {
    private List<Almacen> lista_almacenes;
    private ModeloAlmacen modelo_almacen;
    private VistaAlmacen vista_almacen;
    
    public ControladorAlmacen(String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_almacenes = new ArrayList<>();
        modelo_almacen = new ModeloAlmacen(DATABASE_USER, DATABASE_PASSWORD);
        vista_almacen = new VistaAlmacen();
    }
    
    private void showOptionsFrame(Almacen almacen) {
        OptionsAlmacen frame = new OptionsAlmacen();
        
        String DATABASE_USER = modelo_almacen.getDatabaseUser();
        String DATABASE_PASSWORD = modelo_almacen.getDatabasePassword();
        
        frame.button_bodegas.addActionListener((e) -> {
            frame.setVisible(false);
        });

        frame.button_cajas.addActionListener((e) -> {
            frame.setVisible(false);
        });
        
        frame.button_volver.addActionListener((e) -> {
            frame.setVisible(false);
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
    
    private void showUpdateFrame(String title, Almacen almacen, int index) {
        UpdateAlmacen frame = new UpdateAlmacen();

        frame.button_enviar.addActionListener((e) -> {
            String nombre = frame.textfield_nombre.getText();
            String direccion = frame.textfield_direccion.getText();
            
            if (nombre.equals("") || direccion.equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Casilla(s) vacia(s)");
                return;
            }
            
            // Obtener el modelo default de la tabla
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_almacen.table_almacen.getModel();
            
            // Agregar
            if (almacen == null) {
                Almacen nuevo_almacen = new Almacen(nombre, direccion);
                
                if (modelo_almacen.insert(nuevo_almacen)) {
                    lista_almacenes.add(nuevo_almacen);
                    
                    modelo_tabla.addRow(new Object [] {
                        nuevo_almacen.getId(),
                        nuevo_almacen.getNombre(),
                        nuevo_almacen.getDireccion()
                    });
                    
                    JOptionPane.showMessageDialog(null, "Almacen agregado");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error: Almacen no se pudo agregar");
                }
                
                frame.textfield_nombre.setText("");
                frame.textfield_direccion.setText("");
            }
            // Editar
            else {
                almacen.setNombre(nombre);
                almacen.setDireccion(direccion);
                
                if (modelo_almacen.update(almacen)) {                         
                    lista_almacenes.set(index, almacen);
                    
                    modelo_tabla.setValueAt(almacen.getNombre(), index, 1);
                    modelo_tabla.setValueAt(almacen.getDireccion(), index, 2);
                    
                    JOptionPane.showMessageDialog(null, "Almacen editado"); 
                }
                else {
                    JOptionPane.showMessageDialog(null, "Error: Almacen no pudo ser editado");
                }
                
                frame.setVisible(false);
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
    
    private void showSelectFrame(String title, int option) {
        SelectFrame frame = new SelectFrame();
        
        for (Almacen almacen : lista_almacenes) {
            frame.combobox_select.addItem(Integer.toString(almacen.getId()));
        }
        
        frame.button_seleccionar.addActionListener((e) -> {
            frame.setVisible(false);
            
            int index = 0;
            int id_seleccionado = Integer.parseInt(frame.combobox_select.getSelectedItem().toString());

            for (Almacen almacen : lista_almacenes) {
                
                if (id_seleccionado == almacen.getId()) {
                    // Dependiendo de la opcion elegida anteriormente, cambiara
                    // el comportamiento del boton seleccionar
                    switch (option) {
                        // Al seleccionar un almacen
                        case 1:
                            vista_almacen.setVisible(false);
                            showOptionsFrame(almacen);
                            break;
                            
                        // Al editar un almacen
                        case 2:
                            showUpdateFrame("Editar almacen", almacen, index);
                            break;
                            
                        // Al in/habilitar un almacen
                        case 3:
                            if (JOptionPane.showConfirmDialog(null, "¿Esta seguro de in/habilitar este almacen?", title, 0) == 0) {

                                if (modelo_almacen.disable(almacen)) {
                                    almacen.setId(almacen.getId()*-1);

                                    DefaultTableModel modelo_tabla = (DefaultTableModel) vista_almacen.table_almacen.getModel();
                                    modelo_tabla.setValueAt(almacen.getId(), index, 0);

                                    JOptionPane.showMessageDialog(null, "Almacen modificado");
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Error: Almacen no pudo ser modificado");
                                }
                            }
                            break;
                            
                        default:
                            // No hacer nada
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
    }
    
    //Prepara la vista, obteniendo 
    public void initializationAlmacen() {
        if (modelo_almacen.select(lista_almacenes)) {
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_almacen.table_almacen.getModel();
            
            for (Almacen almacen : lista_almacenes) {
                modelo_tabla.addRow(new Object [] {
                    almacen.getId(),
                    almacen.getNombre(),
                    almacen.getDireccion()
                });
            }
            
            // Inicializar los actions listeners de los botones
            buttonsActionPerformed();
            
            vista_almacen.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
}
