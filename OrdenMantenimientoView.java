/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Mantenimiento;

import java.sql.Date;

/**
 *
 * @author Lenovo ThinkPad
 */
public class OrdenMantenimientoView {

    private int id;
    private String busPlaca;
    private String tallerNombre;
    private Date fechaIngreso;
    private String estado;
    private String choferNombre;
    private double total;

    public OrdenMantenimientoView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusPlaca() {
        return busPlaca;
    }

    public void setBusPlaca(String busPlaca) {
        this.busPlaca = busPlaca;
    }

    public String getTallerNombre() {
        return tallerNombre;
    }

    public void setTallerNombre(String tallerNombre) {
        this.tallerNombre = tallerNombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getChoferNombre() {
        return choferNombre;
    }

    public void setChoferNombre(String choferNombre) {
        this.choferNombre = choferNombre;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}