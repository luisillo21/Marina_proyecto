package com.example.reservaciones.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.reservaciones.Adapter.ReservacionAdapter;
import com.example.reservaciones.Adapter.UsuarioAdapter;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Model.Reservacion;
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
                Intent intent = new Intent(UsuariosActivity.this,DetalleUsuarios.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario",obj);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });



    }
}
