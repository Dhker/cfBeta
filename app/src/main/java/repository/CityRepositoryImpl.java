package repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import model.City;
import model.Country;
import utils.JSONParser;

public class CityRepositoryImpl implements CityRepository {
	private final String URL  =   "http://api.chessfamily.net/api/query"; 
	private final String AUTH  = "chessfemily" ;
	@Override
	public City GetCityByID(String IDCity) throws Exception{
		City result =null;
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "city_get"));
	    params.add(new BasicNameValuePair("id",IDCity));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    System.out.println(json) ;
	    boolean  success = json.getInt("success") ==  1; 
	    
	    if (success){
	    result = new City();
	    JSONObject CityJSON = json.getJSONObject("city") ; 
	    result.setId(CityJSON.getInt("id"));
	    result.setLabel(CityJSON.getString("city"));
	    result.setLatitude(CityJSON.getString("latitude"));
	    result.setLongitude(CityJSON.getString("longitude"));
	    Country CountryObject = new Country();
	    CountryObject.setLabel(CityJSON.getString("country"));
	     
	    }
	    
		return result;
	}

}
