package com.example.reservaciones.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.reservaciones.Api.Api;
import com.example.reservaciones.Api.Deserializers.SincronizacionDeserializers;
import com.example.reservaciones.Api.Services.UsuarioService;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Database.ReservacionesDB;
import com.example.reservaciones.Model.Detalle_reservacion;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.Model.SincronizacionModel;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Sincronizacion extends AppCompatActivity {
    Button sincronizar;
    ReservacionesDB reservacion;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizacion);
        sincronizar = (Button) findViewById(R.id.btn_sincronizar);

        sincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservacion = new ReservacionesDB(Sincronizacion.this,"reservaciones",null,1);
                db = reservacion.getReadableDatabase();
                Cursor row = db.rawQuery("SELECT * FROM usuario where estado = 'A'",null);
                if(row.moveToFirst())
                {
                    do{
                        final Usuario user = new Usuario();
                        user.setCedula(row.getString(0));
                        user.setNombre(row.getString(1));
                        user.setApellido(row.getString(2));
                        user.setUsuario(row.getString(3));
                        user.setClave(row.getString(4));
                        user.setRol(row.getInt(5));
                        user.setEstado(row.getString(6));
                        user.setIdbase(row.getString(7));
                        GsonBuilder builder = new GsonBuilder().setLenient();
                        builder.registerTypeAdapter(SincronizacionModel.class,new SincronizacionDeserializers());
                        Api.retrofit = null;
                        UsuarioService serv = Api.getAPI(builder).create(UsuarioService.class);
                        Gson gson = new Gson();
                        String json = "["+gson.toJson(user)+"]";
                        System.out.println(json);

                        Call<SincronizacionModel> datos = serv.setUsuario(json);
                        datos.enqueue(new Callback<SincronizacionModel>() {
                            @Override
                            public void onResponse(Call<SincronizacionModel> call, Response<SincronizacionModel> response) {
                                if (response.isSuccessful()){
                                    UsuarioDao dao = new UsuarioDao();
                                    SincronizacionModel sin = new SincronizacionModel();
                                    if(sin.getId()!=0)
                                    {
                                        dao.update_idbase(user.getCedula(),String.valueOf(sin.getId()),Sincronizacion.this);
                                        Toast.makeText(Sincronizacion.this,"Se ha actualizado de forma correcta",Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(Sincronizacion.this,"No ha actualizado de forma correcta, Revise posible dupluicaciones",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<SincronizacionModel> call, Throwable t) {
                                Toast.makeText(Sincronizacion.this,"OCURRIO UN ERROR",Toast.LENGTH_SHORT).show();
                                t.printStackTrace();
                                System.out.println("error em guardar data");
                            }
                        });
                    }while(row.moveToNext());
                }
                row = db.rawQuery("select * from reservacion",null);
                if(row.moveToFirst()){
                    do{
                        final Reservacion reserv = new Reservacion();
                        reserv.setId_reservacion(row.getInt(0));
                        reserv.setNombre(row.getString(1));
                        reserv.setObservacion(row.getString(2));
                        reserv.setCant_asientos(row.getInt(3));
                        reserv.setHora(row.getString(4));
                        reserv.setReservado(row.getString(5));
                        reserv.setEstado(row.getString(6));
                        reserv.setIdbase(row.getInt(7));
                        GsonBuilder builder = new GsonBuilder().setLenient();
                        builder.registerTypeAdapter(SincronizacionModel.class,new SincronizacionDeserializers());
                        Api.retrofit = null;
                        UsuarioService serv = Api.getAPI(builder).create(UsuarioService.class);
                        Gson gson = new Gson();
                        String json = "["+gson.toJson(reserv)+"]";
                        System.out.println(json);
                        Call<SincronizacionModel> datos = serv.SetReservaciones(json);
                        datos.enqueue(new Callback<SincronizacionModel>() {
                            @Override
                            public void onResponse(Call<SincronizacionModel> call, Response<SincronizacionModel> response) {
                                if(response.isSuccessful())
                                {
                                    ReservacionesDao dao = new ReservacionesDao();
                                    SincronizacionModel sin = response.body();
                                    if(sin.getId()!=0) {
                                        dao.update_idbase(reserv.getId_reservacion(), sin.getId(), Sincronizacion.this);
                                    }else {
                                        Toast.makeText(Sincronizacion.this, "No ha actualizado de forma correcta, Revise posible dupluicaciones", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<SincronizacionModel> call, Throwable t) {
                                Toast.makeText(Sincronizacion.this,"OCURRIO UN ERROR",Toast.LENGTH_SHORT).show();
                                t.printStackTrace();
                                System.out.println("error em guardar data");

                            }
                        });

                    }while (row.moveToNext());
                }

                row = db.rawQuery("select * from detalle_reservacion",null);
                if(row.moveToFirst()){
                    do{
                        final Detalle_reservacion rese = new Detalle_reservacion();
                        rese.setId_detalle(row.getInt(0));
                        rese.setId_usuario(row.getInt(1));
                        rese.setId_reservacion(row.getInt(2));
                        rese.setIdbase(row.getInt(3));
                        GsonBuilder builder = new GsonBuilder().setLenient();
                        builder.registerTypeAdapter(SincronizacionModel.class,new SincronizacionDeserializers());
                        Api.retrofit = null;
                        UsuarioService serv = Api.getAPI(builder).create(UsuarioService.class);
                        Gson gson = new Gson();
                        String json = "["+gson.toJson(rese)+"]";
                        System.out.println(json);

                        Call<SincronizacionModel> datos = serv.setDetalle(json);
                        datos.enqueue(new Callback<SincronizacionModel>() {
                            @Override
                            public void onResponse(Call<SincronizacionModel> call, Response<SincronizacionModel> response) {
                                if(response.isSuccessful())
                                {
                                    ReservacionesDao dao = new ReservacionesDao();
                                    SincronizacionModel sin = response.body();
                                    if(sin.getId()!=0) {
                                        dao.update_idbase_detalle(rese.getId_reservacion(), sin.getId(), Sincronizacion.this);
                                    }else{
                                        Toast.makeText(Sincronizacion.this,"OCURRIO UN ERROR",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<SincronizacionModel> call, Throwable t) {
                                Toast.makeText(Sincronizacion.this,"OCURRIO UN ERROR",Toast.LENGTH_SHORT).show();
                                t.printStackTrace();
                            }
                        });

                    }while (row.moveToNext());
                }
            }
        });



    }
}
