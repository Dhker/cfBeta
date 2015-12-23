package com.fide.ae.chessfamilybeta;


import android.media.Image;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.readystatesoftware.viewbadger.BadgeView;

import utils.ChessFamilyUtils;


public class Bottom_Menu_Fragment extends Fragment {

private ImageButton home_btn,favorite_btn,message_btn,notification_btn,menu_btn ;
    public Bottom_Menu_Fragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ChessFamilyUtils.ClickEffect(home_btn);
        ChessFamilyUtils.ClickEffect(favorite_btn);

        ChessFamilyUtils.ClickEffect(message_btn);
        ChessFamilyUtils.ClickEffect(menu_btn);

        ChessFamilyUtils.ClickEffect(notification_btn);






    }
   private View rootV ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         rootV = inflater.inflate(R.layout.fragment_bottom__menu, container, false);
        this.message_btn =(ImageButton) rootV.findViewById(R.id.message_btn);
        this.notification_btn =(ImageButton) rootV.findViewById(R.id.notification_btn);
        this.favorite_btn=(ImageButton) rootV.findViewById(R.id.favorite_btn);
        this.menu_btn=(ImageButton)rootV.findViewById(R.id.menu_btn);


    return rootV ;
    }
/*
    public void setNotificationNumber(int i ){


        BadgeView badge = new BadgeView(this.getActivity(), notification);
        badge.setText(String.valueOf(i));
        badge.setBadgePosition(badge.POSITION_TOP_RIGHT);
        badge.setBackgroundResource(R.drawable.cercle);

        badge.show();


    }

    public void setMessageNumber(int i ){


        BadgeView badge = new BadgeView(this.getActivity(), messages);
        badge.setText(String.valueOf(i));
        badge.setBadgePosition(badge.POSITION_TOP_RIGHT);
        badge.setBackgroundResource(R.drawable.cercle);


        badge.show();


    }
*/



}
