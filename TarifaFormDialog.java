package com.transporte.gestion.vista.Operaciones;

import com.transporte.gestion.controlador.Operaciones.RutaHorarioController;
import com.transporte.gestion.controlador.Operaciones.TarifaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.RutaHorario;
import com.transporte.gestion.modelo.Operaciones.RutaHorarioView;
import com.transporte.gestion.modelo.Operaciones.Tarifa;
import com.transporte.gestion.modelo.Operaciones.TarifaView;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class TarifaFormDialog extends javax.swing.JDialog {

    private final TarifaController tarifaController;
    private final RutaHorarioController rutaHorarioController = new RutaHorarioController();

    private TarifaView tarifaEditarView = null;
    private boolean guardado = false;

    public boolean isGuardado() {
        return guardado;
    }

    public TarifaFormDialog(Window parent, TarifaController tarifaController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.tarifaController = tarifaController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Tarifa");

        cargarRutaHorarios();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbRutaHorario = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Tarifa");

        cmbRutaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRutaHorarioActionPerformed(evt);
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

        jLabel3.setText("Ruta-Horario:");

        jLabel4.setText("Precio: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnGuardar)
                .addGap(78, 78, 78)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPrecio)
                                    .addComponent(cmbRutaHorario, 0, 240, Short.MAX_VALUE))
                                .addGap(42, 42, 42))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbRutaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
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
        Tarifa t = leerFormulario();
        if (t == null) {
            return;
        }
        if (!validarFormulario(t)) {
            return;
        }

        ResultadoOperacion res;
        if (tarifaEditarView == null) {
            res = tarifaController.crear(t);
        } else {
            t.setId(tarifaEditarView.getId());
            res = tarifaController.actualizar(t);
        }

        if (res != null && res.isOk()) {
            JOptionPane.showMessageDialog(this, res.getMensaje(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            guardado = true;
            dispose();
        } else {
            String msg = (res == null) ? "Error desconocido." : res.getMensaje();
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbRutaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRutaHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRutaHorarioActionPerformed

    private void cargarRutaHorarios() {
        java.awt.event.ActionListener[] listeners = cmbRutaHorario.getActionListeners();
        for (java.awt.event.ActionListener al : listeners) {
            cmbRutaHorario.removeActionListener(al);
        }

        List<RutaHorarioView> lista = rutaHorarioController.listarView();
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for (RutaHorarioView rh : lista) {
            model.addElement(rh);
        }

        cmbRutaHorario.setModel(model);

        if (model.getSize() > 0) {
            cmbRutaHorario.setSelectedIndex(0);
        }

        for (java.awt.event.ActionListener al : listeners) {
            cmbRutaHorario.addActionListener(al);
        }
    }

    private Tarifa leerFormulario() {
        Tarifa t = new Tarifa();

        RutaHorarioView rh = (RutaHorarioView) cmbRutaHorario.getSelectedItem();
        
        if (rh == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una ruta y horario.", "Datos", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        t.setRutaHorarioId(rh.getId());

        String precioStr = txtPrecio.getText().trim();
        if (precioStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El precio es obligatorio.", "Datos", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        try {
            t.setPrecioBase(new BigDecimal(precioStr));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El precio debe ser numérico.", "Datos", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        t.setEstado(true);
        return t;
    }

    private boolean validarFormulario(Tarifa t) {

        if (t.getRutaHorarioId() == null || t.getRutaHorarioId() <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un Ruta-Horario válido.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            cmbRutaHorario.requestFocus();
            return false;
        }

        if (t.getPrecioBase() == null) {
            JOptionPane.showMessageDialog(this, "El precio es obligatorio.", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return false;
        }

        if (t.getPrecioBase().compareTo(BigDecimal.ZERO) <= 0) {
            JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0.", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return false;
        }

        return true;
    }

    public void setTarifaEditar(TarifaView v) {
        this.tarifaEditarView = v;
        setTitle("Actualizar Tarifa");
        jLabel1.setText("Actualizar Tarifa");

        if (v.getPrecio() != null) {
            txtPrecio.setText(v.getPrecio().toString());
        } else {
            txtPrecio.setText("");
        }

        DefaultComboBoxModel model = (DefaultComboBoxModel) cmbRutaHorario.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            RutaHorarioView rh = (RutaHorarioView) model.getElementAt(i);
            if (rh.getId() != null && rh.getId().equals(v.getRutaHorarioId())) {
                cmbRutaHorario.setSelectedIndex(i);
                break;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Object> cmbRutaHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
