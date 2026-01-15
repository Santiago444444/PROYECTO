package com.transporte.gestion.vista.Ventas;

import com.transporte.gestion.dao.Ventas.BoletoDAO;
import com.transporte.gestion.global.enums.BoletoEstado;
import com.transporte.gestion.global.enums.ViajeEstado;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.SesionGlobal;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Ventas.BoletoView;
import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BoletosDialog extends javax.swing.JDialog {

    private final BoletoDAO boletoDAO = new BoletoDAO();
    private final int viajeId;
    private final ViajeEstado estadoViaje;

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "ID",
                "Código",
                "Asiento",
                "Cliente",
                "Pasajero",
                "TipoPasajero",
                "PrecioBase",
                "Descuento",
                "PrecioFinal",
                "IVA",
                "IVA%",
                "Estado",
                "FechaCompra"
            }
    ) {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    public BoletosDialog(Window parent, int viajeId, ViajeEstado estadoViaje) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.viajeId = viajeId;
        this.estadoViaje = estadoViaje;

        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Boletos del viaje ID: " + viajeId);

        tabla.setModel(model);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        ocultarColumnaId();
        btnVender.setEnabled(false);
        btnAnular.setEnabled(false);
        maximizarHorizontal(parent);
        configurarPermisos();
        cargarTabla();
        actualizarBotonAnular();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnAnular = new javax.swing.JButton();
        btnVender = new javax.swing.JButton();

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

        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnVender.setText("Vender");
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
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
                .addComponent(btnVender)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAnular)
                .addGap(12, 12, 12))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnular)
                    .addComponent(btnVender))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        int[] rows = tabla.getSelectedRows();
        if (rows == null || rows.length == 0) {
            return;
        }

        if (rows.length > 1) {
            JOptionPane.showMessageDialog(this,
                    "Solo puede anular 1 boleto a la vez.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Integer id = getIdSeleccionado();
        if (id == null) {
            return;
        }

        if (estadoViaje != ViajeEstado.PROGRAMADO) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pueden anular boletos porque el viaje está " + estadoViaje,
                    "Acción no permitida",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean esVendedor = SesionGlobal.tienePermiso("VENDEDOR");
        BoletoEstado estadoBoleto = getEstadoBoletoSeleccionado();

        if (estadoBoleto == null) {
            JOptionPane.showMessageDialog(this, "Estado de boleto inválido.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (esVendedor) {
            // VENDEDOR: solo VENDIDO -> ANULADO_CLIENTE
            if (estadoBoleto != BoletoEstado.VENDIDO) {
                JOptionPane.showMessageDialog(this,
                        "Como VENDEDOR solo puede anular boletos en estado VENDIDO.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int c = JOptionPane.showConfirmDialog(
                    this,
                    "¿Anular este boleto por petición del cliente?\n(Esta acción NO se puede deshacer)",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );
            if (c != JOptionPane.YES_OPTION) {
                return;
            }

            ResultadoOperacion r = boletoDAO.anularVendidoPorCliente(id);

            if (r != null && r.isOk()) {
                JOptionPane.showMessageDialog(this, r.getMensaje(), "OK", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, (r == null) ? "Error desconocido." : r.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {

            if (estadoBoleto != BoletoEstado.DISPONIBLE) {
                JOptionPane.showMessageDialog(this,
                        "Solo se puede anular un boleto en estado DISPONIBLE.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int c = JOptionPane.showConfirmDialog(
                    this,
                    "¿Desea anular este boleto?\n(Esta acción NO se puede deshacer)",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );
            if (c != JOptionPane.YES_OPTION) {
                return;
            }

            ResultadoOperacion r = boletoDAO.anularSiDisponible(id);

            if (r != null && r.isOk()) {
                JOptionPane.showMessageDialog(this, r.getMensaje(), "OK", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, (r == null) ? "Error desconocido." : r.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAnularActionPerformed

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        actualizarBotonAnular();
    }//GEN-LAST:event_tablaMouseClicked

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        if (!esVendedor()) {
            JOptionPane.showMessageDialog(this, "No tiene permiso para vender.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (estadoViaje != ViajeEstado.PROGRAMADO) {
            JOptionPane.showMessageDialog(this,
                    "No se pueden vender boletos porque el viaje está " + estadoViaje,
                    "Acción no permitida",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.util.List<Integer> ids = getIdsSeleccionados();
        if (ids.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione uno o más boletos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!todosDisponiblesSeleccionados()) {
            JOptionPane.showMessageDialog(this,
                    "Para vender, seleccione solo boletos en estado DISPONIBLE.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.awt.Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
        VentaBoletosDialog dlg = new VentaBoletosDialog(w, viajeId, ids);
        dlg.setVisible(true);

        if (dlg.isGuardado()) {
            cargarTabla();
        }
    }//GEN-LAST:event_btnVenderActionPerformed

    private void maximizarHorizontal(Window parent) {
        java.awt.Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        int h = getHeight();
        if (h <= 0) {
            h = 450;
        }

        setSize(screen.width, h);
        setLocation(0, Math.max(0, (screen.height - h) / 2));
        setLocationRelativeTo(parent);
    }

    private void cargarTabla() {
        List<BoletoView> lista = boletoDAO.listarPorViaje(viajeId);
        model.setRowCount(0);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (BoletoView b : lista) {
            String fecha = (b.getFechaCompra() == null) ? "" : sdf.format(b.getFechaCompra());

            model.addRow(new Object[]{
                b.getId(),
                Utils.cargarString(b.getCodigo()),
                b.getAsientoNumero(),
                b.getClienteCedula() == null ? "" : b.getClienteCedula(),
                b.getPasajeroCedula() == null ? "" : b.getPasajeroCedula(),
                b.getTipoPasajeroNombre() == null ? "" : b.getTipoPasajeroNombre(),
                b.getPrecioBase() == null ? "" : b.getPrecioBase(),
                b.getDescuentoMonto() == null ? "" : b.getDescuentoMonto(),
                b.getPrecioFinal() == null ? "" : b.getPrecioFinal(),
                b.getIvaMonto() == null ? "" : b.getIvaMonto(),
                b.getIvaUsado() == null ? "" : b.getIvaUsado(),
                Utils.cargarString(b.getEstado()),
                fecha
            });
        }

        btnAnular.setEnabled(false);
        btnVender.setEnabled(false);
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

    private BoletoEstado getEstadoBoletoSeleccionado() {
        int row = tabla.getSelectedRow();
        if (row == -1) {
            return null;
        }

        int modelRow = tabla.convertRowIndexToModel(row);
        String estadoTxt = String.valueOf(model.getValueAt(modelRow, 11)).trim(); // col "Estado"
        return BoletoEstado.fromCode(estadoTxt);
    }

    private void ocultarColumnaId() {
        var col = tabla.getColumnModel().getColumn(0);
        col.setMinWidth(0);
        col.setMaxWidth(0);
        col.setPreferredWidth(0);
        col.setResizable(false);
    }

    private boolean esVendedor() {
        return com.transporte.gestion.global.modelos.SesionGlobal.tienePermiso("VENDEDOR");
    }

    private void configurarPermisos() {

        boolean viajeProgramado = (estadoViaje == ViajeEstado.PROGRAMADO);

        btnAnular.setEnabled(false);
        btnVender.setEnabled(false);

        btnVender.setVisible(esVendedor());

        if (!viajeProgramado) {
            btnAnular.setToolTipText("No se pueden anular boletos porque el viaje está " + estadoViaje);
            btnVender.setToolTipText("No se pueden vender boletos porque el viaje está " + estadoViaje);
        }
    }

    private void actualizarBotonAnular() {
        btnAnular.setEnabled(false);
        btnVender.setEnabled(false);

        if (estadoViaje != ViajeEstado.PROGRAMADO) {
            return;
        }

        int[] rows = selectedModelRows();
        if (rows.length == 0) {
            return;
        }

        boolean esVendedor = esVendedor();
        boolean todosDisponibles = allEstados(rows, BoletoEstado.DISPONIBLE);
        boolean todosVendidos = allEstados(rows, BoletoEstado.VENDIDO);

        btnVender.setEnabled(esVendedor && todosDisponibles);

        if (esVendedor) {
            btnAnular.setEnabled(rows.length == 1 && todosVendidos);
        } else {
            btnAnular.setEnabled(todosDisponibles);
        }
    }

    private java.util.List<Integer> getIdsSeleccionados() {
        int[] rows = tabla.getSelectedRows();
        java.util.List<Integer> ids = new java.util.ArrayList<>();
        if (rows == null || rows.length == 0) {
            return ids;
        }

        for (int viewRow : rows) {
            int modelRow = tabla.convertRowIndexToModel(viewRow);
            Object v = model.getValueAt(modelRow, 0);
            if (v instanceof Integer) {
                ids.add((Integer) v);
            }
        }
        return ids;
    }

    private boolean todosDisponiblesSeleccionados() {
        int[] rows = tabla.getSelectedRows();
        if (rows == null || rows.length == 0) {
            return false;
        }

        for (int viewRow : rows) {
            int modelRow = tabla.convertRowIndexToModel(viewRow);
            String estado = String.valueOf(model.getValueAt(modelRow, 11)).trim().toUpperCase();
            if (!BoletoEstado.DISPONIBLE.getCode().equalsIgnoreCase(estado)) {
                return false;
            }
        }
        return true;
    }

    private int[] selectedModelRows() {
        int[] viewRows = tabla.getSelectedRows();
        if (viewRows == null) {
            return new int[0];
        }

        int[] modelRows = new int[viewRows.length];
        for (int i = 0; i < viewRows.length; i++) {
            modelRows[i] = tabla.convertRowIndexToModel(viewRows[i]);
        }
        return modelRows;
    }

    private String estadoEnModelRow(int modelRow) {
        return String.valueOf(model.getValueAt(modelRow, 11)).trim().toUpperCase();
    }

    private boolean allEstados(int[] modelRows, BoletoEstado estado) {
        for (int r : modelRows) {
            if (!estado.getCode().equalsIgnoreCase(estadoEnModelRow(r))) {
                return false;
            }
        }
        return modelRows.length > 0;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnVender;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
