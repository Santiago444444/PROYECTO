package com.transporte.gestion.controlador.Ventas;

import com.transporte.gestion.dao.Ventas.BoletoDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Ventas.BoletoView;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class BoletoController {

    private final BoletoDAO boletoDAO = new BoletoDAO();

    public ResultadoOperacion<Integer> venderBoletos(
            int viajeId,
            List<Integer> boletoIds,
            int clienteId,
            Map<Integer, Integer> boletoIdToPasajeroId,
            Map<Integer, Integer> boletoIdToTipoPasajeroId,
            int metodoPagoId,
            Date fechaFactura
    ) {

        if (viajeId <= 0) {
            return ResultadoOperacion.error("Viaje inválido.");
        }
        if (clienteId <= 0) {
            return ResultadoOperacion.error("Cliente inválido.");
        }
        if (metodoPagoId <= 0) {
            return ResultadoOperacion.error("Método de pago inválido.");
        }
        if (fechaFactura == null) {
            return ResultadoOperacion.error("Fecha de factura inválida.");
        }
        if (boletoIds == null || boletoIds.isEmpty()) {
            return ResultadoOperacion.error("No hay boletos seleccionados.");
        }

        if (boletoIdToPasajeroId == null || boletoIdToPasajeroId.isEmpty()) {
            return ResultadoOperacion.error("No se asignaron pasajeros.");
        }
        if (boletoIdToTipoPasajeroId == null || boletoIdToTipoPasajeroId.isEmpty()) {
            return ResultadoOperacion.error("No se asignaron tipos de pasajero.");
        }

        for (Integer bid : boletoIds) {
            Integer pasId = boletoIdToPasajeroId.get(bid);
            Integer tpId = boletoIdToTipoPasajeroId.get(bid);
            if (pasId == null || pasId <= 0) {
                return ResultadoOperacion.error("Falta pasajero para boleto ID " + bid);
            }
            if (tpId == null || tpId <= 0) {
                return ResultadoOperacion.error("Falta tipo pasajero para boleto ID " + bid);
            }
        }

        return boletoDAO.venderConFactura(
                viajeId,
                boletoIds,
                clienteId,
                boletoIdToPasajeroId,
                boletoIdToTipoPasajeroId,
                metodoPagoId,
                fechaFactura
        );
    }

    public ResultadoOperacion anularSiDisponible(int boletoId) {
        return boletoDAO.anularSiDisponible(boletoId);
    }

    public ResultadoOperacion anularVendidoPorCliente(int boletoId) {
        return boletoDAO.anularVendidoPorCliente(boletoId);
    }

    public List<BoletoView> listarPorIds(int viajeId, List<Integer> boletoIds) {
        if (viajeId <= 0) {
            return new ArrayList<>();
        }
        if (boletoIds == null || boletoIds.isEmpty()) {
            return new ArrayList<>();
        }
        return boletoDAO.listarPorIds(viajeId, boletoIds);
    }
}
