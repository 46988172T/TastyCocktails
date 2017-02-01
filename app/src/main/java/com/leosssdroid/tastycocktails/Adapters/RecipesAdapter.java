package com.leosssdroid.tastycocktails.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leosssdroid.tastycocktails.Clases.Recetas;
import com.leosssdroid.tastycocktails.Clases.UserTasty;
import com.leosssdroid.tastycocktails.DetailRecetas;
import com.leosssdroid.tastycocktails.R;
import com.varunest.sparkbutton.SparkButton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
    public void onBindViewHolder(final RecipesViewHolder holder, int position) {
        final Recetas rec = recetas.get(position);

        Glide.with(context).load(rec.getPicture()).centerCrop().into(holder.imagen_receta);
        final DatabaseReference refNombre = FirebaseDatabase.getInstance().getReference().child("users").child(rec.getIdUsuario());
        refNombre.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserTasty utasty = dataSnapshot.getValue(UserTasty.class);
                Glide.with(context).load(utasty.getPicture()).centerCrop().into(holder.userPic);
                holder.nombre.setText("Subido por "+utasty.getName());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        holder.titulo.setText(rec.getNombre());
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }


    class RecipesViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagen_receta;
        private CircleImageView userPic;
        private TextView titulo, nombre;

        public RecipesViewHolder(final View itemView) {
            super(itemView);
            imagen_receta = (ImageView) itemView.findViewById(R.id.imagen_coctel_card);
            userPic = (CircleImageView) itemView.findViewById(R.id.profileImageRecetaCard);
            titulo = (TextView) itemView.findViewById(R.id.titulo_card);
            nombre = (TextView) itemView.findViewById(R.id.nombre_user_card);
            userPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Gestionar onClick para ir al perfil del usuario que ha subido la receta

                    Toast.makeText(context, "Abrir perfil del usuario que ha subido receta", Toast.LENGTH_SHORT).show();
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int item = getAdapterPosition();
                    Recetas receta = recetas.get(item);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Objeto", receta);
                    Intent intent = new Intent(context,DetailRecetas.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                    //gestionar el onClick para ver el detalle de la receta
                    Toast.makeText(context, "Abrir Receta", Toast.LENGTH_LONG).show();
                }
            });
        }

        public CircleImageView getUserPic() {
            return userPic;
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