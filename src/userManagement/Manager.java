package userManagement;

class Manager extends UserProfile{
	
	private AuthenticationType authRole;
	
	public Manager(int id, String username, String password, String firstName, String lastName,
			ContactInformation contactInformation, AuthenticationType authRole) {
		super(id, username, password, firstName, lastName, contactInformation);
		if (authRole == AuthenticationType.MANAGER)
			this.authRole = authRole; //TODO Placeholder
		else
			this.authRole = AuthenticationType.MANAGER;
	}

	@Override
	public AuthenticationType getAuth() {
		return this.authRole;
	}

}
