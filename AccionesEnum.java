package com.transporte.gestion.global.enums;

public enum AccionesEnum {

    // ========= NIVEL 1 (CATEGORÍAS) =========
    ADMIN(null, "Administración", null, null),

    // =======================================================
    // NIVEL 2: LAS CARPETAS (Categorías)
    // Su padre es ADMIN. Estas no tienen acción, solo agrupan.
    // =======================================================
    CAT_SEGURIDAD(ADMIN, "Seguridad", null, null),
    CAT_PERSONAS(ADMIN, "Gestión Personas", null, null),
    CAT_FLOTA(ADMIN, "Gestión de Flota", null, null),
    CAT_MANTENIMIENTO(ADMIN, "Mantenimiento", null, null),
    CAT_OPERACIONES(ADMIN, "Operaciones y Viajes", null, null),
    CAT_VENTAS(ADMIN, "Ventas y Caja", null, null),
    CAT_INFRA(ADMIN, "Infraestructura", null, null),
    CAT_REPORTES(ADMIN, "Reportes", null, null),
    USUARIOS(CAT_SEGURIDAD, "Usuarios", "USUARIOS", "usuarios.crud"),
    ROL(CAT_SEGURIDAD, "Roles", "ROL", "rol.crud"),
    PERMISOS(CAT_SEGURIDAD, "Permisos", "PERMISOS", "permisos.crud"),
    PERSONA(CAT_PERSONAS, "Personas (General)", "PERSONA", "persona.crud"),
    CLIENTE(CAT_PERSONAS, "Clientes", "CLIENTE", "cliente.crud"),
    CHOFER(CAT_PERSONAS, "Choferes", "CHOFER", "chofer.crud"),
    BUS(CAT_FLOTA, "Buses", "BUS", "bus.crud"),
    MARCA(CAT_FLOTA, "Marcas", "MARCA", "marca.crud"),
    MODELO(CAT_FLOTA, "Modelos", "MODELO", "modelo.crud"),
    CHOFERBUS(CAT_FLOTA, "Asignar Chofer-Bus", "CHOFERBUS", "choferBus.crud"),
    MIS_BUSES(CAT_FLOTA, "Mis Buses (Chofer)", "MIS_BUSES", "mantenimiento.chofer"),
    TALLER(CAT_MANTENIMIENTO, "Talleres", "TALLER", "taller.crud"),
    TIPO_MANTENIMIENTO(CAT_MANTENIMIENTO, "Tipo Mantenimiento", "TIPO_MANTENIMIENTO", "tipo_mantenimiento.crud"),
    MANTENIMIENTO_TALLER(CAT_MANTENIMIENTO, "Atención Taller", "MANT_TALLER", "mantenimiento.taller"),
    HISTORIAL_MANTENIMIENTO(CAT_MANTENIMIENTO, "Historial Mantenimiento", "HIST_MANT", "mantenimiento.historial"),
    RUTA(CAT_OPERACIONES, "Rutas", "RUTA", "ruta.crud"),
    VIAJE(CAT_OPERACIONES, "Viajes", "VIAJE", "viaje.crud"),
    HORARIO(CAT_OPERACIONES, "Horarios", "HORARIO", "horario.crud"),
    RUTAHORARIO(CAT_OPERACIONES, "Ruta - Horario", "RUTAHORARIO", "rutahorario.crud"),
    TARIFA(CAT_OPERACIONES, "Tarifas", "TARIFA", "tarifa.crud"),
    FACTURA(CAT_VENTAS, "Facturas", "FACTURA", "factura.crud"),
    TIPO_PASAJERO(CAT_VENTAS, "Tipo Pasajero", "TIPO_PASAJERO", "tipo_pasajero.crud"),
    METODO_PAGO(CAT_VENTAS, "Métodos de Pago", "METODO_PAGO", "metodo_pago.crud"),
    CIUDAD(CAT_INFRA, "Ciudades", "CIUDAD", "ciudad.crud"),
    PROVINCIA(CAT_INFRA, "Provincias", "PROVINCIA", "provincia.crud"),
    TERMINAL(CAT_INFRA, "Terminales", "TERMINAL", "terminal.crud"),
    COOPERATIVA(CAT_INFRA, "Cooperativas", "COOPERATIVA", "cooperativa.crud"),
    ASIGNACION_COOPERATIVAS(CAT_INFRA, "Asignar Coops", "ASIGNACION", "copterm.crud"),
    REPORTE(CAT_REPORTES, "Reportes", "REPORTE", "reporte.crud");
    
    // ========= NIVEL 3 (ACCIONES / HOJAS) =========
    //PERSONA_LISTAR(PERSONA, "Listar", "PERSONA_VER", "persona.listar"),
    private final AccionesEnum padre;
    private final String label;
    private final String permiso;
    private final String accion;

    AccionesEnum(AccionesEnum padre, String label, String permiso, String accion) {
        this.padre = padre;
        this.label = label;
        this.permiso = permiso;
        this.accion = accion;
    }

    public AccionesEnum getPadre() {
        return padre;
    }

    public String getLabel() {
        return label;
    }

    public String getPermiso() {
        return permiso;
    }

    public String getAccion() {
        return accion;
    }

    public boolean esCarpeta() {
        return accion == null;
    }

    public boolean requierePermiso() {
        return permiso != null && !permiso.isBlank();
    }
}
