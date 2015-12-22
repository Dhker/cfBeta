package repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import configuration.Configuration;
import model.Country;
import utils.JSONParser;


public class CountryRepositoryImpl implements CountryRepository {
  
	  private final String URL  =   Configuration.URL ;
	  private final String AUTH = Configuration.AUTH ; 
	  //Tested
	@Override
	public Country getCountryById(String id) throws Exception{
		// TODO Auto-generated method stub
		
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "country_get"));
	    params.add(new BasicNameValuePair("id", id));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	   // System.out.println(json);
	    JSONObject countryJSON = json.getJSONObject("country") ; 
	   return jsonToCountry(countryJSON) ; 
	}
	
	// Tested
	@Override
	public ArrayList<Country> getAll() throws Exception{
		// TODO Auto-generated method stub
		ArrayList<Country> result = new ArrayList<Country>() ;
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "country_get_all"));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
//	    System.out.println(json);
	    JSONArray  CountryList =    json.getJSONArray("country") ;
		   for(int i = 0 ; i< CountryList.length(); i++)
		   {
			   JSONObject CountryJson = CountryList.getJSONObject(i);
			result.add(jsonToCountry(CountryJson));
//			   System.out.println(language);
		   }
		return result;
	}
	
	private Country jsonToCountry(JSONObject countryJson) throws Exception
	{
		  
		   Country country  = new Country() ; 
		   country.setId(countryJson.getInt("id"));
		   country.setLabel(countryJson.getString("name"));
		   country.setIso_code((countryJson.getString("iso_code") ));
		   return country ; 
	}
}
