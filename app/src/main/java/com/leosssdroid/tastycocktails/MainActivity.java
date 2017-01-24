package com.leosssdroid.tastycocktails;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.facebook.Profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leosssdroid.tastycocktails.Fragments.BuscarFragment;
import com.leosssdroid.tastycocktails.Fragments.FavoritosFragment;
import com.leosssdroid.tastycocktails.Fragments.InicioFragment;
import com.leosssdroid.tastycocktails.Fragments.MensajesFragment;
import com.leosssdroid.tastycocktails.Fragments.PerfilFragment;
import com.leosssdroid.tastycocktails.Fragments.RecetaFragment;

public class MainActivity extends AppCompatActivity {

    private AHBottomNavigation navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorInicioBase));
        navigation = (AHBottomNavigation)findViewById(R.id.navigation);
        createItemsBottomNavigation();
        navigation = (AHBottomNavigation)findViewById(R.id.navigation);

        //Inicio
        InicioFragment inicioFragment = new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, inicioFragment).commit();
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorInicioBase));
        navigation.setAccentColor(Color.parseColor("#C7DAA3"));
        navigation.setInactiveColor(Color.parseColor("#576047"));

        navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch(position){
                    case 0:
                        InicioFragment inicioFragment = new InicioFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, inicioFragment).commit();
                        getWindow().setStatusBarColor(getResources().getColor(R.color.colorInicioBase));
                        navigation.setAccentColor(Color.parseColor("#C7DAA3"));
                        navigation.setInactiveColor(Color.parseColor("#576047"));
                        break;
                    case 1:
                        BuscarFragment buscarFragment = new BuscarFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, buscarFragment).commit();
                        getWindow().setStatusBarColor(getResources().getColor(R.color.colorBuscarBase));
                        navigation.setAccentColor(Color.parseColor("#d0d4c5"));
                        navigation.setInactiveColor(Color.parseColor("#53544E"));
                        break;
                    case 2:
                        PerfilFragment perfilFragment = new PerfilFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, perfilFragment).commit();
                        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPerfilBase));
                        navigation.setAccentColor(Color.parseColor("#E6D3E3"));
                        navigation.setInactiveColor(Color.parseColor("#7F3E77"));
                        break;
                    case 3:
                        FavoritosFragment favoritosFragment = new FavoritosFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, favoritosFragment).commit();
                        getWindow().setStatusBarColor(getResources().getColor(R.color.colorFavoritosBase));
                        navigation.setAccentColor(Color.parseColor("#d3d6fa"));
                        navigation.setInactiveColor(Color.parseColor("#32356c"));
                        break;
                    case 4:
                        RecetaFragment mensajesFragment = new RecetaFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.content_main, mensajesFragment).commit();
                        getWindow().setStatusBarColor(getResources().getColor(R.color.colorMensajesBase));
                        navigation.setAccentColor(Color.parseColor("#fccba7"));
                        navigation.setInactiveColor(Color.parseColor("#895934"));
                        break;
                }
                return true;
            }
        });
    }


    private void createItemsBottomNavigation() {
        AHBottomNavigationItem inicio = new AHBottomNavigationItem(R.string.inicio, R.drawable.ic_home, R.color.colorInicioBase);
        AHBottomNavigationItem buscar = new AHBottomNavigationItem(R.string.buscar, R.drawable.ic_search, R.color.colorBuscarBase);
        AHBottomNavigationItem perfil = new AHBottomNavigationItem(R.string.perfil, R.drawable.ic_profile, R.color.colorPerfilBase);
        AHBottomNavigationItem favoritos = new AHBottomNavigationItem(R.string.favoritos, R.drawable.ic_favorite, R.color.colorFavoritosBase);
        AHBottomNavigationItem mensajes = new AHBottomNavigationItem(R.string.mensajes, R.drawable.ic_chat, R.color.colorMensajesBase);

        navigation.addItem(inicio);
        navigation.addItem(buscar);
        navigation.addItem(perfil);
        navigation.addItem(favoritos);
        navigation.addItem(mensajes);
        navigation.setCurrentItem(0);
        navigation.setBehaviorTranslationEnabled(false);
        navigation.setTitleState(AHBottomNavigation.TitleState.SHOW_WHEN_ACTIVE);
        navigation.setColored(true);
        navigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        navigation.setNotification("1", 4);

    }




}