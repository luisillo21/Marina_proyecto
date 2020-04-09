package com.example.reservaciones.Model;

import java.io.Serializable;

public class Reservacion implements Serializable {

    private int id_reservacion;
    private String nombre;
    private String observacion;
    private int cant_asientos;
    private String hora;
    private String reservado;
    private String estado;
    private int idbase;

    public int getIdbase() {
        return idbase;
    }

    public void setIdbase(int idbase) {
        this.idbase = idbase;
    }

    public Reservacion() {
    }

    public Reservacion(int id_reservacion, String nombre, String observacion, int cant_asientos, String hora, String reservado, String estado) {
        this.id_reservacion = id_reservacion;
        this.nombre = nombre;
        this.observacion = observacion;
        this.cant_asientos = cant_asientos;
        this.hora = hora;
        this.reservado = reservado;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getCant_asientos() {
        return cant_asientos;
    }

    public void setCant_asientos(int cant_asientos) {
        this.cant_asientos = cant_asientos;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getReservado() {
        return reservado;
    }

    public void setReservado(String reservado) {
        this.reservado = reservado;
    }
}
