package model;

public class MeetingPlaceType {
	
	
	
	 public String getImage() {
		return image;
	}





	@Override
	public String toString() {
		return "MeetingPlaceType [id=" + id + ", label=" + label + ", image=" + image + "]";
	}





	public void setImage(String image) {
		this.image = image;
	}
	private int id ;//	ID
	private String label ; //	Label: (Example: Chess Club, Bar, Coffee, Sports Hall, Hotel, Camping, Public place, ...)
	private String image ; //	Icon
	public int getId() {
		return id;
	}
	
	
	
	
	
	public MeetingPlaceType() {
		super();
		// TODO Auto-generated constructor stub
	}





	public MeetingPlaceType(int id, String label, String image) {
		super();
		this.id = id;
		this.label = label;
		this.image = image;
	}





	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
}
