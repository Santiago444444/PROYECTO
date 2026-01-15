package com.transporte.gestion.dao.Flota;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.ChoferBus;
import com.transporte.gestion.modelo.Flota.ChoferBusView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class ChoferBusDAO {

    private static final String SQL_FIND_BY_ID
            = "SELECT cb.id, cb.bus_id, cb.chofer_id, cb.estado, cb.fecha_inicio, cb.fecha_fin, "
            + "       p.identificacion AS chofer_identificacion, p.nombre AS chofer_nombres, p.apellido AS chofer_apellidos "
            + "FROM chofer_bus cb "
            + "JOIN chofer c ON c.id = cb.chofer_id "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE cb.id = ?";

    private static final String SQL_LIST
            = "SELECT cb.id, cb.bus_id, cb.chofer_id, cb.estado, cb.fecha_inicio, cb.fecha_fin, "
            + "       p.identificacion AS chofer_identificacion, p.nombre AS chofer_nombres, p.apellido AS chofer_apellidos "
            + "FROM chofer_bus cb "
            + "JOIN chofer c ON c.id = cb.chofer_id "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR cb.bus_id ILIKE '%' || ? || '%' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR p.nombre ILIKE '%' || ? || '%' "
            + "   OR p.apellido ILIKE '%' || ? || '%' ) "
            + "ORDER BY cb.id DESC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + "FROM chofer_bus cb "
            + "JOIN chofer c ON c.id = cb.chofer_id "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR cb.bus_id ILIKE '%' || ? || '%' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR p.nombre ILIKE '%' || ? || '%' "
            + "   OR p.apellido ILIKE '%' || ? || '%' )";

    private static final String SQL_INSERT
            = "INSERT INTO chofer_bus (bus_id, chofer_id, estado, fecha_inicio, fecha_fin) "
            + "VALUES (?, ?, TRUE, COALESCE(?, CURRENT_DATE), NULL)";

    private static final String SQL_INACTIVAR
            = "UPDATE chofer_bus "
            + "SET estado = FALSE, fecha_fin = CURRENT_DATE "
            + "WHERE id = ? AND estado = TRUE";

    public ChoferBusView buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapView(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en ChoferBusDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<ChoferBusView> listarPaginado(String filtro, int limit, int offset) {
        List<ChoferBusView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            ps.setString(6, filtro);
            ps.setInt(7, limit);
            ps.setInt(8, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapView(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado chofer_bus: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_COUNT)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            ps.setString(6, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error contar chofer_bus: " + e.getMessage());
        }

        return 0;
    }

    public ResultadoOperacion insertar(ChoferBus cb) {
        if (cb == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setString(1, cb.getBusId().trim().toUpperCase());
            ps.setInt(2, cb.getChoferId());

            if (cb.getFechaInicio() != null) {
                ps.setDate(3, cb.getFechaInicio());
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.executeUpdate();
            return ResultadoOperacion.ok("Chofer asignado al bus correctamente.");

        } catch (PSQLException e) {
            // 23505 = unique_violation
            if ("23505".equals(e.getSQLState())) {

                String msg = e.getMessage() != null ? e.getMessage() : "";

                if (msg.contains("uq_chofer_activo")) {
                    return ResultadoOperacion.error("Ese chofer ya tiene un bus activo asignado.");
                }
                if (msg.contains("uq_bus_chofer_activo")) {
                    return ResultadoOperacion.error("Ese bus ya tiene un chofer activo asignado.");
                }
                if (msg.contains("uq_chofer_bus")) {
                    return ResultadoOperacion.error("Ya existe una asignación igual para ese chofer y bus en la misma fecha de inicio.");
                }
                return ResultadoOperacion.error("No se pudo asignar (registro duplicado).");
            }
            // 23503 = foreign_key_violation
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Bus o chofer no existe (referencia inválida).");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion inactivar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INACTIVAR)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return ResultadoOperacion.ok("Asignación inactivada. Se registró fecha fin.");
            }
            return ResultadoOperacion.error("No se pudo inactivar: ya estaba inactiva o no existe.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    private ChoferBusView mapView(ResultSet rs) throws SQLException {
        ChoferBusView v = new ChoferBusView();
        v.setId(rs.getInt("id"));
        v.setBusId(rs.getString("bus_id"));
        v.setChoferId(rs.getInt("chofer_id"));
        v.setEstado(rs.getBoolean("estado"));
        v.setFechaInicio(rs.getDate("fecha_inicio"));
        v.setFechaFin(rs.getDate("fecha_fin"));
        v.setChoferIdentificacion(rs.getString("chofer_identificacion"));
        v.setChoferNombres(rs.getString("chofer_nombres"));
        v.setChoferApellidos(rs.getString("chofer_apellidos"));
        return v;
    }

    public List<ChoferBus> listar() {
        List<ChoferBus> lista = new ArrayList<>();
        String sql = "SELECT id, bus_id, chofer_id, estado "
                + "FROM chofer_bus WHERE estado = true ORDER BY id";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ChoferBus cb = new ChoferBus();
                cb.setId(rs.getInt("id"));
                cb.setBusId(rs.getString("bus_id"));
                cb.setChoferId(rs.getInt("chofer_id"));
                cb.setEstado(rs.getBoolean("estado"));

                lista.add(cb);
            }

        } catch (SQLException e) {
            System.out.println("Error listar ChoferBus: " + e.getMessage());
        }

        return lista;
    }

    public List<ChoferBusView> listarView() {
        List<ChoferBusView> lista = new ArrayList<>();
        String sql
                = "SELECT cb.id, cb.bus_id, cb.chofer_id, p.identificacion AS chofer_identificacion, "
                + "       (p.nombre || ' ' || p.apellido) AS chofer_nombres "
                + "FROM chofer_bus cb "
                + "JOIN chofer c ON c.id = cb.chofer_id "
                + "JOIN persona p ON p.id = c.persona_id "
                + "WHERE cb.estado = true "
                + "ORDER BY cb.id";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ChoferBusView v = new ChoferBusView();
                v.setId(rs.getInt("id"));
                v.setBusId(rs.getString("bus_id"));
                v.setChoferId(rs.getInt("chofer_id"));
                v.setChoferIdentificacion(rs.getString("chofer_identificacion"));
                v.setChoferNombres(rs.getString("chofer_nombres"));

                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error listar ChoferBus: " + e.getMessage());
        }

        return lista;
    }
    
    public List<ChoferBusView> listarActivos() {
        List<ChoferBusView> lista = new ArrayList<>();
        String sql
                = "SELECT cb.id, cb.bus_id, cb.chofer_id, p.identificacion AS chofer_identificacion, "
                + "       (p.nombre || ' ' || p.apellido) AS chofer_nombres "
                + "FROM chofer_bus cb "
                + "JOIN chofer c ON c.id = cb.chofer_id "
                + "JOIN persona p ON p.id = c.persona_id "
                + "JOIN bus b ON b.placa = cb.bus_id "
                + "WHERE cb.estado AND b.estado NOT IN ('MANTENIMIENTO', 'INACTIVO') "
                + "ORDER BY cb.id";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ChoferBusView v = new ChoferBusView();
                v.setId(rs.getInt("id"));
                v.setBusId(rs.getString("bus_id"));
                v.setChoferId(rs.getInt("chofer_id"));
                v.setChoferIdentificacion(rs.getString("chofer_identificacion"));
                v.setChoferNombres(rs.getString("chofer_nombres"));

                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error listar ChoferBus: " + e.getMessage());
        }

        return lista;
    }
    
    public List<ChoferBusView> listarActivosPorCooperativa(int cooperativaId) {
    List<ChoferBusView> lista = new ArrayList<>();

    String sql =
        "SELECT cb.id, cb.bus_id, cb.chofer_id, " +
        "       p.identificacion AS chofer_identificacion, " +
        "       (p.nombre || ' ' || p.apellido) AS chofer_nombres " +
        "FROM chofer_bus cb " +
        "JOIN chofer c ON c.id = cb.chofer_id " +
        "JOIN persona p ON p.id = c.persona_id " +
        "JOIN bus b ON b.placa = cb.bus_id " +
        "WHERE cb.estado = TRUE " +
        "  AND c.estado = TRUE " +
        "  AND b.cooperativa_id = ? " +
        "  AND (b.estado IS NULL OR b.estado NOT IN ('MANTENIMIENTO', 'INACTIVO')) " +
        "ORDER BY cb.id";

    try (Connection con = ConexionBD.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, cooperativaId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ChoferBusView v = new ChoferBusView();
                v.setId(rs.getInt("id"));
                v.setBusId(rs.getString("bus_id"));
                v.setChoferId(rs.getInt("chofer_id"));
                v.setChoferIdentificacion(rs.getString("chofer_identificacion"));
                v.setChoferNombres(rs.getString("chofer_nombres"));
                lista.add(v);
            }
        }

    } catch (SQLException e) {
        System.out.println("Error listar ChoferBus por cooperativa: " + e.getMessage());
    }

    return lista;
}

}
