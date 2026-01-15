package com.transporte.gestion.modelo.Seguridad;

import java.sql.Date;

public class Usuario {

    private int id;
    private int personaId;
    private String usuario;
    private String contrasenia;
    private int rolId;
    private Boolean estado;
    private Date fechaCreacion;

    public Usuario() {
    }

    public Usuario(int id, int personaId, String usuario, String contrasenia, int rolId, Boolean estado) {
        this.id = id;
        this.personaId = personaId;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rolId = rolId;
        this.estado = estado;
    }

    public Usuario(Integer id, Integer personaId, String usuario, String contrasenia,
            Integer rolId, Boolean estado, Date fechaCreacion) {
        this.id = id;
        this.personaId = personaId;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rolId = rolId;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
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
        return "Usuario{id=" + id
                + ", usuario='" + usuario + '\''
                + ", rolId=" + rolId
                + ", estado='" + estado + '\''
                + '}';
    }
}
