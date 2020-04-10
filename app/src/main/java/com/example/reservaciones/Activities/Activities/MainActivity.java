package com.example.reservaciones.Activities.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservaciones.Activities.Activities.ActivitiesAdmin.PrincipalActivity;
import com.example.reservaciones.Activities.Activities.ActivitiesCliente.FormUsuarioClienteActivity;
import com.example.reservaciones.Activities.Activities.ActivitiesCliente.MainClienteActivity;
import com.example.reservaciones.Api.Api;
import com.example.reservaciones.Api.Deserializers.UsuarioDeserializer;
import com.example.reservaciones.Api.Deserializers.UsuarioLogin;
import com.example.reservaciones.Api.Services.UsuarioService;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Database.ReservacionesDB;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario,etPass;
    Button btn_iniciar,btn_salir,btn_crear_usuario_cliente;
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
        btn_crear_usuario_cliente = (Button) findViewById(R.id.btn_crear_usuario_cliente);

        btn_crear_usuario_cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormUsuarioClienteActivity.class );
                startActivity(intent);
                finish();
            }
        });



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
                Cursor row = db.rawQuery("SELECT clave,rol_id from usuario where usuario='"+usuario+"'",null);
                if (row.moveToFirst()){
                    if (clave.equals(row.getString(0))){
                        //Guardamos el id en sharedPreferences
                        SharedPreferences preferencesId;
                        preferencesId = getSharedPreferences("id_usuario", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit =preferencesId.edit();
                        edit.putInt("id_usuario",row.getInt(1));
                        edit.apply();
                        edit.commit();
                        //-------------------------------
                        //Si selecciono el check de recordar se guardara las credenciales en otro sharedPreferences
                        if (cbRecordar.isChecked()){
                            SharedPreferences preferences;
                            preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editors =preferences.edit();
                            editors.putString("nom_usu",usuario);
                            editors.putString("contra",clave);
                            editors.apply();
                            editors.commit();
                        }
                        if(row.getInt(1)==1){
                            Intent intent = new Intent(MainActivity.this, PrincipalActivity.class );
                            startActivity(intent);
                            finish();
                        }else{
                            Intent intent = new Intent(MainActivity.this, MainClienteActivity.class );
                            startActivity(intent);
                            finish();
                        }
                    }
                }else{
                    GsonBuilder builder = new GsonBuilder().setLenient();
                    builder.registerTypeAdapter(Usuario.class, new UsuarioLogin());
                    Api.retrofit = null;
                    UsuarioService  serv = Api.getAPI(builder).create(UsuarioService.class);
                    Call<Usuario> datos = serv.getUsuario(usuario);
                    datos.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (response.isSuccessful()){
                                String usuario = etUsuario.getText().toString();
                                String clave = etPass.getText().toString();
                                Usuario obj = response.body();
                                if (usuario.equals(obj.getUsuario()) && clave.equals(obj.getClave())){
                                    UsuarioDao object = new UsuarioDao();
                                    object.guardar_usuario_admin(obj,MainActivity.this);
                                    Toast.makeText(MainActivity.this,"Usuario Guardado en la base local",Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, PrincipalActivity.class );
                                    startActivity(intent);
                                    finish();
                                }else{
                                    Toast.makeText(MainActivity.this,"Contraseña incorrecta",Toast.LENGTH_LONG).show();
                                }

                            }else{
                                Toast.makeText(MainActivity.this,"Usuario no registrado",Toast.LENGTH_LONG).show();

                                Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(MainActivity.this,"No tiene conexion a internet, por favor consulte a su proveedor",Toast.LENGTH_LONG).show();
                            t.printStackTrace();
                        }
                    });


                }


            }
        });


    }
}
