/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Infraestructura;
import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.Provincia;

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
public class ProvinciaDAO {

    private static final String SQL_FIND_BY_ID = "SELECT * FROM provincia WHERE id = ?";

    public Provincia buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Provincia p = new Provincia();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    return p;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en ProvinciaDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<Provincia> listarPaginado(String filtro, int limit, int offset) {
        List<Provincia> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM provincia
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
                    Provincia p = new Provincia();
                    p.setId(rs.getInt("id"));
                    p.setNombre(rs.getString("nombre"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado provincia: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        String sql = """
            SELECT COUNT(*) FROM provincia
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
            System.out.println("Error contar provincia: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(Provincia p) {
        String sql = "INSERT INTO provincia (nombre) VALUES (?)";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Provincia registrada correctamente.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe una provincia con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar provincia: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Provincia p) {
        String sql = "UPDATE provincia SET nombre = ? WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setInt(2, p.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Provincia actualizada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la provincia a actualizar.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe una provincia con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(int id) {
        String sql = "DELETE FROM provincia WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Provincia eliminada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la provincia.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                    "No se puede eliminar la provincia porque tiene ciudades asociadas."
                );
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
    
    public List<Provincia> listarTodas() {
        List<Provincia> lista = new ArrayList<>();
        String sql = "SELECT * FROM provincia ORDER BY nombre ASC";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Provincia p = new Provincia();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar provincias para combo: " + e.getMessage());
        }
        return lista;
    }
}