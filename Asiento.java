package com.transporte.gestion.modelo.Flota;

public class Asiento {

    private Integer id;
    private String busId;
    private Short numero;
    private Boolean estado;
    private String ubicacion;

    public Asiento() {
    }

    public Asiento(Integer id, String busId, Short numero, Boolean estado, String ubicacion) {
        this.id = id;
        this.busId = busId;
        this.numero = numero;
        this.estado = estado;
        this.ubicacion = ubicacion;
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

    public Short getNumero() {
        return numero;
    }

    public void setNumero(Short numero) {
        this.numero = numero;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
