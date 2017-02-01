package com.leosssdroid.tastycocktails;


import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.frosquivel.magicalcamera.Functionallities.PermissionGranted;
import com.leosssdroid.tastycocktails.Fragments.BuscarFragment;
import com.leosssdroid.tastycocktails.Fragments.FavoritosFragment;
import com.leosssdroid.tastycocktails.Fragments.InicioFragment;
import com.leosssdroid.tastycocktails.Fragments.MensajesFragment;
import com.leosssdroid.tastycocktails.Fragments.PerfilFragment;
import com.leosssdroid.tastycocktails.Fragments.AddRecetaFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    MenuItem prevMenuItem;

    InicioFragment inicioFragment;
    BuscarFragment buscarFragment;
    PerfilFragment perfilFragment;
    FavoritosFragment favoritosFragment;
    AddRecetaFragment addRecetaFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);
        getWindow().setStatusBarColor(getResources().getColor(R.color.foreground_material_light));


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_inicioItem:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.menu_buscarItem:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_perfilItem:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.menu_favoritosItem:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.menu_mensajesItem:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                navigation.getMenu().getItem(position).setChecked(true);
                prevMenuItem = navigation.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }


        });
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        inicioFragment=new InicioFragment();
        buscarFragment=new BuscarFragment();
        perfilFragment=new PerfilFragment();
        favoritosFragment=new FavoritosFragment();
        addRecetaFragment=new AddRecetaFragment();

        adapter.addFragment(inicioFragment);
        adapter.addFragment(buscarFragment);
        adapter.addFragment(perfilFragment);
        adapter.addFragment(favoritosFragment);
        adapter.addFragment(addRecetaFragment);
        viewPager.setAdapter(adapter);
    }
}