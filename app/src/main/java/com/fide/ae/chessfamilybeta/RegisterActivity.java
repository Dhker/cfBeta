package com.fide.ae.chessfamilybeta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import Exceptions.UserCreateException;
import model.Member;
import repository.MemberRepository;
import repository.MemberRepositroyImpl;
import utils.AsyncTaskResult;

import static utils.ChessFamilyUtils.convertStringToDate;
import static utils.ChessFamilyUtils.createNiftyDialog;
import static utils.ChessFamilyUtils.isEmailValid;

public class RegisterActivity extends AppCompatActivity {


    private EditText nameField,lastnameField,emailField,passwordField,password2Field,birthdayField;
    private RadioGroup genderField ;
    private Button registerbtn;
    private String email ;
    private String password,password2 ;
    private String name ;
    private String lastname;
    private String birthday;
    private int gender;


    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);




        registerbtn=(Button)findViewById(R.id.BTNRegister);
        nameField =(EditText)findViewById(R.id.name_f);
        lastnameField=(EditText)findViewById(R.id.name_l);
        emailField=(EditText)findViewById(R.id.email_f);
        passwordField=(EditText)findViewById(R.id.password_f);
        genderField=(RadioGroup)findViewById(R.id.genderGroupe);
        password2Field=(EditText)findViewById(R.id.password_c);
        this.birthdayField=(EditText)findViewById(R.id.birthday_f);
        this.birthdayField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogBirthday();

            }
        });
        passwordField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
                    startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                    return true;
                }
                return false;
            }
        });

        this.RegisterButtonOnClick();




    }

    private void RegisterButtonOnClick() {
          this.registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{


                    name=nameField.getText().toString();
                    lastname=lastnameField.getText().toString();
                    password = passwordField.getText().toString() ;
                    email = emailField.getText().toString() ;
                    birthday = birthdayField.getText().toString() ;
                    gender=genderField.getCheckedRadioButtonId();
                    password2=password2Field.getText().toString() ;
                    //Log.d("saveLogin", ""+saveLogin) ;


                    RegisterService(name, lastname, email, password, birthday, password2) ;

                }catch(Exception e)
                {
                    String message = e.getMessage() ;
                    createNiftyDialog("Error " ,message, RegisterActivity.this) ;
                }
            }
        });

    }

    private void RegisterService(String name, String lastname, String email, String password,String birthday,String password2) throws Exception {

        if((name==null)||(lastname==null)||(email==null) ||(password==null) ||(password2==null)||(lastname==null)  || (birthday==null) )
        {throw new IllegalArgumentException("Null values") ;}

        if(name.isEmpty()||lastname.isEmpty()||email.isEmpty()|| password.isEmpty()|| birthday.isEmpty()|| password2.isEmpty())
        {//  Log.d("error","empty") ;
            throw new IllegalArgumentException("Empty values") ;
        }
        else if (!password.equals(password2))
        {

            throw new IllegalArgumentException("password not match") ;

        }
        else if (!isEmailValid(email)){

            throw new IllegalArgumentException("invalid email") ;

        }

        else
        {
            Member member = new Member();
            member.setName(name);
            member.setLast_Name(lastname);
            member.setEmail(email);
            member.setPassword(password);
            member.setBirthday(convertStringToDate(birthday));

                Register(member) ;

        }
    }



    public void RegisterBtn(View v)
    {
        registerbtn.setBackgroundColor(getResources().getColor(R.color.colorTextEditProfil1));


    }

    private void dialogBirthday(){
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        findViewsById();

        setDateTimeField();

    }
    private void findViewsById() {

        this.birthdayField.setInputType(InputType.TYPE_NULL);
        birthdayField.requestFocus();


    }
    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                birthdayField.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        this.fromDatePickerDialog.show();

    }

    public void Register (Member member){
        new  AsyncTask<Member , Member , AsyncTaskResult<Member>>()
        {

            MemberRepository repository = new MemberRepositroyImpl() ;

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
                // publishProgress();

            }

            @Override
            protected void onPostExecute( AsyncTaskResult<Member> result) {

                if (result.getError() == null)
                {
                    Log.d("isNull", "" + (null))    ;


                    Member member =  result.getResult();

                    if(member!=null) {
                        Log.d("member is null", "false");
                        Intent intent = new Intent(RegisterActivity.this, DashboardActivity.class);
                        intent.putExtra("member", member);

                        startActivity(intent);
                    }





                }else
                {

                    Exception exception=    result.getError() ;

                    if(( exception instanceof UnknownHostException)||( exception instanceof ConnectTimeoutException)) {

                        String title =getResources().getString(R.string.time_out_title);
                        String message = getResources().getString(R.string.time_out_message);
                        createNiftyDialog(title, message, RegisterActivity.this) ;
                    }

                    if( exception instanceof UserCreateException)
                    {
                        {
                          //  Log.d("member is null", "true" ) ;
                            String title = getResources().getString(R.string.user_not_found_title);
                            String message =getResources().getString(R.string.user_not_found_message);
                            createNiftyDialog("enable to create ", "jjjjj", RegisterActivity.this) ;
                        }
                    }

                    if( exception instanceof IllegalArgumentException)
                    {
                        {
                            //  Log.d("member is null", "true" ) ;
                            String title = getResources().getString(R.string.user_not_found_title);
                            String message =getResources().getString(R.string.user_not_found_message);
                            createNiftyDialog("enable to create ", "jjjjj", RegisterActivity.this) ;
                        }
                    }
                }



            }
        }.execute(member) ;
    }
}