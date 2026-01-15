/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Operaciones;

/**
 *
 * @author Lenovo ThinkPad
 */
public class RutaHorarioView {

    private Integer Id;
    private Integer rutaId;
    private Integer horarioId;
    private String nombreRuta;
    private String horaSalida;
    private Boolean estado;
    private Integer cooperativa;

    public Integer getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(Integer cooperativa) {
        this.cooperativa = cooperativa;
    }

    public RutaHorarioView() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getRutaId() {
        return rutaId;
    }

    public void setRutaId(Integer rutaId) {
        this.rutaId = rutaId;
    }

    public Integer getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Integer horarioId) {
        this.horarioId = horarioId;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }


    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombreRuta + " | " + horaSalida;
    }
}