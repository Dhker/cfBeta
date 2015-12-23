package utils;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fide.ae.chessfamilybeta.GameFragment;
import com.fide.ae.chessfamilybeta.Member_list_Fragment;


/**
 * Created by Dhker on 12/23/2015.
 */

public class SectionPagerAdapter extends FragmentPagerAdapter implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    Member_list_Fragment fragment1 = new Member_list_Fragment();


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return fragment1;
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
                return "Players";
            case 1:
                return "Events";
            default:
                return "Locations";
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

        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

        switch (tab.getPosition())
        {

        }
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
