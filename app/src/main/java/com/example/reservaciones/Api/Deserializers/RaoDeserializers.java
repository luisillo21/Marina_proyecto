package com.example.reservaciones.Api.Deserializers;

import com.example.reservaciones.Model.Rol;
import com.example.reservaciones.Serializer.RolSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RaoDeserializers  implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray usuario = json.getAsJsonObject().get("rol").getAsJsonArray();
        ArrayList<Rol> rol = new ArrayList<>();
        RolSerializer lista = new RolSerializer();
        Rol roles;
        for (int i = 0; i < usuario.size();i++) {
            int idrol = usuario.get(i).getAsJsonObject().get("idrol").getAsInt();
            String nombre = usuario.get(i).getAsJsonObject().get("rol_nombre").getAsString();
            String estado = usuario.get(i).getAsJsonObject().get("estado").getAsString();
            roles = new Rol(idrol,nombre,estado);
            rol.add(roles);
        }
        lista.setRol(rol);
        return lista;
    }
}
