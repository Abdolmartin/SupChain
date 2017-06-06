package userManagement;

class Manager extends UserProfile{

	@Override
	public AuthenticationType getAuth() {
		return AuthenticationType.MANAGER;
	}

}
