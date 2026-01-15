/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Ventas;
import com.transporte.gestion.dao.Ventas.MetodoPagoDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Ventas.MetodoPago;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class MetodoPagoController {

    private final MetodoPagoDAO metodoPagoDAO = new MetodoPagoDAO();

    public List<MetodoPago> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return metodoPagoDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return metodoPagoDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return metodoPagoDAO.eliminar(id);
    }

    public ResultadoOperacion crear(MetodoPago m) {
        if (m == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        return metodoPagoDAO.insertar(m);
    }

    public MetodoPago buscarPorId(int id) {
        return metodoPagoDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(MetodoPago m) {
        if (m == null) {
            return ResultadoOperacion.error("Datos inválidos.");
        }
        return metodoPagoDAO.actualizar(m);
    }
    
    public List<MetodoPago> listar() {
        return metodoPagoDAO.listar();
    }
}