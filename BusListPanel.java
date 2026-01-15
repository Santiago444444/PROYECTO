package com.transporte.gestion.vista.Flota;

import com.transporte.gestion.controlador.Flota.BusController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Flota.BusView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class BusListPanel extends javax.swing.JPanel {

    private final BusController busController = new BusController();

    private int paginaActual = 1;
    private final int pageSize = 10;
    private int totalPaginas = 1;

    DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "PLACA",
                "Modelo",
                "Cooperativa",
                "Capacidad",
                "Estado",
                "Año"
            }
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public BusListPanel() {
        initComponents();
        tablaBus.setModel(model);
        tablaBus.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        btnActualizar.setEnabled(false);
        btnAsientos.setEnabled(false);
        btnEliminar.setEnabled(false);
        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelAcciones = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnAsientos = new javax.swing.JButton();
        panelBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBus = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnPrev = new javax.swing.JButton();
        lblPag = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(new java.awt.Color(0, 255, 204));
        jPanel2.setLayout(new java.awt.BorderLayout());

        panelAcciones.setBackground(new java.awt.Color(255, 255, 255));
        panelAcciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 12, 10, 12));
        panelAcciones.setPreferredSize(new java.awt.Dimension(250, 100));

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Activar/Desactivar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAsientos.setText("Editar asientos");
        btnAsientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsientosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccionesLayout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccionesLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAsientos)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnAsientos))
                .addGap(18, 18, 18)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar))
                .addContainerGap())
        );

        jPanel2.add(panelAcciones, java.awt.BorderLayout.EAST);

        panelBusqueda.setBackground(new java.awt.Color(255, 255, 255));
        panelBusqueda.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 12, 10, 12));

        jLabel1.setText("Buscar:");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/lupa1_1.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBusquedaLayout = new javax.swing.GroupLayout(panelBusqueda);
        panelBusqueda.setLayout(panelBusquedaLayout);
        panelBusquedaLayout.setHorizontalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelBusquedaLayout.setVerticalGroup(
            panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscar)
                    .addGroup(panelBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2.add(panelBusqueda, java.awt.BorderLayout.CENTER);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 14, 5, 14));

        tablaBus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tablaBus.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaBus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaBusMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaBus);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14));

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        lblPag.setText("pag..");

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(428, Short.MAX_VALUE)
                .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(lblPag)
                .addGap(38, 38, 38)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext)
                    .addComponent(lblPag)
                    .addComponent(btnPrev)))
        );

        add(jPanel1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        var dlg = new BusFormDialog(
                javax.swing.SwingUtilities.getWindowAncestor(this),
                busController
        );
        dlg.setVisible(true);

        if (dlg.isGuardado()) {
            paginaActual = 1;
            cargarTabla();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

        String placa = getPlacaSeleccionada();
        if (placa == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar un registro.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String estado = getValorSeleccionado("Estado");
        boolean estaInactivo = "INACTIVO".equalsIgnoreCase(estado);

        String accionTexto = estaInactivo ? "activar" : "desactivar";

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea " + accionTexto + " este bus?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        ResultadoOperacion res = estaInactivo
                ? busController.activar(placa)
                : busController.desactivar(placa);

        if (res != null && res.isOk()) {
            JOptionPane.showMessageDialog(this, res.getMensaje(), "OK", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } else {
            String msg = (res == null) ? "Error desconocido." : res.getMensaje();
            JOptionPane.showMessageDialog(this, msg, "No se pudo cambiar estado", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        if (paginaActual > 1) {
            paginaActual--;
            cargarTabla();
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        if (paginaActual < totalPaginas) {
            paginaActual++;
            cargarTabla();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        paginaActual = 1;
        cargarTabla();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tablaBusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaBusMouseClicked
        int fila = tablaBus.getSelectedRow();
        
        if (fila >= 0) {
            Object valor = tablaBus.getValueAt(fila, 4); 
            String estado = (valor != null) ? valor.toString().trim().toUpperCase() : "";

            System.out.println("Admin seleccionó fila " + fila + ". Estado: " + estado);

            if ("MANTENIMIENTO".equals(estado)) {
                btnAsientos.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnActualizar.setEnabled(false);
            } else {
                btnAsientos.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnEliminar.setToolTipText(null);
                btnActualizar.setEnabled(true);
                btnActualizar.setToolTipText(null);
                
            }
        } else {
            btnAsientos.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnActualizar.setEnabled(false);
        }
    }//GEN-LAST:event_tablaBusMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        String placa = getPlacaSeleccionada();
        if (placa == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un bus.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        BusView b = busController.buscarPorPlaca(placa);
        if (b == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el bus.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.awt.Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
        BusFormDialog dlg = new BusFormDialog(w, busController);
        dlg.setBusEditar(b);
        dlg.setVisible(true);

        if (dlg.isGuardado()) {
            cargarTabla();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnAsientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsientosActionPerformed
        String placa = getPlacaSeleccionada();
        if (placa == null || placa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un bus.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.awt.Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
        AsientosDialog dlg = new AsientosDialog(w, placa);
        dlg.setVisible(true);
    }//GEN-LAST:event_btnAsientosActionPerformed

    private void cargarTabla() {
        String filtro = txtBuscar.getText().trim();

        int total = busController.contar(filtro);
        totalPaginas = (int) Math.ceil(total / (double) pageSize);
        if (totalPaginas <= 0) {
            totalPaginas = 1;
        }

        var lista = busController.listar(filtro, pageSize, paginaActual);

        model.setRowCount(0);

        for (var b : lista) {
            model.addRow(new Object[]{
                Utils.cargarString(b.getPlaca()),
                Utils.cargarString(b.getModeloNombre()),
                Utils.cargarString(b.getCooperativaNombre()),
                (b.getCapacidad() != null ? b.getCapacidad() : ""),
                Utils.cargarString(b.getEstado()),
                (b.getAnioFabricacion() != null ? b.getAnioFabricacion() : "")
            });
        }

        lblPag.setText("Página " + paginaActual + " de " + totalPaginas);
        btnPrev.setEnabled(paginaActual > 1);
        btnNext.setEnabled(paginaActual < totalPaginas);

        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private String getPlacaSeleccionada() {
        return getValorSeleccionado("PLACA");
    }

    private String getValorSeleccionado(String colName) {
        int row = tablaBus.getSelectedRow();
        if (row == -1) {
            return null;
        }

        int modelRow = tablaBus.convertRowIndexToModel(row);
        int col = tablaBus.getColumnModel().getColumnIndex(colName);

        Object val = tablaBus.getModel().getValueAt(modelRow, col);
        return (val == null) ? null : String.valueOf(val).trim();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAsientos;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPag;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JTable tablaBus;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
