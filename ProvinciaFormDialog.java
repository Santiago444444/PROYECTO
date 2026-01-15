package com.transporte.gestion.vista.Infraestructura;

import com.transporte.gestion.controlador.Infraestructura.ProvinciaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Infraestructura.Provincia;
import java.awt.Window;
import javax.swing.JOptionPane;

public class ProvinciaFormDialog extends javax.swing.JDialog {

    private final ProvinciaController provinciaController;
    private Provincia provinciaEditar = null;
    private boolean guardado = false;

    public boolean isGuardado() {
        return guardado;
    }

    public ProvinciaFormDialog(Window parent, ProvinciaController provinciaController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.provinciaController = provinciaController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Provincia");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Crear Provincia");

        jLabel5.setText("Nombre:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnGuardar)
                        .addGap(56, 56, 56)
                        .addComponent(btnCancelar))
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93))
            .addGroup(layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblTitulo)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(85, 85, 85))
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
        Provincia p = leerFormulario();

        if (!validarFormulario(p)) {
            return;
        }

        ResultadoOperacion r;
        if (provinciaEditar == null) {
            r = provinciaController.crear(p);
        } else {
            p.setId(provinciaEditar.getId());
            r = provinciaController.actualizar(p);
        }

        if (r != null && r.isOk()) {
            JOptionPane.showMessageDialog(this, r.getMensaje(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            guardado = true;
            dispose();
        } else {
            String msg = (r == null) ? "Error desconocido." : r.getMensaje();
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private Provincia leerFormulario() {
        Provincia p = new Provincia();
        p.setNombre(txtNombre.getText().trim());
        return p;
    }

    private boolean validarFormulario(Provincia p) {
        String nombre = (p.getNombre() == null) ? "" : p.getNombre().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return false;
        }
        
        if (!validarCampo(ValidadorDatos.validarTextoSoloLetras(nombre), "Nombre", txtNombre)) {
            return false;
        }

        if (!validarCampo(ValidadorDatos.validarLongitud(nombre, 2, 50), "Nombre", txtNombre)) {
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

    public void setProvinciaEditar(Provincia p) {

        this.provinciaEditar = p;

        setTitle("Actualizar Provincia");
        if (lblTitulo != null) lblTitulo.setText("Actualizar Provincia");

        txtNombre.setText(p.getNombre() == null ? "" : p.getNombre());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
