package com.transporte.gestion.global.enums;

public enum ViajeEstado {
    PROGRAMADO("PROGRAMADO"),
    COMPLETADO("COMPLETADO"),
    CANCELADO("CANCELADO");

    private final String code;

    ViajeEstado(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ViajeEstado fromCode(String code) {
        if (code == null) {
            return null;
        }
        for (ViajeEstado e : values()) {
            if (e.code.equalsIgnoreCase(code.trim())) {
                return e;
            }
        }
        return null;
    }

    public static boolean esValido(String code) {
        return fromCode(code) != null;
    }
}
