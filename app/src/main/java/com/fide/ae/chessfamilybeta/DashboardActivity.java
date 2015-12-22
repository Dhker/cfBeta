package com.fide.ae.chessfamilybeta;



import android.content.Intent;
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
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import model.Member;


public class DashboardActivity extends AppCompatActivity {

    private   FrameLayout layout ;


    private  Member  member ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_dashboard);








       Bundle bundle= this.getIntent().getExtras();
        member =(Member) bundle.get("member");
        Log.d("Member" ,""+(member==null)) ;
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


int top =10;



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


}
