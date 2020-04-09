package com.example.reservaciones.Activities.Activities.ActivitiesCliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.reservaciones.Activities.Activities.ActivitiesAdmin.PrincipalActivity;
import com.example.reservaciones.Activities.Activities.MainActivity;
import com.example.reservaciones.Adapter.ReservacionAdapter;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.R;

import java.util.ArrayList;
import java.util.List;

public class ReservacionesUsuarioActivity extends AppCompatActivity {
    ListView listReservacionesUsuario;
    List<Reservacion> lista_reserv;
    ReservacionAdapter adaptador;
    ReservacionesDao crud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences;
        preferences = getSharedPreferences("id_usuario", Context.MODE_PRIVATE);
        int usu = preferences.getInt("id_usuario",0);
        setContentView(R.layout.activity_reservaciones_usuario);
        listReservacionesUsuario = (ListView) findViewById(R.id.listReservacionesUsuario);
        lista_reserv = new ArrayList<Reservacion>();
        ReservacionesDao  crud = new ReservacionesDao();
        lista_reserv = crud.Reservaciones_usuario(usu,ReservacionesUsuarioActivity.this);
        adaptador = new ReservacionAdapter(ReservacionesUsuarioActivity.this,lista_reserv);
        listReservacionesUsuario.setAdapter(adaptador);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menucliente,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.itCerrarSesion:
                SharedPreferences preferences;
                preferences = getSharedPreferences("credenciales",MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent3 = new Intent(ReservacionesUsuarioActivity.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.itClientecerrar:
                finish();
                return true;

            case R.id.itReservDisponibles:
                Intent intent4 = new Intent(ReservacionesUsuarioActivity.this, MainClienteActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.itCreserv:
                Intent intent6 = new Intent(ReservacionesUsuarioActivity.this, ReservacionesUsuarioActivity.class);
                startActivity(intent6);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
