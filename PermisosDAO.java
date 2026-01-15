package com.transporte.gestion.dao.Seguridad;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Seguridad.PermisosView;
import com.transporte.gestion.modelo.Seguridad.Permisos;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.postgresql.util.PSQLException;

public class PermisosDAO {

    private static final String SQL_BY_ROL = "SELECT * "
            + "FROM permisos "
            + "WHERE rol_id = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT p.id, p.rol_id, r.nombre AS rol_nombre, p.permisos "
            + "FROM permisos p "
            + " JOIN rol r ON r.id = p.rol_id "
            + "WHERE p.id = ?";

    public Permisos obtenerPermisosDeRol(int rolId) {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_BY_ROL)) {

            ps.setInt(1, rolId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");

                Array array = rs.getArray("permisos");
                String[] valores = (String[]) array.getArray();

                Set<String> permisos = new HashSet<>();
                for (String p : valores) {
                    permisos.add(p);
                }

                return new Permisos(id, rolId, permisos);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public PermisosView buscarPorId(int idPermiso) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, idPermiso);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    PermisosView p = new PermisosView();
                    p.setId(rs.getInt("id"));
                    p.setRolId(rs.getInt("rol_id"));
                    p.setRolNombre(rs.getString("rol_nombre"));

                    Array arr = rs.getArray("permisos");
                    String[] permisos = (arr != null) ? (String[]) arr.getArray() : new String[0];
                    p.setPermisos(permisos);

                    return p;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en PermisosDao.buscarPorId: " + e.getMessage());
        }

        return null;
    }

    //identificacion, nombre, apellido
    public List<PermisosView> listarPaginado(String filtro, int limit, int offset) {
        List<PermisosView> lista = new ArrayList<>();

        String sql
                = "SELECT p.id, p.rol_id, r.nombre AS rol_nombre, p.permisos "
                + "FROM permisos p "
                + "JOIN rol r ON r.id = p.rol_id "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "OR r.nombre ILIKE '%' || ? || '%' ) "
                + "ORDER BY id DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setInt(4, limit);
            ps.setInt(5, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PermisosView p = new PermisosView();
                    p.setId(rs.getInt("id"));
                    p.setRolId(rs.getInt("rol_id"));
                    p.setRolNombre(rs.getString("rol_nombre"));
                    Array arr = rs.getArray("permisos");
                    String[] permisos = (arr != null) ? (String[]) arr.getArray() : new String[0];
                    p.setPermisos(permisos);
                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado permisos: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        String sql
                = "SELECT COUNT(*) "
                + "FROM permisos p "
                + "JOIN rol r ON r.id = p.rol_id "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + " OR r.nombre ILIKE '%' || ? || '%' )";

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
            System.out.println("Error contar permisos: " + e.getMessage());
        }

        return 0;
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "DELETE FROM permisos WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Permisos eliminada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la persona a eliminar.");

        } catch (PSQLException e) {
            // 23503 = foreign_key_violation (PostgreSQL)
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                        "No se puede eliminar la persona porque tiene registros relacionados."
                );
            }
            return ResultadoOperacion.error("Error de base de datos: " + e.getMessage());

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion insertar(Permisos p) {
        String sql = """
        INSERT INTO permisos 
        (rol_id, permisos)
        VALUES (?, ?)
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getRolId());
            Array sqlArray = con.createArrayOf(
                    "text",
                    p.getPermisos().toArray(new String[0])
            );

            ps.setArray(2, sqlArray);

            ps.executeUpdate();
            return ResultadoOperacion.ok("Permiso registrada correctamente.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar permisos: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Permisos p) {
        String sql = "UPDATE permisos SET "
                + "permisos = ? "
                + "WHERE id = ?";

        try (java.sql.Connection con = ConexionBD.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            
            Array sqlArray = con.createArrayOf(
                    "text",
                    p.getPermisos().toArray(new String[0])
            );

            ps.setArray(1, sqlArray);

            ps.setInt(2, p.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Permisos actualizada correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (org.postgresql.util.PSQLException ex) {
            return ResultadoOperacion.error("Error BD: " + ex.getMessage());
        } catch (Exception e) {
            return ResultadoOperacion.error("Error: " + e.getMessage());
        }
    }
}
