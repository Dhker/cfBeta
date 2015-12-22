package model;

public class MeetingPlacePhoto {

	private int id ; 
	
	private MeetingPlace place ; 
	
	private String photo ; // url de photo 
	
	
	

	public MeetingPlacePhoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MeetingPlacePhoto(int id, MeetingPlace place, String photo) {
		super();
		this.id = id;
		this.place = place;
		this.photo = photo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MeetingPlace getPlace() {
		return place;
	}

	public void setPlace(MeetingPlace place) {
		this.place = place;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	

}
