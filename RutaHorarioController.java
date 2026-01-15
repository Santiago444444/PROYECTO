/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Operaciones;

import com.transporte.gestion.dao.Operaciones.RutaHorarioDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.RutaHorario;
import com.transporte.gestion.modelo.Operaciones.RutaHorarioView;
import java.util.List;
import com.transporte.gestion.global.utils.ValidadorDatos;
import com.transporte.gestion.global.modelos.ValidadorResultado;

/**
 *
 * @author Lenovo ThinkPad
 */
public class RutaHorarioController {

    private final RutaHorarioDAO dao = new RutaHorarioDAO();

    public List<RutaHorarioView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return dao.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return dao.contar(filtro);
    }
    
    public RutaHorarioView buscarPorId(int rutaId, int horarioId) {
        return dao.buscarPorId(rutaId, horarioId);
    }

    public ResultadoOperacion crear(RutaHorario rh) {

        if (rh == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        if (rh.getRutaId() == null || rh.getRutaId() <= 0) {
            return ResultadoOperacion.error("Debe seleccionar una ruta.");
        }

        if (rh.getHorarioId() == null || rh.getHorarioId() <= 0) {
            return ResultadoOperacion.error("Debe seleccionar un horario.");
        }
        return dao.insertar(rh);
    }

    public ResultadoOperacion desactivar(int rutaId, int horarioId) {

        if (rutaId <= 0 || horarioId <= 0) {
            return ResultadoOperacion.error("Datos inválidos.");
        }

        return dao.desactivar(rutaId, horarioId);
    }

    public ResultadoOperacion reactivar(int rutaId, int horarioId) {

        if (rutaId <= 0 || horarioId <= 0) {
            return ResultadoOperacion.error("Datos inválidos.");
        }

        return dao.reactivar(rutaId, horarioId);
    }

    public List<RutaHorario> listar() {
        return dao.listar();
    }

    public List<RutaHorarioView> listarView() {
        return dao.listarView();
    }
}
