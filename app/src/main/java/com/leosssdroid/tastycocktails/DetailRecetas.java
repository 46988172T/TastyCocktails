package com.leosssdroid.tastycocktails;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.leosssdroid.tastycocktails.Clases.Recetas;
import com.like.LikeButton;
import com.like.OnLikeListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailRecetas extends AppCompatActivity {

    @BindView (R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.titulo_detail)
    TextView titulo;
    @BindView(R.id.imagen_detail)
    ImageView imagen;
    @BindView(R.id.descripcion_titulo_content)
    TextView descripcion;
    @BindView(R.id.ingredientes_titulo_content)
    TextView ingredientes;
    @BindView(R.id.numberOfLikes)
    TextView numberOfLikesDetail;
    @BindView(R.id.likeButton)
    LikeButton likeButton;

    boolean isEnabled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_recetas);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorInicioBase));

        Bundle bundle = getIntent().getExtras();
        Recetas recetaFromBundle = (Recetas) bundle.getSerializable("Objeto");
        titulo.setText(recetaFromBundle.getNombre());
        Glide.with(this).load(recetaFromBundle.getPicture()).centerCrop().into(imagen);
        descripcion.setText(recetaFromBundle.getDescripcion());
        ingredientes.setText(recetaFromBundle.getIngredientes());

        //TODO implementar si usuario le ha dado like a la receta mirando el bundle que le pasamos

        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                //TODO implementar qué pasa cuando le damos like
                likeButton.setEnabled(true);
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                //TODO implementar qué pasa cuando le damos unlike
                likeButton.setEnabled(false);
            }
        });

    }

}
