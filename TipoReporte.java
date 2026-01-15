package com.transporte.gestion.global.enums;

public enum TipoReporte {

    VENTAS_RUTA_TIEMPO("/reports/RPT_VentasPorRuta.prpt", "ventas_ruta_tiempo"),
    PREFERENCIA_HORARIO("/reports/RPT_PreferenciaHora.prpt", "Preferencias por Horario"),
    TOP_RUTAS_POR_CLIENTE("/reports/RPT_VentasCliente.prpt", "Top Rutas por Cliente");

    private final String prptPath;
    private final String filePrefix;

    TipoReporte(String prptPath, String filePrefix) {
        this.prptPath = prptPath;
        this.filePrefix = filePrefix;
    }

    public String getPrptPath() {
        return prptPath;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    @Override
    public String toString() {
        switch (this) {
            case VENTAS_RUTA_TIEMPO:
                return "Ventas por Ruta (Tiempo)";
            default:
                return name();
        }
    }
}
