package model;

import java.util.ArrayList;

public class MeetingPlace {

	
	private int id ;//�	ID
	 private Member added_by ;//	Added by Member ID (Should be registered as member)
	 private Member admin ; //	ID Administrator (If registered as member) (optional)
	private String Name  ;// 	Place Name
	
	private String adress ; //	Address
	private String longitude ; //	Longitude
	private String latitude ;//	Latitude
	private City city ; //	City
	private Country country ; //	Country
	private MeetingPlaceType type ;// 	Type: ID Type (See Meeting place types)
	private PlaceOpeningTime openingtime ;//	Opening time (For each day of the week specify opening times and description of activity)
	
	private int status ; //	Status: 1= Active, 0=Inactive, 2=Banned (Managed by moderators)
	private String email ; //	Email
	private String website ;//	Website
	private String phone ;//	Phone number
	private ArrayList<String> photos ; //	Photos (Photo Gallery)
	
	
	
	
	
	
	public MeetingPlace() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MeetingPlace(int id, Member added_by, Member admin, String name,
			String adress, String logitude, String latitude, City city,
			Country country, MeetingPlaceType type,
			PlaceOpeningTime openingtime, int status, String email,
			String website, String phone, ArrayList<String> photos) {
		super();
		this.id = id;
		this.added_by = added_by;
		this.admin = admin;
		Name = name;
		this.adress = adress;
		this.longitude = logitude;
		this.latitude = latitude;
		this.city = city;
		this.country = country;
		this.type = type;
		this.openingtime = openingtime;
		this.status = status;
		this.email = email;
		this.website = website;
		this.phone = phone;
		this.photos = photos;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Member getAdded_by() {
		return added_by;
	}
	public void setAdded_by(Member added_by) {
		this.added_by = added_by;
	}
	public Member getAdmin() {
		return admin;
	}
	public void setAdmin(Member admin) {
		this.admin = admin;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
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
	public MeetingPlaceType getType() {
		return type;
	}
	public void setType(MeetingPlaceType type) {
		this.type = type;
	}
	public PlaceOpeningTime getOpeningtime() {
		return openingtime;
	}
	public void setOpeningtime(PlaceOpeningTime openingtime) {
		this.openingtime = openingtime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public ArrayList<String> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<String> photos) {
		this.photos = photos;
	}


	
	
	
}
