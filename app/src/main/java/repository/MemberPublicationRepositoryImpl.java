package repository;


import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import model.Member;
import model.Photo;
import utils.JSONParser;
import model.MemberPublication ;

public class MemberPublicationRepositoryImpl  implements MemberPublicationRepository{
	private final String URL  =   "http://api.chessfamily.net/api/query"; 
    private final String AUTH  = "chessfemily" ;
    private MemberPublication MemberPublication = null;
	private MemberRepository memberRepository = new MemberRepositoryImpl();
	@Override
	public MemberPublication addMemberPublication(String IDMember, String TextFormat, 
			String LinkVideo, String WebLink,String visibility) throws Exception{
	   
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_publication_add"));
	    params.add(new BasicNameValuePair("member_id", IDMember));
	    params.add(new BasicNameValuePair("formatted_text", TextFormat));
	    params.add(new BasicNameValuePair("video_link", LinkVideo));
	    params.add(new BasicNameValuePair("web_link", WebLink));
	    params.add(new BasicNameValuePair("visibility", visibility));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
	    System.out.println(json);	  
        boolean  success = json.getInt("success") ==  1;     
	    if (success){
	    	JSONObject PublactionJSON = json.getJSONObject("member_publication");
	    	MemberPublication = new MemberPublication();
	    	MemberPublication.setId(PublactionJSON.getInt("id"));
	    	MemberPublication.setLink(PublactionJSON.getString("web_link"));
	    	MemberPublication.setVideo(PublactionJSON.getString("video_link"));
	    	MemberPublication.setVisibile(PublactionJSON.getInt("visibility"));
	    	Member mb = new Member();
	    	mb.setID(PublactionJSON.getInt("member_id"));
            MemberPublication.setTime(convertStringToDate(PublactionJSON.getString("date")));
	    	
	    }
	    
		return MemberPublication;
	}
	private  Date convertStringToDate(String dateString)
	{   
		Date date = null;
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = df2.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date;
	}
	@Override
	public boolean PublicationaddPhoto(File Source) {
		boolean Result=false;
		
		return Result;
	}
	@Override
	public ArrayList<MemberPublication> GetFeeds( int perpage, int page) throws Exception{
		ArrayList<MemberPublication> result = new ArrayList<MemberPublication>() ;
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "publications"));
	    params.add(new BasicNameValuePair("perpage", ""+perpage));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    params.add(new BasicNameValuePair("page", ""+page));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
	    System.out.println(json);	  
        boolean  success = json.getInt("success") ==  1;     
	    if (success){

	    JSONArray JSpub=json.getJSONArray("publications");
	    for(int i = 0 ; i< JSpub.length(); i++)
		   {MemberPublication MBpub = new MemberPublication();

	    	JSONObject MSGJson = JSpub.getJSONObject(i);
	    	 MBpub.setId(MSGJson.getInt("id"));
	    	 MBpub.setTime(convertStringToDate(MSGJson.getString("date")));
	    	 MBpub.setText(MSGJson.getString("formatted_text"));
	    	 MBpub.setLink(MSGJson.getString("web_link"));
	    	 MBpub.setVideo(MSGJson.getString("video_link"));
	    	 MBpub.setVisibile(MSGJson.getInt("visibility"));

			   JSONObject  memberJson = MSGJson.getJSONObject("member");
			 Member member = memberRepository.getMemberById(memberJson.getString(memberJson.getString("member_id")));
			   MBpub.setMember(member);


			   result.add(MBpub);
			
		   }
	    }
		return result;
	}
	@Override
	public ArrayList<MemberPublication> GetPublicationsByIDMember(String IDMember,
			int perpage,int page) throws Exception{
		JSONParser jsonParser  = new JSONParser() ;
		ArrayList<MemberPublication> ListeMemberPublication = new ArrayList<MemberPublication>() ;
		ArrayList<Photo> ListePhoto = new ArrayList<Photo>() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_publications"));
	    params.add(new BasicNameValuePair("member_id", IDMember));
	    params.add(new BasicNameValuePair("perpage",""+perpage));
	    params.add(new BasicNameValuePair("page",""+page));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
	    System.out.println(json);	  
        boolean  success = json.getInt("success") ==  1;     
	    if (success){
	    MemberPublication MemberPub = new  MemberPublication();
	    JSONArray pub = json.getJSONArray("publications");

	    for (int i=0;i<pub.length();i++){

		    Photo photoObj = new Photo();
		    JSONObject pubJson = pub.getJSONObject(i);
			Member memberObj = memberRepository.getMemberById(pubJson.getString("member_id")) ;
		    MemberPub.setMember(memberObj);
		    MemberPub.setLink(pubJson.getString("web_link"));
		    MemberPub.setVideo(pubJson.getString("video_link"));
		    MemberPub.setVisibile(pubJson.getInt("visibility"));
			MemberPub.setId(pubJson.getInt("id"));
		    ArrayList<Photo> ListePhotoPub = new ArrayList<Photo>() ;
		    JSONArray Arrayphotos = pubJson.getJSONArray("photos");
		    for (int j=0;j<Arrayphotos.length();j++)
		    {
		    	 JSONObject JSONphoto = pub.getJSONObject(j);
		    	 photoObj.setId(JSONphoto.getInt("id"));
		    
		    	 photoObj.setLink(JSONphoto.getString("image"));
		    	 ListePhoto.add(photoObj);
		    }
		    MemberPub.setPhoto(ListePhotoPub);
		    ListeMemberPublication.add(MemberPub);
	    }
	  
	    }
		return ListeMemberPublication;
	}
	@Override
	public ArrayList<Photo> DeletePhotoPublication(String IDMember, String IDPublication, String IDPhoto) throws Exception {
		ArrayList<Photo> ListePhotos = new ArrayList<Photo>() ;
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_publication_delete_photo"));
	    params.add(new BasicNameValuePair("member_id", IDMember));
	    params.add(new BasicNameValuePair("publication _id", IDPublication));
	    params.add(new BasicNameValuePair("photo id", IDPhoto));

	    JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
	    System.out.println(json);	  
        boolean  success = json.getInt("success") ==  1;     
	    if (success){
	    	JSONArray PhotosPub = json.getJSONArray("publication_photos");
	    	for (int i=0;i<PhotosPub.length();i++)
	    	{
	    	JSONObject ph = PhotosPub.getJSONObject(i);
	    	Photo photo= new Photo();
	    	photo.setId(ph.getInt("id"));
	    
	    	photo.setLink("image");
	    	ListePhotos.add(photo);
	    	}
	    }
		return ListePhotos;
	}
	@Override
	public MemberPublication GetOnePublicationByIDMember(String IDMember, String IDPublication) throws Exception {
		JSONParser jsonParser  = new JSONParser() ;
		MemberPublication MemberPub = new MemberPublication();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_publication_get"));
	    params.add(new BasicNameValuePair("member_id", IDMember));
	    params.add(new BasicNameValuePair("publication _id", IDPublication));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
	    System.out.println(json);
	    if (json.getInt("success")==1)
	    {
	    JSONObject JSONPub = json.getJSONObject("publication");
	    MemberPub.setId(JSONPub.getInt("id"));
	    MemberPub.setTime(convertStringToDate(JSONPub.getString("date")));
	    Member member =new Member();
	    member.setID(JSONPub.getInt("member_id"));
	    MemberPub.setMember(member);
	    MemberPub.setText(JSONPub.getString("formatted_text"));
	    }
		return MemberPub;
	}
	@Override
	public boolean DeleteMemberPublication(String IDMember, String IDPublication) throws Exception {
		JSONParser jsonParser  = new JSONParser() ;
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "member_publication_delete"));
	    params.add(new BasicNameValuePair("member_id", IDMember));
	    params.add(new BasicNameValuePair("publication _id", IDPublication));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params); 
	    System.out.println(json);
	   
		return (json.getInt("success")==1);
	}
}