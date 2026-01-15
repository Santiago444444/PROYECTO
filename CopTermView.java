/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Infraestructura;

/**
 *
 * @author Lenovo ThinkPad
 */
public class CopTermView {

    private Integer cooperativaId;
    private Integer terminalId;
    private String nombreCooperativa;
    private String nombreTerminal;
    private Boolean estado;

    public CopTermView() {
    }

    public Integer getCooperativaId() {
        return cooperativaId;
    }

    public void setCooperativaId(Integer cooperativaId) {
        this.cooperativaId = cooperativaId;
    }

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

    public String getNombreCooperativa() {
        return nombreCooperativa;
    }

    public void setNombreCooperativa(String nombreCooperativa) {
        this.nombreCooperativa = nombreCooperativa;
    }

    public String getNombreTerminal() {
        return nombreTerminal;
    }

    public void setNombreTerminal(String nombreTerminal) {
        this.nombreTerminal = nombreTerminal;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}