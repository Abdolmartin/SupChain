package userManagement;

class Admin extends UserProfile{

	public Admin(String username, String password, String firstName, String lastName,
			ContactInformation contactInformation) {
		super(username, password, firstName, lastName, contactInformation);
	}

	@Override
	public AuthenticationType getAuthRole() {
		return AuthenticationType.ADMIN;
	}

	
	public void setAuthRole(AuthenticationType atype){
		// do nothing
	}

}
