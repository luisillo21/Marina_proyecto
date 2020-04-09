package com.example.reservaciones.Activities.Activities.ActivitiesCliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.reservaciones.Activities.Activities.MainActivity;
import com.example.reservaciones.Dao.UsuarioDao;
import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;

public class FormUsuarioClienteActivity extends AppCompatActivity {
    EditText etCedula,etNombre,etApellido,etUsu,etContra,etPassConf;
    Button btnGuardarCliente;
    UsuarioDao crud;
    Usuario obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_usuario_cliente);

        etCedula = (EditText) findViewById(R.id.etCedula);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etUsu = (EditText) findViewById(R.id.etUsu);
        etContra = (EditText) findViewById(R.id.etContra);
        etPassConf = (EditText) findViewById(R.id.etPassConf);

        btnGuardarCliente = (Button) findViewById(R.id.btnGuardarCliente);

        btnGuardarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCedula.getText().toString().isEmpty() ||
                        etNombre.getText().toString().isEmpty() ||
                        etApellido.getText().toString().isEmpty() ||
                        etUsu.getText().toString().isEmpty() ||
                        etContra.getText().toString().isEmpty() ||
                        etPassConf.getText().toString().isEmpty()){
                        Toast.makeText(FormUsuarioClienteActivity.this, "Debes completar todos los campos", Toast.LENGTH_SHORT).show();
            }else{
                    crud = new UsuarioDao();
                    obj = new Usuario();
                    obj.setCedula(etCedula.getText().toString());
                    obj.setNombre(etNombre.getText().toString());
                    obj.setApellido(etApellido.getText().toString());
                    obj.setUsuario(etUsu.getText().toString());
                    obj.setClave(etContra.getText().toString());
                    boolean resultado = crud.guardar_usuario_cliente(obj,FormUsuarioClienteActivity.this);
                    if(resultado){
                        Intent intent = new Intent(FormUsuarioClienteActivity.this, MainActivity.class);
                        Toast.makeText(FormUsuarioClienteActivity.this, "Guardado"+resultado, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(FormUsuarioClienteActivity.this, "Error en la consulta", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });



    }
}
