package com.transporte.gestion.vista.Infraestructura;

import com.transporte.gestion.controlador.Infraestructura.CooperativaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Infraestructura.Cooperativa;
import java.awt.Window;
import javax.swing.JOptionPane;

public class CooperativaFormDialog extends javax.swing.JDialog {

    private final CooperativaController cooperativaController;
    private Cooperativa cooperativaEditar = null;
    private boolean guardado = false;

    public boolean isGuardado() {
        return guardado;
    }

    public CooperativaFormDialog(Window parent, CooperativaController cooperativaController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.cooperativaController = cooperativaController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Cooperativa");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtTelefono = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Crear Cooperativa");

        jLabel4.setText("Ruc:");

        jLabel5.setText("Nombres:");

        jLabel6.setText("Telefono:");

        jLabel7.setText("Dirección:");

        jLabel8.setText("Email:");

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane1.setViewportView(txtDireccion);

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

        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtNombre)
                        .addComponent(txtRuc)
                        .addComponent(txtMail)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                        .addComponent(txtTelefono))
                    .addComponent(lblTitulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtMail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(15, 15, 15))
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
        Cooperativa c = leerFormulario();

        if (!validarFormulario(c)) {
            return;
        }

        ResultadoOperacion r;
        if (cooperativaEditar == null) {
            r = cooperativaController.crear(c);
        } else {
            c.setId(cooperativaEditar.getId());
            r = cooperativaController.actualizar(c);
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
     
    private Cooperativa leerFormulario() {
        
        Cooperativa c = new Cooperativa();

        c.setNombre(txtNombre.getText().trim());
        c.setRuc(txtRuc.getText().trim());
        c.setDireccion(txtDireccion.getText().trim());
        c.setTelefono(txtTelefono.getText().trim());
        c.setMail(txtMail.getText().trim());
      

        return c;
    }

    private boolean validarFormulario(Cooperativa c) {

        String ruc = (c.getRuc() == null) ? "" : c.getRuc().trim();
        
        if (ruc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El RUC es obligatorio.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtRuc.requestFocus();
            return false;
        }

        ValidadorResultado rRuc = ValidadorDatos.validarRuc(ruc);
        if (!validarCampo(rRuc, "RUC", txtRuc)) {
            return false;
        }

        String nombre = (c.getNombre() == null) ? "" : c.getNombre().trim();
        if (nombre.isEmpty()) {
             JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
             txtNombre.requestFocus();
             return false;
        }
       
        if (!validarCampo(ValidadorDatos.validarTexto(nombre), "Nombre", txtNombre)) {
            return false;
        }

        String tel = (c.getTelefono() == null) ? "" : c.getTelefono().trim();
        if (!tel.isEmpty()) {
            if (!validarCampo(ValidadorDatos.validarTelefono(tel), "Teléfono", txtTelefono)) {
                return false;
            }
        }

        String mail = (c.getMail() == null) ? "" : c.getMail().trim();
        if (!mail.isEmpty()) {
            if (!validarCampo(ValidadorDatos.validarCorreo(mail), "Email", txtMail)) {
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

    public void setCooperativaEditar(Cooperativa c) {
        
        this.cooperativaEditar = c;

        setTitle("Actualizar Cooperativa");
        lblTitulo.setText("Actualizar Cooperativa");

        txtNombre.setText(c.getNombre() == null ? "" : c.getNombre());
        txtRuc.setText(c.getRuc() == null ? "" : c.getRuc());
        
        txtRuc.setEnabled(false);

        txtDireccion.setText(c.getDireccion() == null ? "" : c.getDireccion());
        txtTelefono.setText(c.getTelefono() == null ? "" : c.getTelefono());
        txtMail.setText(c.getMail() == null ? "" : c.getMail());
      
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
