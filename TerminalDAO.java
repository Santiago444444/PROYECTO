/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Infraestructura;
import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.TerminalView;
import com.transporte.gestion.modelo.Infraestructura.Terminal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Lenovo ThinkPad
 */
public class TerminalDAO {

    private static final String SQL_BY_CIUDAD = "SELECT * "
            + "FROM terminal "
            + "WHERE ciudad_id = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT t.id, t.nombre, t.ciudad_id, c.nombre AS ciudad_nombre, "
            + "t.direccion, t.telefono "
            + "FROM terminal t "
            + " JOIN ciudad c ON c.id = t.ciudad_id "
            + "WHERE t.id = ?";

    public Terminal obtenerTerminalDeCiudad(int ciudadId) {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_BY_CIUDAD)) {

            ps.setInt(1, ciudadId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                // ciudadId viene por parametro
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");

                return new Terminal(id, nombre, ciudadId, direccion, telefono);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public TerminalView buscarPorId(int idTerminal) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, idTerminal);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TerminalView t = new TerminalView();
                    t.setId(rs.getInt("id"));
                    t.setNombre(rs.getString("nombre"));
                    t.setCiudadId(rs.getInt("ciudad_id"));
                    t.setCiudadNombre(rs.getString("ciudad_nombre"));
                    t.setDireccion(rs.getString("direccion"));
                    t.setTelefono(rs.getString("telefono"));

                    return t;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en TerminalDAO.buscarPorId: " + e.getMessage());
        }

        return null;
    }

    public List<TerminalView> listarPaginado(String filtro, int limit, int offset) {
        List<TerminalView> lista = new ArrayList<>();

        String sql
                = "SELECT t.id, t.nombre, t.ciudad_id, c.nombre AS ciudad_nombre, "
                + "t.direccion, t.telefono "
                + "FROM terminal t "
                + "JOIN ciudad c ON c.id = t.ciudad_id "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "OR t.nombre ILIKE '%' || ? || '%' "
                + "OR c.nombre ILIKE '%' || ? || '%') "
                + "ORDER BY t.id DESC "
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
                    TerminalView t = new TerminalView();
                    t.setId(rs.getInt("id"));
                    t.setNombre(rs.getString("nombre"));
                    t.setCiudadId(rs.getInt("ciudad_id"));
                    t.setCiudadNombre(rs.getString("ciudad_nombre"));
                    t.setDireccion(rs.getString("direccion"));
                    t.setTelefono(rs.getString("telefono"));
                    
                    lista.add(t);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado terminal: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        String sql
                = "SELECT COUNT(*) "
                + "FROM terminal t "
                + "JOIN ciudad c ON c.id = t.ciudad_id "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "OR t.nombre ILIKE '%' || ? || '%' "
                + "OR c.nombre ILIKE '%' || ? || '%')";

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
            System.out.println("Error contar terminal: " + e.getMessage());
        }

        return 0;
    }

    public List<Terminal> listarTodas() {
        List<Terminal> lista = new ArrayList<>();
        String sql = "SELECT * FROM terminal ORDER BY nombre ASC";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Terminal t = new Terminal();
                t.setId(rs.getInt("id"));
                t.setNombre(rs.getString("nombre"));
                t.setCiudadId(rs.getInt("ciudad_id"));
                t.setDireccion(rs.getString("direccion"));
                t.setTelefono(rs.getString("telefono"));
                
                lista.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar terminales para combo: " + e.getMessage());
        }
        return lista;
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "DELETE FROM terminal WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Terminal eliminada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la terminal a eliminar.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                        "No se puede eliminar la terminal porque tiene registros relacionados."
                );
            }
            return ResultadoOperacion.error("Error de base de datos: " + e.getMessage());

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion insertar(Terminal t) {
        String sql = """
        INSERT INTO terminal 
        (nombre, ciudad_id, direccion, telefono)
        VALUES (?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getCiudadId());
            ps.setString(3, t.getDireccion());
            ps.setString(4, t.getTelefono());
            
            ps.executeUpdate();
            return ResultadoOperacion.ok("Terminal registrada correctamente.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar terminal: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Terminal t) {
        String sql = "UPDATE terminal SET "
                + "nombre = ?, "
                + "ciudad_id = ?, "
                + "direccion = ?, "
                + "telefono = ? "
                + "WHERE id = ?";

        try (java.sql.Connection con = ConexionBD.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getCiudadId());
            ps.setString(3, t.getDireccion());
            ps.setString(4, t.getTelefono());
            ps.setInt(5, t.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Terminal actualizada correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (org.postgresql.util.PSQLException ex) {
            return ResultadoOperacion.error("Error BD: " + ex.getMessage());
        } catch (Exception e) {
            return ResultadoOperacion.error("Error: " + e.getMessage());
        }
    }
    public List<Terminal> listarPorCooperativa(int cooperativaId) {
        List<Terminal> lista = new ArrayList<>();

        String sql = "SELECT t.* FROM terminal t " +
                     "INNER JOIN cop_term ct ON t.id = ct.terminal_id " +
                     "WHERE ct.cooperativa_id = ? " + 
                     "ORDER BY t.nombre"; 

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cooperativaId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Terminal t = new Terminal();
                    t.setId(rs.getInt("id"));
                    t.setNombre(rs.getString("nombre"));    
                    lista.add(t);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPorCooperativa: " + e.getMessage());
        }
        return lista;
    }
}