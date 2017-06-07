package userManagement;

class Admin extends UserProfile{

	public Admin(int id, String username, String password, String firstName, String lastName,
			ContactInformation contactInformation) {
		super(id, username, password, firstName, lastName, contactInformation);
	}

	@Override
	public AuthenticationType getAuth() {
		return AuthenticationType.ADMIN;
	}

}
