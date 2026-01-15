package com.transporte.gestion.vista.Flota;

import com.transporte.gestion.controlador.Flota.BusController;
import com.transporte.gestion.controlador.Infraestructura.CooperativaController;
import com.transporte.gestion.controlador.Flota.MarcaController;
import com.transporte.gestion.controlador.Flota.ModeloController;
import com.transporte.gestion.global.enums.EstadoBusEnum;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Flota.Bus;
import com.transporte.gestion.modelo.Flota.BusView;
import com.transporte.gestion.modelo.Infraestructura.Cooperativa;
import com.transporte.gestion.modelo.Flota.Marca;
import com.transporte.gestion.modelo.Flota.Modelo;
import java.awt.Window;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class BusFormDialog extends javax.swing.JDialog {

    private final BusController busController;

    private final MarcaController marcaController = new MarcaController();
    private final ModeloController modeloController = new ModeloController();
    private final CooperativaController cooperativaController = new CooperativaController();

    private BusView busEditarView = null;
    private boolean guardado = false;

    private java.util.List<Marca> marcasCache = new java.util.ArrayList<>();
    private java.util.List<Modelo> modelosMarcaCache = new java.util.ArrayList<>();
    private java.util.List<Cooperativa> cooperativasCache = new java.util.ArrayList<>();

    public boolean isGuardado() {
        return guardado;
    }

    public BusFormDialog(Window parent, BusController busController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.busController = busController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Bus");
        cargarMarcasEnCombo();
        cargarModelosPorMarca();
        cargarCooperativasEnCombo();
        cmbMarca.addActionListener(e -> onMarcaChanged());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbModelo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        cmbCooperativa = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        txtCapacidad = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbMarca = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear buses");

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

        jLabel5.setText("Placa:");

        jLabel6.setText("Modelo:");

        cmbModelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Cooperativa:");

        cmbCooperativa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Capacidad:");

        jLabel9.setText("Año:");

        jLabel2.setText("Marca:");

        cmbMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(cmbCooperativa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cmbModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(cmbCooperativa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(19, 19, 19))
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
        Bus b = leerFormulario();
        if (b == null) {
            return;
        }
        if (!validarFormulario(b)) {
            return;
        }

        ResultadoOperacion r;

        if (busEditarView == null) {
            r = busController.crear(b);
        } else {
            r = busController.actualizar(b);
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

    private Bus leerFormulario() {
        Bus b = new Bus();

        String placa = (txtPlaca.getText() == null) ? "" : txtPlaca.getText().trim().toUpperCase();
        b.setPlaca(placa);

        int idxModelo = cmbModelo.getSelectedIndex();
        if (idxModelo >= 0 && idxModelo < modelosMarcaCache.size()) {
            b.setModeloId(modelosMarcaCache.get(idxModelo).getId());
        }
        int idxCoop = cmbCooperativa.getSelectedIndex();
        if (idxCoop >= 0 && idxCoop < cooperativasCache.size()) {
            b.setCooperativaId(cooperativasCache.get(idxCoop).getId());
        }

        String capTxt = (txtCapacidad.getText() == null) ? "" : txtCapacidad.getText().trim();
        if (!capTxt.isEmpty()) {
            try {
                b.setCapacidad(Integer.parseInt(capTxt));
            } catch (NumberFormatException e) {

                b.setCapacidad(null);
            }
        } else {
            b.setCapacidad(null);
        }

        b.setEstado(EstadoBusEnum.ACTIVO.name());

        String anioTxt = (txtAnio.getText() == null) ? "" : txtAnio.getText().trim();
        if (!anioTxt.isEmpty()) {
            try {
                b.setAnioFabricacion(Integer.parseInt(anioTxt));
            } catch (NumberFormatException e) {
                b.setAnioFabricacion(null);
            }
        } else {
            b.setAnioFabricacion(null);
        }

        return b;
    }

    private boolean validarFormulario(Bus b) {

        String placa = (b.getPlaca() == null) ? "" : b.getPlaca().trim().toUpperCase();

        if (!validarCampo(ValidadorDatos.validarNoVacio(placa), "Placa", txtPlaca)) {
            return false;
        }

        if (!placa.matches("^[A-Z]{3}-[0-9]{3,4}$")) {
            JOptionPane.showMessageDialog(this,
                    "Placa inválida. Debe tener 3 letras, un guion y 3 o 4 números.\nEjemplo: ABC-1234 o GBA-123",
                    "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            txtPlaca.requestFocus();
            return false;
        }

        int idxMarca = cmbMarca.getSelectedIndex();
        if (idxMarca < 0 || idxMarca >= marcasCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una marca.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbMarca.requestFocus();
            return false;
        }

        int idxModelo = cmbModelo.getSelectedIndex();
        if (idxModelo < 0 || idxModelo >= modelosMarcaCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un modelo.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbModelo.requestFocus();
            return false;
        }

        int idxCoop = cmbCooperativa.getSelectedIndex();
        if (idxCoop < 0 || idxCoop >= cooperativasCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una cooperativa.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbCooperativa.requestFocus();
            return false;
        }

        if (txtCapacidad.getText() != null && !txtCapacidad.getText().trim().isEmpty()) {
            try {
                int cap = Integer.parseInt(txtCapacidad.getText().trim());
                if (cap <= 0 || cap > 100) {
                    JOptionPane.showMessageDialog(this,
                            "Capacidad inválida. Debe ser un número entre 1 y 100.",
                            "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                    txtCapacidad.requestFocus();
                    return false;
                }
                b.setCapacidad(cap);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Capacidad inválida. Debe ser numérica.",
                        "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                txtCapacidad.requestFocus();
                return false;
            }
        }

        if (txtAnio.getText() != null && !txtAnio.getText().trim().isEmpty()) {
            try {
                int anio = Integer.parseInt(txtAnio.getText().trim());
                int anioMin = 1970;
                int anioMax = java.time.Year.now().getValue() + 1;
                if (anio < anioMin || anio > anioMax) {
                    JOptionPane.showMessageDialog(this,
                            "Año inválido. Rango permitido: " + anioMin + " a " + anioMax,
                            "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                    txtAnio.requestFocus();
                    return false;
                }
                b.setAnioFabricacion(anio);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Año inválido. Debe ser numérico.",
                        "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                txtAnio.requestFocus();
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

    public void setBusEditar(BusView b) {
        this.busEditarView = b;

        setTitle("Actualizar Bus");
        jLabel1.setText("Actualizar Bus");

        txtPlaca.setText(Utils.cargarString(b.getPlaca()));
        txtPlaca.setEnabled(false);

        Modelo modelo = modeloController.buscarPorId(b.getModeloId()); // implementa este método
        int marcaId = (modelo != null) ? modelo.getMarcaId() : -1;

        int idxMarca = -1;
        for (int i = 0; i < marcasCache.size(); i++) {
            if (marcasCache.get(i).getId() == marcaId) {
                idxMarca = i;
                break;
            }
        }
        if (idxMarca >= 0) {
            cmbMarca.setSelectedIndex(idxMarca);
        }

        cargarModelosPorMarca();

        int idxModelo = -1;
        for (int i = 0; i < modelosMarcaCache.size(); i++) {
            if (modelosMarcaCache.get(i).getId() == b.getModeloId()) {
                idxModelo = i;
                break;
            }
        }
        if (idxModelo >= 0) {
            cmbModelo.setSelectedIndex(idxModelo);
        }

        // seleccionar cooperativa por id
        int idxC = -1;
        for (int i = 0; i < cooperativasCache.size(); i++) {
            if (cooperativasCache.get(i).getId() == b.getCooperativaId()) {
                idxC = i;
                break;
            }
        }
        if (idxC >= 0) {
            cmbCooperativa.setSelectedIndex(idxC);
        }

        txtCapacidad.setText(b.getCapacidad() != null ? b.getCapacidad().toString() : "");

        txtAnio.setText(b.getAnioFabricacion() != null ? b.getAnioFabricacion().toString() : "");
    }

    private void cargarMarcasEnCombo() {
        marcasCache = marcaController.listar();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Marca m : marcasCache) {
            model.addElement(m.getNombre());
        }
        cmbMarca.setModel(model);

        if (model.getSize() > 0) {
            cmbMarca.setSelectedIndex(0);
        }
    }

    private void cargarModelosPorMarca() {
        int idxMarca = cmbMarca.getSelectedIndex();
        if (idxMarca < 0 || idxMarca >= marcasCache.size()) {
            modelosMarcaCache = new java.util.ArrayList<>();
            cmbModelo.setModel(new DefaultComboBoxModel<>());
            cmbModelo.setEnabled(false);
            return;
        }

        int marcaId = marcasCache.get(idxMarca).getId();
        modelosMarcaCache = modeloController.listarPorMarca(marcaId);

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Modelo mo : modelosMarcaCache) {
            model.addElement(mo.getNombre());
        }
        cmbModelo.setModel(model);
        cmbModelo.setEnabled(model.getSize() > 0);

        if (model.getSize() > 0) {
            cmbModelo.setSelectedIndex(0);
        }
    }

    private void onMarcaChanged() {
        cargarModelosPorMarca();
    }

    private void cargarCooperativasEnCombo() {
        cooperativasCache = cooperativaController.listar();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Cooperativa c : cooperativasCache) {
            model.addElement(c.getNombre());
        }
        cmbCooperativa.setModel(model);
        if (model.getSize() > 0) {
            cmbCooperativa.setSelectedIndex(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbCooperativa;
    private javax.swing.JComboBox<String> cmbMarca;
    private javax.swing.JComboBox<String> cmbModelo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JFormattedTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
