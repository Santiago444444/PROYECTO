package com.transporte.gestion.vista.Reportes;

import com.transporte.gestion.controlador.Reportes.ReportesController;
import com.transporte.gestion.global.utils.GraficoBarrasPanel;
import static com.transporte.gestion.global.enums.ConstantesNegocio.RUTA_PDF;
import com.transporte.gestion.global.enums.Periodicidad;
import com.transporte.gestion.global.enums.TipoReporte;
import com.transporte.gestion.global.utils.PdfPentahoService;
import com.transporte.gestion.global.utils.Utils;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

public class ReportesPanel extends javax.swing.JPanel {

    private final ReportesController service = new ReportesController();
    private java.util.List<TipoReporte> tiposCache = new java.util.ArrayList<>();
    private java.util.List<Periodicidad> periodicidadesCache = new java.util.ArrayList<>();

    private final DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ReportesPanel() {
        initComponents();
        tabla.setModel(model);
        configurarSpinnersFecha();
        cargarTiposEnCombo();
        cargarPeriodicidadesEnCombo();
        setDefaultFechas();
        btnExportPdf.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCargar = new javax.swing.JButton();
        btnExportPdf = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        cbPeriodicidad = new javax.swing.JComboBox<>();
        spDesde = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spHasta = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();

        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        btnExportPdf.setText("Exportar pdf");
        btnExportPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportPdfActionPerformed(evt);
            }
        });

        jLabel1.setText("Reportes");

        jLabel2.setText("Reporte:");

        jLabel3.setText("Rango:");

        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbPeriodicidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Desde:");

        jLabel5.setText("Hasta:");

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
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(280, 280, 280)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPeriodicidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCargar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExportPdf)
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCargar)
                            .addComponent(btnExportPdf))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbPeriodicidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(spHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        try {
            TipoReporte tipo = getTipoSeleccionado();
            Periodicidad per = getPeriodicidadSeleccionada();

            Date desde = (Date) spDesde.getValue();
            Date hasta = (Date) spHasta.getValue();

            List<Map<String, Object>> filas = service.consultar(tipo, per, desde, hasta);

            cargarTabla(filas);
            
            generarGrafico(filas, tipo); 

            btnExportPdf.setEnabled(filas != null && !filas.isEmpty());

            JOptionPane.showMessageDialog(this,
                    "Resultados: " + (filas == null ? 0 : filas.size()),
                    "OK", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            btnExportPdf.setEnabled(false);
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error cargando reporte:\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void generarGrafico(List<Map<String, Object>> filas, TipoReporte tipo) {
        if (filas == null || filas.isEmpty()) {
            jScrollPane2.setViewportView(new javax.swing.JLabel("No hay datos para graficar."));
            return;
        }

        String colX = "";
        String colY = "";
        String nombreReporte = tipo.name();
        
        if (nombreReporte.equals("VENTAS_RUTA_TIEMPO")) {
            colX = "ruta";
            colY = "total_ventas";
        } else if (nombreReporte.equals("PREFERENCIA_HORARIO")) {
            colX = "hora_salida";
            colY = "boletos";
        } else if (nombreReporte.equals("TOP_RUTAS_POR_CLIENTE")) {
            colX = "cliente";
            colY = "total_ventas";
        }

        Map<String, Object> primeraFila = filas.get(0);

        if (!primeraFila.containsKey(colX)) {
             if (primeraFila.containsKey("ruta")) colX = "ruta";
             else if (primeraFila.containsKey("Ruta")) colX = "Ruta";
             else if (primeraFila.containsKey("cliente")) colX = "cliente";
             else if (primeraFila.containsKey("hora_salida")) colX = "hora_salida";
        }

        if (!primeraFila.containsKey(colY)) {
             if (primeraFila.containsKey("total_ventas")) colY = "total_ventas";
             else if (primeraFila.containsKey("total")) colY = "total";
             else if (primeraFila.containsKey("boletos_vendidos")) colY = "boletos_vendidos";
             else if (primeraFila.containsKey("boletos")) colY = "boletos"; 
        }

        if (colX.isEmpty() || colY.isEmpty() || !primeraFila.containsKey(colX) || !primeraFila.containsKey(colY)) {
            jScrollPane2.setViewportView(new javax.swing.JLabel("No se pudieron identificar columnas para graficar (" + colX + "," + colY + ")"));
            return;
        }

        String[] etiquetas = new String[filas.size()];
        double[] valores = new double[filas.size()];

        try {
            for (int i = 0; i < filas.size(); i++) {
                Map<String, Object> row = filas.get(i);
                
                Object objX = row.get(colX);
                etiquetas[i] = (objX == null) ? "" : objX.toString();

                Object objY = row.get(colY);
                if (objY instanceof Number) {
                    valores[i] = ((Number) objY).doubleValue();
                } else {
                    try {
                        valores[i] = Double.parseDouble(objY.toString());
                    } catch (Exception e) {
                        valores[i] = 0.0;
                    }
                }
            }
            
            com.transporte.gestion.global.utils.GraficoBarrasPanel grafico = 
                new com.transporte.gestion.global.utils.GraficoBarrasPanel(etiquetas, valores);
            jScrollPane2.setViewportView(grafico);

        } catch (Exception e) {
            e.printStackTrace();
            jScrollPane2.setViewportView(new javax.swing.JLabel("Error generando gráfico: " + e.getMessage()));
        }
    }

    private void cargarTabla(List<Map<String, Object>> filas) {
        model.setRowCount(0);
        model.setColumnCount(0);

        if (filas == null || filas.isEmpty()) {
            return;
        }

        // columnas desde la primera fila
        Map<String, Object> first = filas.get(0);
        for (String col : first.keySet()) {
            model.addColumn(col);
        }

        for (Map<String, Object> row : filas) {
            Object[] data = row.values().toArray(new Object[0]);
            model.addRow(data);
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnExportPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportPdfActionPerformed
        try {
            System.out.print("aaaaaaaaaaaaaaaaaaaa1");
            TipoReporte tipo = getTipoSeleccionado();
            Periodicidad per = getPeriodicidadSeleccionada();

            Date desde = getSpinnerDate(spDesde);
            Date hasta = getSpinnerDate(spHasta);

            Path baseDir = Paths.get(RUTA_PDF);
            Files.createDirectories(baseDir);
            System.out.print("aaaaaaaaaaaaaaaaaaaa2");
            byte[] pdf = PdfPentahoService.generarReporte(tipo, per, desde, hasta);
            System.out.print("aaaaaaaaaaaaaaaaaaaa33");
            String nombre = tipo.getFilePrefix() + "_" + per.name() + "_" + Utils.fmtFechaArchivo(desde) + "_a_" + Utils.fmtFechaArchivo(hasta) + ".pdf";
            Path out = baseDir.resolve(nombre);
            System.out.print("aaaaaaaaaaaaaaaaaaaa4");
            Files.write(out, pdf);
            System.out.print("aaaaaaaaaaaaaaaaaaaa5");
            JOptionPane.showMessageDialog(
                    this,
                    "PDF exportado:\n" + out.toAbsolutePath(),
                    "OK",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo exportar:\n" + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }//GEN-LAST:event_btnExportPdfActionPerformed

    private void cargarTiposEnCombo() {
        tiposCache = java.util.Arrays.asList(TipoReporte.values());

        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
        for (TipoReporte t : tiposCache) {
            m.addElement(t.name()); // o t.getLabel() si tienes
        }
        cbTipo.setModel(m);

        if (m.getSize() > 0) {
            cbTipo.setSelectedIndex(0);
        }
    }

    private void cargarPeriodicidadesEnCombo() {
        periodicidadesCache = java.util.Arrays.asList(Periodicidad.values());

        DefaultComboBoxModel<String> m = new DefaultComboBoxModel<>();
        for (Periodicidad p : periodicidadesCache) {
            m.addElement(p.name());
        }
        cbPeriodicidad.setModel(m);

        int idxMensual = -1;
        for (int i = 0; i < periodicidadesCache.size(); i++) {
            if (periodicidadesCache.get(i) == Periodicidad.MENSUAL) {
                idxMensual = i;
                break;
            }
        }
        if (idxMensual >= 0) {
            cbPeriodicidad.setSelectedIndex(idxMensual);
        } else if (m.getSize() > 0) {
            cbPeriodicidad.setSelectedIndex(0);
        }
    }

    private TipoReporte getTipoSeleccionado() {
        int idx = cbTipo.getSelectedIndex();
        if (idx < 0 || idx >= tiposCache.size()) {
            return TipoReporte.values()[0];
        }
        return tiposCache.get(idx);
    }

    private Periodicidad getPeriodicidadSeleccionada() {
        int idx = cbPeriodicidad.getSelectedIndex();
        if (idx < 0 || idx >= periodicidadesCache.size()) {
            return Periodicidad.values()[0];
        }
        return periodicidadesCache.get(idx);
    }

    private void setDefaultFechas() {
        Date hoy = new Date();
        spHasta.setValue(hoy);

        Calendar cal = Calendar.getInstance();
        cal.setTime(hoy);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        spDesde.setValue(cal.getTime());
    }

    private static Date getSpinnerDate(JSpinner sp) {
        Object v = sp.getValue();
        return (v instanceof Date) ? (Date) v : new Date();
    }

    private void configurarSpinnersFecha() {
        spDesde.setModel(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        spHasta.setModel(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));

        spDesde.setEditor(new JSpinner.DateEditor(spDesde, "yyyy-MM-dd"));
        spHasta.setEditor(new JSpinner.DateEditor(spHasta, "yyyy-MM-dd"));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnExportPdf;
    private javax.swing.JComboBox<String> cbPeriodicidad;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spDesde;
    private javax.swing.JSpinner spHasta;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
