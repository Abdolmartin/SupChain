package userManagement;

import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import common.Viewable;

public class ContactInformation implements Viewable{
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

	@Override
	public JSONObject showInfo() {
		HashMap<String, String> map = new HashMap<>();
		map.put("email", this.emailAddress);
		map.put("telephone", this.telephoneNumber);
		map.put("address", this.physicalAddress);
		return new JSONObject(map);
	}
	
}
