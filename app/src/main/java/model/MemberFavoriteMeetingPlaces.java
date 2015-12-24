package model;

import java.io.Serializable;

public class MemberFavoriteMeetingPlaces  implements Serializable {

 private int id ; // 		ID
	private Member member ; // 	ID Member
	private MeetingPlace place ; // 	ID Meeting Place (See Meeting Place table)
	
	
	
	
	
	
	
	
	
	
	public MemberFavoriteMeetingPlaces() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberFavoriteMeetingPlaces(int id, Member member, MeetingPlace place) {
		super();
		this.id = id;
		this.member = member;
		this.place = place;
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
	public MeetingPlace getPlace() {
		return place;
	}
	public void setPlace(MeetingPlace place) {
		this.place = place;
	}







}
