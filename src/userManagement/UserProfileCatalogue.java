package userManagement;

import java.util.ArrayList;

public class UserProfileCatalogue {
	
	ArrayList<UserProfile> usersList;
	
	private static UserProfileCatalogue userProfileCatalogue = new UserProfileCatalogue();
	
	public static UserProfileCatalogue getCatalogue(){
		return UserProfileCatalogue.userProfileCatalogue;
	}
	
	private UserProfileCatalogue() {
		this.usersList = new ArrayList<>();
	}
	
	public UserProfile getByID(int userID){
		for (int i=0;i<this.usersList.size();i++){
			UserProfile currentUser = this.usersList.get(i);
			if (currentUser.getId() == userID){
				return currentUser;
			}
		}
		return null;
	}
	
	public void addUser(UserProfile userProfile){
		this.usersList.add(userProfile);
	}
	
	public ArrayList<UserProfile> search(){
		return null;
	}
	
	public UserProfile createIntraOrganisationUser(){
		return null;
	}
	
	private boolean checkValidUser(){
		return true;
	}
	
	public UserProfile createCustomer(){
		return null;
	}
	
	public ArrayList<UserProfile> getManagersByRole(){
		return null;
	}
	
	private boolean checkValidUserInfo(){
		return true;
	}
	
	
}
