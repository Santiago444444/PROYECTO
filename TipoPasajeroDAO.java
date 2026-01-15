/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Ventas;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Ventas.TipoPasajero;

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
public class TipoPasajeroDAO {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM tipo_pasajero WHERE id = ?";

    public TipoPasajero buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TipoPasajero t = new TipoPasajero();
                    t.setId(rs.getInt("id"));
                    t.setNombre(rs.getString("nombre"));
                    t.setDescuento(rs.getBigDecimal("descuento"));
                    return t;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en TipoPasajeroDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<TipoPasajero> listarPaginado(String filtro, int limit, int offset) {
        List<TipoPasajero> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM tipo_pasajero
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%')
            ORDER BY nombre
            LIMIT ? OFFSET ?
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setInt(4, limit);
            ps.setInt(5, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TipoPasajero t = new TipoPasajero();
                    t.setId(rs.getInt("id"));
                    t.setNombre(rs.getString("nombre"));
                    t.setDescuento(rs.getBigDecimal("descuento"));
                    lista.add(t);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado tipoPasajero: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        String sql = """
            SELECT COUNT(*) FROM tipo_pasajero
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%')
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error contar tipoPasajero: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(TipoPasajero t) {
        String sql = "INSERT INTO tipo_pasajero (nombre, descuento) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setBigDecimal(2, t.getDescuento());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Tipo de pasajero registrado correctamente.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un tipo de pasajero con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(TipoPasajero t) {
        String sql = "UPDATE tipo_pasajero SET nombre = ?, descuento = ? WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setBigDecimal(2, t.getDescuento());
            ps.setInt(3, t.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Tipo de pasajero actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el registro.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un tipo de pasajero con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(int id) {
        String sql = "DELETE FROM tipo_pasajero WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Tipo de pasajero eliminado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el registro.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                        "No se puede eliminar porque existen pasajes asociados a este tipo de pasajero."
                );
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public List<TipoPasajero> listar() {
        List<TipoPasajero> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM tipo_pasajero
            ORDER BY nombre
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TipoPasajero t = new TipoPasajero();
                    t.setId(rs.getInt("id"));
                    t.setNombre(rs.getString("nombre"));
                    t.setDescuento(rs.getBigDecimal("descuento"));
                    lista.add(t);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado tipoPasajero: " + e.getMessage());
        }
        return lista;
    }
}
