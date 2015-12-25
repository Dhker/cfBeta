package com.fide.ae.chessfamilybeta;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

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
    return  root ;
    }



    //SETTING UP VIEWS
    private Button btn_evt ;
    private SeekBar distanceBar ;
    private RadioGroup radio_btns;
    private Spinner spinnerEtype ;
    private TextView km ;
    private CalendarView calendar ;

    private void setupviews(
    ) {
        this.btn_evt = (Button) root.findViewById(R.id.search_event);
        this.distanceBar = (SeekBar) root.findViewById(R.id.distance_bar);

        this.radio_btns = (RadioGroup) root.findViewById(R.id.gender_radio);
        this.spinnerEtype = (Spinner) root.findViewById(R.id.spinner_event_type);

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

    public void initializeCalendar() {

        calendar = (CalendarView) root.findViewById(R.id.activity_date);



        // sets whether to show the week number.

        calendar.setShowWeekNumber(false);



        // sets the first day of week according to Calendar.

        // here we set Monday as the first day of the Calendar

        calendar.setFirstDayOfWeek(2);




        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

                    //show the selected date as a toast

            @Override

            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                setDate_evt( day + "/" + month + "/" + year);

                Toast.makeText(current.getActivity(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();

            }

        });

    }

    private int distance ;
    private String eventType,date_evt ;

    public int getDistance()
    {
        return this.distance;
    }

    public String getEventType( )
    {
        return
        this.spinnerEtype.getSelectedItem().toString();

    }
    public String getDate_evt()
    {
        return this.date_evt ;

    }

    public void setDate_evt(String date)
    {
        this.date_evt= date ;
    }


    //SENDING VALUES WITHING INTENT

    private void searchAction()
    {
        Bundle locationquery = new Bundle();
        locationquery.putString("Distance",String.valueOf(getDistance()));
        locationquery.putString("EventType",getEventType());

        locationquery.putString("ActivityDate",getDate_evt());



        Intent search = new Intent(this.getActivity(),SearchActivity.class);
        search.putExtra("event",locationquery);

        startActivity(search);

    }



}
