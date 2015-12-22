package com.fide.ae.chessfamilybeta;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        text= (TextView) findViewById(R.id.privacy);
        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    text.setTextColor(getResources().getColor(R.color.colorTextEditProfil2));

                }
                return true;
            }
        });
    }




    public void setUpPrivacy(View view){


        text.setTextColor(getResources().getColor(R.color.colorTextEditProfil1));






    }
    private   TextView text ;

    public void setUpFaq(View view){
      final  TextView text= (TextView) findViewById(R.id.FAQ);
        text.setTextColor(getResources().getColor(R.color.colorTextEditProfil1));


        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    text.setTextColor(getResources().getColor(R.color.colorTextEditProfil2));

                }
                return true;
            }
        });


    }
    public void setUpGames(View view){
      final  TextView text= (TextView) findViewById(R.id.MyGame);
        text.setTextColor(getResources().getColor(R.color.colorTextEditProfil1));


        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    text.setTextColor(getResources().getColor(R.color.colorTextEditProfil2));

                }
                return true;
            }
        });


    }
    public void setUpEvents(View view){

      final  TextView text= (TextView) findViewById(R.id.event);
        text.setTextColor(getResources().getColor(R.color.colorTextEditProfil1));


        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    text.setTextColor(getResources().getColor(R.color.colorTextEditProfil2));

                }
                return true;
            }
        });

    }

    public void setUpSetting(View view){
      final   TextView text= (TextView) findViewById(R.id.Setting);
        text.setTextColor(getResources().getColor(R.color.colorTextEditProfil1));

        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction()==MotionEvent.ACTION_UP)
                {
                    text.setTextColor(getResources().getColor(R.color.colorTextEditProfil2));

                }
                    return true ;
            }
        });


    }
    public void setUpProfile(View view){

       final TextView text= (TextView) findViewById(R.id.profil);
        text.setTextColor(getResources().getColor(R.color.colorTextEditProfil1));


        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    text.setTextColor(getResources().getColor(R.color.colorTextEditProfil2));

                }
                return true;
            }
        });

    }
    public void setUpLogout(View view){
      final  TextView text= (TextView) findViewById(R.id.Logout);
        text.setTextColor(getResources().getColor(R.color.colorTextEditProfil1));


        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    text.setTextColor(getResources().getColor(R.color.colorTextEditProfil2));

                }
                return true;
            }
        });



    }



}
