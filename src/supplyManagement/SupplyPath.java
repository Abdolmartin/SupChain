package supplyManagement;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import common.Summarizable;
import common.Viewable;
import salesManagement.Component;
import userManagement.ContactInformation;

public class SupplyPath implements Viewable, Summarizable{
	String supplierName;
	ArrayList<Component> componentsList;
	int id;
	ContactInformation contactInformation;
	
	public SupplyPath(String supplierName, ArrayList<Component> componentsList, ContactInformation contactInformation) {
		super();
		this.supplierName = supplierName;
		this.componentsList = componentsList;
		this.contactInformation = contactInformation;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public ArrayList<Component> getComponentsList() {
		return componentsList;
	}

	public void setComponentsList(ArrayList<Component> componentsList) {
		this.componentsList = componentsList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	@Override
	public JSONObject showSummary() {
		
		return null; 
	}

	@Override
	public JSONObject showInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
