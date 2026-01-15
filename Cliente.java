/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Personas;
import java.sql.Date;

/**
 *
 * @author Lenovo ThinkPad
 */
public class Cliente {

    private int id;
    private int personaId;
    private Date fechaCreacion;
    private String observaciones;

    public Cliente() {
    }

    public Cliente(int id, int personaId, Date fechaCreacion, String observaciones) {
        this.id = id;
        this.personaId = personaId;
        this.fechaCreacion = fechaCreacion;
        this.observaciones = observaciones;
    }

    public Cliente(Integer id, Integer personaId, Date fechaCreacion, String observaciones) {
        this.id = id;
        this.personaId = personaId;
        this.fechaCreacion = fechaCreacion;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id
                + ", personaId=" + personaId
                + ", fechaCreacion=" + fechaCreacion
                + '}';
    }
}