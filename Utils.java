package com.transporte.gestion.global.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static String hashPassword(String plainPassword) {
        if (plainPassword == null) {
            throw new IllegalArgumentException("La contraseña no puede ser nula");
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(plainPassword.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algoritmo SHA-256 no disponible", e);
        }
    }

    public static String cargarString(String s) {
        return (s == null) ? "" : s;
    }

    public static String boolSiNo(Boolean b) {
        if (b == null) {
            return "";
        }
        return b ? "Sí" : "No";
    }

    public static String fmtFecha(java.util.Date d) {
        if (d == null) {
            return "";
        }
        return d.toString();
    }

    public static String fmtFechaArchivo(java.util.Date d) {
        if (d == null) {
            return "null";
        }
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(d);
    }
}
