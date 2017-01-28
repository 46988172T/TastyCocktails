package com.leosssdroid.tastycocktails.Fragments;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.Profile;
import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.frosquivel.magicalcamera.Functionallities.PrivateInformation;
import com.frosquivel.magicalcamera.Functionallities.URIPaths;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kbeanie.multipicker.api.CameraImagePicker;
import com.kbeanie.multipicker.api.ImagePicker;
import com.kbeanie.multipicker.api.Picker;
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback;
import com.kbeanie.multipicker.api.entity.ChosenImage;
import com.leosssdroid.tastycocktails.Clases.Recetas;
import com.leosssdroid.tastycocktails.MainActivity;
import com.leosssdroid.tastycocktails.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddRecetaFragment extends Fragment implements ImagePickerCallback {


    View viewReceta;

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

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference recetasRef = mDatabase.child("recetas");
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://tasty-cocktails.appspot.com");
    StorageReference imagesRef = storageRef.child("images");
    private ImagePicker imagePicker;
    Recetas nuevaReceta;
    Uri uriFile;

    public AddRecetaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewReceta = inflater.inflate(R.layout.fragment_add_receta, container, false);
        //getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ButterKnife.bind(this, viewReceta);
        nuevaReceta = new Recetas();

        cardAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectPicture();
            }
        });




        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (recipeName.getText().toString().equals("") || recipeDescription.getText().toString().equals("") || recipeIngredientes.getText().toString().equals("") || !coctelPhoto.isShown()) {
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
                    StorageReference riversRef = storageRef.child("images/"+uriFile.getLastPathSegment());
                    UploadTask uploadTask = riversRef.putFile(uriFile);

                    // Register observers to listen for when the download is done or if it fails
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            nuevaReceta.setNombre(recipeName.getText().toString());
                            nuevaReceta.setDescripcion(recipeDescription.getText().toString());
                            nuevaReceta.setIngredientes(recipeIngredientes.getText().toString());
                            nuevaReceta.setIdUsuario(Profile.getCurrentProfile().getId());
                            nuevaReceta.setPicture(downloadUrl.toString());
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
                    });
                }
            }
        });

        return viewReceta;
    }

    private void uploadPhoto() {

    }

    private void selectPicture() {
        imagePicker = new ImagePicker(this);
        imagePicker.shouldGenerateMetadata(true);
        imagePicker.shouldGenerateThumbnails(true);
        imagePicker.setImagePickerCallback(this);
        imagePicker.pickImage();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                if (imagePicker == null) {
                    imagePicker = new ImagePicker(this);
                    imagePicker.setImagePickerCallback(this);
                }
                imagePicker.submit(data);

            } else if (requestCode == Picker.PICK_IMAGE_CAMERA) {

            }
        }
        textAddPhoto.setText("");
    }

    @Override
    public void onImagesChosen(List<ChosenImage> list) {
        Glide.with(getContext()).load(list.get(0).getQueryUri()).centerCrop().into(coctelPhoto);
        System.out.println(list.get(0).getThumbnailPath());
        uriFile = Uri.fromFile(new File(list.get(0).getThumbnailPath()));
    }

    @Override
    public void onError(String message) {

    }
}
