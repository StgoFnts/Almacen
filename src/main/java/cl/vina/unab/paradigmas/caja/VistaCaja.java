package cl.vina.unab.paradigmas.caja;

public class VistaCaja extends javax.swing.JFrame {

    public VistaCaja() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_cajas = new javax.swing.JPanel();
        label_cajas = new javax.swing.JLabel();
        combobox_cajas = new javax.swing.JComboBox<>();
        button_deshabilitar = new javax.swing.JButton();
        button_crear = new javax.swing.JButton();
        button_editar = new javax.swing.JButton();
        button_volver = new javax.swing.JButton();
        button_boletas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_cajas.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panel_cajasLayout = new javax.swing.GroupLayout(panel_cajas);
        panel_cajas.setLayout(panel_cajasLayout);
        panel_cajasLayout.setHorizontalGroup(
            panel_cajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 895, Short.MAX_VALUE)
        );
        panel_cajasLayout.setVerticalGroup(
            panel_cajasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 572, Short.MAX_VALUE)
        );

        label_cajas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_cajas.setText("<html><p>Selecciona alguna</p><p style='text-align: center;'>opción</p></html>");

        button_deshabilitar.setText("Abrir caja");

        button_crear.setText("Crear caja");

        button_editar.setText("Editar caja");

        button_volver.setText("Volver");

        button_boletas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        button_boletas.setText("Ver boletas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(button_volver)
                    .addComponent(button_crear)
                    .addComponent(button_deshabilitar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combobox_cajas, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_cajas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_boletas)
                    .addComponent(button_editar))
                .addGap(10, 10, 10)
                .addComponent(panel_cajas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {button_boletas, button_crear, button_deshabilitar, button_editar, button_volver});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel_cajas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(label_cajas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(combobox_cajas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button_deshabilitar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_crear, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(button_volver)
                .addGap(20, 20, 20)
                .addComponent(button_boletas, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {button_crear, button_deshabilitar, button_editar, button_volver});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton button_boletas;
    public javax.swing.JButton button_crear;
    public javax.swing.JButton button_deshabilitar;
    public javax.swing.JButton button_editar;
    public javax.swing.JButton button_volver;
    public javax.swing.JComboBox<Object> combobox_cajas;
    public javax.swing.JLabel label_cajas;
    public javax.swing.JPanel panel_cajas;
    // End of variables declaration//GEN-END:variables
}
