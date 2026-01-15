/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Operaciones;

/**
 *
 * @author Lenovo ThinkPad
 */
public class RutaView {
    private Integer id;
    private Integer cooperativaId;
    private String nombreCooperativa;
    private Integer origenTerminalId;
    private String nombreOrigen;
    private Integer destinoTerminalId;
    private String nombreDestino;
    private Integer distancia;
    private Integer duracion;
    private Boolean estado;

    public RutaView() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCooperativaId() {
        return cooperativaId;
    }

    public void setCooperativaId(Integer cooperativaId) {
        this.cooperativaId = cooperativaId;
    }

    public String getNombreCooperativa() {
        return nombreCooperativa;
    }

    public void setNombreCooperativa(String nombreCooperativa) {
        this.nombreCooperativa = nombreCooperativa;
    }

    public Integer getOrigenTerminalId() {
        return origenTerminalId;
    }

    public void setOrigenTerminalId(Integer origenTerminalId) {
        this.origenTerminalId = origenTerminalId;
    }

    public String getNombreOrigen() {
        return nombreOrigen;
    }

    public void setNombreOrigen(String nombreOrigen) {
        this.nombreOrigen = nombreOrigen;
    }

    public Integer getDestinoTerminalId() {
        return destinoTerminalId;
    }

    public void setDestinoTerminalId(Integer destinoTerminalId) {
        this.destinoTerminalId = destinoTerminalId;
    }

    public String getNombreDestino() {
        return nombreDestino;
    }

    public void setNombreDestino(String nombreDestino) {
        this.nombreDestino = nombreDestino;
    }

    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return nombreOrigen + "-" + nombreDestino;
    }
}