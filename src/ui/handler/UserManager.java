package ui.handler;

import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;
import userManagement.AuthenticationType;
import userManagement.UserProfile;
import userManagement.UserProfileCatalogue;

public class UserManager {
	
	public String logIn(String username, String password){
		try {
			UserProfileCatalogue.getCatalogue().logInUser(username, password);
		} catch (InvalidArgumentException e) {
			return e.getMessage();
		}
		return Constants.SUCCESS;
	}
	public int getUserID(String username){
		try {
			return UserProfileCatalogue.getCatalogue().getUserID(username);
		} catch (InvalidArgumentException e) {
			return -1;
		}
	}
	public JSONObject getInfo(int userID){
		try {
			return UserProfileCatalogue.getCatalogue().getUserInfo(userID);
		} catch (Exception e) {
			HashMap<String, String> map = new HashMap<>();
			map.put("error", Constants.NO_SUCH_USER);
			return new JSONObject(map);
		}
	}
	
	public String getUserAuthenticationLevel(int userID){
		try {
			AuthenticationType authLevel = UserProfileCatalogue.getCatalogue().getUserAuthenticationLevel(userID);
			switch(authLevel){
				case MANAGER:
					return Constants.MANAGER;
				case EMPLOYEE:
					return Constants.EMPLOYEE;
				case ADMIN:
					return Constants.ADMIN;
				case CUSTOMER:
					return Constants.CUSTOMER;
				default:
					return Constants.MANAGER;
			}
		} catch (InvalidArgumentException e) {
		}
		return Constants.NO_SUCH_USER;
	}
	
	public String createUser(String role, String username, String password, String firstName, String lastName, 
			String telephoneNumber, String emailAddress, String physicalAddress){
		if (role.equals(Constants.CUSTOMER)){
			try {
				UserProfileCatalogue.getCatalogue().createCustomer(username, password, firstName, lastName, telephoneNumber, emailAddress, physicalAddress);
			} catch (InvalidArgumentException e) {
				return e.getMessage();
			}
		}
		else{
			AuthenticationType authRole = AuthenticationType.MANAGER;
			switch(role)
			{
				case Constants.MANAGER:
					authRole = AuthenticationType.MANAGER;
					break;
				case "production_manager":
					authRole = AuthenticationType.PRODUCTION_MANAGER;
					break;
				case "warehouse_manager":
					authRole = AuthenticationType.WAREHOUSE_MANAGER;
					break;
				case Constants.EMPLOYEE:
					authRole = AuthenticationType.EMPLOYEE;
					break;
				case Constants.ADMIN:
					authRole = AuthenticationType.ADMIN;
					break;
				default:
					break;
			}
			try {
				UserProfileCatalogue.getCatalogue().createIntraOrganisationUser(username, password, firstName,
						lastName, telephoneNumber, emailAddress, physicalAddress, authRole);
			} catch (InvalidArgumentException e) {
				return e.getMessage();
			}			
		}
		return Constants.SUCCESS;
	}
	public String logOut(int userID){
		try {
			UserProfileCatalogue.getCatalogue().logOutUser(userID);
		} catch (InvalidArgumentException e) {
			return e.getMessage();
		}
		return Constants.SUCCESS;
	}
}
