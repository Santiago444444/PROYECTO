/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Operaciones;
import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Horario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;
/**
 *
 * @author Lenovo ThinkPad
 */
public class HorarioDAO {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM horario WHERE id = ?";

    public Horario buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Horario h = new Horario();
                    h.setId(rs.getInt("id"));
                    h.setHoraSalida(rs.getTime("hora_salida"));
                    h.setObservaciones(rs.getString("observaciones"));
                    return h;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en HorarioDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<Horario> listarPaginado(String filtro, int limit, int offset) {
        List<Horario> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM horario
            WHERE (? IS NULL OR TRIM(?) = ''
               OR CAST(hora_salida AS TEXT) ILIKE '%' || ? || '%'
               OR observaciones ILIKE '%' || ? || '%')
            ORDER BY hora_salida ASC
            LIMIT ? OFFSET ?
        """;

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setInt(5, limit);
            ps.setInt(6, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Horario h = new Horario();
                    h.setId(rs.getInt("id"));
                    h.setHoraSalida(rs.getTime("hora_salida"));
                    h.setObservaciones(rs.getString("observaciones"));
                    lista.add(h);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado horario: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        String sql = """
            SELECT COUNT(*) FROM horario
            WHERE (? IS NULL OR TRIM(?) = ''
               OR CAST(hora_salida AS TEXT) ILIKE '%' || ? || '%'
               OR observaciones ILIKE '%' || ? || '%')
        """;

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

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
            System.out.println("Error contar horario: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(Horario h) {
        String sql = "INSERT INTO horario (hora_salida, observaciones) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTime(1, h.getHoraSalida());
            
            if (h.getObservaciones() != null && !h.getObservaciones().isBlank()) {
                ps.setString(2, h.getObservaciones().trim());
            } else {
                ps.setNull(2, java.sql.Types.VARCHAR);
            }

            ps.executeUpdate();
            return ResultadoOperacion.ok("Horario registrado correctamente.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un horario con esa hora de salida.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar horario: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Horario h) {
        String sql = "UPDATE horario SET hora_salida = ?, observaciones = ? WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setTime(1, h.getHoraSalida());

            if (h.getObservaciones() != null && !h.getObservaciones().isBlank()) {
                ps.setString(2, h.getObservaciones().trim());
            } else {
                ps.setNull(2, java.sql.Types.VARCHAR);
            }

            ps.setInt(3, h.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Horario actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el horario a actualizar.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un horario con esa hora.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(int id) {
        String sql = "DELETE FROM horario WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Horario eliminado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el horario.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                    "No se puede eliminar el horario porque está asignado a un viaje o ruta."
                );
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
    
    public List<Horario> listarPorRuta(int rutaId) {

        List<Horario> lista = new ArrayList<>();

        String sql = "SELECT h.* " +
                     "FROM horario h " +
                     "INNER JOIN ruta_horario rh ON h.id = rh.horario_id " +
                     "WHERE rh.ruta_id = ? " +
                     "ORDER BY h.hora_salida";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, rutaId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Horario h = new Horario();
                    h.setId(rs.getInt("id"));
                    h.setHoraSalida(rs.getTime("hora_salida"));
                    lista.add(h);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPorRuta: " + e.getMessage());
        }

        return lista;
    }
    public List<Horario> listar() {

        List<Horario> lista = new ArrayList<>();

        String sql = "SELECT * FROM horario ORDER BY hora_salida";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Horario h = new Horario();
                h.setId(rs.getInt("id"));
                h.setHoraSalida(rs.getTime("hora_salida"));
                lista.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Error listar Horario: " + e.getMessage());
        }

        return lista;
    }
}