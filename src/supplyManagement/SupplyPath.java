package supplyManagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Constants;
import common.Summarizable;
import common.Viewable;
import salesManagement.Component;
import salesManagement.ProductElement;
import salesManagement.ProductElementPrinterService;
import userManagement.ContactInformation;

public class SupplyPath implements Viewable, Summarizable{
	String supplierName;
	String name;
	ArrayList<Component> componentsList;
	private int id;
	ContactInformation contactInformation;
	
	public SupplyPath(String supplierName, String name, ArrayList<Component> componentsList, ContactInformation contactInformation) {
		super();
		this.supplierName = supplierName;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public JSONObject showSummary() {
		HashMap<String, String> map = new HashMap<>();
		map.put(Constants.ID, String.valueOf(this.getId()));
		map.put("name", this.name);
		map.put("supplierName", this.supplierName);
		return new JSONObject(map);
	}

	@Override
	public JSONObject showInfo() {
		JSONObject result = this.contactInformation.showInfo();
		result.put(Constants.ID, String.valueOf(this.getId()));
		result.put("name", this.name);
		result.put("supplierName", this.supplierName);
		ArrayList<String> componentNames = new ProductElementPrinterService().
				getNamesList((ArrayList<ProductElement>)(ArrayList<?>)this.componentsList);
		String componentsConcat = String.join(", ", componentNames);
		result.put("components", componentsConcat);
		return result;
	}
	
	
}
