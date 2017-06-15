package userManagement;

class Customer extends UserProfile{

	public Customer(String username, String password, String firstName, String lastName,
			ContactInformation contactInformation) {
		super(username, password, firstName, lastName, contactInformation);
	}

	@Override
	public AuthenticationType getAuthRole() {
		return AuthenticationType.CUSTOMER;
	}

}
