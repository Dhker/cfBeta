package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import configuration.Configuration;
import model.MeetingPlace;
import model.MeetingPlaceType;
import utils.JSONParser;

public class MeetingPlaceRepositoryImpl implements MeetingPlaceRepository {
  
	

	  private final String URL  =   Configuration.URL ; 
	  private final String AUTH = Configuration.AUTH ;
	  // Tested  
	@Override
	public ArrayList<MeetingPlaceType> getAllMeetingPlaceType()throws Exception {
		// TODO Auto-generated method stub
		
		
		ArrayList<MeetingPlaceType> result = new ArrayList<MeetingPlaceType>() ; 
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "meeting_place_type"));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
 // System.out.println(json);
	    JSONArray  meetingJSON =    json.getJSONArray("meeting_place_type") ;
		   for(int i = 0 ; i< meetingJSON.length(); i++)
		   {
			  JSONObject element = meetingJSON.getJSONObject(i);
			 // System.out.println(element);
			  MeetingPlaceType meetingType  =jsonToMeetingPLaceType(element); 
//			   System.out.println(language);
			   result.add(meetingType) ; 
		   }
		return result;
	}
	
	private MeetingPlaceType jsonToMeetingPLaceType(JSONObject object) throws Exception
	{MeetingPlaceType result  = new MeetingPlaceType() ; 
	result.setLabel(object.getString("label"));
	result.setId(object.getInt("id")); 
	result.setImage(object.getString("image"));
		return result;
		
	}
  
	private MeetingPlace jsonToMeetingPlace(JSONObject object) throws Exception

	{
		MeetingPlace result = new MeetingPlace() ; 
		result.setPhone(object.getString("phone_number"));
		result.setId(object.getInt("id"));
		result.setAdress(object.getString("adress"));
		result.setLatitude(object.getString("latitude"));
		result.setLongitude(object.getString("longitude"));
		result.setName(object.getString("name"));
		result.setWebsite(object.getString("website"));
		result.setStatus(object.getInt("status"));
		
		return result ; 
	}
	@Override
	public MeetingPlace getMeetingPlaceById(String id) throws Exception{
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "meeting_place_type"));
	    //params.add(new BasicNameValuePair("force_object", "0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return jsonToMeetingPlace(json);
	}

}
