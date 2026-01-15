/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.controlador.Operaciones;
import com.transporte.gestion.dao.Operaciones.TarifaDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Operaciones.Tarifa;
import com.transporte.gestion.modelo.Operaciones.TarifaView;
import java.math.BigDecimal;
import java.util.List;


/**
 *
 * @author Lenovo ThinkPad
 */
public class TarifaController {

    private final TarifaDAO dao;

    public TarifaController() {
        this.dao = new TarifaDAO();
    }

    public List<TarifaView> listar(String filtro, int limit, int page) {
        int offset = (page - 1) * limit;
        if (filtro == null) filtro = "";
        return dao.listar(filtro, limit, offset);
    }

    public int contar(String filtro) {
        if (filtro == null) filtro = "";
        return dao.contar(filtro);
    }

    public Tarifa buscarPorId(Integer id) {
        if (id == null) return null;
        return dao.buscarPorId(id);
    }

    public ResultadoOperacion crear(Tarifa tarifa) {

        if (tarifa.getRutaHorarioId() == null) {
            return ResultadoOperacion.error("Debe seleccionar una ruta y horario.");
        }

        if (tarifa.getPrecioBase() == null ||
            tarifa.getPrecioBase().compareTo(BigDecimal.ZERO) <= 0) {
            return ResultadoOperacion.error("El precio debe ser mayor a 0.");
        }

        if (tarifa.getEstado() == null) {
            tarifa.setEstado(true);
        }

        return dao.insertar(tarifa);
    }

    public ResultadoOperacion actualizar(Tarifa tarifa) {

        if (tarifa.getId() == null) {
            return ResultadoOperacion.error("El ID de la tarifa es obligatorio.");
        }

        if (tarifa.getPrecioBase() == null ||
            tarifa.getPrecioBase().compareTo(BigDecimal.ZERO) <= 0) {
            return ResultadoOperacion.error("El precio debe ser válido.");
        }

        return dao.actualizar(tarifa);
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
}