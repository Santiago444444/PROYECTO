package com.transporte.gestion.dao.Personas;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Personas.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

public class PersonaDAO {

    private static final String SQL_FIND_BY_ID
            = "SELECT * "
            + "FROM persona WHERE id = ?";

        private static final String SQL_FIND_BY_IDENTIFICACION
            = "SELECT * "
            + "FROM persona WHERE identificacion = ?";
    
    public Persona buscarPorId(int idPersona) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, idPersona);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Persona p = new Persona();
                    p.setId(rs.getInt("id"));
                    p.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                    p.setIdentificacion(rs.getString("identificacion"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setDireccion(rs.getString("direccion"));
                    p.setMail(rs.getString("mail"));
                    p.setDiscapacidad(rs.getBoolean("discapacidad"));
                    p.setGenero(rs.getString("genero"));
                    return p;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en PersonaDAO.buscarPorId: " + e.getMessage());
        }

        return null;
    }

     public Persona buscarPorIdentificacion(String identificacion) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_IDENTIFICACION)) {

            ps.setString(1, identificacion);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Persona p = new Persona();
                    p.setId(rs.getInt("id"));
                    p.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                    p.setIdentificacion(rs.getString("identificacion"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setDireccion(rs.getString("direccion"));
                    p.setMail(rs.getString("mail"));
                    p.setDiscapacidad(rs.getBoolean("discapacidad"));
                    p.setGenero(rs.getString("genero"));
                    return p;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en PersonaDAO.buscarPorId: " + e.getMessage());
        }

        return null;
    }

    
    //identificacion, nombre, apellido
    public List<Persona> listarPaginado(String filtro, int limit, int offset) {
        List<Persona> lista = new ArrayList<>();

        String sql
                = "SELECT * "
                + "FROM persona "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "   OR identificacion ILIKE '%' || ? || '%' "
                + "   OR nombre ILIKE '%' || ? || '%' "
                + "   OR apellido ILIKE '%' || ? || '%' )"
                + "ORDER BY id DESC "
                + "LIMIT ? OFFSET ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            ps.setInt(6, limit);
            ps.setInt(7, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Persona p = new Persona();
                    p.setId(rs.getInt("id"));
                    p.setTipoIdentificacion(rs.getString("tipo_identificacion"));
                    p.setIdentificacion(rs.getString("identificacion"));
                    p.setNombre(rs.getString("nombre"));
                    p.setApellido(rs.getString("apellido"));
                    p.setTelefono(rs.getString("telefono"));
                    p.setMail(rs.getString("mail"));
                    p.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    p.setDireccion(rs.getString("direccion"));
                    p.setDiscapacidad(rs.getBoolean("discapacidad"));
                    p.setGenero(rs.getString("genero"));
                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado persona: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        String sql
                = "SELECT COUNT(*) "
                + "FROM persona "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "   OR identificacion ILIKE '%' || ? || '%' "
                + "   OR nombre ILIKE '%' || ? || '%' "
                + "   OR apellido ILIKE '%' || ? || '%' ) ";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
            System.out.println("Error contar persona: " + e.getMessage());
        }

        return 0;
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "DELETE FROM persona WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Persona eliminada correctamente.");
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

    public ResultadoOperacion insertar(Persona p) {
        String sql = """
        INSERT INTO persona
        (tipo_identificacion, identificacion, nombre, apellido, fecha_nacimiento,
         telefono, direccion, mail, discapacidad, genero)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getTipoIdentificacion());
            ps.setString(2, p.getIdentificacion());
            ps.setString(3, p.getNombre());
            ps.setString(4, p.getApellido());

            if (p.getFechaNacimiento() != null) {
                ps.setDate(5, new java.sql.Date(p.getFechaNacimiento().getTime()));
            }

            ps.setString(6, p.getTelefono());
            ps.setString(7, p.getDireccion());
            ps.setString(8, p.getMail());

            ps.setBoolean(9, p.getDiscapacidad() != null && p.getDiscapacidad());

            ps.setString(10, p.getGenero());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Persona registrada correctamente.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar persona: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Persona p) {
        String sql = "UPDATE persona SET "
                + "nombre = ?, apellido = ?, fecha_nacimiento = ?, telefono = ?, "
                + "direccion = ?, mail = ?, discapacidad = ?, genero = ? "
                + "WHERE id = ?";

        try (java.sql.Connection con = ConexionBD.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellido());

            if (p.getFechaNacimiento() != null) {
                ps.setDate(3, new java.sql.Date(p.getFechaNacimiento().getTime()));
            }
            
            ps.setString(4, p.getTelefono());
            ps.setString(5, p.getDireccion());
            ps.setString(6, p.getMail());

            // asumo boolean
            ps.setBoolean(7, p.getDiscapacidad() != null && p.getDiscapacidad());

            ps.setString(8, p.getGenero());
            ps.setInt(9, p.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Persona actualizada correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (org.postgresql.util.PSQLException ex) {
            return ResultadoOperacion.error("Error BD: " + ex.getMessage());
        } catch (Exception e) {
            return ResultadoOperacion.error("Error: " + e.getMessage());
        }
    }

}
