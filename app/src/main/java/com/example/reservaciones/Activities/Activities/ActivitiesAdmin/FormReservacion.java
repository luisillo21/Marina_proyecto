package com.example.reservaciones.Activities.Activities.ActivitiesAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.reservaciones.Activities.Activities.MainActivity;
import com.example.reservaciones.Activities.Activities.UsuariosActivity;
import com.example.reservaciones.Activities.Sincronizacion;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.R;

public class FormReservacion extends AppCompatActivity {

    EditText etformNomreserv,etFormObservacion,etformcantAsientos,etformHora;
    Button btn_guardar_form_reservacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_reservacion);

        etformNomreserv = (EditText)findViewById(R.id.etformNomreserv);
        etFormObservacion = (EditText)findViewById(R.id.etFormObservacion);
        etformcantAsientos = (EditText)findViewById(R.id.etformcantAsientos);
        etformHora = (EditText)findViewById(R.id.etformHora);

        btn_guardar_form_reservacion = (Button) findViewById(R.id.btn_guardar_form_reservacion);

        btn_guardar_form_reservacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = etformNomreserv.getText().toString();
                String Observacion = etFormObservacion.getText().toString();
                int Cantidad = Integer.valueOf(etformcantAsientos.getText().toString());
                String Hora = etformHora.getText().toString();
                Reservacion rese = new Reservacion();

                if (nombre.isEmpty() || Observacion.isEmpty() || Cantidad == 0 || Hora.isEmpty() ){
                    Toast.makeText(FormReservacion.this,"Todos los campos son obligatorios",Toast.LENGTH_SHORT).show();
                }else{
                    rese.setNombre(nombre);
                    rese.setObservacion(Observacion);
                    rese.setCant_asientos(Cantidad);
                    rese.setHora(Hora);
                    ReservacionesDao dao = new ReservacionesDao();
                    dao.guardar_reservacion(rese,FormReservacion.this);
                    etformNomreserv.setText("");
                    etFormObservacion.setText("");
                    etformcantAsientos.setText("");
                    etformHora.setText("");
                    Toast.makeText(FormReservacion.this,"La reservacion "+nombre+" Asido creada",Toast.LENGTH_SHORT).show();


                }

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
                Intent intent3 = new Intent(FormReservacion.this, MainActivity.class);
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
                Intent intent5 = new Intent(FormReservacion.this, FormUsuarioActivity.class);
                startActivity(intent5);
                finish();
                return true;

            case R.id.it_Usuarios:
                Intent intent6 = new Intent(FormReservacion.this, UsuariosActivity.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_Reservaciones:
                Intent intent7 = new Intent(FormReservacion.this,PrincipalActivity.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_sincronizar:
                Intent intent8 = new Intent(FormReservacion.this, Sincronizacion.class);
            startActivity(intent8);
            finish();
            return true;


            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
