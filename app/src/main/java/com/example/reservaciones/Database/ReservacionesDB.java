package com.example.reservaciones.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ReservacionesDB extends SQLiteOpenHelper {

    public ReservacionesDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE rol(id_rol integer PRIMARY KEY AUTOINCREMENT NOT NULL,rol_nombre text,estado text default 'A',id_base integer default 0)");
        db.execSQL("CREATE TABLE reservacion(id_reservacion integer PRIMARY KEY AUTOINCREMENT NOT NULL" +
                ",nombre text" +
                ",observacion text" +
                ",cant_asientos integer" +
                ",hora text" +
                ",reservado text DEFAULT 'NO'" +
                ",estado text DEFAULT 'A'" +
                ",id_base integer DEFAULT 0)");
        db.execSQL("CREATE TABLE detalle_reservacion(id_detalle integer PRIMARY KEY AUTOINCREMENT NOT NULL,id_usuario integer,id_reservacion integer,id_base integer DEFAULT 0)");
        db.execSQL("CREATE TABLE usuario(cedula text primary key ,nombre text,apellido text,usuario text,clave text, rol_id integer,estado text DEFAULT 'A',id_base integer DEFAULT 0)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS rol");
        db.execSQL("CREATE TABLE rol(id_rol integer PRIMARY KEY AUTOINCREMENT NOT NULL,rol_nombre text)");
        db.execSQL("DROP TABLE IF EXISTS reservaciones");
        db.execSQL("CREATE TABLE reservacion(id_reservacion integer PRIMARY KEY AUTOINCREMENT NOT NULL,nombre text,observacion text,cant_asientos integer,hora text,reservado text DEFAULT 'NO',estado text DEFAULT 'A',id_base integer DEFAULT 0)");
        db.execSQL("DROP TABLE IF EXISTS detalle_reservacion");
        db.execSQL("CREATE TABLE detalle_reservacion(id_detalle integer PRIMARY KEY AUTOINCREMENT NOT NULL,id_usuario integer,id_reservacion integer,id_base integer DEFAULT 0)");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("CREATE TABLE usuario(cedula text primary key ,nombre text,apellido text,usuario text,clave text, rol_id integer,estado text DEFAULT 'A',id_base integer DEFAULT 0)");

    }

}
