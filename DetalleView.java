package com.transporte.gestion.modelo.Ventas;

import java.math.BigDecimal;

public class DetalleView {

    private Integer id;
    private Integer facturaId;

    private Integer boletoId;
    private String boletoCodigo;
    private Integer asientoNumero;

    private String descripcion;
    private BigDecimal precio;
    private BigDecimal iva;
    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public Integer getBoletoId() {
        return boletoId;
    }

    public void setBoletoId(Integer boletoId) {
        this.boletoId = boletoId;
    }

    public String getBoletoCodigo() {
        return boletoCodigo;
    }

    public void setBoletoCodigo(String boletoCodigo) {
        this.boletoCodigo = boletoCodigo;
    }

    public Integer getAsientoNumero() {
        return asientoNumero;
    }

    public void setAsientoNumero(Integer asientoNumero) {
        this.asientoNumero = asientoNumero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
