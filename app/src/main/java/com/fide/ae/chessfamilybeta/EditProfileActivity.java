package com.fide.ae.chessfamilybeta;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import android.view.View.OnClickListener ;


import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import model.ChessProfile;
import model.Language;
import model.Member;
import model.Title;
import model.TrainerFor;
import repository.LanguageRepository;
import repository.LanguageRepositoryImpl;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;
import utils.AsyncTaskResult;
import utils.ChessFamilyUtils;
import utils.ImageFromCamGal;
import utils.ServiceCalls;
import utils.SessionSotrage;

import static utils.ChessFamilyUtils.convertStringToDate;

public class EditProfileActivity extends AppCompatActivity  implements  OnClickListener{

    private CheckBox isTitle,isArbiter,isOrganizer,isTrainer;
    private Spinner titles,trainer_for ;
    private CircleImageView profileImage ;
    private TextView birthday ;
    private EditText firstN, lastN, password ;
    private ImageFromCamGal editPhoto;
    private Button editBtn ;
    private Member member ;
    private MemberRepository memberRepository = new MemberRepositoryImpl();
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private String lastNameValue,firstNameValue,passwordValue ;
    private Date birthdayValue ;
    private int isTrainerValue=0,isOrganizerValue=0,isTitleValue=0,isArbiterValue=0 ;
    private Spinner language_list ;
    private ArrayList<String> Alllanguages;
    private ArrayList<String> allFlags ;
    private ArrayList<String> memberFlags ;
    private ArrayList< String> memberLanguages;
    private String[] values;
    private ImageButton addbtn ;
    private ArrayList<Language> languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_edit_profile);

        this.setupViews();
        SessionSotrage.getCurrentSessionMember(EditProfileActivity.this);
        member= SessionSotrage.CurrentSessionMember ;

this.language_list.setVisibility(View.INVISIBLE);
        this.setupLanguages();












        editPhoto=new ImageFromCamGal(this,profileImage,member);

        loadUserInformation(String.valueOf(member.getID()));
        //PUSH TEST/*
        /*Parse.initialize(this, "0Ej5SNPfwkMoz57PlZatSp4nbk8DuBwXUqjYbe0V", "FUEv83u49TkaZMpNxGgd1cFLMQEnh3u9DaUZRJen");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        ParseObject parse =ParseObject.createWithoutData(Member.class.getName(),"member_id");
        parse.put("member_id", "1");
        parse.saveInBackground();
        ParseInstallation obj = new ParseInstallation();*/
          //ParsePushBroadcastReceiver parsePush = new ParsePushBroadcastReceiver();





    }


private void setupLanguages()
{
    this.Alllanguages=new ArrayList<String>();
    this.allFlags=new ArrayList<String>();
    this.memberLanguages=new ArrayList<String>();
    this.memberFlags=new ArrayList<String>();




    if(member.getLanguages()!=null) {
        this.language_list.setVisibility(View.VISIBLE);
        String[] memberLanguagesLabel= new String[member.getLanguages().size()];
        for (int j = 0; j < member.getLanguages().size(); j++) {
            memberLanguagesLabel[j] = languages.get(j).getLabel();
            memberLanguages.add(member.getLanguages().get(j).getLabel());
            memberFlags.add(member.getLanguages().get(j).getFlag());

        }
        MyAdapter languageAdapter = new MyAdapter(this,R.layout.spoken_languages_item,memberLanguagesLabel,false);
        this.language_list.setAdapter(languageAdapter);


    }
}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.editPhoto.onActivityResult(requestCode, resultCode, data);
        Log.d("EditActivity", "Result") ;


    }

    private void setupViews()
    {
        this.language_list = (Spinner)findViewById(R.id.lang_list) ;
        this.addbtn=(ImageButton)findViewById(R.id.add_lang_btn);
        this.addbtn.setOnClickListener(this);
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


        //Bottom menu

        Bottom_Menu_Fragment bottom_menu_fragment = (Bottom_Menu_Fragment)getSupportFragmentManager().findFragmentById(R.id.bottom_menu_edit);
//bottom_menu_fragment.setdrawerMenu(super.drawerLayout);

            //ADD TITLES


        this.titles=(Spinner)findViewById(R.id.titles);
        //ADD TAINERS FOR
        this.trainer_for=(Spinner)findViewById(R.id.trainer_for);
        this.titles.setVisibility(View.INVISIBLE);
        this.trainer_for.setVisibility(View.INVISIBLE);
    }


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
String chosenLang ;

    private void addLanguage()
    {
        AsyncTask<String,Void,Void> serviceCall = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {
                LanguageRepository languageRepository = new LanguageRepositoryImpl();
                try {
                   boolean addlang = languageRepository.addLanguageToMember(params[0],params[1],false);
                    if(addlang) Log.d("successAdd","Yesss");
                    else Log.d("successAdd","Noooo");
                } catch (Exception e) {
                    Log.d("addingLangTest",e.getMessage());

                    e.printStackTrace();
                }
                return null;
            }
        }; serviceCall.execute(String.valueOf(member.getID()),chosenLang);
    }
    @Override
    public void onClick(View v) {
        //ADD LANGUAGE
        if(this.addbtn.equals(v))
        {
this.showLanguageDialog() ;
        }


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
        else if(v.equals(this.profileImage)) {
            this.editPhoto.selectImage();
        }

        else if (v.equals(this.editBtn))
        {
            Member member = new Member();
            ChessProfile chessProfile = new ChessProfile();
            chessProfile.setIsArbiter(this.isArbiterValue);
            chessProfile.setIsOrganizer(this.isOrganizerValue);
            chessProfile.setIsTitled(this.isTitleValue);
            if(this.titles.getSelectedItem()!=null)
                chessProfile.setTitle(Title.valueOf(this.titles.getSelectedItem().toString()));
            chessProfile.setIsTrainer(this.isTrainerValue);
            if(this.trainer_for.getSelectedItem()!=null)
                chessProfile.setTrainerLevel(TrainerFor.valueOf(this.trainer_for.getSelectedItem().toString()));

            member.setBirthday(birthdayValue);
            member.setPassword(this.passwordValue);
            member.setName(this.firstNameValue);
            member.setLast_Name(this.lastNameValue);
            member.setProfile(chessProfile);
            updateUserInformation(member);

        }
    }

    private void showLanguageDialog() {

        ServiceCalls.getAllLanguages();
        languages = ServiceCalls.languagesList;
        this.values= new String[languages.size()];
        for(int i=0;i<languages.size();i++)
        {
            this.values[i]=languages.get(i).getLabel();


            Alllanguages.add(languages.get(i).getLabel());
            allFlags.add(languages.get(i).getFlag());

        }


        MyAdapter adapter = new MyAdapter(this,R.layout.spoken_languages_item,this.values,true);



        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(getResources().getString(R.string.choose_lang));
        alertDialogBuilder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chosenLang=String.valueOf(languages.get(which).getId());
                Log.d("langTest",languages.get(which).getLabel());
                addLanguage();
                if(member.getLanguages()!=null)
                Log.d("MemberLang", member.getLanguages().toString());
                language_list.setVisibility(View.VISIBLE);
            }
        });
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();








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
                        updateUI(member) ;
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


public void updateUI(Member member)
{
    if (member.getPhoto()!=null)
    {
        Picasso.with(this).load(member.getPhoto()).into(this.profileImage);

    }

}

    public class MyAdapter extends ArrayAdapter<String>{
        private ArrayList<String> flags,languages ;


        public MyAdapter(Context context, int textViewResourceId, String[] values,Boolean all) {

            super(context, textViewResourceId,values);
            if(all)
            {
                this.flags=allFlags ;
                this.languages=Alllanguages;
            }
            else
            {
                this.flags=memberFlags ;
                this.languages=memberLanguages;
            }

        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.spoken_languages_item, parent, false);
            TextView label=(TextView)row.findViewById(R.id.language_label);


            label.setText(Alllanguages.get(position));




            ImageView flag=(ImageView)row.findViewById(R.id.flag);
            Picasso.with(getContext()).load(allFlags.get(position)).into(flag);
            return row;
        }
    }
}
