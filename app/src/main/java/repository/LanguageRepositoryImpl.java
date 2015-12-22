package repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import configuration.Configuration;
import model.Language;
import utils.JSONParser;


public class LanguageRepositoryImpl implements LanguageRepository {

	


	  private final String URL  =   Configuration.URL ; 
	  private final String AUTH = Configuration.AUTH ;

	  //Tested
	  @Override
	public ArrayList<Language> getAllLanguages() throws Exception{
		// TODO Auto-generated method stub
		ArrayList<Language> result = new ArrayList() ; 
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "language_get_all"));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
  // System.out.println(json);
	    JSONArray  languages =    json.getJSONArray("language") ;
		   for(int i = 0 ; i< languages.length(); i++)
		   {
			  JSONObject jsonLanguages = languages.getJSONObject(i);
			   Language language  =jsonToLanguage(jsonLanguages); 
//			   System.out.println(language);
			   result.add(language) ; 
		   }
		return result;
	}
 //Tested
	@Override
	public ArrayList<Language> getMemberLanguages(String id) throws Exception{
		// TODO Auto-generated method stub
		
		ArrayList<Language> result = new ArrayList() ; 
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_languages"));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    params.add(new BasicNameValuePair("member_id", id));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    //System.out.println(json);
	    JSONArray  languages =    json.getJSONArray("member_languages") ;
		   for(int i = 0 ; i< languages.length(); i++)
		   {
			  JSONObject jsonLanguages = languages.getJSONObject(i);
		
   	   result.add(jsonToLanguage(jsonLanguages)) ; 

		   }
		return result;
		
	}

	//Tested
	@Override
	public boolean deleteMemberLanguage(String memberId, String languageId) throws Exception{
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_language_delete"));
	    params.add(new BasicNameValuePair("member_id", memberId));
	    params.add(new BasicNameValuePair("language_id", languageId));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    return (json.getInt("success")==1) ; 
	}

	
	//Tested
	@Override
	public boolean  setDefaultLanguage(String memberId, String languageId) throws
	Exception{
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_language_default"));
	    params.add(new BasicNameValuePair("member_id", memberId));
	    params.add(new BasicNameValuePair("language_id", languageId));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    // System.out.println(json);
	    return (json.getInt("success")==1) ; 
	}
	
	private Language jsonToLanguage(JSONObject jsonLanguage)
	{
		
		 Language language  = new Language() ; 
		 try{  int id = jsonLanguage.getInt("id") ; 
		   language.setId(id );
		 }catch(Exception e){}
			 
		 try{  int id = jsonLanguage.getInt("language_id") ; 
		   language.setId(id );
		 }catch(Exception e){}
		  
		 try{
		   language.setLabel(jsonLanguage.getString("label")); 
		 }catch(Exception e)
		 {
			 
		 }try{
		  language.setFlag(jsonLanguage.getString("image")) ; }
		 catch(Exception e)
			 {
				 
			 }  
		 try{ 
			 
			 boolean  isDefault = (jsonLanguage.getInt("is_default")== 1) ; 
			  language.setDefault(isDefault) ;
		 }
			 catch(Exception e)
				 {
					 
				 }
		   return language ; 
		
	}
	//Tested
	@Override
	public boolean addLanguageToMember(String memberId, String languageId , boolean isDefault)throws Exception {
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_language_add"));
	    params.add(new BasicNameValuePair("member_id", memberId));
	    params.add(new BasicNameValuePair("language_id", languageId));
	    int defaultVar =(isDefault== true)?1:0 ; 
	    params.add(new BasicNameValuePair("is_default", String.valueOf(defaultVar)));
		    
	   
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    // System.out.println(json);
	    return (json.getInt("success")==1) ; }

}
