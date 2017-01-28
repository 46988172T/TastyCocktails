package com.leosssdroid.tastycocktails;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.leosssdroid.tastycocktails.Fragments.BuscarFragment;
import com.leosssdroid.tastycocktails.Fragments.FavoritosFragment;
import com.leosssdroid.tastycocktails.Fragments.InicioFragment;
import com.leosssdroid.tastycocktails.Fragments.PerfilFragment;
import com.leosssdroid.tastycocktails.Fragments.AddRecetaFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation) AHBottomNavigation navigation;
    public static PermissionGranted permissionGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorInicioBase));
        createItemsBottomNavigation();
        permissionGranted = new PermissionGranted(this);
        if (android.os.Build.VERSION.SDK_INT >= 25) {
            permissionGranted.checkAllMagicalCameraPermission();
        }else{
            permissionGranted.checkCameraPermission();
            permissionGranted.checkReadExternalPermission();
            permissionGranted.checkWriteExternalPermission();
            permissionGranted.checkLocationPermission();
        }
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
                        AddRecetaFragment mensajesFragment = new AddRecetaFragment();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        permissionGranted.permissionGrant(requestCode, permissions, grantResults);
    }




}