/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Operaciones;

import java.math.BigDecimal;

/**
 *
 * @author Lenovo ThinkPad
 */
public class TarifaView {

    private Integer id;
    private Integer rutaHorarioId;

    private String origen;
    private String destino;
    private String horaSalida;

    private BigDecimal precio;
    private Boolean estado;

    public TarifaView() {
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

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}