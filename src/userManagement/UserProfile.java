package userManagement;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import common.Viewable;

public abstract class UserProfile implements Authenticatable, Viewable{
	
	@Override
	public JSONObject showInfo() {
		return null;
	}

	private int id;
	private String username, password;
	private String firstName, lastName;
	private ContactInformation contactInformation;
	private ArrayList<Notification> notifications;
	private boolean loggedIn;	
	
	public UserProfile(int id, String username, String password, String firstName, String lastName,
			ContactInformation contactInformation) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactInformation = contactInformation;
		this.notifications = new ArrayList<>();
		this.loggedIn = false;
		this.id = id;
	}

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
	
	public String getUsername(){
		return this.username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public void changeUserInfo(String password, String firstName, String lastName, String telephoneNumber,
			String emailAddress, String physicalAddress) {
		if (password!=null)
			this.setPassword(password);
		if (firstName!=null)
			this.setFirstName(firstName);
		if (lastName!=null)
			this.setLastName(lastName);
		if (telephoneNumber!=null && emailAddress!=null && physicalAddress!=null){
			ContactInformation contactInformation = new ContactInformation(emailAddress, telephoneNumber, physicalAddress);
			this.setContactInformation(contactInformation);
		}
	}
	
	
	
	
	
}
