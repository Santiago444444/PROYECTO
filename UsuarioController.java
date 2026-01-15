package com.transporte.gestion.controlador.Seguridad;

import com.transporte.gestion.dao.Seguridad.UsuarioDAO;
import com.transporte.gestion.global.modelos.ResultadoOperacion;
import com.transporte.gestion.global.utils.Utils; 
import com.transporte.gestion.modelo.Seguridad.UsuariosView;
import com.transporte.gestion.modelo.Seguridad.Usuario;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class UsuarioController {

    private final UsuarioDAO usuariosDAO = new UsuarioDAO();

    public List<UsuariosView> listar(String filtro, int pageSize, int paginaActual) {
        int offset = (paginaActual - 1) * pageSize;
        return usuariosDAO.listarPaginado(filtro, pageSize, offset);
    }

    public int contar(String filtro) {
        return usuariosDAO.contar(filtro);
    }

    public UsuariosView buscarPorId(int id) {
        return usuariosDAO.buscarPorId(id);
    }

    public Usuario buscarPorUsuario(String username) {
        if (username == null || username.trim().isEmpty()) {
            return null;
        }
        return usuariosDAO.buscarPorUsuario(username.trim());
    }

    public ResultadoOperacion crear(Usuario u, String passwordPlano) {
        if (u == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        if (u.getEstado() == null) {
            u.setEstado(true);
        }

        if (u.getFechaCreacion() == null) {
            u.setFechaCreacion(Date.valueOf(LocalDate.now()));
        }

        String hash = Utils.hashPassword(passwordPlano.trim());
        u.setContrasenia(hash);

        return usuariosDAO.insertar(u);
    }

    public ResultadoOperacion actualizar(Usuario u, boolean actualizarContrasenia, String nuevaPassword) {
        if (u == null) {
            return ResultadoOperacion.error("Datos nulos.");
        }

        if (actualizarContrasenia) {
            if (nuevaPassword == null || nuevaPassword.trim().isEmpty()) {
                return ResultadoOperacion.error("Debe ingresar la nueva contraseña.");
            }
            if (nuevaPassword.trim().length() < 4) {
                return ResultadoOperacion.error("La nueva contraseña debe tener al menos 4 caracteres.");
            }

            String hash = Utils.hashPassword(nuevaPassword.trim());
            u.setContrasenia(hash);

            return usuariosDAO.actualizar(u,hash);
        }

        return usuariosDAO.actualizar(u,null);
    }


    public ResultadoOperacion eliminar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return usuariosDAO.eliminar(id);
    }


    public ResultadoOperacion reactivar(Integer id) {
        if (id == null) {
            return ResultadoOperacion.error("ID inválido.");
        }
        return usuariosDAO.reactivar(id);
    }
}
