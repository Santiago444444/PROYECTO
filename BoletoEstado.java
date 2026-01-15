package com.transporte.gestion.global.enums;

public enum BoletoEstado {
    DISPONIBLE("DISPONIBLE"),
    VENDIDO("VENDIDO"),
    ANULADO("ANULADO"),
    ANULADO_CLIENTE("ANULADO_CLIENTE");

    private final String code;

    BoletoEstado(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static BoletoEstado fromCode(String s) {
        if (s == null) {
            return null;
        }
        String x = s.trim().toUpperCase();
        for (BoletoEstado e : values()) {
            if (e.code.equalsIgnoreCase(x)) {
                return e;
            }
        }
        return null;
    }
}
