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
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.reservaciones.Activities.Activities.ActivitiesAdmin.DetalleReservacion;
import com.example.reservaciones.Activities.Activities.ActivitiesAdmin.PrincipalActivity;
import com.example.reservaciones.Activities.Activities.MainActivity;
import com.example.reservaciones.Adapter.ReservacionAdapter;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.R;

import java.util.ArrayList;
import java.util.List;

public class MainClienteActivity extends AppCompatActivity {
    ListView listReservaciones;
    List<Reservacion> lista_reserv;
    ReservacionAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cliente);
        lista_reserv = new ArrayList<Reservacion>();
        ReservacionesDao crud = new ReservacionesDao() ;
        lista_reserv = crud.Listar_reservaciones_disponibles(MainClienteActivity.this);
            listReservaciones = (ListView) findViewById(R.id.lstReservacionCliente);
            adaptador = new ReservacionAdapter(getApplicationContext(),lista_reserv);
            listReservaciones.setAdapter(adaptador);
            listReservaciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Reservacion obj = lista_reserv.get(position);
                    Intent intent = new Intent(MainClienteActivity.this, ReservacionDetalleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("reservacion", obj);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    finish();

                }
            });

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
                Intent intent3 = new Intent(MainClienteActivity.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.itClientecerrar:
                finish();
                return true;

            case R.id.itReservDisponibles:
                Intent intent4 = new Intent(MainClienteActivity.this, MainClienteActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.itCreserv:
                Intent intent6 = new Intent(MainClienteActivity.this, ReservacionesUsuarioActivity.class);
                startActivity(intent6);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
