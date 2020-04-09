package com.example.reservaciones.Model;

public class Detalle_reservacion {
    public int id_detalle;
    public int id_usuario;
    public int id_reservacion;
    public int idbase;



    public Detalle_reservacion() {

    }

    public int getIdbase() {
        return idbase;
    }

    public void setIdbase(int idbase) {
        this.idbase = idbase;
    }


    public Detalle_reservacion(int id_detalle, int id_usuario, int id_reservacion) {
        this.id_detalle = id_detalle;
        this.id_usuario = id_usuario;
        this.id_reservacion = id_reservacion;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }
}
