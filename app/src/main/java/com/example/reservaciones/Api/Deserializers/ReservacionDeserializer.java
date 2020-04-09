package com.example.reservaciones.Api.Deserializers;

import com.example.reservaciones.Model.Reservacion;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReservacionDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray objeto = json.getAsJsonObject().get("reservacion").getAsJsonArray();
        List<Reservacion> lista = new ArrayList<>();
        for (int i = 0;i<objeto.size();i++){
            Reservacion reservacion = new Reservacion();
            reservacion.setId_reservacion(objeto.get(i).getAsJsonObject().get("id_reservacion").getAsInt());
            reservacion.setNombre(objeto.get(i).getAsJsonObject().get("nombre").getAsString());
            reservacion.setObservacion(objeto.get(i).getAsJsonObject().get("observacion").getAsString());
            reservacion.setCant_asientos(objeto.get(i).getAsJsonObject().get("cant_asientos").getAsInt());
            reservacion.setHora(objeto.get(i).getAsJsonObject().get("hora").getAsString());
            reservacion.setReservado(objeto.get(i).getAsJsonObject().get("reservado").getAsString());
            reservacion.setEstado(objeto.get(i).getAsJsonObject().get("estado").getAsString());
            lista.add(reservacion);
        }
        return lista;
    }
}
