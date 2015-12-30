package utils;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseGeoPoint;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import java.util.LinkedList;

/**
 * Created by Dhker on 12/28/2015.
 */
public class ParsePushNotif {

   public static final String applicationID ="0Ej5SNPfwkMoz57PlZatSp4nbk8DuBwXUqjYbe0V" ;
    public static final String clientKey ="FUEv83u49TkaZMpNxGgd1cFLMQEnh3u9DaUZRJen";

    public static void initParse(Context context,int idMember)
    {
        Parse.enableLocalDatastore(context);

        Parse.initialize(context, applicationID, clientKey);
        ParseInstallation parseInstallation =  ParseInstallation.getCurrentInstallation() ;
        parseInstallation.put("member_id",idMember);
        parseInstallation.saveInBackground() ;
       // ParsePush.subscribeInBackground("Players") ;
        //sendNotificationToChannel("Players","hello");
    }

    //SUBSCRIBE IN A CHANNEL

    public static void subscribeChannel(String Channel)
    {
        ParsePush.subscribeInBackground(Channel);

    }


    //UNSCBSCRIBE CHANNEL

    public static void unsubscribeChannel(String Channel)
    {
        ParsePush.unsubscribeInBackground(Channel);

    }
    //SEND MESSAGE TO ONE CHANNEL

    public static void sendNotificationToChannel(String channel,String message)
    {
        ParsePush push = new ParsePush();
        push.setChannel(channel);
        push.setMessage(message);
        push.sendInBackground();


    }

    //SEND MESSAGE TO MULTIPLE CHANNEL
    public static void sendNotificationToMultipleChannel(String message,String ...channel)
    {
        LinkedList<String> channels = new LinkedList<String>();

        for(String ch : channel)
        channels.add(ch);

        ParsePush push = new ParsePush();
        push.setChannels(channels); // Notice we use setChannels not setChannel
        push.setMessage(message);
        push.sendInBackground();
    }

    //SEND PUSH TO A MEMBER

    public static void sendNotificationToMember(int memberID,String message)
    {
        ParseQuery pushQuery = ParseInstallation.getQuery();
        pushQuery.whereEqualTo("member_id",memberID);
        ParsePush push = new ParsePush();
        push.setQuery(pushQuery); // Set our Installation query
        push.setMessage(message);
        push.sendInBackground();

    }



    public void addCurrentLocation(double longitude,double latitude )
    {
        ParseGeoPoint location= new ParseGeoPoint(latitude,longitude);

        ParseInstallation currentInstallation = ParseInstallation.getCurrentInstallation();
        currentInstallation.put("location",location);

    }

    public static void sendNearByNotification(String message)

    {

    }


}
