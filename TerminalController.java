/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Infraestructura;
import com.transporte.gestion.dao.Infraestructura.TerminalDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.TerminalView;
import com.transporte.gestion.modelo.Infraestructura.Terminal;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class TerminalController {

    private final TerminalDAO terminalDAO = new TerminalDAO();
    private final TerminalDAO dao = new TerminalDAO();

    public List<TerminalView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return terminalDAO.listarPaginado(filtro, pageSize, offset);
    }

    public List<Terminal> listar() {
        return terminalDAO.listarTodas();
    }

    public int contar(String filtro) {
        return terminalDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return terminalDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Terminal t) {
        if (t == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        return terminalDAO.insertar(t);
    }

    public TerminalView buscarPorId(int id) {
        return terminalDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Terminal t) {
        if (t == null || t.getId() <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return terminalDAO.actualizar(t);
    }
    public List<Terminal> listarPorCooperativa(int id) {
        return dao.listarPorCooperativa(id);
    }
}
