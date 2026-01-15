/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Infraestructura;
import com.transporte.gestion.dao.Infraestructura.CopTermDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.CopTerm;
import com.transporte.gestion.modelo.Infraestructura.CopTermView;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class CopTermController {

    private final CopTermDAO dao = new CopTermDAO();

    public List<CopTermView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return dao.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return dao.contar(filtro);
    }

    public CopTermView buscarPorId(int coopId, int termId) {
        return dao.buscarPorId(coopId, termId);
    }

    public ResultadoOperacion crear(CopTerm ct) {
        if (ct == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        if (ct.getCooperativaId() <= 0) {
            return ResultadoOperacion.error("Debe seleccionar una cooperativa.");
        }
        if (ct.getTerminalId() <= 0) {
            return ResultadoOperacion.error("Debe seleccionar una terminal.");
        }

        return dao.insertar(ct);
    }

    public ResultadoOperacion desactivar(int coopId, int termId) {

        if (coopId <= 0 || termId <= 0) {
            return ResultadoOperacion.error("Datos inválidos.");
        }

        return dao.desactivar(coopId, termId);
    }
    
    public ResultadoOperacion reactivar(int coopId, int termId) {

        if (coopId <= 0 || termId <= 0) {
            return ResultadoOperacion.error("Datos inválidos.");
        }

        return dao.reactivar(coopId, termId);
    }
}