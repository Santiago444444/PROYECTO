/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Mantenimiento;
import com.transporte.gestion.dao.Flota.BusDAO;
import com.transporte.gestion.dao.Personas.ChoferDAO;
import com.transporte.gestion.dao.Mantenimiento.OrdenMantenimientoDAO;
import com.transporte.gestion.dao.Mantenimiento.TallerDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.modelos.SesionGlobal;
import com.transporte.gestion.modelo.Flota.Bus;
import com.transporte.gestion.modelo.Mantenimiento.DetalleMantenimiento;
import com.transporte.gestion.modelo.Mantenimiento.DetalleMantenimientoView;
import com.transporte.gestion.modelo.Mantenimiento.OrdenMantenimiento;
import com.transporte.gestion.modelo.Mantenimiento.OrdenMantenimientoView;
import com.transporte.gestion.modelo.Mantenimiento.Taller; 
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Lenovo ThinkPad
 */
public class OrdenMantenimientoController {

    private final OrdenMantenimientoDAO ordenDAO;
    private final BusDAO busDAO;
    private final ChoferDAO choferDAO;
    private final TallerDAO tallerDAO;

    public OrdenMantenimientoController() {
        this.ordenDAO = new OrdenMantenimientoDAO();
        this.busDAO = new BusDAO();
        this.choferDAO = new ChoferDAO();
        this.tallerDAO = new TallerDAO();
    }

    public List<Bus> obtenerBusesChoferLogueado() {
        if (!SesionGlobal.haySesionActiva()) {
            return new ArrayList<>();
        }
        
        int personaId = SesionGlobal.getPersonaId();
        int choferId = choferDAO.obtenerIdChoferPorPersona(personaId);
        
        if (choferId > 0) {
            return busDAO.listarBusesActivosPorChofer(choferId);
        }
        return new ArrayList<>();
    }

    public List<Taller> listarTalleres() {
        return tallerDAO.listarPaginado("", 100, 0);
    }

    public ResultadoOperacion iniciarMantenimiento(OrdenMantenimiento orden) {
        if (orden.getBusId() == null || orden.getBusId().isEmpty()) {
            return new ResultadoOperacion(false, "Debe seleccionar un bus.");
        }
        if (orden.getTallerId() <= 0) {
            return new ResultadoOperacion(false, "Debe seleccionar un taller.");
        }

        if (SesionGlobal.haySesionActiva()) {
            int personaId = SesionGlobal.getPersonaId();
            int choferId = choferDAO.obtenerIdChoferPorPersona(personaId);
            if (choferId > 0) {
                orden.setChoferReportaId(choferId);
            }
        }

        return ordenDAO.iniciarOrden(orden);
    }

    public List<OrdenMantenimientoView> listarPendientes() {
        return ordenDAO.listarPorEstado("ABIERTA");
    }

    public ResultadoOperacion cerrarMantenimiento(OrdenMantenimiento orden, List<DetalleMantenimiento> detalles) {
        if (orden == null || orden.getId() == 0) {
            return new ResultadoOperacion(false, "Orden no válida.");
        }

        if (detalles == null || detalles.isEmpty()) {
            return new ResultadoOperacion(false, "Debe ingresar al menos un detalle.");
        }

        for (DetalleMantenimiento det : detalles) {
            if (det.getCantidad() <= 0) {
                return new ResultadoOperacion(false, "La cantidad debe ser mayor a 0.");
            }
            if (det.getCostoUnitario() < 0) {
                return new ResultadoOperacion(false, "El costo no puede ser negativo.");
            }
        }

        return ordenDAO.cerrarOrden(orden, detalles);
    }

    public List<OrdenMantenimientoView> listarHistorialCompleto() {
        return ordenDAO.listarTodas();
    }
    
    public List<DetalleMantenimientoView> obtenerDetalles(int ordenId) {
        return ordenDAO.listarDetallesPorOrden(ordenId);
    }
    
    public List<com.transporte.gestion.modelo.Mantenimiento.TipoMantenimiento> listarTiposMantenimiento() {
        com.transporte.gestion.dao.Mantenimiento.TipoMantenimientoDAO dao = new com.transporte.gestion.dao.Mantenimiento.TipoMantenimientoDAO();
        return dao.listarPaginado("", 100, 0);
    }
}