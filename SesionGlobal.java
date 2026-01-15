package com.transporte.gestion.global.modelos;

import com.transporte.gestion.modelo.Seguridad.Usuario;
import java.util.Set;

public class SesionGlobal {

    private static Usuario usuarioActual;

    private static Set<String> permisosUsuario;

    private SesionGlobal() {
    }

    public static void iniciarSesion(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static boolean haySesionActiva() {
        return usuarioActual != null;
    }

    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public static int getUsuarioId() {
        return (usuarioActual != null) ? usuarioActual.getId() : 0;
    }

    public static int getPersonaId() {
        return (usuarioActual != null) ? usuarioActual.getPersonaId() : 0;
    }

    public static int getRolId() {
        return (usuarioActual != null) ? usuarioActual.getRolId() : 0;
    }

    public static String getNombreUsuario() {
        return (usuarioActual != null) ? usuarioActual.getUsuario() : null;
    }

    public static Boolean getEstadoUsuario() {
        return (usuarioActual != null) ? usuarioActual.getEstado() : null;
    }

    public static void setPermisosUsuario(Set<String> permisos) {
        permisosUsuario = permisos;
    }

    public static Set<String> getPermisosUsuario() {
        return permisosUsuario;
    }

    public static boolean tienePermiso(String permiso) {
        return permisosUsuario != null && permisosUsuario.contains(permiso);
    }
}
