package com.transporte.gestion.vista.Mantenimiento;

import com.transporte.gestion.controlador.Mantenimiento.OrdenMantenimientoController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Mantenimiento.OrdenMantenimiento;
import com.transporte.gestion.modelo.Mantenimiento.Taller;
import java.util.List;
import javax.swing.JOptionPane;

public class MantenimientoInicioDialog extends javax.swing.JDialog {

    private OrdenMantenimientoController controller;
    private String busId;
    private boolean guardado = false;

    public MantenimientoInicioDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
    }

    public void setController(OrdenMantenimientoController controller) {
        this.controller = controller;
        cargarTalleres();
    }

    public void setBusId(String busId) {
        this.busId = busId;
        if (lblTitulo != null) {
            lblTitulo.setText("Mantenimiento Bus: " + busId);
        }
    }

    public boolean isGuardado() {
        return guardado;
    }

    private void cargarTalleres() {
        cmbTaller.removeAllItems();
        if (controller != null) {
            List<Taller> lista = controller.listarTalleres();
            for (Taller t : lista) {
                cmbTaller.addItem(t);
            }
        }
    }

    private OrdenMantenimiento leerFormulario() {
        OrdenMantenimiento orden = new OrdenMantenimiento();
        
        if (busId == null || busId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha especificado el Bus.", "Error Interno", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        orden.setBusId(busId);

        Taller taller = (Taller) cmbTaller.getSelectedItem();
        if (taller != null) {
            orden.setTallerId(taller.getId());
        }

        try {
            String kmStr = txtKilometraje.getText().trim();
            if (!kmStr.isEmpty()) {
                orden.setKilometraje(Integer.parseInt(kmStr));
            } else {
                orden.setKilometraje(0);
            }
        } catch (NumberFormatException e) {
            orden.setKilometraje(-1);
        }

        orden.setObservaciones(txtObservaciones.getText().trim());

        return orden;
    }

    private boolean validarFormulario(OrdenMantenimiento orden) {
        if (orden.getTallerId() <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un taller.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbTaller.requestFocus();
            return false;
        }

        if (orden.getKilometraje() < 0) {
            JOptionPane.showMessageDialog(this, "El kilometraje debe ser un número válido positivo.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtKilometraje.requestFocus();
            return false;
        }

        if (orden.getObservaciones().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una observación sobre la falla.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtObservaciones.requestFocus();
            return false;
        }
        
        if (orden.getObservaciones().length() > 200) {
            JOptionPane.showMessageDialog(this, "La observación es muy larga (máx 200 caracteres).", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtObservaciones.requestFocus();
            return false;
        }

        return true;
    }

    private void guardar() {
        OrdenMantenimiento orden = leerFormulario();
        if (orden == null) return; 

        if (!validarFormulario(orden)) {
            return; 
        }

        ResultadoOperacion resultado = controller.iniciarMantenimiento(orden);

        if (resultado.isOk()) {
            JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            guardado = true;
            setVisible(false);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, resultado.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancelar() {
        guardado = false;
        setVisible(false);
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblPersonaInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtObservaciones = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtKilometraje = new javax.swing.JTextField();
        cmbTaller = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Mantenimiento");

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

        lblPersonaInfo.setText("Taller:");

        jLabel3.setText("Observaciones: ");

        jLabel4.setText("Kilometraje:");

        txtKilometraje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKilometrajeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPersonaInfo)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(118, 118, 118)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTaller, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(lblPersonaInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lblTitulo)
                        .addGap(7, 7, 7)
                        .addComponent(cmbTaller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtKilometraje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtKilometrajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKilometrajeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKilometrajeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Taller> cmbTaller;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblPersonaInfo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtKilometraje;
    private javax.swing.JTextField txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
