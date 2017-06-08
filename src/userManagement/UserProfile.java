package userManagement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import common.Viewable;
import exceptions.InvalidArgumentException;

public abstract class UserProfile implements Authenticatable, Viewable{
	
	@Override
	public JSONObject showInfo() {
		HashMap<String, String> map = new HashMap<>();
		map.put("username", username);
		map.put("firstName", firstName);
		map.put("lastName", lastName);
		map.put("telephone", contactInformation.getTelephoneNumber());
		map.put("email", contactInformation.getEmailAddress());
		map.put("address", contactInformation.getPhysicalAddress());
		return new JSONObject(map);
	}

	private int id;
	private String username, password;
	private String firstName, lastName;
	private ContactInformation contactInformation;
	private ArrayList<Notification> notifications;
	private boolean loggedIn;
	
	public UserProfile(){}
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
	
	public void logIn(String givenPassword) throws InvalidArgumentException{
		if (this.authenticate(givenPassword)){
			this.loggedIn = true;
			ActionLog actionLog = new ActionLog(this.getUsername(), "logIn", new Date());
			ActionLogCatalogue.getCatalogue().addLog(actionLog);
		} else
			throw new InvalidArgumentException("wrongPass");
	}
	
	public void logOut(){
		this.loggedIn = false;
		ActionLog actionLog = new ActionLog(this.getUsername(), "logOut", new Date());
		ActionLogCatalogue.getCatalogue().addLog(actionLog);
	}
	
	public boolean authenticate(String givenPassword){
		if (this.password.equals(givenPassword)){
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
			String emailAddress, String physicalAddress, String oldPass) throws InvalidArgumentException {
		if (!authenticate(oldPass)){
			throw new InvalidArgumentException(Constants.WRONG_PASS);
		}
		if (!password.equals(""))
			this.setPassword(password);
		if (!firstName.equals(""))
			this.setFirstName(firstName);
		if (!lastName.equals(""))
			this.setLastName(lastName);
		
		if (telephoneNumber.equals(""))
			telephoneNumber = contactInformation.getTelephoneNumber();
		if (emailAddress.equals(""))
			emailAddress = contactInformation.getEmailAddress();
		if (physicalAddress.equals(""))
			physicalAddress = contactInformation.getPhysicalAddress();
		
		ContactInformation contactInformation = new ContactInformation(emailAddress, telephoneNumber, physicalAddress);
		this.setContactInformation(contactInformation);
		
		ActionLog actionLog = new ActionLog(this.getUsername(), "changeInfo", new Date());
		ActionLogCatalogue.getCatalogue().addLog(actionLog);
	}
	
	
	
	
	
}
