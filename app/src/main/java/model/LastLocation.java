package model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class LastLocation  implements Serializable {

	
	
	private int id ; 
	private Member member ; 
	private Date lastedate ; 
	private Time lasttime ;
	private String latitude ;
	private String longitude ; 
	private String ip_adress ;
	private City city;
	private Country country ;
	private MeetingPlace place ;
	
	
	
	
	
	
	public LastLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LastLocation(int id, Member member, Date lastedate, Time lasttime,
			String latitude, String longitude, String ip_adress, City city,
			Country country, MeetingPlace place) {
		super();
		this.id = id;
		this.member = member;
		this.lastedate = lastedate;
		this.lasttime = lasttime;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ip_adress = ip_adress;
		this.city = city;
		this.country = country;
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
	public Date getLastedate() {
		return lastedate;
	}
	public void setLastedate(Date lastedate) {
		this.lastedate = lastedate;
	}
	public Time getLasttime() {
		return lasttime;
	}
	public void setLasttime(Time lasttime) {
		this.lasttime = lasttime;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getIp_adress() {
		return ip_adress;
	}
	public void setIp_adress(String ip_adress) {
		this.ip_adress = ip_adress;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public MeetingPlace getPlace() {
		return place;
	}
	public void setPlace(MeetingPlace place) {
		this.place = place;
	}
	
	

	
	
	
	
	
	

}
