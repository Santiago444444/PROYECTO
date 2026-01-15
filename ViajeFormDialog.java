package com.transporte.gestion.vista.Operaciones;

import com.transporte.gestion.controlador.Flota.ChoferBusController;
import com.transporte.gestion.controlador.Operaciones.RutaHorarioController;
import com.transporte.gestion.controlador.Operaciones.ViajeController;
import com.transporte.gestion.global.enums.ViajeEstado;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.ValidadorResultado;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.modelo.Flota.ChoferBusView;
import com.transporte.gestion.modelo.Operaciones.RutaHorarioView;
import com.transporte.gestion.modelo.Operaciones.Viaje;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.awt.Window;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import java.util.Date;

public class ViajeFormDialog extends javax.swing.JDialog {

    private final ViajeController viajeController;
    private final RutaHorarioController rutaHorarioController = new RutaHorarioController();
    private final ChoferBusController choferBusController = new ChoferBusController();

    private boolean guardado = false;
    private java.util.List<RutaHorarioView> rutasCache = new java.util.ArrayList<>();
    private java.util.List<ChoferBusView> choferBusCache = new java.util.ArrayList<>();

    public boolean isGuardado() {
        return guardado;
    }

    public ViajeFormDialog(Window parent, ViajeController viajeController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.viajeController = viajeController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Crear Viaje");
        configurarFecha();
        cargarCombos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbRutaHorario = new javax.swing.JComboBox<>();
        cmbChoferBus = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        spFecha = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Crear usuarios");

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

        jLabel5.setText("Ruta - Horario:");

        jLabel6.setText("Chofer - Bus:");

        cmbRutaHorario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbRutaHorario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbRutaHorarioItemStateChanged(evt);
            }
        });
        cmbRutaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRutaHorarioActionPerformed(evt);
            }
        });

        cmbChoferBus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Fecha:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnGuardar)
                        .addGap(27, 27, 27)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(115, 115, 115)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(spFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cmbRutaHorario, 0, 275, Short.MAX_VALUE)
                            .addComponent(cmbChoferBus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbRutaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbChoferBus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
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
        Viaje v = leerFormulario();
        if (v == null) {
            return;
        }
        if (!validarFormulario(v)) {
            return;
        }
        ResultadoOperacion r = viajeController.crear(v);

        if (r != null && r.isOk()) {
            JOptionPane.showMessageDialog(this, r.getMensaje(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            guardado = true;
            dispose();
        } else {
            String msg = (r == null) ? "Error desconocido." : r.getMensaje();
            JOptionPane.showMessageDialog(this, msg, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cmbRutaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRutaHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRutaHorarioActionPerformed

    private void cmbRutaHorarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRutaHorarioItemStateChanged
        onRutaHorarioChanged();
    }//GEN-LAST:event_cmbRutaHorarioItemStateChanged

    private Viaje leerFormulario() {
        Viaje v = new Viaje();

        int idxRuta = cmbRutaHorario.getSelectedIndex();
        if (idxRuta < 0 || idxRuta >= rutasCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Ruta/Horario.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbRutaHorario.requestFocus();
            return null;
        }
        v.setRutaHorarioId(rutasCache.get(idxRuta).getId());

        int idxCB = cmbChoferBus.getSelectedIndex();
        if (idxCB < 0 || idxCB >= choferBusCache.size()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Chofer/Bus.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbChoferBus.requestFocus();
            return null;
        }
        v.setChoferBusId(choferBusCache.get(idxCB).getId());

        Object val = spFecha.getValue();
        if (!(val instanceof java.util.Date)) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la fecha del viaje.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            spFecha.requestFocus();
            return null;
        }

        java.util.Date utilDate = (java.util.Date) val;
        v.setFecha(new java.sql.Date(utilDate.getTime()));
        v.setEstado(ViajeEstado.PROGRAMADO.getCode());

        return v;
    }

    private boolean validarFormulario(Viaje v) {

        if (v.getRutaHorarioId() == null || v.getRutaHorarioId() <= 0) {
            JOptionPane.showMessageDialog(this, "Ruta/Horario inválido.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbRutaHorario.requestFocus();
            return false;
        }

        if (v.getChoferBusId() == null || v.getChoferBusId() <= 0) {
            JOptionPane.showMessageDialog(this, "Chofer/Bus inválido.", "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            cmbChoferBus.requestFocus();
            return false;
        }

        Date f = v.getFecha();
        ValidadorResultado vr = ValidadorDatos.validarNoNulo(f, "Fecha");
        if (!validarCampo(vr, "Fecha", spFecha)) {
            return false;
        }

        java.time.LocalDate fechaViaje = ((java.sql.Date) f).toLocalDate();
        java.time.LocalDate fechaHoy = java.time.LocalDate.now();

        if (fechaViaje.isBefore(fechaHoy)) {
            JOptionPane.showMessageDialog(this, 
                "La fecha del viaje no puede ser anterior a la fecha actual.", 
                "Fecha inválida", JOptionPane.WARNING_MESSAGE);
            spFecha.requestFocus();
            return false;
        }

        if (fechaViaje.equals(fechaHoy)) {
            int idxRuta = cmbRutaHorario.getSelectedIndex();
            
            if (idxRuta >= 0 && idxRuta < rutasCache.size()) {
                RutaHorarioView rh = rutasCache.get(idxRuta);
                
                if (rh.getHoraSalida() != null && !rh.getHoraSalida().isEmpty()) {
                    try {
                        java.time.LocalTime horaSalida = java.time.LocalTime.parse(rh.getHoraSalida());
                        java.time.LocalTime horaActual = java.time.LocalTime.now();

                        if (horaSalida.isBefore(horaActual)) {
                            JOptionPane.showMessageDialog(this, 
                                "El horario seleccionado (" + rh.getHoraSalida() + ") ya pasó por el día de hoy.\n" +
                                "Seleccione un horario futuro o cambie la fecha.", 
                                "Horario inválido", JOptionPane.WARNING_MESSAGE);
                            cmbRutaHorario.requestFocus();
                            return false;
                        }
                    } catch (java.time.format.DateTimeParseException e) {
                        System.err.println("Error al parsear la hora: " + rh.getHoraSalida());
                    }
                }
            }
        }
        
        ViajeEstado ve = ViajeEstado.fromCode(v.getEstado());
        if (ve == null) {
            JOptionPane.showMessageDialog(this,
                    "Estado inválido. Use: PROGRAMADO, COMPLETADO o CANCELADO.",
                    "Datos inválidos",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        v.setEstado(ve.getCode());

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

    private void cargarCombos() {
        rutasCache = rutaHorarioController.listarView();
        DefaultComboBoxModel<String> modelRuta = new DefaultComboBoxModel<>();
        
        for (RutaHorarioView rh : rutasCache) {
            String hora = (rh.getHoraSalida() == null) ? "" : rh.getHoraSalida().toString();
            
            String label = rh.getNombreRuta() + " | " + hora;   
            modelRuta.addElement(label);
        }
        cmbRutaHorario.setModel(modelRuta);

        choferBusCache = choferBusController.listarActivos();
        DefaultComboBoxModel<String> modelCB = new DefaultComboBoxModel<>();
        for (ChoferBusView cb : choferBusCache) {
            String label = cb.getBusId() + " | " + cb.getChoferIdentificacion() + " | " + cb.getChoferNombres();
            modelCB.addElement(label);
        }
        cmbChoferBus.setModel(modelCB);

        if (modelRuta.getSize() > 0) {
            cmbRutaHorario.setSelectedIndex(0);
        }
        if (modelCB.getSize() > 0) {
            cmbChoferBus.setSelectedIndex(0);
        }

        if (rutasCache.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay Rutas/Horarios activos para asignar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        if (choferBusCache.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay Chofer/Bus activos para asignar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        
        if (!rutasCache.isEmpty()) {
        cmbRutaHorario.setSelectedIndex(0);
        onRutaHorarioChanged();
        }   
    }

    private void configurarFecha() {

        SpinnerDateModel model = new SpinnerDateModel(
                new java.util.Date(),
                new java.util.Date(),
                null,
                java.util.Calendar.DAY_OF_MONTH
        );
        spFecha.setModel(model);
        spFecha.setEditor(new javax.swing.JSpinner.DateEditor(spFecha, "yyyy-MM-dd"));
    }
    
    private void onRutaHorarioChanged() {
        int idxRuta = cmbRutaHorario.getSelectedIndex();
        if (idxRuta < 0 || idxRuta >= rutasCache.size()) {
            return;
        }

        RutaHorarioView rh = rutasCache.get(idxRuta);

        Integer cooperativaId = rh.getCooperativa(); 
        if (cooperativaId == null || cooperativaId <= 0) {
            cargarChoferBusFiltrado(null);
            return;
        }

        cargarChoferBusFiltrado(cooperativaId);
    }
    
    private void cargarChoferBusFiltrado(Integer cooperativaId) {
        if (cooperativaId == null) {
            choferBusCache = new java.util.ArrayList<>();
        } else {
            choferBusCache = choferBusController.listarActivosPorCooperativa(cooperativaId);
        }

        DefaultComboBoxModel<String> modelCB = new DefaultComboBoxModel<>();
        for (ChoferBusView cb : choferBusCache) {
            String label = cb.getBusId() + " | " + cb.getChoferIdentificacion() + " | " + cb.getChoferNombres();
            modelCB.addElement(label);
        }
        cmbChoferBus.setModel(modelCB);

        if (modelCB.getSize() > 0) {
            cmbChoferBus.setSelectedIndex(0);
            cmbChoferBus.setEnabled(true);
        } else {
            cmbChoferBus.setEnabled(false);
            JOptionPane.showMessageDialog(this,
                "No hay Chofer/Bus activos para la cooperativa seleccionada.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbChoferBus;
    private javax.swing.JComboBox<String> cmbRutaHorario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSpinner spFecha;
    // End of variables declaration//GEN-END:variables
}
