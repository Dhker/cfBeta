package utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.fide.ae.chessfamilybeta.DashboardActivity;
import com.fide.ae.chessfamilybeta.R;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import com.michael.easydialog.EasyDialog;
import com.readystatesoftware.viewbadger.BadgeView;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import dmax.dialog.SpotsDialog;
import model.Language;
import model.Member;
import repository.LanguageRepository;
import repository.LanguageRepositoryImpl;
import repository.MessageRepository;
import repository.MessageRepositoryImpl;
import repository.NotificationRepository;
import repository.NotificationRepositoryImpl;

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

    public static void attchFragment(AppCompatActivity activity,Fragment fragment,int container)
    {
        FragmentManager fragMan = activity.getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        int i=0;
        i++ ;
        fragTransaction.add(container, fragment , "fragment" + i).commit();

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

   public static String getUnreadMessagesNumber()
    {
        AsyncTask<String,String,Integer> serviceCall =

        new AsyncTask<String , String , Integer>()
        {
   Integer result   ;


            @Override
            protected void onPreExecute() {


            }

            @Override
            protected void onProgressUpdate(String... values) {

                Log.d("Progress Update","true") ;

            }

            @Override
            protected  Integer doInBackground(String... params) {



             result =null ;

                MessageRepository messageRepository = new MessageRepositoryImpl() ;
                try {
                    result = Integer.valueOf(messageRepository.getUnreadMessages(params[0]));
                    Log.d("value",result.toString());
                } catch (Exception e) {
                    Log.d("value",e.getMessage());
                    e.printStackTrace();
                }

                return result;

            }

            @Override
            protected void onPostExecute(Integer result) {


            }




        };
         serviceCall.execute(String.valueOf(SessionSotrage.CurrentSessionMember.getID()));
       /* try {
            return serviceCall.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "0" ;
*/
        return "" ;
    }
    public static String getUnreadNotificationNumber(String memberID)
    {
        AsyncTask<String,String,Integer> serviceCall =

                new AsyncTask<String , String , Integer>()
                {
                    Integer result   ;


                    @Override
                    protected void onPreExecute() {


                    }

                    @Override
                    protected void onProgressUpdate(String... values) {

                        Log.d("Progress Update","true") ;

                    }

                    @Override
                    protected  Integer doInBackground(String... params) {



                        result =null ;

                        NotificationRepository notificationRepository = new NotificationRepositoryImpl() ;
                        try {
                            result = Integer.valueOf(notificationRepository.getUnreadNotifications(params[0]));
                            Log.d("value",result.toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return result;

                    }

                    @Override
                    protected void onPostExecute(Integer result) {


                    }




                };
        serviceCall.execute(memberID);
        try {
            return serviceCall.get().toString();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "0" ;

    }


    public static void logoutActivity(final AppCompatActivity activity)
    {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("LOGOUT");
        activity.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("onReceive", "Logout in progress");
                //At this point you should start the login activity and finish this one
                activity.finish();
            }
        }, intentFilter);


    }

    private static EasyDialog easeyDialog;
    public static void EasyDialog(View view,Context context,View attachedto)
    {

        if(view!=null)
        {
        easeyDialog =new EasyDialog(context)
                // .setLayoutResourceId(R.layout.layout_tip_content_horizontal)//layout resource id
                .setLayout(view)
                .setBackgroundColor(context.getResources().getColor(R.color.background_color_black))
                        // .setLocation(new location[])//point in screen
                .setLocationByAttachedView(attachedto)
                .setGravity(EasyDialog.GRAVITY_BOTTOM)
                .setAnimationTranslationShow(EasyDialog.DIRECTION_X, 1000, -600, 100, -50, 50, 0)
                .setAnimationAlphaShow(1000, 0.3f, 1.0f)
                .setAnimationTranslationDismiss(EasyDialog.DIRECTION_X, 500, -50, 800)
                .setAnimationAlphaDismiss(500, 1.0f, 0.0f)
                .setTouchOutsideDismiss(true)
                .setMatchParent(true)
                .setMarginLeftAndRight(24, 24)
                .setOutsideColor(context.getResources().getColor(android.R.color.transparent))
                .show();
}
    }

    public static void hideEasyDialog()
    {
        easeyDialog.dismiss();
    }


}
