package com.transporte.gestion.dao.Reportes;

import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.enums.Periodicidad;
import com.transporte.gestion.global.enums.TipoReporte;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class ReportesDAO {

    public List<Map<String, Object>> consultar(TipoReporte tipo, Periodicidad per, Date desde, Date hasta) {
        switch (tipo) {
            case VENTAS_RUTA_TIEMPO:
                return ventasPorRutaTiempo(per, desde, hasta);
            case PREFERENCIA_HORARIO:
                return preferenciaHorario(per, desde, hasta);
            case TOP_RUTAS_POR_CLIENTE:
                return topRutasPorCliente(per, desde, hasta);
            default:
                return List.of();
        }
    }

    private String bucket(Periodicidad per) {
        // Para PostgreSQL: DATE_TRUNC
        switch (per) {
            case ANUAL:
                return "DATE_TRUNC('year', fc.fecha)";
            case MENSUAL:
                return "DATE_TRUNC('month', fc.fecha)";
            case DIARIA:
            default:
                return "DATE_TRUNC('day', fc.fecha)";
        }
    }

    private List<Map<String, Object>> ventasPorRutaTiempo(Periodicidad per, Date desde, Date hasta) {
        String t = bucket(per);

        String sql
                = "SELECT " + t + " AS periodo, "
                + "       r.id AS ruta_id, "
                + "       CONCAT(tor.nombre, ' -> ', tde.nombre) AS ruta, "
                + "       COUNT(fd.id) AS boletos_vendidos, "
                + "       COALESCE(SUM(fd.total), 0) AS total_ventas "
                + "FROM factura_cabecera fc "
                + "JOIN factura_detalle fd ON fd.factura_id = fc.id "
                + "JOIN boletos b ON b.id = fd.boleto_id "
                + "JOIN viaje v ON v.id = b.viaje_id "
                + "JOIN ruta_horario rh ON rh.id = v.ruta_horario_id "
                + "JOIN ruta r ON r.id = rh.ruta_id "           
                + "JOIN terminal tor ON tor.id = r.origen_terminal_id "
                + "JOIN terminal tde ON tde.id = r.destino_terminal_id "
                + "WHERE fc.fecha BETWEEN ? AND ? "
                + "GROUP BY periodo, r.id, ruta "
                + "ORDER BY periodo DESC, total_ventas DESC";

        return query(
                sql,
                new java.sql.Date(desde.getTime()),
                new java.sql.Date(hasta.getTime())
        );
    }

    private List<Map<String, Object>> preferenciaHorario(Periodicidad per, Date desde, Date hasta) {
        // “Preferencia” = hora_salida más vendida por ruta en el periodo.
        // Aquí lo dejamos como tabla de distribución por hora_salida para que sea “estadística” real.
        String t = bucket(per);

        String sql
                = "SELECT " + t + " AS periodo, "
                + "       CONCAT(tor.nombre, ' -> ', tde.nombre) AS ruta, "
                + "       h.hora_salida, "
                + "       COUNT(fd.id) AS boletos "
                + "FROM factura_cabecera fc "
                + "JOIN factura_detalle fd ON fd.factura_id = fc.id "
                + "JOIN boletos b ON b.id = fd.boleto_id "
                + "JOIN viaje v ON v.id = b.viaje_id "
                + "JOIN ruta_horario rh ON rh.id = v.ruta_horario_id "
                + "JOIN ruta r ON r.id = rh.ruta_id "
                + "JOIN horario h ON h.id = rh.horario_id "
                + "JOIN terminal tor ON tor.id = r.origen_terminal_id "
                + "JOIN terminal tde ON tde.id = r.destino_terminal_id "
                + "WHERE fc.fecha BETWEEN ? AND ? "
                + "GROUP BY periodo, ruta, h.hora_salida "
                + "ORDER BY periodo DESC, ruta, boletos DESC";

        return query(
                sql,
                new java.sql.Date(desde.getTime()),
                new java.sql.Date(hasta.getTime())
        );
    }

    private List<Map<String, Object>> topRutasPorCliente(Periodicidad per, Date desde, Date hasta) {
        String t = bucket(per);

        String sql
                = "SELECT " + t + " AS periodo, "
                + "       fc.cliente_id, "
                + "       CONCAT(p.apellido, ' ', p.nombre) AS cliente, "
                + "       CONCAT(tor.nombre, ' -> ', tde.nombre) AS ruta, "
                + "       COUNT(fd.id) AS compras, "
                + "       COALESCE(SUM(fd.total), 0) AS total "
                + "FROM factura_cabecera fc "
                + "JOIN cliente c ON c.id = fc.cliente_id "
                + "JOIN persona p ON p.id = c.persona_id "
                + "JOIN factura_detalle fd ON fd.factura_id = fc.id "
                + "JOIN boletos b ON b.id = fd.boleto_id "
                + "JOIN viaje v ON v.id = b.viaje_id "
                + "JOIN ruta_horario rh ON rh.id = v.ruta_horario_id "
                + "JOIN ruta r ON r.id = rh.ruta_id "
                + "JOIN terminal tor ON tor.id = r.origen_terminal_id "
                + "JOIN terminal tde ON tde.id = r.destino_terminal_id "
                + "WHERE fc.fecha BETWEEN ? AND ? "
                + "GROUP BY periodo, fc.cliente_id, cliente, ruta "
                + "ORDER BY periodo DESC, compras DESC, total DESC";

        return query(
                sql,
                new java.sql.Date(desde.getTime()),
                new java.sql.Date(hasta.getTime())
        );
    }

    private List<Map<String, Object>> query(String sql, Date desde, Date hasta) {
        List<Map<String, Object>> out = new ArrayList<>();

        try (Connection con = ConexionBD.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(desde.getTime()));
            ps.setDate(2, new java.sql.Date(hasta.getTime()));
            
            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int cols = md.getColumnCount();

                while (rs.next()) {
                    Map<String, Object> row = new LinkedHashMap<>();
                    for (int i = 1; i <= cols; i++) {
                        String col = md.getColumnLabel(i);
                        row.put(col, rs.getObject(i));
                    }
                    out.add(row);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error ReportesDAO.query: " + e.getMessage(), e);
        }

        return out;
    }
}
