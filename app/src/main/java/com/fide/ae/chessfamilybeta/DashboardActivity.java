package com.fide.ae.chessfamilybeta;






import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;


import com.parse.ParseUser;
import com.readystatesoftware.viewbadger.BadgeView;

import model.Member;
import utils.ChessFamilyUtils;
import utils.ImageResource;
import utils.ParsePushNotif;
import utils.SectionPagerAdapter;
import utils.SessionSotrage;


public class DashboardActivity extends BaseActivity {



    private   FrameLayout layout ;
    private ImageButton home_btn,notif_btn,message_btn,favorite_btn ;
    private  android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

    public ViewPager viewPager;

    private HomeFragment homeFragment ;
    private FeedsFragment feedsFragment;
    private MessageFragment messageFragment ;
    private NotificationFragment  notificationFragment;

private TabLayout tabLayout;

    private int currentTabPosition ;


    static boolean active  ;
    private  Member  member ;
    private ImageView iv1;
    private ImageView iv3;
    private SectionPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

ChessFamilyUtils.logoutActivity(this);


        this.member= SessionSotrage.CurrentSessionMember ;

        super.updateUI(member);



        Bundle bundle= this.getIntent().getExtras();
        if(bundle!=null) {


            if (bundle.containsKey("tabPosition")) {
                currentTabPosition = (int) bundle.get("tabPosition");
            }
        }




        homeFragment = new HomeFragment() ;
       
        feedsFragment = new FeedsFragment() ;
        messageFragment = new MessageFragment() ;
        notificationFragment = new NotificationFragment() ;


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_dashboard, null, false);
        drawerLayout.addView(contentView, 0);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_navigation_btn);



        setSupportActionBar(myToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

         tabLayout = (TabLayout) findViewById(R.id.tab_dash);
        viewPager = (ViewPager) findViewById(R.id.pager_dash);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.DarkBrown));
        tabLayout.setSelectedTabIndicatorHeight(5);









        pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), viewPager)  ;

       /* pagerAdapter.addTitle("Member");
        pagerAdapter.addTitle("Event");
        pagerAdapter.addTitle("Location");*/


     //  setting the resource when the tab is selected


        pagerAdapter.addFragement(homeFragment);
        pagerAdapter.addFragement(messageFragment);
        pagerAdapter.addFragement(feedsFragment);
        pagerAdapter.addFragement(notificationFragment);





        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(currentTabPosition);
        tabLayout.setupWithViewPager(viewPager);
        ImageView iv0 = new ImageView(this);iv0.setImageResource(R.drawable.ic_home_menu);
         iv1 = new ImageView(this);iv1.setImageResource(R.drawable.ic_message_menu);
        ImageView iv2 = new ImageView(this);iv2.setImageResource(R.drawable.ic_feeds);
         iv3 = new ImageView(this);iv3.setImageResource(R.drawable.ic_notification);
        tabLayout.getTabAt(0).setCustomView(iv0);
        tabLayout.getTabAt(1).setCustomView(iv1);
        tabLayout.getTabAt(2).setCustomView(iv2);
        tabLayout.getTabAt(3).setCustomView(iv3);

        tabLayout.setTabTextColors(Color.GRAY, getResources().getColor(R.color.Linearmenu)) ;
        tabLayout.setOnTabSelectedListener(pagerAdapter);

this.setNotificationNumber();





//        member =(Member) bundle.get("member");
        Log.d("Member", "" + (member == null)) ;
      //  this.addFragment(new GameFragment(),300);
      //  this.addFragment(new GameFragment(),400);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dashboard, menu);

        return  true ;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search: {
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.menu_refresh:

                return true;
            case android.R.id.home :
                super.drawerLayout.openDrawer(Gravity.LEFT);
                return true ;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        this.active = false ;
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.active = true ;
    }


    public void setNotificationNumber(){



        BadgeView badge = new BadgeView(this,iv3);

        badge.setText("3");
        badge.setBadgePosition(badge.POSITION_TOP_RIGHT);
        badge.setBackgroundResource(R.drawable.cercle);

        badge.show();this.pagerAdapter.unBadgeTab(3,badge);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Activity", "Dashboard");
        ImageResource.handleResult(requestCode, resultCode, data);
    }
}
