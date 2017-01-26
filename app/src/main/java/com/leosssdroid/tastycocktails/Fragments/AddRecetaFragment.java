package com.leosssdroid.tastycocktails.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leosssdroid.tastycocktails.Clases.Recetas;
import com.leosssdroid.tastycocktails.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRecetaFragment extends Fragment {

    View viewReceta;
    Recetas nuevaReceta;

    @BindView(R.id.recipe_name_text)
    EditText recipeName;
    @BindView(R.id.recipe_description_text)
    EditText recipeDescription;
    @BindView(R.id.recipe_ingredientes_text)
    EditText recipeIngredientes;
    @BindView(R.id.upload_button)
    Button uploadButton;

    //String nombre, descripcion, ingredientes;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference recetasRef = mDatabase.child("recetas");

    public AddRecetaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewReceta = inflater.inflate(R.layout.fragment_add_receta, container, false);
        ButterKnife.bind(this,viewReceta);
        nuevaReceta = new Recetas();

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(recipeName.getText().toString().equals("")||recipeDescription.getText().toString().equals("")||recipeIngredientes.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.dialog_receta_incompleta)
                            .setTitle(R.string.app_name);
                    builder.setPositiveButton(R.string.entendido, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                    AlertDialog dialog = builder.create();
                }else{
                    nuevaReceta.setNombre(recipeName.getText().toString());
                    nuevaReceta.setDescripcion(recipeDescription.getText().toString());
                    nuevaReceta.setIngredientes(recipeIngredientes.getText().toString());
                    recetasRef.push().setValue(nuevaReceta);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.dialog_add_receta)
                            .setTitle(R.string.app_name);
                    builder.setPositiveButton(R.string.genial, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            recipeName.setText("");
                            recipeDescription.setText("");
                            recipeIngredientes.setText("");
                        }
                    });
                    builder.show();
                    AlertDialog dialog = builder.create();
                }
            }
        });








        return viewReceta;
    }

}
