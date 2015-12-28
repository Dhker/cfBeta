package com.fide.ae.chessfamilybeta;

import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import model.Member;

import repository.MemberRepositoryImpl;
import repository.MemberRepository;
import utils.AsyncTaskResult;

public class ProfileActivity extends AppCompatActivity {

    MemberRepository memberRepository = new MemberRepositoryImpl();


    private Member member ;


    // UI Components
    private ImageView  photo ;
    private TextView userName ;
    private TextView age ;
    private TextView gender  ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        // UI components initialization
        photo = (ImageView) findViewById(R.id.photo) ;
        userName= (TextView)findViewById(R.id.userName);
        age=(TextView)findViewById(R.id.age);
        gender= (TextView)findViewById(R.id.gender) ;
        Bundle bundle= this.getIntent().getExtras();
        member =(Member) bundle.get("member");
        loadUserInformation("" + member.getID());
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
    public void updateUserInformation(Member member)
    {

    if((member==null))
    {
        throw new IllegalArgumentException();

    } else
    {



        new AsyncTask<Member  ,Member , AsyncTaskResult<Boolean>>(){

            @Override
            protected void onPostExecute(AsyncTaskResult<Boolean> result) {

                if (result.getError() == null)
                {
                   Boolean success = result.getResult()   ;
                    Log.d("Updated",""+success) ;
                    // updateUI(member);
                }else
                {
                }


            }

            @Override
            protected AsyncTaskResult<Boolean> doInBackground(Member... params) {


                Member member1  = params[0] ;
                AsyncTaskResult<Boolean> result =null ;



                    try {

                        Boolean sucess = memberRepository.updateMemberInformation(member1) ;
                        result= new AsyncTaskResult<Boolean >(sucess) ;

                        return result;
                    } catch (Exception e) {
                        Log.d("error", e.toString())    ;
                        e.printStackTrace();
                        result = new  AsyncTaskResult<Boolean>(e) ;
                        return result ;
                    }



            }
        }.execute(member);

    }

}

    // load user informations
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
                        Log.d("new",""+member) ;
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
