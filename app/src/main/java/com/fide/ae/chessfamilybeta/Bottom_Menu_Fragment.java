package com.fide.ae.chessfamilybeta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.readystatesoftware.viewbadger.BadgeView;

import utils.ChessFamilyUtils;
import utils.SessionSotrage;


public class Bottom_Menu_Fragment extends Fragment implements View.OnClickListener {

private ImageButton home_btn,favorite_btn,message_btn,notification_btn,menu_btn ;

    private FrameLayout frameLayout ;
    public Bottom_Menu_Fragment() {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);








    }
   private View rootV ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootV = inflater.inflate(R.layout.fragment_bottom_menu, container, false);
        frameLayout = (FrameLayout)  rootV.findViewById(R.id.notification_button_lay) ;
        this.message_btn =(ImageButton) rootV.findViewById(R.id.message_btn);
        this.notification_btn =(ImageButton) rootV.findViewById(R.id.notification_btn);
        this.favorite_btn=(ImageButton) rootV.findViewById(R.id.favorite_btn);
        this.home_btn =(ImageButton) rootV.findViewById(R.id.home_btn) ;
        //setNotificationNumber() ;
        setMessagesNumber();
        buttonsOnClick() ;
    return rootV ;
    }




    private void buttonsOnClick()
    {

        home_btn.setOnClickListener(this);
        message_btn.setOnClickListener(this);
        favorite_btn.setOnClickListener(this);
        notification_btn.setOnClickListener(this);


    }


    public void setNotificationNumber(){


        BadgeView badge = new BadgeView(this.getActivity(),notification_btn );

        badge.setText(ChessFamilyUtils.getUnreadNotificationNumber(String.valueOf(SessionSotrage.CurrentSessionMember)));
        badge.setBadgePosition(badge.POSITION_TOP_RIGHT);
        badge.setBackgroundResource(R.drawable.cercle);

        badge.show();


    }
    public void setMessagesNumber(){


        BadgeView badge = new BadgeView(this.getActivity(),notification_btn );

        badge.setText(ChessFamilyUtils.getUnreadMessagesNumber());
        badge.setBadgePosition(badge.POSITION_TOP_RIGHT);
        badge.setBackgroundResource(R.drawable.cercle);

        badge.show();


    }


    @Override
    public void onClick(View v) {

        if(v.equals(home_btn))
        {
            Intent intent = new Intent(this.getActivity() , DashboardActivity.class) ;
            intent.putExtra("tabPosition" ,0) ;
            this.startActivity(intent);

        }
        if(v.equals(message_btn))
        {
            Intent intent = new Intent(this.getActivity() , DashboardActivity.class) ;
            intent.putExtra("tabPosition" ,1) ;
            this.startActivity(intent);
        }

        if(v.equals(favorite_btn))
        {
            Intent intent = new Intent(this.getActivity() , DashboardActivity.class) ;
            intent.putExtra("tabPosition" ,2) ;
            this.startActivity(intent);
        }
        if(v.equals(notification_btn))
        {
            Intent intent = new Intent(this.getActivity() , DashboardActivity.class) ;
            intent.putExtra("tabPosition" ,3) ;
            this.startActivity(intent);
        }


    }

    private DrawerLayout drawer ;
    public  void setdrawerMenu(DrawerLayout drawer)
    {
        this.drawer=drawer ;
    }

    public DrawerLayout getDrawer()
    {
        return this.drawer ;
    }
/*
    public void setMessageNumber(int i ){


        BadgeView badge = new BadgeView(this.getActivity(), messages);
        badge.setText(String.valueOf(i));
        badge.setBadgePosition(badge.POSITION_TOP_RIGHT);
        badge.setBackgroundResource(R.drawable.cercle);


        badge.show();


    }
*/



}
