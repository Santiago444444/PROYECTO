package com.transporte.gestion.vista.Mantenimiento;

import com.transporte.gestion.controlador.Mantenimiento.OrdenMantenimientoController;
import com.transporte.gestion.modelo.Mantenimiento.DetalleMantenimientoView;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class DetalleOrdenDialog extends javax.swing.JDialog {

    private OrdenMantenimientoController controller;
    private DefaultTableModel modelDetalles;

    public DetalleOrdenDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        configurarTabla();
    }

    public void setController(OrdenMantenimientoController controller) {
        this.controller = controller;
    }

    public void cargarDatos(int ordenId, String bus, double total) {
        lblTitulo.setText("Detalles Orden #" + ordenId + " (Bus: " + bus + ")");
        lblTotal.setText(String.format("$ %.2f", total));
        
        cargarTabla(ordenId);
    }

    private void configurarTabla() {
        String[] cols = {"Tipo Mantenimiento", "Descripción", "Costo U.", "Cant.", "Total"};
        modelDetalles = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tablaDetalles.setModel(modelDetalles);
    }

    private void cargarTabla(int ordenId) {
        if (controller == null) return;
        
        modelDetalles.setRowCount(0);
        List<DetalleMantenimientoView> lista = controller.obtenerDetalles(ordenId);
        
        for (DetalleMantenimientoView d : lista) {
            Object[] fila = {
                d.getTipoMantenimientoNombre(),
                d.getDescripcion(),
                d.getCostoUnitario(),
                d.getCantidad(),
                d.getTotal()
            };
            modelDetalles.addRow(fila);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaDetalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDetallesMouseClicked(evt);
            }
        });
        scroll.setViewportView(tablaDetalles);

        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotal)
                    .addComponent(jButton1))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaDetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDetallesMouseClicked

    }//GEN-LAST:event_tablaDetallesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tablaDetalles;
    // End of variables declaration//GEN-END:variables
}
