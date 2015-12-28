package com.fide.ae.chessfamilybeta;


import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentSearchLocation extends Fragment {

    private View root ;

    private SeekBar distanceBar ;
    private Spinner locationSpinner;
    private RadioGroup radio_btns;
    private TextView km ;
    private Button search_btn ;

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
        root =  inflater.inflate(R.layout.fragment_search_location, container, false);
        this.SetupViews();
        this.searchAction();
        return root ;
    }
//SETUP VIEWS

    private void SetupViews ()
    {

        this.search_btn = (Button) root.findViewById(R.id.search_location) ;
        this.radio_btns = (RadioGroup) root.findViewById(R.id.acitivity_radio);
        this.locationSpinner =(Spinner)root.findViewById(R.id.spinner_locations);

        this.distanceBar = (SeekBar) root.findViewById(R.id.distance_bar);
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

        });

    }

    private int distance ;
    private String location,activity ;


    public int getDistanceValue()
    {
        return distance ;
    }
    public String getLocation()
    {
        return   this.locationSpinner.getSelectedItem().toString() ;
    }

    public String getActivities ()
    {
        if(this.radio_btns.getCheckedRadioButtonId()==R.id.opened_radio)
            return "Opened" ;
        return "All";
    }

    //SENDING VALUES WITHING INTENT

    private void searchAction()
    {
        this.search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle locationquery = new Bundle();
                locationquery.putString("Distance", String.valueOf(getDistanceValue()));
                if(getLocation()!=null)
                locationquery.putString("Location", getLocation());

                locationquery.putString("Active", String.valueOf(getActivities()));


                Intent search = new Intent(current.getActivity(), SearchActivity.class);
                search.putExtra("location", locationquery);
                search.putExtra("Search","location") ;

                startActivity(search);

            }
        });

    }
    }
