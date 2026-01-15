/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.dao.Operaciones;
import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Ruta;
import com.transporte.gestion.modelo.Operaciones.RutaView;
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
public class RutaDAO {

    // --- CONSTANTES SQL ---

    private static final String SQL_SELECT_PAGINADO = """
            SELECT r.id, r.cooperativa_id, r.origen_terminal_id, r.destino_terminal_id, 
                   r.distancia, r.duracion, r.estado,
                   c.nombre AS coop_nombre,
                   t_org.nombre AS origen_nombre,
                   t_dst.nombre AS destino_nombre
            FROM ruta r
            INNER JOIN cooperativa c ON r.cooperativa_id = c.id
            INNER JOIN terminal t_org ON r.origen_terminal_id = t_org.id
            INNER JOIN terminal t_dst ON r.destino_terminal_id = t_dst.id
            WHERE (? IS NULL OR TRIM(?) = '' 
               OR c.nombre ILIKE '%' || ? || '%' 
               OR t_org.nombre ILIKE '%' || ? || '%' 
               OR t_dst.nombre ILIKE '%' || ? || '%')
            ORDER BY r.id DESC 
            LIMIT ? OFFSET ?
            """;
    private static final String SQL_SELECT_PAGINADO1 = """
            SELECT r.id, r.cooperativa_id, r.origen_terminal_id, r.destino_terminal_id, 
                   r.distancia, r.duracion, r.estado,
                   c.nombre AS coop_nombre,
                   t_org.nombre AS origen_nombre,
                   t_dst.nombre AS destino_nombre
            FROM ruta r
            INNER JOIN cooperativa c ON r.cooperativa_id = c.id
            INNER JOIN terminal t_org ON r.origen_terminal_id = t_org.id
            INNER JOIN terminal t_dst ON r.destino_terminal_id = t_dst.id
            ORDER BY r.id DESC 
            """;

    private static final String SQL_COUNT = """
            SELECT COUNT(*) FROM ruta r
            INNER JOIN cooperativa c ON r.cooperativa_id = c.id
            INNER JOIN terminal t_org ON r.origen_terminal_id = t_org.id
            INNER JOIN terminal t_dst ON r.destino_terminal_id = t_dst.id
            WHERE (? IS NULL OR TRIM(?) = '' 
               OR c.nombre ILIKE '%' || ? || '%' 
               OR t_org.nombre ILIKE '%' || ? || '%' 
               OR t_dst.nombre ILIKE '%' || ? || '%')
            """;

    private static final String SQL_FIND_BY_ID = "SELECT * FROM ruta WHERE id = ?";

    private static final String SQL_INSERT = 
            "INSERT INTO ruta (cooperativa_id, origen_terminal_id, destino_terminal_id, distancia, duracion, estado) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = 
            "UPDATE ruta SET cooperativa_id=?, origen_terminal_id=?, destino_terminal_id=?, distancia=?, duracion=?, estado=? WHERE id=?";

    private static final String SQL_DESACTIVAR = "UPDATE ruta SET estado = false WHERE id = ?";
    
    private static final String SQL_REACTIVAR = "UPDATE ruta SET estado = true WHERE id = ?";

    
    public List<RutaView> listar(String filtro, int limit, int offset) {
        List<RutaView> lista = new ArrayList<>();
        
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_SELECT_PAGINADO)) {

            ps.setString(1, filtro);
            ps.setString(2, filtro);
            ps.setString(3, filtro);
            ps.setString(4, filtro);
            ps.setString(5, filtro);
            
            ps.setInt(6, limit);
            ps.setInt(7, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    RutaView r = new RutaView();
                    r.setId(rs.getInt("id"));
                    r.setCooperativaId(rs.getInt("cooperativa_id"));
                    r.setOrigenTerminalId(rs.getInt("origen_terminal_id"));
                    r.setDestinoTerminalId(rs.getInt("destino_terminal_id"));
                    r.setDistancia(rs.getInt("distancia"));
                    r.setDuracion(rs.getInt("duracion"));
                    r.setEstado(rs.getBoolean("estado"));

                    r.setNombreCooperativa(rs.getString("coop_nombre"));
                    r.setNombreOrigen(rs.getString("origen_nombre"));
                    r.setNombreDestino(rs.getString("destino_nombre"));
                    
                    lista.add(r);
                }
            }
            
        } catch (SQLException e) {
            System.out.println("Error listar ruta: " + e.getMessage());
        }
        return lista;
    }

    public int contar(String filtro) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_COUNT)) {
             
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
            System.out.println("Error contar ruta: " + e.getMessage());
        }
        return 0;
    }

    public Ruta buscarPorId(Integer id) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {
            
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Ruta r = new Ruta();
                    r.setId(rs.getInt("id"));
                    r.setCooperativaId(rs.getInt("cooperativa_id"));
                    r.setOrigenTerminalId(rs.getInt("origen_terminal_id"));
                    r.setDestinoTerminalId(rs.getInt("destino_terminal_id"));
                    r.setDistancia(rs.getInt("distancia"));
                    r.setDuracion(rs.getInt("duracion"));
                    r.setEstado(rs.getBoolean("estado"));
                    return r;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error buscarPorId ruta: " + e.getMessage());
        }
        return null;
    }

    public ResultadoOperacion insertar(Ruta ruta) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
            
            ps.setInt(1, ruta.getCooperativaId());
            ps.setInt(2, ruta.getOrigenTerminalId());
            ps.setInt(3, ruta.getDestinoTerminalId());
            ps.setInt(4, ruta.getDistancia());
            ps.setInt(5, ruta.getDuracion());
            ps.setBoolean(6, ruta.getEstado());
            
            ps.executeUpdate();
            return ResultadoOperacion.ok("Ruta registrada correctamente.");
            
        } catch (PSQLException e) {
            if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error("La ruta no es válida: Verifique que los terminales pertenezcan a la cooperativa.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion actualizar(Ruta ruta) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
            
            ps.setInt(1, ruta.getCooperativaId());
            ps.setInt(2, ruta.getOrigenTerminalId());
            ps.setInt(3, ruta.getDestinoTerminalId());
            ps.setInt(4, ruta.getDistancia());
            ps.setInt(5, ruta.getDuracion());
            ps.setBoolean(6, ruta.getEstado());
            ps.setInt(7, ruta.getId());
            
            int filas = ps.executeUpdate();
            if (filas > 0) {
                return ResultadoOperacion.ok("Ruta actualizada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la ruta a actualizar.");
            
        } catch (PSQLException e) {
             if ("23503".equals(e.getSQLState())) {
                return ResultadoOperacion.error("La ruta no es válida: Verifique que los terminales pertenezcan a la cooperativa.");
            }
            return ResultadoOperacion.error("Error BD: " + e.getMessage());
        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion eliminar(Integer id) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_DESACTIVAR)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Ruta desactivada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la ruta.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }

    public ResultadoOperacion reactivar(Integer id) {
        try (Connection con = ConexionBD.getConnection(); 
             PreparedStatement ps = con.prepareStatement(SQL_REACTIVAR)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                return ResultadoOperacion.ok("Ruta activada correctamente.");
            }
            return ResultadoOperacion.error("No se encontró la ruta.");

        } catch (SQLException e) {
            return ResultadoOperacion.error("Error SQL: " + e.getMessage());
        }
    }
    
    public List<Ruta> listar() {

        List<Ruta> lista = new ArrayList<>();

        String sql = "SELECT * FROM ruta ORDER BY id";

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ruta r = new Ruta();
                r.setId(rs.getInt("id"));
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error listar Ruta: " + e.getMessage());
        }

        return lista;
    }
    public List<RutaView> listarView() {

        List<RutaView> lista = new ArrayList<>();

        String sql = SQL_SELECT_PAGINADO1;

        try (Connection con = ConexionBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
                while (rs.next()) {
                    RutaView r = new RutaView();
                    r.setId(rs.getInt("id"));
                    r.setCooperativaId(rs.getInt("cooperativa_id"));
                    r.setOrigenTerminalId(rs.getInt("origen_terminal_id"));
                    r.setDestinoTerminalId(rs.getInt("destino_terminal_id"));
                    r.setDistancia(rs.getInt("distancia"));
                    r.setDuracion(rs.getInt("duracion"));
                    r.setEstado(rs.getBoolean("estado"));

                    r.setNombreCooperativa(rs.getString("coop_nombre"));
                    r.setNombreOrigen(rs.getString("origen_nombre"));
                    r.setNombreDestino(rs.getString("destino_nombre"));
                    
                    lista.add(r);
                }

        } catch (SQLException e) {
            System.out.println("Error listar Ruta: " + e.getMessage());
        }

        return lista;
    }
}