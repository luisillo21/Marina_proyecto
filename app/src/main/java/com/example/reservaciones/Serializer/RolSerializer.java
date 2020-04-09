package com.example.reservaciones.Serializer;

import com.example.reservaciones.Model.Rol;

import java.util.ArrayList;

public class RolSerializer {
    public ArrayList<Rol>  rol;

    public RolSerializer(ArrayList<Rol> rol) {
        this.rol = rol;
    }

    public ArrayList<Rol> getRol() {
        return rol;
    }

    public void setRol(ArrayList<Rol> rol) {
        this.rol = rol;
    }
}
