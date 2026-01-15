package com.transporte.gestion.controlador.Operaciones;

import com.transporte.gestion.dao.Operaciones.ViajeDAO;
import com.transporte.gestion.global.enums.ViajeEstado;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Viaje;
import com.transporte.gestion.modelo.Operaciones.ViajeView;
import java.util.List;

public class ViajeController {

    private final ViajeDAO viajeDAO = new ViajeDAO();

    public List<ViajeView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return viajeDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return viajeDAO.contar(filtro);
    }

    public List<ViajeView> listarVendedor(String filtro, int pageSize, int paginaActual, java.sql.Date desde, java.sql.Date hasta) {
        int offset = (paginaActual - 1) * pageSize;
        return viajeDAO.listarPaginadoVendedor(
                filtro, pageSize, offset,
                desde, hasta
         
        );
    }

    public int contarVendedor(String filtro, java.sql.Date desde, java.sql.Date hasta) {
        return viajeDAO.contarVendedor(
                filtro,
                desde, hasta
                
        );
    }

    public List<ViajeView> listar(String filtro, int pageSize, int paginaActual, Integer personaId) {
        int offset = (paginaActual - 1) * pageSize;

        if (personaId == null || personaId <= 0) {
            return viajeDAO.listarPaginado(filtro, pageSize, offset);
        }
        return viajeDAO.listarPaginadoPorChofer(filtro, pageSize, offset, personaId);
    }

    public int contar(String filtro, Integer personaId) {
        if (personaId == null || personaId <= 0) {
            return viajeDAO.contar(filtro);
        }
        return viajeDAO.contarPorChofer(filtro, personaId);
    }

    public Viaje buscarPorId(Integer id) {
        if (id == null || id <= 0) {
            return null;
        }
        return viajeDAO.buscarPorId(id);
    }

    public ResultadoOperacion crear(Viaje v) {
        if (v == null) {
            return ResultadoOperacion.error("Datos inválidos.");
        }

        // Validaciones mínimas
        if (v.getRutaHorarioId() == null || v.getRutaHorarioId() <= 0) {
            return ResultadoOperacion.error("Seleccione una ruta/horario.");
        }
        if (v.getChoferBusId() == null || v.getChoferBusId() <= 0) {
            return ResultadoOperacion.error("Seleccione un bus/chofer.");
        }
        if (v.getFecha() == null) {
            return ResultadoOperacion.error("Seleccione la fecha del viaje.");
        }

        if (v.getEstado() == null || v.getEstado().trim().isEmpty()) {
            v.setEstado(ViajeEstado.PROGRAMADO.name());
        } else {
            v.setEstado(v.getEstado().trim().toUpperCase());
        }

        if (!ViajeEstado.esValido(v.getEstado())) {
            return ResultadoOperacion.error("Estado inválido. ");
        }

        return viajeDAO.insertarConBoletos(v);
    }

    public ResultadoOperacion actualizar(Viaje v) {
        if (v == null || v.getId() == null || v.getId() <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }

        if (v.getRutaHorarioId() == null || v.getRutaHorarioId() <= 0) {
            return ResultadoOperacion.error("Seleccione una ruta/horario.");
        }
        if (v.getChoferBusId() == null || v.getChoferBusId() <= 0) {
            return ResultadoOperacion.error("Seleccione un bus/chofer.");
        }
        if (v.getFecha() == null) {
            return ResultadoOperacion.error("Seleccione la fecha del viaje.");
        }

        if (v.getEstado() != null) {
            v.setEstado(v.getEstado().trim().toUpperCase());
        }

        if (!ViajeEstado.esValido(v.getEstado())) {
            return ResultadoOperacion.error("Estado inválido. ");
        }

        // si se cambia bus/fecha/ruta en un viaje ya vendido bloquear
        return viajeDAO.actualizar(v);
    }

    public ResultadoOperacion cancelar(Integer id) {
        return cambiarEstado(id, ViajeEstado.CANCELADO.name());
    }

    public ResultadoOperacion completar(Integer id) {
        return cambiarEstado(id, ViajeEstado.COMPLETADO.name());
    }

    public ResultadoOperacion reprogramar(Integer id) {
        return cambiarEstado(id, ViajeEstado.PROGRAMADO.name());
    }

    public ResultadoOperacion cambiarEstado(Integer id, String estado) {
        if (id == null || id <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }
        if (!ViajeEstado.esValido(estado)) {
            return ResultadoOperacion.error("Estado inválido.");
        }

        String estadoNorm = estado.trim().toUpperCase();
        return viajeDAO.cambiarEstado(id, estadoNorm);
    }

    public ResultadoOperacion anular(int viajeId) {
        if (viajeId <= 0) {
            return ResultadoOperacion.error("ID de viaje inválido.");
        }
        return viajeDAO.anularViajeConBoletos(viajeId);
    }
}
