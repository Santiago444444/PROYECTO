package com.transporte.gestion.modelo.Flota;

public class Bus {

    private String placa;            
    private Integer modeloId;        
    private Integer cooperativaId; 
    private Integer capacidad;      
    private String estado;         
    private Integer anioFabricacion; 

    public Bus() {
    }

    public Bus(String placa, Integer modeloId, Integer cooperativaId,
            Integer capacidad, String estado, Integer anioFabricacion) {
        this.placa = placa;
        this.modeloId = modeloId;
        this.cooperativaId = cooperativaId;
        this.capacidad = capacidad;
        this.estado = estado;
        this.anioFabricacion = anioFabricacion;
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

    public Integer getCooperativaId() {
        return cooperativaId;
    }

    public void setCooperativaId(Integer cooperativaId) {
        this.cooperativaId = cooperativaId;
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

    @Override
    public String toString() {
        return "Bus{"
                + "placa='" + placa + '\''
                + ", modeloId=" + modeloId
                + ", cooperativaId=" + cooperativaId
                + ", capacidad=" + capacidad
                + ", estado='" + estado + '\''
                + ", anioFabricacion=" + anioFabricacion
                + '}';
    }
}
