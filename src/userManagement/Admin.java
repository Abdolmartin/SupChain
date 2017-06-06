package userManagement;

class Admin extends UserProfile{

	@Override
	public AuthenticationType getAuth() {
		return AuthenticationType.ADMIN;
	}

}
