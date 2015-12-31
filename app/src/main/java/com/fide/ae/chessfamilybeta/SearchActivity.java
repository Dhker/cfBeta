
package com.fide.ae.chessfamilybeta;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import android.support.v4.view.ViewPager;

import android.support.v7.widget.Toolbar;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import utils.SectionPagerAdapter;

public class SearchActivity extends  BaseActivity {


    private  Toolbar myToolbar ;
    public  ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_search, null, false);
        drawerLayout.addView(contentView, 0);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar) ;
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
         viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.DarkBrown));
        tabLayout.setSelectedTabIndicatorHeight(5);



        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager())  ;

        pagerAdapter.addTitle(getResources().getString(R.string.member));
        pagerAdapter.addTitle(getResources().getString(R.string.event));
        pagerAdapter.addTitle( getResources().getString(R.string.location));
        //  setting the resource when the tab is selected
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.player_selected));
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.event_selected));
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.location_selected));
        // setting the resource when the tab is not selected
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.player_not_selected));
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.event_not_selected));
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.location_not_selected));

        pagerAdapter.addFragement(new FragmentSearchMember());
        pagerAdapter.addFragement(new FragmentSearchEvent());
        pagerAdapter.addFragement(new FragmentSearchLocation());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.player_selected));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.event_not_selected));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.location_not_selected));

        tabLayout.setTabTextColors(Color.GRAY, getResources().getColor(R.color.Linearmenu));

        tabLayout.setOnTabSelectedListener(pagerAdapter);




    }





}
