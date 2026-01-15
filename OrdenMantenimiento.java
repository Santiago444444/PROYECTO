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
public class OrdenMantenimiento {

    private int id;
    private String busId;
    private Date fechaIngreso;
    private Date fechaSalida;
    private int kilometraje;
    private String estado;
    private int tallerId;
    private String observaciones;
    private int choferReportaId;
    private double subtotal;
    private double iva;
    private double total;

    public OrdenMantenimiento() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getTallerId() {
        return tallerId;
    }

    public void setTallerId(int tallerId) {
        this.tallerId = tallerId;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getChoferReportaId() {
        return choferReportaId;
    }

    public void setChoferReportaId(int choferReportaId) {
        this.choferReportaId = choferReportaId;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}