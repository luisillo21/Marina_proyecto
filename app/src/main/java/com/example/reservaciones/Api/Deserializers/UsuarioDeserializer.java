package com.example.reservaciones.Api.Deserializers;

import com.example.reservaciones.Model.Usuario;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class UsuarioDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String cedula = json.getAsJsonObject().get("cedula").getAsString();
        String nombre = json.getAsJsonObject().get("nombre").getAsString();
        String apellido = json.getAsJsonObject().get("apellido").getAsString();
        String usuario = json.getAsJsonObject().get("usuario").getAsString();
        String clave = json.getAsJsonObject().get("clave").getAsString();
        int rol_id = json.getAsJsonObject().get("rol").getAsInt();
        String estado = json.getAsJsonObject().get("estado").getAsString();
        Usuario objUsuario  = new Usuario(cedula,nombre,apellido,usuario,clave,rol_id,estado);
        return objUsuario;
    }
}
