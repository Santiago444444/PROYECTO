package com.transporte.gestion.modelo.Flota;

public class BusView {

    private String placa;

    private Integer modeloId;
    private String modeloNombre;   
    private Integer marcaId;      
    private String marcaNombre; 

    private Integer cooperativaId;
    private String cooperativaNombre;

    private Integer capacidad;
    private String estado;
    private Integer anioFabricacion;

    public BusView() {
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Integer getModeloId() {
        return modeloId;
    }

    public void setModeloId(Integer modeloId) {
        this.modeloId = modeloId;
    }

    public String getModeloNombre() {
        return modeloNombre;
    }

    public void setModeloNombre(String modeloNombre) {
        this.modeloNombre = modeloNombre;
    }

    public Integer getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(Integer marcaId) {
        this.marcaId = marcaId;
    }

    public String getMarcaNombre() {
        return marcaNombre;
    }

    public void setMarcaNombre(String marcaNombre) {
        this.marcaNombre = marcaNombre;
    }

    public Integer getCooperativaId() {
        return cooperativaId;
    }

    public void setCooperativaId(Integer cooperativaId) {
        this.cooperativaId = cooperativaId;
    }

    public String getCooperativaNombre() {
        return cooperativaNombre;
    }

    public void setCooperativaNombre(String cooperativaNombre) {
        this.cooperativaNombre = cooperativaNombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(Integer anioFabricacion) {
        this.anioFabricacion = anioFabricacion;
    }
}
