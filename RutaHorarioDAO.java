package com.transporte.gestion.dao.Operaciones;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.RutaHorario;
import com.transporte.gestion.modelo.Operaciones.RutaHorarioView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class RutaHorarioDAO {

    private static final String SQL_LIST
            = "SELECT rh.ruta_id, rh.horario_id, rh.estado, "
            + "       CONCAT(t1.nombre, ' - ', t2.nombre) AS nom_ruta, "
            + "       h.hora_salida::TEXT AS hora_salida "
            + "FROM ruta_horario rh "
            + "JOIN ruta r ON r.id = rh.ruta_id "
            + "JOIN terminal t1 ON t1.id = r.origen_terminal_id "
            + "JOIN terminal t2 ON t2.id = r.destino_terminal_id "
            + "JOIN horario h ON h.id = rh.horario_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR t1.nombre ILIKE '%' || ? || '%' "
            + "   OR t2.nombre ILIKE '%' || ? || '%' ) "
            + "ORDER BY t1.nombre ASC, t2.nombre ASC, h.hora_salida ASC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + "FROM ruta_horario rh "
            + "JOIN ruta r ON r.id = rh.ruta_id "
            + "JOIN terminal t1 ON t1.id = r.origen_terminal_id "
            + "JOIN terminal t2 ON t2.id = r.destino_terminal_id "
            + "JOIN horario h ON h.id = rh.horario_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR t1.nombre ILIKE '%' || ? || '%' "
            + "   OR t2.nombre ILIKE '%' || ? || '%' )";

    private static final String SQL_FIND_COMPOSITE
            = "SELECT rh.ruta_id, rh.horario_id, rh.estado, "
            + "       CONCAT(t1.nombre, ' - ', t2.nombre) AS nom_ruta, "
            + "       h.hora_salida::TEXT AS hora_salida "
            + "FROM ruta_horario rh "
            + "JOIN ruta r ON r.id = rh.ruta_id "
            + "JOIN terminal t1 ON t1.id = r.origen_terminal_id "
            + "JOIN terminal t2 ON t2.id = r.destino_terminal_id "
            + "JOIN horario h ON h.id = rh.horario_id "
            + "WHERE rh.ruta_id = ? "
            + "  AND rh.horario_id = ?";

    private static final String SQL_INSERT
            = "INSERT INTO ruta_horario (ruta_id, horario_id, estado) "
            + "VALUES (?, ?, true) "
            + "ON CONFLICT (ruta_id, horario_id) "
            + "DO UPDATE SET estado = true";

    private static final String SQL_DESACTIVAR
            = "UPDATE ruta_horario "
            + "SET estado = false "
            + "WHERE ruta_id = ? "
            + "  AND horario_id = ?";

    private static final String SQL_REACTIVAR
            = "UPDATE ruta_horario "
            + "SET estado = true "
            + "WHERE ruta_id = ? "
            + "  AND horario_id = ?";

    public List<RutaHorarioView> listarPaginado(String filtro, int limit, int offset) {

        List<RutaHorarioView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setInt(5, limit);
            ps.setInt(6, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RutaHorarioView v = new RutaHorarioView();
                    v.setRutaId(rs.getInt("ruta_id"));
                    v.setHorarioId(rs.getInt("horario_id"));
                    v.setEstado(rs.getBoolean("estado"));
                    v.setNombreRuta(rs.getString("nom_ruta"));
                    v.setHoraSalida(rs.getString("hora_salida"));
                    lista.add(v);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listar RutaHorario: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_COUNT)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error contar RutaHorario: " + e.getMessage());
        }

        return 0;
    }

    public RutaHorarioView buscarPorId(int rutaId, int horarioId) {

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_COMPOSITE)) {

            ps.setInt(1, rutaId);
            ps.setInt(2, horarioId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    RutaHorarioView v = new RutaHorarioView();
                    v.setRutaId(rs.getInt("ruta_id"));
                    v.setHorarioId(rs.getInt("horario_id"));
                    v.setEstado(rs.getBoolean("estado"));
                    v.setNombreRuta(rs.getString("nom_ruta"));
                    v.setHoraSalida(rs.getString("hora_salida"));
                    return v;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error buscarPorId RutaHorario: " + e.getMessage());
        }

        return null;
    }

    public ResultadoOperacion insertar(RutaHorario rh) {

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, rh.getRutaId());
            ps.setInt(2, rh.getHorarioId());
            ps.executeUpdate();

            return ResultadoOperacion.ok("Asignación activada correctamente.");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion desactivar(int rutaId, int horarioId) {

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_DESACTIVAR)) {

            ps.setInt(1, rutaId);
            ps.setInt(2, horarioId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Asignación desactivada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la asignación.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion reactivar(int rutaId, int horarioId) {

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_REACTIVAR)) {

            ps.setInt(1, rutaId);
            ps.setInt(2, horarioId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Asignación activada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la asignación.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public List<RutaHorario> listar() {
        List<RutaHorario> lista = new ArrayList<>();
        String sql = "SELECT id, ruta_id, horario_id, estado FROM ruta_horario WHERE estado = true ORDER BY id";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RutaHorario rh = new RutaHorario();
                rh.setId(rs.getInt("id"));
                rh.setRutaId(rs.getInt("ruta_id"));
                rh.setHorarioId(rs.getInt("horario_id"));
                rh.setEstado(rs.getBoolean("estado"));
                lista.add(rh);
            }

        } catch (SQLException e) {
            System.out.println("Error listar RutaHorario: " + e.getMessage());
        }

        return lista;
    }

    public List<RutaHorarioView> listarView() {
        List<RutaHorarioView> lista = new ArrayList<>();
        String sql
                = "SELECT rh.id, rh.ruta_id, rh.horario_id, r.cooperativa_id, "
                + "       (t_or.nombre || ' - ' || t_de.nombre) AS nombre_ruta, "
                + "       h.hora_salida::TEXT AS hora_salida, "
                + "       rh.estado "
                + "FROM ruta_horario rh "
                + "JOIN ruta r ON r.id = rh.ruta_id "
                + "JOIN terminal t_or ON t_or.id = r.origen_terminal_id "
                + "JOIN terminal t_de ON t_de.id = r.destino_terminal_id "
                + "JOIN horario h ON h.id = rh.horario_id "
                + "WHERE rh.estado = true "
                + "ORDER BY rh.id";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RutaHorarioView v = new RutaHorarioView();
                v.setId(rs.getInt("id"));
                v.setRutaId(rs.getInt("ruta_id"));
                v.setHorarioId(rs.getInt("horario_id"));
                v.setNombreRuta(rs.getString("nombre_ruta"));
                v.setHoraSalida(rs.getString("hora_salida"));
                v.setEstado(rs.getBoolean("estado"));
                v.setCooperativa(rs.getInt("cooperativa_id"));
                lista.add(v);
            }

        } catch (SQLException e) {
            System.out.println("Error listar RutaHorario: " + e.getMessage());
        }

        return lista;
    }
}
