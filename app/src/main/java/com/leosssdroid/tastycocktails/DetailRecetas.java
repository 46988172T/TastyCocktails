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

    }

}
