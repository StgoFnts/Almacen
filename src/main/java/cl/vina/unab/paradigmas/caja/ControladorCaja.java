package cl.vina.unab.paradigmas.caja;

import cl.vina.unab.paradigmas.almacen.ModeloAlmacen;
import cl.vina.unab.paradigmas.boleta.DaoBoleta;
import cl.vina.unab.paradigmas.boleta.ModeloBoleta;
import cl.vina.unab.paradigmas.boleta.ModeloDetalleBoleta;
import cl.vina.unab.paradigmas.main.utilidades.SelectFrame;
import cl.vina.unab.paradigmas.main.utilidades.SelectItem;
import cl.vina.unab.paradigmas.stock.DaoStock;
import cl.vina.unab.paradigmas.stock.ModeloStock;
import cl.vina.unab.paradigmas.vendedor.ModeloVendedor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class ControladorCaja {
    private List<ModeloCaja> lista_cajas;
    private List<ModeloVendedor> lista_vendedores;
    private List<ModeloProductoVenta> lista_productos_venta;
    private DaoCaja dao_caja;
    private VistaCaja vista_caja;
    private JFrame vista_anterior;
    private ModeloAlmacen almacen;

    public ControladorCaja(JFrame vista_anterior, ModeloAlmacen almacenSeleccionado, String DATABASE_USER, String DATABASE_PASSWORD) {
        lista_cajas = new ArrayList<>();
        lista_vendedores = new ArrayList<>();
        lista_productos_venta = new ArrayList<>();
        dao_caja = new DaoCaja(DATABASE_USER, DATABASE_PASSWORD);
        vista_caja = new VistaCaja();
        this.vista_anterior = vista_anterior;
        this.almacen = almacenSeleccionado;
    }
    
    public void initializationCaja() {
        if (dao_caja.select(lista_cajas, lista_vendedores, lista_productos_venta, almacen.getId())) {
            // Rehabilitar cualquier caja, en caso de que alguna hubiese quedado abierta
            // En alguna sesión anterior o que haya sido interrumpida
            for (ModeloCaja caja : lista_cajas) {
                if (caja.getId() < 0) {
                    if (dao_caja.disable(caja)) {
                        caja.setId(caja.getId()*-1);
                    }
                }
            }
            
            vista_caja.button_crear.addActionListener((e) -> {
                showUpdatePanel(null);  //Ir a la ventana agregar
            });

            vista_caja.button_editar.addActionListener((e) -> {
                showSelectFrame("Editar caja", true);   //Ir a la ventana seleccionar
            });

            vista_caja.button_deshabilitar.addActionListener((e) -> {
                showSelectFrame("Abrir caja", false);
            });
            
            vista_caja.button_volver.addActionListener((e) -> {
                if (vista_caja.combobox_cajas.getItemCount() > 0) {
                    if (JOptionPane.showConfirmDialog(null, "¿Estas seguro? Continuar cerrara todas las cajas.", "Confirmar volver", 0) != 0) {
                        return;
                    }
                }
                
                vista_caja.setVisible(false);
                vista_anterior.setVisible(true);
            });
            
            vista_caja.button_boletas.addActionListener((e) -> {
            });
            
            vista_caja.combobox_cajas.addActionListener((e) -> {
                showPanel((VistaCajaAbierta) vista_caja.combobox_cajas.getSelectedItem());
            });
            
            vista_caja.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(null, "Un error ha ocurrido");
        }
    }
    
    private void showPanel(JPanel panel) {
        panel.setSize(899, 576);
        
        vista_caja.panel_cajas.removeAll();
        vista_caja.panel_cajas.add(panel);
        vista_caja.panel_cajas.revalidate();
        vista_caja.panel_cajas.repaint();
    }
    
    private void showSelectFrame(String title, boolean is_editing) {
        SelectFrame frame = new SelectFrame();
        
        for (ModeloCaja caja : lista_cajas) {
            // En el caso de editar tambien, ya que no se deberia de poder editar una caja ya abierta
            if (caja.getId() > 0) {
                frame.combobox_select.addItem(caja);
            }
        }
        
        // Si es que no hay cajas O ninguna caja abierta.
        if (frame.combobox_select.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "Error: No hay cajas disponibles");
        }
        else {
            frame.button_seleccionar.addActionListener((e) -> {
                ModeloCaja caja = (ModeloCaja) frame.combobox_select.getSelectedItem();

                if(is_editing) {
                    showUpdatePanel(caja);
                }
                else {
                    showCajaAbiertaPanel(caja);
                }

                frame.setVisible(false);
            });

            frame.setLocationRelativeTo(vista_caja);
            frame.setTitle(title);
            frame.setVisible(true);
        }
    }
    
    private void showUpdatePanel(ModeloCaja caja) {
        VistaUpdateCaja panel = new VistaUpdateCaja();
        
        for (ModeloVendedor vendedor : lista_vendedores) {
            panel.combobox_vendedores.addItem(vendedor);
        }
        
        if (panel.combobox_vendedores.getItemCount() == 0) {
            JOptionPane.showMessageDialog(panel, "Error: No hay vendedores disponibles");
        }
        else {
            panel.button_enviar.addActionListener((e) -> {
                String tipo = panel.textfield_tipo.getText();
                String numero_string = panel.textfield_numero.getText();
                int idVendedor = ((ModeloVendedor) panel.combobox_vendedores.getSelectedItem()).getId();

                if (tipo.equals("") || numero_string.equals("")) {
                    JOptionPane.showMessageDialog(panel, "Error: Casilla(s) vacia(s)");
                }
                else {
                    try {
                        int numero = Integer.parseInt(numero_string);

                        if (numero < 1) {
                            JOptionPane.showMessageDialog(panel, "Error: Ingresa valor(es) mayor a 0");
                        }
                        else {
                            if (caja == null) {
                                ModeloCaja caja_nueva = new ModeloCaja(tipo, numero, idVendedor);

                                if (dao_caja.insert(caja_nueva, almacen.getId())) {

                                    lista_cajas.add(caja_nueva);

                                    JOptionPane.showMessageDialog(null, "Caja creada");
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Error: Caja no se pudo creada");
                                }
                            }
                            else {
                                //Settear valores, en caso de que hayan sido modificados
                                caja.setTipo(tipo);
                                caja.setNumero(numero);
                                caja.setIdVendedor(idVendedor);
                                
                                if (dao_caja.update(caja)) {
                                    JOptionPane.showMessageDialog(null, "Caja editada"); 
                                }
                                else {
                                    JOptionPane.showMessageDialog(null, "Error: Caja no pudo ser editada");
                                }
                           }
                       }
                    }
                    catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Error: Casilla 'numero' solamente acepta valores numericos...");
                    }
                }
            });

            if (caja != null) {
                panel.textfield_tipo.setText(caja.getTipo());
                panel.textfield_numero.setText(Integer.toString(caja.getNumero()));
            }
            
            showPanel(panel);
        }
    }

    private void showCajaAbiertaPanel(ModeloCaja caja) {
        if (dao_caja.disable(caja)) {
            VistaCajaAbierta panel = new VistaCajaAbierta(caja.getId());
               
            for (int i = 0; i < lista_productos_venta.size(); i++) {
                panel.combobox_productos.addItem(lista_productos_venta.get(i));
            }
            
            if (panel.combobox_productos.getItemCount() != 0) {
                // Deshabilitarlo temporalmente (deshabilitado mientras esta siendo ocupado / esta abierto)
                caja.setId(caja.getId()*-1);
                
                panel.button_agregar.addActionListener((e) -> {
                    addToCarrito(panel);
                });
                
                panel.button_editar.addActionListener((e) -> {
                    editCarrito(panel, true);
                });
                
                panel.button_eliminar.addActionListener((e) -> {
                    editCarrito(panel, false);
                });
                
                panel.button_vender.addActionListener((e) -> {
                    payCarrito(panel, caja);
                });
               
                
                // Agregar caja al combobox
                vista_caja.combobox_cajas.addItem(panel);
                // Y llevar selección en combobox a la caja más recientemente agregada
                vista_caja.combobox_cajas.setSelectedItem(panel);
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: No hay productos en este almacen");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Error: No se pudo abrir la Caja N°" + caja.getId());
        }
    }
    
    private void addToCarrito(VistaCajaAbierta panel) {
        if (panel.combobox_productos.getItemCount() == 0) {      
            JOptionPane.showMessageDialog(null, "Error: Ya no quedan productos para agregar");
        }
        else if (panel.textfield_cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Error: Casilla cantidad vacia");
        }
        else {
            ModeloProductoVenta producto = (ModeloProductoVenta) panel.combobox_productos.getSelectedItem();
            try {
                if (producto.getStock() < Integer.parseInt(panel.textfield_cantidad.getText())) {
                    JOptionPane.showMessageDialog(null, "Error: Cantidad insuficiente en stock de esta bodega. Intente con un valor menor o igual a "+producto.getStock());
                }
                else {
                    ((DefaultTableModel) panel.table_carrito.getModel()).addRow(new Object[] {
                        producto.getIdBodega(),
                        producto.getIdProducto(),
                        producto.getNombre(),
                        panel.textfield_cantidad.getText(),
                        producto.getPrecio() * Integer.parseInt(panel.textfield_cantidad.getText())
                    });

                    // Eliminarlo de combobox, ya que ya esta en la lista
                    panel.combobox_productos.removeItemAt(panel.combobox_productos.getSelectedIndex()); 
                }
            }
            catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese valores numericos");
            }
            panel.textfield_cantidad.setText("");
        }
    }
    
    private void editCarrito(VistaCajaAbierta panel, boolean is_editing) {
        SelectFrame frame = new SelectFrame();
        
        // Por cada fila de la tabla carrito
        for (int i = 0; i < panel.table_carrito.getRowCount(); i++) {
            // Por cada producto en la lista de productos venta
            for (ModeloProductoVenta producto : lista_productos_venta) {
                // Obtener id's de la tabla, solamente haciendo más legible el condicional
                int table_id_bodega = Integer.parseInt(panel.table_carrito.getValueAt(i, 0).toString());
                int table_id_producto = Integer.parseInt(panel.table_carrito.getValueAt(i, 1).toString());
                // Si id's de la tabla coinciden con lo de la lista de productos disponibles, agregarlo al combobox
                if (producto.getIdBodega() == table_id_bodega && producto.getIdProducto() == table_id_producto) {
                    frame.combobox_select.addItem(new SelectItem(producto, i));
                }
            }
        }
        
        if (frame.combobox_select.getItemCount() == 0) {
            JOptionPane.showMessageDialog(null, "Error: No hay productos en el carrito para seleccionar");
        }
        else {
            frame.button_seleccionar.addActionListener((e) -> {
                // Obtener el objeto del item seleccioando
                SelectItem producto = (SelectItem) frame.combobox_select.getSelectedItem();
                // Agregar item al combobox
                panel.combobox_productos.addItem(producto.getObject());
                if (is_editing) {
                    // Poner valor de cantidad anterior
                    panel.textfield_cantidad.setText(panel.table_carrito.getValueAt(producto.getRow(), 3).toString());
                    // Y dejar en selección el producto que se esta editando
                    panel.combobox_productos.setSelectedItem(producto.getObject());
                }
                // Remover de carrito temporalmente
                ((DefaultTableModel) panel.table_carrito.getModel()).removeRow(producto.getRow());
                frame.setVisible(false);
            });

            frame.setVisible(true);
        }
    }
    
    private void payCarrito(VistaCajaAbierta panel, ModeloCaja caja) {
        if (JOptionPane.showConfirmDialog(null, "¿Desea generar su boleta?", "Confirmación venta", 0) == 0) {
            
            DaoBoleta dao_boleta = new DaoBoleta(dao_caja.getDatabaseUser(), dao_caja.getDatabasePassword());
            
            String fecha = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            
            ModeloBoleta boleta = new ModeloBoleta(fecha, caja.getId());
            
            float total_venta = 0;
            
            if (dao_boleta.insertBoleta(boleta)) {
                // Por cada fila de la tabla carrito en un ciclo inverso
                for (int i = panel.table_carrito.getRowCount() - 1; i > -1; i--) {
                    // Obtener id's de la tabla, solamente haciendo más legible el condicional
                    int table_id_bodega = Integer.parseInt(panel.table_carrito.getValueAt(i, 0).toString());
                    int table_id_producto = Integer.parseInt(panel.table_carrito.getValueAt(i, 1).toString());
                    int cantidad = Integer.parseInt(panel.table_carrito.getValueAt(i, 3).toString());
                    float precio_venta = Float.parseFloat(panel.table_carrito.getValueAt(i, 4).toString());
                    
                    // Por cada producto en la lista de productos venta
                    for (ModeloProductoVenta producto : lista_productos_venta) {
                        

                        // Si id's de la tabla coinciden con lo de la lista de productos disponibles, quitarlo de la boleta y crear detalle de la boleta
                        if (producto.getIdBodega() == table_id_bodega && producto.getIdProducto() == table_id_producto) {
                            // Obtener cantidad
                            
                            total_venta += precio_venta;
                            
                            DaoStock dao_stock = new DaoStock(dao_caja.getDatabaseUser(), dao_caja.getDatabasePassword());
                            ModeloStock stock_bodega = new ModeloStock(producto.getIdProducto(), producto.getStock() - cantidad);
                            // Si el valor en bodega queda en 0, borrar producto de esa bodega, ya que su registro quedara en la boleta de todas formas.
                            if (stock_bodega.getStock() == 0) {
                                dao_stock.delete(stock_bodega, producto.getIdBodega());
                            }
                            else {
                                dao_stock.update(stock_bodega, producto.getIdBodega());
                            }
                            
                            // Crear detalle de la boleta, por cada producto
                            ModeloDetalleBoleta detalle_boleta = new ModeloDetalleBoleta(boleta.getIdBoleta(), producto.getIdProducto(), cantidad, precio_venta);
                            // Y guardarlo en la base de datos
                            dao_boleta.insertDetalleBoleta(detalle_boleta);
                            // Eliminar de la tabla
                            ((DefaultTableModel) panel.table_carrito.getModel()).removeRow(i);
                        }
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Error: No fue posible crear la boleta");
            }
            
            System.out.println(total_venta);
        }
    }
}
