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
import com.example.reservaciones.Activities.Sincronizacion;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;

public class DetalleUsuarios extends AppCompatActivity {
    EditText nombre,apellido,usuario,pass,confPass;
    Button btnEditar,btnEliminar;
    Usuario obj_usuario;
    Bundle objeto;
    TextView cedula;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuarios);

        final Bundle objeto = getIntent().getExtras();
        obj_usuario = new Usuario();

        if (objeto != null){
            obj_usuario = (Usuario) objeto.getSerializable("usuario");
            Log.e("ERROR DE SERIALIZER","SERIALIZER"+obj_usuario.getNombre());
        }
        btnEditar = (Button)findViewById(R.id.btn_editar_usuario);
        btnEliminar =(Button)findViewById(R.id.btn_eliminar_usuario);
        nombre =(EditText) findViewById(R.id.editNombre);
        cedula =(TextView) findViewById(R.id.editCedula);
        apellido = (EditText) findViewById(R.id.editApellido);
        usuario = (EditText) findViewById(R.id.editUsuario);
        pass = (EditText) findViewById(R.id.edit_form_pass);
        confPass =  (EditText) findViewById(R.id.edit_pass_confirmar);

        nombre.setText(obj_usuario.getNombre());
        cedula.setText(obj_usuario.getCedula());
        apellido.setText(obj_usuario.getApellido());
        usuario.setText(obj_usuario.getUsuario());
        pass.setText(obj_usuario.getClave());

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UsuarioDao obj = new UsuarioDao();
                String var_cedula = cedula.getText().toString();
                obj.Eliminar_usuario(var_cedula,DetalleUsuarios.this);Intent intent = new Intent(DetalleUsuarios.this, UsuariosActivity.class);
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
                    editObj.setCedula(var_2);
                    editObj.setNombre(var_1);
                    editObj.setApellido(var_3);
                    editObj.setUsuario(var_4);
                    editObj.setClave(var_5);
                    editObj.setEstado("A");
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
                Intent intent3 = new Intent(DetalleUsuarios.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_salir:
                finish();
                return true;

            case R.id.it_AgregarReservacion:
                Intent intent4 = new Intent(DetalleUsuarios.this, FormReservacion.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_agregarUsuario:
                Intent intent5 = new Intent(DetalleUsuarios.this, FormUsuarioActivity.class);
                startActivity(intent5);
                finish();
                return true;

            case R.id.it_Usuarios:
                Intent intent6 = new Intent(DetalleUsuarios.this,UsuariosActivity.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_Reservaciones:
                Intent intent7 = new Intent(DetalleUsuarios.this,PrincipalActivity.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_sincronizar:
                Intent intent8 = new Intent(DetalleUsuarios.this, Sincronizacion.class);
                startActivity(intent8);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}
