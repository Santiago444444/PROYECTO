package com.transporte.gestion.vista.Ventas;

import com.transporte.gestion.dao.Ventas.DetalleDAO;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Ventas.DetalleView;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class DetalleDialog extends javax.swing.JDialog {

    private final DetalleDAO detalleDAO = new DetalleDAO();
    private final int facturaId;

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Descripción", "boleto", "P.Unit", "Subtotal"}
    ) {
        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };

    public DetalleDialog(Window parent, int facturaId) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.facturaId = facturaId;

        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Detalle de factura: " + facturaId);

        tabla.setModel(model);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ocultarColumnaId();
        cargarTabla();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

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
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        scroll.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked

    }//GEN-LAST:event_tablaMouseClicked

    private void cargarTabla() {
        List<DetalleView> lista = detalleDAO.listarPorFactura(facturaId);
        model.setRowCount(0);

        for (DetalleView d : lista) {
            model.addRow(new Object[]{
                d.getId(),
                Utils.cargarString(d.getDescripcion()),
                Utils.cargarString(d.getBoletoCodigo()),
                fmtMoney(d.getPrecio()),
                fmtMoney(d.getTotal())
            });
        }
    }

    private String fmtMoney(BigDecimal v) {
        return v == null ? "" : v.toPlainString();
    }

    private void ocultarColumnaId() {
        var col = tabla.getColumnModel().getColumn(0);
        col.setMinWidth(0);
        col.setMaxWidth(0);
        col.setPreferredWidth(0);
        col.setResizable(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
