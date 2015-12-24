
package com.fide.ae.chessfamilybeta;


import android.graphics.Color;
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




    public class SectionPagerAdapter extends FragmentPagerAdapter implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        Member_list_Fragment fragment1 = new Member_list_Fragment();


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new FragmentSearchMember();
                case 1:
                    return new Fragment();
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getString(R.string.member);
                case 1:
                    return getResources().getString(R.string.event);
                default:
                    return getResources().getString(R.string.location);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }


        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition())
            {
                case 0 : tab.setIcon(getResources().getDrawable(R.drawable.player_selected));break ;
                case 1 : tab.setIcon(getResources().getDrawable(R.drawable.event_selected)); break ;
                case 2 :tab.setIcon(getResources().getDrawable(R.drawable.location_selected)) ; break ;
            }

        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

            switch (tab.getPosition())
            {
                case 0 : tab.setIcon(getResources().getDrawable(R.drawable.player_not_selected));break ;
                case 1 : tab.setIcon(getResources().getDrawable(R.drawable.event_not_selected));break ;
                case 2 :tab.setIcon(getResources().getDrawable(R.drawable.location_not_selected)) ; break ;
            }
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    }

}
