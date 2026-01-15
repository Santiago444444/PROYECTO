package com.transporte.gestion.global.modelos;

public class ResultadoOperacion<T> {

    private final boolean ok;
    private final String mensaje;
    private final T data;

    private ResultadoOperacion(boolean ok, String mensaje, T data) {
        this.ok = ok;
        this.mensaje = mensaje;
        this.data = data;
    }

    public ResultadoOperacion(boolean ok, String mensaje) {
        this.ok = ok;
        this.mensaje = mensaje;
        this.data = null;
    }

    public static ResultadoOperacion ok(String mensaje) {
        return new ResultadoOperacion(true, mensaje, null);
    }

    public static ResultadoOperacion error(String mensaje) {
        return new ResultadoOperacion(false, mensaje, null);
    }

    public static <T> ResultadoOperacion<T> ok(String mensaje, T data) {
        return new ResultadoOperacion<>(true, mensaje, data);
    }

    public boolean isOk() {
        return ok;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getData() {
        return data;
    }
}
