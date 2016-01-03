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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;


import com.parse.ParseUser;

import model.Member;
import utils.ChessFamilyUtils;
import utils.ParsePushNotif;
import utils.SectionPagerAdapter;


public class DashboardActivity extends BaseActivity {



    private   FrameLayout layout ;
    private ImageButton home_btn,notif_btn,message_btn,favorite_btn ;
    private  android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();

    public ViewPager viewPager;

    private HomeFragment homeFragment ;
    private FavoriteFragment favoriteFragment;
    private MessageFragment messageFragment ;
    private NotificationFragment  notificationFragment;



    private int currentTabPosition ;


    static boolean active  ;
    private  Member  member ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_dashboard);
 */

        ParsePushNotif.initParse(this);

        ParsePushNotif.sendNotificationToChannel("Everyone","Happy 2016");

        //SETUP VIEWS


        Bundle bundle= this.getIntent().getExtras();
        if(bundle!=null) {


            if (bundle.containsKey("tabPosition")) {
                currentTabPosition = (int) bundle.get("tabPosition");
            }
        }


        homeFragment = new HomeFragment() ;
        favoriteFragment = new FavoriteFragment() ;
        messageFragment = new MessageFragment() ;
        notificationFragment = new NotificationFragment() ;


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_dashboard, null, false);
        drawerLayout.addView(contentView, 0);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_dash);
        viewPager = (ViewPager) findViewById(R.id.pager_dash);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.DarkBrown));
        tabLayout.setSelectedTabIndicatorHeight(5);

        // this.home_btn = (ImageButton) findViewById(R.id.home_menu_btn);
      /*  this.notif_btn=(ImageButton) findViewById(R.id.notification_menu_btn);
        this.message_btn=(ImageButton) findViewById(R.id.message_menu_btn);
        this.favorite_btn= (ImageButton) findViewById(R.id.favorite_menu_btn);


*/







       SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), viewPager)  ;

       /* pagerAdapter.addTitle("Member");
        pagerAdapter.addTitle("Event");
        pagerAdapter.addTitle("Location");*/


     //  setting the resource when the tab is selected

        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.ic_home_menu));
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.ic_message_menu));
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.ic_favorite_menu));
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.ic_notification));


      // setting the resource when the tab is not selected
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.ic_home_menu));
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.ic_message_menu));
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.ic_favorite_menu));
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.ic_notification));

        pagerAdapter.addFragement(homeFragment);
        pagerAdapter.addFragement(messageFragment);
        pagerAdapter.addFragement(favoriteFragment);
        pagerAdapter.addFragement(notificationFragment);





        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(currentTabPosition);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_home_menu));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_message_menu));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_favorite_menu));
        tabLayout.getTabAt(3).setIcon(getResources().getDrawable(R.drawable.ic_notification));

        tabLayout.setTabTextColors(Color.GRAY, getResources().getColor(R.color.Linearmenu)) ;
        tabLayout.setOnTabSelectedListener(pagerAdapter);







//        member =(Member) bundle.get("member");
        Log.d("Member", "" + (member == null)) ;
      //  this.addFragment(new GameFragment(),300);
      //  this.addFragment(new GameFragment(),400);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

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






}
