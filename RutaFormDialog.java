package com.transporte.gestion.vista.Operaciones;

import com.transporte.gestion.controlador.Infraestructura.CooperativaController;
import com.transporte.gestion.controlador.Operaciones.RutaController;
import com.transporte.gestion.controlador.Infraestructura.TerminalController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Infraestructura.Cooperativa;
import com.transporte.gestion.modelo.Operaciones.Ruta;
import com.transporte.gestion.modelo.Operaciones.RutaView;
import com.transporte.gestion.modelo.Infraestructura.Terminal;
import java.awt.Window;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class RutaFormDialog extends javax.swing.JDialog {

    private final RutaController rutaController;
    private final CooperativaController coopController = new CooperativaController();
    private final TerminalController termController = new TerminalController();
    
    private RutaView rutaEditarView = null;
    private boolean guardado = false;

    public boolean isGuardado() {
        return guardado;
    }

    public RutaFormDialog(Window parent, RutaController rutaController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.rutaController = rutaController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Ruta");
        
        cargarCooperativas();
        
        cmbOrigen.setEnabled(false);
        cmbDestino.setEnabled(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbOrigen = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cmbDestino = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbCooperativa = new javax.swing.JComboBox<>();
        txtDistancia = new javax.swing.JTextField();
        txtDuracion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear Ruta");

        jLabel2.setText("Distacia:");

        cmbOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbOrigenActionPerformed(evt);
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

        cmbDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDestinoActionPerformed(evt);
            }
        });

        jLabel3.setText("Origen: ");

        jLabel4.setText("Cooperativa: ");

        jLabel5.setText("Destino:");

        jLabel6.setText("Duracion:");

        cmbCooperativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCooperativaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(28, 28, 28)))
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCooperativa, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtDistancia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(btnGuardar)
                .addGap(78, 78, 78)
                .addComponent(btnCancelar))
            .addGroup(layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbCooperativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDuracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
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
        Ruta r = leerFormulario();
        if (r == null) {
            return;
        }
        if (!validarFormulario(r)) {
            return;
        }

        ResultadoOperacion res;
        if (rutaEditarView == null) {
            res = rutaController.crear(r);
        } else {
            r.setId(rutaEditarView.getId());
            res = rutaController.actualizar(r);
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

    private void cmbOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbOrigenActionPerformed

    private void cmbDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDestinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbDestinoActionPerformed

    private void cmbCooperativaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCooperativaActionPerformed
        Cooperativa seleccion = (Cooperativa) cmbCooperativa.getSelectedItem();
        
        cmbOrigen.removeAllItems();
        cmbDestino.removeAllItems();
        
        if (seleccion != null) {
            List<Terminal> terminales = termController.listarPorCooperativa(seleccion.getId());
            for (Terminal t : terminales) {
                cmbOrigen.addItem(t);
                cmbDestino.addItem(t);
            }
            cmbOrigen.setEnabled(true);
            cmbDestino.setEnabled(true);
        } else {
            cmbOrigen.setEnabled(false);
            cmbDestino.setEnabled(false);
        }
    }//GEN-LAST:event_cmbCooperativaActionPerformed

    private void cargarCooperativas() {
        java.awt.event.ActionListener[] listeners = cmbCooperativa.getActionListeners();
        for (java.awt.event.ActionListener al : listeners) cmbCooperativa.removeActionListener(al);

        List<Cooperativa> lista = coopController.listar();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (Cooperativa c : lista) {
            model.addElement(c);
        }
        cmbCooperativa.setModel(model);
        cmbCooperativa.setSelectedIndex(-1);

        for (java.awt.event.ActionListener al : listeners) cmbCooperativa.addActionListener(al);
    }

    private Ruta leerFormulario() {
        Ruta r = new Ruta();

        Cooperativa coop = (Cooperativa) cmbCooperativa.getSelectedItem();
        if (coop == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una cooperativa.", "Datos", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        r.setCooperativaId(coop.getId());

        Terminal termOrigen = (Terminal) cmbOrigen.getSelectedItem();
        Terminal termDestino = (Terminal) cmbDestino.getSelectedItem();
        
        if (termOrigen == null || termDestino == null) {
             JOptionPane.showMessageDialog(this, "Debe seleccionar origen y destino.", "Datos", JOptionPane.WARNING_MESSAGE);
             return null;
        }
        r.setOrigenTerminalId(termOrigen.getId());
        r.setDestinoTerminalId(termDestino.getId());

        String distStr = txtDistancia.getText().trim();
        String durStr = txtDuracion.getText().trim();
        
        if (distStr.isEmpty() || durStr.isEmpty()) {
             JOptionPane.showMessageDialog(this, "Distancia y duración son obligatorias.", "Datos", JOptionPane.WARNING_MESSAGE);
             return null;
        }

        try {
            r.setDistancia(Integer.parseInt(distStr));
            r.setDuracion(Integer.parseInt(durStr));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Distancia y duración deben ser numéricos.", "Datos", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        
        r.setEstado(true);

        return r;
    }

    private boolean validarFormulario(Ruta r) {
        if (r.getCooperativaId() <= 0) return false;

        if (r.getDistancia() <= 0) {
            JOptionPane.showMessageDialog(this, "La distancia debe ser mayor a 0.", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtDistancia.requestFocus();
            return false;
        }
        
        if (r.getDuracion() <= 0) {
            JOptionPane.showMessageDialog(this, "La duración debe ser mayor a 0.", "Aviso", JOptionPane.WARNING_MESSAGE);
            txtDuracion.requestFocus();
            return false;
        }
        
        if (r.getOrigenTerminalId().equals(r.getDestinoTerminalId())) {
            JOptionPane.showMessageDialog(this, "El origen y el destino no pueden ser el mismo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }

    public void setRutaEditar(RutaView r) {
        this.rutaEditarView = r;
        setTitle("Actualizar Ruta");
        jLabel1.setText("Actualizar Ruta");

        txtDistancia.setText(String.valueOf(r.getDistancia()));
        txtDuracion.setText(String.valueOf(r.getDuracion()));

        DefaultComboBoxModel modelCoop = (DefaultComboBoxModel) cmbCooperativa.getModel();
        for (int i = 0; i < modelCoop.getSize(); i++) {
            Cooperativa c = (Cooperativa) modelCoop.getElementAt(i);
            if (c.getId() == r.getCooperativaId()) { 
                cmbCooperativa.setSelectedIndex(i);
                break;
            }
        }
        
        seleccionarTerminalPorId(cmbOrigen, r.getOrigenTerminalId());
        seleccionarTerminalPorId(cmbDestino, r.getDestinoTerminalId());
    }
    
    private void seleccionarTerminalPorId(javax.swing.JComboBox combo, Integer id) {
    DefaultComboBoxModel model = (DefaultComboBoxModel) combo.getModel();
    for (int i = 0; i < model.getSize(); i++) {
        Terminal t = (Terminal) model.getElementAt(i);
        
        if (t.getId() == id) { 
            combo.setSelectedIndex(i);
            break;
        }
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<Object> cmbCooperativa;
    private javax.swing.JComboBox<Object> cmbDestino;
    private javax.swing.JComboBox<Object> cmbOrigen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtDistancia;
    private javax.swing.JTextField txtDuracion;
    // End of variables declaration//GEN-END:variables
}
