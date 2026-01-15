package com.transporte.gestion.dao.Infraestructura;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.CopTerm;
import com.transporte.gestion.modelo.Infraestructura.CopTermView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class CopTermDAO {

    private static final String SQL_LIST
            = "SELECT ct.cooperativa_id, ct.terminal_id, ct.estado, "
            + "       c.nombre AS nom_coop, t.nombre AS nom_term "
            + "FROM cop_term ct "
            + "JOIN cooperativa c ON c.id = ct.cooperativa_id "
            + "JOIN terminal t ON t.id = ct.terminal_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR c.nombre ILIKE '%' || ? || '%' "
            + "   OR t.nombre ILIKE '%' || ? || '%' ) "
            + "ORDER BY c.nombre ASC, t.nombre ASC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + "FROM cop_term ct "
            + "JOIN cooperativa c ON c.id = ct.cooperativa_id "
            + "JOIN terminal t ON t.id = ct.terminal_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR c.nombre ILIKE '%' || ? || '%' "
            + "   OR t.nombre ILIKE '%' || ? || '%' )";

    private static final String SQL_FIND_COMPOSITE
            = "SELECT ct.cooperativa_id, ct.terminal_id, ct.estado, "
            + "       c.nombre AS nom_coop, t.nombre AS nom_term "
            + "FROM cop_term ct "
            + "JOIN cooperativa c ON c.id = ct.cooperativa_id "
            + "JOIN terminal t ON t.id = ct.terminal_id "
            + "WHERE ct.cooperativa_id = ? "
            + "  AND ct.terminal_id = ?";

    private static final String SQL_INSERT
            = "INSERT INTO cop_term (cooperativa_id, terminal_id, estado) "
            + "VALUES (?, ?, true) "
            + "ON CONFLICT (cooperativa_id, terminal_id) "
            + "DO UPDATE SET estado = true";

    private static final String SQL_DESACTIVAR
            = "UPDATE cop_term "
            + "SET estado = false "
            + "WHERE cooperativa_id = ? "
            + "  AND terminal_id = ?";

    private static final String SQL_REACTIVAR
            = "UPDATE cop_term "
            + "SET estado = true "
            + "WHERE cooperativa_id = ? "
            + "  AND terminal_id = ?";

    public List<CopTermView> listarPaginado(String filtro, int limit, int offset) {

        List<CopTermView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setInt(5, limit);
            ps.setInt(6, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CopTermView v = new CopTermView();
                    v.setCooperativaId(rs.getInt("cooperativa_id"));
                    v.setTerminalId(rs.getInt("terminal_id"));
                    v.setEstado(rs.getBoolean("estado"));
                    v.setNombreCooperativa(rs.getString("nom_coop"));
                    v.setNombreTerminal(rs.getString("nom_term"));
                    lista.add(v);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listar CopTerm: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_COUNT)) {

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
            System.out.println("Error contar CopTerm: " + e.getMessage());
        }

        return 0;
    }

    public CopTermView buscarPorId(int coopId, int termId) {

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_COMPOSITE)) {

            ps.setInt(1, coopId);
            ps.setInt(2, termId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CopTermView v = new CopTermView();
                    v.setCooperativaId(rs.getInt("cooperativa_id"));
                    v.setTerminalId(rs.getInt("terminal_id"));
                    v.setEstado(rs.getBoolean("estado"));
                    v.setNombreCooperativa(rs.getString("nom_coop"));
                    v.setNombreTerminal(rs.getString("nom_term"));
                    return v;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error buscarPorId CopTerm: " + e.getMessage());
        }

        return null;
    }

    public ResultadoOperacion insertar(CopTerm ct) {

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, ct.getCooperativaId());
            ps.setInt(2, ct.getTerminalId());
            ps.executeUpdate();

            return ResultadoOperacion.ok("Asignación activada correctamente.");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion desactivar(int coopId, int termId) {

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_DESACTIVAR)) {

            ps.setInt(1, coopId);
            ps.setInt(2, termId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Asignación desactivada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la asignación.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion reactivar(int coopId, int termId) {

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_REACTIVAR)) {

            ps.setInt(1, coopId);
            ps.setInt(2, termId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Asignación activada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la asignación.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
}