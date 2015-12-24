package model;

import java.io.Serializable;
import java.util.Date;

public class MemberFreindShip  implements Serializable {

	
	
	private int	ID ;
	private Member member ;//	ID member
	private Member freind ;//	ID Friend (See Member table)
	private Date lastexchange ;//	Last exchange: (Date= YYYYMMDD)
	private int status ;//	Status: 1= Active (default), 0=Inactive, 2=Blocked (Managed by moderators)
	
	
	
	public MemberFreindShip() {
		
	}
	public MemberFreindShip(int iD, Member member, Member freind,
			Date lastexchange, int status) {
		super();
		ID = iD;
		this.member = member;
		this.freind = freind;
		this.lastexchange = lastexchange;
		this.status = status;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Member getFreind() {
		return freind;
	}
	public void setFreind(Member freind) {
		this.freind = freind;
	}
	public Date getLastexchange() {
		return lastexchange;
	}
	public void setLastexchange(Date lastexchange) {
		this.lastexchange = lastexchange;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	

	
	

}
