package repository;




import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Member;
import utils.JSONParser ;
import configuration.Configuration;
import model.Event;
import model.MemberFavoriteEvents;


public class EventRepositoryImpl implements EventRepository {



	  private final String URL  =   Configuration.URL ;
	  private final String AUTH = Configuration.AUTH ;
	private MemberRepository memberRepository = new MemberRepositroyImpl() ; 
	@Override
	public MemberFavoriteEvents getFavoriteEventsByMemberID(String memeberID) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Event getEventById(String id) throws Exception {
		// TODO Auto-generated method stub
		
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "event_get"));
	    params.add(new BasicNameValuePair("event_id", id));
	    
	    
	    //RestAPI JSON Result
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    
	    //System.out.println(json);
	    boolean  success = json.getInt("success") ==  1; 
	    if(success)
	    {  
	    	JSONObject eventJSON = json.getJSONObject("event") ; 
	    return jsonToEvent(eventJSON)	 ; 
	    }
	    
	    else
		return null;
	}

	@Override
	public void createEvent(Event event)throws Exception{
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "event_add"));
	    params.add(new BasicNameValuePair("announcer_id",String.valueOf(event.getAnnouncer().getID() )));
	    params.add(new BasicNameValuePair("type_id",String.valueOf(event.getType().getId())));
	   // params.add(new BasicNameValuePair("meeting_placeid",String.valueOf(event.getId())));
	    params.add(new BasicNameValuePair("name",event.getName()));
	    params.add(new BasicNameValuePair("description",event.getDescription()));
	    params.add(new BasicNameValuePair("prize_fund",event.getPrize())); 
	    params.add(new BasicNameValuePair("phone_number",event.getPhone()));
	    params.add(new BasicNameValuePair("email",event.getEmail()));  
	    params.add(new BasicNameValuePair("website",event.getWebsite()));
	    params.add(new BasicNameValuePair("organizer",event.getOrganizer()));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    
	}

	@Override
	public void updateEvent(Event newEvent)throws Exception {
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "event_edit"));
	    params.add(new BasicNameValuePair("announcer_id",String.valueOf(newEvent.getAnnouncer().getID() )));
	    params.add(new BasicNameValuePair("type_id",String.valueOf(newEvent.getType().getId())));
	   // params.add(new BasicNameValuePair("meeting_placeid",String.valueOf(event.getId())));
	    params.add(new BasicNameValuePair("name",newEvent.getName()));
	    params.add(new BasicNameValuePair("description",newEvent.getDescription()));
	    params.add(new BasicNameValuePair("prize_fund",newEvent.getPrize())); 
	    params.add(new BasicNameValuePair("phone_number",newEvent.getPhone()));
	    params.add(new BasicNameValuePair("email",newEvent.getEmail()));  
	    params.add(new BasicNameValuePair("website",newEvent.getWebsite()));
	    params.add(new BasicNameValuePair("organizer",newEvent.getOrganizer()));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	}
	
	private Event jsonToEvent(JSONObject eventJSON) throws Exception
	{
		Event  event  = new Event() ; 
    	
    	event.setId(eventJSON.getInt("id"));
    	event.setName(eventJSON.getString("name"));
    	event.setEmail(eventJSON.getString("email"));
    	event.setPrize(eventJSON.getString("prize_fund"));
    	event.setPhone(eventJSON.getString("phone_number"));
    	event.setOrganizer(eventJSON.getString("organizer"));
    	event.setRate(eventJSON.getInt("id"));
    	event.setDescription(eventJSON.getString("phone_number"));
    	event.setWebsite(eventJSON.getString("website"));
    	Member announcer = memberRepository.getMemberById(eventJSON.getString("announcer_id")) ;
    	event.setAnnouncer(announcer);
    	return event  ; 
	}

}
