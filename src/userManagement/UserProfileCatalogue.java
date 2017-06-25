package userManagement;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import common.Constants;
import database.UserProfileRepository;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;

public class UserProfileCatalogue {
	
	ArrayList<UserProfile> userList;
	private UserProfileRepository repo = new UserProfileRepository();
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
		throw new InvalidArgumentException(Constants.NO_SUCH_ENTITY);
	}
	
	public UserProfile findUser(String username) throws InvalidArgumentException{
		for (int i=0;i<this.userList.size();i++){
			UserProfile currentUser = this.userList.get(i);
			if (username.equals(currentUser.getUsername())){
				return currentUser;
			}
		}
		System.out.println("NOTFOUND");
		throw new InvalidArgumentException(Constants.NO_SUCH_ENTITY);
	}
	
	public String getUsername(int userID) throws InvalidArgumentException{
		return this.getByID(userID).getUsername();
	}
	
	public int getUserID(String username) throws InvalidArgumentException{
		UserProfile user = this.findUser(username);
		if (user != null)
			return user.getId();
		else
			throw new InvalidArgumentException(Constants.NO_SUCH_ENTITY);
	}
	
	public JSONObject getUserInfo(int userID) throws InvalidArgumentException{
		UserProfile user = this.getByID(userID);
		return user.showInfo();
	}
	
	public void addUser(UserProfile userProfile){
		this.userList.add(userProfile);
		this.repo.save(userProfile);
	}
	
	public ArrayList<UserProfile> search(){
		return null;
	}
	
	public AuthenticationType getUserAuthenticationLevel(int userID) throws InvalidArgumentException{
		UserProfile user = this.getByID(userID);
		return user.getAuthRole();
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
			newUser = new Employee(username, password, firstName, lastName, contactInformation);
		else if (authRole == AuthenticationType.ADMIN)
			newUser = new Admin(username, password, firstName, lastName, contactInformation);
		else
			newUser = new Manager(username, password, firstName, lastName, contactInformation, authRole);
		addUser(newUser);
		return newUser;
	}
	
	private boolean checkValidUser(String username, String password){
		//Check for duplicate username
		for (int i=0;i<userList.size();i++){
			if (username.equals(userList.get(i).getUsername()))
				return false;
		}
		if (username.equals(Constants.SYSTEM_ACTOR))
			return false;
		return checkValidUserInfo(password);
	}
	
	public UserProfile createCustomer(String username, String password, String firstName, String lastName, 
			String telephoneNumber, String emailAddress, String physicalAddress) throws InvalidArgumentException{
		if (!checkValidUser(username, password)){
			throw new InvalidArgumentException(Constants.INVALID_INFO);
		}
		ContactInformation contactInformation = new ContactInformation(emailAddress, telephoneNumber, physicalAddress);
		Customer customer = new Customer(username, password, firstName, lastName, contactInformation);
		addUser(customer);
		return customer;
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
					String telephoneNumber, String emailAddress, String physicalAddress, String oldPass) throws NonExistentEntityException, InvalidArgumentException{
		UserProfile userProfile = this.getByID(userID);
		if (userProfile == null){
			throw new NonExistentEntityException(Constants.NO_SUCH_ENTITY);
		}
		userProfile.changeUserInfo(password, firstName, lastName, 
					telephoneNumber, emailAddress, physicalAddress, oldPass);
		return repo.update(userProfile);
	}
	
	public boolean addNotification(int userID, Notification notification) throws InvalidArgumentException{
		UserProfile userProfile = this.getByID(userID);
		userProfile.addNotification(notification);
		return this.repo.update(userProfile);
	}
	
	public void initialise(){
		this.userList = this.repo.getAll();
	}
}
