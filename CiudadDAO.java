/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Infraestructura;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.Ciudad;
import com.transporte.gestion.modelo.Infraestructura.CiudadView;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Lenovo ThinkPad
 */
public class CiudadDAO {

    private static final String SQL_BY_PROVINCIA = "SELECT * "
            + "FROM ciudad "
            + "WHERE provincia_id = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT c.id, c.nombre, c.provincia_id, p.nombre AS provincia_nombre "
            + "FROM ciudad c "
            + " JOIN provincia p ON p.id = c.provincia_id "
            + "WHERE c.id = ?";

    private static final String SQL_LISTAR_POR_PROVINCIA
            = "SELECT id, nombre, provincia_id FROM ciudad WHERE provincia_id = ? ORDER BY nombre";

    public Ciudad obtenerCiudadDeProvincia(int provinciaId) {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_BY_PROVINCIA)) {

            ps.setInt(1, provinciaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");

                return new Ciudad(id, nombre, provinciaId);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public CiudadView buscarPorId(int idCiudad) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, idCiudad);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CiudadView c = new CiudadView();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setProvinciaId(rs.getInt("provincia_id"));
                    c.setProvinciaNombre(rs.getString("provincia_nombre"));
                    return c;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en CiudadDAO.buscarPorId: " + e.getMessage());
        }

        return null;
    }

    public List<CiudadView> listarPaginado(String filtro, int limit, int offset) {
        List<CiudadView> lista = new ArrayList<>();

        String sql
                = "SELECT c.id, c.nombre, c.provincia_id, p.nombre AS provincia_nombre "
                + "FROM ciudad c "
                + "JOIN provincia p ON p.id = c.provincia_id "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "OR c.nombre ILIKE '%' || ? || '%' "
                + "OR p.nombre ILIKE '%' || ? || '%') "
                + "ORDER BY c.id DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setInt(5, limit);
            ps.setInt(6, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CiudadView c = new CiudadView();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setProvinciaId(rs.getInt("provincia_id"));
                    c.setProvinciaNombre(rs.getString("provincia_nombre"));
                    lista.add(c);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado ciudad: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        String sql
                = "SELECT COUNT(*) "
                + "FROM ciudad c "
                + "JOIN provincia p ON p.id = c.provincia_id "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "OR c.nombre ILIKE '%' || ? || '%' "
                + "OR p.nombre ILIKE '%' || ? || '%')";

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
            System.out.println("Error contar ciudad: " + e.getMessage());
        }

        return 0;
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "DELETE FROM ciudad WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Ciudad eliminada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la ciudad a eliminar.");

        } catch (PSQLException e) {
            // 23503 = foreign_key_violation
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                        "No se puede eliminar la ciudad porque tiene registros relacionados."
                );
            }
            return ResultadoOperacion.error("Error de base de datos: " + e.getMessage());

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion insertar(Ciudad c) {
        String sql = """
        INSERT INTO ciudad 
        (nombre, provincia_id)
        VALUES (?, ?)
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getProvinciaId());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Ciudad registrada correctamente.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar ciudad: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Ciudad c) {
        String sql = "UPDATE ciudad SET "
                + "nombre = ?, "
                + "provincia_id = ? "
                + "WHERE id = ?";

        try (java.sql.Connection con = ConexionBD.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getProvinciaId());
            ps.setInt(3, c.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Ciudad actualizada correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (org.postgresql.util.PSQLException ex) {
            return ResultadoOperacion.error("Error BD: " + ex.getMessage());
        } catch (Exception e) {
            return ResultadoOperacion.error("Error: " + e.getMessage());
        }
    }

    public java.util.List<Ciudad> listarTodas() {
        java.util.List<Ciudad> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM ciudad ORDER BY nombre ASC";

        try (java.sql.Connection con = ConexionBD.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(sql); java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ciudad c = new Ciudad();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                lista.add(c);
            }

        } catch (java.sql.SQLException e) {
            System.out.println("Error al listar ciudades para combo: " + e.getMessage());
        }
        return lista;
    }

    public List<Ciudad> listarPorProvincia(int provinciaId) {
        List<Ciudad> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LISTAR_POR_PROVINCIA)) {

            ps.setInt(1, provinciaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ciudad m = new Ciudad();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setProvinciaId(rs.getInt("provincia_id"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error CiudadDao.listar: " + e.getMessage());
        }
        return lista;
    }
}
