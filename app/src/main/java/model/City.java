package model;

import java.io.Serializable;

public class City   implements Serializable {

	
	private int id ; 
	private Country country ;
	private String label ; 
	private String longitude  ;
	private String latitude ; 
	private String zip_code ;
	
	
	
	
	
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	public City(int id, Country country, String label, String longitude,
			String latitude, String zip_code) {
		super();
		this.id = id;
		this.country = country;
		this.label = label;
		this.longitude = longitude;
		this.latitude = latitude;
		this.zip_code = zip_code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
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
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	} 
	
	
	
	
	
}
