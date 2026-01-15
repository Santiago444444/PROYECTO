package com.transporte.gestion.controlador.Flota;

import com.transporte.gestion.dao.Flota.BusDAO;
import com.transporte.gestion.global.enums.EstadoBusEnum;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.Bus;
import com.transporte.gestion.modelo.Flota.BusView;
import java.util.List;

public class BusController {

    private final BusDAO busDAO = new BusDAO();

    public List<BusView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return busDAO.listarPaginado(filtro, pageSize, offset);
    }

    public List<BusView> listar(String filtro, int pageSize, int paginaActual, String soloActivos) {
        int offset = (paginaActual - 1) * pageSize;
        return busDAO.listarPaginado(filtro, pageSize, offset, soloActivos);
    }

    public int contar(String filtro) {
        return busDAO.contar(filtro);
    }

    public BusView buscarPorPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            return null;
        }
        return busDAO.buscarPorPlaca(normalizarPlaca(placa));
    }

    public ResultadoOperacion crear(Bus b) {

        if (b.getEstado() == null || b.getEstado().trim().isEmpty()) {
            b.setEstado(EstadoBusEnum.ACTIVO.name());
        } else {
            b.setEstado(b.getEstado().trim().toUpperCase());
        }
        b.setPlaca(normalizarPlaca(b.getPlaca()));

        return busDAO.insertarConAsientos(b);
    }

    public ResultadoOperacion actualizar(Bus b) {

        b.setPlaca(normalizarPlaca(b.getPlaca()));
        if (b.getEstado() != null) {
            b.setEstado(b.getEstado().trim().toUpperCase());
        }

        return busDAO.actualizarConAsientos(b);
    }

    public ResultadoOperacion desactivar(String placa) {
        return cambiarEstado(placa, EstadoBusEnum.INACTIVO.name());
    }

    public ResultadoOperacion activar(String placa) {
        return cambiarEstado(placa, EstadoBusEnum.ACTIVO.name());
    }

    public ResultadoOperacion ponerMantenimiento(String placa) {
        return cambiarEstado(placa, EstadoBusEnum.MANTENIMIENTO.name());
    }

    public ResultadoOperacion cambiarEstado(String placa, String estado) {
        if (placa == null || placa.trim().isEmpty()) {
            return ResultadoOperacion.error("Placa inválida.");
        }
        if (!EstadoBusEnum.esValido(estado)) {
            return ResultadoOperacion.error("Estado inválido. Use: ACTIVO, MANTENIMIENTO o INACTIVO.");
        }

        String placaNorm = normalizarPlaca(placa);
        String estadoNorm = estado.trim().toUpperCase();

        return busDAO.cambiarEstado(placaNorm, estadoNorm);
    }

    private String normalizarPlaca(String placa) {
        return placa == null ? null : placa.trim().toUpperCase();
    }
}
