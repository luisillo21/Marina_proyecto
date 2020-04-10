package com.example.reservaciones.Api.Deserializers;

import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.Serializer.UsuarioSerializer;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UsuarioDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray obj = json.getAsJsonObject().get("usuario").getAsJsonArray();
        UsuarioSerializer ser = new UsuarioSerializer();
        ArrayList<Usuario> lista = new ArrayList<>();
        for (int i = 0; i<obj.size();i++)
        {
            String cedula = obj.get(i).getAsJsonObject().get("cedula").getAsString();
            int id = obj.get(i).getAsJsonObject().get("idusuario").getAsInt();
            String nombre = obj.get(i).getAsJsonObject().get("nombre").getAsString();
            String apellido = obj.get(i).getAsJsonObject().get("apellido").getAsString();
            String usuario = obj.get(i).getAsJsonObject().get("usuario").getAsString();
            String clave = obj.get(i).getAsJsonObject().get("clave").getAsString();
            int rol_id = obj.get(i).getAsJsonObject().get("rol").getAsInt();
            String estado = obj.get(i).getAsJsonObject().get("estado").getAsString();
            Usuario objUsuario  = new Usuario(cedula,nombre,apellido,usuario,clave,rol_id,estado);
            objUsuario.setIdusuario(id);
            lista.add(objUsuario);
        }
        ser.setUser(lista);
        return ser;
    }
}
