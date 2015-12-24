package com.fide.ae.chessfamilybeta;






import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;


import model.Member;
import utils.ChessFamilyUtils;


public class DashboardActivity extends AppCompatActivity {

    private   FrameLayout layout ;
    private ImageButton home_btn,notif_btn,message_btn,favorite_btn ;


    private  Member  member ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_dashboard);

        //SETUP VIEWS

       // this.home_btn = (ImageButton) findViewById(R.id.home_menu_btn);
        this.notif_btn=(ImageButton) findViewById(R.id.notification_menu_btn);
        this.message_btn=(ImageButton) findViewById(R.id.message_menu_btn);
        this.favorite_btn= (ImageButton) findViewById(R.id.favorite_menu_btn);









       Bundle bundle= this.getIntent().getExtras();
        member =(Member) bundle.get("member");
        Log.d("Member", "" + (member == null)) ;
      //  this.addFragment(new GameFragment(),300);
      //  this.addFragment(new GameFragment(),400);



    }

    public void HomeBtn(View v)

    {

        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.add(R.id.dashboard_content, new HomeFragment()).commit();

    }









}
