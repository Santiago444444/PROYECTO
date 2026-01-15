/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Operaciones;
import com.transporte.gestion.dao.Operaciones.RutaDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Ruta;
import com.transporte.gestion.modelo.Operaciones.RutaView;
import java.util.List;

/**
 *
 * @author Lenovo ThinkPad
 */
public class RutaController {

    private final RutaDAO dao;

    public RutaController() {
        this.dao = new RutaDAO();
    }

    public List<RutaView> listar(String filtro, int limit, int page) {
        int offset = (page - 1) * limit; 
        if (filtro == null) {
            filtro = "";
        }
        return dao.listar(filtro, limit, offset);
    }

    public int contar(String filtro) {
        if (filtro == null) {
            filtro = "";
        }
        return dao.contar(filtro);
    }

    public Ruta buscarPorId(Integer id) {
        if (id == null) {
            return null;
        }
        return dao.buscarPorId(id);
    }

    public ResultadoOperacion crear(Ruta ruta) {
        if (ruta.getCooperativaId() == null) {
            return ResultadoOperacion.error("Debe seleccionar una cooperativa.");
        }
        if (ruta.getOrigenTerminalId() == null) {
            return ResultadoOperacion.error("Debe seleccionar el terminal de origen.");
        }
        if (ruta.getDestinoTerminalId() == null) {
            return ResultadoOperacion.error("Debe seleccionar el terminal de destino.");
        }
        
        // Validación lógica: Origen != Destino
        if (ruta.getOrigenTerminalId().equals(ruta.getDestinoTerminalId())) {
            return ResultadoOperacion.error("El origen y el destino no pueden ser el mismo.");
        }

        if (ruta.getDistancia() == null || ruta.getDistancia() <= 0) {
            return ResultadoOperacion.error("La distancia debe ser mayor a 0.");
        }
        if (ruta.getDuracion() == null || ruta.getDuracion() <= 0) {
            return ResultadoOperacion.error("La duración debe ser mayor a 0.");
        }

        if (ruta.getEstado() == null) {
            ruta.setEstado(true);
        }

        return dao.insertar(ruta);
    }

    public ResultadoOperacion actualizar(Ruta ruta) {
        if (ruta.getId() == null) {
             return ResultadoOperacion.error("El ID de la ruta es obligatorio.");
        }
        if (ruta.getCooperativaId() == null) {
            return ResultadoOperacion.error("Debe seleccionar una cooperativa.");
        }
        if (ruta.getOrigenTerminalId().equals(ruta.getDestinoTerminalId())) {
            return ResultadoOperacion.error("El origen y el destino no pueden ser el mismo.");
        }
        if (ruta.getDistancia() == null || ruta.getDistancia() <= 0) {
            return ResultadoOperacion.error("La distancia debe ser válida.");
        }

        return dao.actualizar(ruta);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return dao.eliminar(id);
    }

    public ResultadoOperacion reactivar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return dao.reactivar(id);
    }
    public List<Ruta> listar() {
        return dao.listar();
    }
    public List<RutaView> listarView() {
        return dao.listarView();
    }
}