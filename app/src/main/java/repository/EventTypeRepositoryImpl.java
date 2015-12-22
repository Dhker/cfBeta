package repository;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import configuration.Configuration;
import model.EventType;
import utils.JSONParser;


public class EventTypeRepositoryImpl implements EventTypeRepository {

	 private final String URL  =   Configuration.URL ;
	  private final String AUTH = Configuration.AUTH ; 
	@Override
	public ArrayList<EventType> getAll()throws Exception {
		// TODO Auto-generated method stub
		ArrayList<EventType> Result =  new ArrayList<EventType>() ;

		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "event_type"));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	     if (json != null)
	     {
	    JSONArray  jsonEventTypes =    json.getJSONArray("event_type") ;
	    

	    		
	    for(int i = 0 ; i< jsonEventTypes.length(); i++)
		   {
			  JSONObject jsonEventType = jsonEventTypes.getJSONObject(i);
			   System.out.println(jsonEventType) ;
			   Result.add(jsonToEventType(jsonEventType)) ; 
//			   System.out.println(language);
		   }
	    return Result ; 
	}
		return Result;
	}

	@Override
	public EventType getEventTypeById(String id) {
		// TODO Auto-generated method stub
	
	
		return null;
	}

	
	private EventType jsonToEventType(JSONObject jsonEventType) throws Exception
	{
		EventType  eventType  = new EventType() ; 
		
		eventType.setId(jsonEventType.getInt("id"));
		eventType.setLabel(jsonEventType.getString("label"));
		
		return eventType ; 
	}
	
}
