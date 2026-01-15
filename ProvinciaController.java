/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Infraestructura;
import com.transporte.gestion.dao.Infraestructura.ProvinciaDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Infraestructura.Provincia;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class ProvinciaController {

    private final ProvinciaDAO provinciaDAO = new ProvinciaDAO();

    public List<Provincia> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return provinciaDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return provinciaDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return provinciaDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Provincia p) {
        if (p == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        return provinciaDAO.insertar(p);
    }

    public Provincia buscarPorId(int id) {
        return provinciaDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Provincia p) {
        if (p == null) {
            return ResultadoOperacion.error("Datos inválidos."); // Mensaje corregido
        }
        return provinciaDAO.actualizar(p);
    }
    public List<Provincia> listar() {
        return provinciaDAO.listarTodas();
    }
}