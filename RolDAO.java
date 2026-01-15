package com.transporte.gestion.dao.Seguridad;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Seguridad.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class RolDAO {
    private static final String SQL_FIND_BY_ID = "SELECT * FROM rol WHERE id = ?";

    private static final String SQL_LISTAR = """
        SELECT *
        FROM rol
        ORDER BY nombre
    """;

    public List<Rol> listar() {
        List<Rol> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(SQL_LISTAR);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Rol(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion")));
            }
        } catch (Exception e) {
            System.out.println("Error RolDAO.listar: " + e.getMessage());
        }

        return lista;
    }
    public Rol buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Rol r = new Rol();
                    r.setId(rs.getInt("id"));
                    r.setNombre(rs.getString("nombre"));
                    r.setDescripcion(rs.getString("descripcion"));
                    return r;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en RolDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<Rol> listarPaginado(String filtro, int limit, int offset) {
        List<Rol> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM rol
            WHERE (? IS NULL OR TRIM(?) = ''
               OR nombre ILIKE '%' || ? || '%'
               OR descripcion ILIKE '%' || ? || '%')
            ORDER BY id ASC
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
                    Rol r = new Rol();
                    r.setId(rs.getInt("id"));
                    r.setNombre(rs.getString("nombre"));
                    r.setDescripcion(rs.getString("descripcion"));
                    lista.add(r);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listarPaginado rol: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        String sql = """
            SELECT COUNT(*) FROM rol
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
            System.out.println("Error contar rol: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(Rol r) {
        String sql = "INSERT INTO rol (nombre, descripcion) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getNombre());
            ps.setString(2, r.getDescripcion());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Rol creado correctamente.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un rol con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al crear el rol: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Rol r) {
        String sql = "UPDATE rol SET nombre = ?, descripcion = ? WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, r.getNombre());
            ps.setString(2, r.getDescripcion());
            ps.setInt(3, r.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Rol actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el rol.");

        } catch (PSQLException e) {
            if ("23505".equals(e.getSQLState())) {
                return ResultadoOperacion.error("Ya existe un rol con ese nombre.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(int id) {
        String sql = "DELETE FROM rol WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Rol eliminado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el rol.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                    "No se puede eliminar el rol porque está asignado a usuarios."
                );
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
}
