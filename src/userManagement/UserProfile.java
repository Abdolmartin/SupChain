package userManagement;

import java.util.ArrayList;

public abstract class UserProfile implements Authenticatable{
	
	private int id;
	private String username, password;
	private String firstName, lastName;
	private ContactInformation contactInformation;
	private ArrayList<Notification> notifications;
	private boolean loggedIn;

	public int getId() {
		return this.id;
	}
	
	public ArrayList<Notification> getNotificationsList(){
		return this.notifications;
	}
	
	public void addNotification(Notification n){
		this.notifications.add(n);
	}
	
	public void logIn(){
		this.loggedIn = true;
	}
	
	public void logOut(){
		this.loggedIn = false;
	}
	
	public boolean authenticate(String givenPassword){
		if (this.password == givenPassword){
			return true;
		}
		return false;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	
	
}
