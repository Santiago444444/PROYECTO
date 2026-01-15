/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Operaciones;
import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Tarifa;
import com.transporte.gestion.modelo.Operaciones.TarifaView;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;
/**
 *
 * @author Lenovo ThinkPad
 */
public class TarifaDAO {

    private static final String SQL_LISTAR = """
        SELECT
            t.id,
            t.ruta_horario_id,
            t.precio_base,
            t.estado,

            tor.nombre AS origen,
            tde.nombre AS destino,
            h.hora_salida
            
        FROM tarifa t
        INNER JOIN ruta_horario rh ON t.ruta_horario_id = rh.id
        INNER JOIN ruta r ON rh.ruta_id = r.id
        INNER JOIN terminal tor ON r.origen_terminal_id = tor.id
        INNER JOIN terminal tde ON r.destino_terminal_id = tde.id
        INNER JOIN horario h ON rh.horario_id = h.id

        WHERE (
            ? IS NULL OR TRIM(?) = ''
            OR tor.nombre ILIKE '%' || ? || '%'
            OR tde.nombre ILIKE '%' || ? || '%'
        )
        ORDER BY t.id DESC
        LIMIT ? OFFSET ?
    """;

    private static final String SQL_COUNT = """
        SELECT COUNT(*)
        FROM tarifa t
        INNER JOIN ruta_horario rh ON t.ruta_horario_id = rh.id
        INNER JOIN ruta r ON rh.ruta_id = r.id
        INNER JOIN terminal tor ON r.origen_terminal_id = tor.id
        INNER JOIN terminal tde ON r.destino_terminal_id = tde.id
        INNER JOIN horario h ON rh.horario_id = h.id

        WHERE (
            ? IS NULL OR TRIM(?) = ''
            OR tor.nombre ILIKE '%' || ? || '%'
            OR tde.nombre ILIKE '%' || ? || '%'
        )
    """;

    private static final String SQL_FIND_BY_ID =
        "SELECT * FROM tarifa WHERE id = ?";

    private static final String SQL_INSERT =
        "INSERT INTO tarifa (ruta_horario_id, precio_base, estado) VALUES (?, ?, true)";

    private static final String SQL_UPDATE =
        "UPDATE tarifa SET ruta_horario_id=?, precio_base=? WHERE id=?";

    private static final String SQL_DESACTIVAR =
        "UPDATE tarifa SET estado=false WHERE id=?";

    private static final String SQL_REACTIVAR =
        "UPDATE tarifa SET estado=true WHERE id=?";


    public List<TarifaView> listar(String filtro, int limit, int offset) {

        List<TarifaView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            
            ps.setInt(5, limit);
            ps.setInt(6, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    TarifaView v = new TarifaView();
                    v.setId(rs.getInt("id"));
                    v.setRutaHorarioId(rs.getInt("ruta_horario_id"));
                    v.setPrecio(rs.getBigDecimal("precio_base"));
                    v.setEstado(rs.getBoolean("estado"));

                    v.setOrigen(rs.getString("origen"));
                    v.setDestino(rs.getString("destino"));
                    v.setHoraSalida(rs.getString("hora_salida"));
                    lista.add(v);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listar tarifa: " + e.getMessage());
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
                if (rs.next()) return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("Error contar tarifa: " + e.getMessage());
        }

        return 0;
    }

    public Tarifa buscarPorId(Integer id) {

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Tarifa t = new Tarifa();
                    t.setId(rs.getInt("id"));
                    t.setRutaHorarioId(rs.getInt("ruta_horario_id"));
                    t.setPrecioBase(rs.getBigDecimal("precio_base"));
                    t.setEstado(rs.getBoolean("estado"));
                    return t;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error buscar tarifa: " + e.getMessage());
        }

        return null;
    }

    public ResultadoOperacion insertar(Tarifa t) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, t.getRutaHorarioId());
            ps.setBigDecimal(2, t.getPrecioBase());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Tarifa registrada correctamente.");

        } catch (PSQLException e) {

            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                    "Ya existe una tarifa registrada para esta ruta y horario."
                );
            }

            return ResultadoOperacion.error("Error BD: " + e.getMessage());

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
    

    public ResultadoOperacion actualizar(Tarifa t) {
        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, t.getRutaHorarioId());
            ps.setBigDecimal(2, t.getPrecioBase());
            ps.setInt(3, t.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Tarifa actualizada correctamente.");
            }

            return ResultadoOperacion.error("No se encontró la tarifa a actualizar.");

        } catch (PSQLException e) {

            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                    "Ya existe una tarifa registrada para esta ruta y horario."
                );
            }

            return ResultadoOperacion.error("Error BD: " + e.getMessage());

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(Integer id) {

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_DESACTIVAR)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Tarifa desactivada.");
            }
            return ResultadoOperacion.error("No se encontró la tarifa.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion reactivar(Integer id) {

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_REACTIVAR)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Tarifa activada.");
            }
            return ResultadoOperacion.error("No se encontró la tarifa.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
}