package utils;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import static utils.ChessFamilyUtils .*;
import model.Member;


/**
 * Created by Dhker on 12/22/2015.
 */
public class FacebookLogin {
    public static   CallbackManager callbackManager;


    public void  FacebookConnect(AppCompatActivity activity ) {

        FacebookSdk.sdkInitialize(activity);
        callbackManager = CallbackManager.Factory.create();


        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email", "user_photos", "public_profile"));

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("LoginActivity", response.toString());
                                // Get facebook data from login
                              Member   member = getFacebookData(object);
                                Log.d("member", member.toString())  ;


                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location");
                        request.setParameters(parameters);
                    request.executeAsync();
                }



                    @Override
                    public void onCancel() {
                        Log.d("test", "On cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("test", error.toString());
                    }
                });




    }









    private  Member getFacebookData(JSONObject object) {
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

