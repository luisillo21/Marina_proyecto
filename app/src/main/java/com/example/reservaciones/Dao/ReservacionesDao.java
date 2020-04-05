package com.example.reservaciones.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.reservaciones.Database.ReservacionesDB;
import com.example.reservaciones.Model.Detalle_reservacion;
import com.example.reservaciones.Model.Reservacion;

import java.util.ArrayList;

public class ReservacionesDao {


    public void guardar_reservacion(Reservacion reserv, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre",reserv.getNombre());
        valores.put("observacion",reserv.getObservacion());
        valores.put("cant_asientos",reserv.getCant_asientos());
        valores.put("hora",reserv.getHora());
        valores.put("reservado","NO");
        db.insert("reservaciones",null,valores);
        db.close();
    }


    public void guardar_detalle(Detalle_reservacion reserv, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id_reservacion",reserv.getId_reservacion());
        valores.put("id_usuario",reserv.getId_usuario());
        db.insert("detalle_reservacion",null,valores);
        db.close();
    }





    public void update_reservacion(Reservacion obj, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre",obj.getNombre());
        valores.put("observacion",obj.getNombre());
        valores.put("cant_asientos",obj.getCant_asientos());
        valores.put("hora",obj.getHora());
        db.update("usuario",valores,"id_reservacion="+obj.getId_reservacion(),null);
        db.close();
    }
    public void eliminar_reservacion(int id_reservacion, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("estado","I");
        db.update("personal",valores,"id_reservacion="+id_reservacion,null);
        db.close();
    }
    public ArrayList<Reservacion> Listar_reservaciones(Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor row = db.rawQuery("SELECT * from reservaciones",null);
        ArrayList<Reservacion> lista_reservacion = new ArrayList<Reservacion>();

        while(row.moveToNext()){
            Reservacion obj = new Reservacion();
            obj.setId_reservacion(row.getInt(0));
            obj.setNombre(row.getString(1));
            obj.setObservacion(row.getString(2));
            obj.setCant_asientos(row.getInt(3));
            obj.setHora(row.getString(4));

            lista_reservacion.add(obj);
        }
        db.close();
        return lista_reservacion;

    }
}
