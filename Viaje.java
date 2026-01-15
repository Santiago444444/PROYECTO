package com.transporte.gestion.modelo.Operaciones;

import java.sql.Date;

public class Viaje {

    private Integer id;
    private Integer rutaHorarioId;
    private Integer choferBusId;
    private Date fecha;
    private String estado;

    public Viaje() {
    }

    public Viaje(Integer id, Integer rutaHorarioId, Integer choferBusId,
            Date fecha, String estado) {
        this.id = id;
        this.rutaHorarioId = rutaHorarioId;
        this.choferBusId = choferBusId;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRutaHorarioId() {
        return rutaHorarioId;
    }

    public void setRutaHorarioId(Integer rutaHorarioId) {
        this.rutaHorarioId = rutaHorarioId;
    }

    public Integer getChoferBusId() {
        return choferBusId;
    }

    public void setChoferBusId(Integer choferBusId) {
        this.choferBusId = choferBusId;
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

    @Override
    public String toString() {
        return "Viaje{"
                + "id=" + id
                + ", rutaHorarioId=" + rutaHorarioId
                + ", choferBusId=" + choferBusId
                + ", fecha=" + fecha
                + ", estado='" + estado + '\''
                + '}';
    }
}
