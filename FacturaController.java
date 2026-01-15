package com.transporte.gestion.controlador.Ventas;

import com.transporte.gestion.dao.Ventas.FacturaDAO;
import com.transporte.gestion.modelo.Ventas.FacturaView;
import java.util.List;

public class FacturaController {

    private final FacturaDAO facturaDAO = new FacturaDAO();

    public List<FacturaView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return facturaDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return facturaDAO.contar(filtro);
    }

    public FacturaView buscarPorId(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return facturaDAO.buscarPorId(id);
    }

}
