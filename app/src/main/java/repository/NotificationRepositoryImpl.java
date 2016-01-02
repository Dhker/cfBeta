package repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import model.Member;
import model.Notification;
import utils.JSONParser;

public class NotificationRepositoryImpl implements NotificationRepository {
	private final String URL  =   "http://api.chessfamily.net/api/query"; 
	private final String AUTH  = "chessfemily" ;
	@Override
	public Notification addNotification(String IDMember, String IDReciver, String message) throws Exception {
		JSONParser jsonParser  = new JSONParser() ;
		Notification notification =  new Notification() ;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("authentication", AUTH));
	    params.add(new BasicNameValuePair("action", "notification_add"));
	    params.add(new BasicNameValuePair("member_id",IDMember));
	    params.add(new BasicNameValuePair("receiver_id",IDReciver));
	    params.add(new BasicNameValuePair("message",message));
	    JSONObject json = jsonParser.getJSONFromUrl(URL, params);
	    System.out.println(json);
	    if (json.getInt("success")==1)
	    {
	    	JSONObject JSONNotif= json.getJSONObject("notification");
	    	notification.setId(JSONNotif.getInt("id"));
	    	notification.setTime(convertStringToDate(JSONNotif.getString("date")));
	         //notification.setDelivered(JSONNotif.getInt("is_delivered"));
	    	//notification.setDelivryDate(convertStringToDate(JSONNotif.getString("delivery_date")));
	    	//notification.setJsonDate(JSONNotif.getString("json_data"));
	    	notification.setMessage(JSONNotif.getString("message"));
	    	//notification.seturlkey(JSONNotif.getInt("url_key"));
	    	Member Sender =new Member();
	    	Sender.setID(JSONNotif.getInt("sender_id"));
	    	notification.setSender(Sender);
	    	Member Reciver =new Member();
	    	Reciver.setID(JSONNotif.getInt("receiver_id"));
	    	notification.setReceiver(Reciver);
	    }
		return notification;
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
