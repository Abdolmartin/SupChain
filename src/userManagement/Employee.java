package userManagement;

class Employee extends UserProfile{

	public Employee(String username, String password, String firstName, String lastName,
			ContactInformation contactInformation) {
		super(username, password, firstName, lastName, contactInformation);
	}

	@Override
	public AuthenticationType getAuthRole() {
		return AuthenticationType.EMPLOYEE;
	}

}
