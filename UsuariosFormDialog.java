package com.transporte.gestion.vista.Seguridad;

import com.transporte.gestion.controlador.Personas.PersonaController;
import com.transporte.gestion.controlador.Seguridad.RolController;
import com.transporte.gestion.controlador.Seguridad.UsuarioController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Personas.Persona;
import com.transporte.gestion.modelo.Seguridad.UsuariosView;
import com.transporte.gestion.modelo.Seguridad.Rol;
import com.transporte.gestion.modelo.Seguridad.Usuario;
import com.transporte.gestion.vista.Personas.PersonaFormDialog;
import com.transporte.gestion.vista.Personas.PersonaPickerDialog;
import java.awt.Window;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class UsuariosFormDialog extends javax.swing.JDialog {

    private final UsuarioController usuarioController;
    private final RolController rolController = new RolController();
    private final PersonaController personaController = new PersonaController();
    private UsuariosView usuarioEditarView = null;
    private boolean guardado = false;
    private java.util.List<Rol> rolesCache = new java.util.ArrayList<>();
    private Integer personaIdSeleccionada = null;

    public boolean isGuardado() {
        return guardado;
    }

    public UsuariosFormDialog(Window parent, UsuarioController usuarioController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.usuarioController = usuarioController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Usuario");
        cargarRolesEnCombo();

        btnResetPassword.setVisible(false);
        btnCambiarPersona.setEnabled(true);
        lblPersonaInfo.setText("(No seleccionada)");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCrearPerson = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnResetPassword = new javax.swing.JButton();
        btnCambiarPersona = new javax.swing.JButton();
        lblPersonaInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        btnCrearPerson.setText("Crear Persona");
        btnCrearPerson.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearPersonActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear usuarios");

        jLabel2.setText("Rol:");

        cmbRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRolActionPerformed(evt);
            }
        });

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

        btnResetPassword.setText("Reset");
        btnResetPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPasswordActionPerformed(evt);
            }
        });

        btnCambiarPersona.setText("Elegir");
        btnCambiarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarPersonaActionPerformed(evt);
            }
        });

        lblPersonaInfo.setText("Persona...");

        jLabel3.setText("Usuario:");

        jLabel4.setText("Contraseña:");

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
                .addGap(179, 179, 179)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(lblPersonaInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jButton1)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnGuardar)
                                .addGap(27, 27, 27)
                                .addComponent(btnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnResetPassword))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnCambiarPersona, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPersonaInfo)
                            .addComponent(btnCambiarPersona))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel3)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnResetPassword)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnGuardar))
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(25, 25, 25))))
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
        Usuario u = leerFormulario();
        if (u == null) {
            return;
        }
        if (!validarFormulario(u)) {
            return;
        }

        ResultadoOperacion r;
        String pass = (u.getContrasenia() == null) ? "" : u.getContrasenia().trim();
        if (usuarioEditarView == null) {
            r = usuarioController.crear(u, pass);
        } else {
            u.setId(usuarioEditarView.getId());

            boolean actualizarPass = !pass.isEmpty();
            r = usuarioController.actualizar(u, actualizarPass, pass);
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
            String info = p.getIdentificacion() + " | " + Utils.cargarString(p.getMail());
            lblPersonaInfo.setText(info);
        }
    }//GEN-LAST:event_btnCambiarPersonaActionPerformed

    private void cmbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRolActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRolActionPerformed

    private void btnResetPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPasswordActionPerformed
        JOptionPane.showMessageDialog(this,
                "Escriba una nueva contraseña y presione Guardar.\n"
                + "La contraseña solo se actualiza si usted escribe una nueva.",
                "Reset password",
                JOptionPane.INFORMATION_MESSAGE);

        txtPassword.setText("");
        txtPassword.requestFocus();
        txtPassword.setEnabled(true);
    }//GEN-LAST:event_btnResetPasswordActionPerformed

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

    private Usuario leerFormulario() {
        Usuario u = new Usuario();

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
        u.setPersonaId(personaIdSeleccionada);

        int idx = cmbRol.getSelectedIndex();
        if (idx >= 0 && idx < rolesCache.size()) {
            u.setRolId(rolesCache.get(idx).getId());
        }

        u.setUsuario(txtUsuario.getText().trim());

        String pass = new String(txtPassword.getPassword()).trim();
        u.setContrasenia(pass);

        return u;
    }

    private Set<String> parsePermisos(String texto) {
        Set<String> out = new LinkedHashSet<>();
        if (texto == null) {
            return out;
        }

        String[] parts = texto.split("[,\\n]+");
        Arrays.stream(parts)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(String::toUpperCase)
                .forEach(out::add);

        return out;
    }

    private boolean validarFormulario(Usuario u) {

        if (u.getPersonaId() <= 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una persona.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            btnCambiarPersona.requestFocus();
            return false;
        }

        int idx = cmbRol.getSelectedIndex();
        if (idx < 0 || idx >= rolesCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un rol.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbRol.requestFocus();
            return false;
        }

        if (!validarCampo(ValidadorDatos.validarNoVacio(u.getUsuario()), "Usuario", txtUsuario)) {
            return false;
        }
        if (!u.getUsuario().matches("^[a-zA-Z0-9_\\.\\-]{4,30}$")) {
            JOptionPane.showMessageDialog(this,
                    "Usuario inválido. Use letras/números y _ . - (4 a 30 caracteres).",
                    "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtUsuario.requestFocus();
            return false;
        }

        String pass = (u.getContrasenia() == null) ? "" : u.getContrasenia().trim();
        if (usuarioEditarView == null) {
            if (!validarCampo(ValidadorDatos.validarNoVacio(pass), "Contraseña", txtPassword)) {
                return false;
            }
            if (pass.length() < 6) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres.",
                        "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                txtPassword.requestFocus();
                return false;
            }
        } else {
            if (!pass.isEmpty() && pass.length() < 6) {
                JOptionPane.showMessageDialog(this, "La nueva contraseña debe tener al menos 6 caracteres.",
                        "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                txtPassword.requestFocus();
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

    public void setUsuarioEditar(UsuariosView u) {
        this.usuarioEditarView = u;

        setTitle("Actualizar Usuario");
        jLabel1.setText("Actualizar Usuario");

        // persona (no editable por seguridad, o sí editable si quieres)
        personaIdSeleccionada = u.getPersonaId();
        lblPersonaInfo.setText(Utils.cargarString(u.getIdentificacion()) + " | " + Utils.cargarString(u.getMail()));
        btnCambiarPersona.setEnabled(false); // recomendado: no cambiar persona en update

        // rol
        int index = -1;
        for (int i = 0; i < rolesCache.size(); i++) {
            if (rolesCache.get(i).getId() == u.getRolId()) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            cmbRol.setSelectedIndex(index);
        }

        txtUsuario.setText(Utils.cargarString(u.getUsuario()));

        txtPassword.setText("");

        // habilita botón reset
    
        txtPassword.setEnabled(false);
        btnResetPassword.setVisible(true);
    }

    private void cargarRolesEnCombo() {
        rolesCache = rolController.listar();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Rol r : rolesCache) {
            model.addElement(r.getNombre());
        }

        cmbRol.setModel(model);

        if (model.getSize() > 0) {
            cmbRol.setSelectedIndex(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCambiarPersona;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrearPerson;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnResetPassword;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblPersonaInfo;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
