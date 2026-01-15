package com.transporte.gestion.vista.Operaciones;

import com.transporte.gestion.controlador.Operaciones.TarifaController;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Tarifa;
import com.transporte.gestion.modelo.Operaciones.TarifaView;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TarifaListPanel extends javax.swing.JPanel {

    private final TarifaController controller = new TarifaController();

    private int paginaActual = 1;
    private final int pageSize = 10;
    private int totalPaginas = 1;

    DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "ID",
                "Ruta",
                "Hora",
                "Precio",
                "Estado"
            }
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public TarifaListPanel() {
        initComponents();
        tablaTarifas.setModel(model);
        ocultarColumnas();
        tablaTarifas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        btnActualizar.setEnabled(false);
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
        panelBusqueda = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTarifas = new javax.swing.JTable();
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

        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAccionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar))
                    .addGroup(panelAccionesLayout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

        tablaTarifas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tablaTarifas.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaTarifas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaTarifasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTarifas);

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
        TarifaFormDialog dlg = new TarifaFormDialog(
                javax.swing.SwingUtilities.getWindowAncestor(this),
                controller
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

        Integer id = getIdSeleccionado();
        if (id == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar una tarifa.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int row = tablaTarifas.getSelectedRow();
        int modelRow = tablaTarifas.convertRowIndexToModel(row);

        int colEstado = tablaTarifas.getColumnModel().getColumnIndex("Estado");

        String estadoStr = String.valueOf(
                tablaTarifas.getModel().getValueAt(modelRow, colEstado)
        ).trim().toUpperCase();

        boolean estaActivo = "ACTIVO".equals(estadoStr);

        String accionTexto = estaActivo ? "desactivar" : "reactivar";

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro que desea " + accionTexto + " esta tarifa?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            ResultadoOperacion res;

            if (estaActivo) {
                res = controller.eliminar(id);
            } else {
                res = controller.reactivar(id);
            }

            if (res.isOk()) {
                JOptionPane.showMessageDialog(this, res.getMensaje(), "OK",
                        JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, res.getMensaje(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
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

    private void tablaTarifasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTarifasMouseClicked
        boolean seleccionado = tablaTarifas.getSelectedRow() >= 0;
        btnActualizar.setEnabled(seleccionado);
        btnEliminar.setEnabled(seleccionado);
    }//GEN-LAST:event_tablaTarifasMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Integer id = getIdSeleccionado();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una tarifa.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Tarifa entidad = controller.buscarPorId(id);

        if (entidad == null) {
            JOptionPane.showMessageDialog(this, "No se encontró la tarifa.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TarifaView view = new TarifaView();
        view.setId(entidad.getId());
        view.setRutaHorarioId(entidad.getRutaHorarioId());
        view.setPrecio(entidad.getPrecioBase());
        view.setEstado(entidad.getEstado());

        java.awt.Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
        TarifaFormDialog dlg = new TarifaFormDialog(w, controller);
        dlg.setTarifaEditar(view);
        dlg.setVisible(true);

        if (dlg.isGuardado()) {
            cargarTabla();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void cargarTabla() {
        String filtro = txtBuscar.getText().trim();

        int total = controller.contar(filtro);
        totalPaginas = (int) Math.ceil(total / (double) pageSize);
        if (totalPaginas <= 0) {
            totalPaginas = 1;
        }

        java.util.List<TarifaView> lista = controller.listar(filtro, pageSize, paginaActual);

        model.setRowCount(0);

        for (TarifaView t : lista) {
            model.addRow(new Object[]{
                t.getId(),
                t.getOrigen() + " - " + t.getDestino(),
                t.getHoraSalida(),
                t.getPrecio(),
                Boolean.TRUE.equals(t.getEstado()) ? "ACTIVO" : "INACTIVO"
            });
        }

        lblPag.setText("Página " + paginaActual + " de " + totalPaginas);
        btnPrev.setEnabled(paginaActual > 1);
        btnNext.setEnabled(paginaActual < totalPaginas);

        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void ocultarColumnas() {
        int colIndex = tablaTarifas.getColumnModel().getColumnIndex("ID");

        javax.swing.table.TableColumn column = tablaTarifas.getColumnModel().getColumn(colIndex);

        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
    }

    private Integer getIdSeleccionado() {
        int row = tablaTarifas.getSelectedRow();
        if (row == -1) {
            return null;
        }

        int modelRow = tablaTarifas.convertRowIndexToModel(row);
        int columnIndex = tablaTarifas.getColumnModel().getColumnIndex("ID");

        Object value = tablaTarifas.getModel().getValueAt(modelRow, columnIndex);
        if (value instanceof Integer) {
            return (Integer) value;
        }

        return null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
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
    private javax.swing.JTable tablaTarifas;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
