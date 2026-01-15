package com.transporte.gestion.vista.Mantenimiento;

import com.transporte.gestion.controlador.Mantenimiento.OrdenMantenimientoController;
import com.transporte.gestion.modelo.Mantenimiento.OrdenMantenimientoView;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TallerPendientesPanel extends javax.swing.JPanel {

    private OrdenMantenimientoController controller;
    private DefaultTableModel tableModel;

    public TallerPendientesPanel() {
        initComponents();
        this.controller = new OrdenMantenimientoController();
        configurarTabla();
        cargarDatos();
        
        btnAtender.setEnabled(false);
    }

    private void configurarTabla() {
        String[] columnas = {"ID Orden", "Bus Placa", "Fecha Ingreso", "Taller", "Estado"};
        tableModel = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla.setModel(tableModel);
    }

    private void cargarDatos() {
        tableModel.setRowCount(0);
        List<OrdenMantenimientoView> lista = controller.listarPendientes();
        
        for (OrdenMantenimientoView o : lista) {
            Object[] fila = {
                o.getId(),
                o.getBusPlaca(),
                o.getFechaIngreso(),
                o.getTallerNombre(),
                o.getEstado()
            };
            tableModel.addRow(fila);
        }
    }

    private void atenderOrden() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una orden para atender.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int ordenId = (int) tabla.getValueAt(fila, 0);
        String busPlaca = (String) tabla.getValueAt(fila, 1);

        java.awt.Window parent = javax.swing.SwingUtilities.getWindowAncestor(this);
        MantenimientoCierreDialog dialog;
        
        if (parent instanceof java.awt.Frame) {
            dialog = new MantenimientoCierreDialog((java.awt.Frame) parent, true);
        } else {
            dialog = new MantenimientoCierreDialog(null, true);
        }
        
        dialog.setController(controller);
        dialog.setDatosOrden(ordenId, busPlaca);
        dialog.setVisible(true);

        if (dialog.isCerrada()) {
            cargarDatos();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelAcciones = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnAtender = new javax.swing.JButton();
        panelBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        lblPag = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));
        jPanel2.setLayout(new java.awt.BorderLayout());

        panelAcciones.setBackground(new java.awt.Color(255, 255, 255));
        panelAcciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 12, 10, 12));
        panelAcciones.setPreferredSize(new java.awt.Dimension(250, 100));

        btnActualizar.setText("Actualizar Lista");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnAtender.setText("Cerrar Orden");
        btnAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtenderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAtender)
                    .addComponent(btnActualizar))
                .addContainerGap(68, Short.MAX_VALUE))
        );
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActualizar)
                .addGap(18, 18, 18)
                .addComponent(btnAtender)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.add(panelAcciones, java.awt.BorderLayout.EAST);

        panelBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        panelBusqueda.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 12, 10, 12));

        jLabel1.setText("Buscar:");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lupa1_1.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscar)
                    .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.add(panelBusqueda, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 14, 5, 14));

        tabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        lblPag.setText("pag..");

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(428, Short.MAX_VALUE)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(lblPag)
                .addGap(38, 38, 38)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(lblPag)
                    .addComponent(btnPrev)))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed

    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargarDatos();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        boolean seleccionado = tabla.getSelectedRow() >= 0;
        btnAtender.setEnabled(seleccionado);
    }//GEN-LAST:event_tablaMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        cargarDatos();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderActionPerformed
        atenderOrden();
    }//GEN-LAST:event_btnAtenderActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAtender;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPag;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
