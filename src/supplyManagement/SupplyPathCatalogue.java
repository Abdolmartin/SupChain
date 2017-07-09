package supplyManagement;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import exceptions.InvalidArgumentException;
import salesManagement.Component;
import salesManagement.ProductElementCatalogue;
import userManagement.ContactInformation;

public class SupplyPathCatalogue {
	private ArrayList<SupplyPath> supplyPathList;
	
	private static SupplyPathCatalogue supplyPathCatalogue = new SupplyPathCatalogue();
	
	public static SupplyPathCatalogue getCatalogue(){
		return SupplyPathCatalogue.supplyPathCatalogue;
	}
	
	private SupplyPathCatalogue(){
		this.supplyPathList = new ArrayList<>();
	}
	
	public SupplyPath getByID(int id) throws InvalidArgumentException{
		for (int i=0;i<this.supplyPathList.size();i++){
			SupplyPath currentSupplyPath = this.supplyPathList.get(i);
			if (currentSupplyPath.getId() == id){
				return currentSupplyPath;
			}
		}
		throw new InvalidArgumentException(Constants.NO_SUCH_ENTITY);
	}
	
	public void addSupplyPath(SupplyPath supplyPath){
		this.supplyPathList.add(supplyPath);
	}
	
	public void createSupplyPath(String supplierName, String name,
			int[] componentIDs, String emailAddress, String telephoneNumber, String physicalAddress){
		ContactInformation contactInformation = new ContactInformation(emailAddress, telephoneNumber, physicalAddress);
		ArrayList<Component> componentsList = new ArrayList<>();
		for (int i=0;i<componentIDs.length;i++){
			componentsList.add((Component)ProductElementCatalogue.getCatalogue().getByID(componentIDs[i]));
		}
		SupplyPath supplyPath = new SupplyPath(supplierName, name, componentsList, contactInformation);
		this.addSupplyPath(supplyPath);
	}
	
	public ArrayList<SupplyPath> search(){
		return this.supplyPathList;
	}
	
	public List<JSONObject> showSearchSummary(){
		List<SupplyPath> searchResult = this.search();
		List<JSONObject> results = new ArrayList<>();
		
		for (int i=0;i<searchResult.size();i++){
			results.add(searchResult.get(i).showSummary());
		}
		return results;
	}
	
	public JSONObject getSupplyPathInfo(int id) throws InvalidArgumentException{
		SupplyPath supplyPath = this.getByID(id);
		return supplyPath.showInfo();
	}
}
