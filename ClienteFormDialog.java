package com.transporte.gestion.vista.Personas;

import com.transporte.gestion.controlador.Personas.ClienteController;
import com.transporte.gestion.controlador.Personas.PersonaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Personas.Cliente;
import com.transporte.gestion.modelo.Personas.ClienteView;
import com.transporte.gestion.modelo.Personas.Persona;
import java.awt.Window;
import javax.swing.JOptionPane;

public class ClienteFormDialog extends javax.swing.JDialog {

    private final ClienteController clienteController;
    private final PersonaController personaController = new PersonaController();
    private ClienteView clienteEditarView = null;
    private boolean guardado = false;
    private Integer personaIdSeleccionada = null;

    public boolean isGuardado() {
        return guardado;
    }

    public ClienteFormDialog(Window parent, ClienteController clienteController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.clienteController = clienteController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Cliente");

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
        txtObservaciones = new javax.swing.JTextField();
        btnCrearPerson = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Cliente");

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

        jLabel3.setText("Observaciones: ");

        btnCrearPerson.setText("Crear Persona");
        btnCrearPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPersonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(179, 179, 179)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPersonaInfo)
                                    .addComponent(jLabel3))
                                .addGap(118, 118, 118)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCambiarPersona)
                                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 35, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCrearPerson)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblPersonaInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnCambiarPersona)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar)
                    .addComponent(btnCrearPerson))
                .addContainerGap())
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
        Cliente c = leerFormulario();
        if (c == null) {
            return;
        }
        if (!validarFormulario(c)) {
            return;
        }

        ResultadoOperacion r;
        if (clienteEditarView == null) {
            r = clienteController.crear(c);
        } else {
            c.setId(clienteEditarView.getId());
            r = clienteController.actualizar(c);
        }

        if (r != null && r.isOk()) {
            clienteEditarView = clienteController.buscarPorPersonaId(c.getPersonaId());

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

    private void btnCrearPersonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearPersonActionPerformed

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
    }//GEN-LAST:event_btnCrearPersonActionPerformed

    private Cliente leerFormulario() {
        Cliente c = new Cliente();

        if (personaIdSeleccionada == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una persona.",
                    "Datos inválidos",
                    JOptionPane.WARNING_MESSAGE
            );
            btnCambiarPersona.requestFocus();
            return null;
        }
        c.setPersonaId(personaIdSeleccionada);
        c.setObservaciones(txtObservaciones.getText().trim());

        return c;
    }

    private boolean validarFormulario(Cliente c) {

        if (c.getPersonaId() <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una persona.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            btnCambiarPersona.requestFocus();
            return false;
        }

        if (!c.getObservaciones().isEmpty()) {
            if (!validarCampo(ValidadorDatos.validarLongitud(c.getObservaciones(), 0, 200), "Observaciones", txtObservaciones)) {
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

    public void setClienteEditar(ClienteView c) {
        this.clienteEditarView = c;

        setTitle("Actualizar Cliente");
        jLabel1.setText("Actualizar Cliente");

        personaIdSeleccionada = c.getPersonaId();
        String info = Utils.cargarString(c.getIdentificacion()) + " | " + Utils.cargarString(c.getNombres()) + " " + Utils.cargarString(c.getApellidos());
        lblPersonaInfo.setText(info);
        btnCambiarPersona.setEnabled(false);
        btnCrearPerson.setVisible(false);
        btnCrearPerson.setEnabled(false);

        txtObservaciones.setText(Utils.cargarString(c.getObservaciones()));
    }

    public Integer getPersonaIdSeleccionada() {
        return personaIdSeleccionada;
    }

    public ClienteView getClienteEditarView() {
        return clienteEditarView;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarPersona;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrearPerson;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblPersonaInfo;
    private javax.swing.JTextField txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
