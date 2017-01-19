package com.leosssdroid.tastycocktails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private TextView tvPrueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvPrueba = (TextView)findViewById(R.id.tvPrueba);

        navigation = (BottomNavigationView)findViewById(R.id.navigation);
        /**navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.inicioItem){
                    tvPrueba.setText(R.string.inicio);
                }else if(item.getItemId() == R.id.perfilItem){
                    tvPrueba.setText(R.string.perfil);
                }else if(item.getItemId() == R.id.favoritosItem){
                    tvPrueba.setText(R.string.favoritos);
                }else if(item.getItemId() == R.id.mensajesItem){
                    tvPrueba.setText(R.string.mensajes);
                }

                return false;
            }
        });*/

    }

    private void goLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginActivity();
    }

}