package com.transporte.gestion.controlador.Reportes;

import com.transporte.gestion.dao.Reportes.ReportesDAO;
import com.transporte.gestion.global.enums.Periodicidad;
import com.transporte.gestion.global.enums.TipoReporte;
import java.util.Date;

import java.util.List;
import java.util.Map;

public class ReportesController {

    private final ReportesDAO dao = new ReportesDAO();

    public List<Map<String, Object>> consultar(TipoReporte tipo, Periodicidad per, Date desde, Date hasta) {
        if (tipo == null) {
            throw new IllegalArgumentException("TipoReporte es requerido");
        }
        if (per == null) {
            throw new IllegalArgumentException("Periodicidad es requerida");
        }
        if (desde == null || hasta == null) {
            throw new IllegalArgumentException("Rango de fechas requerido");
        }
        return dao.consultar(tipo, per, desde, hasta);
    }
}
