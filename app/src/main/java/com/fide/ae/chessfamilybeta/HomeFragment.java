package com.fide.ae.chessfamilybeta;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import model.MemberPublication;
import utils.PublicationAdaptar;


public class HomeFragment extends Fragment {

    private View RootView;
    private ListView  publicationList  ;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.RootView = inflater.inflate(R.layout.home_layout, container, false);
        publicationList =(ListView) RootView.findViewById(R.id.list) ;
        ArrayList<MemberPublication> arrayOfUsers = new ArrayList<MemberPublication>();
        for( int i=0  ;i<150 ; i++)
        {
            MemberPublication pub  = new MemberPublication() ;
            pub.setText("pub"+i);
            pub.setTime(new Date());
            arrayOfUsers.add(pub) ;
        }

// Create the adapter to convert the array to views
        PublicationAdaptar adapter = new PublicationAdaptar (getActivity(), arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView = (ListView) RootView. findViewById(R.id.list);
        listView.setAdapter(adapter);
        return RootView;

    }


}
