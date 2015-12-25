package com.fide.ae.chessfamilybeta;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentSearchMember extends Fragment {

private View root,resultroot ;
    private SeekBar distanceBar ;
    private Spinner locationSpinner,profileSpinner ;
    private NumberPicker from_age,to_age;
    private Button btn_search;
    private RadioGroup radio_btns ;
    private int distance , age_from,age_to,gender ;
    private String location, profile;
    private Fragment current ;
    private ViewGroup container ;
    private int type_fragment ;
    private TextView km ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.current=this ;

    }

    public void setType_fragment(int type_fragment) {
        this.type_fragment = type_fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        this.container=container;


        root= inflater.inflate(R.layout.fragment_search_member, container, false);
        resultroot= inflater.inflate(R.layout.search_result_layout, container, false);

        this.setupviews();



    return root ;
    }

//SETING UP VIEWS
    private void setupviews(
                           ) {
        this.btn_search = (Button) root.findViewById(R.id.search_member);
        this.distanceBar = (SeekBar) root.findViewById(R.id.distance_bar);
        this.from_age = (NumberPicker) root.findViewById(R.id.age_value_from);
        this.to_age = (NumberPicker) root.findViewById(R.id.age_value_to);
        this.radio_btns = (RadioGroup) root.findViewById(R.id.gender_radio);
        this.profileSpinner = (Spinner) root.findViewById(R.id.spinner_profile);
        this.locationSpinner = (Spinner) root.findViewById(R.id.spinner_locations);
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
                Toast.makeText(current.getActivity(), progress + " Km", Toast.LENGTH_SHORT).show();
            }

        });
        this.setupPicker(from_age);
        this.setupPicker(to_age);





    }

    private ListView member_list ;
    //SETUP VIEWS FOR RESULT
    private void setUpSearchViews()
    {
        this.member_list = (ListView)resultroot.findViewById(R.id.listsearch_result);


    }

    private int from, to ;


    private void setupPicker(final NumberPicker np)
    {


        np.setMinValue(0);
        np.setMaxValue(100);
        np.setWrapSelectorWheel(false);

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {


                if (np.getId()==R.id.age_value_from)
                {
                    from = newVal ;
                }
                else
                    to=newVal;





            }
        });

    }


}
