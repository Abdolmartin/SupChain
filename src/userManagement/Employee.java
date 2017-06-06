package userManagement;

class Employee extends UserProfile{

	@Override
	public AuthenticationType getAuth() {
		return AuthenticationType.EMPLOYEE;
	}

}
