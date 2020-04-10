package com.example.reservaciones.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
        valores.put("rol_id",1);
        valores.put("id_base",usuario.getIdusuario());
        db.insert("usuario",null,valores);
        db.close();
    }

    public boolean guardar_usuario_cliente(Usuario usuario, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        boolean bandera;
        valores.put("cedula",usuario.getCedula());
        valores.put("nombre",usuario.getNombre());
        valores.put("apellido",usuario.getApellido());
        valores.put("usuario",usuario.getUsuario());
        valores.put("clave",usuario.getClave());
        valores.put("rol_id",2);
        long result =  db.insert("usuario",null,valores);

        if(result != 0){
           bandera = true;
        }else{
            bandera = false;
        }
        db.close();

        return bandera;
    }



    public void update_usuario(Usuario usuario, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("cedula",usuario.getCedula());
        valores.put("nombre",usuario.getNombre());
        valores.put("apellido",usuario.getApellido());
        valores.put("usuario",usuario.getUsuario());
        valores.put("clave",usuario.getClave());
        db.update("usuario",valores,"cedula="+usuario.getCedula(),null);
        db.close();
    }
    public void update_idbase(String id, String idbase,Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id_base",idbase);
        db.update("usuario",valores,"cedula="+id,null);
        db.close();
    }
    public void Eliminar_usuario(String cedula, Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("estado","I");
        db.update("usuario",valores,"cedula="+cedula,null);
        db.close();
    }
    public ArrayList<Usuario> Listar_todo(Context context){
        ReservacionesDB admin = new ReservacionesDB(context,"reservaciones",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor row = db.rawQuery("SELECT * from usuario where estado = 'A'",null);
        ArrayList<Usuario> lista = new ArrayList<Usuario>();

        while(row.moveToNext()){

            Usuario obj = new Usuario();
            obj.setCedula(row.getString(0));
            obj.setNombre(row.getString(1));
            obj.setApellido(row.getString(2));
            obj.setUsuario(row.getString(3));
            obj.setClave(row.getString(4));
            obj.setRol(row.getInt(5));
            obj.setEstado(row.getString(6));
            lista.add(obj);
        }
        db.close();
        return lista;

    }
}
