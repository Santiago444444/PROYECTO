/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Mantenimiento;
import com.transporte.gestion.dao.Mantenimiento.TallerDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Mantenimiento.Taller;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class TallerController {

    private final TallerDAO tallerDAO = new TallerDAO();

    public List<Taller> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return tallerDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return tallerDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return tallerDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Taller t) {
        if (t == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        return tallerDAO.insertar(t);
    }

    public Taller buscarPorId(int id) {
        return tallerDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Taller t) {
        if (t == null) {
            return ResultadoOperacion.error("Datos inválidos.");
        }
        return tallerDAO.actualizar(t);
    }
}