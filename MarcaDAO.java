/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Flota;
import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.Marca;

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
public class MarcaDAO {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM marca WHERE id = ?";

    public Marca buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Marca m = new Marca();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    return m;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en MarcaDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<Marca> listarPaginado(String filtro, int limit, int offset) {
        List<Marca> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM marca
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%')
            ORDER BY nombre
            LIMIT ? OFFSET ?
        """;

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setInt(4, limit);
            ps.setInt(5, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Marca m = new Marca();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado marca: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        String sql = """
            SELECT COUNT(*) FROM marca
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%')
        """;

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error contar marca: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(Marca m) {
        String sql = "INSERT INTO marca (nombre) VALUES (?)";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Marca registrada correctamente.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe una marca con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar marca: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Marca m) {
        String sql = "UPDATE marca SET nombre = ? WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Marca actualizada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la marca a actualizar.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe una marca con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(int id) {
        String sql = "DELETE FROM marca WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Marca eliminada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la marca.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                    "No se puede eliminar la marca porque tiene vehículos asociados."
                );
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
    public List<Marca> listarTodas() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT * FROM marca ORDER BY nombre ASC";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Marca m = new Marca();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                lista.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar marcas para combo: " + e.getMessage());
        }
        return lista;
    }
}