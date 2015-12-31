
package com.fide.ae.chessfamilybeta;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import android.view.ViewGroup;


import utils.SectionPagerAdapter;

public class SearchActivity extends  AppCompatActivity {

    public  ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
