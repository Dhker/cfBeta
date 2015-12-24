package model;

import java.io.Serializable;

public class PlaceOpeningTime   implements Serializable {

	
	
private int ID ;
private MeetingPlace meetingp ;
private String day ; //Day of the week (Sunday, Monday...)
private String	start ;
	private String end ; // �	End
private String desciption ; // �	Description (Example: Chess course for beginners)
   //Note: The same day could have many records for many activities (that could be parallel)





public int getID() {
	return ID;
}
public PlaceOpeningTime() {
	super();
	// TODO Auto-generated constructor stub
}
public PlaceOpeningTime(int iD, MeetingPlace meetingp, String day,
		String start, String end, String desciption) {
	super();
	ID = iD;
	this.meetingp = meetingp;
	this.day = day;
	this.start = start;
	this.end = end;
	this.desciption = desciption;
}
public void setID(int iD) {
	ID = iD;
}
public MeetingPlace getMeetingp() {
	return meetingp;
}
public void setMeetingp(MeetingPlace meetingp) {
	this.meetingp = meetingp;
}
public String getDay() {
	return day;
}
public void setDay(String day) {
	this.day = day;
}
public String getStart() {
	return start;
}
public void setStart(String start) {
	this.start = start;
}
public String getEnd() {
	return end;
}
public void setEnd(String end) {
	this.end = end;
}
public String getDesciption() {
	return desciption;
}
public void setDesciption(String desciption) {
	this.desciption = desciption;
}


	
	
		
		

}
