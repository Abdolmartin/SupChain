package userManagement;


public class Manager extends UserProfile{
	
	private AuthenticationType authRole;
	
	public Manager(){}
	
	public Manager(String username, String password, String firstName, String lastName,
			ContactInformation contactInformation, AuthenticationType authRole) {
		super(username, password, firstName, lastName, contactInformation);
		if (authRole == AuthenticationType.MANAGER || authRole == AuthenticationType.PRODUCTION_MANAGER || 
				authRole == AuthenticationType.WAREHOUSE_MANAGER)
			this.authRole = authRole; //TODO Placeholder
		else
			this.authRole = AuthenticationType.MANAGER;
	}

	@Override
	public AuthenticationType getAuthRole() {
		return this.authRole;
	}
	
	
	public void setAuthRole(AuthenticationType auth) {
		this.authRole = auth;
	}

	
}
