package com.transporte.gestion.modelo.Seguridad;

import java.util.Set;

public class Permisos {

    private int id;
    private int rolId;
    private Set<String> permisos;

    public Permisos(int id, int rolId, Set<String> permisos) {
        this.id = id;
        this.rolId = rolId;
        this.permisos = permisos;
    }

    public Permisos() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public void setPermisos(Set<String> permisos) {
        this.permisos = permisos;
    }

    public int getId() {
        return id;
    }

    public int getRolId() {
        return rolId;
    }

    public Set<String> getPermisos() {
        return permisos;
    }

}
