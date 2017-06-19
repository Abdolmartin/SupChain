package salesManagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;

import common.Summarizable;
import common.Viewable;
import productManagement.ProductionProcess;
import exceptions.InvalidArgumentException;

public abstract class ProductElement implements Viewable, Summarizable{
	
	private int id;
	private String name;
	private int invLowerBound = 0;
	private int invUpperBound = Integer.MAX_VALUE;
	private ArrayList<ProductElementItem> productElementItemList;
	
	@Override
	public JSONObject showInfo(){
		HashMap<String, String> map = new HashMap<>();
		map.put("name", this.name);
		map.put("type", this.getType());
		map.put("latest price", String.valueOf(this.getLatestPrice()));
		map.put("id", String.valueOf(this.getId()));
		return new JSONObject(map);
	}
	
	@Override
	public JSONObject showSummary(){
		return this.showInfo();
	}
	
	public ProductElement(String name, int invLowerBound, int invUpperBound) {
		this.name = name;
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

	public void setId(int id) {
		this.id = id;
	}
	
	public abstract String getType();
	
	public void addItem(ProductElementItem pElementItem) throws InvalidArgumentException{
		if (checkItemValidity(pElementItem)){
			this.productElementItemList.add(pElementItem);
			//TODO status? maybe it's handled elsewhere
		}
		else{
			throw new InvalidArgumentException();
		}
		
	}
	
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
		if (high <= low){ //equal is bad too, because it would send notifications at every damn change.
			throw new InvalidArgumentException("high <=  low");
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
		//TODO
		return null;
	}
	
	public ArrayList<ProductionProcess> getConsumingProcesses(){
		//TODO
		return null;
	}
}
