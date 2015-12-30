package com.fide.ae.chessfamilybeta;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Member;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;
import utils.AsyncTaskResult;

public class BaseActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    protected DrawerLayout drawerLayout;


    private CircleImageView photo ;
    private TextView email ;
    private TextView userName;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);



    }

    private MemberRepository memberRepository = new MemberRepositoryImpl();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);


        // Initializing Toolbar and setting it as the actionbar
     //   toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
       Log.d("created" ,"yes") ;
        photo =(CircleImageView) findViewById(R.id.profile_image) ;
        email = (TextView) findViewById(R.id.drawerEmail) ;
        userName =(TextView) findViewById(R.id.drawUsername) ;



        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
       navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

           // This method will trigger on item Click of navigation menu
           @Override
           public boolean onNavigationItemSelected(MenuItem menuItem) {


               //Checking if the item is in checked state or not, if not make it in checked state
               if (menuItem.isChecked()) menuItem.setChecked(false);
               else menuItem.setChecked(true);

               //Closing drawer on item click
               drawerLayout.closeDrawers();

               //Check to see which item was being clicked and perform appropriate action
               switch (menuItem.getItemId()) {


                   //Replacing the main content with ContentFragment Which is our Inbox View;
                   case R.id.inbox:
                       Toast.makeText(getApplicationContext(), "Inbox Selected", Toast.LENGTH_SHORT).show();

                       return true;

                   // For rest of the options we just show a toast on click

                   case R.id.starred:
                       Toast.makeText(getApplicationContext(), "Stared Selected", Toast.LENGTH_SHORT).show();
                       return true;
                   case R.id.sent_mail:
                       Toast.makeText(getApplicationContext(), "Send Selected", Toast.LENGTH_SHORT).show();
                       return true;
                   case R.id.drafts:
                       Toast.makeText(getApplicationContext(), "Drafts Selected", Toast.LENGTH_SHORT).show();
                       return true;
                   case R.id.allmail:
                       Toast.makeText(getApplicationContext(), "All Mail Selected", Toast.LENGTH_SHORT).show();
                       return true;
                   case R.id.trash:
                       Toast.makeText(getApplicationContext(), "Trash Selected", Toast.LENGTH_SHORT).show();
                       return true;
                   case R.id.spam:
                       Toast.makeText(getApplicationContext(), "Spam Selected", Toast.LENGTH_SHORT).show();
                       return true;
                   default:
                       Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                       return true;

               }
           }
       });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
      /*  ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();



*/





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    public void updateUI(Member member)
    {
        if(member!= null)
        {




            String userNameText = member.getName()+" "+ member.getLast_Name() ;
            userName.setText(userNameText);
            email.setText(member.getEmail());
            if(member.getPhoto()!=null)
            {
                Picasso.with(BaseActivity.this)
                        .load(member.getPhoto())
                        .into(photo);
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
                        Member member = result.getResult()   ;
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

}
