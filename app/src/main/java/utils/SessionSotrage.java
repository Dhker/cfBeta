package utils;

import android.content.Context;
import android.content.SharedPreferences;

import model.ChessProfile;
import model.Member;
import model.Title;
import model.TrainerFor;

/**
 * Created by Dhker on 1/3/2016.
 */
public class SessionSotrage

{
    public static Member CurrentSessionMember ;

    public static void saveCurrentSessionMember(Member member,Context _context)
    {
        CurrentSessionMember=member ;

        SharedPreferences pref = _context.getSharedPreferences("Chessfamily", 0);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt("id", member.getID());
        if(member.getName()!=null)
        editor.putString("name",member.getName());
        if(member.getLast_Name()!=null)
            editor.putString("lastname",member.getLast_Name());
        if(member.getBirthday()!=null)
            editor.putString("birthday", member.getBirthday().toString());
        if(Integer.valueOf(member.getGender())!=null)
            editor.putInt("gender", member.getGender());
        if(member.getFacebook_ID()!=null)
            editor.putString("facebookid", member.getFacebook_ID());
        if(member.getEmail()!=null)
            editor.putString("email",member.getEmail());
        if(member.getPhoto()!=null)
            editor.putString("photo",member.getPhoto());
        if(member.getProfile()!=null)
        {
            editor.putInt("titled", member.getProfile().getIsTitled());
            if(member.getProfile().getTitle()!=null)
            editor.putString("title",member.getProfile().getTitle().toString());
            editor.putInt("player",member.getProfile().getIsPlayer());
            editor.putInt("arbiter",member.getProfile().getIsArbiter());
            editor.putInt("organizer",member.getProfile().getIsOrganizer());
            editor.putInt("trainer", member.getProfile().getIsTrainer());
            if(member.getProfile().getTrainerLevel()!=null)
            editor.putString("trainerlevel",member.getProfile().getTrainerLevel().toString());


        }
        if(Integer.valueOf(member.getAvailble())!=null)
            editor.putInt("available",member.getAvailble());
        if(Integer.valueOf(member.getStatus())!=null)
            editor.putInt("status",member.getStatus());

editor.commit();

    }


    public static void getCurrentSessionMember(Context _context)
    {
        SharedPreferences pref = _context.getSharedPreferences("Chessfamily", 0);
        Member member = new Member();
        try {
            member.setBirthday(ChessFamilyUtils.convertStringToDate(pref.getString("birthday",null)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        member.setFacebook_ID(pref.getString("facebookid", null));
        member.setID(pref.getInt("id", 1));
        member.setAvailble(pref.getInt("available", 1));
        member.setLast_Name(pref.getString("lastname", null));
        member.setName(pref.getString("name", null));
        member.setEmail(pref.getString("email", null));
        member.setPhoto(pref.getString("photo", null));

        ChessProfile chessProfile = new ChessProfile() ;

        chessProfile.setIsArbiter(pref.getInt("arbiter", 0));
        chessProfile.setIsPlayer(pref.getInt("player", 1));
        chessProfile.setIsOrganizer(pref.getInt("organizer", 0));
        chessProfile.setIsTitled(pref.getInt("titled", 0));
        chessProfile.setIsTrainer(pref.getInt("trainer", 0));

        if(pref.getString("trainerlevel",null)!=null)

        chessProfile.setTrainerLevel(TrainerFor.valueOf(pref.getString("trainerlevel", null)));

        if(pref.getString("title",null)!=null)

            chessProfile.setTitle(Title.valueOf(pref.getString("title", null)));


        member.setProfile(chessProfile);


         CurrentSessionMember=member ;
    }


    public static boolean checkLogin(Context _context)
    {
        SharedPreferences pref = _context.getSharedPreferences("Chessfamily", 0); // 0 - for private mode
        return pref.getBoolean("isLogged",false);

    }
    public static void SessionLogin(Context _context)
    {
        SharedPreferences pref = _context.getSharedPreferences("Chessfamily", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isLogged",true);
        editor.commit();
    }
    public static void SessionLogout(Context _context)
    {
        SharedPreferences pref = _context.getSharedPreferences("Chessfamily", 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        CurrentSessionMember=null ;

    }



}
