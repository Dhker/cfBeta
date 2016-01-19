package utils;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.fide.ae.chessfamilybeta.DashboardActivity;
import com.fide.ae.chessfamilybeta.LoginActivity;

/**
 * Created by Dhker on 12/24/2015.
 */
public class App extends Application {
    private static boolean sFirstRun = false;


    public static boolean fetchFirstRun() {
        boolean old = sFirstRun;
        sFirstRun = false;
        return old;
    }

    //--called when app process is created--
    @Override
    public void onCreate() {
        super.onCreate();


        if (fetchFirstRun())
  //  ParsePushNotif.initParse(this);
        sFirstRun = true;
    }





}