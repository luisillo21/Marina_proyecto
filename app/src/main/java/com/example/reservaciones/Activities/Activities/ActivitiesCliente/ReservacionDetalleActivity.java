package com.example.reservaciones.Activities.Activities.ActivitiesCliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.reservaciones.Activities.Activities.MainActivity;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Model.Detalle_reservacion;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;

public class ReservacionDetalleActivity extends AppCompatActivity {
    TextView etCformNombre,etCformObs,etCformCantA,etCDetalleHora;
    Button btnReservarCliente;
    Reservacion obj;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservacion_detalle);

        SharedPreferences preferences;
        preferences = getSharedPreferences("id_usuario", Context.MODE_PRIVATE);
        final int usu = preferences.getInt("id_usuario",0);

        etCformNombre = (TextView) findViewById(R.id.etCformNombre);
        etCformObs = (TextView) findViewById(R.id.etCformObs);
        etCformCantA = (TextView) findViewById(R.id.etCformCantA);
        etCDetalleHora = (TextView) findViewById(R.id.etCDetalleHora);
        btnReservarCliente = (Button) findViewById(R.id.btnReservarCliente);
        obj = new Reservacion();
        bundle = getIntent().getExtras();

        if (bundle != null){
            obj = (Reservacion) bundle.getSerializable("reservacion");
        }

        etCformNombre.setText(obj.getNombre());
        etCformObs.setText(obj.getObservacion());
        etCformCantA.setText(String.valueOf(obj.getCant_asientos()));
        etCDetalleHora.setText(obj.getHora());

        btnReservarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReservacionesDao crud = new ReservacionesDao();
                crud.reservar(obj,ReservacionDetalleActivity.this);
                Detalle_reservacion dt = new Detalle_reservacion();
                dt.setId_usuario(usu);
                dt.setId_reservacion(obj.getId_reservacion());
                crud.guardar_detalle(dt,ReservacionDetalleActivity.this);
                Intent intent = new Intent(ReservacionDetalleActivity.this,MainClienteActivity.class);
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
                Intent intent3 = new Intent(ReservacionDetalleActivity.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.itClientecerrar:
                finish();
                return true;

            case R.id.itReservDisponibles:
                Intent intent4 = new Intent(ReservacionDetalleActivity.this, MainClienteActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.itCreserv:
                Intent intent6 = new Intent(ReservacionDetalleActivity.this, ReservacionesUsuarioActivity.class);
                startActivity(intent6);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
