package utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fide.ae.chessfamilybeta.FragmentSearchEvent;
import com.fide.ae.chessfamilybeta.FragmentSearchLocation;
import com.fide.ae.chessfamilybeta.FragmentSearchMember;
import com.fide.ae.chessfamilybeta.R;
import com.readystatesoftware.viewbadger.BadgeView;

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
    private Context context;
   private  ArrayList<Integer> tabpos ;
    private boolean iconsMode=false;


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



    public SectionPagerAdapter(FragmentManager fm , ViewPager viewPager) {
        super(fm);
        this.fm=fm;
        titles = new ArrayList<String>();
        fragments = new ArrayList<Fragment>();
        selectedResources=new ArrayList<Drawable>();
        unselectedResources =new ArrayList<Drawable>();
        this.viewPager = viewPager ;
        this.tabpos= new ArrayList<Integer>();

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


        //   if (selectedResources != null)
        //     tab.setIcon(selectedResources.get(tab.getPosition()));


        viewPager.setCurrentItem(tab.getPosition());
        if ((this.badgedtab == tab.getPosition()) && (this.badge != null)) {

            this.badge.hide();

        }
        if (iconsMode) {

            if ((this.selectedResources != null)) {

                tab.setIcon(selectedResources.get(tab.getPosition()));
            }

        }
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
     //   if(unselectedResources!=null )
       //     tab.setIcon(unselectedResources.get(tab.getPosition())) ;

        if(this.iconsMode) {
            if ((this.unselectedResources != null)) {
                tab.setIcon(unselectedResources.get(tab.getPosition()));
            }
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

private BadgeView badge ;
    private int badgedtab ;

    public void unBadgeTab(int tab, BadgeView badge)
    {
this.badge=badge ;
        this.badgedtab=tab ;
    }


    public void setIcons(int pos,Drawable selectedIcon,Drawable Unselectedicon)
    {
        this.unselectedResources.add(pos,Unselectedicon);
        this.selectedResources.add(pos,selectedIcon);
    }

    public void setIconMode()
    {
        this.iconsMode = true ;
    }



}
