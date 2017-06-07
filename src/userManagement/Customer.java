package userManagement;

class Customer extends UserProfile{

	public Customer(int id, String username, String password, String firstName, String lastName,
			ContactInformation contactInformation) {
		super(id, username, password, firstName, lastName, contactInformation);
	}

	@Override
	public AuthenticationType getAuth() {
		return AuthenticationType.CUSTOMER;
	}

}
