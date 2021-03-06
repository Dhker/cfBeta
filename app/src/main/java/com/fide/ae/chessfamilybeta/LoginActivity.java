package com.fide.ae.chessfamilybeta;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telecom.Call;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import Exceptions.UserCreateException;
import dmax.dialog.SpotsDialog;
import model.Member;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;

import utils.AsyncTaskResult;
import utils.ChessFamilyUtils;
import utils.FacebookLogin;
import utils.SessionSotrage;

import static utils.ChessFamilyUtils.*;


public class LoginActivity extends AppCompatActivity {


    // UI components
    private EditText passwordField ;
    private AutoCompleteTextView emailField ;
    private TextView register ;
    private ImageButton loginbtn;
    private ImageButton fbbtn;
    private TextView forgottext ;
    private CheckBox rememberMeBtn ;
    private FrameLayout forgotpass;

    //Preferences for storing user Login and Password
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;


    // User settings
    private String email ;
    private String password ;
    private boolean saveLogin;



    //facebook callbackManager
    private   CallbackManager callbackManager;


    // user repository
    private MemberRepository  repository = new MemberRepositoryImpl() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Chechking session

        if(SessionSotrage.checkLogin(this))
        {
            SessionSotrage.getCurrentSessionMember(LoginActivity.this);
            Intent intent = new Intent(this, DashboardActivity.class);

            startActivity(intent);
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


         // UI componenets Initilaization
        passwordField = (EditText)findViewById(R.id.password_f) ;
        loginbtn = (ImageButton)findViewById(R.id.email_btn);
        fbbtn= (ImageButton)findViewById(R.id.fb_btn);
        forgottext=(TextView)findViewById(R.id.forgot);

        emailField = (AutoCompleteTextView) findViewById(R.id.email_f) ;
        register = (TextView)findViewById(R.id.register_btn) ;
        rememberMeBtn =(CheckBox)findViewById(R.id.rememberme) ;
        forgotpass=(FrameLayout) findViewById(R.id.forgot_pass_click);

        passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
              //  if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                {
                   // startActivity(new Intent(LoginActivity.this, DashboardActivity.class));

                    Log.d("IME", "true") ;

                    try {

                        connectByEmail();

                    } catch (IllegalArgumentException e) {
                        Log.d("error", e.getMessage());
                        createNiftyDialog("Email and password ", "check your email and password" , LoginActivity.this);
                        return false ;
                    }

                    return true;
                }
               // return false;
            }
        });

        loadUserDetails();
       // Log.d("saveLogin", ""+saveLogin) ;

        this.loginButtonOnClick();
        this.setupForgotpassword();
        this.setupRegisterbutton();
        this.setupRemberme();


    }



    // Save the user email password in the preferences
    private void saveUserDetails(String email, String password , boolean saveMe)
    {


        if (saveMe) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("email", email);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();

        }

    }

   // load user settings from the  preferences
    private void loadUserDetails()
    {
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {

            email= loginPreferences.getString("email", "");
            password=loginPreferences.getString("password", "");

            //update UI
            emailField.setText(email);
            passwordField.setText(password);
            rememberMeBtn.setChecked(true);
        }
    }



    // private method that make a call to the web service API
    private void connectByEmail()
       {

           email = emailField.getText().toString();
           password = passwordField.getText().toString();
           saveLogin = rememberMeBtn.isChecked();
           if(email==null|| password==null)
           {
              //  Log.d("error","null") ;

               throw new IllegalArgumentException("Null values") ;
           }
           if(email.isEmpty()|| password.isEmpty())
           {
              //  Log.d("error","empty") ;

               throw new IllegalArgumentException("Empty values") ;
           }
           else
           {
                // Create a annoynme class that makes the call to web service
               new AsyncTask<String , String , AsyncTaskResult<Member>>()
               {
                   private SpotsDialog progressDialog;

                   @Override
                   protected void onPreExecute() {
                       progressDialog = new SpotsDialog(LoginActivity.this, R.style.progressDialogCustom);
                       progressDialog.show();

                   }

                   @Override
                   protected void onProgressUpdate(String... values) {

                   Log.d("Progress Update","true") ;

               }

                   @Override
                   protected  AsyncTaskResult<Member> doInBackground(String... params) {

                   Log.d("param[1]",params[1]) ;
                   Log.d("param[0]",params[0]) ;

                   AsyncTaskResult<Member> result =null ;
                   Member member = null ;

                   try {
                       member = repository.Connect(params[0],params[1]);
                       //  throw new Exception() ;

                       result= new AsyncTaskResult<Member>(member) ;

                       return result;
                   } catch (Exception e) {
                       Log.d("error", e.toString())    ;
                       e.printStackTrace();
                       result = new  AsyncTaskResult<Member>(e) ;
                       return result ;
                   }
                   // publishProgress();

               }

                   @Override
                   protected void onPostExecute( AsyncTaskResult<Member> result) {

                       progressDialog.dismiss();
                   if (result.getError() == null)
                   {
                       Log.d("isNull", ""+(null))    ;


                       Member member = (Member) result.getResult();

                       if(member!=null) {


                           SessionSotrage.saveCurrentSessionMember(member,LoginActivity.this);
                           Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);

                           SessionSotrage.SessionLogin(LoginActivity.this);
                           startActivity(intent);
                         //  LoginActivity.this.finish();

                       }
                       else
                       {
                           Log.d("member is null", "true" ) ;
                           String title = getResources().getString(R.string.user_not_found_title);
                           String message =getResources().getString(R.string.user_not_found_message);
                           createNiftyDialog(title, message, LoginActivity.this);
                       }



                   }else
                   {

                       Exception exception=    result.getError() ;

                       if(( exception instanceof UnknownHostException)||( exception instanceof ConnectTimeoutException)) {

                           String title =getResources().getString(R.string.time_out_title);
                           String message = getResources().getString(R.string.time_out_message);
                           createNiftyDialog(title, message , LoginActivity.this);

                       }

                   }



               }
               }.execute(email, password);
           }


       }

   // On click event for the Login button
    private void loginButtonOnClick()
    {
        this.loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    connectByEmail();

                } catch (IllegalArgumentException e) {
                    //Log.d("error", e.getMessage());
                    createNiftyDialog("Email and password ", "check your email and password" , LoginActivity.this);

                }

            }
        });


    }

    //Register bubutton action
    public void setupRegisterbutton()
    {
        register.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                register.setTextColor(getResources().getColor(R.color.colorPartieInformationTextEditProfil));
                return false ;
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));


            }

        });



     }


    // SEND RECOVERY EMAIL
    public void sendRecoverEmail(String emailRecover)
    {

        new AsyncTask<String, String, AsyncTaskResult<Boolean>>() {
            private SpotsDialog progressDialog;

            @Override
            protected void onPostExecute(AsyncTaskResult<Boolean> result) {






                progressDialog.dismiss();
                if (result.getError() == null) {
                    //    Log.d("isNull", "" + (null));


                    if (result.getResult() == true)
                    {
                        String title = getResources().getString(R.string.email);
                    String message = getResources().getString(R.string.emailSend);
                    createNiftyDialog(title, message,LoginActivity.this);
                    } else
                    {
                        String title = "Invalid email !!" ;
                        String message ="check you email address";
                        createNiftyDialog(title, message,LoginActivity.this);
                    }


                } else {

                    Exception exception = result.getError();

                    if ((exception instanceof UnknownHostException) || (exception instanceof ConnectTimeoutException)) {

                        String title = getResources().getString(R.string.time_out_title);
                        String message = getResources().getString(R.string.time_out_message);
                        createNiftyDialog(title, message,LoginActivity.this);
                    }

                }







            }

            @Override
            protected void onPreExecute() {
                progressDialog = new SpotsDialog(LoginActivity.this, R.style.progressDialogCustom);
                progressDialog.show();

            }

            @Override
            protected void onProgressUpdate(String... values) {

                Log.d("Progress Update", "true");

            }

            @Override
            protected AsyncTaskResult<Boolean> doInBackground(String... params) {



                try {
                    boolean success = repository.sendForgetPasswordEmail(params[0]) ;
                    //  throw new Exception() ;
                    Log.d("send",""+success) ;
                    AsyncTaskResult   result = new AsyncTaskResult<Boolean>(success);

                    return result;
                } catch (Exception e) {
                    Log.d("error", e.toString());
                    e.printStackTrace();
                    AsyncTaskResult  result = new AsyncTaskResult<Member>(e);
                    return result;
                }
                // publishProgress();

            }

        }.execute(emailRecover);

    }




    public void setupForgotpassword()
    {

        this.forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                forgottext.setTextColor(getResources().getColor(R.color.colorPartieInformationTextEditProfil));

                LinearLayout dialogLayout = new LinearLayout(LoginActivity.this);
                dialogLayout.setOrientation(LinearLayout.VERTICAL);


                final EditText edittext = new EditText(LoginActivity.this);


                edittext.setBackgroundResource(R.drawable.border_style);


                Button confirm = new Button(LoginActivity.this);
                confirm.setBackgroundColor(getResources().getColor(R.color.Linearcadre1));

                confirm.setText(R.string.confirm);
                dialogLayout.addView(edittext);
                FrameLayout layout = new FrameLayout(LoginActivity.this);
                layout.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 10));
                dialogLayout.addView(layout);
                dialogLayout.addView(confirm);

                final AlertDialog emaildialog = new AlertDialog.Builder((LoginActivity.this)).create();


                emaildialog.setTitle(getResources().getString(R.string.forgot_passwod));
                emaildialog.setMessage(getResources().getString(R.string.forgot_pass));


                emaildialog.setView(dialogLayout, 10, 10, 10, 10);

                emaildialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        forgottext.setTextColor(getResources().getColor(R.color.Black));
                    }
                });

                confirm.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //envoyer email ici

                        String emailRecover = edittext.getText().toString();
                        if (!emailRecover.isEmpty()) {
                            emaildialog.dismiss();
                            sendRecoverEmail(emailRecover);
                        }

                    }
                });


                emaildialog.show();

            }
        });


    }


    public void setupRemberme()
    {

       this.rememberMeBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               saveLogin = rememberMeBtn.isChecked();

               email = emailField.getText().toString();
               password = passwordField.getText().toString();
               saveUserDetails(email, password, saveLogin);

               Log.d("clicked", "" + true);
           }
       });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        register.setTextColor(getResources().getColor(R.color.Black));
    }

    @Override
    protected void onPause() {
        super.onPause();

        email = emailField.getText().toString() ;
        password =passwordField.getText().toString();
        saveUserDetails(email, password, saveLogin);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }






    public void registerFb (Member member){
        new  AsyncTask<Member , Member , AsyncTaskResult<Member>>()
        {



            @Override
            protected void onProgressUpdate(Member... values) {

                Log.d("Progress Update","true") ;

            }

            @Override
            protected  AsyncTaskResult<Member> doInBackground(Member... params) {



                AsyncTaskResult<Member> result =null ;

                Member member =params[0];

                try {
                    boolean success = repository.createMember(member);

                    //  throw new Exception() ;

                    result = new AsyncTaskResult<Member>(member);



                    return result;
                } catch (Exception e) {
                    Log.d("error", e.toString())    ;
                    e.printStackTrace();
                    result = new  AsyncTaskResult<Member>(e) ;
                    return result ;
                }

            }

            @Override
            protected void onPostExecute( AsyncTaskResult<Member> result) {

                if (result.getError() == null)
                {
                    Log.d("isNull", "" + (null))    ;


                    Member member =  result.getResult();

                    if(member!=null) {
                        Log.d("add", "false");
                        SessionSotrage.checkLogin(LoginActivity.this);
                        Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                        //intent.putExtra("member", member);

                        startActivity(intent);
                    }





                }else
                {

                    Exception exception=    result.getError() ;

                    if(( exception instanceof UnknownHostException)||( exception instanceof ConnectTimeoutException)) {

                        String title =getResources().getString(R.string.time_out_title);
                        String message = getResources().getString(R.string.time_out_message);
                        createNiftyDialog(title, message, LoginActivity.this) ;
                    }

                    if( exception instanceof UserCreateException)
                    {
                        {
                            //  Log.d("member is null", "true" ) ;
                            String title = getResources().getString(R.string.user_not_found_title);
                            String message =getResources().getString(R.string.user_not_found_message);
                            createNiftyDialog("enable to create ", "jjjjj", LoginActivity.this) ;
                        }
                    }

                    if( exception instanceof IllegalArgumentException)
                    {
                        {
                            //  Log.d("member is null", "true" ) ;
                            String title = getResources().getString(R.string.user_not_found_title);
                            String message =getResources().getString(R.string.user_not_found_message);
                            createNiftyDialog("enable to create ", "jjjjj", LoginActivity.this) ;
                        }
                    }
                }



            }
        }.execute(member) ;
    }



    public void setupFBbutton(View v) {
        FacebookConnect();


    }
    private void connectByFacebookId(Member member) throws  Exception {



        if(member==null)
        {
            Log.d("error","empty") ;

            throw new IllegalArgumentException("Empty values") ;
        }
        else
        {
            // Create a annoynme class that makes the call to web service
            new AsyncTask<Member , Member , AsyncTaskResult<Member>>()
            {
                private SpotsDialog progressDialog;

                @Override
                protected void onPreExecute() {
                    progressDialog = new SpotsDialog(LoginActivity.this, R.style.progressDialogCustom);
                    progressDialog.show();

                }

                @Override
                protected void onProgressUpdate(Member... values) {

                    Log.d("Progress Update","true") ;

                }

                @Override
                protected  AsyncTaskResult<Member> doInBackground(Member... params) {

                    Member param = params[0] ;
                    AsyncTaskResult<Member> result =null ;
                    Member member = null ;
                    Log.d("param" , ""+param) ;
                    try {
                        member = repository.connectWithFacebook(param.getFacebook_ID()) ;
                        //  throw new Exception() ;
                        Log.d("memberIn", ""+member) ;
                        if(member==null)
                        {

                            Log.d("register","fb") ;
                            registerFb(param);
                        }

                        result= new AsyncTaskResult<Member>(member) ;

                        return result;
                    } catch (Exception e) {
                        Log.d("error", e.toString())    ;
                        e.printStackTrace();
                        result = new  AsyncTaskResult<Member>(e) ;
                        return result ;
                    }
                    // publishProgress();

                }

                @Override
                protected void onPostExecute( AsyncTaskResult<Member> result) {

                    progressDialog.dismiss();
                    if (result.getError() == null)
                    {
                        Log.d("isNull", ""+(null))    ;


                        Member member = (Member) result.getResult();

                        if(member!=null) {

                            SessionSotrage.saveCurrentSessionMember(member,LoginActivity.this);

                            SessionSotrage.SessionLogin(LoginActivity.this);



                            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);

                            startActivity(intent);
                             LoginActivity.this.finish();

                        }else
                        {


                        }




                    }else
                    {

                        Exception exception=    result.getError() ;

                        if(( exception instanceof UnknownHostException)||( exception instanceof ConnectTimeoutException)) {

                            String title =getResources().getString(R.string.time_out_title);
                            String message = getResources().getString(R.string.time_out_message);
                            createNiftyDialog(title, message , LoginActivity.this);

                        }

                    }



                }
            }.execute(member);
        }


    }
    public void  FacebookConnect( ) {

        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();



        // Set permissions
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("email", "user_photos", "public_profile"));
        SpotsDialog progressDialog;


        progressDialog = new SpotsDialog(LoginActivity.this, R.style.progressDialogCustom);
        progressDialog.show();
        Log.d("ChessSucess", "onSuccess1") ;
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        Log.d("ChessSucess","onSuccess") ;


                        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("LoginActivity", response.toString());
                                // Get facebook data from login
                                Member member = getFacebookData(object);
                                Log.d("member", member.toString());


                                    try{
                                        if(member!=null) {
                                          Log.d("fbid", member.getFacebook_ID()) ;
                                            connectByFacebookId(member);
                                        }else
                                        {
                                            throw new Exception() ;
                                        }
                                    }catch(Exception e){
                                        String title ="facebook connection" ;
                                        String message = "Enable to connect with facebook " ;
                                        createNiftyDialog(title ,message ,LoginActivity.this);
                                            // /  LoginActivity.this.finish();
                                    }


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
                        Log.d("test", error.getMessage());
                        ChessFamilyUtils.createNiftyDialog("Erruer", error.getMessage(), LoginActivity.this);
                    }
                });




    }


}

