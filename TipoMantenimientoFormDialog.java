package com.transporte.gestion.vista.Mantenimiento;

import com.transporte.gestion.controlador.Mantenimiento.TipoMantenimientoController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Mantenimiento.TipoMantenimiento;
import java.awt.Window;
import javax.swing.JOptionPane;

public class TipoMantenimientoFormDialog extends javax.swing.JDialog {

    private final TipoMantenimientoController tipoMantenimientoController;
    private TipoMantenimiento tipoMantenimientoEditar = null;
    private boolean guardado = false;

    public boolean isGuardado() {
        return guardado;
    }

    public TipoMantenimientoFormDialog(Window parent, TipoMantenimientoController tipoMantenimientoController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.tipoMantenimientoController = tipoMantenimientoController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Tipo de Mantenimiento");
        if (lblTitulo != null) lblTitulo.setText("Crear Tipo de Mantenimiento");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtDescripcion = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Crear Tipo de Mantenimiento");

        jLabel5.setText("Nombres:");

        jLabel6.setText("Descripcion:");

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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnGuardar)
                        .addGap(90, 90, 90)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(lblTitulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(19, Short.MAX_VALUE))
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
        TipoMantenimiento t = leerFormulario();

        if (!validarFormulario(t)) {
            return;
        }

        ResultadoOperacion r;
        if (tipoMantenimientoEditar == null) {
            r = tipoMantenimientoController.crear(t);
        } else {
            t.setId(tipoMantenimientoEditar.getId());
            r = tipoMantenimientoController.actualizar(t);
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

    private TipoMantenimiento leerFormulario() {

        TipoMantenimiento t = new TipoMantenimiento();
        t.setNombre(txtNombre.getText().trim());
        t.setDescripcion(txtDescripcion.getText().trim());
        return t;
    }

    private boolean validarFormulario(TipoMantenimiento t) {

        String nombre = (t.getNombre() == null) ? "" : t.getNombre().trim();
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

        String desc = (t.getDescripcion() == null) ? "" : t.getDescripcion().trim();
        if (!desc.isEmpty()) {
            if (!validarCampo(ValidadorDatos.validarLongitud(desc, 0, 200), "Descripción", txtDescripcion)) {
                return false;
            }
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

    public void setTipoMantenimientoEditar(TipoMantenimiento t) {

        this.tipoMantenimientoEditar = t;

        setTitle("Actualizar Tipo de Mantenimiento");
        lblTitulo.setText("Actualizar Tipo de Mantenimiento");
        txtNombre.setText(t.getNombre() == null ? "" : t.getNombre());
        txtDescripcion.setText(t.getDescripcion() == null ? "" : t.getDescripcion());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JFormattedTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
