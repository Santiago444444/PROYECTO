package com.transporte.gestion.global.utils;

import com.transporte.gestion.global.enums.Periodicidad;
import com.transporte.gestion.global.enums.TipoReporte;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.Date;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfReportUtil;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;

public final class PdfPentahoService {

    private static final String RPT_FACTURA = "/reports/Factura.prpt";
    private static final String RPT_BOLETOS = "/reports/boleto.prpt";

    private static boolean booted = false;

    private PdfPentahoService() {

    }

    public static byte[] generarFactura(int facturaId) throws Exception {
        return generar(RPT_FACTURA, facturaId);
    }

    public static byte[] generarBoletos(int facturaId) throws Exception {
        return generar(RPT_BOLETOS, facturaId);
    }

    private static byte[] generar(String resourcePath, int facturaId) throws Exception {

        MasterReport report = cargarReporte(resourcePath);
        report.getParameterValues().put("P_FACTURA_ID", facturaId);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfReportUtil.createPDF(report, baos);
        return baos.toByteArray();
    }

    private static MasterReport cargarReporte(String resourcePath) throws Exception {

        bootOnce();

        ResourceManager manager = new ResourceManager();
        manager.registerDefaults();

        URL url = PdfPentahoService.class.getResource(resourcePath);
        if (url == null) {
            throw new RuntimeException("No se encontró el reporte: " + resourcePath);
        }

        Resource resource = manager.createDirectly(url, MasterReport.class);
        return (MasterReport) resource.getResource();
    }

    private static synchronized void bootOnce() {
        if (!booted) {
            ClassicEngineBoot.getInstance().start();
            booted = true;
        }
    }

    public static byte[] generarReporte(TipoReporte tipo, Periodicidad per, java.util.Date desde, java.util.Date hasta) throws Exception {
        if (tipo == null) {
            throw new IllegalArgumentException("TipoReporte requerido");
        }
        if (per == null) {
            throw new IllegalArgumentException("Periodicidad requerida");
        }
        if (desde == null || hasta == null) {
            throw new IllegalArgumentException("Rango de fechas requerido");
        }

        MasterReport report = cargarReporte(tipo.getPrptPath());

        report.getParameterValues().put("P_DESDE", new Date(desde.getTime()));
        report.getParameterValues().put("P_HASTA", new Date(hasta.getTime()));
        report.getParameterValues().put("P_PER", per.getSqlValue());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfReportUtil.createPDF(report, baos);
        return baos.toByteArray();
    }

}
