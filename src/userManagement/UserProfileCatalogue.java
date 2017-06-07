package userManagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;

public class UserProfileCatalogue {
	
	ArrayList<UserProfile> userList;
	int lastID;
	
	private static UserProfileCatalogue userProfileCatalogue = new UserProfileCatalogue();
	
	public static UserProfileCatalogue getCatalogue(){
		return UserProfileCatalogue.userProfileCatalogue;
	}
	
	private UserProfileCatalogue() {
		this.lastID = 0;
		this.userList = new ArrayList<>();
	}
	
	public UserProfile getByID(int userID) throws InvalidArgumentException{
		for (int i=0;i<this.userList.size();i++){
			UserProfile currentUser = this.userList.get(i);
			if (currentUser.getId() == userID){
				return currentUser;
			}
		}
		throw new InvalidArgumentException(Constants.NO_SUCH_USER);
	}
	
	public UserProfile findUser(String username) throws InvalidArgumentException{
		for (int i=0;i<this.userList.size();i++){
			UserProfile currentUser = this.userList.get(i);
			if (username.equals(currentUser.getUsername())){
				return currentUser;
			}
		}
		throw new InvalidArgumentException(Constants.NO_SUCH_USER);
	}
	
	public int getUserID(String username) throws InvalidArgumentException{
		UserProfile user = this.findUser(username);
		if (user != null)
			return user.getId();
		else
			throw new InvalidArgumentException(Constants.NO_SUCH_USER);
	}
	
	public JSONObject getUserInfo(int userID) throws InvalidArgumentException{
		UserProfile user = this.getByID(userID);
		return user.showInfo();
	}
	
	public void addUser(UserProfile userProfile){
		this.userList.add(userProfile);
		adjustLastID();
	}

	private void adjustLastID() {
		int maxID = 0;
		for (int i=0;i<userList.size();i++){
			int currentID = userList.get(i).getId();
			if (currentID > maxID)
				maxID = currentID;
		}
		this.lastID = maxID + 1;
	}
	
	public ArrayList<UserProfile> search(){
		return null;
	}
	
	public void logInUser(String username, String password) throws InvalidArgumentException{
		UserProfile user;
		user = UserProfileCatalogue.getCatalogue().findUser(username);
		user.logIn(password);
	}
	
	public void logOutUser(int userID) throws InvalidArgumentException{
		this.getByID(userID).logOut();
	}
	
	public UserProfile createIntraOrganisationUser(String username, String password, String firstName, String lastName, 
			String telephoneNumber, String emailAddress, String physicalAddress, AuthenticationType authRole) throws InvalidArgumentException{
		if (!checkValidUser(username, password)){
			throw new InvalidArgumentException(Constants.INVALID_INFO);
		}
		ContactInformation contactInformation = new ContactInformation(emailAddress, telephoneNumber, physicalAddress);
		UserProfile newUser = null;
		if (authRole == AuthenticationType.EMPLOYEE)
			newUser = new Employee(getNextID(), username, password, firstName, lastName, contactInformation);
		else if (authRole == AuthenticationType.ADMIN)
			newUser = new Admin(getNextID(), username, password, firstName, lastName, contactInformation);
		else if (authRole == AuthenticationType.MANAGER)
			newUser = new Manager(getNextID(), username, password, firstName, lastName, contactInformation, authRole);
		
		return newUser;
	}
	
	private boolean checkValidUser(String username, String password){
		//Check for duplicate username
		for (int i=0;i<userList.size();i++){
			if (username.equals(userList.get(i).getUsername()))
				return false;
		}
		return checkValidUserInfo(password);
	}
	
	public UserProfile createCustomer(String username, String password, String firstName, String lastName, 
			String telephoneNumber, String emailAddress, String physicalAddress) throws InvalidArgumentException{
		if (!checkValidUser(username, password)){
			throw new InvalidArgumentException(Constants.INVALID_USER);
		}
		ContactInformation contactInformation = new ContactInformation(emailAddress, telephoneNumber, physicalAddress);
		Customer customer = new Customer(getNextID(), username, password, firstName, lastName, contactInformation);
		addUser(customer);
		return customer;
	}
	
	private int getNextID() {
		return this.lastID+1;
	}

	public ArrayList<UserProfile> getManagersByRole(AuthenticationType role){
		return null;
	}
	
	private boolean checkValidUserInfo(String password){
		if (password.length() < 8)
			return false;
		return true;
	}
	
	public boolean changeUserInfo(int userID, String password, String firstName, String lastName, 
					String telephoneNumber, String emailAddress, String physicalAddress) throws NonExistentEntityException, InvalidArgumentException{
		UserProfile userProfile = this.getByID(userID);
		if (userProfile == null){
			throw new NonExistentEntityException(Constants.NO_SUCH_USER);
		}
		boolean valid = checkValidUserInfo(password);
		if (valid){
			userProfile.changeUserInfo(password, firstName, lastName, 
					telephoneNumber, emailAddress, physicalAddress);
			return true;
		}
		return false;
	}
	
	
}
