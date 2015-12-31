package com.fide.ae.chessfamilybeta;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;

import utils.ImageSlider;

public class EventActivity extends BaseActivity {

    private SliderLayout slider;
    private ImageSlider imageSlider ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


 */

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_event, null, false);

        drawerLayout.addView(contentView, 0);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.slider = (SliderLayout)findViewById(R.id.slider);


         imageSlider = new ImageSlider(this,slider);
        this.imageSlider.addImage("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        this.imageSlider.addImage("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        this.imageSlider.setupSlider();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return  true ;
    }

    @Override
    protected void onStop() {

        this.imageSlider.onStop();
        super.onStop();

    }
}
