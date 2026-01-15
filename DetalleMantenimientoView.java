/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Mantenimiento;

/**
 *
 * @author Lenovo ThinkPad
 */
public class DetalleMantenimientoView {

    private int id;
    private String tipoMantenimientoNombre;
    private String descripcion;
    private double costoUnitario;
    private int cantidad;
    private double total;

    public DetalleMantenimientoView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoMantenimientoNombre() {
        return tipoMantenimientoNombre;
    }

    public void setTipoMantenimientoNombre(String tipoMantenimientoNombre) {
        this.tipoMantenimientoNombre = tipoMantenimientoNombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}