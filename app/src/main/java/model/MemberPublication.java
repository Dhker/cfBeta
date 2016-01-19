package model;

import java.util.ArrayList;
import java.util.Date;

public class MemberPublication {

	
	
	private int id; //	ID
	private Date time ; //	Date time
	private Member member ;	 // ID Member
	private String text ; //	Formatted Text
	private ArrayList<Photo> Photo;
	private int perpage,page,nbreturn,nbtotale;
	private String Video ; //	Link to vid�o (Major platforms: Youtube, Dailymotion)
	private String link ; //	Web Link
	private int visibile,urlkey ;

	//	Visibility (1= Published default, 0= Not published, 2=Banned)
	public MemberPublication(int id, Date time, Member member, String text,
			ArrayList<Photo> photo, String video, String link,
			int visibile,int perpage,int page,int nbreturn,int nbtotale,int urlkey) {
		super();
		this.id = id;
		this.time = time;
		this.member = member;
		this.text = text;
		Photo = photo;
		Video = video;
		this.link = link;
		this.visibile = visibile;
		this.urlkey=urlkey;
		this.perpage=perpage;
		this.page=page;
		this.nbreturn=nbreturn;
		this.nbtotale=nbtotale;
	}
	
	public MemberPublication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int geturlkey() {
		return urlkey;
	}
	public void seturlkey(int urlkey) {
		this.urlkey = urlkey;
	}
	public int getnbtotale() {
		return nbtotale;
	}
	public void setnbtotale(int nbtotale) {
		this.nbtotale = nbtotale;
	}
	public int getnbreturn() {
		return nbreturn;
	}
	public void setnbreturn(int nbreturn) {
		this.nbreturn = nbreturn;
	}
	public int getpage() {
		return page;
	}
	public void setpage(int page) {
		this.page = page;
	}
	public int getperpage() {
		return perpage;
	}
	public void setperpage(int perpage) {
		this.perpage = perpage;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public ArrayList<Photo> getPhoto() {
		return Photo;
	}
	public void setPhoto(Photo photo) {
		Photo = new ArrayList<Photo>();
		this.Photo.add(photo);
	}
	public String getVideo() {
		return Video;
	}
	public void setVideo(String video) {
		Video = video;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getVisibile() {
		return visibile;
	}
	public void setVisibile(int visibile) {
		this.visibile = visibile;
	}


}
