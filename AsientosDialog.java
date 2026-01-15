package com.transporte.gestion.vista.Flota;

import com.transporte.gestion.dao.Flota.AsientoDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.Asiento;
import java.awt.Window;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AsientosDialog extends javax.swing.JDialog {

    private final AsientoDAO asientoDAO = new AsientoDAO();
    private final String placaBus;

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Número", "Ubicación", "Estado"}
    ) {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    public AsientosDialog(Window parent, String placaBus) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.placaBus = placaBus;

        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Asientos del bus: " + placaBus);

        tabla.setModel(model);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ocultarColumnaId();
        btnEditarUbicacion.setEnabled(false);
        btnToggle.setEnabled(false);

        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnEditarUbicacion = new javax.swing.JButton();
        btnToggle = new javax.swing.JButton();

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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        scroll.setViewportView(tabla);

        btnEditarUbicacion.setText("Editar");
        btnEditarUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarUbicacionActionPerformed(evt);
            }
        });

        btnToggle.setText("Toggle");
        btnToggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToggleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEditarUbicacion)
                .addGap(18, 18, 18)
                .addComponent(btnToggle)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarUbicacion)
                    .addComponent(btnToggle))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarUbicacionActionPerformed
        onEditarUbicacion();
    }//GEN-LAST:event_btnEditarUbicacionActionPerformed

    private void btnToggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToggleActionPerformed
        onToggle();
    }//GEN-LAST:event_btnToggleActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        boolean seleccionado = tabla.getSelectedRow() >= 0;
        btnEditarUbicacion.setEnabled(seleccionado);
        btnToggle.setEnabled(seleccionado);
    }//GEN-LAST:event_tablaMouseClicked

    private void cargarTabla() {
        List<Asiento> lista = asientoDAO.listarPorBus(placaBus);
        model.setRowCount(0);
        for (Asiento a : lista) {
            model.addRow(new Object[]{
                a.getId(),
                a.getNumero(),
                a.getUbicacion(),
                (a.getEstado() != null && a.getEstado()) ? "ACTIVO" : "INACTIVO"
            });
        }
        btnEditarUbicacion.setEnabled(false);
        btnToggle.setEnabled(false);
    }

    private Integer getIdSeleccionado() {
        int row = tabla.getSelectedRow();
        if (row == -1) {
            return null;
        }
        int modelRow = tabla.convertRowIndexToModel(row);
        Object v = model.getValueAt(modelRow, 0);
        return (v instanceof Integer) ? (Integer) v : null;
    }

    private boolean asientoEstaActivoFilaSeleccionada() {
        int row = tabla.getSelectedRow();
        if (row == -1) {
            return false;
        }
        int modelRow = tabla.convertRowIndexToModel(row);
        String estado = String.valueOf(model.getValueAt(modelRow, 3)).trim().toUpperCase();
        return "ACTIVO".equals(estado);
    }

    private void ocultarColumnaId() {
        var col = tabla.getColumnModel().getColumn(0);
        col.setMinWidth(0);
        col.setMaxWidth(0);
        col.setPreferredWidth(0);
        col.setResizable(false);
    }

    private void onEditarUbicacion() {
        Integer id = getIdSeleccionado();
        if (id == null) {
            return;
        }

        int row = tabla.getSelectedRow();
        int modelRow = tabla.convertRowIndexToModel(row);
        String ubicActual = String.valueOf(model.getValueAt(modelRow, 2));

        String nueva = JOptionPane.showInputDialog(
                this,
                "Ubicación (VENTANA/PASILLO/...):",
                ubicActual
        );

        if (nueva == null) {
            return;
        }
        nueva = nueva.trim().toUpperCase();

        if (nueva.isEmpty() || nueva.length() > 20) {
            JOptionPane.showMessageDialog(this, "Ubicación inválida (1..20 chars).", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!nueva.matches("^[A-Z_\\-]{2,20}$")) {
            JOptionPane.showMessageDialog(this, "Use solo letras MAYÚSCULAS, _ o -.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ResultadoOperacion r = asientoDAO.actualizarUbicacion(id, nueva);
        if (r.isOk()) {
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, r.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onToggle() {
        Integer id = getIdSeleccionado();
        if (id == null) {
            return;
        }

        boolean activo = asientoEstaActivoFilaSeleccionada();
        String accion = activo ? "desactivar" : "activar";

        int c = JOptionPane.showConfirmDialog(
                this,
                "¿Desea " + accion + " este asiento?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );
        if (c != JOptionPane.YES_OPTION) {
            return;
        }

        ResultadoOperacion r = asientoDAO.setEstado(id, !activo);
        if (r.isOk()) {
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(this, r.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarUbicacion;
    private javax.swing.JButton btnToggle;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
