package com.transporte.gestion.vista.Personas;

import com.transporte.gestion.controlador.Personas.PersonaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Personas.Persona;
import java.awt.Window;
import javax.swing.JOptionPane;

public class PersonaFormDialog extends javax.swing.JDialog {

    private final PersonaController personaController;
    private Persona personaEditar = null;
    private boolean guardado = false;

    public boolean isGuardado() {
        return guardado;
    }

    public PersonaFormDialog(Window parent, PersonaController personaController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.personaController = personaController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Persona");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        cmbTipoIdentificacion = new javax.swing.JComboBox<>();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        chkDiscapacidad = new javax.swing.JCheckBox();
        cmbGenero = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtTelefono = new javax.swing.JFormattedTextField();
        txtFechaNacimiento = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Persona");

        jLabel2.setText("Tipo identificación:");

        jLabel3.setText("Identificación:");

        jLabel4.setText("Apellidos:");

        jLabel5.setText("Nombres:");

        jLabel6.setText("Telefono:");

        jLabel7.setText("Dirección:");

        jLabel8.setText("Email:");

        jLabel9.setText("Fecha Nacimiento:");

        jLabel10.setText("Discapacidad:");

        jLabel11.setText("Genero:");

        txtIdentificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdentificacionActionPerformed(evt);
            }
        });

        cmbTipoIdentificacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CEDULA", "RUC", "PASAPORTE" }));

        chkDiscapacidad.setText("Si");
        chkDiscapacidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiscapacidadActionPerformed(evt);
            }
        });

        cmbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "F", "M" }));

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

        try {
            txtFechaNacimiento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbTipoIdentificacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chkDiscapacidad)
                            .addComponent(txtIdentificacion)
                            .addComponent(txtNombres)
                            .addComponent(txtApellidos)
                            .addComponent(txtEmail)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                            .addComponent(txtTelefono)
                            .addComponent(txtFechaNacimiento)
                            .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbTipoIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(chkDiscapacidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(cmbGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void chkDiscapacidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiscapacidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkDiscapacidadActionPerformed

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
        Persona p = leerFormulario();

        if (!validarFormulario(p)) {
            return;
        }

        ResultadoOperacion r;
        if (personaEditar == null) {
            r = personaController.crear(p);
        } else {
            p.setId(personaEditar.getId());
            r = personaController.actualizar(p);
        }

        if (r != null && r.isOk()) {
            personaEditar = personaController.buscarPorIdentificacion(p.getIdentificacion());

            JOptionPane.showMessageDialog(this, r.getMensaje(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            guardado = true;
            dispose();
        } else {
            String msg = (r == null) ? "Error desconocido." : r.getMensaje();
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private Persona leerFormulario() {

        Persona p = new Persona();

        p.setTipoIdentificacion(cmbTipoIdentificacion.getSelectedItem().toString());
        p.setIdentificacion(txtIdentificacion.getText().trim());
        p.setNombre(txtNombres.getText().trim());
        p.setApellido(txtApellidos.getText().trim());
        p.setTelefono(txtTelefono.getText().trim());
        p.setMail(txtEmail.getText().trim());
        p.setDireccion(txtDireccion.getText().trim());
        p.setDiscapacidad(chkDiscapacidad.isSelected());
        p.setGenero(cmbGenero.getSelectedItem().toString());

        String fechaTxt = txtFechaNacimiento.getText().trim();

        if (fechaTxt.isEmpty() || fechaTxt.contains("_")) {
            p.setFechaNacimiento(null);
        } else {
            try {
                java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate ld = java.time.LocalDate.parse(fechaTxt, fmt);
                p.setFechaNacimiento(java.sql.Date.valueOf(ld));
            } catch (Exception ex) {
                p.setFechaNacimiento(null);
            }
        }

        return p;
    }

    private boolean validarFormulario(Persona p) {

        String tipo = cmbTipoIdentificacion.getSelectedItem().toString().trim().toUpperCase();

        String id = (p.getIdentificacion() == null)
                ? ""
                : p.getIdentificacion().trim().toUpperCase();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La identificación es obligatoria.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtIdentificacion.requestFocus();
            return false;
        }

        ValidadorResultado rId;
        switch (tipo) {
            case "CEDULA":
                if (!id.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(this, "La cédula debe tener exactamente 10 dígitos.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                    txtIdentificacion.requestFocus();
                    return false;
                }
                rId = ValidadorDatos.validarCedula(id);
                break;

            case "RUC":
                if (!id.matches("\\d{13}")) {
                    JOptionPane.showMessageDialog(this, "El RUC debe tener exactamente 13 dígitos. solo perona natural", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                    txtIdentificacion.requestFocus();
                    return false;
                }
                rId = ValidadorDatos.validarRuc(id);
                break;

            case "PASAPORTE":
                if (id.length() < 6 || id.length() > 20) {
                    JOptionPane.showMessageDialog(this, "El pasaporte debe tener entre 6 y 15 caracteres.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                    txtIdentificacion.requestFocus();
                    return false;
                }
                if (!id.matches("^[A-Z0-9]+$")) {
                    JOptionPane.showMessageDialog(this, "El pasaporte debe ser alfanumérico (sin espacios ni símbolos).", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                    txtIdentificacion.requestFocus();
                    return false;
                }
                rId = ValidadorResultado.ok();
                break;

            default:
                JOptionPane.showMessageDialog(this, "Tipo de identificación no reconocido.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                cmbTipoIdentificacion.requestFocus();
                return false;
        }

        if (rId != null && !rId.isValid()) {
            JOptionPane.showMessageDialog(this, rId.getMessage(), "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtIdentificacion.requestFocus();
            return false;
        }

        if (!validarCampo(ValidadorDatos.validarTextoSoloLetras(p.getNombre()), "Nombres", txtNombres)) {
            return false;
        }

        if (!validarCampo(ValidadorDatos.validarTextoSoloLetras(p.getApellido()), "Apellidos", txtApellidos)) {
            return false;
        }

        String tel = (p.getTelefono() == null) ? "" : p.getTelefono().trim();
        if (!tel.isEmpty() && !validarCampo(ValidadorDatos.validarTelefono(tel), "Teléfono", txtTelefono)) {
            return false;
        }

        String mail = (p.getMail() == null) ? "" : p.getMail().trim();
        if (!mail.isEmpty() && !validarCampo(ValidadorDatos.validarCorreo(mail), "Email", txtEmail)) {
            return false;
        }

        String fechaTxt = txtFechaNacimiento.getText().trim();
        if (!fechaTxt.isEmpty() && !fechaTxt.contains("_")) {

            if (!fechaTxt.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
                JOptionPane.showMessageDialog(this, "Fecha inválida. Use dd/MM/yyyy.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                txtFechaNacimiento.requestFocus();
                return false;
            }

            try {
                java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
                java.time.LocalDate.parse(fechaTxt, fmt);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Fecha inválida. Use dd/MM/yyyy.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                txtFechaNacimiento.requestFocus();
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

    public void setPersonaEditar(Persona p) {
        this.personaEditar = p;

        setTitle("Actualizar Persona");
        jLabel1.setText("Actualizar Persona");

        cmbTipoIdentificacion.setSelectedItem(p.getTipoIdentificacion());
        txtIdentificacion.setText(p.getIdentificacion());

        cmbTipoIdentificacion.setEnabled(false);
        txtIdentificacion.setEnabled(false);

        txtNombres.setText(p.getNombre());
        txtApellidos.setText(p.getApellido());
        txtTelefono.setText(p.getTelefono() == null ? "" : p.getTelefono());
        txtEmail.setText(p.getMail() == null ? "" : p.getMail());
        txtDireccion.setText(p.getDireccion() == null ? "" : p.getDireccion());
        chkDiscapacidad.setSelected(p.getDiscapacidad());

        if (p.getGenero() != null) {
            cmbGenero.setSelectedItem(p.getGenero());
        }

        if (p.getFechaNacimiento() != null) {

            java.time.LocalDate ld;

            java.util.Date fecha = p.getFechaNacimiento();

            if (fecha instanceof java.sql.Date) {
                // java.sql.Date tiene toLocalDate()
                ld = ((java.sql.Date) fecha).toLocalDate();
            } else {
                // java.util.Date normal
                ld = fecha.toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate();
            }

            java.time.format.DateTimeFormatter fmt
                    = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

            txtFechaNacimiento.setText(ld.format(fmt));

        } else {
            txtFechaNacimiento.setText("");
        }

    }

    public Persona getPersonaGuardada() {
        return personaEditar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox chkDiscapacidad;
    private javax.swing.JComboBox<String> cmbGenero;
    private javax.swing.JComboBox<String> cmbTipoIdentificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JFormattedTextField txtFechaNacimiento;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
