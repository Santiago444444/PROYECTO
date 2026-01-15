package com.transporte.gestion.global.enums;

public enum Periodicidad {
    DIARIA("day"),
    MENSUAL("month"),
    ANUAL("year");

    private final String sqlValue;

    Periodicidad(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public String getSqlValue() {
        return sqlValue;
    }

    @Override
    public String toString() {
        switch (this) {
            case DIARIA:
                return "Diaria";
            case MENSUAL:
                return "Mensual";
            case ANUAL:
                return "Anual";
            default:
                return name();
        }
    }
}
