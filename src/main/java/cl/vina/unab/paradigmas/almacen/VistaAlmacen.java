package cl.vina.unab.paradigmas.almacen;

import cl.vina.unab.paradigmas.main.utilidades.DisabledColorTableCellRenderer;

public class VistaAlmacen extends javax.swing.JFrame {

    public VistaAlmacen() {
        initComponents();
        // Ocultar columna, aunque en el modelo sigue existiendo
        table_almacen.removeColumn(table_almacen.getColumn("IDREAL"));
        table_almacen.setDefaultRenderer(Object.class, new DisabledColorTableCellRenderer());
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_seleccionar = new javax.swing.JButton();
        button_agregar = new javax.swing.JButton();
        button_editar = new javax.swing.JButton();
        button_deshabilitar = new javax.swing.JButton();
        button_volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_almacen = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_seleccionar.setText("Seleccionar");

        button_agregar.setText("Agregar");

        button_editar.setText("Editar");

        button_deshabilitar.setText("Deshabilitar");

        button_volver.setText("Volver");

        table_almacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDREAL", "id", "nombre", "direccion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_almacen);
        if (table_almacen.getColumnModel().getColumnCount() > 0) {
            table_almacen.getColumnModel().getColumn(0).setResizable(false);
            table_almacen.getColumnModel().getColumn(1).setResizable(false);
            table_almacen.getColumnModel().getColumn(1).setPreferredWidth(50);
            table_almacen.getColumnModel().getColumn(2).setResizable(false);
            table_almacen.getColumnModel().getColumn(2).setPreferredWidth(200);
            table_almacen.getColumnModel().getColumn(3).setResizable(false);
            table_almacen.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(button_volver)
                    .addComponent(button_deshabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(button_seleccionar))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_agregar, button_deshabilitar, button_editar, button_seleccionar, button_volver});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_seleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_deshabilitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_volver)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {button_agregar, button_deshabilitar, button_editar, button_seleccionar, button_volver});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton button_agregar;
    public javax.swing.JButton button_deshabilitar;
    public javax.swing.JButton button_editar;
    public javax.swing.JButton button_seleccionar;
    public javax.swing.JButton button_volver;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable table_almacen;
    // End of variables declaration//GEN-END:variables
}
