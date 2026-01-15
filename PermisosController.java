package com.transporte.gestion.controlador.Seguridad;

import com.transporte.gestion.dao.Seguridad.PermisosDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Seguridad.PermisosView;
import com.transporte.gestion.modelo.Seguridad.Permisos;
import java.util.List;

public class PermisosController {

    private final PermisosDAO permisosDAO = new PermisosDAO();

    public List<PermisosView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return permisosDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return permisosDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return permisosDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Permisos p) {
        if (p == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        return permisosDAO.insertar(p);
    }

    public PermisosView buscarPorId(int id) {
        return permisosDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Permisos p) {
        if (p == null || p.getId() <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return permisosDAO.actualizar(p);
    }
}
