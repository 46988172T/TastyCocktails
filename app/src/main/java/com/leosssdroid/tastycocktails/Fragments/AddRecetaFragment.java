package com.leosssdroid.tastycocktails.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.leosssdroid.tastycocktails.Clases.Recetas;
import com.leosssdroid.tastycocktails.MainActivity;
import com.leosssdroid.tastycocktails.R;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRecetaFragment extends Fragment {


    View viewReceta;
    MagicalCamera magicalCamera;
    Recetas nuevaReceta;

    @BindView(R.id.card_addphoto)
    CardView cardAddPhoto;
    @BindView(R.id.text_addphoto)
    TextView textAddPhoto;
    @BindView(R.id.coctel_photo)
    ImageView coctelPhoto;
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
        ButterKnife.bind(this, viewReceta);
        nuevaReceta = new Recetas();
        magicalCamera = new MagicalCamera(getActivity(), 3000, MainActivity.permissionGranted);

        cardAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });




        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recipeName.getText().toString().equals("") || recipeDescription.getText().toString().equals("") || recipeIngredientes.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage(R.string.dialog_receta_incompleta)
                            .setTitle(R.string.app_name);
                    builder.setPositiveButton(R.string.entendido, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });
                    builder.show();
                    AlertDialog dialog = builder.create();
                } else {
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

    private void selectPicture() {
        if(magicalCamera.selectedFragmentPicture()){
            startActivityForResult(Intent.createChooser(magicalCamera.getIntentFragment(),  "Elige la foto de tu cóctel"),
                    MagicalCamera.SELECT_PHOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        magicalCamera.resultPhoto(requestCode, resultCode, data);
        coctelPhoto.setImageBitmap(magicalCamera.getPhoto());
        coctelPhoto.setScaleType(ImageView.ScaleType.CENTER_CROP);
        textAddPhoto.setText("");
    }
}
