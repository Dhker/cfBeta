package utils;

import android.app.Application;

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

        sFirstRun = true;
    }
}