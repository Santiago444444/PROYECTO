package com.transporte.gestion.vista.Personas;

import com.transporte.gestion.controlador.Personas.ChoferController;
import com.transporte.gestion.controlador.Personas.PersonaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Personas.Chofer;
import com.transporte.gestion.modelo.Personas.ChoferView;
import com.transporte.gestion.modelo.Personas.Persona;
import java.awt.Window;
import javax.swing.JOptionPane;

public class ChoferFormDialog extends javax.swing.JDialog {

    private final ChoferController choferController;
    private final PersonaController personaController = new PersonaController();
    private ChoferView choferEditarView = null;
    private boolean guardado = false;
    private Integer personaIdSeleccionada = null;

    public boolean isGuardado() {
        return guardado;
    }

    public ChoferFormDialog(Window parent, ChoferController choferController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.choferController = choferController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Chofer");

        btnCambiarPersona.setEnabled(true);
        lblPersonaInfo.setText("(No seleccionada)");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCambiarPersona = new javax.swing.JButton();
        lblPersonaInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCaducidad = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Chofer");

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

        btnCambiarPersona.setText("Elegir");
        btnCambiarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarPersonaActionPerformed(evt);
            }
        });

        lblPersonaInfo.setText("Elegir Persona:");

        jLabel3.setText("Tipo Licencia:");

        jLabel4.setText("Caducidad:");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", "E", "F" }));
        cmbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTipoItemStateChanged(evt);
            }
        });
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        jButton1.setText("Crear Persona");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(lblPersonaInfo)
                            .addComponent(jLabel4))
                        .addGap(130, 130, 130)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCambiarPersona)
                                .addGap(168, 168, 168))
                            .addComponent(txtCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(50, 50, 50)
                        .addComponent(btnCancelar)
                        .addGap(72, 72, 72)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersonaInfo)
                    .addComponent(btnCambiarPersona))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtCaducidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(jButton1))
                .addContainerGap(40, Short.MAX_VALUE))
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
        Chofer c = leerFormulario();
        if (c == null) {
            return;
        }
        if (!validarFormulario(c)) {
            return;
        }

        ResultadoOperacion r;
        if (choferEditarView == null) {
            r = choferController.crear(c);
        } else {
            c.setId(choferEditarView.getId());
            r = choferController.actualizar(c);
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

    private void btnCambiarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarPersonaActionPerformed
        Window w = (Window) javax.swing.SwingUtilities.getWindowAncestor(this);
        PersonaPickerDialog dlg = new PersonaPickerDialog(w, personaController);
        dlg.setVisible(true);

        Persona p = dlg.getPersonaSeleccionada();
        if (p != null) {
            personaIdSeleccionada = p.getId();
            String info = p.getIdentificacion() + " | " + Utils.cargarString(p.getNombre()) + " " + Utils.cargarString(p.getApellido());
            lblPersonaInfo.setText(info);
        }
    }//GEN-LAST:event_btnCambiarPersonaActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void cmbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTipoItemStateChanged

    }//GEN-LAST:event_cmbTipoItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Window w = (Window) javax.swing.SwingUtilities.getWindowAncestor(this);

        PersonaFormDialog dlg = new PersonaFormDialog(w, personaController);
        dlg.setVisible(true);

        if (dlg.isGuardado()) {
            Integer personaId = dlg.getPersonaGuardada().getId();
            if (personaId != null) {
                Persona p = personaController.buscarPorId(personaId);

                if (p != null) {
                    personaIdSeleccionada = personaId;

                    String info = p.getIdentificacion() + " | " + Utils.cargarString(p.getNombre()) + " " + Utils.cargarString(p.getApellido());
                    lblPersonaInfo.setText(info);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private Chofer leerFormulario() {
        Chofer c = new Chofer();

        if (personaIdSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una persona.", "Datos", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        c.setPersonaId(personaIdSeleccionada);
        c.setTipoLicencia(cmbTipo.getSelectedItem().toString());

        String fechaTxt = txtCaducidad.getText().trim();
        if (fechaTxt.isEmpty() || fechaTxt.contains("_")) {
            c.setFechaCaducidad(null);
        } else {
            try {
                // Convertir dd/MM/yyyy a java.sql.Date
                java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate ld = java.time.LocalDate.parse(fechaTxt, fmt);
                c.setFechaCaducidad(java.sql.Date.valueOf(ld));
            } catch (Exception ex) {
                c.setFechaCaducidad(null);
            }
        }
        return c;
    }

    private boolean validarFormulario(Chofer c) {

        if (c.getPersonaId() <= 0) return false;

        String fechaTxt = txtCaducidad.getText().trim();
        if (fechaTxt.contains("_") || c.getFechaCaducidad() == null) {
             JOptionPane.showMessageDialog(this, "Fecha caducidad inválida (dd/MM/yyyy).", "Error", JOptionPane.WARNING_MESSAGE);
             txtCaducidad.requestFocus();
             return false;
        }
        
        if (c.getFechaCaducidad().before(new java.util.Date())) {
             JOptionPane.showMessageDialog(this, "La licencia está caducada.", "Aviso", JOptionPane.WARNING_MESSAGE);
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

    public void setChoferEditar(ChoferView c) {
        this.choferEditarView = c;
        setTitle("Actualizar Chofer");
        jLabel1.setText("Actualizar Chofer");

        personaIdSeleccionada = c.getPersonaId();
        String info = Utils.cargarString(c.getIdentificacion()) + " | " + Utils.cargarString(c.getNombres());
        lblPersonaInfo.setText(info);
        btnCambiarPersona.setEnabled(false);

        cmbTipo.setSelectedItem(c.getTipoLicencia());
        
        if (c.getFechaCaducidad() != null) {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
            txtCaducidad.setText(sdf.format(c.getFechaCaducidad()));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarPersona;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblPersonaInfo;
    private javax.swing.JTextField txtCaducidad;
    // End of variables declaration//GEN-END:variables
}
