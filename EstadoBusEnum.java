package com.transporte.gestion.global.enums;

public enum EstadoBusEnum {
    ACTIVO,
    MANTENIMIENTO,
    INACTIVO;

    public static boolean esValido(String v) {
        if (v == null) {
            return false;
        }
        try {
            valueOf(v.trim().toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
