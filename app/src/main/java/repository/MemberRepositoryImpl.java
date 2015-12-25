package repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import Exceptions.UserCreateException;
import configuration.Configuration;
import model.ChessProfile;
import model.City;
import model.Country;
import model.Member;
import model.Title;
import model.TrainerFor;
import utils.JSONParser;

public class MemberRepositoryImpl implements MemberRepository {


	  private final String URL  =   Configuration.URL ; 
	  private final String AUTH = Configuration.AUTH ;
    private JSONParser jsonParser  = new JSONParser() ;
	@Override
	
	// Tested 
	public Member getMemberById(String id)throws Exception{
		// TODO Auto-generated method stub
	    
		// RestAPI Request 
	//	JSONParser jsonParser  = new JSONParser() ;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_get"));
	    params.add(new BasicNameValuePair("id", id));
	    
	    
	    //RestAPI JSON Result
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    //System.out.println(json);
	    
	    boolean  success = json.getInt("success") ==  1; 
	    if (success){
	    	
	    	
	    // Parsing JSON to get member information
	    JSONObject memberJson =  json.getJSONObject("member");
	    
	    
	    //Json to Object Mapping 
		Member Result = jsonToMember(memberJson) ; 
		//Result.setBirthday(new Date(memberJson.getString("birthday")));
		
		  ChessProfile memberProfile = jsonToChessProfile(memberJson) ;
	
	//	System.out.println(memberProfile);
	
		
        Result.setProfile(memberProfile);
		
		
	 	//System.out.println(Result);
		
		return Result;
	    }else
	    {
	    	return  null ; 
	    }
		
		
	}

	@Override
	public boolean  createMember(Member member) throws Exception {
		// TODO Auto-generated method stub
	
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_add"));

		if(member.getName()!=null)
	    params.add(new BasicNameValuePair("name",member.getName() ));
		if(member.getLast_Name()!=null )
	    params.add(new BasicNameValuePair("last_name", member.getLast_Name()));
		if(member.getEmail()!=null)
	    params.add(new BasicNameValuePair("email", member.getEmail()));
		if(member.getGender()!=0 )
	    params.add(new BasicNameValuePair("gender", String.valueOf(member.getGender())));
		if(member.getPassword()!=null)
	    params.add(new BasicNameValuePair("password",member.getPassword()));
		if(member.getOS()!=null)
	    params.add(new BasicNameValuePair("os",member.getOS()));
		if(member.getFacebook_ID()!=null)
		params.add(new BasicNameValuePair("facebook_id",member.getFacebook_ID()));

		     System.out.println(params);
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		boolean sucess =json.getInt("success")==1  ;
		if(!sucess)
		{
			throw new UserCreateException();
		}

		return sucess ;
	}

	@Override
	public ArrayList<Member> getMemberFriendList(Member member) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean  updateMemberInformation(Member member) throws Exception{
		// TODO Auto-generated method stub
	
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_edit"));
	    params.add(new BasicNameValuePair("id",String.valueOf(member.getID())));
	    params.add(new BasicNameValuePair("name", member.getName()));
	    params.add(new BasicNameValuePair("last_name", member.getLast_Name()));
	    params.add(new BasicNameValuePair("email", member.getEmail()));
	    params.add(new BasicNameValuePair("gender", String.valueOf(member.getGender())));
	    params.add(new BasicNameValuePair("password",member.getPassword()));
	    params.add(new BasicNameValuePair("os", member.getOS()));
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String date = formatter.format(member.getBirthday()) ; 
	    params.add(new BasicNameValuePair("birthday",date));
	    System.out.println(params) ; 
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
	   // System.out.println("update"+json) ; 
	    return (json.getInt("success")==1) ; 
	    
	}

	@Override
	public void updateMemberChessProfile(Member member) throws Exception
	{
		// TODO Auto-generated method stub
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_chess_profile_edit"));
	    params.add(new BasicNameValuePair("id", String.valueOf(member.getID()) ));
		ChessProfile  memberProfile  = member.getProfile() ; 
		
		 	params.add(new BasicNameValuePair("is_arbiter", String.valueOf(memberProfile.getIsArbiter())));
		    params.add(new BasicNameValuePair("organizer",  String.valueOf(memberProfile.getIsOrganizer())));
		    params.add(new BasicNameValuePair("is_player", String.valueOf(memberProfile.getIsPlayer())));
		    params.add(new BasicNameValuePair("is_trainer", String.valueOf(memberProfile.getIsTrainer())));    
		    params.add(new BasicNameValuePair("title",memberProfile.getTitle().toString()));    
		    params.add(new BasicNameValuePair("lesson_level",memberProfile.getTrainerLevel().toString()));    
		    params.add(new BasicNameValuePair("is_titled_player" ,String.valueOf(memberProfile.getIsTitled())));    

		    JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
		     System.out.println("update"+json) ; 
	}

	@Override
	public Member Connect(String Email, String password) throws Exception {
		// TODO Auto-generated method stub
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_connect"));
	    params.add(new BasicNameValuePair("email", Email));
	    params.add(new BasicNameValuePair("password", password));

	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		// System.out.println(json);
	    if ((json!= null )&&( (json.getInt("success")==1) ))
	    	
	    {
			JSONObject memberJson =  json.getJSONObject("member");
	    
	    
	    //Json to Object Mapping 
	   return  jsonToMember(memberJson) ;
		
	    }
	    
		return null;
	}






	@Override
	public Member connectWithFacebook(String fbId) throws Exception {
		// TODO Auto-generated method stub


		List<NameValuePair> params = new ArrayList<NameValuePair>();
		Member Result =null ;
		params.add(new BasicNameValuePair("authentication", AUTH));
		params.add(new BasicNameValuePair("action", "member_connect"));
		params.add(new BasicNameValuePair("facebook_id", fbId));
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		System.out.println(json);
		if (json.getInt("success") == 1) {

			JSONObject memberJson = json.getJSONObject("member");
			Result = jsonToMember(memberJson) ;
		/*	Country country = new Country();
			City city = new City();
			ChessProfile ChessProfile = new ChessProfile();
			Result.setID(memberJson.getInt("id"));
			Result.setEmail(memberJson.getString("email"));
			Result.setLast_Name(memberJson.getString("last_name"));
			Result.setGender(memberJson.getInt("gender"));
			Result.setName(memberJson.getString("name"));
			Result.setOS(memberJson.getString("os"));
			Result.setFacebook_ID(memberJson.getString("facebook_id"));
			Result.setGoogle_ID(memberJson.getString("google_id"));
			Result.setDeviceToken(memberJson.getString("device_token"));
			Result.setAvailble(memberJson.getInt("availability"));
			Result.setPhoto(memberJson.getString("photo"));
			Result.setStatus(memberJson.getInt("status"));
			country.setId(memberJson.getInt("residence_countryid"));
			city.setId(memberJson.getInt("residence_cityid"));
			//Result.setID_city(city);
			Result.setID_country(country);
			ChessProfile.setIsArbiter(memberJson.getInt("is_arbiter"));
			ChessProfile.setIsOrganizer(memberJson.getInt("organizer"));
			ChessProfile.setIsPlayer(memberJson.getInt("is_player"));
			ChessProfile.setIsTitled(memberJson.getInt("is_titled_player"));
			//ChessProfile.setTitle(Enum.valueOf(Title.class, memberJson.getString("title")));
			ChessProfile.setIsTrainer(memberJson.getInt("is_trainer"));
		//	ChessProfile.setTrainerLevel(Enum.valueOf(TrainerFor.class, memberJson.getString("lesson_level")));
			Result.setProfile(ChessProfile);*/
		}
		return Result ;
	}

	@Override
	public Member connectWithGoogle(String gId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendForgetPasswordEmail(String email) throws Exception {


		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("authentication", AUTH));
		params.add(new BasicNameValuePair("action", "forget_password"));
		params.add(new BasicNameValuePair("email",email));

		//RestAPI JSON Result
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		//System.out.println(json);

		boolean  success = json.getInt("success") ==  1;
		return success;
	}


	private Member jsonToMember(JSONObject  memberJson) throws Exception
	{
		Member Result = new Member() ;
		try{
			Result.setID(memberJson.getInt("id"));
		}catch(Exception e)
		{}
		try{
			Result.setEmail(memberJson.getString("email"));
		}catch(Exception e)
		{}try{
		Result.setLast_Name(memberJson.getString("last_name"));
		}catch(Exception e)
		{}try{
		Result.setGender(memberJson.getInt("gender"));
		}catch(Exception e)
		{}try{
		Result.setName(memberJson.getString("name"));

		}catch(Exception e)
		{}Result.setOS(memberJson.getString("os"));
		try{
		}catch(Exception e)
		{}Result.setFacebook_ID(memberJson.getString("facebook_id"));
		try{
		}catch(Exception e)
		{}Result.setGoogle_ID(memberJson.getString("google_id"));
		try{
		}catch(Exception e)
		{}try{Result.setDeviceToken(memberJson.getString("device_token"));
		}catch(Exception e)
		{}	Result.setAvailble(memberJson.getInt("availability"));
		try {

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateInString = memberJson.getString("birthday");
			Date date = 	formatter.parse(dateInString) ;
			Result.setBirthday(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 try{ System.out.println(memberJson.getString("image")) ;
			 Result.setPhoto(memberJson.getString("image"));}catch(Exception e) {}

		return Result ;

	}
    private ChessProfile jsonToChessProfile(JSONObject profileJson) throws Exception
    {

		ChessProfile  memberProfile  = new ChessProfile() ; 
		 	int isArbiter = profileJson.getInt("is_arbiter") ; 
		 	int  isOrganizer = profileJson.getInt("organizer") ;
		 	int isPlayer  =  profileJson.getInt("is_player") ;
		 	int isTrainer = profileJson.getInt("is_trainer") ; 
		 	String title = profileJson.getString("title").toUpperCase(); 
		 	String level = profileJson.getString("lesson_level").toUpperCase(); 
		 	
		memberProfile.setIsArbiter(isArbiter);
		memberProfile.setIsOrganizer(isOrganizer);
		memberProfile.setIsPlayer(isPlayer);	
		memberProfile.setIsTrainer(isTrainer); 
		
//		memberProfile.setTitle(Enum.valueOf(Title.class,title)) ;
//		memberProfile.setTrainerLevel(Enum.valueOf(TrainerFor.class,level));
		return memberProfile ; 
    }
}
