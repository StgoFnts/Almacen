package cl.vina.unab.paradigmas.main;

import cl.vina.unab.paradigmas.almacen.ControladorAlmacen;
import cl.vina.unab.paradigmas.producto.ControladorProducto;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ControladorMain {
    
    private ModeloMain modelo_main;
    public VistaMain vista_main;
    
    private String DATABASE_USER;
    private String DATABASE_PASSWORD;
    
    public ControladorMain() {
        vista_main = new VistaMain();
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
            
            modelo_main = new ModeloMain(propiedades.getProperty("user"), propiedades.getProperty("pass"));
            
            // Probar conexion, si es true, es porque hubo un fallo
            if (modelo_main.testConnection()) {
                // Guardar credenciales para poder entregarselas al resto de controladores
                DATABASE_USER = modelo_main.getDatabaseUser();
                DATABASE_PASSWORD = modelo_main.getDatabasePassword();
            }
            else {
                JOptionPane.showMessageDialog(null, "Usuario o contraseña mal ingresada(s)");
                
                // Llevar a login vista
                createConfigFile();
            }
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese credenciales de base de datos");
            createConfigFile();
        }
    }
    
    public void initializationMain() {
                
        vista_main.button_almacenes.addActionListener((e) -> {
            vista_main.setVisible(false);
            new ControladorAlmacen(DATABASE_USER, DATABASE_PASSWORD).initializationAlmacen();
        });
        
        vista_main.button_productos.addActionListener((e) -> {
            vista_main.setVisible(false);
            new ControladorProducto(vista_main, DATABASE_USER, DATABASE_PASSWORD).initializationProducto();
        });
        
        vista_main.button_vendedores.addActionListener((e) -> {
            vista_main.setVisible(false);
            //new ControladorCaja(DATABASE_USER, DATABASE_PASSWORD).initializationCaja();
        });
        
        vista_main.setVisible(true);
    }
    
    public static void main(String[] args) {
        new ControladorMain().initializationMain();
    }
    
}
