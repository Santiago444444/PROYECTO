package com.transporte.gestion.vista.Ventas;

import com.transporte.gestion.vista.Personas.ClientePickerDialog;
import com.transporte.gestion.vista.Personas.ClienteFormDialog;
import com.transporte.gestion.controlador.Ventas.BoletoController;
import com.transporte.gestion.controlador.Personas.ClienteController;
import com.transporte.gestion.controlador.Ventas.MetodoPagoController;
import com.transporte.gestion.controlador.Personas.PersonaController;
import com.transporte.gestion.controlador.Ventas.TipoPasajeroController;
import com.transporte.gestion.global.enums.ConstantesNegocio;
import static com.transporte.gestion.global.enums.ConstantesNegocio.RUTA_PDF;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.utils.PdfPentahoService;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Ventas.BoletoView;
import com.transporte.gestion.modelo.Personas.ClienteView;
import com.transporte.gestion.modelo.Ventas.MetodoPago;
import com.transporte.gestion.modelo.Personas.Persona;
import com.transporte.gestion.modelo.Ventas.TipoPasajero;
import java.awt.Dimension;
import java.awt.Window;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerDateModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class VentaBoletosDialog extends javax.swing.JDialog {

    private final int viajeId;
    private final List<Integer> boletoIds;
    private boolean guardado = false;
    private Integer clienteIdSeleccionado = null;

    private final BoletoController boletoController = new BoletoController();
    private final ClienteController clienteController = new ClienteController();
    private final PersonaController personaController = new PersonaController();
    private final MetodoPagoController metodoPagoController = new MetodoPagoController();
    private final TipoPasajeroController tipoPasajeroController = new TipoPasajeroController();

    private List<TipoPasajero> tiposPasajero = new ArrayList<>();
    private List<MetodoPago> cacheMetodosPago = new ArrayList<>();

    private static final BigDecimal IVA_RATE = new BigDecimal(String.valueOf(ConstantesNegocio.IVA_ACTUAL));

    public boolean isGuardado() {
        return guardado;
    }

    private final DefaultTableModel model = new DefaultTableModel(
            new Object[][]{},
            new String[]{
                "BoletoID",
                "Código",
                "Asiento",
                "Pasajero Cédula",
                "Pasajero Nombre",
                "Edad",
                "Discap.",
                "Tipo",
                "Desc.%",
                "PrecioBase",
                "Desc.$",
                "IVA$",
                "Total"
            }
    ) {
        @Override
        public boolean isCellEditable(int row, int col) {

            return col == 3;
        }
    };

    public VentaBoletosDialog(Window parent, int viajeId, List<Integer> boletoIds) {
        super(parent, ModalityType.APPLICATION_MODAL);
        this.viajeId = viajeId;
        this.boletoIds = (boletoIds == null) ? new ArrayList<>() : new ArrayList<>(boletoIds);

        setTitle("Venta / Facturación - Viaje " + viajeId);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initComponents();

        lblSubtotal.setText("Subtotal: 0.00");
        lblDescuento.setText("Descuento: 0.00");
        lblIva.setText("IVA: 0.00");
        lblTotal.setText("Total: 0.00");

        tabla.setModel(model);
        tabla.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setRowHeight(24);

        jScrollPane1.setViewportView(tabla);
        jScrollPane1.setPreferredSize(new java.awt.Dimension(1000, 280)); // ajusta
        jScrollPane1.setMinimumSize(new java.awt.Dimension(600, 200));

        setLocationRelativeTo(parent);
        setMinimumSize(new Dimension(1100, 650));

        java.util.Date hoy = new java.util.Date();
        SpinnerDateModel dm = new SpinnerDateModel(hoy, hoy, null, Calendar.DAY_OF_MONTH);
        spFecha.setModel(dm);
        spFecha.setEditor(new javax.swing.JSpinner.DateEditor(spFecha, "yyyy-MM-dd"));

        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() != TableModelEvent.UPDATE) {
                    return;
                }
                int row = e.getFirstRow();
                int col = e.getColumn();
                if (row >= 0 && col == 3) { // columna cédula pasajero
                    onPasajeroCedulaEditada(row);
                }
            }
        });

        cargarCombos();
        cargarBoletosSeleccionados();
        recalcularTotales();
        ajustarAnchosTabla();

    }

    private void ajustarAnchosTabla() {
        int[] w = {60, 120, 60, 140, 220, 50, 70, 110, 70, 90, 90, 90, 90};
        for (int i = 0; i < w.length && i < tabla.getColumnModel().getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(w[i]);
        }
    }

    private void cargarCombos() {

        tiposPasajero = tipoPasajeroController.listar();
        if (tiposPasajero == null) {
            tiposPasajero = new ArrayList<>();
        }

        cacheMetodosPago = metodoPagoController.listar();
        if (cacheMetodosPago == null) {
            cacheMetodosPago = new ArrayList<>();
        }

        DefaultComboBoxModel<String> mpModel = new DefaultComboBoxModel<>();
        for (MetodoPago m : cacheMetodosPago) {
            // lo que verá el usuario (ajusta si tu Modelo tiene otro campo)
            mpModel.addElement(m.getNombre());
        }

        cmbMetodoPago.setModel(mpModel);
        if (mpModel.getSize() > 0) {
            cmbMetodoPago.setSelectedIndex(0);
        }
    }

    private MetodoPago getMetodoPagoSeleccionado() {
        int idx = cmbMetodoPago.getSelectedIndex();
        if (idx < 0 || idx >= cacheMetodosPago.size()) {
            return null;
        }
        return cacheMetodosPago.get(idx);
    }

    private void cargarBoletosSeleccionados() {
        if (boletoIds.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay boletos seleccionados.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ideal: controller trae resumen por IDs (y valida pertenencia al viaje)
        // Implementa en tu controller: listarPorIds(viajeId, boletoIds)
        List<BoletoView> lista = boletoController.listarPorIds(viajeId, boletoIds); // <-- A IMPLEMENTAR
        if (lista == null) {
            lista = new ArrayList<>();
        }

        model.setRowCount(0);

        for (BoletoView b : lista) {
            BigDecimal precioBase = nvl(b.getPrecioBase());
            model.addRow(new Object[]{
                b.getId(),
                Utils.cargarString(b.getCodigo()),
                b.getAsientoNumero(),
                "", // pasajero cedula (a llenar)
                "", // pasajero nombre
                "", // edad
                "", // discapacidad
                "", // tipo
                "", // desc %
                fmt(precioBase),
                fmt(BigDecimal.ZERO),
                fmt(BigDecimal.ZERO),
                fmt(precioBase) // total inicial = base
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCedulaCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        txtEmailCliente = new javax.swing.JTextField();
        txtDireccionCliente = new javax.swing.JTextField();
        cmbMetodoPago = new javax.swing.JComboBox<>();
        spFecha = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblSubtotal = new javax.swing.JLabel();
        lblDescuento = new javax.swing.JLabel();
        lblIva = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JButton();
        btnCrearCliente = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtCedulaCliente.setEnabled(false);

        txtNombreCliente.setEnabled(false);

        txtTelefonoCliente.setEnabled(false);

        txtEmailCliente.setEnabled(false);

        txtDireccionCliente.setEnabled(false);

        cmbMetodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        lblSubtotal.setText("jLabel1");

        lblDescuento.setText("jLabel2");

        lblIva.setText("jLabel3");

        lblTotal.setText("jLabel4");

        btnBuscarCliente.setText("Elegir Cliente");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        btnCrearCliente.setText("Crear Cliente");
        btnCrearCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearClienteActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtDireccionCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                            .addComponent(txtEmailCliente)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCedulaCliente, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscarCliente)
                            .addComponent(btnCrearCliente))
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(spFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMetodoPago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(27, 27, 27)
                        .addComponent(btnCancelar))
                    .addComponent(lblSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txtCedulaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmailCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDireccionCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbMetodoPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btnBuscarCliente)
                        .addGap(18, 18, 18)
                        .addComponent(btnCrearCliente)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(lblSubtotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescuento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblIva)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTotal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        Window w = (Window) javax.swing.SwingUtilities.getWindowAncestor(this);

        ClientePickerDialog dlg = new ClientePickerDialog(w, clienteController);
        dlg.setVisible(true);

        ClienteView cv = dlg.getClienteSeleccionado(); // ajusta al getter real
        if (cv == null) {
            return;
        }

        // cache en variables
        clienteIdSeleccionado = cv.getId();

        // llenar UI
        txtCedulaCliente.setText(Utils.cargarString(cv.getIdentificacion()));
        txtNombreCliente.setText(Utils.cargarString(cv.getNombres()) + " " + Utils.cargarString(cv.getApellidos()));
        txtTelefonoCliente.setText(Utils.cargarString(cv.getTelefono()));
        txtEmailCliente.setText(Utils.cargarString(cv.getMail()));
        txtDireccionCliente.setText(Utils.cargarString(cv.getObservaciones()));
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnCrearClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearClienteActionPerformed
        Window w = (Window) javax.swing.SwingUtilities.getWindowAncestor(this);

        ClienteFormDialog dlg = new ClienteFormDialog(w, clienteController);
        dlg.setVisible(true);

        if (dlg.isGuardado()) {
            Integer clienteId = dlg.getClienteEditarView().getId();
            if (clienteId != null) {
                ClienteView cv = clienteController.buscarPorId(clienteId);

                if (cv != null) {
                    clienteIdSeleccionado = cv.getId();

                    txtCedulaCliente.setText(Utils.cargarString(cv.getIdentificacion()));
                    txtNombreCliente.setText(Utils.cargarString(cv.getNombres()) + " " + Utils.cargarString(cv.getApellidos()));
                    txtTelefonoCliente.setText(Utils.cargarString(cv.getTelefono()));
                    txtEmailCliente.setText(Utils.cargarString(cv.getMail()));
                    txtDireccionCliente.setText(Utils.cargarString(cv.getObservaciones()));
                }
            }
        }

    }

    private void onPasajeroCedulaEditada(int row) {
        String ced = String.valueOf(model.getValueAt(row, 3)).trim();
        if (ced.isEmpty()) {

            setRowPasajeroInfo(row, null);
            recalcularFila(row);
            recalcularTotales();
            return;
        }

        Persona p = personaController.buscarPorIdentificacion(ced);
        if (p == null) {
            JOptionPane.showMessageDialog(this,
                    "Pasajero no encontrado: " + ced + "\n(Deberías permitir crearlo luego.)",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            setRowPasajeroInfo(row, null);
            recalcularFila(row);
            recalcularTotales();
            return;
        }

        setRowPasajeroInfo(row, p);
        recalcularFila(row);
        recalcularTotales();
    }

    private void setRowPasajeroInfo(int row, Persona p) {
        if (p == null) {
            model.setValueAt("", row, 4); // nombre
            model.setValueAt("", row, 5); // edad
            model.setValueAt("", row, 6); // discapacidad
            model.setValueAt("", row, 7); // tipo
            model.setValueAt("", row, 8); // desc %
            return;
        }

        String nombre = Utils.cargarString(p.getNombre()) + " " + Utils.cargarString(p.getApellido());
        Integer edad = calcularEdad(p.getFechaNacimiento());
        boolean disc = (p.getDiscapacidad() != null && p.getDiscapacidad());

        TipoPasajero tp = resolverTipoPasajero(edad, disc);

        model.setValueAt(nombre.trim(), row, 4);
        model.setValueAt((edad == null) ? "" : String.valueOf(edad), row, 5);
        model.setValueAt(disc ? "SI" : "NO", row, 6);
        model.setValueAt(tp == null ? "" : tp.getNombre(), row, 7);
        model.setValueAt(tp == null ? "" : fmt(nvl(tp.getDescuento())), row, 8);
    }

    private Integer calcularEdad(java.util.Date fechaNac) {
        if (fechaNac == null) {
            return null;
        }

        Calendar nac = Calendar.getInstance();
        nac.setTime(fechaNac);

        Calendar hoy = Calendar.getInstance();

        int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);

        int mesHoy = hoy.get(Calendar.MONTH);
        int mesNac = nac.get(Calendar.MONTH);

        if (mesHoy < mesNac || (mesHoy == mesNac && hoy.get(Calendar.DAY_OF_MONTH) < nac.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }

        if (edad < 0) {
            return null;
        }
        return edad;
    }

    private TipoPasajero resolverTipoPasajero(Integer edad, boolean discapacidad) {
        if (tiposPasajero == null || tiposPasajero.isEmpty()) {
            return null;
        }

        List<TipoPasajero> candidatos = new ArrayList<>();

        if (discapacidad) {
            TipoPasajero t = buscarTipoPorNombre("DISCAPACIDAD");
            if (t != null) {
                candidatos.add(t);
            }
        }

        if (edad != null) {
            if (edad >= ConstantesNegocio.EDAD_MIN_NINO && edad <= ConstantesNegocio.EDAD_MAX_NINO) {
                TipoPasajero t = buscarTipoPorNombre("NIÑO");
                if (t != null) {
                    candidatos.add(t);
                }
            }
            if (edad >= ConstantesNegocio.EDAD_MIN_TERCERA_EDAD) {
                TipoPasajero t = buscarTipoPorNombre("TERCERA_EDAD");
                if (t != null) {
                    candidatos.add(t);
                }
            }
            if (edad >= ConstantesNegocio.EDAD_MIN_ADULTO && edad <= ConstantesNegocio.EDAD_MAX_ADULTO) {
                TipoPasajero t = buscarTipoPorNombre("ADULTO");
                if (t != null) {
                    candidatos.add(t);
                }
            }
        }

        candidatos.sort((a, b) -> nvl(b.getDescuento()).compareTo(nvl(a.getDescuento())));
        return candidatos.isEmpty() ? null : candidatos.get(0);
    }

    private TipoPasajero buscarTipoPorNombre(String nombre) {
        for (TipoPasajero t : tiposPasajero) {
            if (t != null && t.getNombre() != null && t.getNombre().trim().equalsIgnoreCase(nombre)) {
                return t;
            }
        }
        return null;
    }

    private void recalcularFila(int row) {
        BigDecimal precioBase = parseMoney(model.getValueAt(row, 9));
        BigDecimal descPct = parseMoney(model.getValueAt(row, 8)); // porcentaje 0..100

        BigDecimal descMonto = BigDecimal.ZERO;
        if (descPct.compareTo(BigDecimal.ZERO) > 0) {
            descMonto = precioBase.multiply(descPct).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
        }

        BigDecimal neto = precioBase.subtract(descMonto);
        if (neto.compareTo(BigDecimal.ZERO) < 0) {
            neto = BigDecimal.ZERO;
        }

        BigDecimal iva = neto.multiply(IVA_RATE).setScale(2, RoundingMode.HALF_UP);
        BigDecimal total = neto.add(iva).setScale(2, RoundingMode.HALF_UP);

        model.setValueAt(fmt(descMonto), row, 10);
        model.setValueAt(fmt(iva), row, 11);
        model.setValueAt(fmt(total), row, 12);
    }

    private void recalcularTotales() {
        BigDecimal subtotal = BigDecimal.ZERO;
        BigDecimal desc = BigDecimal.ZERO;
        BigDecimal iva = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        for (int r = 0; r < model.getRowCount(); r++) {
            subtotal = subtotal.add(parseMoney(model.getValueAt(r, 9)));
            desc = desc.add(parseMoney(model.getValueAt(r, 10)));
            iva = iva.add(parseMoney(model.getValueAt(r, 11)));
            total = total.add(parseMoney(model.getValueAt(r, 12)));
        }

        lblSubtotal.setText("Subtotal: " + fmt(subtotal));
        lblDescuento.setText("   Descuento: " + fmt(desc));
        lblIva.setText("   IVA: " + fmt(iva));
        lblTotal.setText("   Total: " + fmt(total));

    }//GEN-LAST:event_btnCrearClienteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (tabla.isEditing()) {
            tabla.getCellEditor().stopCellEditing();
        }

        if (clienteIdSeleccionado == null || clienteIdSeleccionado <= 0) {
            JOptionPane.showMessageDialog(this, "Primero seleccione un CLIENTE.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        MetodoPago mp = getMetodoPagoSeleccionado();
        if (mp == null || mp.getId() <= 0) {
            JOptionPane.showMessageDialog(this, "Seleccione método de pago.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Map<Integer, Integer> boletoIdToPasajeroId = new LinkedHashMap<>();
        Map<Integer, Integer> boletoIdToTipoPasajeroId = new LinkedHashMap<>();

        for (int r = 0; r < model.getRowCount(); r++) {
            Integer boletoId = asInt(model.getValueAt(r, 0));
            String cedPas = String.valueOf(model.getValueAt(r, 3)).trim();
            if (cedPas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Falta cédula de pasajero en la fila " + (r + 1), "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Persona p = personaController.buscarPorIdentificacion(cedPas);
            if (p == null) {
                JOptionPane.showMessageDialog(this, "Pasajero no existe: " + cedPas, "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String tipoNombre = String.valueOf(model.getValueAt(r, 7)).trim();
            TipoPasajero tp = tipoNombre.isEmpty() ? null : buscarTipoPorNombre(tipoNombre);
            if (tp == null) {
                JOptionPane.showMessageDialog(this,
                        "No se pudo determinar TipoPasajero en fila " + (r + 1) + ". Revise tipos en DB.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            boletoIdToPasajeroId.put(boletoId, p.getId());
            boletoIdToTipoPasajeroId.put(boletoId, tp.getId());
        }

        java.util.Date fechaUI = (java.util.Date) spFecha.getValue();
        Date fechaFactura = new Date(fechaUI.getTime());

        ResultadoOperacion<Integer> res = boletoController.venderBoletos(
                viajeId,
                boletoIds,
                clienteIdSeleccionado,
                boletoIdToPasajeroId,
                boletoIdToTipoPasajeroId,
                mp.getId(),
                fechaFactura
        );

        if (res != null && res.isOk()) {
            int facturaId = res.getData();

            try {
                Path baseDir = Paths.get(RUTA_PDF);
                Files.createDirectories(baseDir); // crea si no existe

                byte[] pdfFactura = PdfPentahoService.generarFactura(facturaId);
                Files.write(baseDir.resolve("factura_" + facturaId + ".pdf"), pdfFactura);

                byte[] pdfBoletos = PdfPentahoService.generarBoletos(facturaId);
                Files.write(baseDir.resolve("boletos_" + facturaId + ".pdf"), pdfBoletos);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "La factura se guardó, pero falló el PDF:\n" + e.getMessage(),
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
            }

            JOptionPane.showMessageDialog(this, res.getMensaje(), "OK", JOptionPane.INFORMATION_MESSAGE);
            guardado = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, (res == null) ? "Error desconocido." : res.getMensaje(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        guardado = false;
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    private BigDecimal nvl(BigDecimal x) {
        return (x == null) ? BigDecimal.ZERO : x;
    }

    private String fmt(BigDecimal x) {
        if (x == null) {
            return "";
        }
        return x.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }

    private BigDecimal parseMoney(Object v) {
        if (v == null) {
            return BigDecimal.ZERO;
        }
        String s = String.valueOf(v).trim();
        if (s.isEmpty()) {
            return BigDecimal.ZERO;
        }
        try {
            return new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
        } catch (Exception e) {
            return BigDecimal.ZERO;
        }
    }

    private Integer asInt(Object v) {
        if (v == null) {
            return null;
        }
        if (v instanceof Integer) {
            return (Integer) v;
        }
        try {
            return Integer.parseInt(String.valueOf(v).trim());
        } catch (Exception e) {
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrearCliente;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbMetodoPago;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDescuento;
    private javax.swing.JLabel lblIva;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JSpinner spFecha;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtCedulaCliente;
    private javax.swing.JTextField txtDireccionCliente;
    private javax.swing.JTextField txtEmailCliente;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtTelefonoCliente;
    // End of variables declaration//GEN-END:variables
}
