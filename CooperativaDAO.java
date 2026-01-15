/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Infraestructura;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.Cooperativa;

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
public class CooperativaDAO {

    private static final String SQL_LISTAR = """
        SELECT *
        FROM Cooperativa
        ORDER BY nombre
    """;
    private static final String SQL_FIND_BY_ID = "SELECT * FROM cooperativa WHERE id = ?";

    public Cooperativa buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cooperativa c = new Cooperativa();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setRuc(rs.getString("ruc"));
                    c.setDireccion(rs.getString("direccion"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setMail(rs.getString("mail"));
                    c.setEstado(rs.getBoolean("estado"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en CooperativaDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<Cooperativa> listarPaginado(String filtro, int limit, int offset) {
        List<Cooperativa> lista = new ArrayList<>();

        // Patrón unificado: Busca por Nombre O por RUC
        String sql = """
            SELECT * FROM cooperativa
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%'
               OR ruc ILIKE '%' || ? || '%')
            ORDER BY id DESC
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
                    Cooperativa c = new Cooperativa();
                    c.setId(rs.getInt("id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setRuc(rs.getString("ruc"));
                    c.setDireccion(rs.getString("direccion"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setMail(rs.getString("mail"));
                    c.setEstado(rs.getBoolean("estado"));
                    lista.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado cooperativa: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        String sql = """
            SELECT COUNT(*) FROM cooperativa
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%'
               OR ruc ILIKE '%' || ? || '%')
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
            System.out.println("Error contar cooperativa: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(Cooperativa c) {
        String sql = """
            INSERT INTO cooperativa (nombre, ruc, direccion, telefono, mail, estado)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getRuc());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getMail());
            // Por defecto true si es null
            ps.setBoolean(6, c.getEstado() != null ? c.getEstado() : true);

            ps.executeUpdate();
            return ResultadoOperacion.ok("Cooperativa registrada correctamente.");

        } catch (PSQLException e) {
            // 23505 = unique_violation (ej. RUC repetido)
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe una cooperativa con ese RUC.");
            }
            return ResultadoOperacion.error("Error de base de datos: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar cooperativa: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Cooperativa c) {
        String sql = """
            UPDATE cooperativa
            SET nombre = ?, ruc = ?, direccion = ?, telefono = ?, mail = ?
            WHERE id = ?
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getRuc());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getMail());
            ps.setInt(6, c.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Cooperativa actualizada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la cooperativa a actualizar.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe una cooperativa con ese RUC.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "UPDATE cooperativa SET estado = false WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("cooperativa desactivado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la cooperativa.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion reactivar(Integer id) {
        String sql = "UPDATE cooperativa SET estado = true WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("cooperativa activado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la cooperativa.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public List<Cooperativa> listar() {
        List<Cooperativa> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LISTAR); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Cooperativa(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (Exception e) {
            System.out.println("Error RolDAO.listar: " + e.getMessage());
        }

        return lista;
    }
}
