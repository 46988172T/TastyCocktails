package com.leosssdroid.tastycocktails.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.leosssdroid.tastycocktails.Adapters.RecipesAdapter;
import com.leosssdroid.tastycocktails.Clases.Recetas;
import com.leosssdroid.tastycocktails.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InicioFragment extends Fragment {

    View viewInicio;
    public static ArrayList<Recetas> listaRecetas = new ArrayList<>();

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference refRecetas = mDatabase.child("recetas");


    @BindView(R.id.recycler_view_inicio)
    RecyclerView recyclerViewInicio;


    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewInicio = inflater.inflate(R.layout.fragment_inicio, container, false);
        ButterKnife.bind(this, viewInicio);
        //recyclerViewInicio = (RecyclerView)viewInicio.findViewById(R.id.recycler_view_inicio);


        return viewInicio;
    }

    private void getRecetas() {

        ValueEventListener postlistener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Recetas recetaSnapshot = postSnapshot.getValue(Recetas.class);
                    listaRecetas.add(recetaSnapshot);
                    System.out.println("Leooooooooooooooooooooooooooooo!!!!!!!---->"+listaRecetas.size());
                }
                recyclerViewInicio.setAdapter(new RecipesAdapter(listaRecetas,getContext()));
                recyclerViewInicio.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        refRecetas.addValueEventListener(postlistener);


    }

    @Override
    public void onStart() {
        super.onStart();
        if(!listaRecetas.isEmpty()) {
            listaRecetas.clear();
        }
        getRecetas();
    }
}
