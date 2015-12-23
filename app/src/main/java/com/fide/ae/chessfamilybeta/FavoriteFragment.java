package com.fide.ae.chessfamilybeta;

import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FavoriteFragment extends Fragment {

    private AppCompatActivity activity ;



    private View root ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.favorite_layout, container, false);



        return root ;
    }



}
