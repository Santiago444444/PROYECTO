package com.transporte.gestion.controlador.Personas;

import com.transporte.gestion.dao.Personas.ClienteDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Personas.ClienteView;
import com.transporte.gestion.modelo.Personas.Cliente;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class ClienteController {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public List<ClienteView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return clienteDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return clienteDAO.contar(filtro);
    }

    public ClienteView buscarPorId(int id) {
        return clienteDAO.buscarPorId(id);
    }

    public ResultadoOperacion crear(Cliente c) {
        if (c == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        if (c.getFechaCreacion() == null) {
            c.setFechaCreacion(Date.valueOf(LocalDate.now()));
        }
        return clienteDAO.insertar(c);
    }

    public ResultadoOperacion actualizar(Cliente c) {
        if (c == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        if (c.getId() <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return clienteDAO.actualizar(c);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return clienteDAO.eliminar(id);
    }

    public ClienteView buscarPorIdentificacion(String id) {
        return clienteDAO.buscarPorIdentificacion(id);
    }

    public ClienteView buscarPorPersonaId(int id) {
        return clienteDAO.buscarPorPersonaId(id);
    }

}
