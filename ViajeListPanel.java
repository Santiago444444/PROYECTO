package com.transporte.gestion.vista.Operaciones;

import com.transporte.gestion.controlador.Operaciones.ViajeController;
import com.transporte.gestion.global.enums.ViajeEstado;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.SesionGlobal;
import com.transporte.gestion.modelo.Operaciones.ViajeView;
import com.transporte.gestion.vista.Ventas.BoletosDialog;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ViajeListPanel extends javax.swing.JPanel {

    private final ViajeController viajeController = new ViajeController();

    private int paginaActual = 1;
    private final int pageSize = 10;
    private int totalPaginas = 1;

    private Integer choferIdFiltro = null;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "ID",
                "Fecha",
                "Hora",
                "Ruta",
                "Placa",
                "Chofer",
                "Estado"
            }
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ViajeListPanel() {
        initComponents();
        tablaViajes.setModel(model);
        tablaViajes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        btnVerBoletos.setEnabled(false);
        btnEliminar.setEnabled(false);

        ocultarColumnaId();
        cargarTabla();
        ajustarPermisosUI();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        panelAcciones = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVerBoletos = new javax.swing.JButton();
        btnCompletar = new javax.swing.JButton();
        panelBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaViajes = new javax.swing.JTable();
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

        btnEliminar.setText("Anular");
        btnEliminar.setActionCommand("Cancelar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnVerBoletos.setText("Ver boletos");
        btnVerBoletos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerBoletosActionPerformed(evt);
            }
        });

        btnCompletar.setText("Completar");
        btnCompletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccionesLayout.createSequentialGroup()
                        .addComponent(btnCompletar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccionesLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerBoletos)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerBoletos)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCompletar))
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

        tablaViajes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tablaViajes.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaViajes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaViajesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaViajes);

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
        var dlg = new ViajeFormDialog(
                javax.swing.SwingUtilities.getWindowAncestor(this),
                viajeController
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

        Integer viajeId = getIdSeleccionado();
        if (viajeId == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un viaje.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String estado = getValorSeleccionado("Estado");
        ViajeEstado est = ViajeEstado.fromCode(estado);

        if (est == ViajeEstado.CANCELADO) {
            JOptionPane.showMessageDialog(this, "Este viaje ya está CANCELADO y no se puede reactivar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (est == ViajeEstado.COMPLETADO) {
            JOptionPane.showMessageDialog(this, "Un viaje COMPLETADO no se puede anular.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea ANULAR este viaje?\nEsto anulará todos los boletos relacionados.",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        ResultadoOperacion res = viajeController.anular(viajeId);

        if (res != null && res.isOk()) {
            JOptionPane.showMessageDialog(this, res.getMensaje(), "OK", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } else {
            String msg = (res == null) ? "Error desconocido." : res.getMensaje();
            JOptionPane.showMessageDialog(this, msg, "No se pudo anular", JOptionPane.ERROR_MESSAGE);
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

    private void tablaViajesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaViajesMouseClicked
        boolean seleccionado = tablaViajes.getSelectedRow() >= 0;

        boolean esVendedor = SesionGlobal.tienePermiso("VENDEDOR");

        btnVerBoletos.setEnabled(seleccionado);

        if (esVendedor) {
            btnEliminar.setEnabled(false);
            btnCompletar.setEnabled(false);
            return;
        }

        if (seleccionado) {
            ViajeEstado est = ViajeEstado.fromCode(getValorSeleccionado("Estado"));
            btnEliminar.setEnabled(est == ViajeEstado.PROGRAMADO);
            btnCompletar.setEnabled(est == ViajeEstado.PROGRAMADO);
        } else {
            btnEliminar.setEnabled(false);
            btnCompletar.setEnabled(false);
        }
    }//GEN-LAST:event_tablaViajesMouseClicked

    private void btnVerBoletosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerBoletosActionPerformed
        Integer viajeId = getIdSeleccionado();
        if (viajeId == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un viaje.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        ViajeEstado estadoViaje = ViajeEstado.fromCode(getValorSeleccionado("Estado"));
        java.awt.Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
        BoletosDialog dlg = new BoletosDialog(w, viajeId, estadoViaje);
        dlg.setVisible(true);
    }//GEN-LAST:event_btnVerBoletosActionPerformed

    private void btnCompletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompletarActionPerformed
        Integer viajeId = getIdSeleccionado();
        if (viajeId == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un viaje.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String estadoTxt = getValorSeleccionado("Estado");
        ViajeEstado est = ViajeEstado.fromCode(estadoTxt);

        if (est != ViajeEstado.PROGRAMADO) {
            JOptionPane.showMessageDialog(
                    this,
                    "Solo se pueden COMPLETAR viajes en estado PROGRAMADO.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int c = JOptionPane.showConfirmDialog(
                this,
                "¿Marcar este viaje como COMPLETADO?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (c != JOptionPane.YES_OPTION) {
            return;
        }

        ResultadoOperacion r = viajeController.completar(viajeId);

        if (r != null && r.isOk()) {
            JOptionPane.showMessageDialog(this, r.getMensaje(), "OK", JOptionPane.INFORMATION_MESSAGE);
            cargarTabla();
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    (r == null) ? "Error desconocido." : r.getMensaje(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }//GEN-LAST:event_btnCompletarActionPerformed

    private void cargarTabla() {
        String filtro = txtBuscar.getText().trim();
        boolean esVendedor = SesionGlobal.tienePermiso("VENDEDOR");

        if (esVendedor) {
            cargarTablaVendedor(filtro);
        } else {
            cargarTablaNormal(filtro);
        }

        actualizarPaginacionUI();
        resetBotonesSeleccion();
        ajustarPermisosUI();
    }

    private void cargarTablaVendedor(String filtro) {
        var hoy = java.time.LocalDate.now();
        var hasta = hoy.plusDays(7);
        java.sql.Date d1 = java.sql.Date.valueOf(hoy);
        java.sql.Date d2 = java.sql.Date.valueOf(hasta);

        int total = viajeController.contarVendedor(filtro, d1, d2);
        ajustarPaginas(total);

        List<ViajeView> lista = viajeController.listarVendedor(filtro, pageSize, paginaActual, d1, d2);
        pintarTabla(lista);
    }

    private void cargarTablaNormal(String filtro) {
        Integer personaIdFiltro = resolverPersonaIdFiltro();

        int total = viajeController.contar(filtro, personaIdFiltro);
        ajustarPaginas(total);

        List<ViajeView> lista = viajeController.listar(filtro, pageSize, paginaActual, personaIdFiltro);
        pintarTabla(lista);
    }

    private Integer resolverPersonaIdFiltro() {
        boolean puedeVerTodos = SesionGlobal.tienePermiso("VIAJESALL");
        boolean esVendedor = SesionGlobal.tienePermiso("VENDEDOR");
        boolean haySesion = SesionGlobal.haySesionActiva();

        if (haySesion && !puedeVerTodos && !esVendedor) {
            choferIdFiltro = 1;
            return SesionGlobal.getPersonaId();
        }
        choferIdFiltro = null;
        return null;
    }

    private void ajustarPaginas(int total) {
        totalPaginas = (int) Math.ceil(total / (double) pageSize);
        if (totalPaginas <= 0) {
            totalPaginas = 1;
        }
        if (paginaActual > totalPaginas) {
            paginaActual = totalPaginas;
        }
        if (paginaActual < 1) {
            paginaActual = 1;
        }
    }

    private void pintarTabla(List<ViajeView> lista) {
        model.setRowCount(0);
        for (ViajeView v : lista) {
            String fechaTxt = (v.getFecha() != null) ? sdf.format(v.getFecha()) : "";
            model.addRow(new Object[]{
                v.getId(), fechaTxt, v.getHoraSalida(),
                v.getRutaNombre(), v.getPlacaBus(), v.getChoferNombre(), v.getEstado()
            });
        }
    }

    private void actualizarPaginacionUI() {
        lblPag.setText("Página " + paginaActual + " de " + totalPaginas);
        btnPrev.setEnabled(paginaActual > 1);
        btnNext.setEnabled(paginaActual < totalPaginas);
    }

    private void resetBotonesSeleccion() {
        btnVerBoletos.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnCompletar.setEnabled(false);
    }

    private Integer getIdSeleccionado() {
        String val = getValorSeleccionado("ID");
        if (val == null || val.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(val.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String getValorSeleccionado(String colName) {
        int row = tablaViajes.getSelectedRow();
        if (row == -1) {
            return null;
        }

        int modelRow = tablaViajes.convertRowIndexToModel(row);
        int col = tablaViajes.getColumnModel().getColumnIndex(colName);

        Object val = tablaViajes.getModel().getValueAt(modelRow, col);
        return (val == null) ? null : String.valueOf(val).trim();
    }

    private void ocultarColumnaId() {
        int colIndex = tablaViajes.getColumnModel().getColumnIndex("ID");
        TableColumn col = tablaViajes.getColumnModel().getColumn(colIndex);
        col.setMinWidth(0);
        col.setMaxWidth(0);
        col.setPreferredWidth(0);
    }

    private void ajustarPermisosUI() {
        boolean puedeVerTodos = SesionGlobal.tienePermiso("VIAJESALL");
        boolean esVendedor = SesionGlobal.tienePermiso("VENDEDOR"); // <-- ajusta al permiso real
        boolean modoChofer = (choferIdFiltro != null) && !puedeVerTodos && !esVendedor;

        if (esVendedor) {
            jButton1.setVisible(false);
            btnEliminar.setVisible(false);
            btnCompletar.setVisible(false);
            btnVerBoletos.setVisible(true);
            return;
        }

        if (modoChofer) {
            jButton1.setVisible(false);
            btnEliminar.setVisible(false);
            btnCompletar.setVisible(true);
            btnVerBoletos.setVisible(true);
            return;
        }

        jButton1.setVisible(true);
        btnEliminar.setVisible(true);
        btnCompletar.setVisible(true);
        btnVerBoletos.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCompletar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnVerBoletos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblPag;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelBusqueda;
    private javax.swing.JTable tablaViajes;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
