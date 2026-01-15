/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Ventas;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Ventas.MetodoPago;

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
public class MetodoPagoDAO {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM metodos_pago WHERE id = ?";

    public MetodoPago buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MetodoPago m = new MetodoPago();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setDescripcion(rs.getString("descripcion"));
                    return m;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en MetodoPagoDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<MetodoPago> listarPaginado(String filtro, int limit, int offset) {
        List<MetodoPago> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM metodos_pago
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%'
               OR descripcion ILIKE '%' || ? || '%')
            ORDER BY nombre
            LIMIT ? OFFSET ?
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setInt(5, limit);
            ps.setInt(6, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MetodoPago m = new MetodoPago();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setDescripcion(rs.getString("descripcion"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado metodoPago: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        String sql = """
            SELECT COUNT(*) FROM metodos_pago
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%'
               OR descripcion ILIKE '%' || ? || '%')
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
            System.out.println("Error contar metodoPago: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(MetodoPago m) {
        String sql = "INSERT INTO metodos_pago (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Método de pago registrado correctamente.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un método de pago con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar método de pago: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(MetodoPago m) {
        String sql = "UPDATE metodos_pago SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setString(2, m.getDescripcion());
            ps.setInt(3, m.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Método de pago actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el método de pago.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un método de pago con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(int id) {
        String sql = "DELETE FROM metodos_pago WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Método de pago eliminado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el método de pago.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                        "No se puede eliminar porque existen pagos asociados."
                );
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public List<MetodoPago> listar() {
        List<MetodoPago> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM metodos_pago        
            ORDER BY nombre
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    MetodoPago m = new MetodoPago();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setDescripcion(rs.getString("descripcion"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado metodoPago: " + e.getMessage());
        }
        return lista;
    }
}
