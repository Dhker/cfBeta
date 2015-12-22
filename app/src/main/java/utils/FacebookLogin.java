package utils;

import android.app.Activity;
import android.content.Context;
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

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dhker on 12/22/2015.
 */
public class FacebookLogin {
    private  CallbackManager callbackManager;

    private AppCompatActivity activity ;

    public FacebookLogin(AppCompatActivity activityContext) {
        this.activity = activityContext;
        FacebookSdk.sdkInitialize(this.activity);
        callbackManager = CallbackManager.Factory.create();
        List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "public_profile");
        LoginManager.getInstance().logInWithReadPermissions(activityContext, permissionNeeds);


    }

    public void login()
    {
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {
                                        // Application code
                                        Log.v("LoginActivity", response.toString());
                                    }
                                });




                    }

                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onError(FacebookException exception) {

                    }
                });
    }

    public CallbackManager getCallbackManager()

    {
        return this.callbackManager ;
    }

    }





