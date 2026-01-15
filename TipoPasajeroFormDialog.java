package com.transporte.gestion.vista.Ventas;

import com.transporte.gestion.controlador.Ventas.TipoPasajeroController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Ventas.TipoPasajero;
import java.awt.Window;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class TipoPasajeroFormDialog extends javax.swing.JDialog {

    private final TipoPasajeroController tipoPasajeroController;
    private TipoPasajero tipoPasajeroEditar = null;
    private boolean guardado = false;

    public boolean isGuardado() {
        return guardado;
    }

    public TipoPasajeroFormDialog(Window parent, TipoPasajeroController tipoPasajeroController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.tipoPasajeroController = tipoPasajeroController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Tipo de Pasajero");
        lblTitulo.setText("Crear Tipo de Pasajero");
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
        txtDescuento = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Crear Tipo de Pasajero");

        jLabel5.setText("Nombres:");

        jLabel6.setText("Descuento:");

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

        txtDescuento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat(""))));

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
                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitulo)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnGuardar)
                        .addGap(90, 90, 90)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblTitulo)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescuento)))
                .addGap(152, 152, 152)
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
        TipoPasajero t = leerFormulario();

        if (!validarFormulario(t)) {
            return;
        }

        ResultadoOperacion r;
        if (tipoPasajeroEditar == null) {
            r = tipoPasajeroController.crear(t);
        } else {
            t.setId(tipoPasajeroEditar.getId());
            r = tipoPasajeroController.actualizar(t);
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

    private TipoPasajero leerFormulario() {
        TipoPasajero t = new TipoPasajero();
        t.setNombre(txtNombre.getText().trim());

        String descTxt = txtDescuento.getText().trim().replace(",", ".");
        
        if (descTxt.isEmpty()) {
            t.setDescuento(null);
        } else {
            try {
                t.setDescuento(new BigDecimal(descTxt));
            } catch (Exception e) {
                t.setDescuento(null);
            }
        }
        return t;
    }

    private boolean validarFormulario(TipoPasajero t) {

        String nombre = (t.getNombre() == null) ? "" : t.getNombre().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return false;
        }

        if (!validarCampo(ValidadorDatos.validarTextoSoloLetras(nombre), "Nombre", txtNombre)) {
            return false;
        }

        if (!validarCampo(ValidadorDatos.validarLongitud(nombre, 2, 30), "Nombre", txtNombre)) {
            return false;
        }
        
        String descTxt = txtDescuento.getText().trim().replace(",", ".");

        if (descTxt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El descuento es obligatorio.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtDescuento.requestFocus();
            return false;
        }
        if (!validarCampo(ValidadorDatos.validarDecimal(descTxt), "Descuento", txtDescuento)) {
            return false;
        }

        if (t.getDescuento() != null) {
            if (t.getDescuento().compareTo(BigDecimal.ZERO) < 0 || t.getDescuento().compareTo(new BigDecimal("100")) > 0) {
                JOptionPane.showMessageDialog(this, "El descuento debe estar entre 0 y 100.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                txtDescuento.requestFocus();
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

    public void setTipoPasajeroEditar(TipoPasajero t) {
        this.tipoPasajeroEditar = t;
        setTitle("Actualizar Tipo de Pasajero");
        lblTitulo.setText("Actualizar Tipo de Pasajero");
        
        txtNombre.setText(t.getNombre() == null ? "" : t.getNombre());
        txtDescuento.setText(t.getDescuento() == null ? "" : t.getDescuento().toString());

        txtNombre.setEditable(false);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JFormattedTextField txtDescuento;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
