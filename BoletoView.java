package com.transporte.gestion.modelo.Ventas;

import java.util.Date;
import java.math.BigDecimal;

public class BoletoView {

    private Integer id;
    private String codigo;

    private Integer asientoNumero;

    private String clienteCedula;
    private String pasajeroCedula;

    private String tipoPasajeroNombre;

    private BigDecimal precioBase;
    private BigDecimal descuentoMonto;
    private BigDecimal precioFinal;
    private BigDecimal ivaMonto;
    private BigDecimal ivaUsado;

    private String estado;
    private Date fechaCompra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getAsientoNumero() {
        return asientoNumero;
    }

    public void setAsientoNumero(Integer asientoNumero) {
        this.asientoNumero = asientoNumero;
    }

    public String getClienteCedula() {
        return clienteCedula;
    }

    public void setClienteCedula(String clienteCedula) {
        this.clienteCedula = clienteCedula;
    }

    public String getPasajeroCedula() {
        return pasajeroCedula;
    }

    public void setPasajeroCedula(String pasajeroCedula) {
        this.pasajeroCedula = pasajeroCedula;
    }

    public String getTipoPasajeroNombre() {
        return tipoPasajeroNombre;
    }

    public void setTipoPasajeroNombre(String tipoPasajeroNombre) {
        this.tipoPasajeroNombre = tipoPasajeroNombre;
    }

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public BigDecimal getDescuentoMonto() {
        return descuentoMonto;
    }

    public void setDescuentoMonto(BigDecimal descuentoMonto) {
        this.descuentoMonto = descuentoMonto;
    }

    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }

    public BigDecimal getIvaMonto() {
        return ivaMonto;
    }

    public void setIvaMonto(BigDecimal ivaMonto) {
        this.ivaMonto = ivaMonto;
    }

    public BigDecimal getIvaUsado() {
        return ivaUsado;
    }

    public void setIvaUsado(BigDecimal ivaUsado) {
        this.ivaUsado = ivaUsado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

}
