package com.example.reservaciones.Api.Deserializers;

import com.example.reservaciones.Model.SincronizacionModel;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class SincronizacionDeserializers implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int id = json.getAsJsonObject().get("id").getAsInt();
        SincronizacionModel sin = new SincronizacionModel();
        sin.setId(id);
        return sin;
    }
}
