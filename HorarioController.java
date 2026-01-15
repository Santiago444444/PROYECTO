/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Operaciones;
import com.transporte.gestion.dao.Operaciones.HorarioDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Horario;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class HorarioController {

    private final HorarioDAO horarioDAO = new HorarioDAO();
    private final HorarioDAO dao = new HorarioDAO();

    public List<Horario> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return horarioDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return horarioDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return horarioDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Horario h) {
        if (h == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        return horarioDAO.insertar(h);
    }

    public Horario buscarPorId(int id) {
        return horarioDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Horario h) {
        if (h == null) {
            return ResultadoOperacion.error("Datos inválidos.");
        }
        return horarioDAO.actualizar(h);
    }
    public List<Horario> listarPorRuta(int rutaId) {
        return dao.listarPorRuta(rutaId);
    }
    public List<Horario> listar() {
        return dao.listar();
    }
}