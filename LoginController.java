package com.transporte.gestion.controlador.sistema;

import com.transporte.gestion.dao.Seguridad.UsuarioDAO;
import com.transporte.gestion.global.utils.Utils;
import com.transporte.gestion.modelo.Seguridad.Usuario;
import com.transporte.gestion.global.modelos.SesionGlobal;
import com.transporte.gestion.modelo.Personas.Persona;
import com.transporte.gestion.dao.Personas.PersonaDAO;
import com.transporte.gestion.dao.Seguridad.PermisosDAO;
import com.transporte.gestion.modelo.Seguridad.Permisos;

public class LoginController {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final PersonaDAO personaDAO = new PersonaDAO();
    private PermisosDAO permisosDAO = new PermisosDAO();

    public boolean validarLogin(String usuario, String passwordPlano) {

        if (usuario == null || passwordPlano == null) {
            return false;
        }

        Usuario user = usuarioDAO.buscarPorUsuario(usuario);
        if (user == null) {
            return false;
        }
        String hashIngresado = Utils.hashPassword(passwordPlano.trim());
        boolean ok = hashIngresado.equals(user.getContrasenia());
        if (ok) {
            SesionGlobal.iniciarSesion(user);
            Permisos pr = permisosDAO.obtenerPermisosDeRol(user.getRolId());
            if (pr != null) {
                SesionGlobal.setPermisosUsuario(pr.getPermisos());
            }
        }

        return ok;
    }

    public Usuario obtenerUsuarioActual() {
        return SesionGlobal.getUsuarioActual();
    }

    public Persona obtenerPersonaDeUsuarioActual() {
        Usuario u = SesionGlobal.getUsuarioActual();
        if (u == null) {
            return null;
        }
        int personaId = u.getPersonaId();
        return personaDAO.buscarPorId(personaId);
    }
}
