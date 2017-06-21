package ui.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import exceptions.NonExistentEntityException;
import ui.CustomerPortal;
import userManagement.ActionLog;
import userManagement.ActionLogCatalogue;
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
			map.put("error", Constants.NO_SUCH_ENTITY);
			return new JSONObject(map);
		}
	}
	
	public String changeUserInfo(int userID, String password, String firstName, String lastName, String telephoneNumber,
			String emailAddress, String physicalAddress, String oldPass){
		try {
			UserProfileCatalogue.getCatalogue().changeUserInfo(userID, password, firstName, lastName, telephoneNumber, emailAddress, physicalAddress, oldPass);
		} catch (NonExistentEntityException e) {
			return Constants.NO_SUCH_ENTITY;
		} catch (InvalidArgumentException e1){
			return Constants.INVALID_INFO;
		}
		return Constants.SUCCESS;
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
		return Constants.NO_SUCH_ENTITY;
	}
	
	public String createUser(String role, String username, String password, String firstName, String lastName, 
			String telephoneNumber, String emailAddress, String physicalAddress, int creatorID){
		String creatorName = "";
		try {
			creatorName = UserProfileCatalogue.getCatalogue().getUsername(creatorID);
		} catch (InvalidArgumentException e1) {
			creatorName = Constants.NONE;
		}  		
		if (role.equals(Constants.CUSTOMER)){
			try {
				UserProfileCatalogue.getCatalogue().createCustomer(username, password, firstName, lastName, telephoneNumber, emailAddress, physicalAddress);
				ActionLog actionLog = new ActionLog(creatorName, "register:"+username, new Date());
				ActionLogCatalogue.getCatalogue().addLog(actionLog);
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
				ActionLog actionLog = new ActionLog(creatorName, "create:"+username, new Date());
				ActionLogCatalogue.getCatalogue().addLog(actionLog);
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
	
	public ArrayList<JSONObject> getLogs(int userID){
		String userName = "";
		try {
			userName = UserProfileCatalogue.getCatalogue().getUsername(userID);
			return ActionLogCatalogue.getCatalogue().showSearchResults(userName);
		} catch (InvalidArgumentException e) {
			HashMap<String, String> map = new HashMap<>();
			map.put("error", Constants.NO_SUCH_ENTITY);
			ArrayList<JSONObject> result = new ArrayList<>();
			result.add(new JSONObject(map));
			return result;
		}
		
	}
	public ArrayList<JSONObject> getAllLogs() {
		return ActionLogCatalogue.getCatalogue().showSearchResults("");
	}
	public ArrayList<JSONObject> getNotifications(int userID) {
		UserProfile userProfile = null;
		try {
			userProfile = UserProfileCatalogue.getCatalogue().getByID(userID);
		} catch (InvalidArgumentException e) {
			HashMap<String, String> map = new HashMap<>();
			map.put("error", Constants.NO_SUCH_ENTITY);
			ArrayList<JSONObject> result = new ArrayList<>();
			result.add(new JSONObject(map));
			return result;
		}
		return userProfile.viewNotifications();
	}
}
