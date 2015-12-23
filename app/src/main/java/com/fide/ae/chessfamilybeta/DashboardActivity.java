package com.fide.ae.chessfamilybeta;



import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;


import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import model.Member;


public class DashboardActivity extends AppCompatActivity {


    //UI components
    private   FrameLayout layout ;
    private   ImageButton homeButton  ;
    private   ImageButton favoriteButton ;
    private   ImageButton messageButton ;
    private   ImageButton notificationButton ;


    //Preferences for storing user information
    private SharedPreferences userPreferences;
    private SharedPreferences.Editor userPrefsEditor;



    private  Member  user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_dashboard);


        //UI components intilialization
        messageButton = (ImageButton) findViewById(R.id.message_menu_btn) ;
        homeButton = (ImageButton) findViewById(R.id.home_menu_btn) ;
        favoriteButton =(ImageButton) findViewById(R.id.favorite_menu_btn) ;
        notificationButton =(ImageButton) findViewById(R.id.notification_menu_btn);


        // set OnClick listener for menu buttons
         menuButtonsOnclickListener();






       Bundle bundle= this.getIntent().getExtras();
        user =(Member) bundle.get("member");
        Log.d("Member" ,""+(user==null)) ;
      //  this.addFragment(new GameFragment(),300);
      //  this.addFragment(new GameFragment(),400);



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);



        return true;
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(DashboardActivity.this,SearchActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // set on click listener for each Menu Button
    private void menuButtonsOnclickListener()
    {
        homeButtonOnclickListener();
        messageButtonOnClickListener();
        favoriteButtonOnClickListener();
        notificationButtonOnClickListener();
    }

    // set on click  listener for home button
    private void homeButtonOnclickListener()
    {

    }
    // set on click listener for message button
    private void messageButtonOnClickListener()
    {

    }
    // set on click listener for favorite button
    private void favoriteButtonOnClickListener(){

    }
    // set on click listener for notification button
    private void notificationButtonOnClickListener(){

    }

}
