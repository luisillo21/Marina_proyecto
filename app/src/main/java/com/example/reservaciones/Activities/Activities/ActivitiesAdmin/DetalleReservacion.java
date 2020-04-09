package com.example.reservaciones.Activities.Activities.ActivitiesAdmin;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reservaciones.Activities.Activities.MainActivity;
import com.example.reservaciones.Activities.Activities.UsuariosActivity;
import com.example.reservaciones.Dao.ReservacionesDao;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;

public class DetalleReservacion extends AppCompatActivity {
    EditText etNombreD,etObservacionD,etCant_asientosD,etHoraD;
    Button btnActualizarR,btnEliminarR;
    Reservacion obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle objeto = getIntent().getExtras();
        obj = new Reservacion();

        if (objeto != null){
            obj = (Reservacion) objeto.getSerializable("reservacion");
            Log.e("ERROR DE SERIALIZER","SERIALIZER"+obj.getNombre());
        }

        setContentView(R.layout.activity_detalle_reservacion);
        etNombreD = (EditText)findViewById(R.id.etNombreD);
        etObservacionD = (EditText)findViewById(R.id.etObservacionD);
        etCant_asientosD = (EditText)findViewById(R.id.etCant_asientosD);
        etHoraD = (EditText)findViewById(R.id.etHoraD);
        btnActualizarR = (Button)findViewById(R.id.btnActualizarR);
        btnEliminarR = (Button)findViewById(R.id.btnEliminarR);

        etNombreD.setText(String.valueOf(obj.getNombre()));
        etObservacionD.setText(String.valueOf( obj.getObservacion()));
        etCant_asientosD.setText(String.valueOf( obj.getCant_asientos()));
        etHoraD.setText(String.valueOf(obj.getHora()));

        btnActualizarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String var_1 = etNombreD.getText().toString();
                String var_2 = etObservacionD.getText().toString();
                String var_3 = etCant_asientosD.getText().toString();
                String var_4 = etHoraD.getText().toString();
                ReservacionesDao objDao = new ReservacionesDao();
                if (var_1.isEmpty() || var_2.isEmpty() || var_3.isEmpty() || var_4.isEmpty()){
                    Toast.makeText(DetalleReservacion.this,"Todos los campos son obligatoriosn",Toast.LENGTH_LONG).show();
                }else{
                    Reservacion editObj = new Reservacion();
                    editObj.setId_reservacion(obj.getId_reservacion());
                    editObj.setNombre(var_1);
                    editObj.setObservacion(var_2);
                    editObj.setCant_asientos(Integer.valueOf(var_3));
                    editObj.setHora(var_4);
                    editObj.setEstado("A");
                    objDao.update_reservacion(editObj,DetalleReservacion.this);
                    Intent intent = new Intent(DetalleReservacion.this,PrincipalActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btnEliminarR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReservacionesDao crud = new ReservacionesDao();
                crud.eliminar_reservacion(obj.getId_reservacion(),DetalleReservacion.this);
                Intent intent = new Intent(DetalleReservacion.this,PrincipalActivity.class);
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
                Intent intent3 = new Intent(DetalleReservacion.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_salir:
                finish();
                return true;

            case R.id.it_AgregarReservacion:
                Intent intent4 = new Intent(DetalleReservacion.this, FormReservacion.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_agregarUsuario:
                Intent intent5 = new Intent(DetalleReservacion.this, FormUsuarioActivity.class);
                startActivity(intent5);
                finish();
                return true;

            case R.id.it_Usuarios:
                Intent intent6 = new Intent(DetalleReservacion.this, UsuariosActivity.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_Reservaciones:
                Intent intent9 = new Intent(DetalleReservacion.this, PrincipalActivity.class);
                startActivity(intent9);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
