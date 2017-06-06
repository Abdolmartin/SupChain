package userManagement;

import java.util.ArrayList;

public class UserProfile {
	
	private int id;
	private String username, password;
	private String firstName, lastName;
	private ContactInformation info;
	private ArrayList<Notification> notifications;

	public int getId() {
		return this.id;
	}
	
	public ArrayList<Notification> getNotificationsList(){
		return this.notifications;
	}
	
	public void logIn(){
		
	}
	
	public void logOut(){
		
	}
	
	public boolean authenticate(){
		return false;
	}
}
