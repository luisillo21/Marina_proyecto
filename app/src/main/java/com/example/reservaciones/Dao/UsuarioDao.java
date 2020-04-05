package com.example.reservaciones.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.reservaciones.Database.ReservacionesDB;
import com.example.reservaciones.Model.Usuario;

import java.util.ArrayList;

public class UsuarioDao {

    public void guardar_usuario_admin(Usuario usuario, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("cedula",usuario.getCedula());
        valores.put("nombre",usuario.getNombre());
        valores.put("apellido",usuario.getApellido());
        valores.put("usuario",usuario.getUsuario());
        valores.put("clave",usuario.getClave());
        valores.put("rol",1);
        db.insert("usuario",null,valores);
        db.close();
    }

    public void guardar_usuario_cliente(Usuario usuario, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("cedula",usuario.getCedula());
        valores.put("nombre",usuario.getNombre());
        valores.put("apellido",usuario.getApellido());
        valores.put("usuario",usuario.getUsuario());
        valores.put("clave",usuario.getClave());
        valores.put("rol",2);
        db.insert("usuario",null,valores);
        db.close();
    }



    public void update_usuario(Usuario usuario, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("cedula",usuario.getCedula());
        valores.put("nombre",usuario.getNombre());
        valores.put("apellido",usuario.getApellido());
        valores.put("usuario",usuario.getUsuario());
        db.update("usuario",valores,"id_personal="+usuario.getCedula(),null);
        db.close();
    }
    public void Eliminar_usuario(String cedula, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("estado","I");
        db.update("personal",valores,"id_personal="+cedula,null);
        db.close();
    }
    public ArrayList<Usuario> Listar_todo(Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor row = db.rawQuery("SELECT * from personal where estado = 'A'",null);
        ArrayList<Usuario> lista = new ArrayList<Usuario>();

        while(row.moveToNext()){


            Usuario obj = new Usuario();
            obj.setCedula(row.getString(0));
            obj.setNombre(row.getString(1));
            obj.setApellido(row.getString(2));
            obj.setUsuario(row.getString(3));
            obj.setRol(row.getInt(5));

            lista.add(obj);
        }
        db.close();
        return lista;

    }
}
