package com.transporte.gestion.vista.Seguridad;

import com.transporte.gestion.controlador.Seguridad.PermisosController;
import com.transporte.gestion.controlador.Seguridad.RolController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Seguridad.PermisosView;
import com.transporte.gestion.modelo.Seguridad.Permisos;
import com.transporte.gestion.modelo.Seguridad.Rol;
import java.awt.Window;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class PermisosFormDialog extends javax.swing.JDialog {

    private final PermisosController permisosController;
    private final RolController rolController = new RolController();
    private Permisos permisosEditar = null;
    private boolean guardado = false;
    private java.util.List<Rol> rolesCache = new java.util.ArrayList<>();

    public boolean isGuardado() {
        return guardado;
    }

    public PermisosFormDialog(Window parent, PermisosController permisosController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.permisosController = permisosController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Permisos");
        cargarRolesEnCombo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPermisos = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Permisos");

        jLabel2.setText("Rol:");

        jLabel3.setText("Permisos:");

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

        txtPermisos.setColumns(20);
        txtPermisos.setRows(5);
        jScrollPane2.setViewportView(txtPermisos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbRol, 0, 291, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(23, Short.MAX_VALUE))
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
        Permisos p = leerFormulario();

        if (!validarFormulario(p)) {
            return;
        }

        ResultadoOperacion r;
        if (permisosEditar == null) {
            r = permisosController.crear(p);
        } else {
            p.setId(permisosEditar.getId());
            r = permisosController.actualizar(p);
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

    private Permisos leerFormulario() {
        Permisos p = new Permisos();

        int idx = cmbRol.getSelectedIndex();
        if (idx >= 0 && idx < rolesCache.size()) {
            p.setRolId(rolesCache.get(idx).getId());
        }

        String texto = txtPermisos.getText().trim();
        Set<String> setPermisos = parsePermisos(texto);
        p.setPermisos(setPermisos);

        return p;
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

    private boolean validarFormulario(Permisos p) {

        int idx = cmbRol.getSelectedIndex();
        if (idx < 0 || idx >= rolesCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un rol.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbRol.requestFocus();
            return false;
        }

        String texto = txtPermisos.getText();
        ValidadorResultado r = ValidadorDatos.validarNoVacio(texto);
        if (!validarCampo(r, "Permisos", txtPermisos)) {
            return false;
        }

        Set<String> perms = parsePermisos(texto);
        if (perms.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe ingresar al menos un permiso válido (uno por línea o separados por coma).",
                    "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtPermisos.requestFocus();
            return false;
        }

        for (String perm : perms) {
            if (!perm.matches("^[A-Z0-9_\\.\\-]{2,60}$")) {
                JOptionPane.showMessageDialog(this,
                        "Permiso inválido: '" + perm + "'. Use MAYÚSCULAS, números y _ . - (2 a 60 chars).",
                        "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                txtPermisos.requestFocus();
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

    public void setPermisosEditar(PermisosView p) {

        this.permisosEditar = new Permisos();
        this.permisosEditar.setId(p.getId());
        this.permisosEditar.setRolId(p.getRolId());

        setTitle("Actualizar Permisos");
        jLabel1.setText("Actualizar Permisos");

        int index = -1;
        for (int i = 0; i < rolesCache.size(); i++) {
            if (rolesCache.get(i).getId() == p.getRolId()) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            cmbRol.setSelectedIndex(index);
        }

        if (p.getPermisos() != null) {
            txtPermisos.setText(String.join("\n", p.getPermisos()));
        } else {
            txtPermisos.setText("");
        }

        cmbRol.setEnabled(false);
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
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtPermisos;
    // End of variables declaration//GEN-END:variables
}
