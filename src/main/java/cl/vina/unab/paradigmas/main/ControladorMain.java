package cl.vina.unab.paradigmas.main;

import cl.vina.unab.paradigmas.utilidades.VistaLogin;
import cl.vina.unab.paradigmas.almacen.ControladorAlmacen;
import cl.vina.unab.paradigmas.producto.ControladorProducto;
import cl.vina.unab.paradigmas.vendedor.ControladorVendedor;
import com.formdev.flatlaf.FlatLightLaf;
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
    }
    
    // Inicializar los action listeners de cada boton, los cuales dan acceso
    // a las distintas interfaces
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
    
    private void createConfigFile() {
        VistaLogin vista_login = new VistaLogin();
        // Agregar action listener
        vista_login.button_enviar.addActionListener((e) -> {
            vista_login.setVisible(false);
            // Intentar crear archivo .cfg con las credenciales ingresadas en la ventana login
            try {
                FileWriter credenciales = new FileWriter("app.cfg");
                credenciales.write("user=" + vista_login.textfield_usuario.getText());
                credenciales.write("\npass=" + vista_login.textfield_contrasena.getText());
                credenciales.close();
                // Reintentar probar conexion con las credenciales ingresadas
                openConfigFile();
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
            }
        });
        vista_login.setVisible(true);
    }
    
    private void openConfigFile() {
        // Intentar abrir archivo .cfg
        try (FileInputStream credenciales = new FileInputStream("app.cfg")) {
            Properties propiedades = new Properties();
            propiedades.load(credenciales);
            
            // Crear objeto con las propiedades encontradas en archivo
            dao_main = new DaoMain(propiedades.getProperty("user"),propiedades.getProperty("pass"));

            // Probar conexion, si es false, es porque hubo un fallo
            if (dao_main.testConnection()) {
                this.initializationMain();
            }
            else {
//                JOptionPane.showMessageDialog(null, "Usuario o contraseña mal ingresada(s)");
                // Llevar a login vista
                createConfigFile();
            }
        }
        // Si archivo no existe, crearlo
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ingrese credenciales de base de datos");
            createConfigFile();
        }
    }
    
    public static void main(String[] args) {
        FlatLightLaf.setup();
        ControladorMain controlador_main = new ControladorMain();
        controlador_main.openConfigFile();
    }
    
}
