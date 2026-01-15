package com.transporte.gestion.controlador.Seguridad;

import com.transporte.gestion.dao.Seguridad.RolDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Seguridad.Rol;
import java.util.List;

public class RolController {

    private final RolDAO rolDAO = new RolDAO();
    
    public List<Rol> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return rolDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return rolDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return rolDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Rol r) {
        if (r == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        return rolDAO.insertar(r);
    }

    public Rol buscarPorId(int id) {
        return rolDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Rol r) {
        if (r == null) {
            return ResultadoOperacion.error("Datos inválidos.");
        }
        return rolDAO.actualizar(r);
    }

    public List<Rol> listar() {
        return rolDAO.listar();
    }
}
