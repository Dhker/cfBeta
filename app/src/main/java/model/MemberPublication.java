package model;

import java.io.Serializable;
import java.util.Date;

public class MemberPublication  implements Serializable {

	
	
	private int id; //	ID
	private Date time ; //	Date time
	private Member member ;	 // ID Member
	private String text ; //	Formatted Text
	private String Photo;
	private String Video ; //	Link to vid�o (Major platforms: Youtube, Dailymotion)
	private String link ; //	Web Link
	private int visibile ; //	Visibility (1= Published default, 0= Not published, 2=Banned)
	public MemberPublication(int id, Date time, Member member, String text,
			String photo, String video, String link, int visibile) {
		super();
		this.id = id;
		this.time = time;
		this.member = member;
		this.text = text;
		Photo = photo;
		Video = video;
		this.link = link;
		this.visibile = visibile;
	}
	public MemberPublication() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
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
