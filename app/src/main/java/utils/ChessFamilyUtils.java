package utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.media.Image;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.fide.ae.chessfamilybeta.R;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Member;

/**
 * Created by wassim on 21/12/15.
 */
public class ChessFamilyUtils {


    // verify email
   public static boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches();
    }

    // create a nofity Dialog
   public  static void createNiftyDialog(String title , String message, Context context)
    {
        NiftyDialogBuilder.getInstance(context)
                .withMessage(message)
                .withTitle(title)
                .withDialogColor("#965a36")
                .withDuration(300)
                .withEffect(Effectstype.Shake)
                .show();


    }
    public static Date convertStringToDate (String date)throws Exception{

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            return formatter.parse(date);
        }catch(Exception e)
        {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/DD/YYYY");
            return formatter.parse(date);
        }

    }


    //EFFECT WHEN BUTTON IS CLICKED

    public static void ClickEffect(ImageButton view)

    {
        view.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {


                        v.setBackgroundColor(Color.GRAY);

                        break;
                    }
                    case MotionEvent.ACTION_UP:
                        // Your action here on button click
                    case MotionEvent.ACTION_CANCEL: {

                        v.setBackgroundColor(Color.TRANSPARENT);

                        break;
                    }
                }
                return true;
            }
        });
    }

    public static Member getFacebookData(JSONObject object) {
        Member member =null;
        try {
            member = new Member() ;




            if(object.has("id"))

                member.setFacebook_ID( object.getString("id")) ;
            if (object.has("first_name"))
                member.setName(object.getString("first_name"));
            if (object.has("last_name"))
                member.setLast_Name(object.getString("last_name"));
            if (object.has("email"))
                member.setEmail(object.getString("email"));
            if (object.has("gender"))
                member.setGender( object.getString("gender").equals("male")? 1:2);
            if (object.has("birthday"))

            {
                try {
                    member.setBirthday(convertStringToDate(object.getString("birthday")));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + member.getFacebook_ID()+ "/picture?width=200&height=150");
              Log.d("url", "" + profile_pic.toString());
                member.setPhoto(profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            //Log.d("member", member.toString()

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return member ;
    }
}
