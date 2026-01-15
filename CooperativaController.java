package com.transporte.gestion.controlador.Infraestructura;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.transporte.gestion.dao.Infraestructura.CooperativaDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.Cooperativa;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class CooperativaController {

    private final CooperativaDAO cooperativaDAO = new CooperativaDAO();

    public List<Cooperativa> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return cooperativaDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return cooperativaDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return cooperativaDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Cooperativa c) {
        if (c == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        if (c.getEstado() == null) {
            c.setEstado(true);
        }

        return cooperativaDAO.insertar(c);
    }

    public Cooperativa buscarPorId(int id) {
        return cooperativaDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Cooperativa c) {
        if (c == null) {
            return ResultadoOperacion.error("Datos inválidos."); // Corregido el mensaje
        }
        return cooperativaDAO.actualizar(c);
    }

    public ResultadoOperacion reactivar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return cooperativaDAO.reactivar(id);
    }

    public List<Cooperativa> listar() {
        return cooperativaDAO.listar();
    }
}
