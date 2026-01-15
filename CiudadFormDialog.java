package com.transporte.gestion.vista.Infraestructura;

import com.transporte.gestion.controlador.Infraestructura.CiudadController;
import com.transporte.gestion.controlador.Infraestructura.ProvinciaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Infraestructura.CiudadView;
import com.transporte.gestion.modelo.Infraestructura.Ciudad;
import com.transporte.gestion.modelo.Infraestructura.Provincia;

import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class CiudadFormDialog extends javax.swing.JDialog {

    private final CiudadController ciudadController;
    private final ProvinciaController provinciaController = new ProvinciaController();
    private Ciudad ciudadEditar = null;
    private boolean guardado = false;
    
    private java.util.List<Provincia> provinciasCache = new java.util.ArrayList<>();

    public boolean isGuardado() {
        return guardado;
    }

    public CiudadFormDialog(Window parent, CiudadController ciudadController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.ciudadController = ciudadController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Ciudad");
        
        cargarProvinciasEnCombo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbProvincia = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNombre = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Ciudad");

        jLabel2.setText("Provincia:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnGuardar)
                        .addGap(46, 46, 46)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbProvincia, 0, 291, Short.MAX_VALUE)
                                .addComponent(jScrollPane2)))))
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
                    .addComponent(cmbProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(15, Short.MAX_VALUE))
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
        Ciudad c = leerFormulario();

        if (!validarFormulario(c)) {
            return;
        }

        ResultadoOperacion r;
        if (ciudadEditar == null) {
            r = ciudadController.crear(c);
        } else {
            c.setId(ciudadEditar.getId());
            r = ciudadController.actualizar(c);
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

    private Ciudad leerFormulario() {
        Ciudad c = new Ciudad();

        int idx = cmbProvincia.getSelectedIndex();
        if (idx >= 0 && idx < provinciasCache.size()) {
            c.setProvinciaId(provinciasCache.get(idx).getId());
        }

        String texto = txtNombre.getText().trim();
        c.setNombre(texto);

        return c;
    }

    private boolean validarFormulario(Ciudad c) {
        int idx = cmbProvincia.getSelectedIndex();
        if (idx < 0 || idx >= provinciasCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una provincia.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbProvincia.requestFocus();
            return false;
        }

        String texto = txtNombre.getText();
        ValidadorResultado r = ValidadorDatos.validarNoVacio(texto);
        if (!validarCampo(r, "Nombre", txtNombre)) {
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

    public void setCiudadEditar(CiudadView c) {
        this.ciudadEditar = new Ciudad();
        this.ciudadEditar.setId(c.getId());
        this.ciudadEditar.setProvinciaId(c.getProvinciaId());

        setTitle("Actualizar Ciudad");
        jLabel1.setText("Actualizar Ciudad");

        int index = -1;
        for (int i = 0; i < provinciasCache.size(); i++) {
            if (provinciasCache.get(i).getId() == c.getProvinciaId()) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            cmbProvincia.setSelectedIndex(index);
        }

        if (c.getNombre() != null) {
            txtNombre.setText(c.getNombre());
        } else {
            txtNombre.setText("");
        }

        cmbProvincia.setEnabled(false);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbProvincia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtNombre;
    // End of variables declaration//GEN-END:variables
}
