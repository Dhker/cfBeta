package com.fide.ae.chessfamilybeta;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import android.view.View.OnClickListener ;


import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.PushService;

import de.hdodenhof.circleimageview.CircleImageView;
import model.ChessProfile;
import model.Member;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;
import utils.AsyncTaskResult;
import utils.ImageFromCamGal;

import static utils.ChessFamilyUtils.convertStringToDate;

public class EditProfileActivity extends AppCompatActivity implements  OnClickListener{

    private CheckBox isTitle,isArbiter,isOrganizer,isTrainer;
    private Spinner titles,trainer_for ;
    private CircleImageView profileImage ;
    private TextView birthday ;
    private EditText firstN, lastN, password ;
    private ImageFromCamGal editPhoto;
    private Button editBtn ;
    private Member member ;
    private MemberRepository memberRepository = new MemberRepositoryImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_edit_profile);
        this.setupViews();
        editPhoto=new ImageFromCamGal(this,profileImage);

        //PUSH TEST
        Parse.initialize(this, "0Ej5SNPfwkMoz57PlZatSp4nbk8DuBwXUqjYbe0V", "FUEv83u49TkaZMpNxGgd1cFLMQEnh3u9DaUZRJen");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseObject parse =ParseObject.createWithoutData(Member.class.getName(),"member_id");
        parse.put("member_id", "1");
        parse.saveInBackground();
        ParseInstallation obj = new ParseInstallation();
          //ParsePushBroadcastReceiver parsePush = new ParsePushBroadcastReceiver();






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.editPhoto.onActivityResult(requestCode, resultCode, data);
    }

    private void setupViews()
    {
        this.isTitle=(CheckBox)findViewById(R.id.is_titled) ;
        this.isTitle.setOnClickListener(this);
        this.isArbiter=(CheckBox)findViewById(R.id.is_arbiter) ;
        this.isOrganizer=(CheckBox)findViewById(R.id.is_organizer) ;
        this.isTrainer=(CheckBox)findViewById(R.id.is_trainer) ;
        this.isTrainer.setOnClickListener(this);
        this.profileImage=(CircleImageView)findViewById(R.id.profile_image) ;
        this.profileImage.setOnClickListener(this);
        this.birthday = (TextView)findViewById(R.id.birthday_button);
        this.birthday.setOnClickListener(this);
        this.lastN=(EditText)findViewById(R.id.last_name);
        this.firstN=(EditText)findViewById(R.id.first_name);
        this.password=(EditText)findViewById(R.id.password);
        this.editBtn=(Button)findViewById(R.id.submit_edit);



            //ADD TITLES


        this.titles=(Spinner)findViewById(R.id.titles);
        //ADD TAINERS FOR
        this.trainer_for=(Spinner)findViewById(R.id.trainer_for);
        this.titles.setVisibility(View.INVISIBLE);
        this.trainer_for.setVisibility(View.INVISIBLE);
    }
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String lastNameValue,firstNameValue,passwordValue ;
    private Date birthdayValue ;
    private int isTrainerValue=0,isOrganizerValue=0,isTitleValue=0,isArbiterValue=0 ;

    private  void initValues()
    {

        if(this.isOrganizer.isChecked())
            this.isOrganizerValue=1 ;
        if (this.isTrainer.isChecked())
            this.isTrainerValue=1 ;
        if(this.isArbiter.isChecked())
            this.isArbiterValue=1;
        if(this.isTitle.isChecked())
            this.isTitleValue =1;
        this.lastNameValue=this.lastN.getText().toString();
        this.firstNameValue=this.firstN.getText().toString();
        this.passwordValue=this.password.getText().toString();
    }


    private void dialogBirthday(){
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        setDateTimeField();

    }


    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                try {
                    birthdayValue=convertStringToDate((dateFormatter.format(newDate.getTime())));
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        this.fromDatePickerDialog.show();


    }


    public void UpdateBtn(View v)
    {
        this.initValues();


    }

    @Override
    public void onClick(View v) {
        if (v.equals(this.isTitle)) {
            if(this.isTitle.isChecked())
            this.titles.setVisibility(View.VISIBLE);
            else
                this.titles.setVisibility(View.INVISIBLE);


        }
        else if (v.equals(this.isTrainer))
        {
            if(this.isTrainer.isChecked())
                this.trainer_for.setVisibility(View.VISIBLE);
            else
                this.trainer_for.setVisibility(View.INVISIBLE);


        }
        else if(v.equals(this.birthday)) {
            dialogBirthday();

        }
        else if(v.equals(this.profileImage))
            this.editPhoto.selectImage();

        else if (v.equals(this.editBtn))
        {
            ChessProfile chessProfile = new ChessProfile();
            chessProfile.setIsArbiter(this.isArbiterValue);
            this.member.setBirthday(birthdayValue);
            this.member.setPassword(this.passwordValue);
            this.member.setName(this.firstNameValue);
            this.member.setLast_Name(this.lastNameValue);
            this.member.setProfile(chessProfile);

        }
    }

    public void updateUserInformation(Member member)
    {

        if((member==null))
        {
            throw new IllegalArgumentException();

        } else
        {



            new AsyncTask<Member  ,Member , AsyncTaskResult<Boolean>>(){

                @Override
                protected void onPostExecute(AsyncTaskResult<Boolean> result) {

                    if (result.getError() == null)
                    {
                        Boolean success = result.getResult()   ;
                        Log.d("Updated",""+success) ;
                        // updateUI(member);
                    }else
                    {
                    }


                }

                @Override
                protected AsyncTaskResult<Boolean> doInBackground(Member... params) {


                    Member member1  = params[0] ;
                    AsyncTaskResult<Boolean> result =null ;



                    try {

                        Boolean sucess = memberRepository.updateMemberInformation(member1) ;
                        memberRepository.updateMemberChessProfile(member1);
                        result= new AsyncTaskResult<Boolean >(sucess) ;

                        return result;
                    } catch (Exception e) {
                        Log.d("error", e.toString())    ;
                        e.printStackTrace();
                        result = new  AsyncTaskResult<Boolean>(e) ;
                        return result ;
                    }



                }
            }.execute(member);

        }

    }

    // load user informations
    public void loadUserInformation(String id)
    {

        if((id==null)||(id.isEmpty()))
        {
            throw new IllegalArgumentException();

        } else
        {



            new AsyncTask<String  ,String , AsyncTaskResult<Member>>(){

                @Override
                protected void onPostExecute(AsyncTaskResult<Member> result) {

                    if (result.getError() == null)
                    {
                        member = result.getResult()   ;
                        Log.d("new", "" + member) ;
                     //   updateUI(member);
                    }else
                    {
                    }


                }

                @Override
                protected AsyncTaskResult<Member> doInBackground(String... params) {


                    String id  = params[0] ;
                    AsyncTaskResult<Member> result =null ;



                    try {

                        Member member = memberRepository.getMemberById(id) ;
                        result= new AsyncTaskResult<Member>(member) ;

                        return result;
                    } catch (Exception e) {
                        Log.d("error", e.toString())    ;
                        e.printStackTrace();
                        result = new  AsyncTaskResult<Member>(e) ;
                        return result ;
                    }



                }
            }.execute(id);

        }

    }


}
