package com.transporte.gestion.vista.Flota;

import com.transporte.gestion.vista.Personas.ChoferPickerDialog;
import com.transporte.gestion.controlador.Flota.BusController;
import com.transporte.gestion.controlador.Flota.ChoferBusController;
import com.transporte.gestion.controlador.Personas.ChoferController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Flota.BusView;
import com.transporte.gestion.modelo.Flota.ChoferBus;
import com.transporte.gestion.modelo.Personas.ChoferView;

import java.awt.Window;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ChoferBusFormDialog extends javax.swing.JDialog {

    private final ChoferBusController choferBusController;
    private final ChoferController choferController = new ChoferController();
    private final BusController busController = new BusController();

    private boolean guardado = false;

    private Integer choferIdSeleccionado = null;
    private String busPlacaSeleccionada = null;

    public boolean isGuardado() {
        return guardado;
    }

    public ChoferBusFormDialog(Window parent, ChoferBusController choferBusController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.choferBusController = choferBusController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Asignar Chofer a Bus");

        lblChoferInfo.setText("(No seleccionado)");
        lblBusInfo.setText("(No seleccionado)");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnElegirChofer = new javax.swing.JButton();
        lblChoferInfo = new javax.swing.JLabel();
        lblBusInfo = new javax.swing.JLabel();
        btnElegirBus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Asignar chofer a bus");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnElegirChofer.setText("Elegir");
        btnElegirChofer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirChoferActionPerformed(evt);
            }
        });

        lblChoferInfo.setText("Chofer...");

        lblBusInfo.setText("Bus...");

        btnElegirBus.setText("Elegir");
        btnElegirBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElegirBusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblBusInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnElegirBus))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblChoferInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnElegirChofer))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(203, 203, 203)
                        .addComponent(btnGuardar)
                        .addGap(44, 44, 44)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChoferInfo)
                    .addComponent(btnElegirChofer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBusInfo)
                    .addComponent(btnElegirBus))
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int r = JOptionPane.showConfirmDialog(
                this,
                "¿Desea cancelar? Los cambios no se guardarán.",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (r == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        ChoferBus cb = leerFormulario();
        if (cb == null) {
            return;
        }
        if (!validarFormulario(cb)) {
            return;
        }

        ResultadoOperacion r = choferBusController.crear(cb);

        if (r != null && r.isOk()) {
            JOptionPane.showMessageDialog(this, r.getMensaje(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            guardado = true;
            dispose();
        } else {
            String msg = (r == null) ? "Error desconocido." : r.getMensaje();
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnElegirChoferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirChoferActionPerformed
        Window w = (Window) javax.swing.SwingUtilities.getWindowAncestor(this);

        ChoferPickerDialog dlg = new ChoferPickerDialog(w, choferController);
        dlg.setVisible(true);

        ChoferView c = dlg.getChoferSeleccionado();
        if (c != null) {
            choferIdSeleccionado = c.getId();
            String info = Utils.cargarString(c.getIdentificacion()) + " | "
                    + Utils.cargarString(c.getNombres()) + " "
                    + Utils.cargarString(c.getApellidos());
            lblChoferInfo.setText(info);
        }
    }//GEN-LAST:event_btnElegirChoferActionPerformed

    private void btnElegirBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElegirBusActionPerformed
        Window w = (Window) javax.swing.SwingUtilities.getWindowAncestor(this);

        BusPickerDialog dlg = new BusPickerDialog(w, busController);
        dlg.setVisible(true);

        BusView b = dlg.getBusSeleccionado();
        if (b != null) {
            busPlacaSeleccionada = Utils.cargarString(b.getPlaca()).trim().toUpperCase();
            String info = busPlacaSeleccionada + " | "
                    + Utils.cargarString(b.getMarcaNombre()) + " - "
                    + Utils.cargarString(b.getModeloNombre());
            lblBusInfo.setText(info);
        }
    }//GEN-LAST:event_btnElegirBusActionPerformed

    private ChoferBus leerFormulario() {
        ChoferBus cb = new ChoferBus();

        if (choferIdSeleccionado == null || choferIdSeleccionado <= 0) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un chofer.",
                    "Datos inválidos",
                    JOptionPane.WARNING_MESSAGE
            );
            btnElegirChofer.requestFocus();
            return null;
        }
        cb.setChoferId(choferIdSeleccionado);

        if (busPlacaSeleccionada == null || busPlacaSeleccionada.trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un bus.",
                    "Datos inválidos",
                    JOptionPane.WARNING_MESSAGE
            );
            btnElegirBus.requestFocus();
            return null;
        }
        cb.setBusId(busPlacaSeleccionada.trim().toUpperCase());

        return cb;
    }

    private boolean validarFormulario(ChoferBus cb) {

        if (cb.getChoferId() == null || cb.getChoferId() <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un chofer.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            btnElegirChofer.requestFocus();
            return false;
        }

        String placa = Utils.cargarString(cb.getBusId()).trim().toUpperCase();
        if (!validarCampo(ValidadorDatos.validarNoVacio(placa), "Bus", btnElegirBus)) {
            return false;
        }
        if (!placa.matches("^[A-Z0-9\\-]{6,8}$")) {
            JOptionPane.showMessageDialog(this,
                    "Placa inválida. Use 6 a 8 caracteres (letras/números y opcional '-'). Ej: ABC-1234",
                    "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            btnElegirBus.requestFocus();
            return false;
        }

        return true;
    }

    private boolean validarCampo(ValidadorResultado r, String campo, javax.swing.JComponent focus) {
        if (r == null || r.isValid()) {
            return true;
        }

        JOptionPane.showMessageDialog(
                this,
                campo + ": " + r.getMessage(),
                "Datos inválidos",
                JOptionPane.WARNING_MESSAGE
        );
        if (focus != null) {
            focus.requestFocus();
        }
        return false;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnElegirBus;
    private javax.swing.JButton btnElegirChofer;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBusInfo;
    private javax.swing.JLabel lblChoferInfo;
    // End of variables declaration//GEN-END:variables
}
