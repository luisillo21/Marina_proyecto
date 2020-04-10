package com.example.reservaciones.Serializer;

import com.example.reservaciones.Model.Usuario;

import java.util.ArrayList;

public class UsuarioSerializer {
    public ArrayList<Usuario> user;

    public UsuarioSerializer(ArrayList<Usuario> user) {
        this.user = user;
    }

    public UsuarioSerializer() {

    }

    public ArrayList<Usuario> getUser() {
        return user;
    }

    public void setUser(ArrayList<Usuario> user) {
        this.user = user;
    }
}
