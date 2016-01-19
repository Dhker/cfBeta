package repository;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Member;
import model.Message;
import utils.JSONParser;

public class MessageRepositoryImpl implements MessageRepository {
	private final String URL  =   "http://api.chessfamily.net/api/query"; 
	private final String AUTH  = "chessfemily" ;


	@Override
	public Message sendMessage(String IDmember,String IDreciver,String Object,String message) throws Exception{
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		Message MSG =  new Message() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "send_message"));
	    params.add(new BasicNameValuePair("member_id",IDmember));
	    params.add(new BasicNameValuePair("receiver_id",IDreciver));
	    params.add(new BasicNameValuePair("object",Object));
	    params.add(new BasicNameValuePair("message",message));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	  //  System.out.println(json);
	    if (json.getInt("success")==1)
	    {
	    JSONObject MessageJSON = json.getJSONObject("message") ; 
	    Member SenderOBj = new Member();
	    SenderOBj.setID(MessageJSON.getInt("member_id"));
	    Member ReceiverOBj = new Member();
	    ReceiverOBj.setID(MessageJSON.getInt("receiver_id"));
	    
	    MSG.setId(MessageJSON.getInt("id"));
	   // MSG.setisRead(MessageJSON.getInt("is_read"));
	    MSG.setIsDelivered(MessageJSON.getInt("is_delivered"));
	    //MSG.setorigin_id(MessageJSON.getInt("origin_id"));
	    MSG.setMessage(MessageJSON.getString("message"));
	    MSG.setObjet(MessageJSON.getString("object"));
	   // MSG.seturl_key(MessageJSON.getInt("url_key"));
	  //  MSG.setorigin_id(MessageJSON.getInt("origin_id"));
	    MSG.setSender(SenderOBj);
	    MSG.setReceiver(ReceiverOBj);
        MSG.setDate(convertStringToDate(MessageJSON.getString("date")));
	}
	    return MSG; 
	}
	@Override
	public ArrayList<Message> messagesSended(String IDMember,String Perpage,String Page) throws Exception{
		// TODO Auto-generated method stub
		ArrayList<Message> result = new ArrayList<Message>() ;
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "messages_sended"));
	    params.add(new BasicNameValuePair("member_id",IDMember));
	    params.add(new BasicNameValuePair("perpage",Perpage));
	    params.add(new BasicNameValuePair("page",Page));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	   //System.out.println(json);
	    Message msgObj = new Message();
	   /* msgObj.setnbrReturn(json.getInt("nb_return"));
	    msgObj.setperpage(json.getInt("perpage"));
	    msgObj.setpage(json.getInt("page"));
	    msgObj.setnbTotale(json.getInt("nb_total"));*/
	    result.add(msgObj);
	    JSONArray  MessageList=json.getJSONArray("mssages_sended") ;
	    for(int i = 0 ; i< MessageList.length(); i++)
		   {
	    	JSONObject MSGJson = MessageList.getJSONObject(i);
			 msgObj.setId(MSGJson.getInt("id"));
			 msgObj.setDate(convertStringToDate(MSGJson.getString("date")));
			 msgObj.setMessage(MSGJson.getString("message"));
			 msgObj.setObjet(MSGJson.getString("object"));
			 Member Reciver = new Member();
			 Reciver.setID(MSGJson.getInt("receiver_id"));
			 Reciver.setName(MSGJson.getString("receiver_name"));
			 Reciver.setLast_Name(MSGJson.getString("receiver_last_name"));
			 Reciver.setEmail(MSGJson.getString("receiver_email"));
			 result.add(msgObj);
			
		   }
	    return result; 
	}
	@Override
	public int messageSendedDelete(String IDMember,String IDMessage) throws Exception {
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "message_sended_delete"));
	    params.add(new BasicNameValuePair("member_id",IDMember));
	    params.add(new BasicNameValuePair("message _id",IDMessage));
	    //params.add(new BasicNameValuePair("forced_object","0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	  // System.out.println(json) ;
		return json.getInt("success");
	}
	@Override
	public ArrayList<Message>  messagesReceived(String IDMember,String is_not_read,String PerPage,String Page) throws Exception{
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		 ArrayList<Message>MessageList = new ArrayList<Message>();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "messages_received"));
	    params.add(new BasicNameValuePair("member_id",IDMember));
	    params.add(new BasicNameValuePair("is_not_read",is_not_read));
	    params.add(new BasicNameValuePair("perpage",PerPage));
	    params.add(new BasicNameValuePair("page",Page));
	    params.add(new BasicNameValuePair("force_object", "0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	  //  System.out.println("json Requette:"+json);


	    JSONArray  MessageArrayJson =    json.getJSONArray("messages_received") ;
	
	    for(int i = 0 ; i< MessageArrayJson.length(); i++)
		   {
			   Message MessageObject = new Message();
	    	 JSONObject MSGJson = MessageArrayJson.getJSONObject(i);
			 MessageObject.setId(MSGJson.getInt("id"));
			 MessageObject.setMessage(MSGJson.getString("message"));
		     MessageObject.setObjet(MSGJson.getString("object"));
			 Member memberSender = new Member();
			 memberSender.setID(MSGJson.getInt("sender_id"));
			 memberSender.setName(MSGJson.getString("sender_name"));
			 memberSender.setLast_Name(MSGJson.getString("sender_last_name"));
			 memberSender.setEmail(MSGJson.getString("sender_email"));
			 MessageObject.setReceiver(memberSender);
			 MessageList.add(MessageObject);
		   }
		return MessageList;
	}
	@Override
	public int message_received_read(String IDMember,String IDMessage) throws Exception {
		// TODO Auto-generated method stub
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "message_received_read"));
	    params.add(new BasicNameValuePair("member_id",IDMember));
	    params.add(new BasicNameValuePair("message_id'",IDMessage));
	    //params.add(new BasicNameValuePair("forced_object","0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	   // System.out.print(json) ;
		return json.getInt("success");
	}
	@Override
	public int message_received_delete(String IDMember,String IDMessage) throws Exception {
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "message_received_delete"));
	    params.add(new BasicNameValuePair("member_id",IDMember));
	    params.add(new BasicNameValuePair("message_id'",IDMessage));
	    //params.add(new BasicNameValuePair("forced_object","0"));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	  //  System.out.print(json) ;
		
		return json.getInt("success");
	}

	@Override
	public int getUnreadMessages(String MemberID) throws Exception
	{
		JSONParser jsonParser  = new JSONParser() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("authentication", AUTH));
		params.add(new BasicNameValuePair("action", "not_read_messages"));
		params.add(new BasicNameValuePair("member_id",MemberID));
		JSONObject json = jsonParser.getJSONFromUrl(URL, params);
		return json.getInt("not_read_messages") ;
	}

	public Date convertStringToDate(String dateString)
	{   
		Date date = null;
		SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = df2.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
         //System.out.println("Date: " + date);
	    return date;
	}
	

}
