package com.fide.ae.chessfamilybeta;

import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

import model.Member;
import model.MemberPublication;
import repository.MemberRepositoryImpl;
import repository.MemberRepository;
import utils.AsyncTaskResult;

public class ProfileActivity extends AppCompatActivity {

    MemberRepository memberRepository = new MemberRepositoryImpl();

    // User informations
    private Member member ;


    // UI Components
    private ImageView  photo ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        // UI components initialization
        photo = (ImageView) findViewById(R.id.photo) ;

    }

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
