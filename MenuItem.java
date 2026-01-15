package com.transporte.gestion.global.modelos;

public class MenuItem {

    private final String label;
    private final String accion; // null si es carpeta

    public MenuItem(String label, String accion) {
        this.label = label;
        this.accion = accion;
    }

    public String getLabel() {
        return label;
    }

    public String getAccion() {
        return accion;
    }

    public boolean esCarpeta() {
        return accion == null;
    }

    @Override
    public String toString() {
        return label;
    }
}
