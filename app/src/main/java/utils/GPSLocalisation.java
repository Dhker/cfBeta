package utils;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;



/**
 * Created by Dhker on 12/27/2015.
 */
public class GPSLocalisation implements LocationListener {

    private AppCompatActivity activity ;
    private double longitude,latitude ;
    private Location lastLocation ;

    public GPSLocalisation(AppCompatActivity activity) {
        this.activity=activity;
        LocationListener locListener =this;


        LocationManager locManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);




        lastLocation = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
       if (lastLocation != null) {

            this.latitude=lastLocation.getLatitude();
            this.longitude=lastLocation.getLongitude();
            Log.d("old","lat :  "+ this.latitude);
            Log.d("old","long :  "+    this.longitude);

            this.onLocationChanged(lastLocation);
        }


    }

    @Override
    public void onLocationChanged(Location location) {

        // Retrieving Latitude
        this.latitude=location.getLatitude();
// Retrieving getLongitude
        this.longitude=location.getLongitude();

        String text = "My Current Location is:\nLatitude = "
                + location.getLatitude() + "\nLongitude = "
                + location.getLongitude();
        Log.d("inside", getLatitude());

        //Toast.makeText(activity.getApplicationContext(), text, Toast.LENGTH_SHORT)
             //   .show();
    }
    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(activity.getApplicationContext(), "GPS Disabled",
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(activity.getApplicationContext(), "GPS Enabled",
                Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }


    public String getLongitude() {
        return String.valueOf(longitude);
    }

    public String getLatitude() {
        return String.valueOf(latitude);
    }
}
