package model;

import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable {

	private int id ; //	ID
	private Date time ; // 	Date Time
	private Member sender ; //	ID Sender
	private Member receiver ; //	ID Receiver
	private String Message ; // 	Message
	private int isDelivered ; //	Is Delivered
	private Date delivryDate ; //	Delivery date
	
	private String JsonDate ;

	
	
	
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(int id, Date time, Member sender, Member receiver,
			String message, int isDelivered, Date delivryDate,
			String jsonDate) {
		super();
		this.id = id;
		this.time = time;
		this.sender = sender;
		this.receiver = receiver;
		Message = message;
		this.isDelivered = isDelivered;
		this.delivryDate = delivryDate;
		JsonDate = jsonDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Member getSender() {
		return sender;
	}

	public void setSender(Member sender) {
		this.sender = sender;
	}

	public Member getReceiver() {
		return receiver;
	}

	public void setReceiver(Member receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public int isDelivered() {
		return isDelivered;
	}

	public void setDelivered(int isDelivered) {
		this.isDelivered = isDelivered;
	}

	public Date getDelivryDate() {
		return delivryDate;
	}

	public void setDelivryDate(Date delivryDate) {
		this.delivryDate = delivryDate;
	}

	public String getJsonDate() {
		return JsonDate;
	}

	public void setJsonDate(String jsonDate) {
		JsonDate = jsonDate;
	} 
	


}
