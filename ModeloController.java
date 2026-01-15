/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Flota;
import com.transporte.gestion.dao.Flota.ModeloDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.ModeloView;
import com.transporte.gestion.modelo.Flota.Modelo;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class ModeloController {

    private final ModeloDAO modeloDAO = new ModeloDAO();

    public List<ModeloView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return modeloDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return modeloDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return modeloDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Modelo m) {
        if (m == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        return modeloDAO.insertar(m);
    }

    public ModeloView buscarPorId(int id) {
        return modeloDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Modelo m) {
        if (m == null || m.getId() <= 0) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return modeloDAO.actualizar(m);
    }
    
    public List<Modelo> listar() {
        return modeloDAO.listar();
    }
    
     public List<Modelo> listarPorMarca(int marcaId) {
        return modeloDAO.listarPorMarca(marcaId);
    }
}