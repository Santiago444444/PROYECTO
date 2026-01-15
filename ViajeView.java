package com.transporte.gestion.modelo.Operaciones;

import java.util.Date;

public class ViajeView {

    private Integer id;
    private Date fecha;
    private String estado;

    private String horaSalida;      
    private String placaBus;         
    private String choferNombre;     
    private String rutaNombre;    
    private String cooperativaNombre;

    public ViajeView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getPlacaBus() {
        return placaBus;
    }

    public void setPlacaBus(String placaBus) {
        this.placaBus = placaBus;
    }

    public String getChoferNombre() {
        return choferNombre;
    }

    public void setChoferNombre(String choferNombre) {
        this.choferNombre = choferNombre;
    }

    public String getRutaNombre() {
        return rutaNombre;
    }

    public void setRutaNombre(String rutaNombre) {
        this.rutaNombre = rutaNombre;
    }

    public String getCooperativaNombre() {
        return cooperativaNombre;
    }

    public void setCooperativaNombre(String cooperativaNombre) {
        this.cooperativaNombre = cooperativaNombre;
    }
}
