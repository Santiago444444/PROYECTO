package com.transporte.gestion.global.utils;

import com.transporte.gestion.global.modelos.ValidadorResultado;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class ValidadorDatos {

    private static final Scanner sc = new Scanner(System.in);

    public static String leerTextoSoloLetras(String mensaje) {
        String texto;
        while (true) {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            ValidadorResultado r = validarTextoSoloLetras(texto);
            if (r.isValid()) {
                return texto;
            }
            System.out.println(r.getMessage());
        }
    }

    public static String leerTexto(String mensaje) {
        String texto;
        while (true) {
            System.out.print(mensaje);
            texto = sc.nextLine().trim();
            ValidadorResultado r = validarTexto(texto);
            if (r.isValid()) {
                return texto;
            }
            System.out.println(r.getMessage());
        }
    }

    public static double leerDecimal(String mensaje) {
        String entrada;
        while (true) {
            System.out.print(mensaje);
            entrada = sc.nextLine().trim();
            ValidadorResultado r = validarDecimal(entrada);
            if (r.isValid()) {
                return Double.parseDouble(entrada);
            }
            System.out.println(r.getMessage());
        }
    }

    public static int leerEntero(String mensaje) {
        String entrada;
        while (true) {
            System.out.print(mensaje);
            entrada = sc.nextLine().trim();
            ValidadorResultado r = validarEntero(entrada);
            if (r.isValid()) {
                return Integer.parseInt(entrada);
            }
            System.out.println(r.getMessage());
        }
    }
    
    
    public static ValidadorResultado validarCedula(String cedula) {
        if (cedula == null || cedula.length() != 10) {
            return ValidadorResultado.error("La cédula debe tener 10 dígitos.");
        }
        try {
            int provincia = Integer.parseInt(cedula.substring(0, 2));
            boolean provinciaValida = (provincia >= 1 && provincia <= 24) || provincia == 30;
            if (!provinciaValida) {
                return ValidadorResultado.error("Cédula inválida."); 
            }

            int tercerDigito = Integer.parseInt(cedula.substring(2, 3));

            if (tercerDigito >= 6) {
                return ValidadorResultado.error("Cédula inválida.");
            }

            int[] coef = {2, 1, 2, 1, 2, 1, 2, 1, 2};
            int suma = 0;
            for (int i = 0; i < 9; i++) {
                int dig = Character.getNumericValue(cedula.charAt(i));
                int prod = dig * coef[i];
                if (prod >= 10) {
                    prod -= 9;
                }
                suma += prod;
            }
            int residuo = suma % 10;
            int verificador = (residuo == 0) ? 0 : 10 - residuo;

            if (verificador != Character.getNumericValue(cedula.charAt(9))) {
                return ValidadorResultado.error("Cédula inválida."); 
            }

            return ValidadorResultado.ok();

        } catch (NumberFormatException e) {
            return ValidadorResultado.error("La cédula solo debe contener números.");
        }
    }

    public static ValidadorResultado validarRuc(String ruc) {
        if (ruc == null || !ruc.matches("\\d{13}")) {
            return ValidadorResultado.error("El RUC debe tener 13 dígitos numéricos.");
        }

        ValidadorResultado ced = validarCedula(ruc.substring(0, 10));
        if (!ced.isValid()) {
            return ValidadorResultado.error("Los primeros 10 dígitos del RUC no son una cédula válida.");
        }

        if (ruc.substring(10, 13).equals("000")) {
            return ValidadorResultado.error("El RUC no puede terminar en 000.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarPlaca(String placa) {
        if (placa == null) {
            return ValidadorResultado.error("La placa no puede ser nula.");
        }
        String p = placa.trim().toUpperCase();

        if (!p.matches("^[A-Z]{3}-\\d{3,4}$")) {
            return ValidadorResultado.error("Formato de placa inválido (Ej: ABC-1234).");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarCorreo(String correo) {
        if (correo == null) {
            return ValidadorResultado.error("El correo no puede ser nulo.");
        }
        String valor = correo.trim();

        if (valor.isEmpty()) {
            return ValidadorResultado.error("El correo no puede estar vacío.");
        }

        String patron = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (!valor.matches(patron)) {
            return ValidadorResultado.error("Formato de correo electrónico inválido.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarTelefono(String telefono) {
        if (telefono == null) {
            return ValidadorResultado.error("El teléfono no puede ser nulo.");
        }

        if (!telefono.trim().matches("^0\\d{8,9}$")) {
            return ValidadorResultado.error("El teléfono debe iniciar con 0 y tener 9 o 10 dígitos.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarNoNulo(Object valor, String nombreCampo) {
        if (valor == null) {
            return ValidadorResultado.error("El campo " + nombreCampo + " no puede ser nulo.");
        }
        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarHora(String hora) {
        if (hora == null) {
            return ValidadorResultado.error("La hora no puede ser nula.");
        }

        String h = hora.trim();

        if (!h.matches("^\\d{2}:\\d{2}$")) {
            return ValidadorResultado.error("Formato de hora inválido (HH:mm).");
        }

        try {
            int hh = Integer.parseInt(h.substring(0, 2));
            int mm = Integer.parseInt(h.substring(3, 5));

            if (hh < 0 || hh > 23) {
                return ValidadorResultado.error("La hora debe estar entre 00 y 23.");
            }
            if (mm < 0 || mm > 59) {
                return ValidadorResultado.error("Los minutos deben estar entre 00 y 59.");
            }

            return ValidadorResultado.ok();

        } catch (NumberFormatException e) {
            return ValidadorResultado.error("Formato de hora inválido.");
        }
    }

    public static ValidadorResultado validarFecha(String fecha) {
        if (fecha == null) {
            return ValidadorResultado.error("La fecha no puede ser nula.");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            sdf.parse(fecha.trim());
            return ValidadorResultado.ok();
        } catch (ParseException e) {
            return ValidadorResultado.error("Formato de fecha inválido (yyyy-MM-dd).");
        }
    }

    public static ValidadorResultado validarAnioBus(int anio) {
        int actual = Calendar.getInstance().get(Calendar.YEAR);

        if (anio < 1970) {
            return ValidadorResultado.error("El año del bus no puede ser menor a 1970.");
        }
        if (anio > actual) {
            return ValidadorResultado.error("El año del bus no puede ser mayor al actual.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarEdadChofer(Date fechaNacimiento) {
        if (fechaNacimiento == null) {
            return ValidadorResultado.error("Debe ingresar una fecha de nacimiento.");
        }

        Calendar hoy = Calendar.getInstance();
        Calendar nac = Calendar.getInstance();
        nac.setTime(fechaNacimiento);

        int edad = hoy.get(Calendar.YEAR) - nac.get(Calendar.YEAR);

        if (hoy.get(Calendar.MONTH) < nac.get(Calendar.MONTH)
                || (hoy.get(Calendar.MONTH) == nac.get(Calendar.MONTH)
                && hoy.get(Calendar.DAY_OF_MONTH) < nac.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }

        if (edad < 22) {
            return ValidadorResultado.error("El chofer debe tener mínimo 22 años.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarMetodoPago(String metodo) {
        if (metodo == null) {
            return ValidadorResultado.error("Método de pago no puede ser nulo.");
        }

        String m = metodo.trim().toLowerCase();

        switch (m) {
            case "efectivo":
            case "tarjeta debito":
            case "tarjeta de debito":
            case "tarjeta credito":
            case "tarjeta de credito":
            case "transferencia":
            case "transferencia bancaria":
                return ValidadorResultado.ok();
        }

        return ValidadorResultado.error("Método de pago inválido.");
    }

    public static ValidadorResultado validarEstado(String estado) {
        if (estado == null) {
            return ValidadorResultado.error("El estado no puede ser nulo.");
        }

        String v = estado.trim().toUpperCase();

        if (!v.equals("ACTIVO") && !v.equals("INACTIVO")) {
            return ValidadorResultado.error("Estado inválido: debe ser ACTIVO o INACTIVO.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarTexto(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return ValidadorResultado.error("El texto no puede estar vacío.");
        }

        if (!texto.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ0-9 ,.\\-]+$")) {
            return ValidadorResultado.error("Texto inválido. Solo letras, números y (,.-).");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarTextoSoloLetras(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return ValidadorResultado.error("El texto no puede estar vacío.");
        }

        if (!texto.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {
            return ValidadorResultado.error("Solo se permiten letras y espacios.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarNoVacio(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return ValidadorResultado.error("El campo no puede estar vacío.");
        }
        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarLongitud(String texto, int min, int max) {
        if (texto == null) {
            return ValidadorResultado.error("El texto no puede ser nulo.");
        }

        int len = texto.trim().length();

        if (len < min) {
            return ValidadorResultado.error("El texto debe tener al menos " + min + " caracteres.");
        }
        if (len > max) {
            return ValidadorResultado.error("El texto debe tener máximo " + max + " caracteres.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarEntero(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return ValidadorResultado.error("Debe ingresar un número entero.");
        }

        if (!texto.matches("\\d+")) {
            return ValidadorResultado.error("Solo se permiten números enteros.");
        }

        return ValidadorResultado.ok();
    }

    public static ValidadorResultado validarDecimal(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return ValidadorResultado.error("Debe ingresar un número decimal.");
        }

        if (!texto.matches("^\\d+(\\.\\d+)?$")) {
            return ValidadorResultado.error("Formato de número decimal inválido.");
        }

        return ValidadorResultado.ok();
    }
}
