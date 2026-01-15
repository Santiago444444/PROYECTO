/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Flota;

/**
 *
 * @author Lenovo ThinkPad
 */
public class Modelo {

    private int id;
    private String nombre;
    private int marcaId;

    public Modelo() {
    }

    public Modelo(int id, String nombre, int marcaId) {
        this.id = id;
        this.nombre = nombre;
        this.marcaId = marcaId;
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

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }
}