package com.example.reservaciones.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservaciones.Api.Api;
import com.example.reservaciones.Api.Deserializers.RaoDeserializers;
import com.example.reservaciones.Api.Deserializers.ReservacionDeserializer;
import com.example.reservaciones.Api.Deserializers.UsuarioDeserializer;
import com.example.reservaciones.Api.Services.UsuarioService;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Dao.RolDao;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Database.ReservacionesDB;
import com.example.reservaciones.Model.Rol;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;
import com.example.reservaciones.Serializer.ReservacionSerializer;
import com.example.reservaciones.Serializer.RolSerializer;
import com.example.reservaciones.Serializer.UsuarioSerializer;
import com.google.gson.GsonBuilder;

import java.security.Principal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario,etPass;
    Button btn_iniciar,btn_salir;
    CheckBox cbRecordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbRecordar = (CheckBox) findViewById(R.id.cbRecordar);



        etUsuario = (EditText) findViewById(R.id.etUsu);
        etPass = (EditText) findViewById(R.id.etPass);
        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
        btn_salir = (Button) findViewById(R.id.btn_salir);



        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etUsuario.getText().toString();
                String clave = etPass.getText().toString();



                ReservacionesDB admin = new ReservacionesDB(MainActivity.this,"reservaciones",null,1);
                SQLiteDatabase db = admin.getWritableDatabase();
                Cursor row = db.rawQuery("SELECT clave from usuario where usuario='"+usuario+"'",null);
                if (row.moveToFirst()){
                    if (clave.equals(row.getString(0))){
                        if (cbRecordar.isChecked()){
                            SharedPreferences preferences;
                            preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editors =preferences.edit();
                            editors.putString("nom_usu",usuario);
                            editors.putString("contra",clave);
                            editors.apply();
                            editors.commit();
                        }
                        Intent intent = new Intent(MainActivity.this, PrincipalActivity.class );
                        startActivity(intent);
                        finish();
                    }
                }else{
                    Traer_data();
                }


            }
        });


    }
    public void Traer_data(){
        final GsonBuilder builder = new GsonBuilder().setLenient();
        builder.registerTypeAdapter(UsuarioSerializer.class,new UsuarioDeserializer());
        Api.retrofit = null;
        UsuarioService serv = Api.getAPI(builder).create(UsuarioService.class);
        Call<UsuarioSerializer> datos = serv.getUsuario(etUsuario.getText().toString());
        datos.enqueue(new Callback<UsuarioSerializer>() {
            @Override
            public void onResponse(Call<UsuarioSerializer> call, Response<UsuarioSerializer> response) {
                if(response.isSuccessful()) {
                    builder.registerTypeAdapter(RolSerializer.class, new RaoDeserializers());
                    Api.retrofit = null;
                    UsuarioService serv = Api.getAPI(builder).create(UsuarioService.class);
                    Call<RolSerializer> data = serv.getRol();
                    data.enqueue(new Callback<RolSerializer>() {
                        @Override
                        public void onResponse(Call<RolSerializer> call, Response<RolSerializer> response) {
                            if(response.isSuccessful()){
                                RolSerializer rol = response.body();
                                for (int i = 0; i < rol.getRol().size(); i++) {
                                    Log.e("ERROR DE ROLES","LO QUE TE ESTA TRAYENDO ES: "+response.body().getRol().get(i).getRol_nombre().toString());
                                    RolDao roldao = new RolDao(MainActivity.this);
                                    roldao.Guardar_Rol(rol.getRol().get(i));
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<RolSerializer> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"Tienes un error al traer la informacion",Toast.LENGTH_LONG).show();
                            t.printStackTrace();
                        }
                    });

                    builder.registerTypeAdapter(ReservacionSerializer.class,new ReservacionDeserializer());
                    Api.retrofit = null;
                    serv = Api.getAPI(builder).create(UsuarioService.class);
                    Call<ReservacionSerializer> datitos = serv.getReservacion();
                    datitos.enqueue(new Callback<ReservacionSerializer>() {
                        @Override
                        public void onResponse(Call<ReservacionSerializer> call, Response<ReservacionSerializer> response) {
                            if (response.isSuccessful()) {
                                ReservacionSerializer reservacion = response.body();
                                for(int i = 0;i<reservacion.getReservacion().size();i++){
                                    ReservacionesDao dao = new ReservacionesDao();
                                    dao.guardar_reservacion(reservacion.getReservacion().get(i),MainActivity.this);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ReservacionSerializer> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"Tienes un error al traer la informacion",Toast.LENGTH_LONG).show();
                            t.printStackTrace();
                        }
                    });

                    builder.registerTypeAdapter(UsuarioSerializer.class,new UsuarioDeserializer());
                    Api.retrofit = null;
                    serv = Api.getAPI(builder).create(UsuarioService.class);
                    Call<UsuarioSerializer> cal = serv.getUsuario();
                    cal.enqueue(new Callback<UsuarioSerializer>() {
                        @Override
                        public void onResponse(Call<UsuarioSerializer> call, Response<UsuarioSerializer> response) {
                            if (response.isSuccessful()){
                                UsuarioSerializer user = response.body();
                                for(int i = 0;i<user.getUser().size();i++){
                                    UsuarioDao dao = new UsuarioDao();
                                    dao.guardar_usuario_admin(user.getUser().get(i),MainActivity.this);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<UsuarioSerializer> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"Tienes un error al traer la informacion",Toast.LENGTH_LONG).show();
                            t.printStackTrace();
                        }
                    });

                    String usuario = etUsuario.getText().toString();
                    String clave = etPass.getText().toString();
                    UsuarioSerializer user = response.body();
                    for (int i =0;i<user.getUser().size();i++)
                    {
                        if(usuario.equals(user.getUser().get(i).getUsuario())&& clave.equals(user.getUser().get(i).getClave())){
                            Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this,"Usuario o Contraseña Incorrecta",Toast.LENGTH_LONG).show();
                        }
                    }


                }
            }

            @Override
            public void onFailure(Call<UsuarioSerializer> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Usuario no registrado en el sistema",Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }
}
