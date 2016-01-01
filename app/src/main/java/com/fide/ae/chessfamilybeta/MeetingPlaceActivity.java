package com.fide.ae.chessfamilybeta;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;

import org.w3c.dom.Text;

import java.util.Iterator;

import model.MeetingPlace;
import model.Member;
import repository.MeetingPlaceRepository;
import repository.MeetingPlaceRepositoryImpl;
import utils.ImageSlider;



public class MeetingPlaceActivity extends AppCompatActivity {
    private TextView placeName, placetype,website,email,phone,adresse;
    private ListView opening_time ;
    private ImageView status,favorite_btn ;

    protected DrawerLayout drawerLayout;

    private  SliderLayout slider ;
    private  ImageSlider imageSlider ;

    private MeetingPlace meetingPlace ;
    private Member currentmember ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_meeting_place, null, false);

        drawerLayout.addView(contentView, 0);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //INIT MEETING PLACE MODEL

/*        this.meetingPlace = (MeetingPlace)this.getIntent().getExtras().get("meeting_place") ;

        this.currentmember=(Member)this.getIntent().getExtras().get("member") ; */



        this.slider = (SliderLayout)findViewById(R.id.slider);



    }


    private void initViews()
    {
        //ADRESSE
        this.adresse=(TextView)findViewById(R.id.adresse);
        if(this.meetingPlace.getAdress()!=null)
            this.adresse.setText(meetingPlace.getAdress());
        else
            this.adresse.setText(getResources().getString(R.string.no_adresse));


        //CONTACT

        this.email=(TextView)findViewById(R.id.email_contact);
        if(this.meetingPlace.getEmail()!=null)

            this.email.setText(meetingPlace.getEmail());
        else
            this.email.setText(getResources().getString(R.string.no_email));

        //Website

        this.website=(TextView)findViewById(R.id.website_contact);
        if(this.meetingPlace.getWebsite()!=null)
            this.website.setText(meetingPlace.getWebsite());
        else
            this.website.setText(getResources().getString(R.string.no_website));

//PHONE
        this.phone=(TextView)findViewById(R.id.phone_contact);

        if(this.meetingPlace.getPhone()!=null)
            this.phone.setText(meetingPlace.getPhone());
        else
            this.phone.setText(getResources().getString(R.string.no_phone));
//PLACE NAME
        this.placeName=(TextView)findViewById(R.id.place_name);
        this.placeName.setText(meetingPlace.getName());
        //PLACE TYPE
        this.placetype=(TextView)findViewById(R.id.place_type);
        this.placetype.setText(meetingPlace.getType().toString());

        //SETUP IMAGES
        imageSlider = new ImageSlider(this,slider);
        Iterator<String> iterator = this.meetingPlace.getPhotos().iterator();

        while(iterator.hasNext())
            this.imageSlider.addImage(meetingPlace.getName(),iterator.next());

        if(this.meetingPlace.getStatus()==1)
            this.status.setImageResource(android.R.drawable.presence_online);

        //SETUP OPENING TIMES


/*
        try {

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,

            this.opening_time.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

*/




    }

    @Override
    protected void onStop() {
        super.onStop();
        this.imageSlider.onStop();
    }
}
