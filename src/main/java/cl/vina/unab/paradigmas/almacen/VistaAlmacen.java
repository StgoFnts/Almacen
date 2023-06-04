package cl.vina.unab.paradigmas.almacen;

import cl.vina.unab.paradigmas.utilidades.DisabledColorTableCellRenderer;

public class VistaAlmacen extends javax.swing.JFrame {

    public VistaAlmacen() {
        initComponents();
        // Ocultar columna, aunque en el modelo sigue existiendo
        table_almacen.removeColumn(table_almacen.getColumn("IDREAL"));
        table_almacen.setDefaultRenderer(Object.class, new DisabledColorTableCellRenderer());
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        button_editar = new javax.swing.JButton();
        button_deshabilitar = new javax.swing.JButton();
        button_volver = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_almacen = new javax.swing.JTable();
        button_agregar = new javax.swing.JButton();
        button_seleccionar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_editar.setText("Editar");
        button_editar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        button_editar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        button_deshabilitar.setText("Deshabilitar");
        button_deshabilitar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        button_deshabilitar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        button_volver.setText("Volver");
        button_volver.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        button_volver.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        table_almacen.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "IDREAL", "ID", "NOMBRE", "DIRECCION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_almacen.setSelectionForeground(new java.awt.Color(0, 153, 204));
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

        button_agregar.setText("Agregar");
        button_agregar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        button_agregar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        button_seleccionar.setText("Seleccionar");
        button_seleccionar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        button_seleccionar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setText("<html><p style='text-align: center'>Selecciona algunas de las opciones");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(button_volver)
                                .addComponent(button_deshabilitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_agregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(button_seleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_agregar, button_deshabilitar, button_editar, button_seleccionar, button_volver});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(button_seleccionar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_editar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_deshabilitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(button_volver)))
                .addGap(25, 25, 25))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {button_agregar, button_deshabilitar, button_editar, button_seleccionar, button_volver});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton button_agregar;
    public javax.swing.JButton button_deshabilitar;
    public javax.swing.JButton button_editar;
    public javax.swing.JButton button_seleccionar;
    public javax.swing.JButton button_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable table_almacen;
    // End of variables declaration//GEN-END:variables
}
