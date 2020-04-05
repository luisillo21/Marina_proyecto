package com.example.reservaciones.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.reservaciones.R;

public class FormReservacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_reservacion);

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
                Intent intent3 = new Intent(FormReservacion.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_salir:
                finish();
                return true;

            case R.id.it_AgregarReservacion:
                Intent intent4 = new Intent(FormReservacion.this,FormReservacion.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_agregarUsuario:
                Intent intent5 = new Intent(FormReservacion.this,FormUsuarioActivity.class);
                startActivity(intent5);
                finish();
                return true;

            case R.id.it_Usuarios:
                Intent intent6 = new Intent(FormReservacion.this,UsuariosActivity.class);
                startActivity(intent6);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
