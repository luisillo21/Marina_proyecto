package com.example.reservaciones.Serializer;

import com.example.reservaciones.Model.Reservacion;

import java.util.ArrayList;

public class ReservacionSerializer {
    ArrayList<Reservacion> reservacion;

    public ReservacionSerializer(ArrayList<Reservacion> reservacion) {
        this.reservacion = reservacion;
    }

    public ReservacionSerializer() {

    }

    public ArrayList<Reservacion> getReservacion() {
        return reservacion;
    }

    public void setReservacion(ArrayList<Reservacion> reservacion) {
        this.reservacion = reservacion;
    }
}
