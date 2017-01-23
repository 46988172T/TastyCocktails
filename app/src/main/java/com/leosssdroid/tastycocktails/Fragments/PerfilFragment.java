package com.leosssdroid.tastycocktails.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.leosssdroid.tastycocktails.LoginActivity;
import com.leosssdroid.tastycocktails.R;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    View viewPerfil;
    ImageView bgProfile;
    Button salir;
    CircleImageView profileImage;
    TextView profileName, profileDescription;
    ImageButton ibLogoutFb;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        viewPerfil = inflater.inflate(R.layout.fragment_perfil, container, false);
        bgProfile = (ImageView)viewPerfil.findViewById(R.id.bgProfile);
        profileImage = (CircleImageView)viewPerfil.findViewById(R.id.profileImage);
        profileName = (TextView)viewPerfil.findViewById(R.id.profileName);

        profileName.setText(Profile.getCurrentProfile().getName());
        Glide.with(getContext())
                .load(Profile.getCurrentProfile().getProfilePictureUri(256,256).toString())
                .into(profileImage);

        Glide.with(getContext())
                .load(Profile.getCurrentProfile().getProfilePictureUri(512,512).toString())
                //.centerCrop()
                .bitmapTransform(new GrayscaleTransformation(getContext()),
                        new CenterCrop(getContext()),
                        new BlurTransformation(getContext(),10)

                )
                .into(bgProfile);
        ibLogoutFb = (ImageButton)viewPerfil.findViewById(R.id.ibLogoutFb);
        ibLogoutFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        return viewPerfil;
    }

    private void goLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginActivity();
    }

}
