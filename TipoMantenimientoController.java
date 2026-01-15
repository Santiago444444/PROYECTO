/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Mantenimiento;
import com.transporte.gestion.dao.Mantenimiento.TipoMantenimientoDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Mantenimiento.TipoMantenimiento;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class TipoMantenimientoController {

    private final TipoMantenimientoDAO tipoMantenimientoDAO = new TipoMantenimientoDAO();

    public List<TipoMantenimiento> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return tipoMantenimientoDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return tipoMantenimientoDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return tipoMantenimientoDAO.eliminar(id);
    }

    public ResultadoOperacion crear(TipoMantenimiento t) {
        if (t == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        return tipoMantenimientoDAO.insertar(t);
    }

    public TipoMantenimiento buscarPorId(int id) {
        return tipoMantenimientoDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(TipoMantenimiento t) {
        if (t == null) {
            return ResultadoOperacion.error("Datos inválidos.");
        }
        return tipoMantenimientoDAO.actualizar(t);
    }
}