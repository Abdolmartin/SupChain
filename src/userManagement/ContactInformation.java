package userManagement;

public class ContactInformation {
	private int id;
	private String emailAddress;
	private String telephoneNumber;
	private String physicalAddress;
	
	public ContactInformation(){}
	
	public ContactInformation(String emailAddress, String telephoneNumber, String physicalAddress) {
		this.emailAddress = emailAddress;
		this.telephoneNumber = telephoneNumber;
		this.physicalAddress = physicalAddress;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id = id;
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
