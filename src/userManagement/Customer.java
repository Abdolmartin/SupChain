package userManagement;

public class Customer extends UserProfile{

	public Customer(String username, String password, String firstName, String lastName,
			ContactInformation contactInformation) {
		super(username, password, firstName, lastName, contactInformation);
	}

	@Override
	public AuthenticationType getAuthRole() {
		return AuthenticationType.CUSTOMER;
	}

	
	public void setAuthRole(AuthenticationType atype){
		// do nothing
	}

}
