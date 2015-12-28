package com.fide.ae.chessfamilybeta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentSearchEvent extends Fragment {

    private View root ;
    private Fragment current ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.current=this ;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_search_event, container, false);
        this.setupviews();
        this.searchAction();
    return  root ;
    }



    //SETTING UP VIEWS
    private Button search_btn ;
    private SeekBar distanceBar ;
    private RadioGroup radio_btns;
    private Spinner spinnerEtype ;
    private TextView km ;
    private NumberPicker days ;

    private void setupviews(
    ) {
        this.search_btn = (Button) root.findViewById(R.id.search_event);
        this.distanceBar = (SeekBar) root.findViewById(R.id.distance_bar);

        this.radio_btns = (RadioGroup) root.findViewById(R.id.gender_radio);
        this.spinnerEtype = (Spinner) root.findViewById(R.id.spinner_event_type);
        this.days=(NumberPicker)root.findViewById(R.id.number_days);
        this.setupPicker(days);

        this.km=(TextView) root.findViewById(R.id.kilometres);
        //SETUP SEEK BAR DISTANCE
        this.km.setText(this.distanceBar.getProgress() + " Km");
        distanceBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                km.setText(progress + " Km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                km.setText(progress + " Km");
                distance=progress;
                Toast.makeText(current.getActivity(), progress + " Km", Toast.LENGTH_SHORT).show();
            }

        });}




    private int distance,nb_days ;


    public int getDistance()
    {
        return this.distance;
    }

    public String getEventType( )
    {
        return
        this.spinnerEtype.getSelectedItem().toString();

    }
    public int getNb_days()
    {
        return this.nb_days ;

    }





    //SENDING VALUES WITHING INTENT

    private void searchAction()
    {
        this.search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle locationquery = new Bundle();
                locationquery.putString("Distance", String.valueOf(getDistance()));
                if(getEventType() !=null)
                locationquery.putString("EventType", getEventType());

                locationquery.putString("NumberDays", String.valueOf(getNb_days()));


                Intent search = new Intent(current.getActivity(), SearchActivity.class);
                search.putExtra("event", locationquery);
                search.putExtra("Search","event") ;

                startActivity(search);

            }
        });


    }
    private void setupPicker(final NumberPicker np)
    {


        np.setMinValue(0);
        np.setMaxValue(100);
        np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

nb_days=newVal ;

            }
        });

    }



}
