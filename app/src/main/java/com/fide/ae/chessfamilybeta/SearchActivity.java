
package com.fide.ae.chessfamilybeta;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
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


import utils.ChessFamilyUtils;
import utils.SectionPagerAdapter;

public class SearchActivity extends  BaseActivity {


    private  Toolbar myToolbar ;
    public  ViewPager viewPager;
    private SectionPagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ChessFamilyUtils.logoutActivity(this);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_search, null, false);
        drawerLayout.addView(contentView, 0);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar) ;


        myToolbar.setNavigationIcon(R.drawable.ic_back_menu);



        setSupportActionBar(myToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
         viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.DarkBrown));
        tabLayout.setSelectedTabIndicatorHeight(5);



         pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), viewPager)  ;

        pagerAdapter.addTitle(getResources().getString(R.string.member));
        pagerAdapter.addTitle(getResources().getString(R.string.event));
        pagerAdapter.addTitle(getResources().getString(R.string.location));
        //  setting the resource when the tab is selected


        pagerAdapter.addFragement(new FragmentSearchMember());
        pagerAdapter.addFragement(new FragmentSearchEvent());
        pagerAdapter.addFragement(new FragmentSearchLocation());

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        pagerAdapter.setIconMode();
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.player_selected));
        pagerAdapter.setIcons(0, getResources().getDrawable(R.drawable.player_selected), getResources().getDrawable(R.drawable.player_not_selected));


        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.event_not_selected));

        pagerAdapter.setIcons(1, getResources().getDrawable(R.drawable.event_selected), getResources().getDrawable(R.drawable.event_not_selected));



        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.location_not_selected));
        pagerAdapter.setIcons(2, getResources().getDrawable(R.drawable.location_selected), getResources().getDrawable(R.drawable.location_not_selected));

        tabLayout.setTabTextColors(Color.GRAY, getResources().getColor(R.color.Linearmenu));

        tabLayout.setOnTabSelectedListener(pagerAdapter);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return  true ;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home :
                NavUtils.navigateUpFromSameTask(this);

                return true ;
            default:
                return super.onOptionsItemSelected(item);
        }
    }






}
