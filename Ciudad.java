/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Infraestructura;

/**
 *
 * @author Lenovo ThinkPad
 */
public class Ciudad {

    private int id;
    private String nombre;
    private int provinciaId;

    public Ciudad() {
    }

    public Ciudad(int id, String nombre, int provinciaId) {
        this.id = id;
        this.nombre = nombre;
        this.provinciaId = provinciaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProvinciaId() {
        return provinciaId;
    }

    public void setProvinciaId(int provinciaId) {
        this.provinciaId = provinciaId;
    }
}
