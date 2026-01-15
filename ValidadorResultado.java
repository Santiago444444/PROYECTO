package com.transporte.gestion.global.modelos;

public class ValidadorResultado {

    private final boolean valid;
    private final String message;

    private ValidadorResultado(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public static ValidadorResultado ok() {
        return new ValidadorResultado(true, null);
    }

    public static ValidadorResultado error(String message) {
        return new ValidadorResultado(false, message);
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
