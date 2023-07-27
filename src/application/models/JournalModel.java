package application.models;

public class JournalModel {
	private int id;
	private String title;
	private String date;
	private int hour;
	private int minute;
	private String context;
	
	public JournalModel(int id, String title, String date, int hour, int minute, String context) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.context = context;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDate() {
		return this.date;
	}
	
	public String getTime() {
		return String.format("%d:%d", this.hour, this.minute);
	}
	
	public int getHour() {
		return this.hour;
	}
	
	public int getMinute() {
		return this.minute;
	}
	
	public String getContext() {
		return this.context;
	}
	
	public int getID() {
		return this.id;
	}
	
}
