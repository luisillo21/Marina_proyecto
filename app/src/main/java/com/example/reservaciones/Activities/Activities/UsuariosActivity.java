package com.example.reservaciones.Activities.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.reservaciones.Activities.Activities.ActivitiesAdmin.DetalleUsuarios;
import com.example.reservaciones.Activities.Activities.ActivitiesAdmin.FormReservacion;
import com.example.reservaciones.Activities.Activities.ActivitiesAdmin.FormUsuarioActivity;
import com.example.reservaciones.Activities.Activities.ActivitiesAdmin.PrincipalActivity;
import com.example.reservaciones.Adapter.UsuarioAdapter;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;

import java.util.ArrayList;
import java.util.List;

public class UsuariosActivity extends AppCompatActivity {

    ListView listUsuarios;
    List<Usuario> lista_usuarios;
    UsuarioAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        listUsuarios = (ListView) findViewById(R.id.lstUsuario);
        lista_usuarios = new ArrayList<Usuario>();
        UsuarioDao crud = new UsuarioDao();
        lista_usuarios  = crud.Listar_todo(UsuariosActivity.this);
        for(int i = 0; i<lista_usuarios.size();i++){
            Log.e("ERROR DE LISTA","LISTA USUARIO"+lista_usuarios.get(i).getCedula());
        }
        adaptador = new UsuarioAdapter(getApplicationContext(),lista_usuarios);
        listUsuarios.setAdapter(adaptador);

        Log.e("FINAL","hasta aqui si funciona: ");


        listUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Usuario obj=lista_usuarios.get(position);
                Intent intent = new Intent(UsuariosActivity.this, DetalleUsuarios.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario",obj);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.it_cerrar_sesion:
                SharedPreferences preferences;
                preferences = getSharedPreferences("credenciales",MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent3 = new Intent(UsuariosActivity.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_salir:
                finish();
                return true;

            case R.id.it_AgregarReservacion:
                Intent intent4 = new Intent(UsuariosActivity.this, FormReservacion.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_agregarUsuario:
                Intent intent5 = new Intent(UsuariosActivity.this,FormUsuarioActivity.class);
                startActivity(intent5);
                finish();
                return true;

            case R.id.it_Usuarios:
                Intent intent6 = new Intent(UsuariosActivity.this,UsuariosActivity.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_Reservaciones:
                Intent intent7 = new Intent(UsuariosActivity.this,PrincipalActivity.class);
                startActivity(intent7);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
