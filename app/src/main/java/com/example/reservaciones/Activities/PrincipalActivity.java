package com.example.reservaciones.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.reservaciones.Adapter.ReservacionAdapter;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.R;

import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    ListView listReservaciones;
    List<Reservacion> lista_reserv;
    ReservacionAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        listReservaciones = (ListView) findViewById(R.id.listReservaciones);
        lista_reserv = new ArrayList<Reservacion>();
        ReservacionesDao  crud = new ReservacionesDao();
        lista_reserv = crud.Listar_reservaciones(PrincipalActivity.this);
        adaptador = new ReservacionAdapter(PrincipalActivity.this,lista_reserv);
        listReservaciones.setAdapter(adaptador);

        listReservaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reservacion obj =  lista_reserv.get(position);
                Intent intent = new Intent(PrincipalActivity.this,DetalleReservacion.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("reservacion",obj);
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
                Intent intent3 = new Intent(PrincipalActivity.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_salir:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }


}
