package model;

public class Language {

	private int id ; //	ID
	private String label ; //	Label: (Example: English, French, Spanish, ...)
	private String	Flag ;
	private boolean isDefault = false ; 
	
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
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
	
	@Override
	public String toString() {
		return "Language [id=" + id + ", label=" + label + ", Flag=" + Flag + ", isDefault=" + isDefault + "]";
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getFlag() {
		return Flag;
	}
	public void setFlag(String flag) {
		Flag = flag;
	}
	public Language(int id, String label, String flag) {
		super();
		this.id = id;
		this.label = label;
		Flag = flag;
	}
	public Language() {
		super();
		// TODO Auto-generated constructor stub
	}


}
