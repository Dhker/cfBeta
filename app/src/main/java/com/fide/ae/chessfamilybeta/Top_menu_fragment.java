package com.fide.ae.chessfamilybeta;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.Toast;

import utils.ChessFamilyUtils;
import utils.GPSLocalisation;


public class Top_menu_fragment extends Fragment {

    private View root ;
    private ImageButton back_btn,search_btn,positon_btn;
    private Fragment current ;


    public Top_menu_fragment() {

        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.current=this ;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root=inflater.inflate(R.layout.fragment_top_menu, container, false);
        this.back_btn=(ImageButton)root.getRootView().findViewById(R.id.top_menu_back_btn);
        this.positon_btn=(ImageButton)root.getRootView().findViewById(R.id.top_menu_location);
        this.search_btn=(ImageButton)root.getRootView().findViewById(R.id.search_top_menu);

        if (getActivity().getLocalClassName().equals("DashboardActivity"))
        {
            back_btn.setImageResource(R.drawable.ic_logout);

        }
//BUTTON RESEARCH
        this.search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    startActivity(new Intent(getActivity(), SearchActivity.class));



            }
        });

//BUTTON BACK

        this.back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(getActivity());
                if (getActivity().getLocalClassName().equals("DashboardActivity"))
                {
                   startActivity(new Intent(getActivity(),LoginActivity.class));


                }


            }
        });


        //Button Refresh Position
        this.positon_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GPSLocalisation gps = new GPSLocalisation((AppCompatActivity)current.getActivity());
                Log.d("bgiiiiir", gps.getLatitude());
                Toast.makeText(current.getActivity().getApplicationContext(), "Longitude : "+gps.getLongitude()+"\nLongitude : "+gps.getLatitude(), Toast.LENGTH_SHORT)
                   .show();

            }
        });

        // Inflate the layout for this fragment
        return root;
    }




}
