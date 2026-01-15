package com.transporte.gestion.vista.Infraestructura;

import com.transporte.gestion.controlador.Infraestructura.CopTermController;
import com.transporte.gestion.controlador.Infraestructura.CooperativaController;
import com.transporte.gestion.controlador.Infraestructura.TerminalController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Infraestructura.CopTerm;
import com.transporte.gestion.modelo.Infraestructura.Cooperativa;
import com.transporte.gestion.modelo.Infraestructura.Terminal;

import java.awt.Window;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class CopTermFormDialog extends javax.swing.JDialog {

    private final CopTermController controller;
    private final CooperativaController coopController = new CooperativaController();
    private final TerminalController termController = new TerminalController();

    private CopTerm objetoViejo = null; 
    private boolean guardado = false;
    
    private List<Cooperativa> listaCoops;
    private List<Terminal> listaTerms;

    public boolean isGuardado() {
        return guardado;
    }

    public CopTermFormDialog(Window parent, CopTermController controller) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.controller = controller;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Asignar Cooperativa a Terminal");
        
        cargarCombos();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbTerminal = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblPersonaInfo = new javax.swing.JLabel();
        lblPersonaInfo1 = new javax.swing.JLabel();
        cmbCooperativa = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Asignación Coop - Terminal");

        cmbTerminal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTerminalActionPerformed(evt);
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

        lblPersonaInfo.setText("Cooperativa:");

        lblPersonaInfo1.setText("Terminal:");

        cmbCooperativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCooperativaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPersonaInfo)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblPersonaInfo1)
                            .addComponent(btnGuardar))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(btnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(cmbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(70, 70, 70))
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(192, Short.MAX_VALUE)
                    .addComponent(cmbCooperativa, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(73, 73, 73)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(lblPersonaInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTerminal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPersonaInfo1))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addContainerGap(119, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(57, 57, 57)
                    .addComponent(cmbCooperativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(234, Short.MAX_VALUE)))
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
        CopTerm nuevo = leerFormulario();
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

    private void cmbTerminalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTerminalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTerminalActionPerformed

    private void cmbCooperativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCooperativaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCooperativaActionPerformed

    private CopTerm leerFormulario() {
        Cooperativa selCoop = (Cooperativa) cmbCooperativa.getSelectedItem();
        Terminal selTerm = (Terminal) cmbTerminal.getSelectedItem();

        if (selCoop == null || selTerm == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar ambos campos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return new CopTerm(selCoop.getId(), selTerm.getId());
    }

    public void setObjetoEditar(CopTerm v) {
        this.objetoViejo = v;
        setTitle("Editar Asignación");
        jLabel1.setText("Editar Asignación");

        if (listaCoops != null) {
            for (int i = 0; i < listaCoops.size(); i++) {
                if (listaCoops.get(i).getId() == v.getCooperativaId()) {
                    cmbCooperativa.setSelectedIndex(i);
                    break;
                }
            }
        }
        if (listaTerms != null) {
            for (int i = 0; i < listaTerms.size(); i++) {
                if (listaTerms.get(i).getId() == v.getTerminalId()) {
                    cmbTerminal.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void cargarCombos() {
        listaCoops = coopController.listar(); 
        DefaultComboBoxModel modelCoop = new DefaultComboBoxModel();

        if (listaCoops != null) {
            for (Cooperativa c : listaCoops) {
                modelCoop.addElement(c);
            }
        }
        cmbCooperativa.setModel(modelCoop);

        listaTerms = termController.listar(); 
        DefaultComboBoxModel modelTerm = new DefaultComboBoxModel();

        if (listaTerms != null) {
            for (Terminal t : listaTerms) {
                modelTerm.addElement(t);
            }
        }
        cmbTerminal.setModel(modelTerm);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbCooperativa;
    private javax.swing.JComboBox<String> cmbTerminal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblPersonaInfo;
    private javax.swing.JLabel lblPersonaInfo1;
    // End of variables declaration//GEN-END:variables
}
