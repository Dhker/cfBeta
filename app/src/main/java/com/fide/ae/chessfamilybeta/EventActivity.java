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
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;

import java.util.Iterator;

import model.Event;
import utils.ImageSlider;

public class EventActivity extends BaseActivity {

    private SliderLayout slider;
    private ImageSlider imageSlider ;
    private TextView startDate,endDate ,organizedby,eventname,website,phone,email,description,adresse;

    private Event event ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);


 */
        this.event=(Event)this.getIntent().getExtras().get("event");

        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_event, null, false);

        drawerLayout.addView(contentView, 0);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }

    private void initViews()
    {
        //ADRESSE
        this.adresse=(TextView)findViewById(R.id.adresse);
        if(this.event.getPlace().getAdress()!=null)
            this.adresse.setText(event.getPlace().getAdress());
        else
            this.adresse.setText(getResources().getString(R.string.no_adresse));

        //description

        this.description=(TextView)findViewById(R.id.description_text);

        if(event.getDescription()!=null)
        {
            this.description.setText(event.getDescription());
        }


        //CONTACT

        this.email=(TextView)findViewById(R.id.email_contact);
        if(this.event.getPlace().getEmail()!=null)

            this.email.setText(event.getPlace().getEmail());
        else
            this.email.setText(getResources().getString(R.string.no_email));

        //Website

        this.website=(TextView)findViewById(R.id.website_contact);
        if(this.event.getWebsite()!=null)
            this.website.setText(event.getWebsite());
        else
            this.website.setText(getResources().getString(R.string.no_website));

//PHONE
        this.phone=(TextView)findViewById(R.id.phone_contact);

        if(this.event.getPhone()!=null)
            this.phone.setText(event.getPhone());
        else
            this.phone.setText(getResources().getString(R.string.no_phone));
//organizer NAME
        this.organizedby=(TextView)findViewById(R.id.organized_by);
        if(this.event.getOrganizer()!=null)
        this.organizedby.append(event.getOrganizer());
        //event name
        this.eventname=(TextView)findViewById(R.id.event_name);
        if (this.event.getName()!=null)

        this.eventname.setText(event.getName());

        //SETUP IMAGES
        imageSlider = new ImageSlider(this,slider);
        Iterator<String> iterator = this.event.getPlace().getPhotos().iterator();

        while(iterator.hasNext())
            this.imageSlider.addImage(this.event.getPlace().getName(),iterator.next());


        //SETUP OPENING TIMES


/*
        try {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    R.layout.item_member, this.meetingPlace.getOpeningtime());
            this.opening_time.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

*/




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
