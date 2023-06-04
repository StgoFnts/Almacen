package cl.vina.unab.paradigmas.main;

import cl.vina.unab.paradigmas.main.utilidades.VistaLogin;
import cl.vina.unab.paradigmas.almacen.ControladorAlmacen;
import cl.vina.unab.paradigmas.producto.ControladorProducto;
import cl.vina.unab.paradigmas.vendedor.ControladorVendedor;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class ControladorMain {
    
    private DaoMain dao_main;
    private VistaMain vista_main;
    
    public ControladorMain() {
        vista_main = new VistaMain();
        openConfigFile();       
    }
    
    public void initializationMain() {
                
        vista_main.button_almacenes.addActionListener((e) -> {
            vista_main.setVisible(false);
            new ControladorAlmacen(
                vista_main, 
                dao_main.getDatabaseUser(), 
                dao_main.getDatabasePassword()
            ).initializationAlmacen();
        });
        
        vista_main.button_productos.addActionListener((e) -> {
            vista_main.setVisible(false);
            new ControladorProducto(
                vista_main,
                dao_main.getDatabaseUser(), 
                dao_main.getDatabasePassword()
            ).initializationProducto();
        });
        
        vista_main.button_vendedores.addActionListener((e) -> {
            vista_main.setVisible(false);
            new ControladorVendedor(
                vista_main,
                dao_main.getDatabaseUser(), 
                dao_main.getDatabasePassword()
            ).initializationVendedor();
        });
        
        vista_main.setVisible(true);
    }
    
    // Funciones que se llaman entre si mismas, (siendo como un ciclo) hasta que
    // sea posible ingresar a la base de datos
    private void createConfigFile() {
        VistaLogin vista_login = new VistaLogin();
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
            
            dao_main = new DaoMain(propiedades.getProperty("user"),propiedades.getProperty("pass"));

            // Probar conexion, si es true, es porque hubo un fallo
            if (!dao_main.testConnection()) {
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
    
    public static void main(String[] args) {
        new ControladorMain().initializationMain();       
    }
    
}
