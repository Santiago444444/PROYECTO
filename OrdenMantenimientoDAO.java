package com.transporte.gestion.dao.Mantenimiento;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.transporte.gestion.global.ConexionBD;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Mantenimiento.DetalleMantenimiento;
import com.transporte.gestion.modelo.Mantenimiento.DetalleMantenimientoView;
import com.transporte.gestion.modelo.Mantenimiento.OrdenMantenimiento;
import com.transporte.gestion.modelo.Mantenimiento.OrdenMantenimientoView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.postgresql.util.PSQLException;

/**
 *
 * @author Lenovo ThinkPad
 */
public class OrdenMantenimientoDAO {

    private static final String INSERT_ORDEN_INICIO = "INSERT INTO orden_mantenimiento (bus_id, fecha_ingreso, kilometraje, estado, taller_id, observaciones, chofer_reporta_id) VALUES (?, CURRENT_DATE, ?, 'ABIERTA', ?, ?, ?)";
    private static final String UPDATE_BUS_MANTENIMIENTO = "UPDATE bus SET estado = 'MANTENIMIENTO' WHERE placa = ?";

    private static final String INSERT_DETALLE = "INSERT INTO detalle_mantenimiento (orden_id, tipo_mantenimiento_id, descripcion, costo_u, cantidad, total) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_ORDEN_CIERRE = "UPDATE orden_mantenimiento SET fecha_salida = CURRENT_DATE, estado = 'CERRADA', subtotal = ?, iva = ?, total = ? WHERE id = ?";
    private static final String UPDATE_BUS_ACTIVO = "UPDATE bus SET estado = 'ACTIVO' WHERE placa = ?";

    private static final String SELECT_ALL_VIEW = "SELECT o.id, b.placa, t.nombre as taller, o.fecha_ingreso, o.estado, o.total, p.nombre || ' ' || p.apellido as chofer FROM orden_mantenimiento o INNER JOIN bus b ON o.bus_id = b.placa INNER JOIN taller t ON o.taller_id = t.id LEFT JOIN chofer c ON o.chofer_reporta_id = c.id LEFT JOIN persona p ON c.persona_id = p.id ORDER BY o.fecha_ingreso DESC";
    private static final String SELECT_BY_ESTADO_VIEW = "SELECT o.id, b.placa, t.nombre as taller, o.fecha_ingreso, o.estado, o.total, p.nombre || ' ' || p.apellido as chofer FROM orden_mantenimiento o INNER JOIN bus b ON o.bus_id = b.placa INNER JOIN taller t ON o.taller_id = t.id LEFT JOIN chofer c ON o.chofer_reporta_id = c.id LEFT JOIN persona p ON c.persona_id = p.id WHERE o.estado = ? ORDER BY o.fecha_ingreso DESC";
    
    private static final String SELECT_DETALLES = "SELECT d.id, tm.nombre as tipo, d.descripcion, d.costo_u, d.cantidad, d.total FROM detalle_mantenimiento d INNER JOIN tipo_mantenimiento tm ON d.tipo_mantenimiento_id = tm.id WHERE d.orden_id = ?";

    public ResultadoOperacion iniciarOrden(OrdenMantenimiento orden) {
        Connection con = null;
        PreparedStatement psOrden = null;
        PreparedStatement psBus = null;
        ResultSet rs = null;

        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);

            psOrden = con.prepareStatement(INSERT_ORDEN_INICIO, Statement.RETURN_GENERATED_KEYS);
            psOrden.setString(1, orden.getBusId());
            psOrden.setInt(2, orden.getKilometraje());
            psOrden.setInt(3, orden.getTallerId());
            psOrden.setString(4, orden.getObservaciones());
            
            if (orden.getChoferReportaId() > 0) {
                psOrden.setInt(5, orden.getChoferReportaId());
            } else {
                psOrden.setNull(5, java.sql.Types.INTEGER);
            }

            int filas = psOrden.executeUpdate();
            if (filas == 0) {
                con.rollback();
                return new ResultadoOperacion(false, "No se pudo insertar la orden.");
            }

            int idGenerado = 0;
            rs = psOrden.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

            psBus = con.prepareStatement(UPDATE_BUS_MANTENIMIENTO);
            psBus.setString(1, orden.getBusId());
            psBus.executeUpdate();

            con.commit();
            // CORREGIDO: Concatenamos el ID en el mensaje porque el constructor solo acepta (boolean, String)
            return new ResultadoOperacion(true, "Orden de mantenimiento #" + idGenerado + " iniciada correctamente.");

        } catch (PSQLException e) { 
            try { if (con != null) con.rollback(); } catch (SQLException ex) {}
            System.out.println("Error Postgres al iniciar orden: " + e.getMessage());
            return new ResultadoOperacion(false, "Error de Base de Datos: " + e.getMessage());
            
        } catch (SQLException e) {
            try { if (con != null) con.rollback(); } catch (SQLException ex) {}
            System.out.println("Error SQL al iniciar orden: " + e.getMessage());
            return new ResultadoOperacion(false, "Error SQL Genérico: " + e.getMessage());
            
        } finally {
            try {
                if (rs != null) rs.close();
                if (psOrden != null) psOrden.close();
                if (psBus != null) psBus.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public ResultadoOperacion cerrarOrden(OrdenMantenimiento orden, List<DetalleMantenimiento> detalles) {
        Connection con = null;
        PreparedStatement psDetalle = null;
        PreparedStatement psOrden = null;
        PreparedStatement psBus = null;

        try {
            con = ConexionBD.getConnection();
            con.setAutoCommit(false);

            psDetalle = con.prepareStatement(INSERT_DETALLE);
            double subtotalAcumulado = 0;

            for (DetalleMantenimiento det : detalles) {
                psDetalle.setInt(1, orden.getId());
                psDetalle.setInt(2, det.getTipoMantenimientoId());
                psDetalle.setString(3, det.getDescripcion());
                psDetalle.setDouble(4, det.getCostoUnitario());
                psDetalle.setInt(5, det.getCantidad());
                
                double totalLinea = det.getCostoUnitario() * det.getCantidad();
                psDetalle.setDouble(6, totalLinea);
                psDetalle.addBatch();
                
                subtotalAcumulado += totalLinea;
            }
            psDetalle.executeBatch();

            double iva = subtotalAcumulado * 0.15; // Puedes ajustar el IVA aquí
            double total = subtotalAcumulado + iva;

            psOrden = con.prepareStatement(UPDATE_ORDEN_CIERRE);
            psOrden.setDouble(1, subtotalAcumulado);
            psOrden.setDouble(2, iva);
            psOrden.setDouble(3, total);
            psOrden.setInt(4, orden.getId());
            psOrden.executeUpdate();

            psBus = con.prepareStatement(UPDATE_BUS_ACTIVO);
            psBus.setString(1, orden.getBusId());
            psBus.executeUpdate();

            con.commit();
            return new ResultadoOperacion(true, "Mantenimiento finalizado exitosamente.");

        } catch (PSQLException e) {
            try { if (con != null) con.rollback(); } catch (SQLException ex) {}
            System.out.println("Error Postgres al cerrar orden: " + e.getMessage());
            return new ResultadoOperacion(false, "Error de Base de Datos: " + e.getMessage());

        } catch (SQLException e) {
            try { if (con != null) con.rollback(); } catch (SQLException ex) {}
            System.out.println("Error SQL al cerrar orden: " + e.getMessage());
            return new ResultadoOperacion(false, "Error SQL: " + e.getMessage());
            
        } finally {
            try {
                if (psDetalle != null) psDetalle.close();
                if (psOrden != null) psOrden.close();
                if (psBus != null) psBus.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }

    public List<OrdenMantenimientoView> listarTodas() {
        return ejecutarConsultaView(SELECT_ALL_VIEW, null);
    }

    public List<OrdenMantenimientoView> listarPorEstado(String estado) {
        return ejecutarConsultaView(SELECT_BY_ESTADO_VIEW, estado);
    }

    private List<OrdenMantenimientoView> ejecutarConsultaView(String sql, String parametro) {
        List<OrdenMantenimientoView> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConexionBD.getConnection();
            ps = con.prepareStatement(sql);
            if (parametro != null) {
                ps.setString(1, parametro);
            }
            rs = ps.executeQuery();
            while (rs.next()) {
                OrdenMantenimientoView v = new OrdenMantenimientoView();
                v.setId(rs.getInt("id"));
                v.setBusPlaca(rs.getString("placa"));
                v.setTallerNombre(rs.getString("taller"));
                v.setFechaIngreso(rs.getDate("fecha_ingreso"));
                v.setEstado(rs.getString("estado"));
                v.setChoferNombre(rs.getString("chofer"));
                v.setTotal(rs.getDouble("total"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar ordenes: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar recursos listar: " + e.getMessage());
            }
        }
        return lista;
    }

    public List<DetalleMantenimientoView> listarDetallesPorOrden(int ordenId) {
        List<DetalleMantenimientoView> lista = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = ConexionBD.getConnection();
            ps = con.prepareStatement(SELECT_DETALLES);
            ps.setInt(1, ordenId);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleMantenimientoView d = new DetalleMantenimientoView();
                d.setId(rs.getInt("id"));
                d.setTipoMantenimientoNombre(rs.getString("tipo"));
                d.setDescripcion(rs.getString("descripcion"));
                d.setCostoUnitario(rs.getDouble("costo_u"));
                d.setCantidad(rs.getInt("cantidad"));
                d.setTotal(rs.getDouble("total"));
                lista.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar detalles: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                System.out.println("Error cerrar recursos detalles: " + e.getMessage());
            }
        }
        return lista;
    }
}