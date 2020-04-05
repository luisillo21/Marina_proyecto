package com.example.reservaciones.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.R;

public class FormReservacion extends AppCompatActivity {

    EditText etusuario,etobservaciom,etcantidad,ethora,lista;
    Button btn_iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_reservacion);

        etusuario = (EditText)findViewById(R.id.etUsu);
        etobservaciom = (EditText)findViewById(R.id.etObservacion);
        etcantidad = (EditText)findViewById(R.id.etCant_asientos);
        ethora = (EditText)findViewById(R.id.Hora);
        lista = (EditText)findViewById(R.id.reservacion_id);

        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etusuario.getText().toString();
                String Observacion = etusuario.getText().toString();
                int Cantidad = Integer.valueOf(etusuario.getText().toString());
                String Hora = etusuario.getText().toString();
                String Reservacion = etusuario.getText().toString();
                String Estado = "A";
                Reservacion rese = new Reservacion(0,usuario,Observacion,Cantidad,Hora,Reservacion,Estado);
                ReservacionesDao dao = new ReservacionesDao();
                dao.guardar_reservacion(rese,FormReservacion.this);
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
