package com.fide.ae.chessfamilybeta;


import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;


public class SearchResultActivity extends AppCompatActivity {



private Bundle values ;
    private Intent currentIntent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        this.currentIntent=this.getIntent() ;

        if (this.currentIntent!=null)
        {
            String request = this.currentIntent.getExtras().getString("Search");

            switch (request)
            {
                case  "member" :{this.setUpMemberSearch();break;}
                case    "event" : {this.setupEventSearch();break;}
                case "location" :{this.setupLocationSearch();break;}
            }

        }




    }


    private void setUpMemberSearch()
    {


    }

    private void setupEventSearch()

    {

    }

    private void setupLocationSearch()
    {

    }
}
