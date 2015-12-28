
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
import android.view.ViewGroup;

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
private FragmentManager fm;
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm=fm;
            fm.beginTransaction().add(new FragmentSearchMember(),"Member").commit();
            fm.beginTransaction().add(new FragmentSearchLocation(),"Location").commit();



        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment= new FragmentSearchMember() ;
            switch (position) {
                case 0:fragment =new FragmentSearchMember() ;

                    break ;

                case 1:
                    fragment= new FragmentSearchEvent();break ;
                case 2 :
                    fragment= new FragmentSearchLocation();

                default:


            }
            return fragment ;
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



            viewPager.setCurrentItem(position);



        }

        @Override
        public void onPageSelected(int position) {
            getItem(position);
            fm.beginTransaction().add(new FragmentSearchLocation(),"SearcgLocation").commit();



        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }


        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            switch (tab.getPosition())
            {
                case 0 : tab.setIcon(getResources().getDrawable(R.drawable.player_selected));break ;
                case 1 : tab.setIcon(getResources().getDrawable(R.drawable.event_selected)); this.getItem(1); break ;
                case 2 :tab.setIcon(getResources().getDrawable(R.drawable.location_selected)) ; this.getItem(2);break ;

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
        private Fragment mCurrentFragment;

        public Fragment getCurrentFragment() {
            return mCurrentFragment;
        }
        //...
        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            if (getCurrentFragment() != object) {
                mCurrentFragment = ((Fragment) object);
            }
            super.setPrimaryItem(container, position, object);
        }

    }


}
