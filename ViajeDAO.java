package com.transporte.gestion.dao.Operaciones;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.enums.BoletoEstado;
import com.transporte.gestion.global.enums.ViajeEstado;
import com.transporte.gestion.global.enums.ConstantesNegocio;
import com.transporte.gestion.modelo.Operaciones.Viaje;
import com.transporte.gestion.modelo.Operaciones.ViajeView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.postgresql.util.PSQLException;

public class ViajeDAO {

    private static final String SQL_SELECT_VIEW
            = "SELECT v.id, v.fecha, v.estado, "
            + "       TO_CHAR(h.hora_salida, 'HH24:MI:SS') AS hora_salida, "
            + "       b.placa AS placa_bus, "
            + "       (per.nombre || ' ' || per.apellido) AS chofer_nombre, "
            + "       (t_or.nombre || ' - ' || t_de.nombre) AS ruta_nombre, "
            + "       coop.nombre AS cooperativa_nombre ";

    private static final String SQL_FROM_JOINS
            = "FROM viaje v "
            + "JOIN ruta_horario rh ON rh.id = v.ruta_horario_id "
            + "JOIN horario h ON h.id = rh.horario_id "
            + "JOIN ruta r ON r.id = rh.ruta_id "
            + "JOIN terminal t_or ON t_or.id = r.origen_terminal_id "
            + "JOIN terminal t_de ON t_de.id = r.destino_terminal_id "
            + "JOIN chofer_bus cb ON cb.id = v.chofer_bus_id "
            + "JOIN bus b ON b.placa = cb.bus_id "
            + "JOIN chofer ch ON ch.id = cb.chofer_id "
            + "JOIN persona per ON per.id = ch.persona_id "
            + "JOIN cooperativa coop ON coop.id = r.cooperativa_id ";

    private static final String SQL_FILTRO
            = " ( ? IS NULL OR TRIM(?) = '' "
            + "   OR b.placa ILIKE '%' || ? || '%' "
            + "   OR (per.nombre || ' ' || per.apellido) ILIKE '%' || ? || '%' "
            + "   OR t_or.nombre ILIKE '%' || ? || '%' "
            + "   OR t_de.nombre ILIKE '%' || ? || '%' "
            + "   OR v.estado ILIKE '%' || ? || '%' ) ";

    private static final String SQL_LIST
            = SQL_SELECT_VIEW
            + SQL_FROM_JOINS
            + "WHERE " + SQL_FILTRO
            + "ORDER BY v.fecha DESC, h.hora_salida ASC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + SQL_FROM_JOINS
            + "WHERE " + SQL_FILTRO;

    private static final String SQL_LIST_POR_CHOFER
            = SQL_SELECT_VIEW
            + SQL_FROM_JOINS
            + "WHERE per.id = ? AND " + SQL_FILTRO
            + "ORDER BY v.fecha DESC, h.hora_salida ASC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT_POR_CHOFER
            = "SELECT COUNT(*) "
            + SQL_FROM_JOINS
            + "WHERE per.id = ? AND " + SQL_FILTRO;

    private static final String SQL_LIST_VENDEDOR
            = SQL_SELECT_VIEW
            + SQL_FROM_JOINS
            + "WHERE v.fecha >= ? AND v.fecha <= ? AND " + SQL_FILTRO
            + "ORDER BY v.fecha ASC, h.hora_salida ASC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT_VENDEDOR
            = "SELECT COUNT(*) "
            + SQL_FROM_JOINS
            + "WHERE v.fecha >= ? AND v.fecha <= ? AND " + SQL_FILTRO;

    private static final String SQL_FIND_BY_ID
            = "SELECT id, ruta_horario_id, chofer_bus_id, fecha, estado "
            + "FROM viaje WHERE id = ?";

    private static final String SQL_INSERT_VIAJE
            = "INSERT INTO viaje (ruta_horario_id, chofer_bus_id, fecha, estado) "
            + "VALUES (?, ?, ?, ?) RETURNING id";

    private static final String SQL_UPDATE_VIAJE
            = "UPDATE viaje SET ruta_horario_id=?, chofer_bus_id=?, fecha=?, estado=? "
            + "WHERE id=?";

    private static final String SQL_UPDATE_ESTADO
            = "UPDATE viaje SET estado=? WHERE id=?";

    private static final String SQL_GET_BUS_PLACA_FROM_CHOFER_BUS
            = "SELECT bus_id FROM chofer_bus WHERE id = ?";

    private static final String SQL_LIST_ASIENTOS_BUS
            = "SELECT id FROM asientos WHERE bus_id = ? ORDER BY numero ASC";

    private static final String SQL_INSERT_BOLETO
            = "INSERT INTO boletos (codigo, viaje_id, asiento_id, estado, fecha_compra, precio_base) "
            + "VALUES (?, ?, ?, ?, NULL, ?)";

    private static final String SQL_GET_TARIFA_ACTIVA
            = "SELECT precio_base "
            + "FROM tarifa "
            + "WHERE ruta_horario_id = ? AND estado = true";

    private static final String SQL_GET_ESTADO_VIAJE
            = "SELECT estado FROM viaje WHERE id = ?";

    private static final String SQL_UPDATE_VIAJE_ESTADO
            = "UPDATE viaje SET estado = ? WHERE id = ?";

    private static final String SQL_UPDATE_BOLETOS_ESTADO_BY_VIAJE
            = "UPDATE boletos SET estado = ? WHERE viaje_id = ? AND estado = " + BoletoEstado.DISPONIBLE.getCode();

    private static final String SQL_EXISTE_TARIFA_ACTIVA
            = "SELECT 1 FROM tarifa WHERE ruta_horario_id = ? AND estado = TRUE LIMIT 1";

    public List<ViajeView> listarPaginadoVendedor(
            String filtro, int limit, int offset,
            java.sql.Date desde, java.sql.Date hasta
    ) {
        List<ViajeView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST_VENDEDOR)) {

            int i = 1;

            ps.setDate(i++, desde);
            ps.setDate(i++, hasta);

            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);

            ps.setInt(i++, limit);
            ps.setInt(i++, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapViajeView(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error ViajeDAO.listarPaginadoVendedor: " + e.getMessage());
        }

        return lista;
    }

    public int contarVendedor(
            String filtro,
            java.sql.Date desde, java.sql.Date hasta
    ) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_COUNT_VENDEDOR)) {

            int i = 1;

            ps.setDate(i++, desde);
            ps.setDate(i++, hasta);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error ViajeDAO.contarVendedor: " + e.getMessage());
        }
        return 0;
    }

    public Viaje buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Viaje v = new Viaje();
                    v.setId(rs.getInt("id"));
                    v.setRutaHorarioId(rs.getInt("ruta_horario_id"));
                    v.setChoferBusId(rs.getInt("chofer_bus_id"));
                    v.setFecha(rs.getDate("fecha"));
                    v.setEstado(rs.getString("estado"));
                    return v;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error ViajeDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<ViajeView> listarPaginado(String filtro, int limit, int offset) {
        List<ViajeView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            int i = 1;
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);

            ps.setInt(i++, limit);
            ps.setInt(i++, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapViajeView(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error ViajeDAO.listarPaginado: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_COUNT)) {

            int i = 1;
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error ViajeDAO.contar: " + e.getMessage());
        }
        return 0;
    }

    public List<ViajeView> listarPaginadoPorChofer(String filtro, int limit, int offset, int personaId) {
        List<ViajeView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST_POR_CHOFER)) {

            int i = 1;
            ps.setInt(i++, personaId);

            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);

            ps.setInt(i++, limit);
            ps.setInt(i++, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapViajeView(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error ViajeDAO.listarPaginadoPorChofer: " + e.getMessage());
        }

        return lista;
    }

    public int contarPorChofer(String filtro, int personaId) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_COUNT_POR_CHOFER)) {

            int i = 1;
            ps.setInt(i++, personaId);

            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error ViajeDAO.contarPorChofer: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertarConBoletos(Viaje v) {
        try (Connection con = ConexionBD.getConnection()) {
            con.setAutoCommit(false);

            try {
                int viajeId;
                try (PreparedStatement ps = con.prepareStatement(SQL_INSERT_VIAJE)) {
                    ps.setInt(1, v.getRutaHorarioId());
                    ps.setInt(2, v.getChoferBusId());
                    ps.setDate(3, new java.sql.Date(v.getFecha().getTime()));
                    ps.setString(4, v.getEstado() != null ? v.getEstado() : ViajeEstado.PROGRAMADO.getCode());

                    try (ResultSet rs = ps.executeQuery()) {
                        if (!rs.next()) {
                            con.rollback();
                            return ResultadoOperacion.error("No se pudo insertar el viaje.");
                        }
                        viajeId = rs.getInt(1);
                    }
                }

                String placaBus = null;
                try (PreparedStatement psBus = con.prepareStatement(SQL_GET_BUS_PLACA_FROM_CHOFER_BUS)) {
                    psBus.setInt(1, v.getChoferBusId());
                    try (ResultSet rs = psBus.executeQuery()) {
                        if (rs.next()) {
                            placaBus = rs.getString(1);
                        }
                    }
                }
                if (placaBus == null) {
                    con.rollback();
                    return ResultadoOperacion.error("No se encontró el bus asignado al chofer_bus.");
                }

                List<Integer> asientosIds = new ArrayList<>();
                try (PreparedStatement psAs = con.prepareStatement(SQL_LIST_ASIENTOS_BUS)) {
                    psAs.setString(1, placaBus);
                    try (ResultSet rs = psAs.executeQuery()) {
                        while (rs.next()) {
                            asientosIds.add(rs.getInt(1));
                        }
                    }
                }
                if (asientosIds.isEmpty()) {
                    con.rollback();
                    return ResultadoOperacion.error("El bus no tiene asientos creados. Cree asientos antes de crear viajes.");
                }

                try (PreparedStatement psTar = con.prepareStatement(SQL_EXISTE_TARIFA_ACTIVA)) {
                    psTar.setInt(1, v.getRutaHorarioId());
                    try (ResultSet rsTar = psTar.executeQuery()) {
                        if (!rsTar.next()) {
                            con.rollback();
                            return ResultadoOperacion.error(
                                    "No se puede crear el viaje: la Ruta/Horario seleccionada no tiene TARIFA activa. "
                                    + "Cree la tarifa primero."
                            );
                        }
                    }
                }

                java.math.BigDecimal precioBaseTarifa = null;
                try (PreparedStatement psTar = con.prepareStatement(SQL_GET_TARIFA_ACTIVA)) {
                    psTar.setInt(1, v.getRutaHorarioId());
                    try (ResultSet rs = psTar.executeQuery()) {
                        if (rs.next()) {
                            precioBaseTarifa = rs.getBigDecimal("precio_base");
                        }
                    }
                }
                if (precioBaseTarifa == null) {
                    con.rollback();
                    return ResultadoOperacion.error("No existe TARIFA activa para esta Ruta/Horario. Cree la tarifa antes de crear el viaje.");
                }

                try (PreparedStatement psBol = con.prepareStatement(SQL_INSERT_BOLETO)) {
                    for (Integer asientoId : asientosIds) {
                        String codigo = generarCodigoBoleto(viajeId, asientoId);
                        psBol.setString(1, codigo);
                        psBol.setInt(2, viajeId);
                        psBol.setInt(3, asientoId);
                        psBol.setString(4, BoletoEstado.DISPONIBLE.getCode());
                        psBol.setBigDecimal(5, precioBaseTarifa);
                        psBol.addBatch();
                    }
                    psBol.executeBatch();
                }

                con.commit();
                return ResultadoOperacion.ok("Viaje creado y boletos generados (" + asientosIds.size() + ").");

            } catch (PSQLException e) {
                con.rollback();
                if ("23505".equals(e.getSQLState())) {
                    return ResultadoOperacion.error("Ya existe un viaje igual (ruta/chofer_bus/fecha) o boletos duplicados.");
                }
                return ResultadoOperacion.error("Error BD: " + e.getMessage());
            } catch (Exception e) {
                con.rollback();
                return ResultadoOperacion.error("Error: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Viaje v) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE_VIAJE)) {

            ps.setInt(1, v.getRutaHorarioId());
            ps.setInt(2, v.getChoferBusId());
            ps.setDate(3, new java.sql.Date(v.getFecha().getTime()));
            ps.setString(4, v.getEstado());
            ps.setInt(5, v.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Viaje actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion cambiarEstado(int viajeId, String estado) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE_ESTADO)) {

            ps.setString(1, estado);
            ps.setInt(2, viajeId);

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Estado actualizado: " + estado);
            }
            return ResultadoOperacion.error("No se encontró el viaje.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    private ViajeView mapViajeView(ResultSet rs) throws SQLException {
        ViajeView v = new ViajeView();
        v.setId(rs.getInt("id"));
        v.setFecha(rs.getDate("fecha"));
        v.setEstado(rs.getString("estado"));
        v.setHoraSalida(rs.getString("hora_salida"));
        v.setPlacaBus(rs.getString("placa_bus"));
        v.setChoferNombre(rs.getString("chofer_nombre"));
        v.setRutaNombre(rs.getString("ruta_nombre"));
        v.setCooperativaNombre(rs.getString("cooperativa_nombre"));
        return v;
    }

    private String generarCodigoBoleto(int viajeId, int asientoId) {
        String rnd = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        return ConstantesNegocio.PREFIJO_BOLETO + viajeId + "-" + asientoId + "-" + rnd;
    }

    public ResultadoOperacion anularViajeConBoletos(int viajeId) {
        try (Connection con = ConexionBD.getConnection()) {
            con.setAutoCommit(false);

            try {

                String estadoActual = null;
                try (PreparedStatement ps = con.prepareStatement(SQL_GET_ESTADO_VIAJE)) {
                    ps.setInt(1, viajeId);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            estadoActual = rs.getString(1);
                        }
                    }
                }

                if (estadoActual == null) {
                    con.rollback();
                    return ResultadoOperacion.error("No se encontró el viaje.");
                }

                if (ViajeEstado.CANCELADO.getCode().equalsIgnoreCase(estadoActual)) {
                    con.rollback();
                    return ResultadoOperacion.error("El viaje ya está cancelado/anulado.");
                }

                if (ViajeEstado.COMPLETADO.getCode().equalsIgnoreCase(estadoActual)) {
                    con.rollback();
                    return ResultadoOperacion.error("No se puede anular un viaje COMPLETADO.");
                }

                try (PreparedStatement ps = con.prepareStatement(SQL_UPDATE_VIAJE_ESTADO)) {
                    ps.setString(1, ViajeEstado.CANCELADO.getCode());
                    ps.setInt(2, viajeId);

                    int rows = ps.executeUpdate();
                    if (rows <= 0) {
                        con.rollback();
                        return ResultadoOperacion.error("No se pudo anular el viaje.");
                    }
                }

                int boletosAfectados;
                try (PreparedStatement ps = con.prepareStatement(SQL_UPDATE_BOLETOS_ESTADO_BY_VIAJE)) {
                    ps.setString(1, BoletoEstado.ANULADO.getCode());
                    ps.setInt(2, viajeId);
                    boletosAfectados = ps.executeUpdate();
                }

                con.commit();
                return ResultadoOperacion.ok("Viaje anulado y boletos anulados: " + boletosAfectados + ".");

            } catch (Exception e) {
                con.rollback();
                return ResultadoOperacion.error("No se pudo anular el viaje: " + e.getMessage());
            } finally {
                con.setAutoCommit(true);
            }

        } catch (Exception e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        }
    }
}