package com.example.reservaciones.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.reservaciones.Model.Usuario;
import com.example.reservaciones.R;

import java.util.List;

public class UsuarioAdapter extends BaseAdapter {

    Context context;
    List<Usuario> lstUsuario;

    public UsuarioAdapter(Context context, List<Usuario> lstUsuario) {
        this.context = context;
        this.lstUsuario = lstUsuario;
    }

    @Override
    public int getCount() {
        return lstUsuario.size();
    }

    @Override
    public Object getItem(int position) {
        return lstUsuario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.reservacion_filter,null);

        TextView cedula = (TextView)v.findViewById(R.id.cedula_usu_item);
        TextView nombre = (TextView)v.findViewById(R.id.nombre_usu_item);
        TextView apellido = (TextView) v.findViewById(R.id.apellido_usu_item);
        TextView usuario  = (TextView) v.findViewById(R.id.usuario_item);

        cedula.setText(lstUsuario.get(position).getCedula());
        nombre.setText(String.valueOf(lstUsuario.get(position).getNombre()));
        apellido.setText(lstUsuario.get(position).getApellido());
        usuario.setText(lstUsuario.get(position).getUsuario());

        return v;
    }
}
