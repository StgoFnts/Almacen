package cl.vina.unab.paradigmas.bodega;

import cl.vina.unab.paradigmas.utilidades.DisabledColorTableCellRenderer;

public class VistaBodega extends javax.swing.JFrame {

    public VistaBodega() {
        initComponents();
        // Ocultar columna, aunque en el modelo sigue existiendo
        table_bodega.removeColumn(table_bodega.getColumn("IDREAL"));
        table_bodega.setDefaultRenderer(Object.class, new DisabledColorTableCellRenderer());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_deshabilitar = new javax.swing.JButton();
        button_volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_bodega = new javax.swing.JTable();
        button_seleccionar = new javax.swing.JButton();
        button_agregar = new javax.swing.JButton();
        button_editar = new javax.swing.JButton();
        label_bodega = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        button_deshabilitar.setText("Deshabilitar");

        button_volver.setText("Volver");

        table_bodega.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDREAL", "ID", "PESO LIMITE", "VOLUMEN LIMITE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_bodega.setSelectionForeground(new java.awt.Color(0, 153, 204));
        jScrollPane1.setViewportView(table_bodega);
        if (table_bodega.getColumnModel().getColumnCount() > 0) {
            table_bodega.getColumnModel().getColumn(0).setResizable(false);
            table_bodega.getColumnModel().getColumn(1).setResizable(false);
            table_bodega.getColumnModel().getColumn(1).setPreferredWidth(1);
            table_bodega.getColumnModel().getColumn(2).setResizable(false);
            table_bodega.getColumnModel().getColumn(2).setPreferredWidth(150);
            table_bodega.getColumnModel().getColumn(3).setResizable(false);
            table_bodega.getColumnModel().getColumn(3).setPreferredWidth(150);
        }

        button_seleccionar.setText("Seleccionar");

        button_agregar.setText("Agregar");

        button_editar.setText("Editar");

        label_bodega.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(button_volver)
                    .addComponent(button_deshabilitar)
                    .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_seleccionar))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_bodega, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_agregar, button_deshabilitar, button_editar, button_seleccionar, button_volver});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(label_bodega, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_seleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_deshabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
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
    public javax.swing.JLabel label_bodega;
    public javax.swing.JTable table_bodega;
    // End of variables declaration//GEN-END:variables
}
