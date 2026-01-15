package com.transporte.gestion.dao.Personas;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Personas.Chofer;
import com.transporte.gestion.modelo.Personas.ChoferView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class ChoferDAO {

    private static final String SQL_FIND_BY_ID
            = "SELECT "
            + "c.id, c.persona_id, c.tipo_licencia, c.fecha_caducidad, c.estado, c.fecha_creacion, "
            + "p.identificacion, p.nombre AS nombres, p.apellido AS apellidos, p.mail, p.telefono "
            + "FROM chofer c "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE c.id = ?";

    private static final String SQL_LIST
            = "SELECT c.id, c.persona_id, c.tipo_licencia, c.fecha_caducidad, c.estado, c.fecha_creacion, "
            + "       p.identificacion, p.nombre AS nombres, p.apellido AS apellidos, p.mail, p.telefono "
            + "FROM chofer c "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE ( ? = FALSE OR c.estado = TRUE ) "
            + "  AND ( ? IS NULL OR TRIM(?) = '' "
            + "   OR c.tipo_licencia ILIKE '%' || ? || '%' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR p.nombre ILIKE '%' || ? || '%' "
            + "   OR p.apellido ILIKE '%' || ? || '%' ) "
            + "ORDER BY c.id DESC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + "FROM chofer c "
            + "JOIN persona p ON p.id = c.persona_id "
            + "WHERE (? IS NULL OR TRIM(?) = '' "
            + "   OR c.tipo_licencia ILIKE '%' || ? || '%' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR p.nombre ILIKE '%' || ? || '%' "
            + "   OR p.apellido ILIKE '%' || ? || '%' )";

    private static final String SQL_INSERT
            = "INSERT INTO chofer (persona_id, tipo_licencia, fecha_caducidad, estado, fecha_creacion) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE
            = "UPDATE chofer SET persona_id = ?, tipo_licencia = ?, fecha_caducidad = ? "
            + "WHERE id = ?";

    public ChoferView buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ChoferView c = new ChoferView();
                    c.setId(rs.getInt("id"));
                    c.setPersonaId(rs.getInt("persona_id"));

                    c.setTipoLicencia(rs.getString("tipo_licencia"));
                    c.setFechaCaducidad(rs.getDate("fecha_caducidad"));

                    c.setEstado(rs.getBoolean("estado"));
                    c.setFechaCreacion(rs.getDate("fecha_creacion"));

                    c.setIdentificacion(rs.getString("identificacion"));
                    c.setNombres(rs.getString("nombres"));
                    c.setApellidos(rs.getString("apellidos"));
                    c.setMail(rs.getString("mail"));
                    c.setTelefono(rs.getString("telefono"));
                    return c;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en ChoferDAO.buscarPorId: " + e.getMessage());
        }
        return null;
    }

    public List<ChoferView> listarPaginado(String filtro, int limit, int offset) {
        return listarPaginado(filtro, limit, offset, false);
    }

    public List<ChoferView> listarPaginado(String filtro, int limit, int offset, boolean soloActivos) {
        List<ChoferView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            ps.setBoolean(1, soloActivos);

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
                    ChoferView c = new ChoferView();
                    c.setId(rs.getInt("id"));
                    c.setPersonaId(rs.getInt("persona_id"));

                    c.setTipoLicencia(rs.getString("tipo_licencia"));
                    c.setFechaCaducidad(rs.getDate("fecha_caducidad"));

                    c.setEstado(rs.getBoolean("estado"));
                    c.setFechaCreacion(rs.getDate("fecha_creacion"));

                    c.setIdentificacion(rs.getString("identificacion"));
                    c.setNombres(rs.getString("nombres"));
                    c.setApellidos(rs.getString("apellidos"));
                    c.setMail(rs.getString("mail"));
                    c.setTelefono(rs.getString("telefono"));

                    lista.add(c);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado chofer: " + e.getMessage());
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

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error contar chofer: " + e.getMessage());
        }
        return 0;
    }

    public List<Chofer> listarTodas() {
        List<Chofer> lista = new ArrayList<>();
        String sql = "SELECT c.*, p.nombre, p.apellido FROM chofer c "
                + "JOIN persona p ON p.id = c.persona_id "
                + "WHERE c.estado = TRUE ORDER BY p.apellido ASC";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Chofer c = new Chofer();
                c.setId(rs.getInt("id"));
                c.setPersonaId(rs.getInt("persona_id"));
                c.setTipoLicencia(rs.getString("tipo_licencia"));
                c.setEstado(rs.getBoolean("estado"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error listarTodas chofer: " + e.getMessage());
        }
        return lista;
    }

    public ResultadoOperacion insertar(Chofer c) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {

            ps.setInt(1, c.getPersonaId());
            ps.setString(2, c.getTipoLicencia());

            if (c.getFechaCaducidad() != null) {
                ps.setDate(3, c.getFechaCaducidad());
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            ps.setBoolean(4, c.getEstado() != null && c.getEstado());
            ps.setDate(5, c.getFechaCreacion() != null ? c.getFechaCreacion() : new Date(System.currentTimeMillis()));

            ps.executeUpdate();
            return ResultadoOperacion.ok("Chofer registrado correctamente.");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Chofer c) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, c.getPersonaId());
            ps.setString(2, c.getTipoLicencia());

            if (c.getFechaCaducidad() != null) {
                ps.setDate(3, c.getFechaCaducidad());
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            ps.setInt(4, c.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Chofer actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (PSQLException e) {
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "UPDATE chofer SET estado = false WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Chofer desactivado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el chofer.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion reactivar(Integer id) {
        String sql = "UPDATE chofer SET estado = true WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Chofer activado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el chofer.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public Integer buscarIdPorPersona(int personaId) {
        String sql = "SELECT id FROM chofer WHERE persona_id = ? AND estado = true";
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, personaId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscarIdPorPersona: " + e.getMessage());
        }
        return null;
    }
    
    public int obtenerIdChoferPorPersona(int personaId) {
        String sql = "SELECT id FROM chofer WHERE persona_id = ?";
        try (java.sql.Connection con = com.transporte.gestion.global.ConexionBD.getConnection();
             java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, personaId);
            java.sql.ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar chofer: " + e.getMessage());
        }
        return 0;
    }
}
