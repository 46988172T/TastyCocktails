package com.leosssdroid.tastycocktails.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.leosssdroid.tastycocktails.LoginActivity;
import com.leosssdroid.tastycocktails.R;
import com.leosssdroid.tastycocktails.Clases.UserTasty;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    View viewPerfil;

    @BindView(R.id.bgProfile) ImageView bgProfile;
    @BindView(R.id.profileImage) CircleImageView profileImage;
    @BindView(R.id.profileName) TextView profileName;
    @BindView(R.id.profileDescription) TextView profileDescription;
    @BindView(R.id.followers) TextView followers;
    @BindView(R.id.recipes) TextView recetas;
    @BindView(R.id.following) TextView following;
    @BindView(R.id.ibLogoutFb) ImageButton ibLogoutFb;

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
        ButterKnife.bind(this,viewPerfil);
            if(Profile.getCurrentProfile() != null) {
                Glide.with(getContext())
                        .load(Profile.getCurrentProfile().getProfilePictureUri(256, 256).toString())
                        .into(profileImage);

                Glide.with(getContext())
                        .load(Profile.getCurrentProfile().getProfilePictureUri(512, 512).toString())
                        //.centerCrop()
                        .bitmapTransform(new GrayscaleTransformation(getContext()),
                                new CenterCrop(getContext()),
                                new BlurTransformation(getContext(), 10)

                        )
                        .into(bgProfile);
                //ibLogoutFb = (ImageButton) viewPerfil.findViewById(R.id.ibLogoutFb);
                ibLogoutFb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage(R.string.dialog_message)
                                .setTitle(R.string.dialog_title);
                        builder.setPositiveButton(R.string.salir, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                logout();
                            }
                        });
                        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                        builder.show();
                        AlertDialog dialog = builder.create();


                    }
                });
            }else{
                goLoginActivity();
            }

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

                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    GenericTypeIndicator<UserTasty> genericTypeIndicator = new GenericTypeIndicator<UserTasty>() {};
                    UserTasty userSnapshot = postSnapshot.getValue(genericTypeIndicator);
                    if(userSnapshot.getIdFacebbok().equals(Profile.getCurrentProfile().getId())){
                        userTastyFromFirebase = userSnapshot;

                        break;
                    }
                }

                profileName.setText(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("name").getValue(String.class));
                profileDescription.setText(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("description").getValue(String.class));

                //Rellenamos el textView de los followers verificando cuales estan a true (string) porque si nos dejan de seguir puede estar a false.
                if(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("followers").exists()){
                    int seguidores = 0;
                    Map<String, String> followersMap;
                    followersMap = userTastyFromFirebase.getFollowers();
                    for(Map.Entry<String,String> e: followersMap.entrySet()) {
                        if(e.getValue().equals("true")){
                            seguidores += 1;
                        }
                    }
                    followers.setText(String.valueOf(seguidores));
                }else{
                    followers.setText("0");
                }

                //Rellenamos el textView de los following verificando cuales estan a true (string) porque si nos dejan de seguir puede estar a false.
                if(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("followers").exists()){
                    int seguidores = 0;
                    Map<String, String> followingMap;
                    followingMap = userTastyFromFirebase.getFollowing();
                    for(Map.Entry<String,String> e: followingMap.entrySet()) {
                        if(e.getValue().equals("true")){
                            seguidores += 1;
                        }
                    }
                    following.setText(String.valueOf(seguidores));
                }else{
                    following.setText("0");
                }



                //Rellenamos el textView de las recetas simplemente contando cuantas tenemos.
                if(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("recipes").exists()) {
                    recetas.setText(String.valueOf(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("recipes").getChildrenCount()));
                }else{
                    recetas.setText("0");
                }

                /*if(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("favs").exists()) {
                    favs.setText(String.valueOf(dataSnapshot.child(Profile.getCurrentProfile().getId()).child("favs").getChildrenCount()));
                }else{
                    favs.setText("0");
                }*/


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("ERRRRRROOOOOOORRRRR");
            }
        });
    }
}
