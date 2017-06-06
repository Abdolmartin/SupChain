package userManagement;

public class ContactInformation {
	String emailAddress;
	String telephoneNumber;
	String physicalAddress;
	
	public ContactInformation(String emailAddress, String telephoneNumber, String physicalAddress) {
		this.emailAddress = emailAddress;
		this.telephoneNumber = telephoneNumber;
		this.physicalAddress = physicalAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getPhysicalAddress() {
		return physicalAddress;
	}

	public void setPhysicalAddress(String physicalAddress) {
		this.physicalAddress = physicalAddress;
	}
	
}
