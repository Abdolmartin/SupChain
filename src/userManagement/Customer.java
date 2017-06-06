package userManagement;

class Customer extends UserProfile{

	@Override
	public AuthenticationType getAuth() {
		return AuthenticationType.CUSTOMER;
	}

}
