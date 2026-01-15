package com.transporte.gestion.dao.Ventas;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.enums.BoletoEstado;
import com.transporte.gestion.global.enums.ConstantesNegocio;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Ventas.BoletoView;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoletoDAO {

    private static final String SQL_ANULAR_SI_DISPONIBLE
            = "UPDATE boletos SET estado = ? "
            + "WHERE id = ? AND estado = ?";

    private static final String SQL_LIST_BY_VIAJE
            = "SELECT b.id, b.codigo, b.estado, b.fecha_compra, "
            + "       b.precio_base, b.descuento_monto, b.precio_final, b.iva_monto, b.iva_usado, "
            + "       a.numero AS asiento_numero, "
            + "       perCli.identificacion AS cliente_cedula, "
            + "       perPas.identificacion AS pasajero_cedula, "
            + "       tp.nombre AS tipo_pasajero_nombre "
            + "FROM boletos b "
            + "JOIN asientos a ON a.id = b.asiento_id "
            + "LEFT JOIN cliente c ON c.id = b.cliente_id "
            + "LEFT JOIN persona perCli ON perCli.id = c.persona_id "
            + "LEFT JOIN persona perPas ON perPas.id = b.pasajero_id "
            + "LEFT JOIN tipo_pasajero tp ON tp.id = b.tipo_pasajero_id "
            + "WHERE b.viaje_id = ? "
            + "ORDER BY a.numero ASC";

    private static final String SQL_LIST_BY_IDS
            = "SELECT b.id, b.codigo, b.estado, b.fecha_compra, "
            + "       b.precio_base, b.descuento_monto, b.precio_final, b.iva_monto, b.iva_usado, "
            + "       a.numero AS asiento_numero, "
            + "       perCli.identificacion AS cliente_cedula, "
            + "       perPas.identificacion AS pasajero_cedula, "
            + "       tp.nombre AS tipo_pasajero_nombre "
            + "FROM boletos b "
            + "JOIN asientos a ON a.id = b.asiento_id "
            + "LEFT JOIN cliente c ON c.id = b.cliente_id "
            + "LEFT JOIN persona perCli ON perCli.id = c.persona_id "
            + "LEFT JOIN persona perPas ON perPas.id = b.pasajero_id "
            + "LEFT JOIN tipo_pasajero tp ON tp.id = b.tipo_pasajero_id "
            + "WHERE b.viaje_id = ? AND b.id = ANY(?) "
            + "ORDER BY a.numero ASC";

    private static final String SQL_LOCK_DISPONIBLES
            = "SELECT b.id, b.codigo, b.precio_base "
            + "FROM boletos b "
            + "WHERE b.viaje_id = ? "
            + "  AND b.id = ANY (?) "
            + "  AND UPPER(b.estado) = 'DISPONIBLE' "
            + "FOR UPDATE";

    private static final String SQL_GET_TIPOS
            = "SELECT id, descuento, nombre "
            + "FROM tipo_pasajero "
            + "WHERE id = ANY (?)";

    private static final String SQL_UPDATE_BOLETO_VENTA
            = "UPDATE boletos SET "
            + "  estado = ?, "
            + "  fecha_compra = ?, "
            + "  cliente_id = ?, "
            + "  pasajero_id = ?, "
            + "  tipo_pasajero_id = ?, "
            + "  descuento_monto = ?, "
            + "  iva_monto = ?, "
            + "  iva_usado = ?, "
            + "  precio_final = ? "
            + "WHERE id = ? AND viaje_id = ? AND UPPER(estado) = 'DISPONIBLE'";

    private static final String SQL_INSERT_FACT_CAB
            = "INSERT INTO factura_cabecera (cliente_id, fecha, iva, total, metodo_pago_id) "
            + "VALUES (?, ?, ?, ?, ?) RETURNING id";

    private static final String SQL_INSERT_FACT_DET
            = "INSERT INTO factura_detalle (factura_id, boleto_id, descripcion, precio, iva, total) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    public ResultadoOperacion anularVendidoPorCliente(int boletoId) {
        String sql
                = "UPDATE boletos "
                + "SET estado = ? "
                + "WHERE id = ? AND UPPER(estado) = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, BoletoEstado.ANULADO_CLIENTE.getCode());
            ps.setInt(2, boletoId);
            ps.setString(3, BoletoEstado.VENDIDO.getCode());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Boleto anulado por petición del cliente.");
            }
            return ResultadoOperacion.error("Solo se puede anular un boleto en estado VENDIDO.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public List<BoletoView> listarPorViaje(int viajeId) {
        List<BoletoView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST_BY_VIAJE)) {

            ps.setInt(1, viajeId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BoletoView v = new BoletoView();
                    v.setId(rs.getInt("id"));
                    v.setCodigo(rs.getString("codigo"));
                    v.setEstado(rs.getString("estado"));

                    Date fc = rs.getDate("fecha_compra");
                    v.setFechaCompra(fc);

                    int num = rs.getInt("asiento_numero");
                    v.setAsientoNumero(rs.wasNull() ? null : num);

                    v.setClienteCedula(rs.getString("cliente_cedula"));
                    v.setPasajeroCedula(rs.getString("pasajero_cedula"));
                    v.setTipoPasajeroNombre(rs.getString("tipo_pasajero_nombre"));

                    v.setPrecioBase(rs.getBigDecimal("precio_base"));
                    v.setDescuentoMonto(rs.getBigDecimal("descuento_monto"));
                    v.setPrecioFinal(rs.getBigDecimal("precio_final"));
                    v.setIvaMonto(rs.getBigDecimal("iva_monto"));
                    v.setIvaUsado(rs.getBigDecimal("iva_usado"));

                    lista.add(v);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error BoletoDAO.listarPorViaje: " + e.getMessage());
        }

        return lista;
    }

    public ResultadoOperacion anularSiDisponible(int boletoId) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_ANULAR_SI_DISPONIBLE)) {

            ps.setString(1, BoletoEstado.ANULADO.getCode());
            ps.setInt(2, boletoId);
            ps.setString(3, BoletoEstado.DISPONIBLE.getCode());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Boleto anulado.");
            }
            return ResultadoOperacion.error("Solo se puede anular un boleto en estado DISPONIBLE.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public List<BoletoView> listarPorIds(int viajeId, List<Integer> boletoIds) {
        List<BoletoView> lista = new ArrayList<>();
        if (boletoIds == null || boletoIds.isEmpty()) {
            return lista;
        }

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST_BY_IDS)) {

            ps.setInt(1, viajeId);
            Array arr = con.createArrayOf("int4", boletoIds.toArray(new Integer[0]));
            ps.setArray(2, arr);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BoletoView v = new BoletoView();
                    v.setId(rs.getInt("id"));
                    v.setCodigo(rs.getString("codigo"));
                    v.setEstado(rs.getString("estado"));
                    v.setFechaCompra(rs.getDate("fecha_compra"));

                    int num = rs.getInt("asiento_numero");
                    v.setAsientoNumero(rs.wasNull() ? null : num);

                    v.setClienteCedula(rs.getString("cliente_cedula"));
                    v.setPasajeroCedula(rs.getString("pasajero_cedula"));
                    v.setTipoPasajeroNombre(rs.getString("tipo_pasajero_nombre"));

                    v.setPrecioBase(rs.getBigDecimal("precio_base"));
                    v.setDescuentoMonto(rs.getBigDecimal("descuento_monto"));
                    v.setPrecioFinal(rs.getBigDecimal("precio_final"));
                    v.setIvaMonto(rs.getBigDecimal("iva_monto"));
                    v.setIvaUsado(rs.getBigDecimal("iva_usado"));

                    lista.add(v);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error BoletoDAO.listarPorIds: " + e.getMessage());
        }

        return lista;
    }

    private static BigDecimal bdIvaRate() {

        return new BigDecimal(String.valueOf(ConstantesNegocio.IVA_ACTUAL));
    }

    public ResultadoOperacion<Integer> venderConFactura(
            int viajeId,
            List<Integer> boletoIds,
            int clienteId,
            Map<Integer, Integer> boletoIdToPasajeroId,
            Map<Integer, Integer> boletoIdToTipoPasajeroId,
            int metodoPagoId,
            java.sql.Date fechaFactura
    ) {
        if (viajeId <= 0 || clienteId <= 0 || metodoPagoId <= 0 || fechaFactura == null) {
            return ResultadoOperacion.error("Parámetros inválidos para vender.");
        }
        if (boletoIds == null || boletoIds.isEmpty()) {
            return ResultadoOperacion.error("No hay boletos seleccionados.");
        }

        BigDecimal IVA_RATE = bdIvaRate();

        try (Connection con = ConexionBD.getConnection()) {
            con.setAutoCommit(false);

            try {

                List<BoletoVentaRow> rows = lockBoletosDisponibles(con, viajeId, boletoIds);

                if (rows.size() != boletoIds.size()) {
                    con.rollback();
                    return ResultadoOperacion.error(
                            "Uno o más boletos ya no están DISPONIBLES, o no pertenecen al viaje. Refresque la lista."
                    );
                }

                Map<Integer, TipoInfo> tipoMap = cargarTipos(con, new HashSet<>(boletoIdToTipoPasajeroId.values()));
                // validar que existan todos
                for (Integer bid : boletoIds) {
                    Integer tipoId = boletoIdToTipoPasajeroId.get(bid);
                    if (tipoId == null || !tipoMap.containsKey(tipoId)) {
                        con.rollback();
                        return ResultadoOperacion.error("TipoPasajero inválido o no existe para boleto " + bid);
                    }
                }

                BigDecimal sumIva = BigDecimal.ZERO;
                BigDecimal sumTotal = BigDecimal.ZERO;

                Map<Integer, CalculoBoleto> calcMap = new HashMap<>();

                for (BoletoVentaRow br : rows) {
                    int boletoId = br.id;
                    BigDecimal precioBase = nvl(br.precioBase);

                    int pasajeroId = boletoIdToPasajeroId.get(boletoId);
                    int tipoId = boletoIdToTipoPasajeroId.get(boletoId);

                    BigDecimal descPct = nvl(tipoMap.get(tipoId).descuento); // 0..100
                    BigDecimal descMonto = precioBase.multiply(descPct)
                            .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

                    BigDecimal neto = precioBase.subtract(descMonto);
                    if (neto.compareTo(BigDecimal.ZERO) < 0) {
                        neto = BigDecimal.ZERO;
                    }

                    BigDecimal iva = neto.multiply(IVA_RATE).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal total = neto.add(iva).setScale(2, RoundingMode.HALF_UP);

                    calcMap.put(boletoId, new CalculoBoleto(
                            pasajeroId, tipoId, descMonto, iva, total, neto, br.codigo
                    ));

                    sumIva = sumIva.add(iva);
                    sumTotal = sumTotal.add(total);
                }

                int facturaId;
                try (PreparedStatement ps = con.prepareStatement(SQL_INSERT_FACT_CAB)) {
                    ps.setInt(1, clienteId);
                    ps.setDate(2, fechaFactura);
                    ps.setBigDecimal(3, sumIva.setScale(2, RoundingMode.HALF_UP));
                    ps.setBigDecimal(4, sumTotal.setScale(2, RoundingMode.HALF_UP));
                    ps.setInt(5, metodoPagoId);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            con.rollback();
                            return ResultadoOperacion.error("No se pudo crear la factura (cabecera).");
                        }
                        facturaId = rs.getInt(1);
                    }
                }

                try (PreparedStatement psUpd = con.prepareStatement(SQL_UPDATE_BOLETO_VENTA); PreparedStatement psDet = con.prepareStatement(SQL_INSERT_FACT_DET)) {

                    int updatedCount = 0;

                    for (BoletoVentaRow br : rows) {
                        CalculoBoleto c = calcMap.get(br.id);

                        int i = 1;
                        psUpd.setString(i++, BoletoEstado.VENDIDO.getCode());
                        psUpd.setDate(i++, fechaFactura);
                        psUpd.setInt(i++, clienteId);
                        psUpd.setInt(i++, c.pasajeroId);
                        psUpd.setInt(i++, c.tipoPasajeroId);
                        psUpd.setBigDecimal(i++, c.descMonto);
                        psUpd.setBigDecimal(i++, c.ivaMonto);
                        psUpd.setBigDecimal(i++, IVA_RATE);
                        psUpd.setBigDecimal(i++, c.total);
                        psUpd.setInt(i++, br.id);
                        psUpd.setInt(i++, viajeId);

                        int u = psUpd.executeUpdate();
                        if (u != 1) {
                            con.rollback();
                            return ResultadoOperacion.error(
                                    "Conflicto: un boleto dejó de estar DISPONIBLE durante la venta. Refresque e intente de nuevo."
                            );
                        }
                        updatedCount++;

                        psDet.setInt(1, facturaId);
                        psDet.setInt(2, br.id);
                        psDet.setString(3, "Boleto " + br.codigo);
                        psDet.setBigDecimal(4, c.neto);
                        psDet.setBigDecimal(5, c.ivaMonto);
                        psDet.setBigDecimal(6, c.total);
                        psDet.addBatch();
                    }

                    psDet.executeBatch();

                    if (updatedCount != boletoIds.size()) {
                        con.rollback();
                        return ResultadoOperacion.error("No se vendieron todos los boletos. Intente de nuevo.");
                    }
                }

                con.commit();
                return ResultadoOperacion.ok("Venta realizada. Factura #" + facturaId + " (" + boletoIds.size() + " boleto(s)).",facturaId);

            } catch (Exception e) {
                con.rollback();
                return ResultadoOperacion.error("Error al vender: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        }
    }

    private List<BoletoVentaRow> lockBoletosDisponibles(Connection con, int viajeId, List<Integer> boletoIds) throws SQLException {
        List<BoletoVentaRow> rows = new ArrayList<>();

        try (PreparedStatement ps = con.prepareStatement(SQL_LOCK_DISPONIBLES)) {
            ps.setInt(1, viajeId);
            Array arr = con.createArrayOf("int4", boletoIds.toArray(new Integer[0]));
            ps.setArray(2, arr);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    BoletoVentaRow r = new BoletoVentaRow();
                    r.id = rs.getInt("id");
                    r.codigo = rs.getString("codigo");
                    r.precioBase = rs.getBigDecimal("precio_base");
                    rows.add(r);
                }
            }
        }
        return rows;
    }

    private Map<Integer, TipoInfo> cargarTipos(Connection con, Set<Integer> tipoIds) throws SQLException {
        Map<Integer, TipoInfo> map = new HashMap<>();
        if (tipoIds == null || tipoIds.isEmpty()) {
            return map;
        }

        try (PreparedStatement ps = con.prepareStatement(SQL_GET_TIPOS)) {
            Array arr = con.createArrayOf("int4", tipoIds.toArray(new Integer[0]));
            ps.setArray(1, arr);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    TipoInfo t = new TipoInfo();
                    t.id = id;
                    t.descuento = rs.getBigDecimal("descuento");
                    t.nombre = rs.getString("nombre");
                    map.put(id, t);
                }
            }
        }
        return map;
    }

    private BigDecimal nvl(BigDecimal x) {
        return (x == null) ? BigDecimal.ZERO : x;
    }

    private static class BoletoVentaRow {

        int id;
        String codigo;
        BigDecimal precioBase;
    }

    private static class TipoInfo {

        int id;
        BigDecimal descuento;
        String nombre;
    }

    private static class CalculoBoleto {

        int pasajeroId;
        int tipoPasajeroId;
        BigDecimal descMonto;
        BigDecimal ivaMonto;
        BigDecimal total;
        BigDecimal neto;
        String codigo;

        CalculoBoleto(int pasajeroId, int tipoPasajeroId, BigDecimal descMonto,
                BigDecimal ivaMonto, BigDecimal total, BigDecimal neto, String codigo) {
            this.pasajeroId = pasajeroId;
            this.tipoPasajeroId = tipoPasajeroId;
            this.descMonto = descMonto;
            this.ivaMonto = ivaMonto;
            this.total = total;
            this.neto = neto;
            this.codigo = codigo;
        }
    }

}
