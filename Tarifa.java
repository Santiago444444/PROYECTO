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
public class Tarifa {

    private Integer id;
    private Integer rutaHorarioId;
    private BigDecimal precioBase;
    private Boolean estado;

    public Tarifa() {
    }

    public Tarifa(Integer id, Integer rutaHorarioId, BigDecimal precioBase, Boolean estado) {
        this.id = id;
        this.rutaHorarioId = rutaHorarioId;
        this.precioBase = precioBase;
        this.estado = estado;
    }

    public Tarifa(Integer rutaHorarioId, BigDecimal precioBase, Boolean estado) {
        this.rutaHorarioId = rutaHorarioId;
        this.precioBase = precioBase;
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

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tarifa #" + id;
    }
}