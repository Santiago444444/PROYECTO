/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Infraestructura;

/**
 *
 * @author Lenovo ThinkPad
 */
public class TerminalView extends Terminal {

    private String ciudadNombre;

    public TerminalView() {
        super();
    }

    public String getCiudadNombre() {
        return ciudadNombre;
    }

    public void setCiudadNombre(String ciudadNombre) {
        this.ciudadNombre = ciudadNombre;
    }
}