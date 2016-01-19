package com.fide.ae.chessfamilybeta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Member;

import repository.MemberRepositoryImpl;
import repository.MemberRepository;
import utils.AsyncTaskResult;
import utils.ChessFamilyUtils;
import utils.SectionPagerAdapter;
import utils.SessionSotrage;

public class ProfileActivity extends BaseActivity {



    MemberRepository memberRepository = new MemberRepositoryImpl();


    private Member member ;
    public ViewPager viewPager;

    static boolean active = false;


    // UI Components
    private CircleImageView  photo ;
    private TextView userName ;
    private TextView age ;
    private TextView gender  ;



    private MemberFragment memberFragment ;
    private FavoriteFragment favoriteFragment;
    private MessageFragment messageFragment ;
    private NotificationFragment  notificationFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        super.updateUI(SessionSotrage.CurrentSessionMember);
        super.header.invalidate();

        ChessFamilyUtils.logoutActivity(this);

        memberFragment = new MemberFragment() ;
        favoriteFragment = new FavoriteFragment() ;
        messageFragment = new MessageFragment() ;
        notificationFragment = new NotificationFragment() ;

        this.active = true  ;


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_profile, null, false);
        drawerLayout.addView(contentView, 0);




        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        myToolbar.setTitle(getResources().getString(R.string.myprofile));
        myToolbar.setNavigationIcon(R.drawable.ic_navigation_btn);

        setSupportActionBar(myToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call
getSupportActionBar().setDisplayShowHomeEnabled(true);


        // UI components initialization
        photo = (CircleImageView) findViewById(R.id.profile_image) ;
        userName= (TextView)findViewById(R.id.userName);
        age=(TextView)findViewById(R.id.age);
        gender= (TextView)findViewById(R.id.gender) ;
       this.member= SessionSotrage.CurrentSessionMember;




        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_profile);
        viewPager = (ViewPager) findViewById(R.id.pager_profile);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.Linearcadre1));
        tabLayout.setSelectedTabIndicatorHeight(5);








        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager(), viewPager)  ;


        //  setting the resource when the tab is selected



        pagerAdapter.addFragement(memberFragment);
        pagerAdapter.addFragement(messageFragment);
        pagerAdapter.addFragement(favoriteFragment);
        pagerAdapter.addFragement(notificationFragment);





        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_profile));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_place));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_friends));
        tabLayout.getTabAt(3).setIcon(getResources().getDrawable(R.drawable.ic_event));

        tabLayout.setTabTextColors(Color.GRAY, getResources().getColor(R.color.Linearmenu));

        tabLayout.setOnTabSelectedListener(pagerAdapter);




super.updateUI(member);

        this.updateUI(member);
   //     Log.d("profileImage" , member.getPhoto()) ;



    }

    // update the UI when the user information  are loaded
    public void updateUI(Member member)
    {
        if(member!= null)
        {




                String userNameText = member.getName()+" "+member.getLast_Name()+" |ID:"+member.getID() ;
            userName.setText(userNameText);
                if(member.getPhoto()!=null)
                {
                    Picasso.with(ProfileActivity.this)
                            .load(member.getPhoto())
                            .into(photo);
                }
              if(member.getBirthday()!= null )
              {
                  // calculate age
              }



        }
    }

    // update user information
    public void loadUserInformation(String id)
    {

        if((id==null)||(id.isEmpty()))
        {
            throw new IllegalArgumentException();

        } else
        {



            new AsyncTask<String  ,String , AsyncTaskResult<Member>>(){

                @Override
                protected void onPostExecute(AsyncTaskResult<Member> result) {

                    if (result.getError() == null)
                    {
                        member = result.getResult()   ;
                        Log.d("new", "" + member) ;
                        updateUI(member);
                    }else
                    {
                    }


                }

                @Override
                protected AsyncTaskResult<Member> doInBackground(String... params) {


                    String id  = params[0] ;
                    AsyncTaskResult<Member> result =null ;



                    try {

                        Member member = memberRepository.getMemberById(id) ;
                        result= new AsyncTaskResult<Member>(member) ;
                        return result;
                    } catch (Exception e) {
                        Log.d("error", e.toString())    ;
                        e.printStackTrace();
                        result = new  AsyncTaskResult<Member>(e) ;
                        return result ;
                    }



                }
            }.execute(id);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_profile_menu, menu);

        return true ;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.editprofile: {
                Intent intent = new Intent(this, EditProfileActivity.class);
                startActivity(intent);
                return true;
            }

            case android.R.id.home :
                super.drawerLayout.openDrawer(Gravity.LEFT);
                return true ;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
