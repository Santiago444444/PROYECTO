
package com.transporte.gestion.controlador.Flota;
import com.transporte.gestion.dao.Flota.MarcaDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Flota.Marca;
import java.util.List;


public class MarcaController {

    private final MarcaDAO marcaDAO = new MarcaDAO();

    public List<Marca> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return marcaDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return marcaDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return marcaDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Marca m) {
        if (m == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }
        return marcaDAO.insertar(m);
    }

    public Marca buscarPorId(int id) {
        return marcaDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Marca m) {
        if (m == null) {
            return ResultadoOperacion.error("Datos inválidos.");
        }
        return marcaDAO.actualizar(m);
    }
    public List<Marca> listar() {
        return marcaDAO.listarTodas();
    }
}