package com.transporte.gestion.modelo.Flota;

import java.sql.Date;

public class ChoferBus {

    private Integer id;
    private String busId;
    private Integer choferId;

    private Boolean estado;
    private Date fechaInicio;
    private Date fechaFin;

    public ChoferBus() {
    }

    public ChoferBus(Integer id, String busId, Integer choferId,
            Boolean estado, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.busId = busId;
        this.choferId = choferId;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

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

    @Override
    public String toString() {
        return "ChoferBus{"
                + "id=" + id
                + ", busId='" + busId + '\''
                + ", choferId=" + choferId
                + ", estado=" + estado
                + ", fechaInicio=" + fechaInicio
                + ", fechaFin=" + fechaFin
                + '}';
    }
}
