package com.example.reservaciones.Activities.Activities.ActivitiesAdmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservaciones.Activities.Activities.MainActivity;
import com.example.reservaciones.Activities.Activities.UsuariosActivity;
import com.example.reservaciones.Activities.Sincronizacion;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;

public class FormUsuarioActivity extends AppCompatActivity {

    EditText et_form_usuario,et_form_nombre,et_form_apellido,et_form_cedula,et_form_pass,et_conf_pass;
    Button btn_guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_usuario);

        et_form_usuario = (EditText) findViewById(R.id.etformUsuario);
        et_form_nombre = (EditText) findViewById(R.id.etformNombre);
        et_form_apellido =(EditText) findViewById(R.id.etformApellido);
        et_form_cedula = (EditText) findViewById(R.id.etformCedula);
        et_form_pass = (EditText) findViewById(R.id.et_form_pass);
        et_conf_pass = (EditText) findViewById(R.id.et_pass_confirmar);
        et_form_apellido = (EditText)findViewById(R.id.etformApellido);
        btn_guardar = (Button) findViewById(R.id.btn_guardar_form_usuario);


        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = et_form_pass.getText().toString();
                String conf_pass = et_conf_pass.getText().toString();
                String cedula = et_form_cedula.getText().toString();
                String nombre = et_form_nombre.getText().toString();
                String apellido = et_form_apellido.getText().toString();
                String usuario = et_form_usuario.getText().toString();

                if (pass.equals(conf_pass)){
                    UsuarioDao obj = new UsuarioDao();
                    Usuario usu = new Usuario(cedula,nombre,apellido,usuario,pass,1,"A");
                    obj.guardar_usuario_admin(usu,FormUsuarioActivity.this);
                    Intent intent = new Intent(FormUsuarioActivity.this, UsuariosActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(FormUsuarioActivity.this,"Las contraseña no coincide con el campo confirmar",Toast.LENGTH_LONG).show();
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
                Intent intent3 = new Intent(FormUsuarioActivity.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_salir:
                finish();
                return true;

            case R.id.it_AgregarReservacion:
                Intent intent4 = new Intent(FormUsuarioActivity.this, FormReservacion.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_agregarUsuario:
                Intent intent5 = new Intent(FormUsuarioActivity.this,FormUsuarioActivity.class);
                startActivity(intent5);
                finish();
                return true;

            case R.id.it_Usuarios:
                Intent intent6 = new Intent(FormUsuarioActivity.this,UsuariosActivity.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_Reservaciones:
                Intent intent7 = new Intent(FormUsuarioActivity.this,PrincipalActivity.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_sincronizar:
                Intent intent8 = new Intent(FormUsuarioActivity.this, Sincronizacion.class);
                startActivity(intent8);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
