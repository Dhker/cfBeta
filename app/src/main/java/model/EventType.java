package model;

import java.io.Serializable;

public class EventType  implements Serializable {
	
	
	

	@Override
	public String toString() {
		return "EventType [id=" + id + ", label=" + label + "]";
	}
	private int id ; //	ID
	private String label ; // 	Label: (Example: Tournament, Simultaneous, Seminar, Training camp...)
	
	
	
	
	public EventType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventType(int id, String label) {
		super();
		this.id = id;
		this.label = label;
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


}
