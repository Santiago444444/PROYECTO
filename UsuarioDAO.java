package com.transporte.gestion.dao.Seguridad;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Seguridad.Usuario;
import com.transporte.gestion.modelo.Seguridad.UsuariosView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class UsuarioDAO {

    private static final String SQL_FIND
            = "SELECT id, usuario, contrasenia, rol_id, persona_id FROM usuarios WHERE usuario = ? AND estado = TRUE";

    private static final String SQL_FIND_BY_ID
            = "SELECT "
            + "u.id, "
            + "u.persona_id, "
            + "u.usuario, "
            + "u.rol_id, "
            + "r.nombre AS rol_nombre, "
            + "u.estado, "
            + "u.fecha_creacion, "
            + "p.identificacion, "
            + "p.nombre, "
            + "p.apellido, "
            + "p.mail "
            + "FROM usuarios u "
            + "JOIN rol r ON r.id = u.rol_id "
            + "JOIN persona p ON p.id = u.persona_id "
            + "WHERE u.id = ?";

    private static final String SQL_LIST
            = "SELECT u.id, u.persona_id, u.usuario, u.rol_id, r.nombre AS rol_nombre, "
            + "       u.estado, u.fecha_creacion, "
            + "       p.identificacion, p.nombre AS nombres, p.apellido AS apellidos, p.mail "
            + "FROM usuarios u "
            + "JOIN rol r ON r.id = u.rol_id "
            + "JOIN persona p ON p.id = u.persona_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR u.usuario ILIKE '%' || ? || '%' "
            + "   OR r.nombre ILIKE '%' || ? || '%' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR p.nombre ILIKE '%' || ? || '%' "
            + "   OR p.apellido ILIKE '%' || ? || '%' ) "
            + "ORDER BY u.id DESC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + "FROM usuarios u "
            + "JOIN rol r ON r.id = u.rol_id "
            + "JOIN persona p ON p.id = u.persona_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR u.usuario ILIKE '%' || ? || '%' "
            + "   OR r.nombre ILIKE '%' || ? || '%' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR p.nombre ILIKE '%' || ? || '%' "
            + "   OR p.apellido ILIKE '%' || ? || '%' )";

    private static final String SQL_INSERT
            = "INSERT INTO usuarios (persona_id, usuario, contrasenia, rol_id, estado, fecha_creacion) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE_SIN_PASS
            = "UPDATE usuarios SET persona_id = ?, usuario = ?, rol_id = ? "
            + "WHERE id = ?";

    private static final String SQL_UPDATE_CON_PASS
            = "UPDATE usuarios SET persona_id = ?, usuario = ?, contrasenia = ?, rol_id = ? "
            + "WHERE id = ?";

    public Usuario buscarPorUsuario(String nombreUsuario) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND)) {

            ps.setString(1, nombreUsuario.trim());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasenia(rs.getString("contrasenia"));
                u.setRolId(rs.getInt("rol_id"));
                u.setPersonaId(rs.getInt("persona_id"));
                return u;
            }
        } catch (Exception e) {
            System.out.println("Error DAO: " + e.getMessage());
        }

        return null;
    }

    public UsuariosView buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    UsuariosView u = new UsuariosView();
                    u.setId(rs.getInt("id"));
                    u.setPersonaId(rs.getInt("persona_id"));
                    u.setUsuario(rs.getString("usuario"));

                    u.setRolId(rs.getInt("rol_id"));
                    u.setRolNombre(rs.getString("rol_nombre"));

                    u.setEstado(rs.getBoolean("estado"));
                    u.setFechaCreacion(rs.getDate("fecha_creacion"));

                    u.setIdentificacion(rs.getString("identificacion"));
                    u.setNombres(rs.getString("nombre"));
                    u.setApellidos(rs.getString("apellido"));
                    u.setMail(rs.getString("mail"));
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en UsuariosDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<UsuariosView> listarPaginado(String filtro, int limit, int offset) {
        List<UsuariosView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            ps.setString(6, filtro);
            ps.setString(7, filtro);

            ps.setInt(8, limit);
            ps.setInt(9, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UsuariosView u = new UsuariosView();
                    u.setId(rs.getInt("id"));
                    u.setPersonaId(rs.getInt("persona_id"));
                    u.setUsuario(rs.getString("usuario"));
                    u.setRolId(rs.getInt("rol_id"));
                    u.setRolNombre(rs.getString("rol_nombre"));
                    u.setEstado(rs.getBoolean("estado"));
                    u.setFechaCreacion(rs.getDate("fecha_creacion"));
                    u.setIdentificacion(rs.getString("identificacion"));
                    u.setNombres(rs.getString("nombres"));
                    u.setApellidos(rs.getString("apellidos"));
                    u.setMail(rs.getString("mail"));
                    lista.add(u);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado usuarios: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_COUNT)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            ps.setString(6, filtro);
            ps.setString(7, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error contar usuarios: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(Usuario u) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, u.getPersonaId());
            ps.setString(2, u.getUsuario());
            ps.setString(3, u.getContrasenia());
            ps.setInt(4, u.getRolId());
            ps.setBoolean(5, u.getEstado() != null && u.getEstado());
            ps.setDate(6, u.getFechaCreacion() != null ? u.getFechaCreacion() : new Date(System.currentTimeMillis()));

            ps.executeUpdate();
            return ResultadoOperacion.ok("Usuario registrado correctamente.");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Usuario u, String passwordHash) {
        boolean cambiaPass = passwordHash != null && !passwordHash.trim().isEmpty();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(
                cambiaPass ? SQL_UPDATE_CON_PASS : SQL_UPDATE_SIN_PASS
        )) {

            int i = 1;
            ps.setInt(i++, u.getPersonaId());
            ps.setString(i++, u.getUsuario());

            if (cambiaPass) {
                ps.setString(i++, passwordHash);
            }

            ps.setInt(i++, u.getRolId());
            ps.setInt(i++, u.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Usuario actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "UPDATE usuarios SET estado = false WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Usuario desactivado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el usuario.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion reactivar(Integer id) {
        String sql = "UPDATE usuarios SET estado = true WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Usuario activado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el usuario.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

}
