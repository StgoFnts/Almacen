package cl.vina.unab.paradigmas.producto;

import cl.vina.unab.paradigmas.utilidades.DisabledColorTableCellRenderer;

public class VistaProducto extends javax.swing.JFrame {

    public VistaProducto() {
        initComponents();
        // Ocultar columna, aunque en el modelo sigue existiendo
        table_productos.removeColumn(table_productos.getColumn("IDREAL"));
        table_productos.setDefaultRenderer(Object.class, new DisabledColorTableCellRenderer());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_deshabilitar = new javax.swing.JButton();
        button_crear = new javax.swing.JButton();
        button_editar = new javax.swing.JButton();
        button_volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_productos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interfaz Productos");
        setResizable(false);

        button_deshabilitar.setText("Deshabilitar");

        button_crear.setText("Crear");

        button_editar.setText("Editar");

        button_volver.setText("Volver");

        table_productos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDREAL", "ID", "NOMBRE", "PRECIO", "PESO", "VOLUMEN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_productos.setSelectionForeground(new java.awt.Color(0, 153, 204));
        table_productos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_productos);
        if (table_productos.getColumnModel().getColumnCount() > 0) {
            table_productos.getColumnModel().getColumn(0).setResizable(false);
            table_productos.getColumnModel().getColumn(0).setPreferredWidth(0);
            table_productos.getColumnModel().getColumn(1).setResizable(false);
            table_productos.getColumnModel().getColumn(1).setPreferredWidth(5);
            table_productos.getColumnModel().getColumn(2).setPreferredWidth(200);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(button_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_editar)
                    .addComponent(button_deshabilitar)
                    .addComponent(button_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_crear, button_deshabilitar, button_editar, button_volver});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(button_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button_deshabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {button_crear, button_deshabilitar, button_editar, button_volver});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton button_crear;
    public javax.swing.JButton button_deshabilitar;
    public javax.swing.JButton button_editar;
    public javax.swing.JButton button_volver;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable table_productos;
    // End of variables declaration//GEN-END:variables
}
