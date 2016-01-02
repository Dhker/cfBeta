package com.fide.ae.chessfamilybeta;

import android.app.AlertDialog;
import android.app.Dialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import junit.framework.TestCase;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Member;
import utils.ChessFamilyUtils;

public class MemberProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton message,feeds,follow,profile ;
    private MemberFragment memberFragment ;
    private Member currentMember ;
    private CircleImageView profile_photo ;
    private TextView member_name ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_profile);

        this.message=(ImageButton)findViewById(R.id.message_btn);
        this.message.setOnClickListener(this);
        this.feeds=(ImageButton)findViewById(R.id.feed_btn);
        this.feeds.setOnClickListener(this);
        this.profile=(ImageButton)findViewById(R.id.profile_btn);
        this.profile.setOnClickListener(this);
        this.follow=(ImageButton)findViewById(R.id.follow_btn);
        this.follow.setOnClickListener(this);
        this.currentMember=(Member)this.getIntent().getExtras().get("member");

        //init name and id

        this.member_name=(TextView)findViewById(R.id.userName);

        this.member_name.setText(this.currentMember.getName()+" ||ID : "+this.currentMember.getID());

        //INIT PROFILE IMAGE
        this.profile_photo=(CircleImageView)findViewById(R.id.profile_image);
        Picasso.with(this).load(this.currentMember.getPhoto()).into(this.profile_photo);

        //init first fragment
        MemberFragment memberFragment = new MemberFragment();
        memberFragment.setFacebookLink(true);


        ChessFamilyUtils.attchFragment(this,memberFragment,R.id.profile_container);





    }


    @Override
    public void onClick(View v) {

        if(v.equals(this.profile))
        {
            ChessFamilyUtils.attchFragment(this,memberFragment,R.id.profile_container);
        }
        else if(v.equals(this.message))
        {
            AlertDialog builder = new AlertDialog.Builder(this).create();

            LayoutInflater inflater = getLayoutInflater();
            View dialoglayout = inflater.inflate(R.layout.send_message, null);



            builder.setTitle(getResources().getString(R.string.send_msg));
            builder.setView(dialoglayout);

            builder.show();



        }
        else if(v.equals(this.follow))
        {




        }
        else if (v.equals(this.feeds))
        {

        }


    }
}
