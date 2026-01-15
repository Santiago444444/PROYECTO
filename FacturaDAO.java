package com.transporte.gestion.dao.Ventas;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.modelo.Ventas.FacturaView;
import com.transporte.gestion.modelo.Ventas.Factura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    private static final String SQL_FIND_BY_ID
            = "SELECT f.id, f.cliente_id, f.fecha, f.iva, f.total, f.metodo_pago_id, "
            + "       mp.nombre AS metodo_pago_nombre, "
            + "       p.identificacion, p.nombre AS nombres, p.apellido AS apellidos, p.mail, p.telefono "
            + "FROM factura_cabecera f "
            + "JOIN cliente c ON c.id = f.cliente_id "
            + "JOIN persona p ON p.id = c.persona_id "
            + "JOIN metodos_pago mp ON mp.id = f.metodo_pago_id "
            + "WHERE f.id = ?";

    private static final String SQL_LIST
            = "SELECT f.id, f.cliente_id, f.fecha, f.iva, f.total, f.metodo_pago_id, "
            + "       mp.nombre AS metodo_pago_nombre, "
            + "       p.identificacion, p.nombre AS nombres, p.apellido AS apellidos, p.mail, p.telefono "
            + "FROM factura_cabecera f "
            + "JOIN cliente c ON c.id = f.cliente_id "
            + "JOIN persona p ON p.id = c.persona_id "
            + "JOIN metodos_pago mp ON mp.id = f.metodo_pago_id "
            + "WHERE ( ? IS NULL OR TRIM(?) = '' "
            + "   OR CAST(f.id AS TEXT) ILIKE '%' || ? || '%' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR (p.nombre || ' ' || p.apellido) ILIKE '%' || ? || '%' "
            + "   OR mp.nombre ILIKE '%' || ? || '%' ) "
            + "ORDER BY f.fecha DESC, f.id DESC "
            + "LIMIT ? OFFSET ?";

    private static final String SQL_COUNT
            = "SELECT COUNT(*) "
            + "FROM factura_cabecera f "
            + "JOIN cliente c ON c.id = f.cliente_id "
            + "JOIN persona p ON p.id = c.persona_id "
            + "JOIN metodos_pago mp ON mp.id = f.metodo_pago_id "
            + "WHERE ( ? IS NULL OR TRIM(?) = '' "
            + "   OR CAST(f.id AS TEXT) ILIKE '%' || ? || '%' "
            + "   OR p.identificacion ILIKE '%' || ? || '%' "
            + "   OR (p.nombre || ' ' || p.apellido) ILIKE '%' || ? || '%' "
            + "   OR mp.nombre ILIKE '%' || ? || '%' )";

    public FacturaView buscarPorId(int id) {
        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_ID)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapFacturaView(rs) : null;
            }

        } catch (SQLException e) {
            System.out.println("Error FacturaDAO.buscarPorId: " + e.getMessage());
            return null;
        }
    }

    public List<FacturaView> listarPaginado(String filtro, int limit, int offset) {
        List<FacturaView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST)) {

            int i = 1;
            // patrón igual que tus DAOs: repetir filtro n veces
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);
            ps.setString(i++, filtro);

            ps.setInt(i++, limit);
            ps.setInt(i++, offset);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapFacturaView(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error FacturaDAO.listarPaginado: " + e.getMessage());
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
                return rs.next() ? rs.getInt(1) : 0;
            }

        } catch (SQLException e) {
            System.out.println("Error FacturaDAO.contar: " + e.getMessage());
            return 0;
        }
    }

    private FacturaView mapFacturaView(ResultSet rs) throws SQLException {
        FacturaView f = new FacturaView();
        f.setId(rs.getInt("id"));
        f.setClienteId(rs.getInt("cliente_id"));
        f.setFecha(rs.getDate("fecha"));
        f.setIva(rs.getBigDecimal("iva"));
        f.setTotal(rs.getBigDecimal("total"));

        f.setMetodoPagoId(rs.getInt("metodo_pago_id"));
        f.setMetodoPagoNombre(rs.getString("metodo_pago_nombre"));

        f.setClienteIdentificacion(rs.getString("identificacion"));
        f.setClienteNombres(rs.getString("nombres"));
        f.setClienteApellidos(rs.getString("apellidos"));
        f.setClienteEmail(rs.getString("mail"));
        f.setClienteTelefono(rs.getString("telefono"));
        return f;
    }
}
