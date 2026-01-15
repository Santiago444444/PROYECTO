package com.transporte.gestion.vista.Personas;

import com.transporte.gestion.controlador.Personas.ClienteController;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Personas.ClienteView;

import java.awt.Window;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ClientePickerDialog extends javax.swing.JDialog {

    private final ClienteController clienteController;
    private ClienteView clienteSeleccionado;

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[][]{}, new String[]{"ID", "Identificación", "Nombres", "Apellidos", "Email", "Teléfono"}
    ) {
        @Override
        public boolean isCellEditable(int r, int c) {
            return false;
        }
    };

    public ClientePickerDialog(Window parent, ClienteController clienteController) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.clienteController = clienteController;
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Seleccionar Cliente");
        tabla.setModel(model);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ocultarColumna("ID");
        cargar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
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
        scroll.setViewportView(tabla);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSeleccionar)
                        .addGap(47, 47, 47)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnCancelar))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        Integer id = getIntSeleccionado("ID");
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ClienteView c = clienteController.buscarPorId(id);
        if (c == null) {
            JOptionPane.showMessageDialog(this, "No se encontró el cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        clienteSeleccionado = c;
        dispose();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        cargar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    public ClienteView getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    private void cargar() {
        String filtro = txtBuscar.getText().trim();
        List<ClienteView> lista = clienteController.listar(filtro, 50, 1);

        model.setRowCount(0);
        for (ClienteView c : lista) {
            model.addRow(new Object[]{
                c.getId(),
                Utils.cargarString(c.getIdentificacion()),
                Utils.cargarString(c.getNombres()),
                Utils.cargarString(c.getApellidos()),
                Utils.cargarString(c.getMail()),
                Utils.cargarString(c.getTelefono())
            });
        }
    }

    private void ocultarColumna(String name) {
        int idx = tabla.getColumnModel().getColumnIndex(name);
        TableColumn col = tabla.getColumnModel().getColumn(idx);
        col.setMinWidth(0);
        col.setMaxWidth(0);
        col.setPreferredWidth(0);
        col.setResizable(false);
    }

    private Integer getIntSeleccionado(String colName) {
        int row = tabla.getSelectedRow();
        if (row == -1) {
            return null;
        }
        int modelRow = tabla.convertRowIndexToModel(row);
        int col = tabla.getColumnModel().getColumnIndex(colName);
        Object v = tabla.getModel().getValueAt(modelRow, col);
        if (v instanceof Integer) {
            return (Integer) v;
        }
        try {
            return Integer.parseInt(String.valueOf(v));
        } catch (Exception e) {
            return null;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
