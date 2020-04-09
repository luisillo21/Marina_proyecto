package com.example.reservaciones.Serializer;

import com.example.reservaciones.Model.Detalle_reservacion;

import java.util.ArrayList;

public class DetalleSerializers {
    ArrayList<Detalle_reservacion> detalle;

    public DetalleSerializers(ArrayList<Detalle_reservacion> detalle) {
        this.detalle = detalle;
    }

    public ArrayList<Detalle_reservacion> getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList<Detalle_reservacion> detalle) {
        this.detalle = detalle;
    }
}
