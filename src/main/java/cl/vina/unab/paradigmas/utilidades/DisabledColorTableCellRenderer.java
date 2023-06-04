package cl.vina.unab.paradigmas.utilidades;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

// Funcion utilizada para cambiar color de celdas de los componentes que sean deshabilitados
public class DisabledColorTableCellRenderer implements TableCellRenderer {
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component =  new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        // Obtiene el valor de las columnas ocultas llamadas IDREAL
        // Si el valor es negativo en la base de datos, cambiar fondo a un color distinto
        // a los positivos
        if (Integer.parseInt(table.getModel().getValueAt(row, 0).toString()) < 0) {
            component.setBackground(Color.red);
        } else {
            component.setBackground(Color.white);
        }       
        
        return component;
    }
}
