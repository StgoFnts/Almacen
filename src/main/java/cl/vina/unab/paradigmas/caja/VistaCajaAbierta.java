package cl.vina.unab.paradigmas.caja;

public class VistaCajaAbierta extends javax.swing.JPanel {

    private int id_caja;
    
    public VistaCajaAbierta(int id_caja) {
        this.id_caja = id_caja;
        initComponents();
    }

    //Sobreescribir funcion toString, de forma que al llamar al objeto en combobox, envie el nombre
    @Override
    public String toString() {
        return "Caja N°"+this.id_caja;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table_carrito = new javax.swing.JTable();
        combobox_productos = new javax.swing.JComboBox<>();
        textfield_cantidad = new javax.swing.JTextField();
        button_agregar = new javax.swing.JButton();
        button_editar = new javax.swing.JButton();
        button_eliminar = new javax.swing.JButton();
        button_vender = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        button_cerrar = new javax.swing.JButton();

        table_carrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Bodega", "ID Producto", "Nombre Producto", "Cantidad", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_carrito.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_carrito);
        if (table_carrito.getColumnModel().getColumnCount() > 0) {
            table_carrito.getColumnModel().getColumn(0).setResizable(false);
            table_carrito.getColumnModel().getColumn(0).setPreferredWidth(1);
            table_carrito.getColumnModel().getColumn(1).setResizable(false);
            table_carrito.getColumnModel().getColumn(1).setPreferredWidth(1);
            table_carrito.getColumnModel().getColumn(2).setPreferredWidth(200);
            table_carrito.getColumnModel().getColumn(3).setResizable(false);
            table_carrito.getColumnModel().getColumn(3).setPreferredWidth(50);
            table_carrito.getColumnModel().getColumn(4).setResizable(false);
            table_carrito.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        combobox_productos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        button_agregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button_agregar.setText("<html><p>Agregar al</p><p style='text-align: center;'>carrito</p></html>");

        button_editar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button_editar.setText("<html><p>Editar del</p><p style='text-align: center;'>carrito</p></html>");

        button_eliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        button_eliminar.setText("<html><p style='text-align: center;'>Eliminar del</p><p style='text-align: center;'>carrito</p></html>");

        button_vender.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_vender.setText("<html><p style='text-align: center;'>Generar boleta");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("<html><p style='text-align: center;'>Selecciona e ingresa la cantidad de productos deseados al carrito</p></html>");

        button_cerrar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_cerrar.setText("Cerrar Caja");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(button_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(button_editar)
                            .addComponent(button_vender, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combobox_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(textfield_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(button_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_cerrar, button_eliminar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_vender, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(button_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)
                        .addComponent(button_cerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(combobox_productos)
                            .addComponent(textfield_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_agregar))
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {button_agregar, button_cerrar, button_editar, button_eliminar});

    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton button_agregar;
    public javax.swing.JButton button_cerrar;
    public javax.swing.JButton button_editar;
    public javax.swing.JButton button_eliminar;
    public javax.swing.JButton button_vender;
    public javax.swing.JComboBox<Object> combobox_productos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable table_carrito;
    public javax.swing.JTextField textfield_cantidad;
    // End of variables declaration//GEN-END:variables
}
