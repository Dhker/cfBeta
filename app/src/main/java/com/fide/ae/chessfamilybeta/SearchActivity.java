
package com.fide.ae.chessfamilybeta;

import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;


import android.os.Bundle;
import android.support.design.widget.TabLayout;



import android.support.v4.view.ViewPager;

import android.support.v7.widget.Toolbar;

import android.support.v7.app.AppCompatActivity;

import utils.SectionPagerAdapter;

public class SearchActivity extends  AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.DarkBrown));
        tabLayout.setSelectedTabIndicatorHeight(5);



        if (toolbar != null) {
            this.setSupportActionBar(toolbar);
        }

        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager()) ;

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.player_selected));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.event_not_selected));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.location_not_selected));

        tabLayout.setTabTextColors(Color.GRAY, getResources().getColor(R.color.Linearmenu));

        tabLayout.setOnTabSelectedListener(pagerAdapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);



        return true;
    }









}
