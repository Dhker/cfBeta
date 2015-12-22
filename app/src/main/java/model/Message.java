package model;

import java.util.Date;

public class Message {

	private int id ;
	private Date date ; 
	private Member sender ; 
	private Member receiver ; 
	
	private String objet ;
	
	private String message ; 
	private int isDelivered ; 
	private Date deliverydate ;
	
	
	
	
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Message(int id, Date date, Member sender, Member receiver,
			String objet, String message, int isDelivered, Date deliverydate) {
		super();
		this.id = id;
		this.date = date;
		this.sender = sender;
		this.receiver = receiver;
		this.objet = objet;
		this.message = message;
		this.isDelivered = isDelivered;
		this.deliverydate = deliverydate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getIsDelivered() {
		return isDelivered;
	}
	public void setIsDelivered(int isDelivered) {
		this.isDelivered = isDelivered;
	}
	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	} 
	
	
	
	
}
