package com.transporte.gestion.dao.Ventas;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.modelo.Ventas.DetalleView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleDAO {

    private static final String SQL_LIST_BY_FACTURA
            = "SELECT fd.id, fd.factura_id, fd.boleto_id, "
            + "       b.codigo AS boleto_codigo, "
            + "       a.numero AS asiento_numero, "
            + "       fd.descripcion, fd.precio, fd.iva, fd.total "
            + "FROM factura_detalle fd "
            + "JOIN boletos b ON b.id = fd.boleto_id "
            + "JOIN asientos a ON a.id = b.asiento_id "
            + "WHERE fd.factura_id = ? "
            + "ORDER BY fd.id ASC";

    public List<DetalleView> listarPorFactura(int facturaId) {
        List<DetalleView> lista = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(SQL_LIST_BY_FACTURA)) {

            ps.setInt(1, facturaId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(map(rs));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error DetalleDAO.listarPorFactura: " + e.getMessage());
        }

        return lista;
    }

    private DetalleView map(ResultSet rs) throws SQLException {
        DetalleView d = new DetalleView();

        d.setId(rs.getInt("id"));
        d.setFacturaId(rs.getInt("factura_id"));

        int boletoId = rs.getInt("boleto_id");
        d.setBoletoId(rs.wasNull() ? null : boletoId);

        d.setBoletoCodigo(rs.getString("boleto_codigo"));

        int asientoNumero = rs.getInt("asiento_numero");
        d.setAsientoNumero(rs.wasNull() ? null : asientoNumero);

        d.setDescripcion(rs.getString("descripcion"));
        d.setPrecio(rs.getBigDecimal("precio"));
        d.setIva(rs.getBigDecimal("iva"));
        d.setTotal(rs.getBigDecimal("total"));

        return d;
    }
}
