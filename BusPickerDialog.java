package com.transporte.gestion.vista.Flota;

import com.transporte.gestion.controlador.Flota.BusController;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Flota.BusView;

import java.awt.Window;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BusPickerDialog extends javax.swing.JDialog {

    private final BusController busController;
    private BusView busSeleccionado = null;

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"PLACA", "Modelo", "Cooperativa", "Capacidad", "Año"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public BusPickerDialog(Window parent, BusController busController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.busController = busController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Seleccionar Bus");
        tabla.setModel(model);
        cargar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        scroll.setViewportView(tabla);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar)
                        .addGap(47, 47, 47)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnCancelar))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        String placa = getPlacaSeleccionada();
        if (placa == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un bus.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BusView b = busController.buscarPorPlaca(placa);
        if (b == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el bus.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        busSeleccionado = b;
        dispose();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    public BusView getBusSeleccionado() {
        return busSeleccionado;
    }

    private void cargar() {
        String filtro = txtBuscar.getText().trim();

        List<BusView> lista = busController.listar(filtro, 50, 1, "S");

        model.setRowCount(0);
        for (BusView b : lista) {
            model.addRow(new Object[]{
                Utils.cargarString(b.getPlaca()),
                Utils.cargarString(b.getModeloNombre()),
                Utils.cargarString(b.getCooperativaNombre()), 
                b.getCapacidad(),
                b.getAnioFabricacion()
            });
        }
    }

    private String getPlacaSeleccionada() {
        int row = tabla.getSelectedRow();
        if (row == -1) {
            return null;
        }

        int modelRow = tabla.convertRowIndexToModel(row);
        Object val = tabla.getModel().getValueAt(modelRow, 0);
        String placa = (val == null) ? null : val.toString().trim();

        return (placa == null || placa.isEmpty()) ? null : placa;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
