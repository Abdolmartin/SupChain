package userManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import common.Viewable;
import exceptions.InvalidArgumentException;

public abstract class UserProfile implements Authenticatable, Viewable{
	private int id;
	private String username, password;
	private String firstName, lastName;
	private ContactInformation contactInformation;
	private List<Notification> notifications;
	private boolean loggedIn;
	
	public UserProfile(){}
	public UserProfile(String username, String password, String firstName, String lastName,
			ContactInformation contactInformation) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactInformation = contactInformation;
		this.notifications = new ArrayList<Notification>();
		this.loggedIn = false;
	}
	
	@Override
	public JSONObject showInfo() {
		JSONObject result = contactInformation.showInfo();
		HashMap<String, String> map = new HashMap<>();
		result.put("username", username);
		result.put("firstName", firstName);
		result.put("lastName", lastName);
		result.put("id", String.valueOf(this.getId()));
		return result;
	}


	public int getId() {
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public List<Notification> getNotificationsList(){
		return this.notifications;
	}
	
	public List<JSONObject> viewNotifications(){
		List<Notification> sortedNotifs = new ArrayList<>(this.notifications);
		Collections.sort(sortedNotifs, new Comparator<Notification>() {
			@Override
			public int compare(Notification o1, Notification o2) {
				if (o1.getIsSeen() == true && o2.getIsSeen() == false)
					return -1;
				if (o2.getIsSeen() == true && o1.getIsSeen() == false)
					return 1;
				return 0;
			}
		});
		List<JSONObject> result = new ArrayList<>();
		for (int i=0;i<sortedNotifs.size();i++){
			result.add(sortedNotifs.get(i).showInfo());
		}
		return result;
	}
	
	public void addNotification(Notification n){
		n.setReciever(this);
		this.notifications.add(n);
	}
	
	public void logIn(String givenPassword) throws InvalidArgumentException{
		if (this.authenticate(givenPassword)){
			this.loggedIn = true;
			ActionLog actionLog = new ActionLog(this.getUsername(), "logIn", new Date());
			ActionLogCatalogue.getCatalogue().addLog(actionLog);
		} else
			throw new InvalidArgumentException(Constants.WRONG_PASS);
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
	
	public void setUsername(String username){
		this.username = username;
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
	
	public String getPassword(){
		return this.password;
	}
	
	public void setLoggedIn(boolean logg){
		this.loggedIn = logg;
	}
	
	public boolean getLoggedIn(){
		return this.loggedIn;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
	
	public void setNotifications(List<Notification> list){
		this.notifications = list;
	}
	
	public List<Notification> getNotifications(){
		return this.notifications;
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
	@Override
	public AuthenticationType getAuthRole() {
		return null;
	}	
	
}
