package cl.vina.unab.paradigmas.main;

import cl.vina.unab.paradigmas.main.opciones.LoginVista;
import cl.vina.unab.paradigmas.main.opciones.SelectAlmacen;
import cl.vina.unab.paradigmas.main.opciones.UpdateAlmacen;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainControlador {
    private List<Almacen> lista_almacenes;
    private MainModelo modelo_main;
    private MainVista vista_main;
    
    public MainControlador() {
        lista_almacenes = new ArrayList<>();
        modelo_main = new MainModelo();
        vista_main = new MainVista();

        openConfigFile();       
    }

    // Funciones que se llaman entre si mismas, (siendo como un ciclo) hasta que
    // sea posible ingresar a la base de datos
    private void createConfigFile() {
        LoginVista vista_login = new LoginVista();
        // Agregar action listener
        vista_login.button_enviar.addActionListener((e) -> {
            try (FileWriter credenciales = new FileWriter("app.cfg")) {
                credenciales.write("user=" + vista_login.textfield_usuario.getText());
                credenciales.write("\npass=" + vista_login.textfield_contrasena.getText());
                
                vista_login.setVisible(false);
                // Reintentar
                openConfigFile();
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
            }
        });
        vista_login.setVisible(true);
    }
    
    private void openConfigFile() {
        try (FileInputStream credenciales = new FileInputStream("app.cfg")) {
            Properties propiedades = new Properties();
            propiedades.load(credenciales);
            
            modelo_main.setDatabaseUser(propiedades.getProperty("user"));
            modelo_main.setDatabasePassword(propiedades.getProperty("pass"));
            
            //Probar conexion, si es true, es porque hubo un fallo
            if (modelo_main.testConnection()) {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña mal ingresada(s)");
                createConfigFile();
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese credenciales de base de datos");
            createConfigFile();
        }
    }
    
    private void showUpdateFrame(String title, int id) {
        UpdateAlmacen frame = new UpdateAlmacen();
        
        //Si la id es mayor a 0, colocar datos de almacen coincidente de la lista
        if (id != 0) {
            for (Almacen almacen : lista_almacenes) {
                if (almacen.getId() == id) {
                    frame.jTextField1.setText(almacen.getNombre());
                    frame.jTextField2.setText(almacen.getDireccion());
                }
            }
        }

        frame.button_enviar.addActionListener((e) -> {
            String nombre = frame.jTextField1.getText();
            String direccion = frame.jTextField2.getText();
            if (nombre.equals("") || direccion.equals("")) {
                JOptionPane.showMessageDialog(null, "Error: Casilla(s) vacia(s)");
                return;
            }
            Almacen almacen = new Almacen();
            almacen.setNombre(nombre);
            almacen.setDireccion(direccion);
            
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_main.table_almacen.getModel();
            if (id == 0) {
                if (modelo_main.insert(almacen)) {
                    lista_almacenes.add(almacen);
                    modelo_tabla.addRow(new Object [] {
                        almacen.getId(),
                        almacen.getNombre(),
                        almacen.getDireccion()
                    });
                    JOptionPane.showMessageDialog(null, "Almacen agregado");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Almacen no se pudo agregar");
                }
                frame.jTextField1.setText("");
                frame.jTextField2.setText("");
            }
            else {
                almacen.setId(id);
                if (modelo_main.update(almacen)) {                         
                    int index = 0;
                    for (Almacen almacen_anterior : lista_almacenes) {
                        if (almacen.getId() == almacen_anterior.getId()) {
                            lista_almacenes.set(index, almacen);
                            modelo_tabla.setValueAt(almacen.getId(), index, 0);
                            modelo_tabla.setValueAt(almacen.getNombre(), index, 1);
                            modelo_tabla.setValueAt(almacen.getDireccion(), index, 2);
                            break;
                        }
                        index++;
                    }
                    JOptionPane.showMessageDialog(null, "Almacen editado"); 
                }
                else {
                    JOptionPane.showMessageDialog(null, "Almacen no pudo ser editado");
                }
                frame.setVisible(false);
            }
        });
        
        frame.setTitle(title);
        frame.setVisible(true);
    }
    
    private void showSelectFrame(String title, int option) {
        SelectAlmacen frame = new SelectAlmacen();
        for (Almacen almacen : lista_almacenes) {
            frame.combobox_almacen.addItem(Integer.toString(almacen.getId()));
        }
        switch (option) {
            case 1:
                frame.button_seleccionar.addActionListener((e) -> {
                    // Mostrar Ventana de bodegas
                });
                break;
            case 2:
                frame.button_seleccionar.addActionListener((e) -> {
                    showUpdateFrame("Editar almacen", (int)frame.combobox_almacen.getSelectedItem());
                });
                break;
            case 3:
                frame.button_seleccionar.addActionListener((e) -> {
                    int confirmation = JOptionPane.showConfirmDialog(null, "¿Esta seguro de inhabilitar este almacen?", "Inhabilitar Almacen", 0);
                    if (confirmation == 0) { //Si
                        System.out.println("Si");
                    }
                    else {  //No
                        System.out.println(confirmation);
                    }
                });
                break;
            default:
                throw new AssertionError();
        }
        
        frame.setTitle(title);
        frame.setVisible(true);
    }
    
    private void buttonsActionPerformed() {
        vista_main.button_seleccionar.addActionListener((e) -> {
            showSelectFrame("Seleccionar almacen", 1);
        });
        
        vista_main.button_agregar.addActionListener((e) -> {
            showUpdateFrame("Agregar almacen", 0);
        });
        
        vista_main.button_editar.addActionListener((e) -> {
            showSelectFrame("Editar almacen", 2);
        });
        
        vista_main.button_quitar.addActionListener((e) -> {
            showSelectFrame("Inhabilitar almacen", 3);
        });
    }
    
    //Prepara la vista, obteniendo 
    public void initAlmacen() {
        if (modelo_main.select(lista_almacenes)) {
            DefaultTableModel modelo_tabla = (DefaultTableModel) vista_main.table_almacen.getModel();
            for (Almacen almacen : lista_almacenes) {
                modelo_tabla.addRow(new Object [] {
                    almacen.getId(),
                    almacen.getNombre(),
                    almacen.getDireccion()
                });
            }
            
            buttonsActionPerformed();
            
            vista_main.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
    
    public static void main(String[] args) {
        new MainControlador().initAlmacen();
    }
    
}
