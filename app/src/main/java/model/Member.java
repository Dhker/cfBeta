package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Member implements Serializable  {

	private int	ID ;
	private String	DeviceToken ;
	private String	OS ;
	private String	Name ;
	private String	Last_Name ;
	private Date	Birthday ;
	private	int Gender ;
	private String	Email ;
	private City city	;
	private Country 	ID_country ;
	private String	Password ;
	private	String Facebook_ID ;
	private String	Google_ID ;
	private String	Photo ;
	private ArrayList<Language> languages ; //	Spoken languages
	private int status ;	 // Status: 1= Active (default), 0=Inactive, 2=Banned (Managed by moderators)
	private int availble ; //	Availability: 1= Free (Accept invitations), 0=Busy (Don't accept invitation)
	private LastLocation lastlocation ; //Last information update when localization is activated)
	private ChessProfile profile ; // profile de joueur 
	private ArrayList<MemberFavoriteEvents> favorite_events ; // Favorite events 
	
	
	
	public int getID() {
		return ID;
	}
	
	
	@Override
	public String toString() {
		return "Member [ID=" + ID + ", DeviceToken=" + DeviceToken + ", OS=" + OS + ", Name=" + Name + ", Last_Name="
				+ Last_Name + ", Birthday=" + Birthday + ", Gender=" + Gender + ", Email=" + Email + ", ID_city="
				+ city + ", ID_country=" + ID_country + ", Password=" + Password + ", Facebook_ID=" + Facebook_ID
				+ ", Google_ID=" + Google_ID + ", Photo=" + Photo + ", languages=" + languages + ", status=" + status
				+ ", availble=" + availble + ", lastlocation=" + lastlocation + ", profile=" + profile
				+ ", favorite_events=" + favorite_events + "]";
	}


	public Member() {
		
	}



	public ArrayList<Language> getLanguages() {
		return languages;
	}







	public void setLanguages(ArrayList<Language> languages) {
		this.languages = languages;
	}







	public void setID(int iD) {
		ID = iD;
	}
	public String getDeviceToken() {
		return DeviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		DeviceToken = deviceToken;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public int getGender() {
		return Gender;
	}
	public void setGender(int gender) {
		Gender = gender;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		city = city;
	}
	public Country getID_country() {
		return ID_country;
	}
	public void setID_country(Country iD_country) {
		ID_country = iD_country;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFacebook_ID() {
		return Facebook_ID;
	}
	public void setFacebook_ID(String facebook_ID) {
		Facebook_ID = facebook_ID;
	}
	public String getGoogle_ID() {
		return Google_ID;
	}
	public void setGoogle_ID(String google_ID) {
		Google_ID = google_ID;
	}

	public String getPhoto() {
		if(Photo!=null)
		return Photo;
		return getFaceboohPhoto() ;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAvailble() {
		return availble;
	}
	public void setAvailble(int availble) {
		this.availble = availble;
	}
	public LastLocation getLastlocation() {
		return lastlocation;
	}
	public void setLastlocation(LastLocation lastlocation) {
		this.lastlocation = lastlocation;
	}
	public ChessProfile getProfile() {
		return profile;
	}
	public void setProfile(ChessProfile profile) {
		this.profile = profile;
	}

	private String getFaceboohPhoto()
	{
		return "https://graph.facebook.com/" + getFacebook_ID()+ "/picture?width=200&height=150";
	}
	
	
	
	

}
