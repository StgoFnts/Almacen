package cl.vina.unab.paradigmas.stock;

import cl.vina.unab.paradigmas.main.utilidades.DisabledColorTableCellRenderer;

public class VistaStock extends javax.swing.JFrame {

    public VistaStock() {
        initComponents();
        // Ocultar columna, aunque en el modelo sigue existiendo
        table_stock.removeColumn(table_stock.getColumn("IDREAL"));
        table_stock.setDefaultRenderer(Object.class, new DisabledColorTableCellRenderer());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_stock = new javax.swing.JLabel();
        button_agregar = new javax.swing.JButton();
        button_editar = new javax.swing.JButton();
        button_eliminar = new javax.swing.JButton();
        button_volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_stock = new javax.swing.JTable();
        label_volumen = new javax.swing.JLabel();
        label_peso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_agregar.setText("Agregar");

        button_editar.setText("Editar");

        button_eliminar.setText("Eliminar");

        button_volver.setText("Volver");

        table_stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDREAL", "id", "nombre", "stock", "peso", "volumen"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_stock);
        if (table_stock.getColumnModel().getColumnCount() > 0) {
            table_stock.getColumnModel().getColumn(0).setResizable(false);
            table_stock.getColumnModel().getColumn(1).setResizable(false);
            table_stock.getColumnModel().getColumn(3).setResizable(false);
        }

        label_volumen.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        label_volumen.setOpaque(true);

        label_peso.setFont(new java.awt.Font("Courier New", 1, 12)); // NOI18N
        label_peso.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(button_volver)
                    .addComponent(button_eliminar)
                    .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label_stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label_peso, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(label_volumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_agregar, button_editar, button_eliminar, button_volver});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(label_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(button_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label_peso, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(label_volumen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {button_agregar, button_editar, button_eliminar, button_volver});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton button_agregar;
    public javax.swing.JButton button_editar;
    public javax.swing.JButton button_eliminar;
    public javax.swing.JButton button_volver;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel label_peso;
    public javax.swing.JLabel label_stock;
    public javax.swing.JLabel label_volumen;
    public javax.swing.JTable table_stock;
    // End of variables declaration//GEN-END:variables
}
