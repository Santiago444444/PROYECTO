package com.transporte.gestion.modelo.Flota;

import java.sql.Date;

public class ChoferBusView {

    private Integer id;
    private String busId;

    private Integer choferId;
    private String choferIdentificacion;
    private String choferNombres;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public Integer getChoferId() {
        return choferId;
    }

    public void setChoferId(Integer choferId) {
        this.choferId = choferId;
    }

    public String getChoferIdentificacion() {
        return choferIdentificacion;
    }

    public void setChoferIdentificacion(String choferIdentificacion) {
        this.choferIdentificacion = choferIdentificacion;
    }

    public String getChoferNombres() {
        return choferNombres;
    }

    public void setChoferNombres(String choferNombres) {
        this.choferNombres = choferNombres;
    }

    public String getChoferApellidos() {
        return choferApellidos;
    }

    public void setChoferApellidos(String choferApellidos) {
        this.choferApellidos = choferApellidos;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    private String choferApellidos;

    private Boolean estado;
    private Date fechaInicio;
    private Date fechaFin;

    public String getChoferNombreCompleto() {
        String n = (choferNombres == null) ? "" : choferNombres.trim();
        String a = (choferApellidos == null) ? "" : choferApellidos.trim();
        return (n + " " + a).trim();
    }
}
