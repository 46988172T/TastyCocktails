package com.leosssdroid.tastycocktails.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.fbui.textlayoutbuilder.TextLayoutBuilder;
import com.leosssdroid.tastycocktails.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecetaFragment extends Fragment {

    View viewReceta;

    public RecetaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewReceta = inflater.inflate(R.layout.fragment_receta, container, false);


        return viewReceta;
    }

}
