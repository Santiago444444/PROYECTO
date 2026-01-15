package com.transporte.gestion.dao.Personas;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Personas.Cliente;
import com.transporte.gestion.modelo.Personas.ClienteView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class ClienteDAO {

    private static final String SQL_FIND_BY_ID
            = "SELECT "
            + "c.id, "
            + "c.persona_id, "
            + "c.fecha_creacion, "
            + "c.observaciones, "
            + "p.identificacion, "
            + "p.nombre, "
            + "p.apellido, "
            + "p.mail, "
            + "p.telefono "
            + "FROM cliente c "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE c.id = ?";
    
    private static final String SQL_FIND_BY_PERSONA_ID
            = "SELECT "
            + "c.id, "
            + "c.persona_id, "
            + "c.fecha_creacion, "
            + "c.observaciones, "
            + "p.identificacion, "
            + "p.nombre, "
            + "p.apellido, "
            + "p.mail, "
            + "p.telefono "
            + "FROM cliente c "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE c.persona_id = ?";
    
    private static final String SQL_FIND_BY_IDENTIFICACION
            = "SELECT "
            + "c.id, "
            + "c.persona_id, "
            + "c.fecha_creacion, "
            + "c.observaciones, "
            + "p.identificacion, "
            + "p.nombre, "
            + "p.apellido, "
            + "p.mail, "
            + "p.telefono "
            + "FROM cliente c "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE c.identificacion = ?";

    private static final String SQL_LIST
            = "SELECT c.id, c.persona_id, c.fecha_creacion, c.observaciones, "
            + "       p.identificacion, p.nombre AS nombres, p.apellido AS apellidos, p.mail, p.telefono "
            + "FROM cliente c "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR p.nombre ILIKE '%' || ? || '%' "
            + "   OR p.apellido ILIKE '%' || ? || '%' ) "
            + "ORDER BY c.id DESC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + "FROM cliente c "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR p.nombre ILIKE '%' || ? || '%' "
            + "   OR p.apellido ILIKE '%' || ? || '%' )";

    private static final String SQL_INSERT
            = "INSERT INTO cliente (persona_id, fecha_creacion, observaciones) "
            + "VALUES (?, ?, ?)";

    private static final String SQL_UPDATE
            = "UPDATE cliente SET persona_id = ?, observaciones = ? "
            + "WHERE id = ?";

    public ClienteView buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClienteView c = new ClienteView();
                    c.setId(rs.getInt("id"));
                    c.setPersonaId(rs.getInt("persona_id"));
                    c.setFechaCreacion(rs.getDate("fecha_creacion"));
                    c.setObservaciones(rs.getString("observaciones"));

                    c.setIdentificacion(rs.getString("identificacion"));
                    c.setNombres(rs.getString("nombre"));
                    c.setApellidos(rs.getString("apellido"));
                    c.setMail(rs.getString("mail"));
                    c.setTelefono(rs.getString("telefono"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en ClienteDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public ClienteView buscarPorIdentificacion(String id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_IDENTIFICACION)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClienteView c = new ClienteView();
                    c.setId(rs.getInt("id"));
                    c.setPersonaId(rs.getInt("persona_id"));
                    c.setFechaCreacion(rs.getDate("fecha_creacion"));
                    c.setObservaciones(rs.getString("observaciones"));

                    c.setIdentificacion(rs.getString("identificacion"));
                    c.setNombres(rs.getString("nombre"));
                    c.setApellidos(rs.getString("apellido"));
                    c.setMail(rs.getString("mail"));
                    c.setTelefono(rs.getString("telefono"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en ClienteDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public ClienteView buscarPorPersonaId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_PERSONA_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClienteView c = new ClienteView();
                    c.setId(rs.getInt("id"));
                    c.setPersonaId(rs.getInt("persona_id"));
                    c.setFechaCreacion(rs.getDate("fecha_creacion"));
                    c.setObservaciones(rs.getString("observaciones"));

                    c.setIdentificacion(rs.getString("identificacion"));
                    c.setNombres(rs.getString("nombre"));
                    c.setApellidos(rs.getString("apellido"));
                    c.setMail(rs.getString("mail"));
                    c.setTelefono(rs.getString("telefono"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en ClienteDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<ClienteView> listarPaginado(String filtro, int limit, int offset) {
        List<ClienteView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);

            ps.setInt(6, limit);
            ps.setInt(7, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ClienteView c = new ClienteView();
                    c.setId(rs.getInt("id"));
                    c.setPersonaId(rs.getInt("persona_id"));
                    c.setFechaCreacion(rs.getDate("fecha_creacion"));
                    c.setObservaciones(rs.getString("observaciones"));

                    c.setIdentificacion(rs.getString("identificacion"));
                    c.setNombres(rs.getString("nombres"));
                    c.setApellidos(rs.getString("apellidos"));
                    c.setMail(rs.getString("mail"));
                    c.setTelefono(rs.getString("telefono"));

                    lista.add(c);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado cliente: " + e.getMessage());
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

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error contar cliente: " + e.getMessage());
        }
        return 0;
    }

    public ResultadoOperacion insertar(Cliente c) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, c.getPersonaId());
            ps.setDate(2, c.getFechaCreacion() != null ? c.getFechaCreacion() : new Date(System.currentTimeMillis()));
            ps.setString(3, c.getObservaciones());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Cliente registrado correctamente.");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Cliente c) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, c.getPersonaId());
            ps.setString(2, c.getObservaciones());
            ps.setInt(3, c.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Cliente actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Cliente eliminado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el cliente.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error("No se puede eliminar el cliente porque tiene registros relacionados.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
}
