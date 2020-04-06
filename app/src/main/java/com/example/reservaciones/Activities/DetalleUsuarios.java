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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;

public class DetalleUsuarios extends AppCompatActivity {
    EditText nombre,cedula,apellido,usuario,pass,confPass;
    Button btnEditar,btnEliminar;
    Usuario obj_usuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuarios);
        final Bundle objeto = getIntent().getExtras();
        Reservacion r  = new Reservacion();

        obj_usuario = new Usuario();

        if (objeto != null){
            obj_usuario = (Usuario) objeto.getSerializable("usuarios");
        }

        nombre =(EditText) findViewById(R.id.etformNombre);
        cedula =(EditText) findViewById(R.id.editCedula);
        apellido = (EditText) findViewById(R.id.editApellido);
        usuario = (EditText) findViewById(R.id.editUsuario);
        pass = (EditText) findViewById(R.id.edit_form_pass);
        confPass =  (EditText) findViewById(R.id.edit_pass_confirmar);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDao obj = new UsuarioDao();
                String var_cedula = cedula.getText().toString();
                obj.Eliminar_usuario(var_cedula,DetalleUsuarios.this);Intent intent = new Intent(DetalleUsuarios.this,UsuariosActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String var_1 = nombre.getText().toString();
                String var_2 = cedula.getText().toString();
                String var_3 = apellido.getText().toString();
                String var_4 = usuario.getText().toString();
                String var_5 = pass.getText().toString();
                String var_6 = confPass.getText().toString();
                UsuarioDao objDao = new UsuarioDao();
                if (var_5.equals(var_6)){
                    Usuario editObj = new Usuario();
                    editObj.setCedula(obj_usuario.getCedula());
                    editObj.setNombre(var_1);
                    editObj.setCedula(var_2);
                    editObj.setUsuario(var_4);
                    editObj.setClave(var_5);
                    objDao.update_usuario(editObj,DetalleUsuarios.this);
                    Intent intent = new Intent(DetalleUsuarios.this,UsuariosActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(DetalleUsuarios.this,"La contraseña no coincide con la confirmacion",Toast.LENGTH_LONG).show();
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
                Intent intent3 = new Intent(DetalleUsuarios.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_salir:
                finish();
                return true;

            case R.id.it_AgregarReservacion:
                Intent intent4 = new Intent(DetalleUsuarios.this,FormReservacion.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_agregarUsuario:
                Intent intent5 = new Intent(DetalleUsuarios.this,FormUsuarioActivity.class);
                startActivity(intent5);
                finish();
                return true;

            case R.id.it_Usuarios:
                Intent intent6 = new Intent(DetalleUsuarios.this,UsuariosActivity.class);
                startActivity(intent6);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
