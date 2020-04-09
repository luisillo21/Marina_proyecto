package com.example.reservaciones.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.reservaciones.Database.ReservacionesDB;
import com.example.reservaciones.Model.Rol;

public class RolDao {
    private ReservacionesDB admin;
    private SQLiteDatabase db;

    public RolDao(Context context) {
        this.admin = new ReservacionesDB(context,"reservacion",null,1);
        this.db = this.admin.getReadableDatabase();
    }
    public void Guardar_Rol(Rol rol){
        ContentValues values = new ContentValues();
        values.put("rol_nombre",rol.getRol_nombre());
        values.put("estado","ACTIVO");
        values.put("id_base",rol.getIdrol());
        this.db.insert("rol",null,values);
        this.db.close();
    }

}
