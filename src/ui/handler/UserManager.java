package ui.handler;

import java.util.HashMap;

import org.json.simple.JSONObject;

import exceptions.InvalidArgumentException;
import userManagement.UserProfile;
import userManagement.UserProfileCatalogue;

public class UserManager {
	public String logIn(String username, String password){
		UserProfile user = UserProfileCatalogue.getCatalogue().findUser(username);
		if (user == null){
			return "noUser";
		}
		try{
			user.logIn(password);
		}catch(InvalidArgumentException e){
			return e.getMessage();
		}
		return "success";
	}
	public int getUserID(String username){
		try {
			return UserProfileCatalogue.getCatalogue().getUserID(username);
		} catch (InvalidArgumentException e) {
			return -1;
		}
	}
	public JSONObject getInfo(int userID){
		return UserProfileCatalogue.getCatalogue().getUserInfo(userID);
	}
}
