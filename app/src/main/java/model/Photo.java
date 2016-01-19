package model;

import java.io.File;

public class Photo {
private int id ;
private String link;
	private File photo ;
private MemberPublication publication;

public Photo ()
{

	}




	public MemberPublication getpublication() {
	return publication;
}
public void setpublication(MemberPublication IDpublication) {
	this.publication = IDpublication;
}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(String path) {
		if (path!=null)
		this.photo = new File(path);
	}

	public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getLink() {
	return link;
}
public void setLink(String link) {
	this.link = link;
}
}
