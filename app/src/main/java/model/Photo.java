package model;

public class Photo {
private int id ;
private String link;
private int IDpublication;
public Photo ()
{

	}
public Photo (int id ,String link,int IDpublication)
{
	super();
	this.id=id;
	this.link=link;
	this.IDpublication=IDpublication;
	}
public int getIDpublication() {
	return IDpublication;
}
public void setIDpublication(int IDpublication) {
	this.IDpublication = IDpublication;
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
