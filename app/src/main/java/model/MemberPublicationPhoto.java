package model;

public class MemberPublicationPhoto {

	
	private MemberPublication pub ;
	private String photo ;
	
	
	
	
	public MemberPublicationPhoto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberPublicationPhoto(MemberPublication pub, String photo) {
		super();
		this.pub = pub;
		this.photo = photo;
	}
	public MemberPublication getPub() {
		return pub;
	}
	public void setPub(MemberPublication pub) {
		this.pub = pub;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
	
	
	

}
