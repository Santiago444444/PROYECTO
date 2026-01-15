package com.transporte.gestion.vista.Flota;

import com.transporte.gestion.controlador.Flota.ModeloController;
import com.transporte.gestion.controlador.Flota.MarcaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Flota.ModeloView;
import com.transporte.gestion.modelo.Flota.Modelo;
import com.transporte.gestion.modelo.Flota.Marca;

import java.awt.Window;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class ModeloFormDialog extends javax.swing.JDialog {

    private final ModeloController modeloController;
    private final MarcaController marcaController = new MarcaController();
    private Modelo modeloEditar = null;
    private boolean guardado = false;
    
    private java.util.List<Marca> marcasCache = new java.util.ArrayList<>();

    public boolean isGuardado() {
        return guardado;
    }

    public ModeloFormDialog(Window parent, ModeloController modeloController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.modeloController = modeloController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Modelo");
        cargarMarcasEnCombo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbMarca = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNombre = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Modelo");

        jLabel2.setText("Marca:");

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
                                .addComponent(cmbMarca, 0, 291, Short.MAX_VALUE)
                                .addComponent(jScrollPane2)))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        Modelo m = leerFormulario();

        if (!validarFormulario(m)) {
            return;
        }

        ResultadoOperacion r;
        if (modeloEditar == null) {
            r = modeloController.crear(m);
        } else {
            m.setId(modeloEditar.getId());
            r = modeloController.actualizar(m);
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

    private Modelo leerFormulario() {
        Modelo m = new Modelo();

        int idx = cmbMarca.getSelectedIndex();
        if (idx >= 0 && idx < marcasCache.size()) {
            m.setMarcaId(marcasCache.get(idx).getId());
        }

        String texto = txtNombre.getText().trim();
        m.setNombre(texto);

        return m;
    }

    private boolean validarFormulario(Modelo m) {

        int idx = cmbMarca.getSelectedIndex();
        if (idx < 0 || idx >= marcasCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una marca.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbMarca.requestFocus();
            return false;
        }

        String texto = txtNombre.getText();
        ValidadorResultado r = ValidadorDatos.validarNoVacio(texto);
        if (!validarCampo(r, "Nombre", txtNombre)) {
            return false;
        }

        if (texto.length() > 50) {
             JOptionPane.showMessageDialog(this, "El nombre es muy largo.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
             txtNombre.requestFocus();
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

    public void setModeloEditar(ModeloView m) {
        this.modeloEditar = new Modelo();
        this.modeloEditar.setId(m.getId());
        this.modeloEditar.setMarcaId(m.getMarcaId());

        setTitle("Actualizar Modelo");
        jLabel1.setText("Actualizar Modelo");

        int index = -1;
        for (int i = 0; i < marcasCache.size(); i++) {
            if (marcasCache.get(i).getId() == m.getMarcaId()) {
                index = i;
                break;
            }
        }
        if (index >= 0) {
            cmbMarca.setSelectedIndex(index);
        }

        if (m.getNombre() != null) {
            txtNombre.setText(m.getNombre());
        } else {
            txtNombre.setText("");
        }

        cmbMarca.setEnabled(false);
    }

    private void cargarMarcasEnCombo() {
        marcasCache = marcaController.listar(); 

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Marca marca : marcasCache) {
            model.addElement(marca.getNombre());
        }

        cmbMarca.setModel(model);

        if (model.getSize() > 0) {
            cmbMarca.setSelectedIndex(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtNombre;
    // End of variables declaration//GEN-END:variables
}
