package userManagement;

import java.util.Date;

public class Notification {
	private int id;
	private boolean isSeen = false;
	private String body;
	private Date date;
	private String sender;
	private UserProfile reciever;
	
	
	public Notification(){}
	public Notification(boolean seen, String body, Date date, String sender, UserProfile reciever){
		this.isSeen = seen;
		this.body = body;
		this.date = date;
		this.sender = sender;
		this.reciever = reciever;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public boolean getIsSeen(){
		return this.isSeen;
	}
	
	public void setIsSeen(boolean isSeen){
		this.isSeen = isSeen;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public String getBody(){
		return this.body;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public void setSender(String sender){
		this.sender = sender;
	}
	
	public String getSender(){
		return this.sender;
	}
	
	public void setReciever(UserProfile reciever){
		this.reciever = reciever;
	}
	
	public UserProfile getReciever(){
		return this.reciever;
	}

}
