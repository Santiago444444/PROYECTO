package com.transporte.gestion.controlador.Flota;

import com.transporte.gestion.dao.Flota.ChoferBusDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.ChoferBus;
import com.transporte.gestion.modelo.Flota.ChoferBusView;
import java.util.List;

public class ChoferBusController {

    private final ChoferBusDAO choferBusDAO = new ChoferBusDAO();

    public List<ChoferBusView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return choferBusDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return choferBusDAO.contar(filtro);
    }

    public ChoferBusView buscarPorId(int id) {
        if (id <= 0) {
            return null;
        }
        return choferBusDAO.buscarPorId(id);
    }

    public ResultadoOperacion crear(ChoferBus cb) {

        if (cb == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        if (cb.getBusId() == null || cb.getBusId().trim().isEmpty()) {
            return ResultadoOperacion.error("Debe seleccionar un bus.");
        }

        if (cb.getChoferId() == null || cb.getChoferId() <= 0) {
            return ResultadoOperacion.error("Debe seleccionar un chofer.");
        }

        return choferBusDAO.insertar(cb);
    }

    public ResultadoOperacion inactivar(Integer id) {

        if (id == null || id <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return choferBusDAO.inactivar(id);
    }

    public List<ChoferBus> listar() {
        return choferBusDAO.listar();
    }
    
      public List<ChoferBusView> listarView() {
        return choferBusDAO.listarView();
    }
      
      public List<ChoferBusView> listarActivos() {
        return choferBusDAO.listarActivos();
    }
      
      public List<ChoferBusView> listarActivosPorCooperativa(int cooperativaId) {
    return choferBusDAO.listarActivosPorCooperativa(cooperativaId);
}
}
