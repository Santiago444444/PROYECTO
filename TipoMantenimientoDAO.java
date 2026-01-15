/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Mantenimiento;
import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Mantenimiento.TipoMantenimiento;

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
public class TipoMantenimientoDAO {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM tipo_mantenimiento WHERE id = ?";

    public TipoMantenimiento buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TipoMantenimiento t = new TipoMantenimiento();
                    t.setId(rs.getInt("id"));
                    t.setNombre(rs.getString("nombre"));
                    t.setDescripcion(rs.getString("descripcion"));
                    return t;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en TipoMantenimientoDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<TipoMantenimiento> listarPaginado(String filtro, int limit, int offset) {
        List<TipoMantenimiento> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM tipo_mantenimiento
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%'
               OR descripcion ILIKE '%' || ? || '%')
            ORDER BY nombre
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
                    TipoMantenimiento t = new TipoMantenimiento();
                    t.setId(rs.getInt("id"));
                    t.setNombre(rs.getString("nombre"));
                    t.setDescripcion(rs.getString("descripcion"));
                    lista.add(t);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado tipoMantenimiento: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        String sql = """
            SELECT COUNT(*) FROM tipo_mantenimiento
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%'
               OR descripcion ILIKE '%' || ? || '%')
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
            System.out.println("Error contar tipoMantenimiento: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(TipoMantenimiento t) {
        String sql = "INSERT INTO tipo_mantenimiento (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDescripcion());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Tipo de mantenimiento registrado correctamente.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un tipo de mantenimiento con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(TipoMantenimiento t) {
        String sql = "UPDATE tipo_mantenimiento SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setString(2, t.getDescripcion());
            ps.setInt(3, t.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Tipo de mantenimiento actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el registro.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un tipo de mantenimiento con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(int id) {
        String sql = "DELETE FROM tipo_mantenimiento WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Tipo de mantenimiento eliminado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el registro.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                    "No se puede eliminar porque está asignado a mantenimientos existentes."
                );
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
}