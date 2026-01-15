package com.transporte.gestion.vista.Operaciones;

import com.transporte.gestion.controlador.Operaciones.RutaHorarioController;
import com.transporte.gestion.controlador.Operaciones.RutaController;
import com.transporte.gestion.controlador.Operaciones.HorarioController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Ruta;
import com.transporte.gestion.modelo.Operaciones.Horario;
import com.transporte.gestion.modelo.Operaciones.RutaHorario;
import com.transporte.gestion.modelo.Operaciones.RutaHorarioView;
import com.transporte.gestion.modelo.Operaciones.RutaView;

import java.awt.Window;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class RutaHorarioFormDialog extends javax.swing.JDialog {

    private final RutaHorarioController controller;
    private final RutaController rutaController = new RutaController();
    private final HorarioController horarioController = new HorarioController();

    private boolean guardado = false;

    private List<RutaView> listaRutas;
    private List<Horario> listaHorarios;

    public boolean isGuardado() {
        return guardado;
    }

    public RutaHorarioFormDialog(Window parent, RutaHorarioController controller) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.controller = controller;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Asignar Ruta a Horario");

        cargarCombos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbHorario = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblPersonaInfo = new javax.swing.JLabel();
        lblPersonaInfo1 = new javax.swing.JLabel();
        cmbRuta = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Asignación Ruta - Horario");

        cmbHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHorarioActionPerformed(evt);
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

        lblPersonaInfo.setText("Ruta:");

        lblPersonaInfo1.setText("Horario:");

        cmbRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRutaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel1)
                .addContainerGap(191, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPersonaInfo)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnGuardar)
                        .addGap(87, 87, 87)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPersonaInfo1)
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbRuta, 0, 304, Short.MAX_VALUE)
                            .addComponent(cmbHorario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersonaInfo)
                    .addComponent(cmbRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPersonaInfo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(86, 86, 86))
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
        RutaHorario nuevo = leerFormulario();
        if (nuevo == null) return;

        ResultadoOperacion r = controller.crear(nuevo);

        if (r.isOk()) {
            JOptionPane.showMessageDialog(this, r.getMensaje());
            guardado = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, r.getMensaje(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbHorarioActionPerformed

    private void cmbRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRutaActionPerformed
        RutaView selRuta = (RutaView) cmbRuta.getSelectedItem();
        if (selRuta == null) return;

        listaHorarios = horarioController.listar();

        DefaultComboBoxModel modelHorario = new DefaultComboBoxModel();
        for (Horario h : listaHorarios) {
            modelHorario.addElement(h);
        }
        cmbHorario.setModel(modelHorario);
    }//GEN-LAST:event_cmbRutaActionPerformed

    private RutaHorario leerFormulario() {

        RutaView selRuta = (RutaView) cmbRuta.getSelectedItem();
        Horario selHorario = (Horario) cmbHorario.getSelectedItem();

        if (selRuta == null || selHorario == null) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar Ruta y Horario.",
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return new RutaHorario(
                selRuta.getId(),
                selHorario.getId()
        );
    }

    public void setObjetoEditar(RutaHorarioView v) {

        setTitle("Editar Asignación");
        jLabel1.setText("Editar Asignación");

        if (listaRutas != null) {
            for (int i = 0; i < listaRutas.size(); i++) {
                if (listaRutas.get(i).getId() == v.getRutaId()) {
                    cmbRuta.setSelectedIndex(i);
                    break;
                }
            }
        }

        if (listaHorarios != null) {
            for (int i = 0; i < listaHorarios.size(); i++) {
                if (listaHorarios.get(i).getId() == v.getHorarioId()) {
                    cmbHorario.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void cargarCombos() {

        listaRutas = rutaController.listarView();
        DefaultComboBoxModel modelRuta = new DefaultComboBoxModel();

        if (listaRutas != null) {
            for (RutaView r : listaRutas) {
                modelRuta.addElement(r);
            }
        }
        cmbRuta.setModel(modelRuta);

        listaHorarios = horarioController.listar();
        DefaultComboBoxModel modelHorario = new DefaultComboBoxModel();

        if (listaHorarios != null) {
            for (Horario h : listaHorarios) {
                modelHorario.addElement(h);
            }
        }
        cmbHorario.setModel(modelHorario);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbHorario;
    private javax.swing.JComboBox<String> cmbRuta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblPersonaInfo;
    private javax.swing.JLabel lblPersonaInfo1;
    // End of variables declaration//GEN-END:variables
}
