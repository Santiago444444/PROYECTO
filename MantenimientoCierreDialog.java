package com.transporte.gestion.vista.Mantenimiento;

import com.transporte.gestion.controlador.Mantenimiento.OrdenMantenimientoController;
import static com.transporte.gestion.global.enums.ConstantesNegocio.IVA_ACTUAL;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Mantenimiento.DetalleMantenimiento;
import com.transporte.gestion.modelo.Mantenimiento.OrdenMantenimiento;
import com.transporte.gestion.modelo.Mantenimiento.TipoMantenimiento;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MantenimientoCierreDialog extends javax.swing.JDialog {

    private OrdenMantenimientoController controller;
    private int ordenId;
    private String busPlaca;
    private boolean cerrada = false;
    private DefaultTableModel modelDetalles;
    private List<DetalleMantenimiento> listaDetalles;

    public MantenimientoCierreDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        configurarTabla();
        listaDetalles = new ArrayList<>();
    }

    public void setController(OrdenMantenimientoController controller) {
        this.controller = controller;
        cargarTiposMantenimiento();
    }

    public void setDatosOrden(int ordenId, String busPlaca) {
        this.ordenId = ordenId;
        this.busPlaca = busPlaca;
        lblInfoOrden.setText("Orden #" + ordenId + " - Bus: " + busPlaca);
    }

    public boolean isCerrada() {
        return cerrada;
    }

    private void configurarTabla() {
        String[] cols = {"Tipo", "Descripción", "Costo U.", "Cant.", "Total"};
        modelDetalles = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        tablaDetalles.setModel(modelDetalles);
    }

    private void cargarTiposMantenimiento() {
        cmbTipo.removeAllItems();
        if (controller != null) {
            List<TipoMantenimiento> lista = controller.listarTiposMantenimiento();
            for (TipoMantenimiento t : lista) {
                cmbTipo.addItem(t);
            }
        }
    }

    private void agregarDetalle() {
        TipoMantenimiento tipo = (TipoMantenimiento) cmbTipo.getSelectedItem();
        if (tipo == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un tipo de mantenimiento.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String desc = txtDescripcion.getText().trim();
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese descripción del trabajo.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double costo = 0;
        int cantidad = 0;
        try {
            costo = Double.parseDouble(txtCosto.getText().trim());
            cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (costo < 0 || cantidad <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese costo y cantidad válidos.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double totalLinea = costo * cantidad;

        // Crear objeto Detalle
        DetalleMantenimiento det = new DetalleMantenimiento();
        det.setTipoMantenimientoId(tipo.getId());
        det.setDescripcion(tipo.getNombre() + ": " + desc);
        det.setCostoUnitario(costo);
        det.setCantidad(cantidad);
        det.setTotal(totalLinea);
        listaDetalles.add(det);

        // Agregar a la tabla
        modelDetalles.addRow(new Object[]{
            tipo.getNombre(),
            desc,
            costo,
            cantidad,
            totalLinea
        });

        calcularTotales();
        limpiarCamposDetalle();
    }

    private void calcularTotales() {
        double subtotal = 0;
        for (DetalleMantenimiento d : listaDetalles) {
            subtotal += d.getTotal();
        }
        double iva = subtotal * IVA_ACTUAL;
        double total = subtotal + iva;

        lblSubtotal.setText(String.format("%.2f", subtotal));
        lblIva.setText(String.format("%.2f", iva));
        lblTotal.setText(String.format("%.2f", total));
    }

    private void limpiarCamposDetalle() {
        txtDescripcion.setText("");
        txtCosto.setText("");
        txtCantidad.setText("1");
        cmbTipo.requestFocus();
    }

    private void cerrarOrden() {
        if (listaDetalles.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe agregar al menos un detalle.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro de cerrar esta orden? \nEl bus pasará a estado ACTIVO.",
                "Confirmar Cierre", JOptionPane.YES_NO_OPTION);
        
        if (confirm != JOptionPane.YES_OPTION) return;

        OrdenMantenimiento ordenCabecera = new OrdenMantenimiento();
        ordenCabecera.setId(ordenId);
        ordenCabecera.setBusId(busPlaca); 

        ResultadoOperacion res = controller.cerrarMantenimiento(ordenCabecera, listaDetalles);

        if (res.isOk()) {
            JOptionPane.showMessageDialog(this, res.getMensaje(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cerrada = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, res.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        lblPersonaInfo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCosto = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblIva = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalles = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        lblInfoOrden = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Mantenimiento");

        btnGuardar.setText("FINALIZAR Y CERRAR ORDEN");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblPersonaInfo.setText("Tipo:");

        jLabel3.setText("Detalle:");

        jLabel4.setText("Costo U.");

        txtCosto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCostoActionPerformed(evt);
            }
        });

        jLabel5.setText("Cant");

        txtCantidad.setText("1");
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        lblIva.setText("0.00");

        lblTotal.setText("0.00");

        lblSubtotal.setText("0.00");

        tablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaDetalles);

        jButton1.setText("AGREGAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblInfoOrden.setText("INFOMACION DE ORDEN");

        jLabel1.setText("SUBTOTAL:");

        jLabel2.setText("IVA:");

        jLabel6.setText("TOTAL:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblPersonaInfo)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel3))
                                        .addGap(96, 96, 96))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblInfoOrden)
                                        .addGap(2, 2, 2)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                                        .addComponent(jButton1))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblIva)
                                    .addComponent(lblSubtotal)
                                    .addComponent(lblTotal)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(20, 20, 20)
                .addComponent(lblInfoOrden)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPersonaInfo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnGuardar)
                        .addGap(190, 190, 190))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSubtotal)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIva)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotal)
                            .addComponent(jLabel6))
                        .addGap(143, 143, 143))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        cerrarOrden();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtCostoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCostoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCostoActionPerformed

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregarDetalle();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<TipoMantenimiento> cmbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblInfoOrden;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblPersonaInfo;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tablaDetalles;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCosto;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
