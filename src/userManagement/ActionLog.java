package userManagement;

import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Viewable;

public class ActionLog implements Viewable{
	String actor;
	String action;
	Date date;
	public ActionLog(){}
	public ActionLog(String actor, String action, Date date) {
		super();
		this.actor = actor;
		this.action = action;
		this.date = date;
	}
	@Override
	public JSONObject showInfo() {
		HashMap<String, String> map = new HashMap<>();
		map.put("actor", actor);
		map.put("action", action);
		map.put("date", date.toString());
		return new JSONObject(map);
	}
	
	public String getActor() {
		return actor;
	}
	public String getAction() {
		return action;
	}
	public Date getDate() {
		return date;
	}
	
	
	
}
