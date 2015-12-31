package utils;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.fide.ae.chessfamilybeta.FragmentSearchEvent;
import com.fide.ae.chessfamilybeta.FragmentSearchLocation;
import com.fide.ae.chessfamilybeta.FragmentSearchMember;
import com.fide.ae.chessfamilybeta.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import model.Title;

public class SectionPagerAdapter extends FragmentPagerAdapter implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {
    private FragmentManager fm;

    private ViewPager viewPager ;

    private ArrayList<String> titles ;

    private ArrayList<Fragment> fragments ;

    private ArrayList<Drawable>  unselectedResources ;

    private ArrayList<Drawable>  selectedResources ;

    public void  addUnSelectedResource(Drawable resource)
    {
        if(resource!=null)
            unselectedResources.add(resource) ;
    }


    public void  addSelectedResource(Drawable resource)
    {
        if(resource!=null)
            selectedResources.add(resource) ;
    }


    public void  addTitle(String title)
    {
        if(title!=null)
        titles.add(title) ;
    }

    public void  addFragement(Fragment fragment)
    {
        if(fragment!=null)
            fragments.add(fragment) ;
    }



    public SectionPagerAdapter(FragmentManager fm ) {
        super(fm);
        this.fm=fm;
        titles = new ArrayList<String>();
        fragments = new ArrayList<Fragment>();
        selectedResources=new ArrayList<Drawable>();
        unselectedResources =new ArrayList<Drawable>();


      /*  fm.beginTransaction().add(new FragmentSearchMember(),"Member").commit();
        fm.beginTransaction().add(new FragmentSearchLocation(),"Location").commit();

*/

    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {

        if ((fragments != null))//&&(position<fragments.length) )
        return  fragments.get(position);
        else
        {
            return  new Fragment() ;
        }
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
       if (((titles != null))&&(!titles.isEmpty()) )
       {
           return titles.get(position) ;
       }else
       {
           return "" ;
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

        if(selectedResources!=null )
        tab.setIcon(selectedResources.get(tab.getPosition())) ;

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        if(unselectedResources!=null )
            tab.setIcon(unselectedResources.get(tab.getPosition())) ;

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
