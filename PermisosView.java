package com.transporte.gestion.modelo.Seguridad;

import java.util.Arrays;

public class PermisosView {

    private int id;
    private int rolId;
    private String rolNombre;
    private String[] permisos;

    public PermisosView() {
    }

    public PermisosView(int id, int rolId, String rolNombre, String[] permisos) {
        this.id = id;
        this.rolId = rolId;
        this.rolNombre = rolNombre;
        this.permisos = permisos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public String getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(String rolNombre) {
        this.rolNombre = rolNombre;
    }

    public String[] getPermisos() {
        return permisos;
    }

    public void setPermisos(String[] permisos) {
        this.permisos = permisos;
    }

    public String permisosComoTexto() {
        return permisos == null ? "" : Arrays.toString(permisos);
    }
}
