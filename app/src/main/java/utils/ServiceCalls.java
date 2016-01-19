package utils;

import android.os.AsyncTask;
import android.util.Log;

import com.dd.processbutton.iml.ActionProcessButton;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import model.Language;
import model.MemberPublication;
import model.Photo;
import repository.LanguageRepository;
import repository.LanguageRepositoryImpl;
import repository.MemberPublicationRepository;
import repository.MemberPublicationRepositoryImpl;
import repository.MemberRepository;
import repository.MemberRepositoryImpl;

/**
 * Created by Dhker on 1/15/2016.
 */
public class ServiceCalls {





    //set member availibality
    public static void setAvailibilaty(String MemberId,String status)
    {
        AsyncTask<String,Void,Void>  serviceCall = new AsyncTask<String, Void, Void>() {
            @Override
            protected Void doInBackground(String... params) {

                MemberRepository memberRepository = new MemberRepositoryImpl();
                try {
                    Boolean b=memberRepository.setAvailibility(params[0], params[1]);
                    Log.d("setAvailibality",String.valueOf(b));
                } catch (Exception e) {
                    Log.d("setAvailibality",e.getMessage() );
                    e.printStackTrace();
                }

                return null ;
            }
        } ;

        serviceCall.execute(MemberId,status);
    }
    public static ArrayList<Language> languagesList;

    public static  void getAllLanguages()
    {

        AsyncTask<String,String,ArrayList<Language>> serviceCall = new AsyncTask<String, String, ArrayList<Language>>() {

            public ArrayList<Language> languages ;



            @Override
            protected ArrayList<Language> doInBackground(String... params) {
                LanguageRepository language = new LanguageRepositoryImpl() ;
                try {
                    Log.d("lang","test");
                    languages = language.getAllLanguages() ;

                    Log.d("lang",languages.toString());

                } catch (Exception e) {
                    Log.d("lang", e.getMessage());
                }
                return languages ;

            }



        } ;
        serviceCall.execute();
        try {
            languagesList=new ArrayList<Language>();
            languagesList= serviceCall.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static MemberPublication addPublication(MemberPublication publication, final ActionProcessButton button)
    {

        AsyncTask<MemberPublication,Integer,MemberPublication> call = new AsyncTask<MemberPublication, Integer, MemberPublication>() {

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values[0]);

                button.setMode(ActionProcessButton.Mode.PROGRESS);
                button.setProgress(values[0].intValue());
            }

            @Override
            protected MemberPublication doInBackground(MemberPublication... params) {
                publishProgress(50);
                MemberPublicationRepository memberPublicationRepository = new MemberPublicationRepositoryImpl();
                publishProgress(100);



                try {
                    return memberPublicationRepository.addMemberPublication(params[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null ;
            }
        } ;


        call.execute(publication);
        try {
            return call.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static void addPhotoToPublication(Photo photo,final ActionProcessButton button)
    {

        AsyncTask<Photo,Integer,Void> call = new AsyncTask<Photo, Integer, Void>() {
            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values[0]);
                button.setMode(ActionProcessButton.Mode.PROGRESS);
                button.setProgress(values[0]);


            }

            @Override
            protected Void doInBackground(Photo... params) {

                MemberPublicationRepository memberPublicationRepository = new MemberPublicationRepositoryImpl();
                String memberID =String.valueOf(params[0].getpublication().getMember().getID());
                String pubicationID=String.valueOf(params[0].getpublication().getId());


                try {
publishProgress(50);
                     memberPublicationRepository.PublicationaddPhoto(params[0].getPhoto(), memberID, pubicationID);
publishProgress(100);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.print(e.getMessage());
                }
                return null;
            }
        };call.execute(photo);
    }
}
