/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.transporte.gestion.modelo.Infraestructura;

/**
 *
 * @author Lenovo ThinkPad
 */
public class CopTerm {

    private int cooperativaId;
    private int terminalId;
    private boolean estado;

    public CopTerm() {
        this.estado = true;
    }

    public CopTerm(int cooperativaId, int terminalId) {
        this.cooperativaId = cooperativaId;
        this.terminalId = terminalId;
        this.estado = true;
    }

    public CopTerm(Integer cooperativaId, Integer terminalId) {
        this.cooperativaId = (cooperativaId != null) ? cooperativaId : 0;
        this.terminalId = (terminalId != null) ? terminalId : 0;
        this.estado = true;
    }

    public int getCooperativaId() {
        return cooperativaId;
    }

    public void setCooperativaId(int cooperativaId) {
        this.cooperativaId = cooperativaId;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CopTerm{" +
               "cooperativaId=" + cooperativaId +
               ", terminalId=" + terminalId +
               ", estado=" + estado +
               '}';
    }
}