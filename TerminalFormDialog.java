package com.transporte.gestion.vista.Infraestructura;

import com.transporte.gestion.controlador.Infraestructura.TerminalController;
import com.transporte.gestion.controlador.Infraestructura.CiudadController;
import com.transporte.gestion.controlador.Infraestructura.ProvinciaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Infraestructura.TerminalView;
import com.transporte.gestion.modelo.Infraestructura.Terminal;
import com.transporte.gestion.modelo.Infraestructura.Ciudad;
import com.transporte.gestion.modelo.Infraestructura.Provincia;

import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class TerminalFormDialog extends javax.swing.JDialog {

    private final ProvinciaController provinciaController = new ProvinciaController();
    private final TerminalController terminalController;
    private final CiudadController ciudadController = new CiudadController();
    private Terminal terminalEditar = null;
    private boolean guardado = false;

    private java.util.List<Provincia> provinciasCache = new java.util.ArrayList<>();
    private java.util.List<Ciudad> ciudadesProvinciaCache = new java.util.ArrayList<>();

    public boolean isGuardado() {
        return guardado;
    }

    public TerminalFormDialog(Window parent, TerminalController terminalController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.terminalController = terminalController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Terminal");

        cargarProvinciasEnCombo();
        cargarCiudadesPorProvincia();

        cmbProvincia.addActionListener(e -> onProvinciaChanged());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbCiudad = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNombre = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtTelefono = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbProvincia = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Terminal");

        jLabel2.setText("Ciudad:");

        jLabel3.setText("Nombre:");

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

        txtNombre.setColumns(20);
        txtNombre.setRows(5);
        jScrollPane2.setViewportView(txtNombre);

        jLabel4.setText("Direccion:");

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane3.setViewportView(txtDireccion);

        txtTelefono.setColumns(20);
        txtTelefono.setRows(5);
        jScrollPane4.setViewportView(txtTelefono);

        jLabel5.setText("Telefono:");

        jLabel6.setText("Provincia:");

        cmbProvincia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(btnGuardar)
                        .addGap(45, 45, 45)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCiudad, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                            .addComponent(cmbProvincia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(34, 34, 34))
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
        Terminal t = leerFormulario();

        if (!validarFormulario(t)) {
            return;
        }

        ResultadoOperacion r;
        if (terminalEditar == null) {
            r = terminalController.crear(t);
        } else {
            t.setId(terminalEditar.getId());
            r = terminalController.actualizar(t);
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

    private Terminal leerFormulario() {
        Terminal t = new Terminal();

        int idx = cmbCiudad.getSelectedIndex();
        if (idx >= 0 && idx < ciudadesProvinciaCache.size()) {
            t.setCiudadId(ciudadesProvinciaCache.get(idx).getId());
        }

        t.setNombre(txtNombre.getText().trim());
        t.setDireccion(txtDireccion.getText().trim());
        t.setTelefono(txtTelefono.getText().trim());

        return t;
    }

    private boolean validarFormulario(Terminal t) {

        int idxProv = cmbProvincia.getSelectedIndex();
        if (idxProv < 0 || idxProv >= provinciasCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una provincia.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbProvincia.requestFocus();
            return false;
        }

        int idxCiudad = cmbCiudad.getSelectedIndex();
        if (idxCiudad < 0 || idxCiudad >= ciudadesProvinciaCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una ciudad.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbCiudad.requestFocus();
            return false;
        }

        if (!validarCampo(ValidadorDatos.validarNoVacio(t.getNombre()), "Nombre", txtNombre)) {
            return false;
        }

        if (!t.getDireccion().isEmpty()) {
            if (!validarCampo(ValidadorDatos.validarLongitud(t.getDireccion(), 3, 150), "Dirección", txtDireccion)) {
                return false;
            }
        }

        if (!t.getTelefono().isEmpty()) {
            if (!validarCampo(ValidadorDatos.validarTelefono(t.getTelefono()), "Teléfono", txtTelefono)) {
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

    public void setTerminalEditar(TerminalView t) {
        this.terminalEditar = new Terminal();
        this.terminalEditar.setId(t.getId());
        this.terminalEditar.setCiudadId(t.getCiudadId());

        setTitle("Actualizar Terminal");
        jLabel1.setText("Actualizar Terminal");

        Ciudad ciudad = ciudadController.buscarPorId(t.getCiudadId());
        int provinciaId = (ciudad != null) ? ciudad.getProvinciaId() : -1;

        int idxProv = -1;
        for (int i = 0; i < provinciasCache.size(); i++) {
            if (provinciasCache.get(i).getId() == provinciaId) {
                idxProv = i;
                break;
            }
        }
        if (idxProv >= 0) {
            cmbProvincia.setSelectedIndex(idxProv);
        }

        cargarCiudadesPorProvincia();

        int idxCiudad = -1;
        for (int i = 0; i < ciudadesProvinciaCache.size(); i++) {
            if (ciudadesProvinciaCache.get(i).getId() == t.getCiudadId()) {
                idxCiudad = i;
                break;
            }
        }
        if (idxCiudad >= 0) {
            cmbCiudad.setSelectedIndex(idxCiudad);
        }

        if (t.getNombre() != null) {
            txtNombre.setText(t.getNombre());
        }
        if (t.getDireccion() != null) {
            txtDireccion.setText(t.getDireccion());
        }
        if (t.getTelefono() != null) {
            txtTelefono.setText(t.getTelefono());
        }

        cmbProvincia.setEnabled(false);
        cmbCiudad.setEnabled(false);
    }

    private void cargarProvinciasEnCombo() {
        provinciasCache = provinciaController.listar();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Provincia p : provinciasCache) {
            model.addElement(p.getNombre());
        }
        cmbProvincia.setModel(model);

        if (model.getSize() > 0) {
            cmbProvincia.setSelectedIndex(0);
        }
    }

    private void cargarCiudadesPorProvincia() {
        int idxProv = cmbProvincia.getSelectedIndex();
        if (idxProv < 0 || idxProv >= provinciasCache.size()) {
            ciudadesProvinciaCache = new java.util.ArrayList<>();
            cmbCiudad.setModel(new DefaultComboBoxModel<>());
            cmbCiudad.setEnabled(false);
            return;
        }

        int provinciaId = provinciasCache.get(idxProv).getId();
        ciudadesProvinciaCache = ciudadController.listarPorProvincia(provinciaId);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Ciudad c : ciudadesProvinciaCache) {
            model.addElement(c.getNombre());
        }
        cmbCiudad.setModel(model);
        cmbCiudad.setEnabled(model.getSize() > 0);

        if (model.getSize() > 0) {
            cmbCiudad.setSelectedIndex(0);
        }
    }

    private void onProvinciaChanged() {
        cargarCiudadesPorProvincia();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbCiudad;
    private javax.swing.JComboBox<String> cmbProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextArea txtNombre;
    private javax.swing.JTextArea txtTelefono;
    // End of variables declaration//GEN-END:variables
}
