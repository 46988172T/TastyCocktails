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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leosssdroid.tastycocktails.LoginActivity;
import com.leosssdroid.tastycocktails.MainActivity;
import com.leosssdroid.tastycocktails.R;
import com.leosssdroid.tastycocktails.UserTasty;

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
    CircleImageView profileImage;
    TextView profileName, profileDescription;
    ImageButton ibLogoutFb;

    static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    static DatabaseReference userRef = mDatabase.child("users");
    UserTasty userTastyFromFirebase;
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
        profileDescription = (TextView)viewPerfil.findViewById(R.id.profileDescription);


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

    @Override
    public void onStart() {
        super.onStart();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                profileName.setText(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("name").getValue(String.class));
                profileDescription.setText(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("description").getValue(String.class));

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    UserTasty userSnapshot = postSnapshot.getValue(UserTasty.class);
                    if(userSnapshot.getIdFacebbok().equals(Profile.getCurrentProfile().getId())){
                        userTastyFromFirebase = userSnapshot;
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
