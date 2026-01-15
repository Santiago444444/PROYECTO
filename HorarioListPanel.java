package com.transporte.gestion.vista.Operaciones;

import com.transporte.gestion.controlador.Operaciones.HorarioController;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Operaciones.Horario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class HorarioListPanel extends javax.swing.JPanel {

    private final HorarioController horarioController = new HorarioController();

    private int paginaActual = 1;
    private final int pageSize = 10;
    private int totalPaginas = 1;

    DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "ID",
                "Hora salida",
                "Observaciones"
            }
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public HorarioListPanel() {
        initComponents();
        tablaHorario.setModel(model);
        ocultarColumnas();
        tablaHorario.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
        tablaHorario = new javax.swing.JTable();
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

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAccionesLayout.createSequentialGroup()
                        .addComponent(btnActualizar)
                        .addContainerGap())))
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

        tablaHorario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        tablaHorario.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaHorarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaHorario);

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
        var dlg = new HorarioFormDialog(
                javax.swing.SwingUtilities.getWindowAncestor(this),
                horarioController
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
            JOptionPane.showMessageDialog(this, "Debe seleccionar un registro.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar este registro?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            var res = horarioController.eliminar(id);

            if (res.isOk()) {
                JOptionPane.showMessageDialog(this, res.getMensaje(), "OK", JOptionPane.INFORMATION_MESSAGE);
                cargarTabla();
            } else {
                JOptionPane.showMessageDialog(this, res.getMensaje(), "No se pudo eliminar", JOptionPane.ERROR_MESSAGE);
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

    private void tablaHorarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaHorarioMouseClicked
        boolean seleccionado = tablaHorario.getSelectedRow() >= 0;
        btnActualizar.setEnabled(seleccionado);
        btnEliminar.setEnabled(seleccionado);
    }//GEN-LAST:event_tablaHorarioMouseClicked

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        Integer id = getIdSeleccionado();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un registro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Horario h = horarioController.buscarPorId(id);
        if (h == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el registro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.awt.Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
        HorarioFormDialog dlg = new HorarioFormDialog(w, horarioController);
        dlg.setHorarioEditar(h);
        dlg.setVisible(true);

        if (dlg.isGuardado()) {
            cargarTabla();
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void cargarTabla() {

        String filtro = txtBuscar.getText().trim();

        int total = horarioController.contar(filtro);
        totalPaginas = (int) Math.ceil(total / (double) pageSize);
        if (totalPaginas <= 0) {
            totalPaginas = 1;
        }

        var lista = horarioController.listar(filtro, pageSize, paginaActual);

        model.setRowCount(0);

        for (var h : lista) {
            
            String horaStr = "";
            if (h.getHoraSalida() != null) {
                horaStr = h.getHoraSalida().toString();
                if (horaStr.length() > 5) horaStr = horaStr.substring(0, 5);
            }

            model.addRow(new Object[]{
                h.getId(),
                horaStr,
                Utils.cargarString(h.getObservaciones())
            });
        }

        lblPag.setText("Página " + paginaActual + " de " + totalPaginas);
        btnPrev.setEnabled(paginaActual > 1);
        btnNext.setEnabled(paginaActual < totalPaginas);

        btnActualizar.setEnabled(false);
        btnEliminar.setEnabled(false);
    }

    private void ocultarColumnas() {

        int colIndex = tablaHorario.getColumnModel().getColumnIndex("ID");
        var column = tablaHorario.getColumnModel().getColumn(colIndex);

        column.setMinWidth(0);
        column.setMaxWidth(0);
        column.setPreferredWidth(0);
        column.setResizable(false);
    }

    private Integer getIdSeleccionado() {

        int row = tablaHorario.getSelectedRow();
        if (row == -1) {
            return null;
        }

        int modelRow = tablaHorario.convertRowIndexToModel(row);
        int columnIndex = tablaHorario.getColumnModel().getColumnIndex("ID");

        Object value = tablaHorario.getModel().getValueAt(modelRow, columnIndex);
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
    private javax.swing.JTable tablaHorario;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
