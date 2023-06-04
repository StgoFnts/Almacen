package cl.vina.unab.paradigmas.vendedor;

import cl.vina.unab.paradigmas.utilidades.DisabledColorTableCellRenderer;

public class VistaVendedor extends javax.swing.JFrame {

    public VistaVendedor() {
        initComponents();
        
        table_vendedor.removeColumn(table_vendedor.getColumn("IDREAL"));
        table_vendedor.setDefaultRenderer(Object.class, new DisabledColorTableCellRenderer());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        button_crear = new javax.swing.JButton();
        button_editar = new javax.swing.JButton();
        button_deshabilitar = new javax.swing.JButton();
        button_volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_vendedor = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interfaz Vendedores");

        button_crear.setText("Contratar");

        button_editar.setText("Editar");

        button_deshabilitar.setText("Deshabilitar");

        button_volver.setText("Volver");

        table_vendedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDREAL", "ID", "NOMBRE", "RUN"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_vendedor.setSelectionForeground(new java.awt.Color(0, 153, 204));
        table_vendedor.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_vendedor);
        if (table_vendedor.getColumnModel().getColumnCount() > 0) {
            table_vendedor.getColumnModel().getColumn(0).setResizable(false);
            table_vendedor.getColumnModel().getColumn(0).setPreferredWidth(0);
            table_vendedor.getColumnModel().getColumn(1).setResizable(false);
            table_vendedor.getColumnModel().getColumn(1).setPreferredWidth(5);
            table_vendedor.getColumnModel().getColumn(2).setPreferredWidth(200);
            table_vendedor.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(button_volver)
                    .addComponent(button_deshabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_crear))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_crear, button_deshabilitar, button_editar, button_volver});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_deshabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(button_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
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
    public javax.swing.JTable table_vendedor;
    // End of variables declaration//GEN-END:variables
}
