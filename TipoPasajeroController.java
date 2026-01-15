/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Ventas;

import com.transporte.gestion.dao.Ventas.TipoPasajeroDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Ventas.TipoPasajero;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class TipoPasajeroController {

    private final TipoPasajeroDAO tipoPasajeroDAO = new TipoPasajeroDAO();

    public List<TipoPasajero> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return tipoPasajeroDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return tipoPasajeroDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return tipoPasajeroDAO.eliminar(id);
    }

    public ResultadoOperacion crear(TipoPasajero t) {
        if (t == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        return tipoPasajeroDAO.insertar(t);
    }

    public TipoPasajero buscarPorId(int id) {
        return tipoPasajeroDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(TipoPasajero t) {
        if (t == null) {
            return ResultadoOperacion.error("Datos inválidos.");
        }
        return tipoPasajeroDAO.actualizar(t);
    }

    public List<TipoPasajero> listar() {
        return tipoPasajeroDAO.listar();
    }
}
