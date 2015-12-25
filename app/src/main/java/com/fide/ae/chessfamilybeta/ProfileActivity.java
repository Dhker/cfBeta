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


    String path ="https://graph.facebook.com/10206862451313583/picture?width=200&height=150"  ;    // User informations
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

// load user information by id
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
