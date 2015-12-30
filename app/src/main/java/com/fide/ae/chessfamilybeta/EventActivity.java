package com.fide.ae.chessfamilybeta;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.daimajia.slider.library.SliderLayout;

import utils.ImageSlider;

public class EventActivity extends AppCompatActivity {

    private SliderLayout slider;
    private ImageSlider imageSlider ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        this.slider = (SliderLayout)findViewById(R.id.slider);


         imageSlider = new ImageSlider(this,slider);
        this.imageSlider.addImage("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        this.imageSlider.addImage("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        this.imageSlider.setupSlider();

    }


    @Override
    protected void onStop() {

        this.imageSlider.onStop();
        super.onStop();

    }
}
