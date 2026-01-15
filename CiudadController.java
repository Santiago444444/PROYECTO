/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Infraestructura;

import com.transporte.gestion.dao.Infraestructura.CiudadDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.CiudadView;
import com.transporte.gestion.modelo.Infraestructura.Ciudad;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class CiudadController {

    private final CiudadDAO ciudadDAO = new CiudadDAO();

    public List<CiudadView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return ciudadDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return ciudadDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return ciudadDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Ciudad c) {
        if (c == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        return ciudadDAO.insertar(c);
    }

    public CiudadView buscarPorId(int id) {
        return ciudadDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Ciudad c) {
        if (c == null || c.getId() <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return ciudadDAO.actualizar(c);
    }

    public java.util.List<Ciudad> listar() {
        return ciudadDAO.listarTodas();
    }

    public java.util.List<Ciudad> listarPorProvincia(int provinciaId) {
        return ciudadDAO.listarPorProvincia(provinciaId);
    }
}
