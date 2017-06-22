package userManagement;

import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import common.Viewable;

public class Notification implements Viewable{
	private int id;
	private boolean isSeen = false;
	private String body;
	private Date date;
	private String sender;
	private UserProfile reciever;
	
	
	public Notification(){}
	public Notification(boolean seen, String body, Date date, String sender){
		this.isSeen = seen;
		this.body = body;
		this.date = date;
		this.sender = sender;
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
	
	@Override
	public JSONObject showInfo() {
		HashMap<String, String> map = new HashMap<>();
		map.put("sender", this.sender);
		map.put("date", this.date.toString());
		map.put("body", this.body);
		map.put("seen", String.valueOf(this.isSeen));
		map.put(Constants.ID, String.valueOf(this.id));
		this.setIsSeen(true);
		return new JSONObject(map);
	}

}
