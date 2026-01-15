package com.transporte.gestion.controlador.Personas;

import com.transporte.gestion.dao.Personas.PersonaDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.modelo.Personas.Persona;
import java.util.List;

public class PersonaController {

    private final PersonaDAO personaDAO = new PersonaDAO();

    public List<Persona> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return personaDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return personaDAO.contar(filtro);
    }

    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return personaDAO.eliminar(id);
    }

    public ResultadoOperacion crear(Persona p) {
        if (p == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        return personaDAO.insertar(p);
    }

    public Persona buscarPorId(int id) {
        return personaDAO.buscarPorId(id);
    }

    public ResultadoOperacion actualizar(Persona p) {
        if (p == null) {
            return ResultadoOperacion.error("ID inválido.");
        }

        return personaDAO.actualizar(p);
    }

    public Persona buscarPorIdentificacion(String id) {
        return personaDAO.buscarPorIdentificacion(id);
    }

}
