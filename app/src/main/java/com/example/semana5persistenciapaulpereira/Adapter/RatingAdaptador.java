package com.example.semana5persistenciapaulpereira.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.semana5persistenciapaulpereira.Entidad2;
import com.example.semana5persistenciapaulpereira.R;

import java.util.ArrayList;
import java.util.List;

public class RatingAdaptador extends RecyclerView.Adapter<RatingAdaptador.Entidad2ViewHolder> {

    private ArrayList<Entidad2> items;

    public static class Entidad2ViewHolder extends RecyclerView.ViewHolder {
        public CardView ratingCardView;
        public ImageView imagen;
        public TextView nombre;
        public TextView rating;

        public Entidad2ViewHolder(View v){
            super(v);
            ratingCardView=(CardView) v.findViewById(R.id.rating_card);
            imagen = (ImageView) v.findViewById(R.id.imgFoto);
            nombre=(TextView) v.findViewById(R.id.tvNombre);
            rating=(TextView) v.findViewById(R.id.tvRating);
        }
    }
    @NonNull
    @Override
    public Entidad2ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rating_card, viewGroup,false);
        return new Entidad2ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Entidad2ViewHolder holder, final int position) {
        holder.imagen.setImageResource(items.get(position).getImgFoto());
        holder.nombre.setText(items.get(position).getNombre());
        holder.rating.setText(Integer.toString(items.get(position).getRating()));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    ;


    public RatingAdaptador(ArrayList<Entidad2> items) {
        this.items = items;
    }

    public ArrayList<Entidad2> getItems(){
        return this.items;

    }
}
