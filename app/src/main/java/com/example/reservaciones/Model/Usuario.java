package com.example.reservaciones.Model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String cedula;
    private String nombre;
    private String apellido;
    private String usuario;
    private String clave;
    private int rol;
    private String estado;
    private String idbase;

    public String getIdbase() {
        return idbase;
    }

    public void setIdbase(String idbase) {
        this.idbase = idbase;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario() {
    }

    public Usuario(String cedula, String nombre, String apellido, String usuario, String clave, int rol, String estado) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
        this.estado = estado;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
