package com.example.reservaciones.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.reservaciones.Model.Reservacion;
import com.example.reservaciones.R;

import java.util.List;

public class ReservacionAdapter extends BaseAdapter {


    Context context;
    List<Reservacion> lstReservacion;

    public ReservacionAdapter(Context context, List<Reservacion> lstReservacion) {
        this.context = context;
        this.lstReservacion = lstReservacion;
    }

    @Override
    public int getCount() {
        return lstReservacion.size();
    }

    @Override
    public Object getItem(int position) {
        return lstReservacion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.reservacion_filter,null);
        TextView nombre = (TextView)v.findViewById(R.id.nombre_item);
        TextView observacion = (TextView) v.findViewById(R.id.observacion_item);
        TextView cantidad_asitencias = (TextView) v.findViewById(R.id.cant_asientos_item);
        TextView reservacion = (TextView) v.findViewById(R.id.reservacion_item);
        nombre.setText(String.valueOf(lstReservacion.get(position).getNombre()));
        observacion.setText(lstReservacion.get(position).getObservacion());
        cantidad_asitencias.setText(lstReservacion.get(position).getCant_asientos());
        reservacion.setText(lstReservacion.get(position).getReservado());
        return v;
    }
}
