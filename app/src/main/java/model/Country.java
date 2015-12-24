package model;

import java.io.Serializable;

public class Country   implements Serializable {

	
	@Override
	public String toString() {
		return "Country [id=" + id + ", label=" + label + ", iso_code=" + iso_code + ", flag=" + flag + "]";
	}
	private int id ; 
	private String label ; 
	private String iso_code ; 
	private String flag;
	
	
	
	
	
	
	
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(int id, String label, String iso_code, String flag) {
		super();
		this.id = id;
		this.label = label;
		this.iso_code = iso_code;
		this.flag = flag;
	}
	public int getId() {
		return id;
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
	public String getIso_code() {
		return iso_code;
	}
	public void setIso_code(String iso_code) {
		this.iso_code = iso_code;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	} 
	
	
	
	
	
}
