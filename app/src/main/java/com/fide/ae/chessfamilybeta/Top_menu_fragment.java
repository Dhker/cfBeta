package com.fide.ae.chessfamilybeta;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import utils.ChessFamilyUtils;


public class Top_menu_fragment extends Fragment {

    private View root ;
    private ImageButton back_btn,search_btn,positon_btn;


    public Top_menu_fragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root=inflater.inflate(R.layout.fragment_top_menu, container, false);
        this.back_btn=(ImageButton)root.getRootView().findViewById(R.id.top_menu_back_btn);
        this.positon_btn=(ImageButton)root.getRootView().findViewById(R.id.top_menu_location);
        this.search_btn=(ImageButton)root.getRootView().findViewById(R.id.search_top_menu);
        this.setUpListener();

        ChessFamilyUtils.ClickEffect(back_btn);
        ChessFamilyUtils.ClickEffect(positon_btn);
        ChessFamilyUtils.ClickEffect(search_btn);
        // Inflate the layout for this fragment
        return root;
    }

    private void setUpListener()
    {

        this.search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupmenu= new PopupMenu(Top_menu_fragment.this.getActivity(),search_btn);
                popupmenu.getMenuInflater().inflate(R.menu.search_menu, popupmenu.getMenu());
                popupmenu.show();
            }
        });

    }



}
