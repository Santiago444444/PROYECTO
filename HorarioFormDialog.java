package com.transporte.gestion.vista.Operaciones;

import com.transporte.gestion.controlador.Operaciones.HorarioController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Operaciones.Horario;
import java.awt.Window;
import javax.swing.JOptionPane;

public class HorarioFormDialog extends javax.swing.JDialog {

    private final HorarioController horarioController;
    private Horario horarioEditar = null;
    private boolean guardado = false;

    public boolean isGuardado() {
        return guardado;
    }

    public HorarioFormDialog(Window parent, HorarioController horarioController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.horarioController = horarioController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Horario");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        lblTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtHoraSalida = new javax.swing.JTextField();
        txtObservaciones = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Crear Horario");

        jLabel2.setText("Hora salida (HH:mm)");

        jLabel3.setText("Descripcion:");

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

        txtObservaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtObservacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(128, 128, 128)
                        .addComponent(btnGuardar)
                        .addGap(66, 66, 66)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHoraSalida)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(lblTitulo)))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(103, 103, 103))
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
        
        Horario h = leerFormulario();

        if (!validarFormulario(h)) {
            return;
        }

        ResultadoOperacion r;
        if (horarioEditar == null) {
            r = horarioController.crear(h);
        } else {
            h.setId(horarioEditar.getId());
            r = horarioController.actualizar(h);
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

    private void txtObservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtObservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtObservacionesActionPerformed

    private Horario leerFormulario() {

        Horario h = new Horario();

        String horaTxt = txtHoraSalida.getText().trim();
        
        if (!horaTxt.isEmpty()) {
            try {
                java.time.LocalTime lt = java.time.LocalTime.parse(horaTxt);
                 java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("HH:mm");
                 lt = java.time.LocalTime.parse(horaTxt, fmt);
                 h.setHoraSalida(java.sql.Time.valueOf(lt));
            } catch (Exception ex) {
                h.setHoraSalida(null); 
            }
        } else {
            h.setHoraSalida(null);
        }

        h.setObservaciones(txtObservaciones.getText().trim());

        return h;
    }

    private boolean validarFormulario(Horario h) {

        String horaTxt = txtHoraSalida.getText().trim();
        
        if (horaTxt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La hora de salida es obligatoria.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtHoraSalida.requestFocus();
            return false;
        }

        ValidadorResultado rHora = ValidadorDatos.validarHora(horaTxt);
        if (!validarCampo(rHora, "Hora salida", txtHoraSalida)) {
            return false;
        }

        String obs = (h.getObservaciones() == null) ? "" : h.getObservaciones().trim();
        if (!obs.isEmpty()) {
            if (!validarCampo(ValidadorDatos.validarLongitud(obs, 0, 100), "Observaciones", txtObservaciones)) {
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

    public void setHorarioEditar(Horario h) {
        this.horarioEditar = h;

        setTitle("Actualizar Horario");
        if (lblTitulo != null) lblTitulo.setText("Actualizar Horario");

        if (h.getHoraSalida() != null) {
            java.time.LocalTime lt = h.getHoraSalida().toLocalTime();
            java.time.format.DateTimeFormatter fmt = java.time.format.DateTimeFormatter.ofPattern("HH:mm");
            txtHoraSalida.setText(lt.format(fmt));
        } else {
            txtHoraSalida.setText("");
        }

        txtObservaciones.setText(h.getObservaciones() == null ? "" : h.getObservaciones());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtHoraSalida;
    private javax.swing.JTextField txtObservaciones;
    // End of variables declaration//GEN-END:variables
}
