package com.transporte.gestion.dao.Flota;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.Asiento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsientoDAO {

    private static final String SQL_LIST_BY_BUS
            = "SELECT id, bus_id, numero, estado, ubicacion "
            + "FROM asientos "
            + "WHERE bus_id = ? "
            + "ORDER BY numero ASC";

    private static final String SQL_UPDATE_UBICACION
            = "UPDATE asientos SET ubicacion = ? WHERE id = ?";

    private static final String SQL_TOGGLE_ESTADO
            = "UPDATE asientos SET estado = ? WHERE id = ?";

    private static final String SQL_COUNT_BY_BUS
            = "SELECT COUNT(*) FROM asientos WHERE bus_id = ?";

    private static final String SQL_MAX_NUMERO
            = "SELECT COALESCE(MAX(numero), 0) FROM asientos WHERE bus_id = ?";

    public List<Asiento> listarPorBus(String placa) {
        List<Asiento> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST_BY_BUS)) {

            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Asiento a = new Asiento();
                    a.setId(rs.getInt("id"));
                    a.setBusId(rs.getString("bus_id"));
                    a.setNumero(rs.getShort("numero"));
                    a.setEstado(rs.getBoolean("estado"));
                    a.setUbicacion(rs.getString("ubicacion"));
                    lista.add(a);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPorBus asientos: " + e.getMessage());
        }
        return lista;
    }

    public ResultadoOperacion actualizarUbicacion(Integer asientoId, String ubicacion) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE_UBICACION)) {

            ps.setString(1, ubicacion);
            ps.setInt(2, asientoId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return ResultadoOperacion.ok("Ubicación actualizada.");
            }
            return ResultadoOperacion.error("No se encontró el asiento.");
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion setEstado(Integer asientoId, boolean estado) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_TOGGLE_ESTADO)) {

            ps.setBoolean(1, estado);
            ps.setInt(2, asientoId);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                return ResultadoOperacion.ok(estado ? "Asiento activado." : "Asiento desactivado.");
            }
            return ResultadoOperacion.error("No se encontró el asiento.");
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public int contarPorBus(String placa) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_COUNT_BY_BUS)) {

            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error contarPorBus asientos: " + e.getMessage());
        }
        return 0;
    }

    public short maxNumeroPorBus(String placa) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_MAX_NUMERO)) {

            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getShort(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error maxNumeroPorBus: " + e.getMessage());
        }
        return 0;
    }
}
