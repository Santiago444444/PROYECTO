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
public class Chofer {

    private int id;
    private int personaId;
    private String tipoLicencia;
    private Date fechaCaducidad;
    private Boolean estado;
    private Date fechaCreacion;

    public Chofer() {
    }

    public Chofer(int id, int personaId, String tipoLicencia, Date fechaCaducidad, Boolean estado, Date fechaCreacion) {
        this.id = id;
        this.personaId = personaId;
        this.tipoLicencia = tipoLicencia;
        this.fechaCaducidad = fechaCaducidad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }
    
    public Chofer(Integer id, Integer personaId, String tipoLicencia, Date fechaCaducidad, Boolean estado, Date fechaCreacion) {
        this.id = id;
        this.personaId = personaId;
        this.tipoLicencia = tipoLicencia;
        this.fechaCaducidad = fechaCaducidad;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
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

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Chofer{" + "id=" + id + ", personaId=" + personaId + ", tipoLicencia=" + tipoLicencia + ", fechaCaducidad=" + fechaCaducidad + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + '}';
    }
    
}