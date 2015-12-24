package model;

import java.io.Serializable;
import java.util.Date;

public class MemberFavoriteEvents   implements Serializable {


	private int id ;//	ID
	private Member member ; //	ID Member
	private Event event ; //	ID Event (See Event table)
	private Date reminder ; //	Reminder date (NULL=Default)
	
	
	
	
	public MemberFavoriteEvents(int id, Member member, Event event,
			Date reminder) {
		super();
		this.id = id;
		this.member = member;
		this.event = event;
		this.reminder = reminder;
	}
	
	
	
	public MemberFavoriteEvents() {
		
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public Date getReminder() {
		return reminder;
	}
	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}

	
	
	
}
