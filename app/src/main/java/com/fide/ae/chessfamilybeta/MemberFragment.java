package com.fide.ae.chessfamilybeta;


import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import model.Member;
import utils.ChessFamilyUtils;


public class MemberFragment extends Fragment {

    private Member currentMember ;
    private View root ;
    TextView email, birthday, gender , location,title_details,trainer_for;
    ImageView isTitled,isTrainer,isArbiter,isPlayer,isOrganizer;
    private boolean facebookLink=false ;

    public MemberFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.currentMember=(Member)this.getActivity().getIntent().getExtras().get("member");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root= inflater.inflate(R.layout.fragment_member, container,false);

        this.birthday=(TextView)root.findViewById(R.id.birthday_profile) ;
        this.gender=(TextView)root.findViewById(R.id.gender) ;
        this.location=(TextView)root.findViewById(R.id.location) ;
        this.email=(TextView)root.findViewById(R.id.email) ;
        this.title_details=(TextView)root.findViewById(R.id.titled_details) ;
        this.trainer_for=(TextView)root.findViewById(R.id.trainer_details) ;



        this.isArbiter=(ImageView)root.findViewById(R.id.checked_arbiter) ;
        this.isTitled=(ImageView)root.findViewById(R.id.checked_titled) ;
        this.isTrainer=(ImageView)root.findViewById(R.id.checked_trainer) ;
        this.isPlayer=(ImageView)root.findViewById(R.id.checked_player) ;
        this.isOrganizer=(ImageView)root.findViewById(R.id.checked_oranizer) ;
        this.setFBprofilelink(facebookLink);

     //   this.setUpViews();


        // Inflate the layout for this fragment

        return root;
    }

    public void setFacebookLink(boolean facebookLink) {
        this.facebookLink = facebookLink;
    }

    public void setUpViews ()
    {

        this.birthday.setText(currentMember.getBirthday().toString());
        this.email.setText(currentMember.getEmail());
        if(currentMember.getGender()==1)
        this.gender.setText("Male");
        else
            this.gender.setText("Female");


        if (this.currentMember.getProfile().getIsArbiter()==1)
            this.isArbiter.setImageResource(R.drawable.ic_green_checked);
        if(this.currentMember.getProfile().getIsPlayer()==1)
            this.isPlayer.setImageResource(R.drawable.ic_green_checked);
        if (this.currentMember.getProfile().getIsOrganizer()==1)
            this.isOrganizer.setImageResource(R.drawable.ic_green_checked);

        if (this.currentMember.getProfile().getIsTitled()==1) {
            this.title_details.setText(currentMember.getProfile().getTitle().toString());
            this.isTitled.setImageResource(R.drawable.ic_green_checked);
        }
        else
        this.title_details.setText("");
        if (this.currentMember.getProfile().getIsTrainer()==1) {
            this.trainer_for.setText(currentMember.getProfile().getTrainerLevel().toString());
            this.isTrainer.setImageResource(R.drawable.ic_green_checked);
        }
        else
            this.trainer_for.setText("");

    }

    private void setFBprofilelink(boolean type)
    {
        ImageView fb_icon = (ImageView)root.findViewById(R.id.fb_icon);
        TextView link = (TextView)root.findViewById(R.id.fb_link);

        if(type)
        {
            fb_icon.setImageResource(R.drawable.ic_fb);
            link.setText("See facebook profile");
            link.setBackgroundResource(R.drawable.link_background);
            link.setTypeface(null, Typeface.ITALIC);

            link.setClickable(true);

          final String url = "http://www.facebook.com/"+1645903195;//this.currentMember.getFacebook_ID();


            link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent i =  new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                    startActivity(i);
                }
            });
        }



    }


}
