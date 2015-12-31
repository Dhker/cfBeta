package com.fide.ae.chessfamilybeta;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import model.MeetingPlace;
import model.Member;

import repository.MemberRepositoryImpl;
import repository.MemberRepository;
import utils.AsyncTaskResult;
import utils.SectionPagerAdapter;

public class ProfileActivity extends BaseActivity {



    MemberRepository memberRepository = new MemberRepositoryImpl();


    private Member member ;
    public ViewPager viewPager;

    static boolean active = false;


    // UI Components
    private ImageView  photo ;
    private TextView userName ;
    private TextView age ;
    private TextView gender  ;



    private MemberFragment memberFragment ;
    private MeetingPlaceFragment meetingPlaceFragment;
    private FriendsFragement friendsFragement ;
    private FeedsFragment  feedsFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        memberFragment = new MemberFragment() ;
        meetingPlaceFragment = new MeetingPlaceFragment() ;
        friendsFragement = new FriendsFragement() ;
        feedsFragment = new FeedsFragment() ;

        this.active = true  ;


        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_profile, null, false);
        drawerLayout.addView(contentView, 0);




        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call



        // UI components initialization
        photo = (ImageView) findViewById(R.id.photo) ;
        userName= (TextView)findViewById(R.id.userName);
        age=(TextView)findViewById(R.id.age);
        gender= (TextView)findViewById(R.id.gender) ;
        Bundle bundle= this.getIntent().getExtras();





        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_profile);
        viewPager = (ViewPager) findViewById(R.id.pager_profile);
        viewPager.setOffscreenPageLimit(1);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.Linearcadre1));
        tabLayout.setSelectedTabIndicatorHeight(5);

        // this.home_btn = (ImageButton) findViewById(R.id.home_menu_btn);
      /*  this.notif_btn=(ImageButton) findViewById(R.id.notification_menu_btn);
        this.message_btn=(ImageButton) findViewById(R.id.message_menu_btn);
        this.favorite_btn= (ImageButton) findViewById(R.id.favorite_menu_btn);


*/







        SectionPagerAdapter pagerAdapter = new SectionPagerAdapter(getSupportFragmentManager())  ;

       /* pagerAdapter.addTitle("Member");
        pagerAdapter.addTitle("Event");
        pagerAdapter.addTitle("Location");*/


        //  setting the resource when the tab is selected

        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.ic_profile));
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.ic_place));
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.ic_friends));
        pagerAdapter.addSelectedResource(getResources().getDrawable(R.drawable.ic_feeds));


        // setting the resource when the tab is not selected
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.ic_profile));
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.ic_place));
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.ic_friends));
        pagerAdapter.addUnSelectedResource(getResources().getDrawable(R.drawable.ic_feeds));

        pagerAdapter.addFragement(memberFragment);
        pagerAdapter.addFragement(meetingPlaceFragment);
        pagerAdapter.addFragement(friendsFragement);
        pagerAdapter.addFragement(feedsFragment);





        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(getResources().getDrawable(R.drawable.ic_profile));
        tabLayout.getTabAt(1).setIcon(getResources().getDrawable(R.drawable.ic_place));
        tabLayout.getTabAt(2).setIcon(getResources().getDrawable(R.drawable.ic_friends));
        tabLayout.getTabAt(3).setIcon(getResources().getDrawable(R.drawable.ic_feeds));

        tabLayout.setTabTextColors(Color.GRAY, getResources().getColor(R.color.Linearmenu));

        tabLayout.setOnTabSelectedListener(pagerAdapter);




        // member =(Member) bundle.get("member");
       // loadUserInformation("" + member.getID());
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
              if(member.getGender()!=0)
              {

                  gender.setText((member.getGender()==1)?  R.string.Male:R.string.Female );
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return  true ;
    }
}
