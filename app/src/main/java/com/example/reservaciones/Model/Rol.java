package com.example.reservaciones.Model;

import java.io.Serializable;

public class Rol implements Serializable {
    private int idrol;
    private String rol_nombre;
    private String estado;

    public Rol(int idrol, String rol_nombre, String estado) {
        this.idrol = idrol;
        this.rol_nombre = rol_nombre;
        this.estado = estado;
    }

    public Rol() {
    }

    public int getIdrol() {
        return idrol;
    }

    public void setIdrol(int idrol) {
        this.idrol = idrol;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
