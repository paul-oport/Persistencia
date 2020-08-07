package com.example.semana5persistenciapaulpereira.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.semana5persistenciapaulpereira.Entidad;
import com.example.semana5persistenciapaulpereira.R;


import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<Entidad> listItems;

    public Adaptador(Context context, ArrayList<Entidad> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Entidad Item = (Entidad) getItem(position);


        convertView=LayoutInflater.from(context).inflate(R.layout.item,null);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.tvNombre);
        TextView tvRating = (TextView) convertView.findViewById(R.id.tvRating);

        imgFoto.setImageResource(Item.getImgFoto());
        tvNombre.setText(Item.getNombre());
        tvRating.setText(Integer.toString(Item.getRating()));

        return convertView;
    }
}

