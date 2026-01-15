/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Flota;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.Modelo;
import com.transporte.gestion.modelo.Flota.ModeloView;

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
public class ModeloDAO {

    private static final String SQL_LISTAR = """
        SELECT *
        FROM Modelo
        ORDER BY nombre
    """;
    private static final String SQL_BY_MARCA = "SELECT * "
            + "FROM modelo "
            + "WHERE marca_id = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT m.id, m.nombre, m.marca_id, ma.nombre AS marca_nombre "
            + "FROM modelo m "
            + " JOIN marca ma ON ma.id = m.marca_id "
            + "WHERE m.id = ?";

    private static final String SQL_LISTAR_POR_MARCA
            = "SELECT id, nombre, marca_id FROM modelo WHERE marca_id = ? ORDER BY nombre";

    public Modelo obtenerModeloDeMarca(int marcaId) {
        try (Connection conn = ConexionBD.getConnection(); PreparedStatement ps = conn.prepareStatement(SQL_BY_MARCA)) {

            ps.setInt(1, marcaId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                return new Modelo(id, nombre, marcaId);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ModeloView buscarPorId(int idModelo) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, idModelo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ModeloView m = new ModeloView();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setMarcaId(rs.getInt("marca_id"));
                    m.setMarcaNombre(rs.getString("marca_nombre"));

                    return m;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en ModeloDAO.buscarPorId: " + e.getMessage());
        }

        return null;
    }

    public List<ModeloView> listarPaginado(String filtro, int limit, int offset) {
        List<ModeloView> lista = new ArrayList<>();

        String sql
                = "SELECT m.id, m.nombre, m.marca_id, ma.nombre AS marca_nombre "
                + "FROM modelo m "
                + "JOIN marca ma ON ma.id = m.marca_id "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "OR m.nombre ILIKE '%' || ? || '%' "
                + "OR ma.nombre ILIKE '%' || ? || '%') "
                + "ORDER BY m.id DESC "
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
                    ModeloView m = new ModeloView();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setMarcaId(rs.getInt("marca_id"));
                    m.setMarcaNombre(rs.getString("marca_nombre"));

                    lista.add(m);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error listarPaginado modelo: " + e.getMessage());
        }

        return lista;
    }

    public int contar(String filtro) {
        String sql
                = "SELECT COUNT(*) "
                + "FROM modelo m "
                + "JOIN marca ma ON ma.id = m.marca_id "
                + "WHERE (? IS NULL OR TRIM(?) = '' "
                + "OR m.nombre ILIKE '%' || ? || '%' "
                + "OR ma.nombre ILIKE '%' || ? || '%')";

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
            System.out.println("Error contar modelo: " + e.getMessage());
        }

        return 0;
    }

    public ResultadoOperacion eliminar(Integer id) {
        String sql = "DELETE FROM modelo WHERE id = ?";

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Modelo eliminado correctamente.");
            }
            return ResultadoOperacion.error("No se encontró el modelo a eliminar.");

        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error(
                        "No se puede eliminar el modelo porque tiene registros relacionados."
                );
            }
            return ResultadoOperacion.error("Error de base de datos: " + e.getMessage());

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion insertar(Modelo m) {
        String sql = """
        INSERT INTO modelo 
        (nombre, marca_id)
        VALUES (?, ?)
        """;

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getMarcaId());

            ps.executeUpdate();
            return ResultadoOperacion.ok("Modelo registrado correctamente.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error al registrar modelo: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Modelo m) {
        String sql = "UPDATE modelo SET "
                + "nombre = ?, "
                + "marca_id = ? "
                + "WHERE id = ?";

        try (java.sql.Connection con = ConexionBD.getConnection(); java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getMarcaId());

            ps.setInt(3, m.getId());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Modelo actualizado correctamente.");
            }
            return ResultadoOperacion.error("No se pudo actualizar (registro no encontrado).");

        } catch (org.postgresql.util.PSQLException ex) {
            return ResultadoOperacion.error("Error BD: " + ex.getMessage());
        } catch (Exception e) {
            return ResultadoOperacion.error("Error: " + e.getMessage());
        }
    }

    public List<Modelo> listar() {
        List<Modelo> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LISTAR); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Modelo(rs.getInt("id"), rs.getString("nombre"), rs.getInt("marca_id")));
            }
        } catch (Exception e) {
            System.out.println("Error ModeloDAO.listar: " + e.getMessage());
        }

        return lista;
    }

    public List<Modelo> listarPorMarca(int marcaId) {
        List<Modelo> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LISTAR_POR_MARCA)) {

            ps.setInt(1, marcaId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Modelo m = new Modelo();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setMarcaId(rs.getInt("marca_id"));
                    lista.add(m);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error ModeloDAO.listar: " + e.getMessage());
        }
        return lista;
    }
}
