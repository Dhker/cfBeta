package model;

import java.io.Serializable;
import java.util.Date;

public class Event   implements Serializable {

	private int id ; // 	ID
	private Member announcer ; //	ID Announcer (Member ID)
	private EventType type ; //	ID Events Type (See Event Types)
	private MeetingPlace place ; // 	ID Meeting Place
	private String Name ;//	Event Name
	private String 	organizer ; //  Name
	private Date start ; 	//Start Date
	private Date end ; // 	End Date
	private int timec ; // 	Time Control: 0=No Time (default), 1=Blitz, 2=Rapid, 3=Classic
	private int rate ; // 	Is FIDE Rated: 0= Not FIDE Rated (default), 1= FIDE Rated
	private String description ; //	Description:  (Memo formatted Text including all details about the event)
	private String prize ; //	Prize Fund (optional)
	private String phone ;  //	Phone number (optional)
	private String email  ; //  	Email (optional)
	private String website ; //	Website Url (optional)

	
	
	
	
	
	
	
	
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(int id, Member announcer, EventType type, MeetingPlace place,
			String name, String organizer, Date start, Date end, int timec,
			int rate, String description, String prize, String phone,
			String email, String website) {
		super();
		this.id = id;
		this.announcer = announcer;
		this.type = type;
		this.place = place;
		Name = name;
		this.organizer = organizer;
		this.start = start;
		this.end = end;
		this.timec = timec;
		this.rate = rate;
		this.description = description;
		this.prize = prize;
		this.phone = phone;
		this.email = email;
		this.website = website;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getAnnouncer() {
		return announcer;
	}
	public void setAnnouncer(Member announcer) {
		this.announcer = announcer;
	}
	public EventType getType() {
		return type;
	}
	public void setType(EventType type) {
		this.type = type;
	}
	public MeetingPlace getPlace() {
		return place;
	}
	public void setPlace(MeetingPlace place) {
		this.place = place;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getOrganizer() {
		return organizer;
	}
	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public int getTimec() {
		return timec;
	}
	public void setTimec(int timec) {
		this.timec = timec;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", announcer=" + announcer + ", type=" + type + ", place=" + place + ", Name=" + Name
				+ ", organizer=" + organizer + ", start=" + start + ", end=" + end + ", timec=" + timec + ", rate="
				+ rate + ", description=" + description + ", prize=" + prize + ", phone=" + phone + ", email=" + email
				+ ", website=" + website + "]";
	}


}
