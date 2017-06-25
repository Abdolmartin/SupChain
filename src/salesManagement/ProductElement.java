package salesManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import common.Summarizable;
import common.Viewable;
import exceptions.InvalidArgumentException;
import productionInfo.ProductionProcess;

public abstract class ProductElement implements Viewable, Summarizable{
	
	private int id;
	private String name;
	private String description;
	private int invLowerBound = 0;
	private int invUpperBound = Integer.MAX_VALUE;
	protected List<ProductElementItem> productElementItemList;
	
	@Override
	public JSONObject showInfo(){
		HashMap<String, String> map = new HashMap<>();
		map.put("name", this.name);
		map.put("price", String.valueOf(this.getLatestPrice()));
		map.put(Constants.ID, String.valueOf(this.getId()));
		map.put("desc", this.description);
		return new JSONObject(map);
		
	}
	
	@Override
	public JSONObject showSummary(){
		HashMap<String, String> map = new HashMap<>();
		map.put("name", this.name);
		map.put("type", this.getType());
		map.put("price", String.valueOf(this.getLatestPrice()));
		map.put(Constants.ID, String.valueOf(this.getId()));
		return new JSONObject(map);
	}
	
	public ProductElement(){}
	
	public ProductElement(String name, int invLowerBound, int invUpperBound, String description) {
		this.name = name;
		this.description = description;
		this.productElementItemList = new ArrayList<>();
		try {
			this.defineInventoryBounds(invLowerBound, invUpperBound);
		} catch (InvalidArgumentException e) {
			this.invLowerBound = 0;
			this.invUpperBound = Integer.MAX_VALUE;
		}
		
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String des){
		this.description = des;
	}
	
	public List<ProductElementItem> getProductElementItemList() {
		return productElementItemList;
	}
	
	public ArrayList<JSONObject> viewItems(){
		ArrayList<JSONObject> result = new ArrayList<>();
		ArrayList<ProductElementItem> items = (ArrayList<ProductElementItem>) this.getProductElementItemList();
		for (int i=0;i<items.size();i++){
			result.add(items.get(i).showInfo());
		}
		
		return result;
	}

	public void setProductElementItemList(List<ProductElementItem> productElementItemList) {
		this.productElementItemList = productElementItemList;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInvLowerBound() {
		return invLowerBound;
	}

	public void setInvLowerBound(int invLowerBound) {
		this.invLowerBound = invLowerBound;
	}

	public int getInvUpperBound() {
		return invUpperBound;
	}

	public void setInvUpperBound(int invUpperBound) {
		this.invUpperBound = invUpperBound;
	}

	public abstract String getType();
	
	// Factory method
	public abstract void addItem(ItemStatus initialStatus) throws InvalidArgumentException;
	
	public abstract boolean checkItemValidity(ProductElementItem pElementItem);
	
	public int getAvailableQuantity(){
		int quantity = 0;
		for (int i=0;i<this.productElementItemList.size();i++){
			if (this.productElementItemList.get(i).isAvailable())
				quantity += 1;
		}
		return quantity;
	}
	
	public void checkInventoryBounds(){
		int quantity = this.getAvailableQuantity();
		if (quantity < this.invLowerBound){
			handleLowerThanBound();
		}
		else if (quantity > this.invUpperBound){
			handleHigherThanBound();
		}
	}
	
	public abstract void handleLowerThanBound();
	
	public void handleHigherThanBound(){
		
	}
	
	public void defineInventoryBounds(int low, int high) throws InvalidArgumentException{
		if (high <= low && !(low == -1 && high == -1)){ //equal is bad too, because it would send notifications at every damn change.
			throw new InvalidArgumentException(Constants.INVALID_INFO);
		}
		if (low != -1)
			this.invLowerBound = low;
		if (high != -1)
			this.invUpperBound = high;
	}
	
	public double getLatestPrice(){
		int listSize = this.productElementItemList.size();
		try{
			return this.productElementItemList.get(listSize-1).getPrice();
		}catch (IndexOutOfBoundsException e){
			return -1;
		}
	}
	
	public abstract void changeInventory();
	
	public ArrayList<ProductionProcess> getProducingProcesses(){
		// TODO 
		return null;
	}
	
	public ArrayList<ProductionProcess> getConsumingProcesses(){
		// TODO 
		return null;
	}
}
