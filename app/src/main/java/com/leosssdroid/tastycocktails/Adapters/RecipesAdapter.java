package com.leosssdroid.tastycocktails.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leosssdroid.tastycocktails.Clases.Recetas;
import com.leosssdroid.tastycocktails.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leosss on 30/01/2017.
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {
    ArrayList<Recetas> recetas;
    Context context;

    public RecipesAdapter(@NonNull ArrayList<Recetas> recetas, Context context){
        this.recetas = recetas;
        this.context = context;
    }

    @Override
    public RecipesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);
        return new RecipesViewHolder(row);
    }

    @Override
    public void onBindViewHolder(RecipesViewHolder holder, int position) {
        Recetas rec = recetas.get(position);
        Glide.with(context).load(rec.getPicture()).centerCrop().into(holder.imagen_receta);
        holder.titulo.setText(rec.getNombre());
        holder.nombre.setText("Subido por "+rec.getIdUsuario());
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }


    class RecipesViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen_receta;
        private TextView titulo, nombre;

        public RecipesViewHolder(View itemView) {
            super(itemView);
            imagen_receta = (ImageView) itemView.findViewById(R.id.imagen_coctel_card);
            titulo = (TextView) itemView.findViewById(R.id.titulo_card);
            nombre = (TextView) itemView.findViewById(R.id.nombre_user_card);
        }

        public ImageView getImagen_receta() {
            return imagen_receta;
        }

        public TextView getTitulo() {
            return titulo;
        }

        public TextView getNombre() {
            return nombre;
        }
    }
}