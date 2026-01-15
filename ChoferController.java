package com.transporte.gestion.controlador.Personas;

import com.transporte.gestion.dao.Personas.ChoferDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Personas.ChoferView;
import com.transporte.gestion.modelo.Personas.Chofer;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ChoferController {

    private final ChoferDAO choferDAO = new ChoferDAO();

    public List<ChoferView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return choferDAO.listarPaginado(filtro, pageSize, offset);
    }

    public List<ChoferView> listar(String filtro, int pageSize, int paginaActual, boolean soloActivos) {
        int offset = (paginaActual - 1) * pageSize;
        return choferDAO.listarPaginado(filtro, pageSize, offset, soloActivos);
    }

    public List<Chofer> listar() {
        return choferDAO.listarTodas();
    }

    public int contar(String filtro) {
        return choferDAO.contar(filtro);
    }

    public ChoferView buscarPorId(int id) {
        return choferDAO.buscarPorId(id);
    }

    public ResultadoOperacion crear(Chofer c) {
        if (c == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        if (c.getEstado() == null) {
            c.setEstado(true);
        }

        if (c.getFechaCreacion() == null) {
            c.setFechaCreacion(Date.valueOf(LocalDate.now()));
        }

        return choferDAO.insertar(c);
    }

    public ResultadoOperacion actualizar(Chofer c) {
        if (c == null || c.getId() <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return choferDAO.actualizar(c);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return choferDAO.eliminar(id);
    }

    public ResultadoOperacion reactivar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return choferDAO.reactivar(id);
    }
}
